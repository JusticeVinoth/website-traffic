var timelimit=12000;

function fetchResponse(id) {
	
	$.ajax({
		url : '/csv/status/' + id,
		type : 'GET',
		success : function(response) {
			console.log(response);
			if(response=="true"){
				window.location.href="/website"
			}else if(response=="false"){
				 setTimeout(function(){ fetchResponse(id) }, timelimit);
			}
		}
	});
}

toastr.options = {
  	  "closeButton": true,
  	  "debug": false,
  	  "newestOnTop": true,
  	  "progressBar": true,
  	  "positionClass": "toast-top-center",
  	  "preventDuplicates": false,
  	  "onclick": null,
  	  "showDuration": "300",
  	  "hideDuration": "1000",
  	  "timeOut": "5000",
  	  "extendedTimeOut": "1000",
  	  "showEasing": "swing",
  	  "hideEasing": "linear",
  	  "showMethod": "fadeIn",
  	  "hideMethod": "fadeOut"
  	}