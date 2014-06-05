<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="./css/suning.css"/>
<link rel="stylesheet" type="text/css" href="./css/style.css"/>
<script type="text/javascript" src="js/suning.js" ></script>
<script type="text/javascript" src="js/move.js" ></script>
<script type="text/javascript" src="js/change.js" ></script>
<script type="text/javascript" src="js/close.js" ></script>
<script type="text/javascript" src="js/play.js" ></script>
<script type="text/javascript" src="js/starMove.js" ></script>
<script type="text/javascript" src="jquery/jquery.js"></script>
<script type="text/javascript" src="js/ajaxsender.js"></script>
<script>
window.onload=function(){
	suning();
	move();
	change();
	close();
	play();
	show();
	starMove();
};
</script>
</head>
<body>
<div id="all">
  <div id="top">
	<ul id="ul1">
           <li><img src="./images/top1.jpg"><a href=="#">手机版</a></li>
           <s:if test="#username!=''">
           		 <li> <s:property value="username"/>您好,欢迎来苏宁易购！
           		 </li>
           </s:if>
           <s:else>
			 <li><a href=="#">登录 </a></li>
			</s:else> 
				<li><a href=="#">注册</a></li>
		</ul>
		<ul id="ul2">
           <li><a href="">回易购首页</a></li>
			<li><a href="">我的订单</a></li>
				<li><a href=="#">我的易购</a></li>
					<li><a href=="#">在线客服</a></li>
						<li>服务中心</li>
							<li>网站导航</li>
		</ul>
  </div>
	<div id="two">
			<div id="suning"></div>
				<div id="find">
					<input type="text"/>
						<div id="find_img"></div>
					</div>
						<div id="shop"><span><a href="#">购物车</a></span></div>
		</div>
		<ul id="three"><span><img src="./images/span.jpg"></span>
			<li><a href="">首页</a></li>
				<li><a href="">电器城</a></li>
					<li><a href="">红孩子母婴</a></li>
						<li><a href="">嫔购美妆</a></li>
							<li><a href="">书城</a></li>
								<li><a href="">团购</a></li>
		    </ul>
	<div id="nav">
		<ul id="three_top">
			<li><a href="">首页 >&nbsp </a></li>
				<li><a href="">&nbsp电脑/办公/外设 > </a></li>
					<li><a href=""> &nbsp电子整机 > </a></li>
						<li><a href=""> &nbsp平板电脑 > </a></li>
					<li> &nbsp苹果 iPad 4 WiFi版 9.7英寸平板电脑 16G 白色 MD513CH/A </li>
		    </ul>
			<div id="left">
				<div id="ph"></div>
				<div id="left_footer">
				  <a href="javascript:;"><div id="left_left"></div></a>
				<div id="left_1">
				<ul style="position:relative;">
					<li><img src="./images/li1.jpg";/></li>
					<li><img src="./images/li2.jpg";/></li>
					<li><img src="./images/li3.jpg";/></li>
					<li><img src="./images/li4.jpg";/></li>
					<li><img src="./images/li5.jpg";/></li>
				</ul>
				</div>
				  <a href="javascript:;"><div id="left_right"></div></a>
				</div>
			</div>
			<div id="right">
				<h4>苹果 iPad 4 WiFi版 9.7英寸平板电脑 16G 白色 MD513CH/A</h4>
				<h4 style="color:#da6c21">
				高分辨率 Retina 视网膜屏，非同一般的视觉体验，全新 A6X 速度提升2倍！
				</h4>
				<hr>
			<div id="right_left">
			<table border="0"id="table">
					<tr>
					  <td id="td1">易购价</td>
						<td id="pay">¥3338.00</td>
					</tr>
					<tr>
					  <td id="td1">商品满意度</td>
						<td id="stf">
							<img src="./images/stf.jpg"><a href="#">								（共有5001条评价）
					  </a>
					  </td>
					</tr>
					<tr>
					  <td id="td1">送至</td>
					 <td id="to">
					  <select>
						<option>重庆市 万州区</option>
						<option>重庆市 万州区</option>
						<option>重庆市 万州区</option>
					  </select>
					 </td>
					</tr>
					<tr id="td1">
					<td></td>
					<td style="color:b3100b;">
					暂无货点到货通知，到货第一时间通知您!
					</td>
					</tr>
					<tr>
					  <td id="td1">服务</td>
					  <td style="font-size:14px;">				 					由“苏宁”直接销售和发货，并提供售后服务
					  </td>
					</tr>
					<tr>
					  <td id="td1">温馨提示</td>
					  <td style="font-size:14px;">该商品支持
					  <span id="paydate">
					  <img src="./images/pay.jpg">
					  <a href="#">分期付款</a></span>
					  </td>
					</tr>
				</table>
			</div>
				<div id="right_right">
					<ul>
						<h5>本店详情</h5>
						<li>苏宁自营<br/>商家满意度 <img src="./images/stf.jpg">
						</li>
						<li>在线客服：<img src="./images/face.jpg">
						<a href="#">和我联系</a>
						</li>
					</ul>
					<ul>
						<h5>全部优质商家 &nbsp<a href="#">（1）家
						</a><img src="./images/right.jpg"></h5>
						<li>苏宁自营<br/><span id="pay2">¥3338.00</span>
						<span style="float:right;">免运费</span></li>
					</ul>
					
				</div>
					<div id="right_footer">
					<div id="xxbtn">购物助手
					<img src="./images/btn.jpg"></div>
					</div>
			</div>
	</div>
