//	setTabLayout();
//	$(window).on('resize',function(e){
//		setTabLayout();
//	});

	window.onbeforeunload = function(){
		var tabids = ['voucher', 'voucherDetail'];
		var isChanged, iframe;
		for (var i = 0; i < tabids.length; i++) {
			iframe = document.getElementById(tabids[i]);
			if (!iframe) {continue ;}
			isChanged = iframe.contentWindow.checkVchIsChanged();
			if (isChanged) {
				break ;
			}
		}
		if (isChanged) {
			return "凭证已改变，但尚未保存。";
		}
	};

	function setTabLayout(){
		var totalHeight = $(window).height() - $('#col-main').outerHeight() + $('#col-main').height();
		var tab = $('#main-bd');
		alert(1);
		var h = totalHeight - tab.offset().top;
		tab.height(h);
	}

	(function($){
		var navLi = $('#nav > li');
		$('a',navLi).on('click', function(e){
			e.preventDefault();
		});
		$.each(navLi,function(){
			var subNav = $(this).find('.sub-nav-wrap');
			$(this).bind('mouseover',function(){
				$(this).addClass('on');
				subNav.stop(true,true).fadeIn(250);
				if(subNav.css('top') == 'auto' && subNav.css('bottom') == 'auto'){
					var top = ($(this).outerHeight() - subNav.outerHeight()) / 2;
					subNav.css({top : top});
				}
			}).bind('mouseleave',function(){
				$(this).removeClass('on');
				subNav.stop(true,true).fadeOut(250);
			});
		});
	})(jQuery);

	$('#page-tab').ligerTab({ 
		height: '100%', 
		changeHeightOnResize : true,
		onBeforeAddTabItem : function(tabid){
			setCurrentNav(tabid);
		},
		onAfterSelectTabItem : function(tabid){
			setCurrentNav(tabid);
			if(tabid == 'buy-addedService'){ //购买增值服务页面不进行更新
				return;
			}
			var iframe = document.getElementById(tabid);
			if (iframe && $.isFunction(iframe.contentWindow.HANDLE_UPDATE)) {
				iframe.contentWindow.HANDLE_UPDATE();
			};
		},
		onBeforeRemoveTabItem : function(tabid){
			switch(tabid){
				case 'voucherDetail' :
				case 'voucher' :
					var iframe = document.getElementById(tabid);
					var isChanged = iframe.contentWindow.checkVchIsChanged();
					if (isChanged) {
						iframe.contentWindow.$.dialog.confirm('凭证尚未保存，是否确认离开？', function(){
							$("#page-tab").ligerGetTabManager().removeTabItem(tabid, true);
						});
						return false;
					}
					
				break;
					
			}
		},
		onAfterLeaveTabItem: function(tabid){
			switch(tabid){
				case 'setting-subjectList' :
					getSubject();
					break ;

				case 'setting-voucherWord' : 
					getVchWord();
					break ;
					
				case 'setting-currency' : 
					getCurrency();
					break ;
			}

		}
	});


	function setCurrentNav(tabid){
		if(!tabid){return ;}
		var pre = tabid.match((/([a-zA-Z]+)[-]?/))[1];
		$('#nav > li').removeClass('current');
		$('#nav > li.item-' + pre).addClass('current');
	};

