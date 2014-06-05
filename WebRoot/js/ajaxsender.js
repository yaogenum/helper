
//提交消息
document.onkeydown=function() { 

	if (event.keyCode==13) { 
		sendinfor();
	} 
} 

//用户登录


var type;
var theme;
var sender;
var goodsid;
var ac_name;
var ac_id;
var ac_amount;
var ac_cheapcode;
var useraddress;
function initinfor(types,themes,senders,goodsids,ac_ids,ac_amounts,ac_cheapcodes,ac_names,useraddresss){
	type=types;//类别，
	theme=themes;//商品名称
	sender=senders;
	goodsid=goodsids;
	ac_id=ac_ids;
	ac_amount=ac_amounts;
	ac_cheapcode=ac_cheapcodes;
	ac_name=ac_names;
	useraddress=useraddresss;
} 

//发送消息
function sendinfor(){
	if(sender.length<5){
		alert("请您先登录");
	}
	else{
	
	var information=document.getElementById("tex2").value;
	
	if(information.length<=0){
		alert("发送内容不能为空");
	}
	else{
		$.ajax({
			type:"POST",
			data:"type="+type+"&theme="+theme+"&sender="+sender+"&information="+information,//根据进入当前页面获取用户信息
			url:"send/sendinfors",
			dataType:"Text",
			success:function(data,status){
			
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}
		});
		document.getElementById("tex2").value="";
	} 
	
}
}


function cleartext(){
	document.getElementById("tex2").value="";
}

//请求聊天内容

var processid=0;
var amountid=0;



//不断请求事件
function cycleinfor(){
//初始化参数
	
//请求消息
	processid=setInterval(getinfor,2000);
//聊天人数
	amountid=setInterval(getamount,5000);
}

//停止请求
function stopinfor(){
	clearInterval(processid);
	clearInterval(amountid);
	
}
//发送接收


var text1="";
//请求消息
function getinfor(){

	$.ajax({
		type:"POST",
		data:"type="+type+"&theme="+theme,//根据进入当前页面获取用户信息
		url:"send/getinfors",
		dataType:"Text",
		success:function(data,status){
			text1=data;
			if(data.length<=7){
				
			}
			else{
				
				document.getElementById("tex1").innerHTML=text1;
				
			}
		
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			/* alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus); */
		}
	});
	
}
//聊天人数
function getamount(){
	$.ajax({
		type:"POST",
		data:"theme="+theme,
		url:"send/getinfors!getuseramount",
		dataType:"text",
		success:function(data,status){	
			 document.getElementById("talk").innerHTML="当前站内本商品参与聊天人数为:"+data; 
		},
		error:function(XMLHttpRequest,textStatus,errorThrow){
			/* alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus); */
		}
	});
}



// gethistory();
//展现1000条内容，消息记录
function gethistory(){
	
	document.getElementById("timu").value="历史记录";
	$.ajax({
		type:"POST",
		data:"type="+type+"&theme="+theme,
		url:"send/getinfors!gethitory",
		dataType:"json",
		success:function(data,status){
			if(data.length<=7){
				//无内容
				document.getElementById("contenthistory").innerHTML="历史:短时间内尚未收入或者未有用户聊天记录";
			}
			else{
				
				var res=eval(data);//转换
				 // alert(data);//第一条 
				document.getElementById("contenthistory").innerHTML=data;	
			}
		},
		error:function(XMLHttpRequest,textStatus,errorThrow){
			/* alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus); */
		}
	});
}

//快捷发送消息
function quicklyinfor(){
	var quickly=document.getElementById("face1").value;
	document.getElementById("tex2").innerHTML=document.getElementById("tex2").innerHTML+quickly;
	
}

//以上没有问题，开始群聊第二个模块

//findinfors,查找消息
/* searcherinfor(); */

function searcherinfor(){
	
	var informations=document.getElementById("getpointsinfor").value;
	document.getElementById("timu").innerHTML="查找记录";
	$.ajax({
		type:"POST",
		data:"type="+type+"&theme="+theme+"&sender="+sender+"&information="+informations,
		url:"send/findinfors",
		dataType:"json",
		success:function(data,status){
		//返回数据
			if(data==null||data.length<=5){
				document.getElementById("contenthistory").innerHTML="查询:短时间内尚未收入或者未有用户聊天记录";
				
			}
			else{
				document.getElementById("contenthistory").innerHTML=data;//展示区域
				alert(data);
			}
		
		},
		error:function(XMLHttpRequest,textStatus,errorThrow){
		/* 	alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus); */
		}
	});
}

