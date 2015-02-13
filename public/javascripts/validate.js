$(document).ready(function(){
	$("body").on("submit","#login",function(){
		var userName = document.forms.login.userName.value;
		var passWord = document.forms.login.passWord.value;
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
		} else {
			$.ajax({
				type:"POST",
				data:{"userName":userName,"passWord":passWord},
				url:"login",
				success:function(check){
					console.log("success");	
				}
			});
		}
	});
	
	$(".birthDay").datepicker({
			format: "dd/mm/yyyy"
	});
	
	$("#signup").on("submit",function(){
		var account = $("#account").val();
		var address = $("#address").val();
		var birthday = $("#birthday").val();
		var sex = $("#sex").val();
		$.ajax({
			type:"POST",
			url:"signup",
			data:{"account","address"}
		});
	});
});
