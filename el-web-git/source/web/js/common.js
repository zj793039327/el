/*
 * 公用 js @auther z @date 2013-06-30
 */
var Public = Public || {}; // 公用业务对象
Public.URL_CONSTS = {
	nCode : "sys/queryNCode.action"// 单值代码访问Action
}
/**
 * 获取单值代码
 * 
 * @param {int}
 *            单值代码代码值
 */
Public.getNCode = function(nCode) {
	var param = {
		"code" : nCode
	};
	var returnData = this.getJsonFromAsyncPostAjax(this
					.getUrl(this.URL_CONSTS.nCode), param)
	return returnData;
}

// 获取完整的url
Public.getUrl = function(url) {
	return sys.getCompletePath() + url;
}
/**
 * 同步方式获取远方Ajax数据,直接返回JSON数据
 * 
 * @param url
 * @param params
 */
Public.getJsonFromAsyncPostAjax = function(url, params) {
	var returnText = $.ajax({
				type : "POST",
				url : url,
				async : false,
				data : params,
				datatype : "json"
			}).responseText;
	var returnData = eval("(" + returnText + ")");
	return returnData.NCodeRresult;
};

var Business = Business || {}; // 业务对象

Business.prototype = {
	_initComboBox : function(Ncode, obj, opts) {
		if (obj.length == 0 || isNaN(Ncode)) {
			return
		};
		var opts = $.extend(true, {
					data : eval(Public.getNCode(Ncode)),
					defaultSelected : ['defaultCode', true],
					text : 'name',
					width : 'auto',
					value : 'value',
					maxWidth : '100',
					maxListWidth : '100',
					minWidth : '60',
					cache : false,
					editable : true
				}, opts);
		return $(obj).combo(opts);
	}
}
/**
 * 初始化性别选框
 * 
 * @param {String}
 * @param {Boolean}
 * @param {Object}
 */
Business.initSexCombo = function(obj, opts) {
	this.prototype._initComboBox(2, obj, opts);
}
Business.initTimeZoneCombo = function(obj, opts) {
	this.prototype._initComboBox(5, obj, opts)
}
