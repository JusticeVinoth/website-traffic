var userAlreadyExist = false;

/* $(document).ready(function(){
	$("#myModal").on("blur","#emailId", function() {
		$(".loadImg").show();
		
	});
}); */

//The following url contains path variable.So used raw url here
function getUserDetails(ids) {
	userAlreadyExist=true;
	$(".error").remove();
	$.ajax({
		  method: "GET",
		  url: "/editUser/"+ids,
		  async:false
		})
	  .done(function( data ) {
	      if(data !=undefined && data.activeUser !=undefined && data.activeUser !=null && $.isEmptyObject(data.activeUser) == false ) {
	    	if(data.activeUser.id!=$("#userId").val()){
	    		 $("#emailId").closest("div").find(".error").remove();
	    		$("#emailId").after("<label  class='error'>An user with the given email id already exist.</label>");
		    	userAlreadyExist=true;
	    	}else{
	    		 userAlreadyExist=false; 
	    		 $(".error").remove();
	    	}
	    		
	      }else{
	    	  userAlreadyExist=false; 
	    	  $(".error").remove();
	      }
  	  });
	$(".loadImg").hide();
}	

function editEmp(ids) {
	 $(".error").remove();
	userAlreadyExist = false;
	$(".loadImg").show();
	$(".userExist").html("");
	$.ajax({
		  method: "GET",
		  url: "/editUser/"+ids
		})
	  .done(function( data ) {	
		  $("#firstName").val(data.activeUser.firstName);
	  	$("#userId").val(data.activeUser.id);
	  	$("#lastName").val(data.activeUser.lastName);
	  	$("#designation").val(data.activeUser.designation);
	  	$("#role").val("Employee");
	  	$("#emailId").val(data.activeUser.emailId);
	  	$("#department").val(data.activeUser.department);
		$("#myModal").modal("show")
  	  });
	$(".loadImg").hide();
}

function saveEmployee() {
	$(".loadImg").show();
	getUserDetails($("#emailId").val());
	if(!validateUser()){
		return false;	
	}
	$(".loadImg2").show();
	$.post(saveEmpUrl, $('#empForm').serialize(), function( data ) {
		location.reload();
	});
}

function openEmpModal() {
	$("#firstName").val("");
  	$("#userId").val("");
  	$("#lastName").val("");
  	$("#designation").val("");
  	$("#role").val("Employee");
  	$("#emailId").val("");
  	$("#department").val("");
	$("#myModal").modal("show")
}


function validateUser() {
	$(".error").remove();
	 var errorArray=[];
	 var i=0;
	 
	 if($("#firstName").val()<1 ){
		 errorArray[i++]=("firstName");
		 $("#firstName").after('<label  class="error">First Name is required.</label>');
		 console.log("3");
	 } 
	 if($("#lastName").val()<1 ){
		 errorArray[i++]=("lastName");
		 $("#lastName").after('<label  class="error">Last Name is required.</label>');
		 console.log("4");
	 } 
	 if($("#emailId").val()<1 ){
		 errorArray[i++]=("emailId");
		 $("#emailId").after('<label  class="error">E-mail is required.</label>');
		 console.log("5");
	 }  else if(!validateEmail($("#emailId").val())) {
		 errorArray[i++]=("emailId");
		 $("#emailId").after('<label  class="error">Please enter valid E-mail.</label>');
		 console.log("6");
	 }else if(userAlreadyExist){
		 $("#emailId").closest("div").find(".error").remove();
		 $("#emailId").after('<label  class="error">An user with the given email id already exist.</label>');
		 errorArray[i++]=("emailId");
		 console.log("userAlreadyExist");
	 }
	 if(errorArray.length>0){
		 $("#"+errorArray[0]).focus();
		 return false; 
	 }else {
		 console.log("9");
		 return true; 
	 } 
	 return false; 
}

$(document).ready(function() {
	$('.footable').footable();
	
	var isTaskCreated = false;
	
		Dropzone.autoDiscover = false;
		dropZoneConfigure('#my-awesome-dropzone',true);
		dropZoneConfigure('#my-awesome-Replace',false);
		// to show it in an alert window
		$('#myModal').on('hidden.bs.modal', function () {
			if(empAdded) {
				resetLink();
				
			}
			 
		})
});

function attachFile(pid,fileMovingLocation) {
	var header;
	$("#myModal3").modal("show");
}

function dropZoneConfigure(id,multiInput){
	var parallelUploads;
		var	maxFiles;
		var eventOnclick;
		var clearDropzone;
 if(multiInput){
 		parallelUploads=1;
 		maxFiles=1;
 		eventOnclick="button#uploadDocument";
 		clearDropzone="a#clear-dropzone";
 	}
 	else{
 		parallelUploads=1;
 		maxFiles=1;
 		eventOnclick="button#replaceDocument";
 		clearDropzone="a#clear-ReplaceFile";
 	}
 	
	 $(id).dropzone({
		autoProcessQueue: false,
    uploadMultiple: multiInput,
    parallelUploads: parallelUploads,
    maxFiles: maxFiles,
    addRemoveLinks:true,
    maxFilesize:50, // MB
    acceptedFiles:".xls,.xlsx",
    init: function() {
    	var myDropzone = this;
    	  
                this.element.querySelector(eventOnclick).addEventListener("click", function(e) {
                	e.preventDefault();
                    e.stopPropagation();
                    documentUploaded=true;
                    myDropzone.processQueue();
                });
         
         this.on("sendingmultiple", function() {
         	
         });
         this.on("success", function() {
        	 location.reload(); 
         });
         this.on("successmultiple", function(files, response) {
        	 });
         this.on("errormultiple", function(files, response) {
         });
          
      // Setup the observer for the button.
      	var _this = this;
         document.querySelector(clearDropzone).addEventListener("click", function() {
           // Using "_this" here, because "this" doesn't point to the dropzone anymore
           _this.removeAllFiles();
           // If you want to cancel uploads as well, you
           // could also call _this.removeAllFiles(true);
         });   
     }
   	});	 
	}
