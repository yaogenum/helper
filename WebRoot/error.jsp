<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>苏宁易购-苏宁云商网上商城，领先的综合网上购物商城，正品行货，全国联保，货到付款，让您尽享购物乐趣！</title>

<meta name="description" content="苏宁易购是领先的综合网上购物商城, 在线特价销售家电、通讯数码、电脑、家居百货、服装服饰、母婴、图书、食品、保险、旅行、充值、团购等数万类商品和服务。正品行货, 全国联保, 本地配送, 货到付款。省钱放心上苏宁电器网上商城, 尽享购物乐趣！">
<meta name="keywords" content="网上购物, 网上购物商城, 手机, 电脑, 冰箱, 洗衣机, 相机, 数码, 家居用品, 鞋帽, 化妆品, 母婴用品, 图书，食品，保险，旅行，充值，团购，苏宁电器官方网站, 苏宁电器网上商城, suning, 正品行货, 全国联保, 货到付款。">
	<meta content="noindex,nofollow" name="robots">
<link rel="shortcut icon" href="http://www.suning.com/favicon.ico" type="image/x-icon">
<style style="text/css">
@charset "utf-8";
body,h1,h2,h3,h4,h5,h6,hr,p,dl,dt,dd,ul,ol,li,form,fieldset,legend,button,input,textarea,th,td{margin:0;padding:0;}
body,button,input,select,textarea{font:12px/1.5 arial,tahoma,\5b8b\4f53;}
body{background:#FFF;color:#333;}
h1,h2,h3,h4,h5,h6{font-size:100%;}
em,i{font-style:normal;}
fieldset,img{border:0;}
ul,ol,li{list-style:none;}
button,input,select,textarea{font-size:100%;outline:none}
textarea{resize:none;}
table{border-collapse:collapse;border-spacing:0;}
a:link, a:visited{color:#333;text-decoration:none;outline:none;}
a:hover, a:active{color:#F60;text-decoration:underline;}
.clearfix:after{content:".";display:block;height:0;clear:both;visibility:hidden;}
.clearfix{zoom:1;}
.clear{clear:both;display:block;height:0;overflow:hidden;font-size:0;}
.hide{display:none;}
img.err-product{background:url(http://script.suning.cn/images/ShoppingArea/Common/blankbg.gif) no-repeat 50% 50%;}
.wrapper{width:990px;min-width:990px;margin:0 auto;}
.ifmame-header,.ifmame-footer{width:100%;height:156px;margin:0;padding:0;}
.ifmame-footer{height:180px;}
/* 404 content modified by moxiaohe@2013-08-06*/
.errorMessageShowTop{width:960px;height:1px;margin:10px auto 0;overflow:hidden;}
.errorMessageShow{width:958px;height:350px;margin:0px auto 10px auto;color:#666666;border:1px solid #e9e9e9;}
.errorMessageShow span{position:relative;display:block;width:280px;height:105px;margin:24px auto 18px auto;background:url(https://imgssl.suning.com/OtherArea/headerAndFooter/css/images/404noSearchPageMainBg.gif) no-repeat 18px 0px;}
.errorMessageShow span .msg{position:absolute;top:84px;left:-300px;width:870px;text-align:center;color:#FF6600;font-size:16px;}
.errorMessageShow p{display:block;width:422px;height:35px;margin:0px auto;}
.errorMessageShow a.backIndex{display:block;width:113px;height:30px;margin:20px auto 48px auto;background:url(https://imgssl.suning.com/OtherArea/headerAndFooter/css/images/404noSearchPageMainBg.gif) no-repeat 0px -122px;}
.errorMessageShow dl{width:375px;height:65px;margin:0px auto;}
.errorMessageShow dl dt{float:left;width:52px;height:65px;padding-top:1px;color:#ff6501;font-weight:bold;}
.errorMessageShow dl dd{float:left;width:310px;}
.errorMessageShow dl dd a{margin:0px 10px;color:#0072d2;text-decoration:underline;}
.errorMessageShow dl dd a.firtLink{margin-left:0px;}
.errorMessageShow dl dd a:hover{color:#F60;text-decoration:underline;}
.bottomRecom{clear:both;width:960px;margin:0px auto 10px auto;}
.bottomRecom .addRecom01{float:left;width:475px;}
.bottomRecom .addRecom02{float:right;width:475px;}
</style>
<style type="text/css"></style></head>
<body>
<iframe scrolling="no" frameborder="0" class="ifmame-header" src="./error_files/head.htm"></iframe>
<!-- 错误信息提示 -->
<div class="errorMessageShowTop"></div>
<div class="errorMessageShow">
<span></span>
<p>
	<strong>错误页面：<s:actionerror/></strong>
</p>
<a href="http://www.suning.com/" class="backIndex"></a>
<dl>
<dt>您可以：</dt>
<dd>1.检查刚才输入。</dd>
<dd>2.看看我们的帮助，<a href="http://sale.suning.com/OtherArea/bzzx/wdyg.html" class="firtLink">新手上路</a></dd>
<dd>3.去其他地方逛逛：<a href="http://www.suning.com/" class="firtLink">苏宁易购</a>|<a href="http://club.suning.com/">易购社区</a></dd>
</dl>
</div>
<iframe scrolling="no" frameborder="0" class="ifmame-footer" src="./error_files/footer.htm"></iframe>
<input id="errorCode" type="hidden" value="404">


</body></html>