<%@page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>购物助手登录</title>
    
	<meta http-equiv="pragma" content="no-cache" >
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="苏宁,登录,购物助手">
	<meta http-equiv="description" content="苏宁,购物助手登陆界面">
	<!--<meta http-equiv="refresh" content="5; url=suning.jsp" />-->
	
	<meta content="noindex,nofollow" name="robots">

<link rel="shortcut icon" href="http://www.suning.com/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="./login_files/snAccount_v2.css">
<script type="text/javascript" async="" src="./login_files/ga.js"></script><script type="text/javascript" async="" src="./login_files/sa.js"></script><script type="text/javascript" async="" src="./login_files/da_ga.js"></script><script type="text/javascript" async="" src="./login_files/da_sa.js"></script><script type="text/javascript" src="./login_files/jquery-1.7.1.js"></script><style type="text/css"></style>
<script type="text/javascript" src="./login_files/snAccount_v2.js"></script>
<script type="text/javascript" src="./login_files/snUserLogin.js"></script>
<script type="text/javascript"  src="js/register.js"></script>
<script language="javascript">


//图片验证码输错三次才刷新
var gImgVerCdeErrorCount = 0;
$(function(){
checkInputEvent();
showServerErrorMsg();
initLoginEvent();
});
function checkInputEvent(){
if($("#email2").val() != ''){
$("#email2").parent("dd").addClass("hide-label")
}else{
$("#email2").parent("dd").removeClass("hide-def-name")
}
$("#email2").blur(hideErrorInfo);
$("#email2").keypress(onNormalLogonKeyPress);
//	$("#password").focus(function(){
//		$("#passwordErrMsg").html('<em class="tipTip"></em><span style="color:#333">请输入密码！</span>');
//	});
$("#password").blur(checkNormalLogonPwdOnBlur);
$("#password").keypress(onNormalLogonKeyPress);
$("#validate").focus(function(){
$("#vcodeErrMsg").html('');
});
$("#validate").blur(checkNormalLogonValidateCodeOnBlur);
$("#validate").keypress(onNormalLogonKeyPress);
// 访客登录
//	$("#cellphone").focus(function(){
//		$("#cellphoneErrMsg").html('<em class="tipTip"></em><span style="color:#333">请输入手机号码！</span>');
//	});
//	$("#cellphone").blur(function(){
//		checkCellphone();
//	});
//	$("#cellphone").keypress(onMobileLogonKeyPress);
//	$("#dynamicPwd").focus(function(){
//		$("#dynamicPwdErrMsg").html('<em class="tipTip"></em><span style="color:#333">请输入激活码！</span>');
//	});
//	$("#dynamicPwd").blur(function(){
//		checkDynamicPwd();
//	});
//	$("#dynamicPwd").keypress(onMobileLogonKeyPress);
//初始的时候设定好位置
$("#vcodeErrMsg").show().html('');
}
// 普通登录处理回车事件
function onNormalLogonKeyPress(event) {
var code =event.keyCode;
if(code==13) {
prepareLogonSubmit();
}
}
// 访客登录处理回车事件
//function onMobileLogonKeyPress(event) {
//	var code =event.keyCode;
//	if(code==13) {
//		preprepareLogonMobileSubmit();
//	}
//}
// 显示服务器端过来的错误信息
// 能判断字段的，显示在输入框下方，不能判断的，显示在最下方按钮上方
function showServerErrorMsg() {
// 普通登录的服务器端过来的错误信息

// 快速登录的服务器端过来的错误信息（ErrorMsg如果含有单引号会报错）

}
function fun_getVcode(){
var timenow = new Date().getTime();
var uid = document.getElementById("uuid").value;

document.getElementById("vcodeimg1").src =  "https://vcs.suning.com/vcs/imageCode.htm?uuid="+ uid +"&yys=" + timenow;
gImgVerCdeErrorCount = 0;
}
function prepareLogonSubmit(){
if(!checkNormalLogonIdOnSubmit() || !checkNormalLogonPwdOnSubmit() || !checkNormalLogonValidateCodeOnSubmit() ) {
return;
}
//$("#normalLoadingArea").show();
//$("#normalLoginButtonArea").hide();
$(".login-btn").addClass("login-now-btn");
$('#Logon').submit();
}
//添加时间戳
//function getTimeStamp(){
//	   var d = new Date();
//	   return d.getHours()+ d.getMinutes() + d.getSeconds() + d.getMilliseconds()+ Math.random()*1000 ;
//}
//function preprepareLogonMobileSubmit(){
//	if(!checkCellphone() || !checkDynamicPwd()) {
//	    return;
//  	}
///	$("#mobileLoadingArea").show();
//	$("#mobileLoginButtonArea").hide();
//	var timeStamp = getTimeStamp();
//	document.getElementById("mobileForm").action = "SNVerifyDynamicPwdCmd?timeStamp="+timeStamp;
//	$('#mobileForm').submit();
//}
//访客登录
//function showDivMobileLogin(){
//    document.getElementById('normalLoginBox').style.display = 'none';
//    document.getElementById('mobileLoginBox').style.display = 'block';
//}
//普通登录
//function showDivNormalLogin(){
//    document.getElementById('normalLoginBox').style.display = 'block';
//    document.getElementById('mobileLoginBox').style.display = 'none';
//}
//普通登录：提交时检查账号
function checkNormalLogonIdOnSubmit() {
// 清掉服务器错误消息
//$("#normalLogonServerErrArea").hide();
hideErrorInfo();
var eml = $('#email2').val();
if(eml=='用户名/邮箱/手机号/门店会员卡号'){
//$('#email2').val("");
eml = "";
}
if (eml == null || eml == "") {
//$("#logonIdErrMsg").html('<em class="tipFalse"></em>请输入用户名/邮箱/注册手机！');
showErrorInfo('请输入用户名/邮箱/手机号/门店会员卡号！',this);
return false;
}
var pattern = /^([0-9]+)$/;
if(eml.length == 12 &&  pattern.test(eml)){
//$("#logonIdErrMsg").html("<em class='tipFalse'></em>抱歉无法使用会员卡号直接登录，请使用用户名登录");
//showErrorInfo('抱歉无法使用会员卡号直接登录，请使用用户名登录',this);
//return false;
}
if (eml.length != 0 && eml.length < 50
&& eml.match( /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/ )){
$('#logonId').val(eml.toLowerCase());
} else {
$('#logonId').val(eml);
}
//$("#logonIdErrMsg").html('');
hideErrorInfo();
return true;
}
//普通登录：失去焦点时检查密码
function checkNormalLogonPwdOnBlur() {
// 清掉服务器错误消息
hideErrorInfo();
var pwd = $('#password').val();
if (pwd == null || pwd == "") {
//$("#passwordErrMsg").html('');
hideErrorInfo();
return false;
} else if (pwd.length<6 || pwd.length>20) {
//$("#passwordErrMsg").html('<em class="tipFalse"></em>请输入6-20位密码！');
showErrorInfo('请输入6-20位密码！',this);
return false;
} else {
hideErrorInfo();
//$("#passwordErrMsg").html('');
return true;
}
}
//普通登录：提交时检查密码
function checkNormalLogonPwdOnSubmit() {
// 清掉服务器错误消息
//$("#normalLogonServerErrArea").hide();
hideErrorInfo();
var pwd = $('#password').val();
if (pwd == null || pwd == "") {
//$("#passwordErrMsg").html('<em class="tipFalse"></em>请输入密码！');
showErrorInfo('请输入密码！',this);
return false;
} else if (pwd.length<6 || pwd.length>20) {
//$("#passwordErrMsg").html('<em class="tipFalse"></em>请输入6-20位密码！');
showErrorInfo('请输入6-20位密码！',this);
return false;
} else {
//$("#passwordErrMsg").html('');
hideErrorInfo();
return true;
}
}
// 普通登录：失去焦点时检查图片验证码
function checkNormalLogonValidateCodeOnBlur() {
// 清掉服务器错误消息
//$("#normalLogonServerErrArea").hide();
//hideErrorInfo();
var vcd = $('#validate').val();
if (vcd == null || vcd == "") {
//$("#vcodeErrMsg").show().html('');
hideErrorInfo();
return false;
} else if(vcd.length != 4){
//$("#vcodeErrMsg").show().html('<em class="tipFalse"></em>输入的验证码错误，请重新输入！');
showErrorInfo('输入的验证码错误，请重新输入！',this);
return false;
}else {
//$("#vcodeErrMsg").html('');
//hideErrorInfo();
checkImageValCode();
return true;
}
}
//普通登录：提交时检查图片验证码
function checkNormalLogonValidateCodeOnSubmit() {
// 清掉服务器错误消息
//$("#normalLogonServerErrArea").hide();
hideErrorInfo();
if (gImageVerifyNecessityByLogonIP || gImageVerifyNecessityByLogonUser) {
// 继续往下处理
} else {
return true;
}
var vcd = $('#validate').val();
if (vcd == null || vcd == "") {
//$("#vcodeErrMsg").show().html('<em class="tipFalse"></em>请输入验证码！');
showErrorInfo('请输入验证码！',this);
return false;
} else if(vcd.length != 4){
//$("#vcodeErrMsg").show().html('<em class="tipFalse"></em>输入的验证码错误，请重新输入！');
showErrorInfo('输入的验证码错误，请重新输入！',this);
return false;
}else {
//$("#vcodeErrMsg").html('');
hideErrorInfo();
return true;
}
}
// 检查访客登录的手机号码
//function checkCellphone() {
// 清掉服务器错误消息
//	$("#mobileLogonServerErrArea").hide();
//	var patrn = /^[1]\d{10}$/;
//	var cellphone = $('#cellphone').val();
//	if(cellphone == null || cellphone == "") {
//		$("#cellphoneErrMsg").html('<em class="tipFalse"></em>请输入手机号码！');
//	    return false;
//  	}
//  	if(!patrn.test(document.getElementById('cellphone').value)) {
//  		$("#cellphoneErrMsg").html('<em class="tipFalse"></em>输入的手机号格式不正确，请重新输入！');
//	    return false;
//  	}else {
//  		$("#cellphoneErrMsg").html('');
//  		return true;
//  	}
//}
// 检查访客登录的动态密码
//function checkDynamicPwd() {
// 清掉服务器错误消息
//	$("#mobileLogonServerErrArea").hide();
//	var dynamicPwd = $('#dynamicPwd').val();
// 	if(dynamicPwd == null || dynamicPwd == "") {
//  		$("#dynamicPwdErrMsg").html('<em class="tipFalse"></em>请输入激活码！');
//	    return false;
//	} else if (dynamicPwd.length != 6) {
//		$("#dynamicPwdErrMsg").html('<em class="tipFalse"></em>请输入6位激活码！');
//		return false;
//  	}else {
//  		$("#dynamicPwdErrMsg").html('');
//  		return true;
// 	}
//}
String.prototype.Trim = function(){
return this.replace(/(^\s*)|(\s*$)/g, "");
}
function oAuthLogon(sourceURL){
document.getElementById('oAuthLogonForm').action=sourceURL;
document.getElementById('oAuthLogonForm').submit();
}
function showErrorInfo(msg,obj){
$(".entry-tip").find("i").removeClass("tip-info").addClass("tip-error");
$(".entry-tip").show().find("span").html(msg);
$(obj).addClass("login-tip-error");
}
function hideErrorInfo(){
$(".entry-tip").hide().find("span").html('');
$(":input").removeClass("login-tip-error");
}
function forgetLoginName(){
$(".entry-tip").find("i").removeClass("tip-error").addClass("tip-info");
$(".entry-tip").show().find("span").html('您可以使用用户名/邮箱/手机号/会员卡号进行登录。');
}
</script>
</head>
<body>

