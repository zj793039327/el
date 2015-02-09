//明细账
//URL参数对象
var param = Public.getRequest();
var gridSize = getGridSize(), currentCur = param.currency || 'STD', searchFlag = false;
var SYSTEM = parent.SYSTEM, SUBJECT_LEVEL = parent.SYSTEM.RULE.split('-').length;
//存储当前的搜索条件
var filterConditions = {
	fromPeriod: param.periodFrom  || SYSTEM.CURPERIOD,
 	toPeriod: param.periodTo || SYSTEM.CURPERIOD,
 	accountNum: param.number,
 	number: param.number,
 	accountId: param.id,
 	includeItem: param.includeItem ? param.includeItem : 1,
 	currency: currentCur
};
var subjectData = []; //存储当前的科目数据
var subjectTree;
var selectedSubject;
var colNames = [];
var dataField=[];
var VCHID_DATA = [];

$(window).load(function(){
	//初始化查询
	initFilter(filterConditions);
	//初始化grid
	initGrid(currentCur, true);
	//获取显示数据
	getData(currentCur);
	//搜索科目
	initSearch();

	$('#curSubject').width($('.mod-toolbar-top .right').offset().left - $('#curSubject').offset().left - 40);

	$('#grid .link').live('click', function(e){
		e.preventDefault();
		if (!Business.verifyRight('93')) {
			return ;
		}
		parent.VCHID_DATA = VCHID_DATA;
		var url = '/voucher/voucher.jsp?id='+ $(this).data('view');
		parent.tab.addTabItem({tabid : 'voucherDetail' , text : '凭证' , url : url});
	});
	
	$('#refresh').click(function(e){
		e.preventDefault();
		getData(currentCur);
	});

	Public.setAutoHeight($('#subject-box-bd'));
});


function getData(cur){
	$('#curSubject').width($('.mod-toolbar-top .right').offset().left - $('#curSubject').offset().left - 40);
	$.ajax({
		url: '/gl/general?m=queryAccount',
		type: 'POST',
		data: filterConditions,		
		dataType: 'json',
		success: function(data) {
			subjectData = data.data.items;
			initSubjectTree(subjectData);
			subjectTree = $.fn.zTree.getZTreeObj('subsidiary-subject-tree');
			if (subjectData.length === 0) {
				if(searchFlag) {
					Public.tips({ type : 2, content : '没有满足条件的结果哦！' });
				} else {
					Public.tips({ type : 2, content : '当前期间还没有数据哦！' });
				}
				clearData();
			} else {
				var selectNode = subjectTree.getNodesByParam('id',filterConditions.accountId)[0] || subjectTree.getNodes()[0];
				subjectTree.selectNode(selectNode);
				changedCallback(selectNode);
			}
		}
	});
}


