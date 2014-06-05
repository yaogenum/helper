function nav_move()
{
	var oUl = document.getElementById('show_bottum');
	var aLi = oUl.getElementsByTagName('li');
	var oBg = aLi[aLi.length-1];
	for(var i = 0;i<aLi.length-1;i++)
	{
		aLi[i].onmouseover = function ()
		{
			startMove(oBg,this.offsetLeft);
		}
	}
};
	var iSpeed = 0;
	var left=0;
	function startMove(obj,iTarget) 
	{
		clearInterval(obj.timer)
		obj.timer = setInterval(function ()
		{
			iSpeed += (iTarget-obj.offsetLeft)/5;
			iSpeed *=0.7;
			left+=iSpeed;
			if(Math.abs(iSpeed)<1 && Math.abs(left-iTarget)<1)
			{
				clearInterval(obj.timer)
			}
			else
			{
			obj.style.left=left+'px';
			}
			
		},30);
	}