<div id="grayLayer" style="display:none;"></div>

<div class="login-wrap">
<div class="w940">
<div class="header">
<h1><a title="苏宁易购" href="http://www.suning.com/"><img alt="苏宁易购" src="./login_files/snlogo.png"></a></h1>
</div>
<div class="login-box clearfix">
<div class="pic">
<!--  a target="_blank" href="http://sale.suning.com/images/advertise/001/o2ogwj/index.html"><img src="https://imgssl.suning.com/images/1/cjlygdl.jpg" class="" alt="" /></a>-->
<a target="_blank" href="http://sale.suning.com/images/advertise/001/o2ogwj/index.html" name="Logon_index_denglu005"><img src="./login_files/cjlygdl.jpg" class="" alt=""></a>
</div>
<div class="form">
<div class="free-regist">新用户, <a href="https://member.suning.com/emall/SNUserRegisterView?storeId=10052&catalogId=10051&campaignName=" name="Logon_index_denglu003">免费注册&gt;</a></div>
<!--
<form class="entry" method="post" name="Logon" >
<input type="hidden" name="campaignName" id="campaignName" value="">
<input type="hidden" name="logonId" id="logonId" value=" ">
<input type="hidden" name="storeId" value="10052" id="WC_RememberMeLogonForm_FormInput_storeId_In_Logon_1">
<input type="hidden" name="catalogId" value="10051" id="WC_RememberMeLogonForm_FormInput_catalogId_In_Logon_1">
<input type="hidden" name="reLogonURL" value="LogonForm" id="WC_RememberMeLogonForm_FormInput_reLogonURL_In_Logon_1">
<input type="hidden" name="personalizedCatalog" value="true" id="WC_RememberMeLogonForm_FormInput_personalizedCatalog_In_Logon_1">
<input type="hidden" name="fromOrderId" value="*" id="WC_RememberMeLogonForm_FormInput_fromOrderId_In_Logon_1">
<input type="hidden" name="toOrderId" value="." id="WC_RememberMeLogonForm_FormInput_toOrderId_In_Logon_1">
<input type="hidden" name="deleteIfEmpty" value="*" id="WC_RememberMeLogonForm_FormInput_deleteIfEmpty_In_Logon_1">
<input type="hidden" name="continue" value="1" id="WC_RememberMeLogonForm_FormInput_continue_In_Logon_1">
<input type="hidden" name="createIfEmpty" value="1" id="WC_RememberMeLogonForm_FormInput_createIfEmpty_In_Logon_1">
<input type="hidden" name="calculationUsageId" value="-1" id="WC_RememberMeLogonForm_FormInput_calculationUsageId_In_Logon_1">
<input type="hidden" name="updatePrices" value="1" id="WC_RememberMeLogonForm_FormInput_updatePrices_In_Logon_1">
<input type="hidden" name="previousPage" value="logon" id="WC_RememberMeLogonForm_FormInput_previousPage_In_Logon_1">
<input type="hidden" name="returnPage" value="" id="WC_RememberMeLogonForm_FormInput_returnPage_In_Logon_1">
<input type="hidden" name="returnPage" value="" id="hidden_email12">
<input type="hidden" name="errFlg" id="errFlg" value="1">
<input type="hidden" name="valFlg" id="valFlg">
<input type="hidden" name="uuid" id="uuid" value="b2398d60-562c-46e9-8427-8035eed79ddb">
<input type="hidden" name="imageVerificationOptimized" id="imageVerificationOptimized" value="true">
<input type="hidden" name="URL" value="MyOrdersView?catalogId=10051&amp;storeId=10052" id="WC_RememberMeLogonForm_FormInput_URL_In_Logon_1">

