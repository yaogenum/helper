function starMove()
{	
		var oFind=document.getElementById('xfind');
		var Oshow_all=document.getElementById('show_all');
		var oInput=oFind.getElementsByTagName('input')[0];
		var obtn=document.getElementById('show_text');
		var omenu_left=document.getElementById('nav_left');
		oInput.oninput=function()
		{
			omenu_left.style.display='block';
		}
		
		obtn.onclick=function()
		{
			omenu_left.style.display='none';
			starmove(Oshow_all,'width',780);
		}
		
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