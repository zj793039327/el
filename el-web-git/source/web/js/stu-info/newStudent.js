$(function() {
	Business.initSexCombo($('#stu-sex'));
	Business.initTimeZoneCombo($('#stu-timeZone'),{width:200});
	initPage(Init);

	Public.pageTab();
	

	//摘要
	$('.vch-summary').focus(function() {
		if ($.trim(this.value) == this.defaultValue) {
			$(this).select();
		};
	}).blur(function() {
		if($.trim(this.value).length == 0){
			$(this).val(this.defaultValue);
		}
	});

	//展开更多
	$('#moreTrigger').click(function(e) {
		var cls = 'show';
		if ($(this).hasClass(cls)) {
			$('#moreSet').stop().slideUp();
			$(this).removeClass(cls).html('更多设置<i></i>');
		} else {
			$('#moreSet').stop().slideDown();
			$(this).addClass(cls).html('收起更多<i></i>');
		}
		e.preventDefault();
	})

	//生成凭证
	$('#create-vch').click(function(e) {
		e.preventDefault();
		if (!Business.verifyRight('171')) {
			return ;
		}
		if (!Business.verifyRight('181')) {
			return ;
		}
		if ($(this).hasClass('ui-btn-dis')){
			return ;
		}
		createVch();
	})

	/*重新生成凭证
	$('#hasCreat').on('click', '#recreate-vch', function(e) {
		e.preventDefault();
		createVch();
	})*/

	//反结到上期
	$('#unclose-period').click(function(e) {
		e.preventDefault();
		if (!Business.verifyRight('201')) {
			return ;
		}
		$.dialog.confirm('您确认进行反结转操作吗？', function(){
			unClosePeriod();
		});
	})

	//结转到下期
	$('#close-period').click(function(e) {
		e.preventDefault();
		if (!Business.verifyRight('191')) {
			return ;
		}
		if(!parent.SYSTEM.isDebug && !parent.SYSTEM.isMobile){
			var updateUrl = location.protocol + '//' + location.host + '/update_info.jsp',
				warning = '为确保账套数据安全，请先验证手机',
				url = 'http://service.youshang.com/user/phone_validate.jsp?updateUrl=' + encodeURIComponent(updateUrl) + '&warning=' + encodeURIComponent(warning);
			$.dialog({
				min: false,
				max: false,
				cancle: false,
				lock: true,
				width: 400,
				height: 250,
				title: '验证手机',
				content: 'url:' + url
			});
			return;
		}
		if (!Init.hasCreated && Init.isPl) {
			Public.tips({type: 2, content: '请先生成凭证再结转到下期!'});
			return ;
		}
		$.dialog.confirm('您确认进行结转操作吗？', function(){
			closePeriod();
		});
	})

});


