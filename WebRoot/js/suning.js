function suning(){
	var speed=-1;
	var oleft=document.getElementById('left_1');
	var oleft_left=document.getElementById('left_left');
	var oleft_right=document.getElementById('left_right');
    var oul=oleft.getElementsByTagName('ul')[0];
    var oli=oul.getElementsByTagName('li');
    oul.innerHTML=oul.innerHTML+oul.innerHTML;
    oul.style.width=oli[0].offsetWidth*oli.length+'px';
    var timer=setInterval(move,50);
	function move(){
    if(oul.offsetLeft<-oul.offsetWidth/2){
	    oul.style.left='0';
	}
	if(oul.offsetLeft>0){
	      oul.style.left=-oul.offsetWidth/2+'px';
	}
   oul.style.left=oul.offsetLeft+speed+'px';
};
   oleft.onmouseover=function(){
      clearInterval(timer);
   };
   oleft.onmouseout=function(){
       timer=setInterval(move,50);
   }
   oleft_left.onclick=function(){
    speed=-1;
   };
   oleft_right.onclick=function(){
    speed=1;
   };
};