<h2 class="title">会员登录</h2>
<div class="entry-tip hide">
<table border="0">
<tbody><tr><td><i class="tip-icon tip-error"></i><span>报错位置</span></td></tr>
</tbody></table>
</div>
<dl class="entry-name">
<dt><label for="email2">账号</label></dt>
<dd class="hide-label></form>
<label for="email2">用户名/邮箱/手机号/门店会员卡号</label>
<form action="send/userlogin" method="post">
<input type="text" name="username" id="username" value="1355298881@qq.com" class="text login-name" autocomplete="off" tabindex="1">
</dd>
</dl>
<dl>
<dt><label for="password">密码</label></dt>
<dd>
<input type="password" id="password" name="userpassword" maxlength="20" value="" class="text " autocomplete="off" tabindex="2">
</dd>
</dl>
<dl class="check-code" id="imageVerifyArea" style="display: none;">
<!-- <dt><label for="validate">验证码</label></dt> -->
<!--<dd class="clearfix">
<input type="text" class="text" id="validate" maxlength="4" name="verifyCode" tabindex="3">
<img width="70" height="30" id="vcodeimg1" name="vcodeimg1" src="./login_files/imageCode.htm" onclick="fun_getVcode()">

</dd>-->
</dl>
<!-- <div class="forget-psd">
<a href="https://member.suning.com/emall/RetrievePasswordView?storeId=10052&catalogId=10051" name="Logon_index_denglu001">忘记密码?</a>
</div> -->



