$(document).ready(function(){
	Dropzone.autoDiscover = false;
	
});


function dropZoneConfigure(id,multiInput,format,maxFiles,reload){
	var parallelUploads;
		var	maxFiles;
		var eventOnclick;
		var clearDropzone;
	if(multiInput){
		parallelUploads=3;
	 	this.maxFiles=maxFiles;
 		eventOnclick="button#uploadDocument";
 		clearDropzone="a#clear-dropzone";
 	}
 	else{
		parallelUploads=1;
	 	maxFiles=1;
 		eventOnclick="button#uploadDocument";
 		clearDropzone="a#clear-dropzone";
 	}
 	
	 $(id).dropzone({
		autoProcessQueue: false,
    uploadMultiple: multiInput,
    parallelUploads: parallelUploads,
    maxFiles: maxFiles,
    addRemoveLinks:true,
    maxFilesize:50, // MB
    acceptedFiles:format,
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
        	 if(reload!=undefined && reload==true){
        		 window.location.reload();
        	 }
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