$(document).ready(function(){
	$("body").on("submit","#login",function(){
		var userName = $("#userName").val();
		var passWord = $("#passWord").val();
		if((passWord == "")&&(userName == "")){
			$("#errorP").remove();
			$("#errorN").remove();
			$("#divPass").append("<label id ='errorP' class='control-label'style='color: red;'>Not empty</label>");
			$("#divName").append("<label id ='errorN' class='control-label'style='color: red;'>Not empty</label>");
			return false;
		} else if((userName == "")&&(passWord!="")){
				$("#errorP").remove();
				$("#errorN").remove();
				$("#divName").append("<label id ='errorN' class='control-label'style='color: red;'>Not empty</label>")
				return false;
		} else if((passWord =="")&&(userName!="")){
					$("#errorP").remove();
					$("#errorN").remove();
					$("#divPass").append("<label id ='errorP' class='control-label'style='color: red;'>Not empty</label>");
					return false
		} 
	});
	
	$("#birthday").datepicker({
			format: "dd/mm/yyyy"
	});

});