<div class="login-btn">
<div style="padding-left:35px;">
	<div style="padding-bottom:5px;font-size:25px;color:red;margin-top:-30px;font-family:楷体;">赶快体验一下吧</div>
	<div style="width:350px;font-size:20px;padding-bottom:10px;"><b>用户名：suning  &nbsp;&nbsp;&nbsp;   密码：suning</b></div>
  	<s:form action="userlogin" namespace="/login" >
			
			<tr >
				<s:textfield name="username" key=" 账号"  />
			</tr>
			
			<tr>
			<s:password name="userpassword" key=" 密码" style="margin-top:8px;" />
			</tr><br/>
			<tr>
				<s:submit key="" style="width:303px;height:43px;margin-top:10px;background:url(./login_files/btn.png);margin-left:-2px;border:none" onclick="register()" />
			</tr>
	</s:form>
	</table>
<!--<input type="submit" value="" style="width:303px;height:43px;background:url(./login_files/btn.png);margin-left:-2px;border:none" onclick="register()"/>-->

</div>
</div>

<div class="login-now-btn hide">
<a href="./login_files/login.htm" class="btn-entry" tabindex="4">登录</a>
</div>
<div class="login_other clearfix">
<p>
 <s:fielderror name="nottrue" /> 



</p>
<span class="l">用合作网站账号登录：</span>

<a href="javascript:void(0);" name="Logon_index_denglu007" onclick="javascript:oAuthLogon(&#39;OAuthCall_10052_10051_10201&#39;);return false;" class="l"><em class="tianyi l"></em>天翼</a>

</div>

<form method="post" name="oAuthLogonForm" action="" id="oAuthLogonForm">
<input type="hidden" name="URL" value="MyOrdersView?catalogId=10051&amp;storeId=10052">
</form>
<div class="login-card-redbaby clearfix">
<a href="https://member.suning.com/emall/SNMbrCardInputView?storeId=10052&catalogId=10051" class="a-card" name="Logon_index_denglu012">
<strong>门店会员首次登录<s>&gt;</s></strong><br><span>持有苏宁电器/乐购仕会员卡</span><i></i>
</a>