/* getpoints();//获取索引提示 */


function getpoints(){
	var sou=document.getElementById("getpointsinfor").value;
	$.ajax({
		type:"POST",
		data:"type="+type+"&theme="+theme+"&sou="+sou,
		url:"send/pointinfor",
		dataType:"json",
		success:function(data,status){
			if(data.length>3){
				document.getElementById("points1").innerHTML=data;
			}
			else{
				document.getElementById("points1").innerHTML="形式可能不对";
			}
			
		},
		error:function(XMLHttpRequest,textStatus,errorThrow){
			/* alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus); */
		}
	});
}



//以上均为聊天部分，一下为图像展示



//获取商品数量最多的js模块三
function getthree(){
	alert(ac_name+"收货地址达到"+ac_amount+"或者总数目达到"+ac_amount*7+"即可享受优惠活动"+ac_cheapcode);
	//doucment.getElementById("xtop3").innerHTML=
	document.getElementById("timus").innerHTML="添加您的地址信息";
	document.getElementById("setaddress").value=useraddress;
	get_amount();
	get_addr();
	
	
}


var alertshuliang;

function alertamount(){
	
	if(ac_amount>alertshuliang){
		alert("商品总是数量达到"+alertshuliang+";还差"+(ac_amount*7-alertshuliang)+"即可满足数目团购要求");
	}
	else{
		alert("商品总是数量达到"+alertshuliang);
	}
	
	
}

function get_amount(){

	$.ajax({
		type:"POST",
		data:"goodsname="+theme,
		url:"goods/display!getamount",
		dataType:"text",
		success:function(data,status){
			move_plan(data);
			alertshuliang=data;
		},
		error:function(XMLHttpRequest,textStatus,errorThrow){
			/* alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus); */
		}
	});
}
//获取地址7最多柱形图

var widths="420";
var heights="200";

var addresses=new Array();


function get_addr(){
	
	$.ajax({
		type:"POST",
		data:"goodsname="+theme,
		url:"goods/getaddrs",
		dataType:"text",
		success:function(data,status){
			var datas=eval(data);
			
			for(var i=0;i<7;i++){
				addresses[i]= datas[i].address;
			}
			
		//修改数字内容	
		/* for(var i=0;i<7;i++){
			$(document).ready(function(){
				$(".coord_all").children().value="123";
			});
		} */	
			
			//alert(datas[0].address);//显示图
			$("#coord").insertFusionCharts( {//main
						swfUrl : "js/charts/Column3D.swf",//MSColumn3D.swf
						width : widths,
						height : heights,
						id : "id_chart9",
						dataFormat : "json",
						dataSource : {
							"chart": {
							//"yaxisname": "Sales Figure",
							"caption": "前7名地址显示图",
							"numberprefix": "数目:",
							"showborder": "1",
							"imagesave": "1",
							//"exporthandler": "http://export.api3.fusioncharts.com"
							},
							"data": [
							
								{
								"label": datas[0].address,
								"value": datas[0].amount
								},
							
							
								{
								"label": datas[1].address,
								"value": datas[1].amount
								},
								{
								"label": datas[2].address,
								"value": datas[2].amount
								},
								{
								"label": datas[3].address,
								"value": datas[3].amount
								},
								{
								"label": datas[4].address,
								"value": datas[4].amount
								},
								{
								"label": datas[5].address,
								"value": datas[5].amount
								},
								{
								"label": datas[6].address,
								"value": datas[6].amount
								}
							]
							}	
					});
			
		},
		error:function(XMLHttpRequest,textStatus,errorThrow){
			/* alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus); */
		}
	});
}

