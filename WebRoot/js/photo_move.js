function getStyle(obj,attr)
{
    if(obj.iCurrentStyle)
	{
		return obj.iCurrentStyle[attr];
	}
	 else
	 {
		return getComputedStyle(obj,false)[attr];
	 }
}

//主要函数
function starmove(obj,json,fn)
{
   clearInterval(obj.timer);
   obj.timer=setInterval(function()
   {
		var bStop=true;//这一次运动结束，所有的值都到达了
		for(var attr in json)
		{
			//获取当前值
		   var iCur=0;
		   if(attr=='opacity')
		   {
				iCur=parseFloat(getStyle(obj,attr));
		   }
		   else
			{
				iCur=parseInt(getStyle(obj,attr));
			}
				//算速度
				var iSpeed=(json[attr]-iCur)/6;
				iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);
				//检测停止
				if(iCur!=json[attr])
				{
					bStop=false;
				}
				if(attr=='opacity')
				{
				   obj.style.filter='alpha(opacity:'+(iCur+iSpeed)+')';
				   obj.style.opacity=(iCur+iSpeed)/1000;
				}
				else
				{
					 obj.style[attr]=iCur+iSpeed+'px';
				}
			}
			
	if(bStop)
		{
			clearInterval(obj.timer);
			if(fnEnd!=null)
			{
				fnEnd();
			} 
		}
	},30);
}