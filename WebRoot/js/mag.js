function mag()
{
	var oUl = document.getElementsByTagName('width');
	for(var i=0;i<oUl.length;i++)
	{alert('a');
			var aLi = oUl[i].getElementsByTagName('li');
			var i = 0;
			var iMinZindex = 1;
			for(var i = 0;i<aLi.length;i++)
			{
				aLi[i].style.left = aLi[i].offsetLeft+'px';
				aLi[i].style.top = aLi[i].offsetTop+'px';
			}
			
			for(var i=0;i<aLi.length;i++)
			{
				aLi[i].style.position = 'absolute';
				aLi[i].style.margin = '0';
			}
			for(var i=0;i<aLi.length;i++)
			{
				aLi[i].onmouseover = function()
				{
					this.style.zIndex = iMinZindex++;
					starmove(this,{width: 200,height: 200,marginLeft: -70,marginTop: -70});
				};
				aLi[i].onmouseout = function()
				{
					starmove(this,{width: 60,height: 60,marginLeft: 0,marginTop: 0});
				};
			}
	}
}