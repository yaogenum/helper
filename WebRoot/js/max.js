var maxid=1;
function max()
{		var Ocoord=document.getElementById('coord');
	
		maxid++;
		alert(maxid);
		if(maxid%2!=0){
			widths="680";
			heights="450";
			Ocoord.style.left = -200+'px';
			Ocoord.style.top = 160+'px';
		}
		else{
			
			widths="420";
			heights="200";
		}
		
	
	
		
		
		get_addr();
		
};