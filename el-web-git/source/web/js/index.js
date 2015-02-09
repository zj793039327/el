/*
 * index.jsp 使用 @auther z @date 2013-06-30
 */
// executes when index page loading
setTabLayout();
$(window).on('resize', function(e) {
			setTabLayout();
		});

function setTabLayout() {
	var totalHeight = $(window).height() - $('#col-main').outerHeight()
			+ $('#col-main').height();
	var tab = $('#main-bd');
	var reltivetop = tab.offset() ? tab.offset().top : 0;
	var h = totalHeight - reltivetop;
	tab.height(h);
}
$(function() {
			initNavMouseOverEvent();
			initTab();
		});
/**
 * init index page tabs
 */
function initTab() {
	$('#page-tab').ligerTab({
				height : '100%',
				changeHeightOnResize : true,
				onAfterSelectTabItem : function(tabid) {// 点击页签,选取相应的导航
					setCurrentNav(tabid);
				}
			});
	var tab = $("#page-tab").ligerGetTabManager();
	tab.addTabItem({tabid : 'index',text : 'index',url : 'http://www.baidu.com',showClose : false});
	$('#nav').on('click', 'a[rel=pageTab]', function(e) {
		e.preventDefault();
		var tabid = $(this).attr('tabid'), url = $(this).attr('href'), showClose = $(this)
				.attr('showClose'), text = $(this).attr('tabTxt')
				|| $(this).text();
		setCurrentNav(tabid);
		if (tabid !== 'voucher' && tab.isTabItemExist(tabid)) {
			tab.selectTabItem(tabid);
			tab.reload(tabid);
		} else {
			tab.addTabItem({tabid : tabid,text : text,url : url,showClose : showClose});
		};
	});
}

function initNavMouseOverEvent() {
	// reg mouseover event
	var navLi = $('#nav > li');
	$('a', navLi).on('click', function(e) {
				e.preventDefault();
			});
	$.each(navLi, function() {
		var subNav = $(this).find('.sub-nav-wrap');
		$(this).bind('mouseover', function() {
					$(this).addClass('on');
					subNav.stop(true, true).fadeIn(250);
					if (subNav.css('top') == 'auto'
							&& subNav.css('bottom') == 'auto') {
						var top = ($(this).outerHeight() - subNav.outerHeight())
								/ 2;
						subNav.css({
									top : top
								});
					}
				}).bind('mouseleave', function() {
					$(this).removeClass('on');
					subNav.stop(true, true).fadeOut(250);
				});
	});
}

function setCurrentNav(tabid) {
	if (!tabid) {
		return;
	}
	var pre = tabid.match((/([a-zA-Z]+)[-]?/))[1];
	$('#nav > li').removeClass('current');
	$('#nav > li.item-' + pre).addClass('current');
};