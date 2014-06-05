
function close(){
	var Oclose=document.getElementById('close');
	var Oclose_btn=document.getElementById('close_btn');
	var Oshow_all=document.getElementById('show_all');
	var Odiv1=document.getElementById('show_ifm');
	var Oright=document.getElementById('right_business');
	var Oright1=Oright.getElementsByTagName('input');
	var Odiv2=document.getElementById('show_add');
	Oclose.onclick=function(){
		Oshow_all.style.display='none';
		Oshow_all.style.top=160+'px';
		Oshow_all.style.left=520+'px';
		starmove(Oshow_all,'width',480);
		oDiv1.style.display='none';
		oDiv2.style.display='none';
		
	};
	Oclose_btn.onclick = function()
	{
		
		for(var i = 0;i < 3;i++)
		{
			Oright1.value = '';
		}
		Oright.style.display = 'none';
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
};