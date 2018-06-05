$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
});

function saveContact() {
	if ($("#phone").val() == "" && $("#email").val() == "") {
		$(".error-msg").show();
		return false;
	}
	var webId = $("#web_id").val();
	var phone = $("#phone").val();
	var email = $("#email").val();
	$.post(contactUrl, {
		"id" : webId,
		"phone" : phone,
		"email" : email
	}, function(data) {
		if (data != undefined && data.success != undefined) {
			alert(data.msg)
			if (data.success == true) {
				$("#contactModal").modal("hide");
				location.reload();
			} else {
			}
		} else {
			alert("Something went wrong please try again later")
		}
	});
}

function editContact(id) {
	$(".error-msg").hide();
	$("#phone").val("");
	$("#email").val("");
	$.get(contactUrl, {
		id : id
	}, function(data) {
		if (data != undefined) {
			if (data.phone != undefined) {
				$("#phone").val(data.phone);
			}
			if (data.email != undefined) {
				$("#email").val(data.email);
			}
		}
		$("#web_id").val(id);
	});
	$("#contactModal").modal("show");
}