//	//显示当前期间
//	(function(){
//		var period = SYSTEM.CURYEAR + "年第" + SYSTEM.CURPNUM + "期";
//		$('#period').html(period);
//		//显示公司名称
//		$('#myCompany').html(SYSTEM.myCompany);
//	})();

	//增加页签
	var tab = $("#page-tab").ligerGetTabManager();
	alert(tab);
	$('#nav').on('click','a[rel=pageTab]',function(e){
		e.preventDefault();
		var rightId = $(this).data('requiredright');
		if (rightId && !Business.verifyRight(rightId)) {
			return ;
		}
		var tabid = $(this).attr('tabid'), url = $(this).attr('href'), showClose = $(this).attr('showClose'), text = $(this).attr('tabTxt') || $(this).text();
		if(tabid !== 'voucher' && tab.isTabItemExist(tabid)) {
			tab.selectTabItem(tabid);
			tab.reload(tabid);
		} else {
			tab.addTabItem({tabid: tabid, text: text, url: url, showClose: showClose});
		};
	});
	tab.addTabItem({tabid: 'index', text: '首页', url: '/index.html', showClose: false});

	//帐套切换
	(function($){
		var serviceList = SYSTEM.serviceList;
		init();
		
		function init(){
			if (serviceList.length == 0) {
				return ;
			}
			createHtml();
			bindEvent();
		}

		function createHtml(){
			$('#serviceList').html([
				'<a href="#" class="trigger">切换<b></b></a>',
				'<ul class="list"></ul>'
			].join(''));
			var list = '';
			for (var i = 0, length = serviceList.length; i < length; i++) {
				var row = serviceList[i];
				list += '<li><a href="'+row.url+'" data-ownerid="'+row.ownerId+'" data-serviceid="'+row.serviceId+'">'+row.name+'</a></li>';
			}
			$('#serviceList .list').html(list);
		}


		function bindEvent(){
			$('#serviceList .trigger').on('mouseover', function(){
				$('#serviceList').addClass('service-list-on');	
			});

			$('#serviceList').on('mouseleave', function(){
				$('#serviceList').removeClass('service-list-on');
			});

			$('#serviceList .list a').on('click', function(e){
				var $this = $(this);
				var ownerId = $this.data('ownerid');
				var serviceId = $this.data('serviceid');
				var url = $this.attr('href');
				if ( ownerId == '0') {
					e.preventDefault();
					if ($(this).data('onPost')) {
						return ;
					}
					$this.data('onPost', true);
					$.ajax({
						type:'post',
						dataType: 'json',
						url: '/user?m=startupService&serviceId=' + serviceId,
						success: function(data){
							$this.data('onPost', false);
							if (data.status == 200 && data.data.returnCode == 1) {
								window.location.href = url;
							} else{
								Public.tips({type:1, content:'激活帐套失败！'});
							}
						},
						error: function(){
							 $this.data('onPost', false);
							 Public.tips({type:1, content:'激活帐套失败！'});
						}
					});
				}
			});
		}
	})(jQuery);
	
	//提示绑定手机
	(function($){
		if(SYSTEM.isDebug || SYSTEM.loginCount < 2 || SYSTEM.isMobile){
			return;
		}
	
		var params = {};
		var url,
			updateUrl = location.protocol + '//' + location.host + '/update_info.jsp',
			warning = '为确保账套数据安全，请先验证手机';
		if(!SYSTEM.isshortUser){
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
		}else if(SYSTEM.isshortUser){
			url = 'http://service.youshang.com/user/set_password.jsp?updateUrl=' + encodeURIComponent(updateUrl);
			$.dialog({
				min: false,
				max: false,
				cancle: false,
				lock: true,
				width: 450,
				height: 470,
				title: '设置独立密码',
				content: 'url:' + url
			});
		}
	})(jQuery);

	$(window).load(function(){
		$('#consulting').click(function(){
			var url = "http://im.youshang.com/live800/chatClient/chatbox.jsp?companyID=10001&configID=11&enterurl="+encodeURIComponent("http://www.youshang.com/saas/");
			var name = "在线咨询";
			var iWidth = 575;
			var iHeight = 450;
			var iTop = (window.screen.availHeight-30-iHeight)/2;
			var iLeft = (window.screen.availWidth-10-iWidth)/2;
			window.open(url, name, 'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
			
			//window.open(url,'chatbox73303','toolbar=0,scrollbars=0,location=0,height=450,width=577');
		});
		$('#reInitial').click(function(){
			$.dialog({
				lock: true,
				width: 430,
				height: 180,
				title: '系统提示',
				content: '<div class="re-initialize"><h3>重新初始化系统将会清空你录入的所有数据，请慎重！</h3><ul><li>系统将删除您新增的所有科目</li><li>系统将删除您录入的所有凭证</li><li>系统将删除您录入的所有初始化数据</li></ul><p><input type="checkbox" id="understand" /><label for="understand">我已清楚了解将产生的后果</label></p><p class="check-confirm">（请先确认并勾选“我已清楚了解将产生的后果”）</p></div>',
				icon: 'alert.gif',
				okVal: '重新初始化',
				ok: function () {
					if($('#understand').is(':checked')) {
						this.close();
						var loading = $.dialog.tips('正在重新初始化，请稍候...', 1000, 'loading.gif', true).show();	
						$.ajax({  
						   type: "GET",
						   url: '/transfer?m=clear&siId=' + SYSTEM.DBID + '&userName=' + SYSTEM.UserName,  
						   cache: false,  
						   async: true,  
						   dataType: "json",  
						   //当异步请求成功时调用  
						   success: function(data, status){  
						   		if(data.status === 200) {
									$('#container').html('');
									loading.close();
									window.location.href = 'start.html?re-initial=true';
								}
						   },  
							
						   //当请求出现错误时调用  
						   error: function(err){  
								Public.tips({type: 1, content : '操作失败了哦！' + err });
						   }  
						});
					} else {
						$('.check-confirm').css('visibility','visible');
					}
					return false;
				},
				cancelVal: '放弃',
				cancel: true
			});
		})
	});