function show_Ifm()
{
	var Oshow_all=document.getElementById('show_all');
	var oDiv2=document.getElementById('show_add');
	var oDiv1=document.getElementById('show_ifm');
	var oBtn=document.getElementById('show_Btn');
	oBtn.onclick=function()
	{
		gethistory();
		if(Oshow_all.offsetWidth==484)
			{
				starmove(Oshow_all,'width',780);
					oDiv1.style.display='block';
			}
			else
			{
				starmove(Oshow_all,'width',480);
				oDiv1.style.display='none';
				oDiv2.style.display='none';
			}
	};
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
					var speed=(itarget-cur)/4;
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
}