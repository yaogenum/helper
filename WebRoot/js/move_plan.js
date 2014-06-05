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
function add_move()
{
	var Oadd_move = document.getElementById('add');
	var Oimg_move = document.getElementById('img_move');
	Oadd_move.onclick = function()
	{
		Oadd_img.style.top = 100+'px';
	}		
}