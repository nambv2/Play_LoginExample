$(document).ready(function(){
	$("input:checkbox").removeAttr("checked");
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
	
	$("body").on("submit","#admin",function(){
		return false;
	})
	$("body").on("click",".select",function(){
		var size = $(".size").val();
		var id = $(this).parents().attr("id");
		$(".selectAll").prop("checked",false);
		if($("input:checkbox:checked").length==size){
			console.log("done here");
			$(".selectAll").prop('checked', true);
		}
		
	});
	
	$("body").on("click","#delete",function(){
		var selected = [];
		var account="abc";
		$("input:checked").each(function(){
			var id = $(this).parents().attr("id");
			var MyRows = $('table.table').find('tbody').find('tr');
			var account = $(MyRows[id]).find('td:eq(0)').html();
			selected.push(account);
		});
		$.ajax({
			type:"POST",
			url:"deleteitem",
			data:{"account":selected},
			success:function(){
				location.reload();
			}
		});
	});
	
	$(".selectAll").click(function(){
		$("input[type=checkbox]").not(this).prop("checked",this.checked);
	});
	
	
	
});
