$(function() {
	
	 $(".time").text(function(){
     	var time = $(this).text();
     	return moment(time).format("YYYY年MM月DD日 HH:mm:ss");
     });
	
});