/*查询*/
 function initFilter(data){
 	var fromPeriod = data.fromPeriod, toPeriod = data.toPeriod;
 	showSelectedPeroid(fromPeriod, toPeriod);
	var fromPeriodCombo = Business.initPeriodItem($('#filter-fromPeriod'),{
	  	data: function(){
	  		return parent.PERIOD_DATA || top.PERIOD_DATA;
	  	},
	  	defaultSelected: ['yearPeriod',fromPeriod]
	});
	var toPeriodCombo = Business.initPeriodItem($('#filter-toPeriod'),{
		data: function(){
			return parent.PERIOD_DATA || top.PERIOD_DATA;
		},
		defaultSelected: ['yearPeriod',toPeriod]
	});
	var filterFromSubjectCombo = Business.initSubjectItem($('#filter-fromSubject-wrap'));
	var filterToSubjectCombo = Business.initSubjectItem($('#filter-toSubject-wrap'));
	$('#filter-fromLevel').spinbox({min: 1, max: 9});
	$('#filter-toLevel').spinbox({min: 1, max: 9});
	
	$('#more-conditions').append('<li id="flagItem"><input type="checkbox" id="filter-includeItem"/>&nbsp;<label for="filter-includeItem" style="width:auto;">显示辅助核算</label></li>');
	$('#filter-includeItem').attr('checked', !!data.includeItem);
	
	$('#filter-menu').click(function(){
		if(parent.SYSTEM.OpCur) {
			showCurr();
 		} else {
			$('#flagCurr').hide();
		}
	});

	//展开收起更多条件
    $('#conditions-trigger').on('click', function(e){
      e.preventDefault();
	  if (!$(this).hasClass('conditions-expand')) {
		  $('#more-conditions').stop().slideDown(200, function(){
			   $('#conditions-trigger').addClass('conditions-expand').html('收起更多<b></b>');
			   $('#filter-reset').css('display', 'inline');
		  });
	  } else {
		  $('#more-conditions').stop().slideUp(200, function(){
			  $('#conditions-trigger').removeClass('conditions-expand').html('更多条件<b></b>');
			  $('#filter-reset').css('display', 'none');
		  });
	  };	
    });
    $('#conditions-trigger').trigger('click');

	//设为当前期间
 	$('#set-curPeriod').on('click',function(e){
 		fromPeriodCombo.selectByValue(SYSTEM.CURPERIOD);
 		toPeriodCombo.selectByValue(SYSTEM.CURPERIOD);
 		e.preventDefault();
 	});

	//执行查询
	$('#filter-submit').click(function(e) {
		e.preventDefault();
		var fromPeriodVal = fromPeriodCombo.getValue();						//开始期间
		var toPeriodVal = toPeriodCombo.getValue();							//结束期间													
		var fromAccountId = "";
		if(filterFromSubjectCombo.getSelectedRow()) {
			fromAccountId = filterFromSubjectCombo.getSelectedRow().number;			//开始科目(此处为科目编号number)
		}
		var toAccountId = "";
		if(filterToSubjectCombo.getSelectedRow()) {
			toAccountId = filterToSubjectCombo.getSelectedRow().number;				//结束科目(此处为科目编号number)
		}
		var fromLevel = $('#filter-fromLevel').val();							//开始级次
		var toLevel = $('#filter-toLevel').val();			 					//结束级次
		if(parseInt(fromPeriodVal, 10) > parseInt(toPeriodVal, 10)) {
			Public.tips({type:2, content : '开始期间不能大于结束期间！'});
			return;
		}
		if(parseInt(fromLevel) > parseInt(toLevel)) {
			Public.tips({type:2, content : '开始科目级次不能大于结束科目级次！'});
			return;
		}
		if($('#filter-currency').length > 0) {
			currentCur = $('#filter-currency').getCombo().getValue();
		}
		var accountId = filterConditions.accountId;
		filterConditions = {
			fromPeriod: fromPeriodVal,
			toPeriod: toPeriodVal,
			fromAccountId: fromAccountId,
			toAccountId: toAccountId,
			fromLevel: fromLevel,
			toLevel: toLevel,
			includeItem: $('#filter-includeItem').attr('checked') ? 1 : 0,
			currency: currentCur,
			accountId: accountId
		};
		searchFlag = true;
		$('#filter-menu').removeClass('ui-btn-menu-cur');
		showSelectedPeroid(fromPeriodVal, toPeriodVal);
		getData(currentCur);
	});

	 //重置
	 $('#filter-reset').on('click', function(e){
	  	 e.preventDefault();
	  	 fromPeriodCombo.selectByValue(SYSTEM.CURPERIOD);
		 toPeriodCombo.selectByValue(SYSTEM.CURPERIOD);
		 filterFromSubjectCombo.selectByIndex(-1);
		 filterToSubjectCombo.selectByIndex(-1);
		 $('#filter-fromLevel').val('');
		 $('#filter-toLevel').val('');
		 $('#filter-currency').getCombo().selectByValue('STD');
		 $('#filter-includeItem').attr('checked', true);
	 });

}
function showCurr() {
	if($('#flagCurr').length > 0) {
		$('#flagCurr').show();
	} else {
		$('#flagItem').before('<li id="flagCurr"><label for="#filter-currency">币别:</label>&nbsp;<span id="filter-currency"></span></li>');
		$('#filter-currency').combo({
			data: function(){
				var extraData = [{name: '综合本位币', number: 'STD'}, {name: '所有币别', number: 'ALL'}];
				return extraData.concat(parent.SYSTEM.Currency);
			},
			defaultSelected: ['number', currentCur],
			cache: false,
			text: 'name',
			width: 100,
			value: 'number'
		});
	}
}

function showSelectedPeroid(fromPeriod, toPeriod){
	 fromPeriod = fromPeriod.toString();
	 toPeriod = toPeriod.toString();
	 var html = '<strong>' + fromPeriod.substr(0,4) + '年第' +  parseInt(fromPeriod.substr(4),10) + '期</strong>';
	 if (fromPeriod != toPeriod) {
	 	html += ' 至 <strong>' + toPeriod.substr(0,4) + '年第' +  parseInt(toPeriod.substr(4),10) + '期</strong>';
	 };
	 $('#selected-period').html(html);
}


