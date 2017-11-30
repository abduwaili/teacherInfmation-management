function SearchTearch(){
	$.ajax({
        url: "/xaingmu/src/com.admin.action/AdminAction",
        async: false,
        type: 'POST',
       dataType: 'json',
        success: function(data) {
            alert("ajax成功")	
        }
    });
}

function AuditStudentSigin(TeacherAccount){
	 $.ajax({
	        url: "/xaingmu/AuditTeacherSigin",
	        data: {
	        	TeacherAccount: TeacherAccount
	        },
	        async: false,
	        type: 'POST',
	        dataType: 'json',
	        success: function(data){
	            var ret = eval("(" + data + ")");
	            switch (ret['ret']) {
	                case "Success":
	                    var onlyChoseAlert = simpleAlert({
	                        "content": "已经通过审核",
	                        "buttons": {
	                            "确定": function() {
	                                onlyChoseAlert.close();
	                            }
	                        }
	                    })
	                    break;
	                case "Fail":
	                    var onlyChoseAlert = simpleAlert({
	                        "content": "拒绝审核",
	                        "buttons": {
	                            "确定": function() {
	                                onlyChoseAlert.close();
	                            }
	                        }
	                    })
	                    break;
	                default:
	                    var onlyChoseAlert = simpleAlert({
	                        "content": "未知错误",
	                        "buttons": {
	                            "确定": function() {
	                                onlyChoseAlert.close();
	                            }
	                        }
	                    })
	            }
	        }
	 });
	
}