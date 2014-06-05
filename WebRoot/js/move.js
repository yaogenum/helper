function move(){
	var Oshow_all=document.getElementById('show_all');
	disX=0;
	disY=0;
	Oshow_all.onmousedown=function(ev){
		  var oEvent=ev||event;
		  disX=oEvent.clientX-Oshow_all.offsetLeft;
		  disY=oEvent.clientY-Oshow_all.offsetTop;
		  
		  document.onmousemove=function(ev){
		  var oEvent=ev||event;
		  Oshow_all.style.left=oEvent.clientX-disX+'PX';
		  Oshow_all.style.top=oEvent.clientY-disY+'PX';
		  };
		  document.onmouseup=function(){
				document.onmousemove=null;
				document.onmouseup=null;
		  };
		  };
		  return false;
};