function initGrid(cur, first){
	$("#grid").jqGrid('GridUnload');
	var groupHeaders, width = gridSize.w, height = gridSize.h, datatype = 'json';
	if(cur === 'STD' || cur === parent.SYSTEM.CURRENCY) {
		var autoWidth = width - 100 - 100 - 100 - 100 - 60 - 100 - 35 - 18;
		if(autoWidth > 350) {
			autoWidth = 350;
		}
		if(autoWidth < 150) {
			autoWidth = 150;
		}
		colNames = ['日期', '凭证字号', '摘要', '借方', '贷方', '方向', '余额'];
		dataField = ['ymd','voucherNo','remark','debit','credit','dcType','balance'];
		var	colModel = [
			{name:'ymd',index:'ymd', width:100, align:"center", title:false},
			{name:'voucherNo',index:'voucherNo', width:100, formatter:linkFmatter, title:false},
			{name:'remark',index:'remark', width:autoWidth},
			{name:'debit',index:'debit', width:100, align:"right", formatter:Public.currency, title:false},
			{name:'credit',index:'credit', width:100, align:"right", formatter:Public.currency, title:false},
			{name:'dcType',index:'dcType', width:60, align:"center", title:false},
			{name:'balance',index:'balance', width:100, align:"right", formatter:Public.currency, title:false}
		];
		var gridComplete = function(){
			$("#grid").jqGrid('setGridWidth', gridSize.w);
		}
	} else {
		var hidden = false;
		if(cur === 'ALL') {
			hidden = true;
		}
		height = height - 24;
		var autoWidth = width - 80 - 100 - 80 - 100 - 100 - 100 - 100 - 40 - 100 - 100 - 40;
		if(autoWidth > 350) {
			autoWidth = 350;
		}
		if(autoWidth < 150) {
			autoWidth = 150;
		}
		colNames = ['日期', '凭证字号', '摘要', '币别', '原币', '本位币', '原币', '本位币', '方向', '原币', '本位币'];
		dataField = ['ymd','voucherNo','remark','curRate','debitFor','debit','creditFor','credit','dcType','balanceFor','balance'];
		var	colModel = [
			{name:'ymd',index:'ymd', width:80, align:"center", title:false},
			{name:'voucherNo',index:'voucherNo', width:100, formatter:linkFmatter, title:false},
			{name:'remark',index:'remark', width:autoWidth},
			{name:'curRate',index:'curRate', width:80, title:false},
			{name:'debitFor',index:'debitFor', width:100, align:"right", formatter:Public.currency, title:false},
			{name:'debit',index:'debit', width:100, align:"right", formatter:Public.currency, title:false},
			{name:'creditFor',index:'creditFor', width:100, align:"right", formatter:Public.currency, title:false},
			{name:'credit',index:'credit', width:100, align:"right", formatter:Public.currency, title:false},
			{name:'dcType',index:'dcType', width:40, align:"center", title:false},
			{name:'balanceFor',index:'balanceFor', width:100, align:"right", formatter:Public.currency, title:false, hidden: hidden},
			{name:'balance',index:'balance', width:100, align:"right", formatter:Public.currency, title:false}
		];
		var gridComplete = function(){
			//$("#grid").jqGrid('setGridWidth', gridSize.w);
		}
		groupHeaders = [
			{startColumnName: 'debitFor', numberOfColumns: 2, titleText: '借方金额'},
			{startColumnName: 'creditFor', numberOfColumns: 2, titleText: '贷方金额'},
			{startColumnName: 'dcType', numberOfColumns: 3, titleText: '余额'}
	  	]
	}
	if(first) {
		datatype = "local";
	}
	$("#grid").jqGrid({
		datatype: datatype,
		url:'/gl/general?m=queryDetail',
		postData: filterConditions,
		//width: width,
		height: height,
		altRows: true,
		gridview: true,
		colNames: colNames,
		colModel: colModel,
		page: 1, 
		cmTemplate: {sortable:false},
		sortname: 'date',
		sortorder: "asc",
		pager: "#page",
		rowNum: 20000, 
		scroll: 1,  
		viewrecords: true,
		shrinkToFit: false,
		autowidth: true,
		jsonReader: {
			root:"data.items", 
			records: "data.totalsize",  
			repeatitems : false,
			id: "voucherid"
		},
		gridComplete: gridComplete,
		loadComplete: function(data){
			//用于凭证查询上一、下一
			VCHID_DATA = [];
			if (!data || !data.data) {
				return ;
			};
			$.each(data.data.items, function(i, n){
				if (n.voucherid) {
					if ($.inArray(n.voucherid, VCHID_DATA) == -1) {
						VCHID_DATA.push(n.voucherid);
					}
				}
			});
		}
	});
	
	if (groupHeaders) {
		$('#grid').jqGrid('setGroupHeaders', {
		 	useColSpanStyle: true, 
		 	groupHeaders: groupHeaders
		});		
	}
}



