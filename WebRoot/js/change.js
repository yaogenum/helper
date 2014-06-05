function change(){
	var Odiv=document.getElementById('show_bottum');
	var Opanelall=document.getElementById('panelall');
	var Oshow_all=document.getElementById('show_all');
	var oDiv1=document.getElementById('show_ifm');
	var oDiv2=document.getElementById('show_add');
	var Adiv=Opanelall.getElementsByTagName('ul');
	var Obtn=Odiv.getElementsByTagName('li');
	for(var i=0;i<4;i++)
		{
			Obtn[i].index=i;
			Obtn[i].onclick=function(){
				for(var i=0;i<4;i++)
					{
						Adiv[i].style.display='none';
						Obtn[i].className='';
						if(Oshow_all.offsetWidth==784)
						{
							starmove(Oshow_all,'width',480);
							oDiv1.style.display='none';
							oDiv2.style.display='none';
						}
					}
				this.className='active';
				Adiv[this.index].style.display='block';
				
				
				
				
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
							var speed=(itarget-cur);
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
		}
};