/* add_address(1,1,"yaoge22","重庆邮电大学"); */
function add_address(){
var finaladdress=document.getElementById("setaddress").value;
if(sender.length<5||finaladdress.length<=2){
		alert("请您先登录,或者地址错误");
	}
else{
	
	alert("若亲是真心参加活动！请注意当满足促销条件时，系统将自动生成有效订单！再点击确定;若操作失误请直接关闭此页面");
	
	//alert(goodsid+":"+sender+":"+finaladdress+":"+ac_id);
	$.ajax({
		type:"POST",
		data:"goodsid="+goodsid+"&username="+sender+"&address="+finaladdress+"&Ac_id="+ac_id,
		url:"goods/addaddrs!addaddress",
		dataType:"text",
		success:function(data,status){
			alert(data);
			
		},
		error:function(XMLHttpRequest,textStatus,errorThrow){
			/* alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus); */
		}
	});
	
}
}


//第四个模块ok
function getfourmodel(){
	
	getsales();
	//getchangeprice();
	//getinterest();
}
//前七次折线图销量
function getsales(){
	
	$.ajax({
		type:"POST",
		data:"goodsname="+theme+"&amount="+7,
		url:"goods/analyzergoods!execute",
		dataType:"json",
		success:function(data,status){//先是时间
			//展示图像
						$("#panel4_top").insertFusionCharts( {//main
						swfUrl : "js/charts/Line.swf",//MSColumn3D.swf
						width : "380",
						height : "320",
						id : "id_chart1",
						dataFormat : "json",
						dataSource : {
							
							
    "chart": {
        "caption": "商品销量",
        "subcaption": "折线图",
        "xaxisname": "时间",
        "yaxisname": "销量/个",
        "numberprefix": "",
        "showlabels": "1",
        "showcolumnshadow": "1",
        "animation": "1",
        "showalternatehgridcolor": "1",
        "alternatehgridcolor": "ff5904",
        "divlinecolor": "ff5904",
        "divlinealpha": "20",
        "alternatehgridalpha": "5",
        "canvasbordercolor": "666666",
        "basefontcolor": "666666",
        "linecolor": "FF5904",
        "linealpha": "85",
        "showvalues": "1",
        "rotatevalues": "1",
        "valueposition": "auto",
        "canvaspadding": "8"
    },
    "data": [
        {
            "label": data[0],
            "value": data[1]
        },
        {
            "label": data[2],
            "value": data[3]
        },
        {
            "label": data[4],
            "value": data[5]
        },
        {
            "label":  data[6],
            "value": data[7]
        },
       {
            "label":  data[8],
            "value": data[9]
        },
         {
            "label":  data[10],
            "value": data[11]
        },
        {
            "label":  data[12],
            "value": data[13]
        }
       
    ]

							
						}
					});
			
			
		},
		error:function(XMLHttpRequest,textStatus,errorThrow){
		/* 	alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus); */
		}
	});
}


function getchangeprice(){
	$.ajax({
		type:"POST",
		data:"goodsname="+theme+"&amount="+7,
		url:"goods/analyzergoods!getprice",
		dataType:"json",
		success:function(data,status){
			
			
			
						//展示图像
						$("#panel4_top").insertFusionCharts( {//main
						swfUrl : "js/charts/Line.swf",//MSColumn3D.swf
						width : "380",
						height : "320",
						id : "id_chart2",
						dataFormat : "json",
						dataSource : {
							
							
    "chart": {
        "caption": "商品价格变化",
        "subcaption": "折线图",
        "xaxisname": "时间",
        "yaxisname": "价格/元",
        "numberprefix": "",
        "showlabels": "1",
        "showcolumnshadow": "1",
        "animation": "1",
        "showalternatehgridcolor": "1",
        "alternatehgridcolor": "ff5904",
        "divlinecolor": "ff5904",
        "divlinealpha": "20",
        "alternatehgridalpha": "5",
        "canvasbordercolor": "666666",
        "basefontcolor": "666666",
        "linecolor": "FF5904",
        "linealpha": "85",
        "showvalues": "1",
        "rotatevalues": "1",
        "valueposition": "auto",
        "canvaspadding": "8"
    },
    "data": [
        {
            "label": data[1],
            "value": data[0]
        },
        {
            "label": data[3],
            "value": data[2]
        },
        {
            "label": data[5],
            "value": data[4]
        },
        {
            "label":  data[7],
            "value": data[6]
        },
       {
            "label":  data[9],
            "value": data[8]
        },
         {
            "label":  data[11],
            "value": data[10]
        },
        {
            "label":  data[13],
            "value": data[12]
        }
       
    ]

							
						}
					});
			
			
		},
		error:function(XMLHttpRequest,textStatus,errorThrow){
			/* alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus); */
		}
	});
}