//反结转到下期
function unClosePeriod(){
	var loading = $.dialog.tips('正在反结账到上期，请稍候...', 1000, 'loading.gif', true).show();
	$.ajax({
		type: "post",
		url: '/gl/closeperiod?m=unClosePeriod',
		dataType: "json",
		success: function(data, textStatus) {
			loading.close();
			if (data.status == 200) {
				successCallback(data.data, 'unClosePeriod');
			} else {
				Public.tips({type: 1,content: data.msg});
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			loading.close();
			Public.tips({
				type: 1,
				content: '反结账到上期失败！'
			});
		}
	});
}


//结转到下期
function closePeriod(){
	var loading = $.dialog.tips('正在结转，请稍候...', 1000, 'loading.gif', true).show();
	$.ajax({
		type: 'post',
		url: '/gl/closeperiod?m=closePeriod',
		dataType: 'json',
		success: function(data, textStatus) {
			loading.close();
			if(data.status != 200){
				Public.tips({type: 1, content: '结账到下期失败,' + data.msg});
				return ;
			}
			data = data.data;
			if (data.isBal === false) {
				handleNotBalanced();
				return ;
			}
			if (data.isPlBal === false) {
				Public.tips({type: 2, content: ' 结账到下期失败，有损益余额需重新生成凭证！'});
				return;
			}
			successCallback(data);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			Public.tips({type: 1, content: '结账到下期失败！'});
		}
	});
}



function handleNotBalanced(){
	//是否启用期间
	if (parent.SYSTEM.PERIOD != parent.SYSTEM.CURPERIOD) {
		Public.tips({type: 2, content: '结账到下期失败，财务始初余额不平衡！'});
	} else{
		$.dialog.confirm('结账到下期失败，财务初始余额不平衡！<br />点击确定检查财务初始余额。', function() {
			var url = '/settings/initial-balance.html';
			parent.tab.addTabItem({
				tabid: 'setting-initialBalance',
				text: '财务初始额',
				url: url
			});
		});
	}
	
}

//结账成功回调 参数为true 为反结账 否为结账
function successCallback(closeData, type){
	var tip = type == 'unClosePeriod' ? '反结账到上期成功！' : '恭喜您，结账成功！'
	parent.$("#period").text(closeData.PERIODNAME);
	parent.SYSTEM.CURPERIOD = closeData.CURPERIOD;
	parent.SYSTEM.CURYEAR = closeData.CURPERIOD.slice(0, 4);
	parent.SYSTEM.CURPNUM = parseInt(closeData.CURPERIOD.slice(-2), 10);;
	parent.SYSTEM.StartDate = closeData.StartDate;
	parent.SYSTEM.EndDate = closeData.EndDate;
	parent.Public.tips({type: 0, content: tip});
	parent.getPeriod();
	parent.tab.reload("index");
	parent.tab.removeAll();
	//parent.tab.reload('checkout');
}


//生成凭证链接
function getVchLinkHtml(data){
	var vch_link = '';
	$.each(data, function(idx, item) {
		var link = (idx ? '、' : '') + '<a href="/voucher/voucher.jsp?id=' + item.id + '" rel="pageTab" tabid="voucherDetail" parentOpen="true" tabTxt="凭证" class="view-vch">' + item.name + '</a>';
		vch_link += link;
	});
	return vch_link;
}


//初始化页面
function initPage(data) {
	var period = parent.SYSTEM.CURPERIOD;
	var hasCheckout = parent.SYSTEM.PERIOD !== period ? true : false;//是否结过帐
	$("#curPeriod").text(period.substr(0,4) + '年第' +  parseInt(period.substr(4),10) + '期');
	var summaryHtml = '共录入凭证 ' + data.sum + ' 张' + (hasCheckout ? '' :'，财务初始余额试算' + (data.isBalance ? '平衡' : '<strong class="ui-tips-text">不平衡</strong>')+'。');
	$('#close-summary').html(summaryHtml);
	if (data.hasCreated || !data.isPl) {
		$('#close-period').addClass('ui-btn-sp').removeClass('ui-btn-dis');
	}
	if (!data.isPl && !data.isRateAdj) {
		//$('#create-vch').addClass('ui-btn-dis');
	}
	if(parent.SYSTEM.REGIME === "CHS.NGO") {
		$('#nolimitedRow').show();
		$('#limitedRow').show();
		$('#lrkmRow').hide();
		$('#tzkmRow').hide();
		$('#jzkmRow').hide();
		
	}
	initRateAdjVch(data);
	initPlVch(data);
	data.isFixed ? initFixedVch(data) : '';
};

//期末调汇
function initRateAdjVch(data){
	if (!data.isRateAdj) {
		$('#adjuctVch').remove();
		$('#hdkmRow').remove();
		return;
	} 
	if (data.rateAdjVch.length > 0) {
		$('#adjVchTips').html('已经生成以下期末调汇凭证：' + getVchLinkHtml(data.rateAdjVch)).show();
	}
	$('#adjuctVchDate').val(data.rateAdjDate).datepicker({
		minDate: new Date(data.startDate.replace(/-/g, '/')),
		maxDate: new Date(data.endDate.replace(/-/g, '/'))
	}).datepicker($.datepicker.regional["zh-CN"]);
	Business.initVoucherWord($('#adjuctVchMark'),{
		defaultSelected: ["name", data.rateAdjMark]
	});
	$('#adjustVchSummary').val(data.rateAdjSummary);
	$('#hdkmRow').show();
	Business.initSubjectItem($("#hdkmWrap"),{defaultSelected: ['id', data.hdkm.id]});
	$('#grid').jqGrid({
		url: '/bs/currency?m=findRateAdjustCur',
		datatype: 'json',
		height: 'auto',
		width:710,
		colNames:['编码', '名称', '当前汇率', '调整汇率（期末）'],
		colModel:[
			{name:'FCUR',index:'FCUR', width:50},
			{name:'FNAME', index:'FNAME', width:50},
			{name:'FLASTRATE',index:'FLASTRATE', width:20, align:'right', title:false},
			{name:'FRATE',index:'FRATE',width:50, align:"center", formatter: adjustFormat, title:false}
		],
		page: 1,
		cmTemplate: {sortable:false}, 
		rowNum:300,
		forceFit:true,
		shrinkToFit:true,
		pager: '#page',
		viewrecords: true,
		gridComplete: function(){
			$('#grid .edit-input').numberField({
				decimal: true,
				precision: 4,
				min: 0
			});
		},
		jsonReader: {
			id:'FCUR',
			root:"data.items", 
			records: "data.totalsize",  
			repeatitems : false
		}
	});


}

//损益凭证
function initPlVch(data){
	$('#plVchDate').val(data.date).datepicker({
			minDate: new Date(data.startDate.replace(/-/g, '/')),
			maxDate: new Date(data.endDate.replace(/-/g, '/'))
		}).datepicker($.datepicker.regional["zh-CN"]);
	Business.initVoucherWord($('#plVchMark'),{
		defaultSelected: ["name", data.mark]
	});
	$('#plVchSummary').val(data.summary);
	$('input[name=vch-classify]').filter('[value='+data.classify+']').attr("checked", "checked"); 
	$('#closeType').attr('checked', data.way);
	if(parent.SYSTEM.REGIME === "CHS.NGO") {
		Business.initSubjectItem($("#nolimitedWrap"),{defaultSelected: ['id', data.nolimited.id]});
		Business.initSubjectItem($("#limitedWrap"),{defaultSelected: ['id', data.limited.id]});
		
	} else {
		Business.initSubjectItem($("#lrkmWrap"),{defaultSelected: ['id', data.lrkm.id]});
		Business.initSubjectItem($("#tzkmWrap"),{defaultSelected: ['id', data.tzkm.id]});
		Business.initSubjectItem($("#jzkmWrap"),{defaultSelected: ['id', data.jzkm.id]});
	}

	if (!data.isPl) {
		$('#plVchTips').html('本期结账不需要生成结转损益凭证').show();
	} else {
		if (data.vch.length > 0) {
			$('#plVchTips').html('已经生成以下结转损益凭证：' + getVchLinkHtml(data.vch)).show();
		}
	}
}

//折旧凭证
function initFixedVch(data){
	if (data.fixedVch.length > 0) {
		$('#FixedVchTips').html('已经生成以下折旧凭证：' + getVchLinkHtml(data.fixedVch)).show();
	}
	$('#FixedVchDate').val(data.fixedDate).datepicker({
		minDate: new Date(data.startDate.replace(/-/g, '/')),
		maxDate: new Date(data.endDate.replace(/-/g, '/'))
	}).datepicker($.datepicker.regional["zh-CN"]);
	Business.initVoucherWord($('#FixedVchMark'),{
		defaultSelected: ["name", data.fixedMark]
	});
	$('#FixedVchSummary').val(data.fixedSummary);
}

function adjustFormat(val, opt, row){
	return '<input type="text" class="edit-input" number="'+ row.FCUR +'"value="' + row.FRATE + '" />';
}


//生成凭证
function createVch(){
	var isCreateAdjVch = !!$('#isCreateAdjVch').attr('checked');
	var isCreatePlVch = !!$('#isCreatePlVch').attr('checked');
	var isCreateFixedVch = !!$('#isCreateFixedVch').attr('checked');
	if (!isCreateAdjVch && !isCreatePlVch && !isCreateFixedVch) {
		Public.tips({type: 2, content: '请先勾选需要生成的凭证！'});
		return ;
	};
	
	var VchDate = {};

	var classify = $('[name=vch-classify]:checked').val(),
		way = $('#closeType').attr('checked') ? '1': '0';

	if (isCreateAdjVch) {
		var adjuctVchGroupId = $('#adjuctVchMark').getCombo().getValue(),
		adjuctVchDate = $('#adjuctVchDate').val(), 
		adjustVchSummary = $.trim($('#adjustVchSummary').val()),
		hdkm = $('#hdkmWrap').getCombo().getValue(),
		cur = [];

		$.each($('#dataGrid .edit-input'), function(){
			var item = {};
			item.number = $(this).attr('number');
			item.rate = $(this).val();
			cur.push(item);
		});

		if (!adjuctVchGroupId) {
			Public.tips({type: 2, content: '请先选择期末调汇凭证凭证字！'});
			return ;
		}
		if (!adjuctVchDate) {
			Public.tips({type: 2, content: '请先选择期末调汇凭证日期！'});
			return ;
		}
		if (!adjustVchSummary) {
			Public.tips({type: 2, content: '请先填写期末调汇凭证摘要！'});
			return ;
		}
		if(!hdkm){
			Public.tips({type:2,content : '汇兑损益科目不能为空！'});
			return ;
		}

		VchDate.adjustVchData = {
			groupId: adjuctVchGroupId,
			date: adjuctVchDate,
			rateAdjSummary: adjustVchSummary,
			classify: classify,
			cur: cur,
			way:  way,
			paraHdkm: hdkm
		}
	};
	
	if (isCreateFixedVch) {
		var FixedVchGroupId = $('#FixedVchMark').getCombo().getValue(),
		FixedVchDate = $('#FixedVchDate').val(), 
		FixedVchSummary = $.trim($('#FixedVchSummary').val());

		if (!FixedVchGroupId) {
			Public.tips({type: 2, content: '请先选择折旧凭证凭证字！'});
			return ;
		}
		if (!FixedVchDate) {
			Public.tips({type: 2, content: '请先选择折旧凭证日期！'});
			return ;
		}
		if (!FixedVchSummary) {
			Public.tips({type: 2, content: '请先填写折旧凭证摘要！'});
			return ;
		}

		VchDate.fixedVchData = {
			groupId: FixedVchGroupId,
			date: FixedVchDate,
			fixedSummary: FixedVchSummary,
			classify: classify,
			way:  way
		}
	}

	if (isCreatePlVch){
		var plVchGroupId = $('#plVchMark').getCombo().getValue(),
		plVchDate = $('#plVchDate').val(), 
		plVchSummary = $.trim($('#plVchSummary').val());
		
		if (!plVchGroupId) {
			Public.tips({type: 2, content: '请先选择结转损益凭证凭证字！'});
			return ;
		}
		if (!plVchDate) {
			Public.tips({type: 2, content: '请先选择结转损益凭证日期！'});
			return ;
		}
		if (!plVchSummary) {
			Public.tips({type: 2, content: '请先填写结转损益凭证摘要！'});
			return ;
		};
		
		VchDate.plVchData = {
			groupId: plVchGroupId,
			date: plVchDate,
			summary: plVchSummary,
			classify: classify,
			way:  way
		}
		
		if(parent.SYSTEM.REGIME === "CHS.NGO") {
			var nolimited = $('#nolimitedWrap').getCombo().getValue(); 
			var limited = $('#limitedWrap').getCombo().getValue();
			if(!nolimited){
				Public.tips({type:2,content : '非限定性资产科目不能为空！'});
				return ;
			}
			if(!limited){
				Public.tips({type:2,content : '限定性资产不能为空！'});
				return ;
			}
			VchDate.plVchData.paraNolimited = nolimited;
			VchDate.plVchData.paraLimited = limited;
		} else {
			var lrkm = $('#lrkmWrap').getCombo().getValue(); 
			var tzkm = $('#tzkmWrap').getCombo().getValue();
			var jzkm = $('#jzkmWrap').getCombo().getValue();
			if(!lrkm){
				Public.tips({type:2,content : '本年利润科目不能为空！'});
				return ;
			}
			if(!tzkm){
				Public.tips({type:2,content : '以前年度损益调整科目不能为空！'});
				return ;
			}
			if(!jzkm){
				Public.tips({type:2,content : '以前年度损益调整科目的结转科目不能为空！'});
				return false;
			}
			VchDate.plVchData.paraLrkm = lrkm;
			VchDate.plVchData.paraTzkm = tzkm;
			VchDate.plVchData.paraJzkm = jzkm;
		}
	};
	//createVch(VchDate);
		
	
	
	if(isCreateFixedVch) {
		createFixedVch(VchDate.fixedVchData);
	}
	
	if(isCreateAdjVch) {
		createAdjVch(VchDate.adjustVchData);
	}

	if(isCreatePlVch) {
		createPlVch(VchDate.plVchData);
	}
	
/*	if (isCreateAdjVch && isCreatePlVch) {
		createAdjVch(adjustVchData, function(){
			createPlVch(plVchData);
		});
	} else if (isCreateAdjVch && !isCreatePlVch) {
		createAdjVch(adjustVchData);
	} else if (isCreatePlVch) {
		createPlVch(plVchData);
	}*/
}

function createPlVch(data){
	$.ajax({
		type: "post",
		url: '/gl/closeperiod',
		dataType: 'json',
		data: {
			'paramData': JSON.stringify(data)
		},
		success: function(data, textStatus) {
			if (data.status == 200) {
				data = data.data;				
				if (data && data.items.length != 0) {					
					Public.tips({type: 0, content: '生成凭证成功！'});
					//$('#create-vch').removeClass('ui-btn-sp').html('重新生成');
					$('#adjVchTips').html('已经生成以下期末调汇凭证：' + getVchLinkHtml(data.items)).show();
					$('#plVchTips').html('已经生成以下结转损益凭证：' + getVchLinkHtml(data.items)).show();
					$('#FixedVchTips').html('已经生成以下折旧凭证：' + getVchLinkHtml(data.items)).show();
				} else {
					Public.tips({type:2, content: '本期结账不需要生成结转损益凭证！'});
					$('#plVchTips').html('本期结账不需要生成结转损益凭证').show();
					//$('#create-vch').addClass('ui-btn-dis');
				}
				$('#close-period').removeClass('ui-btn-dis').addClass('ui-btn-sp');
				Init.hasCreated = true;
			}
			else if (data.status == 900){
				Public.tips({
					type: 1,
					content: '生成凭证失败,已达到最大凭证数限制！'
				});
			}else {
				Public.tips({
					type: 1,
					content: '生成凭证失败,' + data.msg
				});
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			Public.tips({
				type: 1,
				content: '生成凭证失败！'
			});
		}
	});
}

function createAdjVch(data, success){
	$.ajax({
		type: "post",
		url: '/gl/closeperiod?m=adjustRate',
		dataType: 'json',
		data: {
			'paramData': JSON.stringify(data)
		},
		success: function(data, textStatus) {
			if (data.status == 200) {
				data = data.data;
				if (data && data.items.length != 0) {
					Public.tips({type: 0, content: '期末调汇凭证生成成功！'});
					$('#adjVchTips').html('已经生成以下期末调汇凭证：' + getVchLinkHtml(data.items)).show();
				} else{
					Public.tips({type:2, content: '该期间汇率没有变化，不需要生成调汇凭证！'});
					$('#adjVchTips').html('该期间汇率没有变化，不需要生成调汇凭证').show();
				}
			
				if (success) {
					success();
				}
			}else if (data.status == 900){
				Public.tips({
					type: 1,
					content: '生成凭证失败,已达到最大凭证数限制！'
				});
			}else {
				Public.tips({
					type: 1,
					content: '期末调汇凭证生成失败,' + data.msg
				});
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			Public.tips({
				type: 1,
				content: '期末调汇凭证生成失败！'
			});
		}
	});
}


function createPlVch(data){
	$.ajax({
		type: "post",
		url: '/gl/closeperiod?m=transferPL',
		dataType: 'json',
		data: {
			'paramData': JSON.stringify(data)
		},
		success: function(data, textStatus) {
			//console.log(data);
			if (data.status == 200) {
				data = data.data;				
				if (data && data.items.length != 0) {					
					Public.tips({type: 0, content: '结转损益凭证生成成功！'});
					//$('#create-vch').removeClass('ui-btn-sp').html('重新生成');
					$('#plVchTips').html('已经生成以下结转损益凭证：' + getVchLinkHtml(data.items)).show();
				} else {
					Public.tips({type:2, content: '本期结账不需要生成结转损益凭证！'});
					$('#plVchTips').html('本期结账不需要生成结转损益凭证').show();
					//$('#create-vch').addClass('ui-btn-dis');
				}
				$('#close-period').removeClass('ui-btn-dis').addClass('ui-btn-sp');
				Init.hasCreated = true;
			}
			else if (data.status == 900){
				Public.tips({
					type: 1,
					content: '生成凭证失败,已达到最大凭证数限制！'
				});
			}else {
			Public.tips({
				type: 1,
				content: '生成凭证失败,' + data.msg
			});
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			Public.tips({
				type: 1,
				content: '生成凭证失败！'
			});
		}
	});
};

function createFixedVch(data){
	$.ajax({
		type: "post",
		url: '/gl/closeperiod?m=fixedAssets',
		dataType: 'json',
		data: {
			'paramData': JSON.stringify(data)
		},
		success: function(data, textStatus) {
			//console.log(data);
			if (data.status == 200) {
				data = data.data;				
				if (data && data.items.length != 0) {					
					Public.tips({type: 0, content: '折旧凭证生成成功！'});
					//$('#create-vch').removeClass('ui-btn-sp').html('重新生成');
					$('#FixedVchTips').html('已经生成以下折旧凭证：' + getVchLinkHtml(data.items)).show();
				} else {
					Public.tips({type:2, content: '本期结账不需要生成折旧凭证！'});
					$('#FixedVchTips').html('本期结账不需要生成折旧凭证').show();
					//$('#create-vch').addClass('ui-btn-dis');
				}
			}
			else if (data.status == 900){
				Public.tips({
					type: 1,
					content: '生成凭证失败,已达到最大凭证数限制！'
				});
			}else {
			Public.tips({
				type: 1,
				content: '生成凭证失败,' + data.msg
			});
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			Public.tips({
				type: 1,
				content: '生成凭证失败！'
			});
		}
	});
}


