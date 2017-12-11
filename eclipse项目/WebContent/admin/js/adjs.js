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

function AuditTearchSigin(TeacherAccount)
{
	var btn0=document.getElementById(TeacherAccount+"t0");
	var btn1=document.getElementById(TeacherAccount+"t1");  
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
	                	alert("已通过")
	                    btn0.innerHTML = "已通过";
	                	btn0.style.background = "pink";
	                	btn0.disabled = true;
	                	btn1.style.display="none";
	                    break;
	                    
	                case "Fail":
	                	alert("操作失败，稍后再试");
	                    break;
	                    
	                default:
	                	alert("发生未知错误，请检查后台");
	            }
	        }
	 });
}

function NoAuditTearchSigin(TeacherAccount)
{
	var btn0=document.getElementById(TeacherAccount+"t0");
	var btn1=document.getElementById(TeacherAccount+"t1");  
	 $.ajax({
	        url: "/xaingmu/NoAuditTeacherSigin",
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
	                	alert("已拒绝")
	                    btn1.innerHTML = "已拒绝";
	                	btn1.style.background = "pink";
	                	btn1.disabled = true;
	                	btn0.style.display="none";
	                    break;
	                    
	                case "Fail":
	                	alert("操作失败，稍后再试");
	                    break;
	                    
	                default:
	                	alert("发生未知错误，请检查后台");
	            }
	        }
	 });
}

function AuditStudentSigin(StudentAccount)
{
	var btn0=document.getElementById(StudentAccount+"s0");
	var btn1=document.getElementById(StudentAccount+"s1");  
	 $.ajax({
	        url: "/xaingmu/AuditStudentSigin",
	        data: {
	        	StudentAccount: StudentAccount
	        },
	        async: false,
	        type: 'POST',
	        dataType: 'json',
	        success: function(data){
	            var ret = eval("(" + data + ")");
	            switch (ret['ret']) {
	                case "Success":
	                	alert("已通过")
	                    btn0.innerHTML = "已通过";
	                	btn0.style.background = "pink";
	                	btn0.disabled = true;
	                	btn1.style.display="none";
	                    break;
	                    
	                case "Fail":
	                	alert("操作失败，稍后再试");
	                    break;
	                    
	                default:
	                	alert("发生未知错误，请检查后台");
	            }
	        }
	 });
}

function NoAuditStudentSigin(StudentAccount)
{
	var btn0=document.getElementById(StudentAccount+"s0");
	var btn1=document.getElementById(StudentAccount+"s1");  
	 $.ajax({
	        url: "/xaingmu/NoAuditStudentSigin",
	        data: {
	        	StudentAccount: StudentAccount
	        },
	        async: false,
	        type: 'POST',
	        dataType: 'json',
	        success: function(data){
	            var ret = eval("(" + data + ")");
	            switch (ret['ret']) {
	                case "Success":
	                	alert("已拒绝")
	                    btn1.innerHTML = "已拒绝";
	                	btn1.style.background = "pink";
	                	btn1.disabled = true;
	                	btn0.style.display="none";
	                    break;
	                    
	                case "Fail":
	                	alert("操作失败，稍后再试");
	                    break;
	                    
	                default:
	                	alert("发生未知错误，请检查后台");
	            }
	        }
	 });
}

function AddBaike(TeacherAccount)
{
	var Baike=document.getElementById("baike").value;  
	 $.ajax({
	        url: "/xaingmu/AddBaike",
	        data: {
	        	TeacherAccount: TeacherAccount,
	        	Baike:Baike
	        },
	        async: false,
	        type: 'POST',
	        dataType: 'json',
	        success: function(data){
	            var ret = eval("(" + data + ")");
	            switch (ret['ret']) {
	                case "Success":
	                	alert("修改成功");
	                    break;
	                    
	                case "Fail":
	                	alert("修改失败");
	                    break;
	                    
	                default:
	                	alert("发生未知错误，请检查后台");
	            }
	        }
	 });
}

function HiddenFeedback(id){
	var btn1=document.getElementById(id);  
	 $.ajax({
	        url: "/xaingmu/HiddenFeedback",
	        data: {
	        	Id: id
	        },
	        async: false,
	        type: 'POST',
	        dataType: 'json',
	        success: function(data){
	            var ret = eval("(" + data + ")");
	            switch (ret['ret']) {
	                case "Success":
	                	alert("操作成功，此反馈以后不会显示");
	                	 btn1.innerHTML = "已处理";
		                	btn1.style.background = "pink";
		                	btn1.disabled = true;
	                    break;
	                    
	                case "Fail":
	                	alert("操作失败，请重试");
	                    break;
	                    
	                default:
	                	alert("发生未知错误，请检查后台");
	            }
	        }
	 });
}