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
    
    <title>购物助手主界面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="苏宁,购物助手">
	<meta http-equiv="description" content="购物助手主页面">
<link rel="stylesheet" type="text/css" href="css/suning.css"/>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<link rel="stylesheet" type="text/css" href="css/style3.css"/>
<link rel="stylesheet" type="text/css" href="css/style4.css"/>
<link rel="stylesheet" type="text/css" href="css/login1.css"/>
<link rel="stylesheet" type="text/css" href="css1/animation.css"/>
<link rel="stylesheet" type="text/css" href="css/maxpic.css">

<script type="text/javascript" src="js/suning.js" ></script>
<script type="text/javascript" src="js/move.js" ></script>
<script type="text/javascript" src="js/change.js" ></script>
<script type="text/javascript" src="js/close.js" ></script>
<script type="text/javascript" src="js/play.js" ></script>
<script type="text/javascript" src="js/starMove.js" ></script>
<script type="text/javascript" src="js/nav_move.js" ></script>
<script type="text/javascript" src="js/show_Ifm.js" ></script>
<script type="text/javascript" src="js/show.js" ></script>
<script type="text/javascript" src="js/show_add.js" ></script>
<script type="text/javascript" src="js1/act.js" ></script>
<script type="text/javascript" src="js/photo_move.js" ></script>
<script type="text/javascript" src="js/move_plan.js" ></script>
<script type="text/javascript" src="js/photo_magn.js" ></script>
<script type="text/javascript" src="js/message.js" ></script>
<script type="text/javascript" src="js/max.js" ></script>

<!-- 
扩展js
-->
<script type="text/javascript" src="jquery/jquery.js"></script>
<script type="text/javascript" src="js/ajaxsender.js"></script>

<script type="text/javascript" src="js/charts/FusionCharts.js">
</script>
<script type="text/javascript" src="js/charts/FusionCharts.jqueryplugin.js">
</script>

<script>
function allload(types,themes,senders,goodsid,ac_id,ac_amount,ac_cheapcode,ac_name,useraddress){
    show();
	suning();
	move();
	change();
	close();
	starMove();
	nav_move();
	show_Ifm();
	show_add();
	play();
	//act();
	photo_magn();
	photo_play();
	
	initinfor(types,themes,senders,goodsid,ac_id,ac_amount,ac_cheapcode,ac_name,useraddress); 

};
</script>
<body onload="allload('${goodsinfor.goodsinfor.goodstype}','${goodsinfor.goodsinfor.goodsname}','${username}','${goodsinfor.goodsinfor.goodsid}','${goodsinfor.db_ac.ac_id}','${goodsinfor.db_ac.ac_amount}','${goodsinfor.db_ac.ac_cheapcode}','${goodsinfor.db_ac.ac_name}','${useraddress}')">
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
           <li><a href="#">回易购首页</a></li>
			<li><a href="#">我的订单</a></li>
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
						<h5>全部优质商家 &nbsp;<a href="#">（1）家
						</a><img src="./images/right.jpg"></h5>
						<li>苏宁自营<br/><span id="pay2">¥3338.00</span>
						<span style="float:right;">免运费</span></li>
					</ul>
					
				</div>
					<div id="right_footer">
					<div id="xxbtn" onclick="startMove()">购物助手
					<img src="./images/btn.jpg"></div>
					</div>
			</div>
	</div>
