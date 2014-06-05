function photo_magn()
{
	var oUl = document.getElementById('ul_li');
	var aLi = oUl.getElementsByTagName('img');
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
			starmove(this,{width: 102,height: 80,marginLeft:0 ,marginTop: 0});
		};
		aLi[i].onmouseout = function()
		{
			starmove(this,{width: 64,height: 64,marginLeft: 0,marginTop: 0});
		};
	}
}

function photo_play()
{	
	var oUl1 = document.getElementById('play');
	var aLi = oUl1.getElementsByTagName('img');
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
			starmove(this,{width:110 ,height: 110,marginLeft:-5,marginTop:-5});
		};
		aLi[i].onmouseout = function()
		{
			starmove(this,{width: 96,height: 96,marginLeft: 1,marginTop: 2});
		};
	}
}