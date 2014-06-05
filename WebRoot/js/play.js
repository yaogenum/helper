function play(){	
	var Osub1=document.getElementById('play');
	var Oifrmae=document.getElementById('iframe');
	var Oleft=document.getElementById('top_1left');
	var Oright=document.getElementById('top_1right');
		Osub1.onclick=function(){
		timer = setInterval(function(){
		Osub1.style.display='none';
		if(Oright.offsetWidth<3)
			{
				clearInterval(timer);
			}
			Oright.style.width=Oright.offsetWidth-2+'px';
			Oleft.style.width=Oleft.offsetWidth-2+'px';
			Oifrmae.style.display = 'block';
		},8);
	};
};
function show(){
	var Obtn=document.getElementById('xxbtn');
	var Oall=document.getElementById('all');
	var Oshow_all=document.getElementById('show_all');
	Obtn.onclick=function(){
		Oshow_all.style.display='block';
	};
};