function linkFmatter (cellvalue, options, rowObject) {
	return cellvalue == '' ? '&nbsp;' : '<a data-view='+ rowObject.voucherid +' class="link">' + cellvalue + '</a>';
};


function getGridSize(){	
	return {
		w : $(window).width() - 250,
		h : $(window).height() - $("#dataGrid").offset().top - 60
	}
};


function resetGrid(){	
	var size = getGridSize();
	$("#grid").jqGrid('setGridWidth', size.w);
	$("#grid").jqGrid('setGridHeight', size.h);
};


function reloadGrid(){	
	$('#grid').jqGrid('setGridParam',{
		url:'/gl/general?m=queryDetail',
		postData: filterConditions,
		datatype: 'json'	
	}).trigger('reloadGrid');
}


function initSubjectTree(data){
	var setting = {
			data: {
				simpleData : {enable: true,idKey : 'id',pIdKey: 'parentId',rootPId: 0},
				key: {
					name : function(node){
						return (node.number + ' ' + node.name);
					}
				}
			},
			callback: {
				onClick : function(e, treeId, treeNode){
					changedCallback(treeNode);
				}
			},
			view: {/*showLine: false, showIcon: false,*/ selectedMulti: false, dblClickExpand: false/*, addDiyDom: addDiyDom*/ }
	};

	$.fn.zTree.init($("#subsidiary-subject-tree"), setting, data);
}


function addDiyDom(treeId, treeNode) {
	var spaceWidth = 10;
	var switchObj = $("#" + treeNode.tId + "_switch"),
	icoObj = $("#" + treeNode.tId + "_ico");
	switchObj.remove();
	icoObj.before(switchObj);

	if (treeNode.level > 0) {
		var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
		switchObj.before(spaceStr);
	}
}



function initSearch(){
	var subjectCombo = Business.initSubjectAutoComplete('#subject-search input',{
		data: function(){
			return subjectData || [];
		},
		cache: false
	});


	$('#subject-search').on('submit',function(e){
		e.preventDefault();
		var id = subjectCombo.getValue();
		if(!id){
			Public.tips({
				type : 2,
				content : '请输入您要查找的科目！'
			});
			return false;
		}
		if (subjectTree) {
			var node = subjectTree.getNodesByParam('id',id)[0];
			subjectTree.selectNode(node);
			changedCallback(node);
		};

	})
}

function changedCallback(data){
	selectedSubject = data;
	var text = data.number + " " + data.fullName;
	$("#curSubject").html("「科目：" + text + "」").attr("title", text);
	filterConditions.accountId = data.id;
	filterConditions.accountNum = data.accountNumber;
	filterConditions.number = data.number;
	initGrid(currentCur);
}

function clearData(){
	selectedSubject = {};
	filterConditions.accountId = filterConditions.accountNum = filterConditions.number = undefined;
	$('#grid').jqGrid('clearGridData');
	$("#curSubject").html('');
}


//打印导出
$('#export,#print,#printAll').on('click',function(e){
	e.preventDefault();
	var isExport = $(this).attr('id') == 'export' ? true : false;
	var rightId = isExport ? '122' : '123';
	if (!Business.verifyRight(rightId)) {
		return ;
	}
	if(subjectData.length == 0){
		Public.tips({type:2, content : '没有满足条件的结果！'});
		return;
	}
	var url = isExport ? '/gl/general?m=exportDetail' : '/gl/general?m=printExportDetail';
	var isNewWinOpen = isExport ? false : true;
	var sheetName = "明细账#" + $("#selected-period").text();
	var op = $(this).data('op')
	if(op == 1){
		sheetName = selectedSubject.fullName + " " + sheetName;
	}
	var data = {
		dataField: dataField.join('#'),
		columnTitle: colNames.join('#'),
		sheetName: sheetName,
		op: op
	};
	$.extend(data, filterConditions);
	Business.getFile(url, data, isNewWinOpen, isExport);
});
