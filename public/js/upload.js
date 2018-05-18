function uploadFile(){
	console.log();
	var url=$("#myFile").val();
	 $.ajax({
			url:uploadUrl,
	         type: 'POST',
	         data:	{"url":url},
	         success: function(data){
	        	 console.log("data :: "+data);
	         }
	 });
}