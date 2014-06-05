/*
 * 2013新版头尾基础js方法
 * Author: 12040494
 * Date: 2013-07-27
 * */

var SFE = SFE || {};

// 头尾等基础方法，依赖于 jquery
SFE.base = (function ($) {
	
	var httpType=("https:"==document.location.protocol)?"https":"http";
	
	// 是否IE6
	var isIE6 = function () {
		return !!window.ActiveXObject && !window.XMLHttpRequest;
	};

	// 取 cookie 中的值
	var d = function (b) {
		var a;
		return (a = document.cookie.match(RegExp("(^| )" + b + "=([^;]*)(;|$)"))) ? decodeURIComponent(a[2]
			.replace(/\+/g, "%20"))
			: null
	};

	// 头部工具条展开
	var toolBarOpen = function () {
		$(".tool-link").children("dl.child-node").hover(function () {
			$(this).addClass("hover");
		}, function () {
			$(this).removeClass("hover");
		});
	};

	// 头部工具条欢迎词
	var toolBarWelcome = function () {
		var b, a = d("logonStatus");
		if (a != null && a != "") {
			var c = d("nick");
			var h = new Date().getHours();
			var t = "";
			if (h >= 0 && h < 3) {
				t = "夜深了";
			} else if (h >= 3 && h < 7) {
				t = "早上好";
			} else if (h >= 7 && h < 12) {
				t = "上午好";
			} else if (h >= 12 && h < 15) {
				t = "中午好";
			} else if (h >= 15 && h < 18) {
				t = "下午好";
			} else if (h >= 18 && h < 24) {
				t = "晚上好";
			}
			c == null && (c = "");
			a == 2 ? b = c + '，' + t + '，欢迎回来！' : a == 0 ? b = c + '！邮箱还未绑定，请<a href="https://'+ sn.memberDomain + sn.context + '/SNBindEmailCheckCmd?storeId=10052&catalogId=10051" title="立即绑定">立即绑定</a>' : a == 1 && (b = c + '！手机还未验证，请<a href="https://'+ sn.memberDomain + sn.context + '/ActivateMobileView?storeId=10052&catalogId=10051" title="立即验证">立即验证</a>');
			b += '&nbsp;&nbsp;<a href="javascript:SFE.base.logoff();" title="退出" target="_top">退出</a>';
			$("#toolBarWelcome").html(b);
			$("#logReg").hide();
			return true;
		}
		return false;
	};

	// 登录跳转
	var logonurl = function () {
		if (!toolBarWelcome()) {
			var logonurl = "";
			if (window.location.href.indexOf("&MyURL") != -1) {
				var v3 = window.location.href.substring(window.location.href
					.indexOf("&MyURL") + 7, window.location.href.length)
				if ((v3.indexOf("LogonForm") != -1)
					|| (v3.indexOf("SNUserRegisterView") != -1)
					|| (v3.indexOf("ForgotPasswordView") != -1)
					|| (v3.indexOf("ForgotCardPswView") != -1)
					|| (v3.indexOf("MobileActCode") != -1)
					|| (v3.indexOf("ResetPassword") != -1)
					|| (v3.indexOf("ForgotPasswordCheckMail") != -1)
					|| (v3.indexOf("ForgotPasswordSendMailView") != -1)
					|| (v3.indexOf("ChangeCardPwdWithIdCard") != -1)
					|| (v3.indexOf("SNNormalMobileRegisterCmd") != -1)
					|| (v3.indexOf("SNCampusMobileRegisterCmd") != -1)
					|| (v3.indexOf("SNCampusEmailRegisterCmd") != -1)
					|| (v3.indexOf("MbrCardInputView") != -1)
					|| (v3.indexOf("SNMbrCardMergeOptionView") != -1)
					|| (v3.indexOf("SNMbrCardMergeNewAccountView") != -1)
					|| (v3.indexOf("SNMbrCardMergeOtherAccountView") != -1)
					|| (v3.indexOf("SNMbrCardCheckCmd") != -1)
					|| (v3.indexOf("SNMbrCardVerifyMyInfoCmd") != -1)
					|| (v3.indexOf("SNMbrCardMergeNewAccountCmd") != -1)
					|| (v3.indexOf("SNMbrCardMergeOtherAccountCmd") != -1)
					|| (v3.indexOf("SNMbrCardMergeOtherVerifyMobileCmd") != -1)
					|| (v3.indexOf("SNMbrCardMergeOtherVerifyEmailCmd") != -1)
					|| (v3.indexOf("SNMbrCardMergeOtherVerifyNewMobileCmd") != -1)
					|| (v3.indexOf("SNMbrCardMergeCmd") != -1)) {
					logonurl = "https://"
						+ sn.memberDomain
						+ sn.context
						+ "/LogonForm?storeId="
						+ sn.storeId
						+ "&catalogId="
						+ sn.catalogId
						+ "&URL="
						+ encodeURIComponent("http://" + sn.domain + sn.context
						+ "/tcd_" + sn.storeId + "_" + sn.catalogId
						+ "_.html");
				} else {
					logonurl = "https://" + sn.memberDomain
						+ sn.context + "/LogonForm?storeId=" + sn.storeId
						+ "&catalogId=" + sn.catalogId + "&URL=" + v3;
				}
			} else if (window.location.href.indexOf("&URL") != -1) {
				var v1 = window.location.href.substring(window.location.href
					.indexOf("&URL") + 5, window.location.href.length);
				if ((v1.indexOf("LogonForm") != -1)
					|| (v1.indexOf("SNUserRegisterView") != -1)
					|| (v1.indexOf("ForgotPasswordView") != -1)
					|| (v1.indexOf("ForgotCardPswView") != -1)
					|| (v1.indexOf("MobileActCode") != -1)
					|| (v1.indexOf("ResetPassword") != -1)
					|| (v1.indexOf("ForgotPasswordCheckMail") != -1)
					|| (v1.indexOf("ForgotPasswordSendMailView") != -1)
					|| (v1.indexOf("ChangeCardPwdWithIdCard") != -1)
					|| (v1.indexOf("SNNormalMobileRegisterCmd") != -1)
					|| (v1.indexOf("SNCampusMobileRegisterCmd") != -1)
					|| (v1.indexOf("SNCampusEmailRegisterCmd") != -1)
					|| (v1.indexOf("MbrCardInputView") != -1)
					|| (v1.indexOf("SNMbrCardMergeOptionView") != -1)
					|| (v1.indexOf("SNMbrCardMergeNewAccountView") != -1)
					|| (v1.indexOf("SNMbrCardMergeOtherAccountView") != -1)
					|| (v1.indexOf("SNMbrCardCheckCmd") != -1)
					|| (v1.indexOf("SNMbrCardVerifyMyInfoCmd") != -1)
					|| (v1.indexOf("SNMbrCardMergeNewAccountCmd") != -1)
					|| (v1.indexOf("SNMbrCardMergeOtherAccountCmd") != -1)
					|| (v1.indexOf("SNMbrCardMergeOtherVerifyMobileCmd") != -1)
					|| (v1.indexOf("SNMbrCardMergeOtherVerifyEmailCmd") != -1)
					|| (v1.indexOf("SNMbrCardMergeOtherVerifyNewMobileCmd") != -1)
					|| (v1.indexOf("SNMbrCardMergeCmd") != -1)) {
					logonurl = "https://"
						+ sn.memberDomain
						+ sn.context
						+ "/LogonForm?storeId="
						+ sn.storeId
						+ "&catalogId="
						+ sn.catalogId
						+ "&URL="
						+ encodeURIComponent("http://" + sn.domain + sn.context
						+ "/tcd_" + sn.storeId + "_" + sn.catalogId
						+ "_.html");
				} else {
					logonurl = "https://" + sn.memberDomain
						+ sn.context + "/LogonForm?storeId=" + sn.storeId
						+ "&catalogId=" + sn.catalogId + "&URL=" + v1;
				}
			} else {
				var v2 = window.location.href.substring(window.location.href
					.lastIndexOf("/") + 1, window.location.href.length);
				if ((v2.indexOf("LogonForm") != -1)
					|| (v2.indexOf("SNUserRegisterView") != -1)
					|| (v2.indexOf("ForgotPasswordView") != -1)
					|| (v2.indexOf("ForgotCardPswView") != -1)
					|| (v2.indexOf("MobileActCode") != -1)
					|| (v2.indexOf("ResetPassword") != -1)
					|| (v2.indexOf("ForgotPasswordCheckMail") != -1)
					|| (v2.indexOf("ForgotPasswordSendMailView") != -1)
					|| (v2.indexOf("ChangeCardPwdWithIdCard") != -1)
					|| (v2.indexOf("SNNormalMobileRegisterCmd") != -1)
					|| (v2.indexOf("SNCampusMobileRegisterCmd") != -1)
					|| (v2.indexOf("SNCampusEmailRegisterCmd") != -1)
					|| (v2.indexOf("MbrCardInputView") != -1)
					|| (v2.indexOf("SNMbrCardMergeOptionView") != -1)
					|| (v2.indexOf("SNMbrCardMergeNewAccountView") != -1)
					|| (v2.indexOf("SNMbrCardMergeOtherAccountView") != -1)
					|| (v2.indexOf("SNMbrCardCheckCmd") != -1)
					|| (v2.indexOf("SNMbrCardVerifyMyInfoCmd") != -1)
					|| (v2.indexOf("SNMbrCardMergeNewAccountCmd") != -1)
					|| (v2.indexOf("SNMbrCardMergeOtherAccountCmd") != -1)
					|| (v2.indexOf("SNMbrCardMergeOtherVerifyMobileCmd") != -1)
					|| (v2.indexOf("SNMbrCardMergeOtherVerifyEmailCmd") != -1)
					|| (v2.indexOf("SNMbrCardMergeOtherVerifyNewMobileCmd") != -1)
					|| (v2.indexOf("SNMbrCardMergeCmd") != -1)) {
					logonurl = "https://"
						+ sn.memberDomain
						+ sn.context
						+ "/LogonForm?storeId="
						+ sn.storeId
						+ "&catalogId="
						+ sn.catalogId
						+ "&URL="
						+ encodeURIComponent("http://" + sn.domain + sn.context
						+ "/tcd_" + sn.storeId + "_" + sn.catalogId
						+ "_.html");
				} else {
					if (window.location.href.substring(
							window.location.href.lastIndexOf("/") + 1,
							window.location.href.length).indexOf(
							"CxnyProductSearch") != -1) {
						logonurl = "https://" + sn.memberDomain
							+ sn.context + "/LogonForm?adId=" + sn.adId
							+ "&storeId=" + sn.storeId + "&catalogId="
							+ sn.catalogId + "&URL="
							+ encodeURIComponent(window.location.href);
					} else {
						if (window.location.href.substring(window.location.href
							.lastIndexOf("/") + 1, window.location.href.length) == ''
							&&window.location.href.match("(.*?redbaby.*?)")==null
							&&window.location.href.match("(.*?binggo.*?)")==null) {
							logonurl = "https://"
								+ sn.memberDomain
								+ sn.context
								+ "/LogonForm?storeId="
								+ sn.storeId
								+ "&catalogId="
								+ sn.catalogId
								+ "&URL="
								+ encodeURIComponent("http://" + sn.domain
								+ sn.context + "/tcd_" + sn.storeId
								+ "_" + sn.catalogId + "_.html");
						} else {
							logonurl = "https://" + sn.memberDomain
								+ sn.context + "/LogonForm?storeId="
								+ sn.storeId + "&catalogId=" + sn.catalogId
								+ "&URL="
								+ encodeURIComponent(window.location.href);
						}
					}
				}
			}
			hrefLink(logonurl);
		}
	};

	// 退出
	var logoff = function(){
		var date = new Date();
		date.setTime(date.getTime() - 10000);
		document.cookie = "logonStatus=a; expires=" + date.toGMTString();

		window.location = 'http://' + sn.memberDomain + sn.context + '/Logoff?storeId=10052&URL=SNSendRedirectCmd';
	};

	// 注册跳转
	var registerurl = function () {
		// 注册
		var registerurl = "";
		if (window.location.href.indexOf("&URL") != -1) {
			registerurl = window.location.href.substring(window.location.href
				.indexOf("&URL") + 5, window.location.href.length);
			if ((registerurl.indexOf("LogonForm") != -1)
				|| (registerurl.indexOf("SNUserRegisterView") != -1)
				|| (registerurl.indexOf("ForgotPasswordView") != -1)
				|| (registerurl.indexOf("SNUserRegisterView") != -1)
				|| (registerurl.indexOf("ForgotPasswordCheckMail") != -1)
				|| (registerurl.indexOf("ForgotPasswordSendMailView") != -1)
				|| (registerurl.indexOf("ChangeCardPwdWithIdCard") != -1)) {
				registerurl = "https://"
					+ sn.memberDomain
					+ sn.context
					+ "/SNUserRegisterView?storeId="
					+ sn.storeId
					+ "&catalogId="
					+ sn.catalogId
					+ "&MyURL="
					+ encodeURIComponent("http://" + sn.domain + sn.context
					+ "/tcd_" + sn.storeId + "_" + sn.catalogId
					+ "_.html");
			} else {
				registerurl = "https://" + sn.memberDomain + sn.context
					+ "/SNUserRegisterView?storeId=" + sn.storeId
					+ "&catalogId=" + sn.catalogId + "&MyURL=" + registerurl;
			}
		} else if (window.location.href.indexOf("&MyURL") != -1) {
			var v1 = window.location.href.substring(window.location.href
				.indexOf("&MyURL") + 7, window.location.href.length);
			if ((v1.indexOf("LogonForm") != -1)
				|| (v1.indexOf("SNUserRegisterView") != -1)
				|| (v1.indexOf("ForgotPasswordView") != -1)
				|| (v1.indexOf("SNUserRegisterView") != -1)
				|| (v1.indexOf("ForgotPasswordCheckMail") != -1)
				|| (v1.indexOf("ForgotPasswordSendMailView") != -1)
				|| (v1.indexOf("ChangeCardPwdWithIdCard") != -1)) {
				registerurl = "https://"
					+ sn.memberDomain
					+ sn.context
					+ "/SNUserRegisterView?storeId="
					+ sn.storeId
					+ "&catalogId="
					+ sn.catalogId
					+ "&MyURL="
					+ encodeURIComponent("http://" + sn.domain + sn.context
					+ "/tcd_" + sn.storeId + "_" + sn.catalogId
					+ "_.html");
			} else {
				registerurl = "https://" + sn.memberDomain + sn.context
					+ "/SNUserRegisterView?storeId=" + sn.storeId
					+ "&catalogId=" + sn.catalogId + "&MyURL=" + v1;
			}
		} else if (window.location.href.indexOf("&krypto") != -1) {
			var v1 = window.location.href.substring(window.location.href
				.lastIndexOf("/") + 1, window.location.href.indexOf("&krypto"));
			if ((v1.indexOf("LogonForm") != -1)
				|| (v1.indexOf("SNUserRegisterView") != -1)
				|| (v1.indexOf("ForgotPasswordView") != -1)
				|| (v1.indexOf("SNUserRegisterView") != -1)
				|| (v1.indexOf("ForgotPasswordCheckMail") != -1)
				|| (v1.indexOf("ForgotPasswordSendMailView") != -1)
				|| (v1.indexOf("ChangeCardPwdWithIdCard") != -1)) {
				registerurl = "https://"
					+ sn.memberDomain
					+ sn.context
					+ "/SNUserRegisterView?storeId="
					+ sn.storeId
					+ "&catalogId="
					+ sn.catalogId
					+ "&MyURL="
					+ encodeURIComponent("http://" + sn.domain + sn.context
					+ "/tcd_" + sn.storeId + "_" + sn.catalogId
					+ "_.html");
			} else {
				registerurl = "https://"
					+ sn.memberDomain
					+ sn.context
					+ "/SNUserRegisterView?storeId="
					+ sn.storeId
					+ "&catalogId="
					+ sn.catalogId
					+ "&MyURL="
					+ encodeURIComponent(window.location.href.substring(0,
					window.location.href.indexOf("&krypto")));
			}
		} else {
			var v2 = window.location.href.substring(window.location.href
				.lastIndexOf("/") + 1, window.location.href.length);
			if ((v2.indexOf("LogonForm") != -1)
				|| (v2.indexOf("SNUserRegisterView") != -1)
				|| (v2.indexOf("ForgotPasswordView") != -1)
				|| (v2.indexOf("SNUserRegisterView") != -1)
				|| (v2.indexOf("ForgotPasswordCheckMail") != -1)
				|| (v2.indexOf("ForgotPasswordSendMailView") != -1)
				|| (v2.indexOf("ChangeCardPwdWithIdCard") != -1)) {
				registerurl = "https://"
					+ sn.memberDomain
					+ sn.context
					+ "/SNUserRegisterView?storeId="
					+ sn.storeId
					+ "&catalogId="
					+ sn.catalogId
					+ "&MyURL="
					+ encodeURIComponent("http://" + sn.domain + sn.context
					+ "/tcd_" + sn.storeId + "_" + sn.catalogId
					+ "_.html");
			} else {
				registerurl = "https://" + sn.memberDomain + sn.context
					+ "/SNUserRegisterView?storeId=" + sn.storeId
					+ "&catalogId=" + sn.catalogId + "&MyURL="
					+ encodeURIComponent(window.location.href);
			}
		}
		hrefLink(registerurl);
	};

	// 加入收藏
	var addFavorite = function () {
		var d = "http://www.suning.com/";
		var c = "苏宁易购-苏宁云商网上商城，领先的综合网上购物商城，正品行货，全国联保，货到付款，让您尽享购物乐趣！";
		if (document.all) {
			window.external.AddFavorite(d, c);
		} else if (window.sidebar) {
			window.sidebar.addPanel(c, d, "");
		} else {
			alert("对不起，您的浏览器不支持此操作!\n请您使用菜单栏或Ctrl+D收藏本站。");
		}
	};

	// 在线客服
	var onlineService = function () {
		window.open("http://" + sn.online + "/webchat/index.jsp?tabId=0", "webcallpage", "height=530,width=800,directories=no,location=no,scrollbars=yes, resizable=yes, toolbar=no, menubar=no,status=no")
	};

	// 城市专区链接
	var setCityUrl = function() {
		var city = $(".cityUrl") || "9173";
		var cityId = d("cityId");
		if (city.length > 0) {
			var url;
			var cityArr = [];
			var cityDomain = getCityDomain();
			cityArr["9173"] = "http://nanjing" + cityDomain;
			cityArr["9017"] = "http://beijing" + cityDomain;
			cityArr["9264"] = "http://shanghai" + cityDomain;
			cityArr["9325"] = "http://chongqing" + cityDomain;
			cityArr["9041"] = "http://guangzhou" + cityDomain;
			cityArr["9281"] = "http://tianjin" + cityDomain;
			cityArr["9051"] = "http://shenzhen" + cityDomain;
			cityArr["9254"] = "http://xian" + cityDomain;
			cityArr["9315"] = "http://hangzhou" + cityDomain;
			cityArr["9197"] = "http://shenyang" + cityDomain;
			cityArr["9265"] = "http://chengdu" + cityDomain;
			cityArr["9135"] = "http://wuhan" + cityDomain;
			if (!!cityArr[cityId]) {
				url = cityArr[cityId];
			} else {
				url = cityArr["9173"];
			}
			city.live("mouseover", function () {
				$(this).attr("href", url);
				$(this).removeClass("cityUrl");
			});
		}
	};
	
	//替换链接中的cityId
	var setSearchCity = function (selector) {
		var city = $(selector);
		if (city != null && city.length > 0) {
			//绑定mouseover事件，替换cityId=xxx
			city.live("mouseover", function () {
				replaceCityParam(this);
			});
			//绑定onclick事件，替换{cityId}占位符，忽略大小写
			city.click(function(){
				replaceCityPlaceHolder(this);
			})
		}
	};
	
	//替换cityId参数cityId=xxx
	function replaceCityParam(obj){
		var cityId =  d("cityId") || "9173";
		var href=$(obj).attr("href");
		//判断是否cityId为最后一个参数，是的话不需要加&,否则要加&
		var split=href.match(/cityId=.*&/gi)==null?"":"&";
		//替换cityId=后面的字符串或数字，直到&或结尾，忽略大小写
		$(obj).attr("href", href.replace(/cityId=.*?&|cityId=.*$/gi, "cityId="+cityId+split));
	}
	
	//替换城市id占位符{cityId}
	function replaceCityPlaceHolder(obj){
		var cityId =  d("cityId") || "9173";
		url = $(obj).attr("href").replace(/{cityId}/gi, cityId).replace(/%7bcityId%7d/gi, cityId);
		$(obj).attr("href", url);
	}
	
	function getCityDomain() {
		var ego_pre_v7_reg = /^(\w*)(pre)(\w*)(.cnsuning.com)$/;
		var ego_sit_v7_reg = /^(\w*)(sit)([1-5].cnsuning.com)$/;

		var _hostName = document.location.hostname;
		var _cityDomain = ".suning.com";
		if (ego_pre_v7_reg.test(_hostName)) {
			_cityDomain = "pre.cnsuning.com";
		} else if (ego_sit_v7_reg.test(_hostName)) {
			_cityDomain = "sit.cnsuning.com";
		}
		return _cityDomain;
	}

	// 搜索热门关键词及默认关键词方法
	var getSearchKeyword = function () {
		// 发送查询请求
		var daMain = sn.searchDomain.replace("http", httpType);
		$.ajax({
			url: daMain + "/hotkeywords.xjsonp?categoryId=" + sn.categoryId + "&callback=?",
			type: "get",
			dataType: "jsonp",
			success: function (data) {
				try {

					// 热词
					var html = data.html.replace("{cityId}", d("cityId"));
					$("#snKeywordNew").html(html);

					// 默认搜索词
					//var searchDefaultKeyword = $("#searchDefaultKeyword").val();
					//$("#searchKeywords").val(searchDefaultKeyword);

				} catch (e) {

				}
			}
		});
	};

	// 搜索框焦点事件
	var searchInputEvent = function () {

		// 搜索按钮滑过
		$("#searchSubmit").hover(function () {
			$(this).addClass("search-btn-hover");
		}, function () {
			$(this).removeClass("search-btn-hover");
		});

		// 鼠标焦点事件
		var obj = $("#searchKeywords");
		obj.focus(function () {
			obj.parents(".g-search").addClass("g-search-focus");
			var value = $(this).val(),
			searchDefaultKeyword = $("#searchDefaultKeyword").val() || "";
			if (value == searchDefaultKeyword) {
				obj.val("").css({color: "#000"});
			}
		}).blur(function () {
				obj.parents(".g-search").removeClass("g-search-focus");
				var value = $.trim($(this).val()),
				searchDefaultKeyword = $("#searchDefaultKeyword").val() || "";
				if (value == "") {
					obj.val(searchDefaultKeyword).css({color: "#999"});
				}
			});
	};

	// 搜索关键词自动完成
	var searchCatalogId = "";
	var searchAutoComplete = function () {
		var isIE6 = !!window.ActiveXObject && !window.XMLHttpRequest;
		var obj = $("#searchKeywords");
		var delay = 200, timer, resultBox = $("#ac_results");
		if (resultBox.size() == 0) {
			$('<div class="g-ac-results" id="ac_results" style="display:none;"></div>').appendTo(".g-search");
			resultBox = $("#ac_results");
		}

		// 联想条目鼠标滑过及点击事件
		resultBox.delegate("li", "hover",function () {
			$(this).addClass("ac_over").siblings().removeClass("ac_over");
		}).delegate("li", "click", function () {
				if ($(this).attr("categoryid") != "") {
					searchCatalogId = $(this).attr("categoryid");
				} else {
					searchCatalogId = "";
				}
				obj.val($(this).find(".keyname").text());
				resultBox.hide();
				resultListCurrentIndex = -1;
				$("#searchSubmit").click();
			});

		// 通过键盘选择搜索词
		var resultListCurrentIndex = -1;
		var selectKeywordByKey = function (n) {
			var resultBox = $("#ac_results"),
				results = resultBox.find("li"),
				maxCount = results.size();
			if (resultBox.is("hidden") || results.size() == 0 || Math.abs(n) != 1) {
				return;
			}
			resultListCurrentIndex += n;
			if (resultListCurrentIndex < 0) {
				resultListCurrentIndex = maxCount - 1;
			}
			if (resultListCurrentIndex == maxCount) {
				resultListCurrentIndex = 0;
			}
			var currentKeywords = results.eq(resultListCurrentIndex);
			results.removeClass("ac_over");
			currentKeywords.addClass("ac_over");
			if (currentKeywords.attr("categoryid")) {
				searchCatalogId = currentKeywords.attr("categoryid");
			} else {
				searchCatalogId = "";
			}
			obj.val(currentKeywords.find(".keyname").text());
			return false;
		};

		// 按键抬起，向服务端发送请求
		obj.keyup(function (event) {
			if (event.which == 13 || event.which == 38 || event.which == 40) {
				return false;
			}
			clearTimeout(timer);
			timer = setTimeout(function () {
				var keyword = $.trim($("#searchKeywords").val());
				if (keyword.length == 0) {
					resultBox.hide();
					return false;
				}
				var daMain = sn.searchDomain.replace("http", httpType);
				$.ajax({
					url: daMain + "autocomplete.jsonp",
					dataType: "jsonp",
					data: {
						callback: "?",
						keyword: keyword
					},
					success: function (data) {
						if (data.words.length == 0) {
							resultBox.hide();
							resultListCurrentIndex = -1;
							return false;
						}
						resultBox.show();
						var resultDom = '<ul>';
						$(data.words).each(function (key, value) {
							if (typeof value.categoryName != "undefined") {
								resultDom += '<li categoryid="' + value.categoryId + '" class="cateSearch">在<b>' + value.categoryName + '</b>分类 中搜索<span style="display:none;" class="keyname">' + value.keyname + '</span></li>'
							} else {
								resultDom += '<li><span class="keyname">' + value.keyname + '</span></li>'
							}
						});
						resultDom += '</ul>';
						resultBox.html(resultDom);
						resultBox.find(".cateSearch:last").addClass("bottom");
					}
				});
			}, delay);
		}).keydown(function (event) {  // 按键按下，检测是否为上下方向键
				if (event.which == 13) { // 回车键
					resultBox.hide();
					resultListCurrentIndex = -1;
					$("#searchSubmit").click();
					return false;
				}
				if (event.which == 38) { // 上方向键
					selectKeywordByKey(-1);
				}
				if (event.which == 40) { // 下方向键
					selectKeywordByKey(1);
				}
			}).click(function () {
				return false;
			});
		$(document).click(function () {
			resultBox.hide();
			resultListCurrentIndex = -1;
		});
	};

	// 搜索提交
	var onSubmitSearch = function () {
		var obj = $('#searchKeywords');
		var tmp = $.trim(obj.val());
		if (tmp == '') {
			obj.focus();
		} else {
			var url = sn.searchDomain + "search.do?keyword=" + encodeURIComponent(tmp);
			if (searchCatalogId != "") {
				url += "&ci=" + searchCatalogId;
			}
			url += "&cityId=" + d("cityId");
			window.location.href = url;
		}
		return false;
	};

	// 购物车数字初始化
	var miniCartInt = function () {
		var itemLength = d("totalProdQty") || 0;
		$("#showTotalQty").text(itemLength);
	};

	// 查询购物车内容
	function invokeMiniCart() {
		var url = "http://" + sn.domain + sn.context + "/MiniCartSearchView?catalogId=" + sn.catalogId + "&storeId=" + sn.storeId;
		$.ajax({
			type: "get",
			dataType: "jsonp",
			url: url,
			success: function (data) {
				var listBox = $("#snCarlist");
				if(isIE6()){
					data = '<iframe class="cartMask"></iframe>' + data;
				}
				listBox.html(data);
				//alert("请注意，查询购物车返回的数据需要使用新的格式!此处错乱因结构变更。");
				listBox.find(".mycarDel").each(function () {
					var i = $(this);
					var k = i.attr("exdata");
					var l = k.split(",")[0];
					i.click(function () {
						miniCart_deleteOrderItem(l);
						return false
					})
				});
			},
			error: function () {
				$("#snCarlist").html('<h3 id="noGoods" class="noGoods hide"><em></em>您的购物车是空的,赶紧选购吧！</h3>');
			}
		});
	}

	// 删除购物车内容
	function miniCart_deleteOrderItem(b) {
		var url = "http://" + sn.domain + sn.context + "/SNCartOperationCmd?method=deleteItem";
		$.ajax({
			type: "get",
			url: url,
			dataType: "jsonp",
			data: {
				itemId: b,
				ts: new Date().getTime(),
				callback: "?"
			}
		});
		miniCartReload();
	}

	// 刷新购物车内容
	function miniCartReload() {
		var c = d("totalProdQty");
		if (c != null) {
			$("#showTotalQty").text(c)
		}
		var a = "10052";
		var e = sn.catalogId;
		var b = "http://" + sn.domain + sn.context + "/MiniCartSearchView?catalogId=" + e + "&storeId=" + a + "&callback=?";
		$.ajax({
			url: b, cache: false, dataType: "jsonp", jsonp: "callback", success: function (h) {
				$("#snCarlist").html(h);
				var g = /<em id="totalProductQty">(\d+)<\/em>/;
				var f = 0;
				if (g.test(h)) {
					f = g.exec(h)[1]
				}
				$("#snCarlist").find(".mycarDel").each(function (j) {
					var i = $(this);
					var k = i.attr("exdata");
					var l = k.split(",");
					i.click(function () {
						miniCart_deleteOrderItem(l[0]);
						return false
					})
				});
				$("#showTotalQty").text(f);
			}})
	}

	//listenMiniCart
	function listenMiniCart() {
		if (isIE6) {
			var obj = $(".g-cart-list-wrapper");
			var _size = obj.find("ul").children().size();
			if (_size > 4) {
				obj.css({height: 328});
			} else {
				obj.css({height: "auto"});
			}
		}
	}

	//openCart
	var oldNum = 0;
	var openCart = function () {
		miniCartInt();
		listenMiniCart();
		var box = $("#myCart");
		var timer;
		box.mouseover(function () {
			clearTimeout(timer);
			timer = setTimeout(function(){
				var quantity = d("totalProdQty");
				if (quantity != oldNum) {
					oldNum = quantity;
					$("#showTotalQty").text(quantity);
					$("#loadingId").show();
					invokeMiniCart();
				} else {
					if (quantity == 0) {
						oldNum = 0;
						$("#snCarlist").html('<h3 id="noGoods" class="noGoods"><em></em>您的购物车是空的,赶紧选购吧！</h3>');
					}
				}
				box.addClass("g-min-cart-hover").find("#snCarlist").show();
				listenMiniCart();
			}, 200);
		}).mouseleave(function () {
			clearTimeout(timer);
			timer = setTimeout(function(){
				box.removeClass("g-min-cart-hover").find("#snCarlist").hide();
			}, 200);
		});
	};

	// 全部分类展开
	var next = false;
	var trail = function () {
		var dataReady = false;
		var category = $('#category');
		var subCategory = $('#subCategory');
		var hook = $(".all-hook");
		var icon = $("#allSort_drop_icon");
		var isHome = category.is(":visible");
		var firstMoveIn = true;
		// 加载三级分类数据
		if(category.size()>0){
			$.getScript("http://" + sn.domain + sn.context + "/threeSort_10052_10051_.html", function(){
				dataReady = true;
			});
		}
		if (!isHome) {
			category.hide();
			icon.show();
			// 非首页展开二级分类
			var timer;
			hook.hover(function () {
				clearTimeout(timer);
				category.show();
				subCategory.hide();
				category.children().removeClass("hover");
			}, function () {
				timer = setTimeout(function () {
					category.hide();
					category.children().removeClass("hover");
				}, 100);
			});
			category.hover(function () {
				clearTimeout(timer);
			}, function () {
				timer = setTimeout(function () {
					category.hide();
					category.children().removeClass("hover");
					subCategory.hide();
				}, 200);
			});
			subCategory.hover(function () {
				clearTimeout(timer);
			}, function () {
				timer = setTimeout(function () {
					category.hide();
					category.children().removeClass("hover");
					subCategory.hide();
				}, 200);
			});
		} else {
			icon.hide();
			category.hover(function () {
				clearTimeout(timer);
			}, function () {
				timer = setTimeout(function () {
					subCategory.hide();
					category.children().removeClass("hover");
				}, 200);
			});
			subCategory.hover(function () {
				clearTimeout(timer);
			}, function () {
				timer = setTimeout(function () {
					subCategory.hide();
					category.children().removeClass("hover");
				}, 200);
			});
		}
		var oldX = 0;
		var oldY = 0;
		var x, y;
		var showTimer;
		category.children("dl").mouseover(function (e) {
			var t = category.offset().top;
			var l = category.offset().left;
			if( firstMoveIn ){
				showTimer = setTimeout(function(){
					subCategory.show();
				}, 200);
				firstMoveIn = false;
			} else {
				clearTimeout(showTimer);
				subCategory.show();
			}

			x = e.clientX;
			y = e.clientY + $(document).scrollTop();
			if (!com([oldX, oldY], [l + 200, -50 + t], [l + 200, subCategory.height() + 50 + t], [x, y])) {
				$(this).addClass('hover').siblings().removeClass('hover');
				var index = $(this).index();
				if( dataReady && typeof publicCategoryOpenData!="undefined" &&  typeof publicCategoryOpenData[index]!="undefined"){
					// 拼接数据为html片段
					var data = publicCategoryOpenData[index];
					var html = "";
					if(isIE6()){
						html += '<iframe class="category-open-mask"></iframe>';
					}
					html += '<div class="sub-category">';
					for(var i=0; i<data.sub.length; i++){
						var subData = data.sub[i];
						html += i==0 ? '<dl class="sc01">' : '<dl>';
						html += '<dt><a href="' + subData.t[1] + '" name="' + subData.t[2] + '" id="' + subData.t[3] + '">' + subData.t[0] + '</a></dt>';
						html += '<dd>';
						for(var j=0; j<subData.s.length; j++){
							var aData = subData.s[j];
							html += '<a href="' + aData[1] + '" name="' + aData[2] + '">' + aData[0] + '</a>';
						}
						html += '</dd>';
						html += '</dl>';
					}
					html += '</div>';
					html += '<div class="sub-brands">';
					html += '<dl><dt>推荐品牌</dt><dd>';
					for(var i=0; i<data.brand.length; i++){
						var aData = data.brand[i];
						if( index==6 ){ // 图书
							html += '<span class="width"><a href="' + aData[1] + '" name="' + aData[2] + '">' + aData[0] + '</a></span>';
						} else {
							html += '<span><a href="' + aData[1] + '" name="' + aData[2] + '">' + aData[0] + '</a></span>';
						}
					}
					html += '</dd></dl>';
					html += '<ul class="category-promotions">';
					for(var i=0; i<data.pic.length; i++){
						var aData = data.pic[i];
						html += i==0 ? '<li class="cp01">' : '<li>';
						html += '<a href="' + aData[2] + '" name="' + aData[3] + '"><img src="' + aData[1] + '" alt="' + aData[0] + '" /></a></li>';
					}
					html += '</ul>';
					html += '</div>';
					html += '<a href="javascript:void(0);" title="关闭" class="close"></a>';
					subCategory.html(html);
				}
			}
			oldX = x;
			oldY = y;
		});
		subCategory.delegate(".close", "click", function () {
			subCategory.hide();
		});
	};

	function com(d1, d2, d3, d4) {
		var a1 = rotation(d1, d2);
		var a2 = rotation(d1, d3);
		var a3 = rotation(d1, d4);
		if (a1 < a2) {
			if (a3 >= a1 && a3 <= a2) {
				return true
			} else {
				return false;
			}
		} else {
			if (a3 >= a2 && a3 <= a1) {
				return true
			} else {
				return false;
			}
		}

	}

	function rotation(d1, d2) {
		return Math.atan2(d2[1] - d1[1], d2[0] - d1[0]) * 180 / Math.PI;
	}

	// 工具条方法打包
	var toolBarEvent = function(){
		toolBarWelcome();
		toolBarOpen();
		setCityUrl();
//		setSearchCity();
	};

	// 搜索框方法打包
	var searchEvent = function(){
		getSearchKeyword();
		searchInputEvent();
		searchAutoComplete();
	};

	// 导航条方法打包
	var mainNavEvent = function(){
		trail();
		openCart();
	};

	// 回到顶部
	var gotop = function(){
		$("html,body").scrollTop(0);
	};

	// 浮动条
	var floatBar = function () {

		//默认参数
		var _d = {
			contents: null,             //滚动条的内容，可以是DOM字符或者jQuery对象
			align: "right",             //水平方向对齐
			vertical: "middle",         //垂直方向对齐
			zIndex: 7500,                //Z轴值
			css: null,                   //附加样式
			id: null,                    //包裹容器的id，必要时可以设置id用来操作DOM
			ieFixed: true               //IE6及更低版本是否模拟fixed效果
		};

		//检测某些垃圾浏览器版本，并保存至变量
		var _ie = ($.browser.msie) ? parseInt($.browser.version) : false;

		//检测并合并传递的参数
		if (arguments.length < 1 || !(arguments[0] instanceof Object)) {
			return $.error("ECode.floatBar: 参数必须为JSON对象");
		}
		$.extend(_d, arguments[0]);

		//挂载DOM
		var _hideCss = {
			position: "fixed",
			top: "-9999em",
			left: "-9999em"
		};
		if (_ie && _ie <= 6) {
			_hideCss.position = "absolute";
		}
		$('<div class="ECode-floatBar"></div>').css(_hideCss).appendTo("body");

		//修正位置
		var _bar = $("body").find(".ECode-floatBar:last");
		_bar.append(_d.contents);
		var _bw = _bar.width(),
			_bh = _bar.height(),
			_css = {zIndex: _d.zIndex};
		if (_d.id != null) {
			_bar.attr("id", _d.id);
		}
		switch (_d.align) {
			case 'right':
				_css.left = 'auto';
				_css.right = 0;
				break;
			case 'left':
				_css.right = 'auto';
				_css.left = 0;
				break;
			case 'center':
				_css.right = 'auto';
				_css.left = '50%';
				_css.marginLeft = -_bw / 2;
				break;
		}
		switch (_d.vertical) {
			case 'top':
				_css.top = 0;
				break;
			case 'bottom':
				_css.top = 'auto';
				_css.bottom = 0;
				break;
			case 'middle':
				_css.top = '50%';
				_css.marginTop = -_bh / 2;
				if (_ie && _ie <= 6) {
					_css.marginTop = 0;
				}
				break;
		}
		_bar.css($.extend(_css, _d.css));

		/*
		 * 以下代码针对IE6及更古老的版本
		 * 如果感觉不爽，可以将 _d.ieFixed 置为 false
		 * 那么IE6下将不会随屏滚动，囧~~
		 * */
		var fixIE6 = function () {
			var _topHide = $(document).scrollTop(),  //页面上部被卷去高度
				_winHeight = $(window).height(),  //可视区域高度
				_winWidth = $(document).width();  //可视区域宽度
			switch (_d.vertical) {
				case 'top':
					_bar.stop().animate({top: _topHide});
					break;
				case 'bottom':
					var _newTop = _winHeight + _topHide - _bh;
					if (_d.css.marginBottom != null) {
						var _mb = parseInt(_d.css.marginBottom);
						//若果IE6下出现 margin-bottom 为负值，则忽略掉，否则合并计算得出 top 值
						if (_mb >= 0) {
							_newTop -= _mb;
						}
					}
					_bar.css({marginTop: 0}).stop().animate({top: _newTop});
					break;
				case 'middle':
					_bar.stop().animate({top: _winHeight / 2 + _topHide - _bh / 2});
					break;
			}
		};
		if (_d.ieFixed && _ie && _ie <= 6) {
			fixIE6();
			$(window).scroll(function () {
				fixIE6();
			});
			$(window).resize(function () {
				fixIE6();
			});
		}
	};

	//返回方法供页面使用
	return {
		d : d,
		logonurl : logonurl,
		setSearchCity : setSearchCity,
		replaceCityParam : replaceCityParam,
		replaceCityPlaceHolder : replaceCityPlaceHolder,
		registerurl : registerurl,
		logoff : logoff,
		onlineService : onlineService,
		toolBarEvent: toolBarEvent,
		searchEvent: searchEvent,
		onSubmitSearch : onSubmitSearch,
		mainNavEvent : mainNavEvent,
		floatBar: floatBar,
		gotop : gotop
	};

})(jQuery);

function hrefLink(e) {
	if (navigator.userAgent.indexOf("Firefox") > 0) {
		window.location = e
	} else {
		var f = document.createElement("a");
		f.href = e;
		document.body.appendChild(f);
		if (/msie/i.test(navigator.userAgent.toLowerCase())) {
			f.click()
		} else {
			var d = document.createEvent("MouseEvent");
			d.initEvent("click", false, false);
			f.dispatchEvent(d)
		}
	}
}

$(function () {
	// 头部方法
	SFE.base.toolBarEvent();
	//SFE.base.searchEvent();
	//SFE.base.mainNavEvent();
	// 通用懒加载
	//$("img[src2]").Jlazyload({type: "image", placeholderClass: "err-product"});
});
