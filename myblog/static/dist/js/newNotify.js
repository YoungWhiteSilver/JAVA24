$(function() {
	
	//新消息通知
	var newNotify = function() {
		
		$.ajax({
			
			url : "/admin/notifyServlet",
			type : "post",
			success : function(json) {
				
				if(json.data > 0) {
					 $("#unRead").text(json.data);
				}
				
			},
			error : function() {
				
			}
			
		})
		
	};
	
	setInterval(newNotify, 3000);
	
	newNotify();
	
	
});