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
	<meta http-equiv="keywords" content="苏宁,比赛,电商">
	<meta http-equiv="description" content="主页测试页面">
<link rel="stylesheet" type="text/css" href="css/suning.css"/>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<link rel="stylesheet" type="text/css" href="css/style3.css"/>
<link rel="stylesheet" type="text/css" href="css/style4.css"/>
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
<!--<script type="text/javascript" src="js/move_plan.js" ></script>-->

<script type="text/javascript" src="jquery/jquery.js"></script>
<script type="text/javascript" src="js/ajaxsender.js"></script>

<script type="text/javascript" src="js/charts/FusionCharts.js">
</script>
<script type="text/javascript" src="js/charts/FusionCharts.jqueryplugin.js">
</script>

<script>
function allload(types,themes,senders,goodsid,ac_id,ac_amount,ac_cheapcode,ac_name){
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
	
	initinfor(types,themes,senders,goodsid,ac_id,ac_amount,ac_cheapcode,ac_name); 
};
</script>

</head>
<body onload="allload('${goodsinfor.goodsinfor.goodstype}','${goodsinfor.goodsinfor.goodsname}','${username}','${goodsinfor.goodsinfor.goodsid}','${goodsinfor.db_ac.ac_id}','${goodsinfor.db_ac.ac_amount}','${goodsinfor.db_ac.ac_cheapcode}','${goodsinfor.db_ac.ac_name}')">
<div id="all" >
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
						<h5>全部优质商家 &nbsp<a href="#">（1）家
						</a><img src="./images/right.jpg"></h5>
						<li>苏宁自营<br/><span id="pay2">¥3338.00</span>
						<span style="float:right;">免运费</span></li>
					</ul>
					
				</div>
					<div id="right_footer">
					<div id="xxbtn" onclick="startMove()" >购物助手
					<img src="./images/btn.jpg" ></div>
					</div>
			</div>
	</div>
</div>
	<div id="show_all">
	   <div id="panelall">
	     <!--第一个-->
		 <ul id="panel1" class="panel1" style="display:block;">
		 <div id="xtop">${goodsinfor.goodsinfor.goodsname}</div>
			<div id="xtop_1">
			<div id="top_1left"></div>
			<div id="top_1right"></div>
			<div id="play">
			<img src="./images/play.fw.png"/> 
			  <!--<video width="320" height="240" controls="controls" autoplay="autoplay">
				 <source src="/i/movie.ogg" type="video/ogg" />
				<source src="/i/movie.mp4" type="video/mp4" />
				<source src="/i/movie.webm" type="video/webm" />
				 <object data="/i/movie.mp4" width="320" height="240">
			 <embed width="320" height="240" src="/i/movie.swf" />
				 </object>
			 </video>-->
			 
			</div>
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
						<input type="text" style="width:115px;
						height:16px;background:#f4f4f4;border:none;" 
						id="getpointsinfor"
						onchange="getpoints()"
						/>      				
						<span id="show_text"><img src="./images/xfind.jpg"
						style="float:right;width:15px;
						   margin-right:5px;margin-top:1px;" onclick="searcherinfor()" ></span>
					</div>
					<!-- 索引提示-->
					
					<div id="nav_left">
						<p id="points1"></p>
					</div>
				</div>	
				</div>
						<div id="bg">
						<button id="show_Btn" onblur="gethistory()" >消息记录</button>
						
						<select id="face1" onblur="quicklyinfor()">
							<option>常用语</option>
							<option>你好</option>
							<option>再见</option>
							<option>拜拜</option>
							<option>hello</option>
							<option>谢谢</option>
						</select>
						</div>
						<!-- tex2发送消息-->
						<textarea type="text" id="tex2" value=""></textarea> 
							<div id="btn">
					    <input type="button"value="发送"
						onclick="sendinfor()" >
						<br/>
						<!--<input type="button"value="关闭">-->
						</div>
		</ul>
		
		
		
	<!---图像展示界面 -->	
		
		
		
		
		
		
		 <!--第三个-->
		<ul id="panel3"class="act">
			<div id="xtop3"></div>
			<div id="coord_all">
				<div id="coord" ></div>
				<div class="add">1</div>
				<div class="add">2</div>
				<div class="add">3</div>
				<div class="add">4</div>
				<div class="add">5</div>
				<div class="add">6</div>
				<div class="add">7</div>
			</div>
			<div id="join"><a href="#">温馨提示：我要加入请点击上边的地名</a></div>
			<div id="width">
				<li id="count"> 商品数量</li>
				<li class="move">
					<div id="move_plan"></div>
				</li>
				<div id="add">+1</div>
            </div>
		</ul>
		 <!--第四个-->
		  <ul id="panel4">
				<div id="xtop4">第四个</div>
				<div id="panel4_top"></div>
				<div id="panel4_left"></div>
				<div id="panel4_right"></div>
		  </ul>
		</div>
			 <div id="show_bottum">
			 <ul id="ul_li">
			   <li style="z-index:9999;">1</li>
			   <li style="z-index:9999;"><img  src=""
			   onclick="cycleinfor()"
			   width="90px" height="40px" /></li>
			   
			   <li style="z-index:9999;">
					<img  src=""
			  onclick="getthree()"
			   width="90px" height="40px" />
			   </li>
			   <li style="z-index:9999;">
			   <img  src=""
			  onclick="getfourmodel()"
			   width="90px" height="40px" />
			   </li>
			   <li class="bg"></li>
			  </ul>
			 </div>
		<div id="close"><img src="./images/close.jpg"></div>
		<div id="show_ifm">
			<div id="ifm_top">
				消息记录
			</div>
			<p id="contenthistory">
			
			</p>
		</div>
		
		<div id="show_add">
		</div>
	</div>
</body>
</html>