</div>
	<div id="show_all">
	   <div id="panelall">
	   
	   
	   
	   
	     <!--第一个-->
		 <ul id="panel1"class="panel1"style="display:block;">
		 <div id="xtop">商品视频</div>
			<div id="xtop_1">
			<div id="top_1left"></div>
			<div id="top_1right"></div>
			<!--播放动画框架
			 <iframe src="animation.html" width="428px" 
				height="315" scrolling="no"frameborder="0"frameborder="none"
				style="display:none;margin-top:15px;" id="iframe"> -->
			<video width="428" height="315" controls="controls" >
				<source src="video/aaas.mp4"  />
			</video>
				
			
			</iframe>
			<div id="hidden_form">
			<form id="Form">
				<input id="input"value="商家名称:"/><br/>
				<input value="获取"class="button">
				<input id="business_btn"class="button"   value="商家登录">
			</form>
		</div>
			<div id="play"><img src="./images/play.fw.png"/></div>
			</div>
		</ul>
		
		
		
		
		
		    <!--第二个-->
			<ul id="panel2">
			<div id="xtop">
			<p id="talk">聊天</p>
			</div>
				<div>
						<div id="xleft">
							<!-- tex1，显示消息处-->
								<textarea id="tex1" > </textarea>
						</div>
				<div id="xright">
					<div id="xfind">
					<!-- 输入搜索内容-->
						<input type="text" id="getpointsinfor"
						onchange="getpoints()" />      				
						<span id="show_text"><img src="./images/xfind.jpg"
						style="float:right;width:15px;
						   margin-right:5px;margin-top:1px;"  onclick="searcherinfor()" ></span>
					</div>
						<!-- 索引提示-->
					<div id="nav_left">
						<p id="points1"></p>
						
					</div>
				</div>
				<div id="sn_bg"></div>
				</div>
										<div id="bg">
						<button id="show_Btn" onclick="gethistory()" >消息记录</button>
						
						<select id="face1" onblur="quicklyinfor()">
							<option>常用语</option>
							<option>你好</option>
							<option>再见</option>
							<option>拜拜</option>
							<option>hello</option>
							<option>谢谢</option>
						</select>
						</div>
						<textarea type="text"id="tex2" value=""></textarea> 
							<div id="btn">
						<button id="btn3" onclick="sendinfor()">发送</button>
						</div>
		</ul>
		
		
		
		
		
		
		 <!--第三个-->
		<ul id="panel3"class="act">
			<div id="xtop">团购</div>
			<div id="coord_all" >
				<div id="coord" ></div>
				<div id="add1" class="add" onclick="quikeaddr(0)">1</div>
				<div id="add2" class="add" onclick="quikeaddr(1)">2</div>
				<div id="add3"  class="add" onclick="quikeaddr(2)">3</div>
				<div id="add4"  class="add" onclick="quikeaddr(3)">4</div>
				<div id="add5"  class="add" onclick="quikeaddr(4)" >5</div>
				<div id="add6"  class="add" onclick="quikeaddr(5)">6</div>
				<div id="add7"  class="add" onclick="quikeaddr(6)">7</div>
			</div>
			<div id="join"><a>点击上边的同色图块可快速匹配加入订单</a></div>
			<div id="width">
				<li id="count"> 商品数量</li>
				<li class="move" >
					<div id="move_plan" onmousemove="alertamount()"></div>
				</li>
				<div id="add"><img src = "./images/add1.png" id="img_move"></div>
            </div>
		</ul>
		
		
		
		
		
		
		 <!--第四个-->
		  <ul id="panel4">
				<div id="xtop">商品信息图形展示</div>
				<div id="panel4_top"></div>
				<div id="panel4_right">
				
						<button onclick="getsales()">销量如何?</button>
						<button onclick="getchangeprice()" >价格变了?</button>
						<button onclick="getinterest()">谁也爱买?</button>
					
				</div>
		  </ul>
		</div>
			 <div id="show_bottum">
			 <ul id="ul_li">
			   <li style="z-index:9999;"class="li_1"><img src="./images/li1.png"  /></li>
			   <li style="z-index:9999;"class="li_2"><img src="./images/li2.png" onclick="cycleinfor()"></li>
			   <li style="z-index:9999;"class="li_3"><img src="./images/li4.png"   onclick="getthree()"></li>
			   <li style="z-index:9999;"class="li_4"><img src="./images/li5.png"  onclick="getfourmodel()"></li>
			   <li class="bg"></li>
			  </ul>
			 </div>
		<div id="close"><img src="./images/close.jpg"></div>
		<!--修改了的-->
			<!--第三个模块小框 -->
		<div id="show_add">
			<div id="xtop" style="width:300px;"><p id="timus"></div>
			<div id="div_form">
				<input type="text" value="地址" id="setaddress"/>
				<input type="button" value="确定" id="submitorder" onclick="add_address()"/>
			</div>
		</div>
		<!--第二个模块小框 -->
		<div id="show_ifm">
			<div id="xtop"style="width:300px;">
			<p id="timu"></p>
			<p id="contenthistory" style="overflow-x:hidden;color:black"></p>
			</div>
		</div>

		</div>
	</div>
	
	
	
	
	
	<!--商家登陆-->
	<div id="right_business">
		<button id="close_btn"><img src = "./images/close.jpg"></button>
		<div id="div1"><a href="#">新用户，免费注册></a></div>
			<ul class="ul1">
				<li><span>展示商品号码</span><input type="text"/></li>
				<li><span>商家名称</span><input type="text"/></li>
				<li><span>登录密码</span><input type="password"/></li>
			</ul>
		<div id="div2"><a href="#">忘记密码？</a></div>
			<button></button>
			<div id="tw"></div><div id="tw_a"><a href="#">天翼</a></div>
	</div>
	
</body>
</html>