function getinterest(){
	$.ajax({
		type:"POST",
		data:"goodsname="+theme+"&amount="+7,
		url:"goods/analyzergoods!interest",
		dataType:"json",
		success:function(datapie,status){//第一个为类别
			//alert(datapie[0]);
		
				//展示图像
						$("#panel4_top").insertFusionCharts( {//main
						swfUrl : "js/charts/Pie3D.swf",//MSColumn3D.swf
						width : "380",
						height : "320",
						id : "id_chart3",
						dataFormat : "json",
						dataSource : 
							{
    "chart": {
        "caption": "购买此商品的用户爱好",
        "palette": "2",
        "animation": "1",
        "formatnumberscale": "0",
        "numberprefix": "人数:",
        "pieslicedepth": "30",
        "startingangle": "125"
    },
    "data": [
        {
            "label": datapie[0],
            "value": datapie[1],
            "issliced": "1"
        },
        {
            "label": datapie[2],
            "value": datapie[3],
            "issliced": "1"
        },
        {
            "label": datapie[4],
            "value": datapie[5],
            "issliced": "0"
        },
		 {
            "label": datapie[6],
            "value": datapie[7],
            "issliced": "0"
        },
		 {
            "label": datapie[8],
            "value": datapie[9],
            "issliced": "0"
        },
		 {
            "label": datapie[10],
            "value": datapie[11],
            "issliced": "0"
        },
		 {
            "label": datapie[12],
            "value": datapie[13],
            "issliced": "0"
        }
    ],
    "styles": {
        "definition": [
            {
                "type": "font",
                "name": "CaptionFont",
                "size": "15",
                "color": "666666"
            },
            {
                "type": "font",
                "name": "SubCaptionFont",
                "bold": "0"
            }
        ],
        "application": [
            {
                "toobject": "caption",
                "styles": "CaptionFont"
            },
            {
                "toobject": "SubCaption",
                "styles": "SubCaptionFont"
            }
        ]
    }
}
					});
		
			
		},
		error:function(XMLHttpRequest,textStatus,errorThrow){
			/* alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus); */
		}
	});
}





//前端
function move_plan(value)
{	
		var Omove_plan = document.getElementById('move_plan');
			starmove(Omove_plan,'width',value);
			
			
			
		function getStyle(obj,name)
		{
			if(obj.currentStyle)
			{
				return obj.currentStyle[name];
			}
			 else
			 {
				return getComputedStyle(obj,false)[name];
			 }
		}
		function starmove(obj,attr,itarget,fnEnd)
		{
		   clearInterval(obj.timer);
		   obj.timer=setInterval(function()
		   {
			   var cur=0;
			   if(attr=='opacity')
			   {
					cur=parseFloat(getStyle(obj,attr));
			   }
			   else
				{
					cur=parseInt(getStyle(obj,attr));
				}
					var speed=(itarget-cur)/5;
					speed=speed>0?Math.ceil(speed):Math.floor(speed);
					if(cur==itarget)
				{
					clearInterval(obj.timer);
					if(fnEnd)fnEnd();
				}
				else
				{
					if(attr=='opacity')
					{
					   obj.style.filter='alpha(opacity:'+(cur+speed)+')';
					   obj.style.opacity=(cur+speed)/100;
					}
					else
					{
						 obj.style[attr]=cur+speed+'px';
					}
				}
			},30);
		}
};


function quikeaddr(idname){
	
	
	if(sender.length<5){
		alert("请您先登录");
	}
	else{
	
	alert("若亲是真心参加活动！请注意当满足促销条件时，系统将自动生成有效订单！再点击确定;若操作失误请直接关闭此页面");
	
	//var finaladdress=document.getElementById("setaddress").value;
	//alert(goodsid+":"+sender+":"+finaladdress+":"+ac_id);
	$.ajax({
		type:"POST",
		data:"goodsid="+goodsid+"&username="+sender+"&address="+addresses[idname]+"&Ac_id="+ac_id,
		url:"goods/addaddrs!addaddress",
		dataType:"text",
		success:function(data,status){
			alert(data);
			
		},
		error:function(XMLHttpRequest,textStatus,errorThrow){
			/* alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus); */
		}
	});
	
}
	
}