</div>
	<div id="show_all">
	   <div id="panelall">
	     <!--第一个-->
		 <ul id="panel1"style="display:block;">
		 <div id="xtop">商品名称</div>
			<div id="xtop_1">
			<div id="top_1left"><span id="sub1">点击</span></div>
			<div id="top_1right"><span id="sub2">观看</span></div>
			</div>
		</ul>
		    <!--第二个-->
			<ul id="panel2">
			<div id="xtop">商品名称</div>
					<div id="xleft">
						<textarea id="tex1"></textarea><!--显示内容-->
						<div id="bg"></div>
						<textarea type="text" id="tex2" value="" ></textarea><!--发送内容-->
					</div>
				<div id="xright">
					<div id="xfind">
					<input type="text"style="width:70px;
					height:16px;background:#f4f4f4;border:none;"/>      			<span><img src="./images/xfind.jpg"style="float:right;width:15px;
					   margin-right:5px;margin-top:1px;"></span>
					</div>
					<div id="xx">
					<div id="nav_left">
						<button id="obtn1">左边</button>
						<div id="menu_left">
								<li>1</li>
								<li>2</li>
								<li>3</li>
								<li>4</li>
								<li>5</li>
						</div>
					</div>
					<div id="nav_right">
						<button id="obtn2">右边</button>
						<div  id="menu_right">
								<li>a</li>
								<li>b</li>
								<li>c</li>
								<li>d</li>
								<li>e</li>
						</div>
					</div>
					</div>
					<div id="xface">
						<select id="face1">
							<option>常用语</option>
							<option>常用啊三个</option>
							<option>常用生日歌语</option>
							<option>常用顺德容桂</option>
							<option>常用芙蓉夫人</option>
							<option>常用发日发语</option>
						</select>
						<span>
						<input type="button" value="清除" onclick="cleartext()" />
			
					    <input type="button" value="发送"  onclick="sendinfor()" />
						</span>
					</div>
				</div>	
		</ul>
		 <!--第三个-->
		<ul id="panel3">
			<div id="xtop_3">
			</div>
		</ul>
		 <!--第四个-->
		  <ul id="panel4">
			<div id="xtop_4">
			</div>
		  </ul>
		</div>
			 <div id="show_bottum">
			 <ul>
			   <li>1</li>
			   <li><img  src="./images/li1.jpg" onclick="cycleinfor()" width="100px" height="80px" /></li>
			    <li>3</li>
			   <li>4</li>
			  </ul>
			 </div>
			<div id="close"><img src="./images/close.jpg"></div>
	</div>
	
</body>
</html>