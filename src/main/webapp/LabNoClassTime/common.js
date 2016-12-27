/**鼠标划上去的时候显示无课列表**/
/*$(document).ready(function(){  
	
	for(var i=0; i<5; i++){
		//创建tr标签
		var tr = document.createElement("tr");
		var element = document.getElementById("tbody");
		element.appendChild(tr);
		for(var j=0; j<6; j++){
			var td = document.createElement("td");
			var elementtr = document.getElementById("tr");
			elementtr.appendChild(td);
		}
		
				
	}

});*/
$(function(){
    $(".unSelected").live('hover', function(event){
       if(event.type=='mouseenter') {
            $(this).html('<strong>添加无课列表</strong>'); 
        } else {
            $(this).html('');
       }
    });
});
$(function(){
   $(".selected").live('hover', function(event){
        if(event.type=='mouseenter') {
        	$(this).html('<strong>取消无课列表</strong>'); 
        } else {
            $(this).html('');
        }
   });
});
