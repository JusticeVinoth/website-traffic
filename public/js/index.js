$(function(){
	$("#report_table_id").DataTable();
	$("#report_table_id_filter").hide();
	$("#report_table_id_length").hide();
	$("#report_table_id_info").hide();
	$("#report_table_id_paginate").hide();
});

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