<a name="Logon_index_denglu006" href="javascript:void(0);" onclick="javascript:oAuthLogon(&#39;OAuthCall_10052_10051_10101&#39;);return false;" class="a-redbaby">
<strong>红孩子会员首次登录<s>&gt;</s></strong><br><span>若您是红孩子/缤购会员</span><i></i>
</a>

</div>
<div class="box-shadow"></div>
</div>
</div>
</div>
</div>

<script language="javascript">
// var msg = '';
// if(msg=='1'){
// var logbox = document.getElementById('normalLoginBox');
// var logboxmobilebox = document.getElementById('mobileLoginBox');
// logbox.style.display = "none";
// logboxmobilebox.style.display = "block";
// }
</script>
<span class="clear"></span>

<link type="text/css" rel="stylesheet" href="./login_files/common.css">

<script type="text/javascript">
var sn=sn||{"context":'/emall',"domain":'www.suning.com',"storeId":'10052',"catalogId":'10051',"memberDomain":'member.suning.com',"online":'online.suning.com',"cookieDomain":'.suning.com',"searchDomain" : 'http://search.suning.com/emall/',"categoryId":"10051"};
</script>
<script type="text/javascript" src="./login_files/SFE.base.js"></script>
<script type="text/javascript" src="./login_files/sn_lazyload.js"></script>
<div class="clear"></div>
<!-- 公用尾部 [[ -->
<div class="g-footer">
<div class="copyrightbox">
<div class="copyright">
<dl class="sncompany">
<dd><a target="_blank" href="http://www.suning.cn/">苏宁云商</a><span>|</span><a href="http://www.suning.com/">苏宁易购</a><span>|</span><a href="http://redbaby.suning.com/" target="_blank">红孩子</a><span>|</span><a href="http://binggo.suning.com/" target="_blank">缤购</a><span>|</span><a href="http://www.suning.com/emall/LegousMainView?catalogId=10051&storeId=10052" target="_blank">乐购仕</a><span>|</span><a href="https://pay.suning.com/epp-portal/useraccount/user-account!initUserAccount.action" target="_blank">易付宝</a></dd>
</dl>
<p class="snlinks">
<a href="http://club.suning.com/hr/aboutus.html" target="_blank">关于苏宁易购</a><span>|</span><a href="http://image.suning.cn/OtherArea/bzzx/lxwm.html" target="_blank">联系我们</a><span>|</span><a href="http://careers.cnsuning.com/" target="_blank">诚聘英才</a><span>|</span><a href="http://sop.suning.com/" target="_blank">供应商入驻</a><span>|</span><a href="http://union.suning.com/" target="_blank">苏宁联盟</a><span>|</span><a href="http://member.suning.com/emall/linkView_10052_10051_.html" target="_blank">友情链接</a><span>|</span><a href="http://sale.suning.com/OtherArea/bzzx/yssm.html" target="_blank">法律声明</a><span>|</span><a href="http://bbs.suning.com/ques" target="_blank">用户体验提升计划</a>
</p>
<p class="rights">Copyright© 2002-2013 ，苏宁版权所有 苏ICP备10207551号 苏B2-20100316 出版物经营许可证新出发苏批字第A-243号</p>
<div class="subfooter">
<a href="https://ss.cnnic.cn/verifyseal.dll?sn=2011060700100009557&ct=ic&pa=911439" title="可信网站示范单位" target="_blank"><img src="./login_files/morelogo2.png"></a>
<a href="http://search.cxwz.org/cert/l/CX20111018000608000610" title="诚信网站" target="_blank"><img src="./login_files/morelogo6.png"></a>
<a href="http://www.jsgsj.gov.cn:60101/keyLicense/verifKey.jsp?serial=320000163820130117100000009630&signData=0+ADYt839gp1EiqiZXnsxsyOnpO32Wg4sFePaiV9+NtTV/XCAMXGzT/AOgycGMm0EjsR/Ot661M7h9GeStpA8QyJTs1Ip1K/CSNaemthn7f1NjI03x1E6v9ZRT+3M60WZIGLBEjFs5XMliufNz1cJlYDQrTZvaZbHyJ2KzgJB4Y=" title="电子营业执照" target="_blank"><img src="./login_files/morelogo7.png"></a>
</div>
</div>
</div>
</div>
<!-- 公用尾部 ]] --><!--问卷调查-->

<script type="text/javascript">
$(function(){
SFE.base.floatBar({contents : $("#snSideTools"),align :"right",vertical:"bottom",css:{"right":60,marginBottom:200}});//左悬浮
$(window).scroll(function(){
var topHide = $(document).scrollTop(); //椤甸潰涓婇儴琚嵎鍘婚珮搴�
var gotop = $("#gotop");
if(topHide>20){
gotop.show();
}else{
gotop.hide();
}
});
});
</script>

