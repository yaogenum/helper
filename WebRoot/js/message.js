function message()
{
	var O1=document.getElementById('tex2');
	var O2=document.getElementById('tex1');
	var O3=document.getElementById('btn3');
	O1.onkeydown=function(ev)
	{
		var oEvent=ev||event;
		if(oEvent.keyCode==13)
		{    
			 O2.value=O2.value+O1.value;
			 O1.value=null;
		}
	};
};