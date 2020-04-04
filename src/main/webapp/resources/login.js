$(function() {
	$('.checkbox').click(function(event) {
		if ($(this).hasClass('checked')) {
			$(this).removeClass('checked');
		} else {
			$(this).addClass('checked');
		}
	});
	$('input').focusin(function(event) {
		$(this).css('borderColor', '#0079CC');
	});
	$('input').focusout(function(event) {
		$(this).css('borderColor', '#cacaca');
	});

	$("#loginbtn").click(function() {
		alert("1111");
		$.ajax({
			url : "http://localhost:8080/dormitory/user/login",
			type : "POST",
			data : {
				"wechatid" : $("#wechatid").val()
			},
			dataType:"json",
			success : function(datas){
				// alert(datas.respCode);
				if(datas.respCode==200){
					//跳转
					location.href="http://localhost:8080/dormitory/repair/list";
				}else{
					alert(datas.respMessage+":"+datas.errorInfo);
				}
			}
		});
	});

});