<script type="text/javascript">
/** start jsp中用到的js工具方法和对象，不包含任何业务逻辑 */
eval(function(p,a,c,k,e,d){e=function(c){return(c<a?"":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p;}('f 1i(){h.1r=1d 23();h.T=1d 22();h.1C=f(t,1q){7(h.T[t]==1o){h.1r.25(t)};h.T[t]=1q};h.1l=f(t){m h.T[t]};h.21=f(t){7(h.1l(t)!=1o){28 h.T[t]}}};f 1L(){x="1G";h.1T=f(8){5 d="";5 I,E,D,K,F,w,u;5 i=0;8=1h(8);1f(i<8.q){I=8.B(i++);E=8.B(i++);D=8.B(i++);K=I>>2;F=((I&3)<<4)|(E>>4);w=((E&15)<<2)|(D>>6);u=D&J;7(1g(E)){w=u=14}y 7(1g(D)){u=14};d=d+x.v(K)+x.v(F)+x.v(w)+x.v(u)};m d};h.1P=f(8){5 d="";5 I,E,D;5 K,F,w,u;5 i=0;8=8.1m(/[^A-1Q-1N-9\\+\\/\\=]/g,"");1f(i<8.q){K=x.C(8.v(i++));F=x.C(8.v(i++));w=x.C(8.v(i++));u=x.C(8.v(i++));I=(K<<2)|(F>>4);E=((F&15)<<4)|(w>>2);D=((w&3)<<6)|u;d=d+l.k(I);7(w!=14){d=d+l.k(E)};7(u!=14){d=d+l.k(D)}};d=1n(d);m d};1h=f(o){o=o.1m(/\\r\\n/g,"\\n");5 e="";N(5 n=0;n<o.q;n++){5 c=o.B(n);7(c<V){e+=l.k(c)}y 7((c>29)&&(c<26)){e+=l.k((c>>6)|27);e+=l.k((c&J)|V)}y{e+=l.k((c>>12)|1k);e+=l.k(((c>>6)&J)|V);e+=l.k((c&J)|V)}};m e};1n=f(e){5 o="";5 i=0;5 c=2d=P=0;1f(i<e.q){c=e.B(i);7(c<V){o+=l.k(c);i++}y 7((c>2c)&&(c<1k)){P=e.B(i+1);o+=l.k(((c&20)<<6)|(P&J));i+=2}y{P=e.B(i+1);1p=e.B(i+2);o+=l.k(((c&15)<<12)|((P&J)<<6)|(1p&J));i+=3}};m o}};f 1M(Y){5 1j=Y.1A(Y.C("?")+1,Y.q);5 1e=1j.1B("&");5 18=1d 1i();N(5 X=0;X<1e.q;X++){5 Z=1e[X].1B("=");N(5 i=0;i<Z.q;i++){18.1C(Z[i],Z[++i])}};m 18};f 1Z(s,1a){N(5 i=0;i<1a.q;i++){7(s==1a[i]){m 1v}};m 1W};f 1R(M){5 a=M.1J("/");5 b=M.q;7(M.C("?")!=-1){b=M.C("?")};5 1u=M.1A(a,b);m 1u};f 1Y(16){5 1c=16;5 11=G.1X(\'13\');N(5 i=0;i<11.q;i++){7(11[i].16==1c){m}};5 Q=G.2b(\'13\');Q.2e=\'2a/1w\';Q.1H=1v;Q.16=1c;5 s=11[0];s.1K.1D(Q,s)};f 1F(1x){5 z="";7(1E.10(L)){5 1s=("j:"==G.S.R)?"j://1t.p.U":"H://13.p.O";z=1s}y 7(1S.10(L)){5 W=("j:"==G.S.R)?"j://1y.p.U":"H://1z.p.O";z=W;}y 7(1V.10(L)){5 19=L.v(L.C("1b")+3);5 j="j://1b"+19+"1t.p.U";5 H="H://1b"+19+"13.p.O";5 17=("j:"==G.S.R)?j:H;z=17}y 7(1O.10(L)){5 17=("j:"==G.S.R)?"j://1U.p.U":"H://1I.p.O";z=17}y{5 W=("j:"==G.S.R)?"j://1y.p.U":"H://1z.p.O";z=W};z=z+"/1w/24/"+1x;m z}',62,139,'|||||var||if|input|||||output|utftext|function||this||https|fromCharCode|String|return||string|suning|length|||key|enc4|charAt|enc3|_keyStr|else|sa_src||charCodeAt|indexOf|chr3|chr2|enc2|document|http|chr1|63|enc1|_hostName|url|for|cn|c2|sga|protocol|location|data|com|128|sa_pre_path|ii|thisUrl|parmArr|test|_ss||script|64||src|sa_sit_path|map|num|arr|sit|sga_src|new|parmsArr|while|isNaN|_utf8_encode|HashMap|str|224|get|replace|_utf8_decode|null|c3|value|keys|sa_prd_path|imgssl|keyword|true|javascript|js_file|preimgssl|prescript|substring|split|put|insertBefore|ego_prd_reg|getJsFilePath|ABCDEFGHIJKLMNOPQRSTUVWXYZzyxwvutsrqponmlkjihgfedcba0987654321|async|sit1script|lastIndexOf|parentNode|Base64|getUrlParamsMap|z0|ego_sit1_v7_reg|decode|Za|getkeyWord|ego_pre_v7_reg|encode|sit1imgssl|ego_sit_v7_reg|false|getElementsByTagName|_loadJs|inArray|31|remove|Object|Array|sn_da|push|2048|192|delete|127|text|createElement|191|c1|type'.split('|'),0,{}))
/** end jsp中用到的js工具方法和对象，不包含任何业务逻辑 */
</script>
<script type="text/javascript">
/**
* 以下域名变量 在da_sa.js和da_ga.js文件中用到
*/
//prd host
var ego_prd_reg = /^(\w*)(.suning.com)$/;
//pre host
var ego_pre_v7_reg = /^(\w*)(pre)(\w*)(.cnsuning.com)$/;
//sit host
var ego_sit_v7_reg = /^(\w*)(sit)([1-5].cnsuning.com)$/;
var ego_sit1_v7_reg = /^(\w*)(sit)(\w*)(.cnsuning.com)$/;
var _hostName = document.location.hostname;//在da_ga.js文件中也有用到
var _thisUrl = document.location.href;//三个js文件都有用到
/**
* 在引入的三个js文件中需要用到的需jsp文件动态获取的变量
*/
var sa_userId = "-1002";
var sa_userType = "G";
var ag_orderId = "";
var sa_orderId = document.getElementById("orderId");
sa_orderId = sa_orderId ? $("#orderId").val() : "";
var sa_isNew = document.getElementById("gaga");
sa_isNew = sa_isNew ? $("#gaga").val() : "";
var sa_quickRegister = document.getElementById("gagaId");
sa_quickRegister = sa_quickRegister ? $("#gagaId").val() : "";
//sa集成层与sa业务处理层之间的异步容器
var _dasaMap = _dasaMap || new HashMap();
//ga集成层与ga业务处理层之间的异步容器
var _dagaMap = _dagaMap || new HashMap();
/**
* 功能：ga和sa电器四级页面库存数据采集
* 调用：电器四级页面异步成功的时候采用侦听的方式调用此方法
*/
function _dapush(){
_dasaMap.put("_sapush", "");
_dagaMap.put("_gapush", "");
}
/**
* 功能：ga和sa图书四级页面库存数据采集
* 调用：图书四级页面异步成功的时候采用侦听的方式调用此方法
*/
function _dapushbook(){
_dasaMap.put("_sapushbook", "");
_dagaMap.put("_gapushbook", "");
}
function _searchDataSaPush(totalRows){
_dasaMap.put("_searchPush", totalRows);
}
</script>
<script type="text/javascript">
(function() {
var da_sa_src = getJsFilePath("da_sa.js");
var da_ga_src = getJsFilePath("da_ga.js");
_loadJs(da_sa_src);
_loadJs(da_ga_src);
})();
</script>

<script type="text/javascript">
/*
* 会员模块(登录/注册/我的易购)点击行为数据采集JS
* Author: 10010585
* Date: 2013-08-15
* */
var sn_prd_reg = /^\w*?.suning.com$/;
var hostName = document.location.hostname;
var protocol = (("https:" == document.location.protocol) ? "https://" : "http://");
var server = getServer();
_tag = "|";
function sendDatasMember(data){
var name = data.name;
var id = data.id;
var Datas = id + "|" + name;
var clickUrl = protocol + server + "/ajaxClick.gif";
var oId = getOnlyIdMember();
var _snck = "_snck";
_addCookie4Member(_snck, oId, '/', "", "");
var pvId = getCookieMember("_snmp");
var cDatas =oId + _tag + pvId + _tag + Datas;
var url = clickUrl + "?" + "_snmk=" + cDatas ;
httpGifSendMember(url);
}
//获取唯一标识
function getOnlyIdMember(){
var now = new Date();
var m = Math.round(100000*Math.random());
var onlyId = now.getTime().toString().concat(m);
return onlyId;
}
// 获取cookie
function getCookieMember(name){
var arrStr = document.cookie.split("; ");
for(var i = 0;i < arrStr.length;i ++){
var temp = arrStr[i].split("=");
if(temp[0] == name) return unescape(temp[1]);
}
}
// 创建一个img标签,发送数据
function httpGifSendMember(strURL) {
var img_src = strURL;
var imgTag = document.createElement('img');
imgTag.src = img_src;
}
//判断环境
function getServer(){
if(sn_prd_reg.test(hostName)){//生产环境
return "click.suning.cn/sa";
} else {//pre、sit环境
return "clicksit.suning.cn/sa";
}
}
//添加cookie，name=value，expire为过期毫秒数
function _addCookie4Member(name, value, path, expires ,domain){
var str=name+"="+escape(value);
if(expires!=""){
var date=new Date();
date.setTime(date.getTime()+expires);
str+=";expires="+date.toGMTString();
}
if(path!=""){
str+=";path="+path;// 指定可访问cookie的目录
}
var dm = hostName;
if(dm.indexOf(".suning.com") != -1){
str+=";domain=.suning.com";// 解决跨域跟踪问题
} else if (dm.indexOf(".cnsuning.com") != -1){
str+=";domain=.cnsuning.com";// 解决跨域跟踪问题
} else {
str+=";domain="+domain;
}
document.cookie=str;
}
$(document).ready(function(){
//登录页面发送数据到SA
$("a[name*=Logon_index_],input[name*=Logon_index_]").live("click",function(){
sendDatasMember(this);
});
//注册页面发送数据到SA
$("a[name*=register_info_],input[name*=register_info_]").live("click",function(){
sendDatasMember(this);
});
//注册成功页面发送数据到SA
$("a[name*=register_suc_],input[name*=register_suc_]").live("click",function(){
sendDatasMember(this);
});
//我的易购首页发送数据到SA
$("a[name*=MySuning_index_],input[name*=MySuning_index_]").live("click",function(){
sendDatasMember(this);
});
//会员卡绑定易购账号流程验证会员卡和密码页面发送数据到SA
$("a[name*=CardLogon_],input[name*=CardLogon_]").live("click",function(){
sendDatasMember(this);
});
//会员卡绑定易购账号流程验证会员卡信息页面发送数据到SA
$("a[name*=CardInfo_],input[name*=CardInfo_]").live("click",function(){
sendDatasMember(this);
});
//会员卡绑定易购账号流程选择合并方式页面发送数据到SA
$("a[name*=MergeWay_],input[name*=MergeWay_]").live("click",function(){
sendDatasMember(this);
});
//会员卡绑定易购账号流程合并新建易购账号页面发送数据到SA
$("a[name*=MobRegister_],input[name*=MobRegister_]").live("click",function(){
sendDatasMember(this);
});
//会员卡绑定易购账号流程合并存在易购账号页面发送数据到SA
$("a[name*=CheckNum_],input[name*=CheckNum_]").live("click",function(){
sendDatasMember(this);
});
//会员卡绑定易购账号流程合并存在易购账号弹框页面（包括邮箱验证码弹框和手机验证码弹框）发送数据到SA
$("a[name*=TestNum_],input[name*=TestNum_]").live("click",function(){
sendDatasMember(this);
});
//会员卡绑定易购账号流程合并成功页面发送数据到SA
$("a[name*=MergeSuc_],input[name*=MergeSuc_]").live("click",function(){
sendDatasMember(this);
});
//易购账号合并会员卡流程页面发送数据到SA
$("a[name*=Mergecard_],input[name*=Mergecard_]").live("click",function(){
sendDatasMember(this);
});
//会员卡绑定易购账号流程合并新建易购账号页面发送数据到SA（new add by 12070643 2013-10-11）
$("a[name*=MobReg_index_none_],input[name*=MobReg_index_none_]").live("click",function(){
sendDatasMember(this);
});
});
</script>

<script type="text/javascript">

function  checkCapsLock(event){
var e = event||window.event;
var keyCode  =  e.keyCode||e.which; 
var isShift  =  e.shiftKey ||(keyCode  ==   16 ) || false ; 
if (((keyCode >=   65   &&  keyCode  <=   90 )  &&   !isShift) 
|| ((keyCode >=   97   &&  keyCode  <=   122 )  &&  isShift)
){
$(".entry-tip").find("i").removeClass("tip-error").addClass("tip-info");
$(".entry-tip").show().find("span").html('键盘大写锁定已打开，请注意大小写 。');
}else{
$(".entry-tip").hide().find("span").html('');
}
}
document.getElementById('password').onkeypress = checkCapsLock;
</script>


<div class="ECode-floatBar" style="position: fixed; top: auto; left: auto; z-index: 7500; right: 60px; bottom: 0px; margin-bottom: 200px;"><div id="snSideTools">
<ul>
<!-- <li><a href="http://club.suning.com/ques/Index/questionnaire/type/cart4.html" title="问卷调查" class="sntool2"></a></li> -->
<li id="gotop"><a href="./login_files/login.htm" title="回顶部" class="sntool3"></a></li>
</ul>
</div></div></body></html>