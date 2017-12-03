
function signOut() {
	$.ajax({
        url: "/xaingmu/SignOut",
        async: false,
        type: 'POST',
        dataType: 'json',
        success: function(data) {
        	window.location.reload();
        }
    });
}
function chooseTeacher() {
    var chooseTeacher = document.getElementById("chooseTeacher");
    var code = "";
    $.ajax({
        url: "/xaingmu/chooseTeacher",
        async: false,
        type: 'POST',
        dataType: 'json',
        success: function(data) {
            var ret = eval("(" + data + ")");
            switch (ret['ret']) {
                case "Success":
                    code = code + ' <div class="4u" name="t1">\
                            <article class="box box-style2">\
                                <a class="image image-full" href="' + ret["teacherHref1"] + '">\
                                    <img alt="" src="' + ret["teacherImage1"] + '"/>\
                                </a>\
                                <h3>\
                                    <a>' +
                                        ret["teacherName1"] +
                                    '</a>\
                                </h3>\
                                <p>'+
                                    ret["teacherInf1"] +
                                '</p>\
                            </article>\
                        </div>\
                        <div class="4u" name="t2">\
                            <article class="box box-style2">\
                                <a class="image image-full" href="' + ret["teacherHref2"] +'" >\
                                    <img alt="" src="' + ret["teacherImage2"] +'"/>\
                                </a>\
                                <h3>\
                                    <a>'+
                                        ret["teacherName2"] +
                                    '</a>\
                                </h3>\
                                <p>'+
                                    ret["teacherInf2"] +
                                '</p>\
                            </article>\
                        </div>\
                        <div class="4u" name="t3">\
                            <article class="box box-style2">\
                                <a class="image image-full" href="' + ret["teacherHref3"] + '">\
                                    <img alt="" src="' + ret["teacherImage3"] + '"/>\
                                </a>\
                                <h3>\
                                    <a>'+
                                        ret["teacherName3"] +
                                    '</a>\
                                </h3>\
                                <p>'+
                                    ret["teacherInf3"] +
                                '</p>\
                            </article>\
                        </div>';
                    chooseTeacher.innerHTML = code;
                    break;
                case "Fail":
                    var onlyChoseAlert = simpleAlert({
                        "content": "获取teacher信息失败",
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

function cancelOrder(id) {
	var btn = document.getElementById(id);
    $.ajax({
        url: "/xaingmu/cancelOrder",
        data: {
            id: id
        },
        async: false,
        type: 'POST',
        dataType: 'json',
        success: function(data) {
            var ret = eval("(" + data + ")");
            switch (ret['ret']) {
                case "Success":
                    var onlyChoseAlert = simpleAlert({
                        "content": "取消成功",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    btn.innerHTML = "已取消";
                    btn.style.background = "pink";
                    btn.disabled = true;
                    break;
                case "SuccessOther":
                    var onlyChoseAlert = simpleAlert({
                        "content": "操作成功",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    btn.innerHTML = "操作成功";
                    btn.style.background = "pink";
                    btn.disabled = true;
                    break;  
                case "Fail":
                    var onlyChoseAlert = simpleAlert({
                        "content": "取消失败,请稍后重试",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                default:
                    var onlyChoseAlert = simpleAlert({
                        " v": "未知错误",
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

function signInCheck() {
    var username = $("input[name=accountIn]").val();
    var password = $("input[name=passwordIn]").val();
    var checkTeacher = document.getElementsByName("isTeacherIn");
    var Login = document.getElementById("Login");
    var code = "";
    var isTeacher = "";
    var c = document.getElementById('closeSignIn');

    if (checkTeacher[1].checked) {
        isTeacher = checkTeacher[1].value;
    } 
    if (checkTeacher[0].checked){
        isTeacher = checkTeacher[0].value;
    }
    if (isTeacher.replace(/(^s*)|(s*$)/g, "").length == 0 ||username.replace(/(^s*)|(s*$)/g, "").length == 0 || password.replace(/(^s*)|(s*$)/g, "").length == 0 || isTeacher.replace(/(^s*)|(s*$)/g, "").length == 0) {
        c.click();
        var onlyChoseAlert = simpleAlert({
            "content": "请输入参数",
            "buttons": {
                "确定": function() {
                    onlyChoseAlert.close();
                }
            }
        })
        return false;
    }
    var c = document.getElementById('closeSignIn');
    c.click();
    $.ajax({
        url: "/xaingmu/LoginIn",
        data: {
            Account: username,
            Password: password,
            IsTeacher: isTeacher
        },
        async: false,
        type: "POST",
        dataType: "json",
        success: function(data) {
            var ret = eval("(" + data + ")");
            switch (ret["ret"]) {
                case "NoPara":
                    var onlyChoseAlert = simpleAlert({
                        "content": "请输入参数",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "Checking":
                    var onlyChoseAlert = simpleAlert({
                        "content": "正在审核中，稍后再登陆",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "NotFound":

                    var onlyChoseAlert = simpleAlert({
                        "content": "用户名不存在",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })

                    break;
                case "ErrorPassword":
                    var onlyChoseAlert = simpleAlert({
                        "content": "密码错误，请重新输入",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "Success":
                    setTimeout(function() {
                    	window.location.reload();
                    }, 1000);
                    break;

            }
        }
    });
    return false;
}

function signUpCheck() {
    var username = $("input[name=accountUp]").val();
    var password = $("input[name=passwordUp]").val();
    var confirmPassword = $("input[name=passwordConfirm]").val();
    var college = $("input[name=collegeUp]").val();
    var c = document.getElementById('closeSignUp');
    var majorOrPosition;
    var code ="";
    var phone = $("input[name=telUp]").val();
    var realName = $("input[name=realNameUp]").val();
    var checkTeacher = document.getElementsByName("isTeacherUp");
    var isTeacher = "";
    if (checkTeacher[1].checked) {
        isTeacher = checkTeacher[1].value;
        majorOrPosition = $("input[name=majorUp]").val();
    } 
    if (checkTeacher[0].checked) {
    	majorOrPosition = $("input[name=positionUp]").val();
        isTeacher = checkTeacher[0].value;
    }
    if (isTeacher.replace(/(^s*)|(s*$)/g, "").length == 0 ||username.replace(/(^s*)|(s*$)/g, "").length == 0 || password.replace(/(^s*)|(s*$)/g, "").length == 0 || confirmPassword.replace(/(^s*)|(s*$)/g, "").length == 0 || college.replace(/(^s*)|(s*$)/g, "").length == 0 || majorOrPosition.replace(/(^s*)|(s*$)/g, "").length == 0 || phone.replace(/(^s*)|(s*$)/g, "").length == 0 || realName.replace(/(^s*)|(s*$)/g, "").length == 0) {
        c.click();
        var onlyChoseAlert = simpleAlert({
            "content": "请输入参数",
            "buttons": {
                "确定": function() {
                    onlyChoseAlert.close();
                }
            }
        })
        return false;
    }
    if (password != confirmPassword) {
        c.click();
        var onlyChoseAlert = simpleAlert({
            "content": "密码不一致",
            "buttons": {
                "确定": function() {
                    onlyChoseAlert.close();
                }
            }
        })
    } else {
        c.click()
        $.ajax({
            url: "/xaingmu/SignUp",
            data: {
                Account: username,
                Password: password,
                realName: realName,
                IsTeacher: isTeacher,
                college: college,
                majorOrPosition: majorOrPosition,
                phone: phone
            },
            async: false,
            type: "POST",
            dataType: "json",
            success: function(data) {
                var ret = eval("(" + data + ")");
                switch (ret["ret"]) {
                    case "Exist":
                        var onlyChoseAlert = simpleAlert({
                            "content": "用户已存在",
                            "buttons": {
                                "确定": function() {
                                    onlyChoseAlert.close();
                                }
                            }
                        })
                        break;
                    case "Success":
                        var onlyChoseAlert = simpleAlert({
                            "content": "注册成功，请登录",
                            "buttons": {
                                "确定": function() {
                                    onlyChoseAlert.close();
                                }
                            }
                        })
                        break;
                }
            }
        });
        return false;
    }
}

function search() {
    var teacherName = $("input[name=searchTearcherName]").val();
    var navTeacherInf = document.getElementById("navTeacherInf");
    var showSearchTeacher = document.getElementById("showSearchTeacher"); // add the dom teacher to show the teacher information
    var code = "";
    if (teacherName.replace(/(^s*)|(s*$)/g, "").length == 0) {
        var onlyChoseAlert = simpleAlert({
            "content": "教师姓名不能为空",
            "buttons": {
                "确定": function() {
                    onlyChoseAlert.close();
                }
            }
        })
        return false;
    }
    $.ajax({
        url: "/xaingmu/searchTeacher",
        data: {
            teacherName: teacherName
        },
        async: false,
        type: "POST",
        dataType: "json",
        success: function(data) {
            var ret = eval("(" + data + ")");
            switch (ret["ret"]) {
                case "NotFound":
                    var onlyChoseAlert = simpleAlert({
                        "content": "教师不存在",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "Fail":
                    var onlyChoseAlert = simpleAlert({
                        "content": "查询失败",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "Success":
                    code = code + '<div class="wrapper wrapper-style1 wrapper-first">\
                    <article class="container" id="articleTeacherInf">\
                <div class="row">\
                    <!--个人图片-->\
                    <div class="4u">\
                        <span class="me image image-full">\
                            <img src="' + ret["searchTeacherImage"] +'"/>\
                        </span>\
                    </div>\
                    <!--个人信息-->\
                    <div class="8u">\
                        <form>\
                            <div class="row first-child">\
                                <div class="6u">\
                                    <label for="searchTeacherName">\
                                        姓名\
                                    </label>\
                                    <input id="searchTeacherName" value="' + ret["searchTeacherName"] + '" style="background-color:#EEE" type="text" readonly/>\
                                </div>\
                                <div class="6u">\
                                    <label for="searchTeacherCollege">\
                                        學院\
                                    </label>\
                                    <input id="searchTeacherCollege" value="' +ret["searchTeacherCollege"] + '" style="background-color:#EEE" type="text"/>\
                                </div>\
                            </div>\
                            <div class="row half">\
                                <div class="6u">\
                                    <label for="searchTeacherPosition">\
                                        职称\
                                    </label>\
                                    <input id="searchTeacherPosition" value="' + ret["searchTeacherPosition"] +'" style="background-color:#EEE" type="text" readonly/>\
                                </div>\
                                <div class="6u">\
                                    <label for="searchTeacherPhone">\
                                        联系方式\
                                    </label>\
                                    <input id="searchTeacherPhone" value="' + ret["searchTeacherPhone"] + '" style="background-color:#EEE" type="text" readonly/>\
                                </div>\
                            </div>\
                            <div class="row half">\
                            <div class="6u">\
                                <label for="searchTeacherMoney">\
                                    基金\
                                </label>\
                                <input id="searchTeacherMoney" name="searchTeacherMoney" placeholder="职称" value="' + ret["searchTeacherMoney"] + '" style="background-color:#EEE" type="text"/>\
                            </div>\
                            <div class="6u">\
                                <label for="searchTeacherFSRA">\
                                    科研成果\
                                </label>\
                                <input id="searchTeacherFSRA" name="searchTeacherFRSA" value="' + ret["searchTeacherFSRA"] +'" placeholder="联系方式" style="background-color:#EEE" type="text"/>\
                            </div>\
                        </div> \
                        </form>\
                        <div class="8u" style="padding-top:2em">\
                            <p>\
                            日程安排如下\
                            </p>\
                        </div>\
                    </div>\
                </div>\
                <table>\
                    <thead>\
                        <tr>\
                            <th>\
                                时间\
                            </th>\
                            <th>\
                                星期一\
                            </th>\
                            <th>\
                                星期二\
                            </th>\
                            <th>\
                                星期三\
                            </th>\
                            <th>\
                                星期四\
                            </th>\
                            <th>\
                                星期五\
                            </th>\
                        </tr>\
                    </thead>\
                    <tbody>\
                        <tr>\
                            <td>\
                    上午\
                </td>';
                if(ret["searchMonAm"] == "none"){
                    code = code + '<td>\
                空闲\
                </td>';
                }else{
                    code = code + '<td>' + ret["searchMonAm"] + '</td>';
                }
                if(ret["searchTueAm"] =="none"){
                    code = code + '<td>\
                空闲\
                </td>';
                }else{
                    code = code + '<td>' + ret["searchTueAm"] + '</td>';
                }
                if(ret["searchWedAm"] == "none"){
                    code = code + '<td>\
                    空闲\
                </td>';
                }else{
                    code = code + '<td>' + ret["searchWedAm"] + '</td>';
                }
                if(ret["searchThuAm"]  == "none"){
                    code = code + '<td>\
                    空闲\
                </td>';
                }else{
                    code = code + '<td>' + ret["searchThuAm"] + '</td>';
                }
                if(ret["searchFriAm"] == "none"){
                    code = code + '<td>\
                    空闲\
                </td>';
                }else{
                    code = code + '<td>' + ret["searchFriAm"] + '</td>';
                }
            code = code + '</tr>\
            <tr>\
                <td>\
                    下午\
                </td>';
            if(ret["searchMonPm"] == "none"){
                code = code + '<td>\
                空闲\
            </td>';
            }else{
                code = code + '<td>' + ret["searchMonPm"] + '</td>';
            }
            if(ret["searchTuePm"] == "none"){
                code = code + '<td>\
                空闲\
            </td>';
            }else{
                code = code + '<td>' + ret["searchTuePm"] + '</td>';
            }
            if(ret["searchWedPm"] == "none"){
                code = code + '<td>\
                空闲\
            </td>';
            }else{
                code = code + '<td>' + ret["searchWedPm"] + '</td>';
            }
            if(ret["searchThuPm"] == "none"){
                code = code + '<td>\
                空闲\
            </td>';
            }else{
                code = code + '<td>' + ret["searchThuPm"] + '</td>';
            }
            if(ret["searchFriPm"] == "none"){
                code = code + '<td>\
                空闲\
            </td>';
            }else{
                code = code + '<td>' + ret["searchFriPm"] + '</td>';
            }
            code = code + '</tr>\
        </tbody>\
    </table> </article></div>';
                    showSearchTeacher.innerHTML = code;
                    showSearchTeacher.style.display = "block";
                    break;
            }
        }
    });
    return false;
}
function addSchedule(){
    var schedule = $("input[name=addScheduleContent]").val();
    var date = $("input[name=scheduleDate]").val();
    var start = $("input[name=addScheduleStart]").val();
    var end = $("input[name=addScheduleEnd]").val();
    var c = document.getElementById("closeAddSchedule");
    var check = document.getElementsByName("isAddWatch");
    var isWatch = "";
    if (check[1].checked) {
        isWatch = check[1].value;
    }  
    if (check[0].checked) {
        isWatch = check[0].value;
    }
    if (schedule.replace(/(^s*)|(s*$)/g, "").length == 0 || date.replace(/(^s*)|(s*$)/g, "").length == 0 || isWatch.replace(/(^s*)|(s*$)/g, "").length == 0) {
        c.click();
        var onlyChoseAlert = simpleAlert({
            "content": "请输入参数",
            "buttons": {
                "确定": function() {
                    onlyChoseAlert.close();
                }
            }
        })
        return false;
    }
    $.ajax({
        url: "/xaingmu/addSchedule",
        data: {
            date: date,
            schedule: schedule,
            start: start,
            end: end,
            isWatch: isWatch
        },
        async: false,
        type: 'POST',
        dataType: 'json',
        success: function(data){
            c.click();
            var ret = eval("(" + data + ")");
            switch (ret['ret']) {
                case "Success":
                    var onlyChoseAlert = simpleAlert({
                        "content": "增加日程成功",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "Fail":
                    var onlyChoseAlert = simpleAlert({
                        "content": "增加日程失败,请稍后重试",
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
function changeSchedule(){
    var schedule = $("input[name=changeScheduleContent]").val();
    var date = $("input[name=changeScheduleDate]").val();
    var start = $("input[name=changeScheduleStart]").val();
    var end = $("input[name=changeScheduleEnd]").val();
    var c = document.getElementById("closeChangeSchedule");
    var check = document.getElementsByName("isChangeWatch");
    var isWatch = "";
    if (check[1].checked) {
        isWatch = check[1].value;
    }  
    if (check[0].checked) {
        isWatch = check[0].value;
    }
    if (schedule.replace(/(^s*)|(s*$)/g, "").length == 0 || date.replace(/(^s*)|(s*$)/g, "").length == 0 || isWatch.replace(/(^s*)|(s*$)/g, "").length == 0) {
        c.click();
        var onlyChoseAlert = simpleAlert({
            "content": "请输入参数",
            "buttons": {
                "确定": function() {
                    onlyChoseAlert.close();
                }
            }
        })
        return false;
    }
    $.ajax({
        url: "/xaingmu/changeSchedule",
        data: {
            date: date,
            schedule: schedule,
            start: start,
            end: end,
            isWatch: isWatch
        },
        async: false,
        type: 'POST',
        dataType: 'json',
        success: function(data){
            c.click();
            var ret = eval("(" + data + ")");
            switch (ret['ret']) {
                case "Success":
                    var onlyChoseAlert = simpleAlert({
                        "content": "修改日程成功",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "Fail":
                    var onlyChoseAlert = simpleAlert({
                        "content": "修改日程失败,请稍后重试",
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
function deleteSchedule(){
    var schedule = $("input[name=deleteScheduleCotent]").val();
    var date = $("input[name=deleteScheduleDate]").val();
    var start = $("input[name=deleteScheduleStart]").val();
    var end = $("input[name=deleteScheduleEnd]").val();
    var c = document.getElementById("closeDeleteSchedule");
    if (schedule.replace(/(^s*)|(s*$)/g, "").length == 0 || date.replace(/(^s*)|(s*$)/g, "").length == 0) {
        c.click();
        var onlyChoseAlert = simpleAlert({
            "content": "请输入参数",
            "buttons": {
                "确定": function() {
                    onlyChoseAlert.close();
                }
            }
        })
        return false;
    }
    $.ajax({
        url: "/xaingmu/deleteSchedule",
        data: {
            date: date,
            schedule: schedule,
            start: start,
            end: end
        },
        async: false,
        type: 'POST',
        dataType: 'json',
        success: function(data){
            c.click();
            var ret = eval("(" + data + ")");
            switch (ret['ret']) {
                case "Success":
                    var onlyChoseAlert = simpleAlert({
                        "content": "删除日程成功",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "Fail":
                    var onlyChoseAlert = simpleAlert({
                        "content": "删除日程失败,请稍后重试",
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
function addOrder(){
    var orderTime = $("input[name=orderTime]").val();
    var orderTeacher = $("input[name=orderTeacher]").val();
    var orderReason =$("input[name=orderReason]").val();
    var orderPhone = $("input[name=orderPhone]").val();
    var c = document.getElementById("studentAddOrder");
    if (orderTime.replace(/(^s*)|(s*$)/g, "").length == 0||orderTeacher.replace(/(^s*)|(s*$)/g, "").length == 0 || orderPhone.replace(/(^s*)|(s*$)/g, "").length == 0 ||orderReason.replace(/(^s*)|(s*$)/g, "").length == 0) {
        c.click();
        var onlyChoseAlert = simpleAlert({
            "content": "请输入预约信息",
            "buttons": {
                "确定": function() {
                    onlyChoseAlert.close();
                }
            }
        })
        return false;
    }
    $.ajax({
        url: "/xaingmu/addOrder",
        data: {
            orderTime: orderTime,
            orderReason: orderReason,
            orderTeacher:orderTeacher,
            orderPhone:orderPhone
        },
        async: false,
        type: 'POST',
        dataType: 'json',
        success: function(data){
            c.click();
            var ret = eval("(" + data + ")");
            switch (ret['ret']) {
                case "Success":
                    var onlyChoseAlert = simpleAlert({
                        "content": "预约成功",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "NoTeacher":
                    var onlyChoseAlert = simpleAlert({
                        "content": "预约教师不存在",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "ErrorTime":
                    var onlyChoseAlert = simpleAlert({
                        "content": "预约时间错误",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "NoTime":
                    var onlyChoseAlert = simpleAlert({
                        "content": "预约时间已被占用，请重新选择时间",
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

function changeTeacherInf(){
    var name = $("input[name=teacherName]").val();
    var phone= $("input[name=teacherPhone]").val();
    var position = $("input[name=teacherPosition]").val();
    var college = $("input[name=teacherCollege]").val();
    if (name.replace(/(^s*)|(s*$)/g, "").length == 0 || college.replace(/(^s*)|(s*$)/g, "").length == 0 || phone.replace(/(^s*)|(s*$)/g, "").length == 0 || position.replace(/(^s*)|(s*$)/g, "").length == 0) {
        var onlyChoseAlert = simpleAlert({
            "content": "修改信息不能为空",
            "buttons": {
                "确定": function() {
                    onlyChoseAlert.close();
                }
            }
        })
        return false;
    }
    $.ajax({
        url: "/xaingmu/changeTeacherInf",
        data: {
            name: name
        },
        async: false,
        type: "POST",
        dataType: "json",
        success: function(data) {
            var ret = eval("(" + data + ")");
            switch (ret["ret"]) {
                case "Success":
                    var onlyChoseAlert = simpleAlert({
                        "content": "更改信息成功",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "Fail":
                    var onlyChoseAlert = simpleAlert({
                        "content": "更改信息失败",
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
function changeStudentInf(){
    var name = $("input[name=studentName]").val();
    var phone= $("input[name=studentTel]").val();
    var major = $("input[name=studentMajor]").val();
    var college = $("input[name=studentCollege]").val();
    if (name.replace(/(^s*)|(s*$)/g, "").length == 0 || college.replace(/(^s*)|(s*$)/g, "").length == 0 || phone.replace(/(^s*)|(s*$)/g, "").length == 0 || major.replace(/(^s*)|(s*$)/g, "").length == 0) {
        var onlyChoseAlert = simpleAlert({
            "content": "修改信息不能为空",
            "buttons": {
                "确定": function() {
                    onlyChoseAlert.close();
                }
            }
        })
        return false;
    }
    $.ajax({
        url: "/xaingmu/changeStudentInf",
        data: {
            name: name,
            major: major,
            college: college,
            phone: phone
        },
        async: false,
        type: "POST",
        dataType: "json",
        success: function(data) {
            var ret = eval("(" + data + ")");
            switch (ret["ret"]) {
                case "Success":
                    var onlyChoseAlert = simpleAlert({
                        "content": "更改信息成功",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "Fail":
                    var onlyChoseAlert = simpleAlert({
                        "content": "更改信息失败",
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
function teacherAgreeOrder(id){

    $.ajax({
        url: "/xaingmu/teacherAgreeOrder",
        data: {
            id: id
        },
        async: false,
        type: 'POST',
        dataType: 'json',
        success: function(data){
            var ret = eval("(" + data + ")");
            switch (ret['ret']) {
                case "Success":
                    var onlyChoseAlert = simpleAlert({
                        "content": "已同意该预约",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "Fail":
                    var onlyChoseAlert = simpleAlert({
                        "content": "同意预约失败,请稍后重试",
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
function teacherCancelOrder(id){
    $.ajax({
        url: "/xaingmu/teacherCancelOrder",
        data: {
            id: id
        },
        async: false,
        type: 'POST',
        dataType: 'json',
        success: function(data){
            var ret = eval("(" + data + ")");
            switch (ret['ret']) {
                case "Success":
                    var onlyChoseAlert = simpleAlert({
                        "content": "已拒绝该预约",
                        "buttons": {
                            "确定": function() {
                                onlyChoseAlert.close();
                            }
                        }
                    })
                    break;
                case "Fail":
                    var onlyChoseAlert = simpleAlert({
                        "content": "拒绝预约失败,请稍后重试",
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
function showSchedule(){
	var btn = document.getElementById("showSchedule");
	$.ajax({
        url: "/xaingmu/showSchedule",
        async: false,
        type: "POST",
        dataType: "json",
        success: function(data) {
        	btn.disabled = true;
            var ret = eval("(" + data + ")");
            var cnt = ret["cnt"];
        	var schedule = ret["schedule"];
        	var array = new Array(cnt);
        	for(var i = 0; i < cnt; i ++){
        		if(schedule[i]["allDay"] == 'true'){
        			array[i] = {
        					title:schedule[i]["title"],
        					allDay:true,
        					start: new Date(schedule[i]["year"], schedule[i]["month"] - 1, schedule[i]["day"])
        			}
        		}else{
        			array[i] = {
        					title:schedule[i]["title"],
        					allDay:false,
        					start: new Date(schedule[i]["year"], schedule[i]["month"] - 1, schedule[i]["day"], schedule[i]["startHour"], schedule[i]["startMinutes"]),
							end: new Date(schedule[i]["year"], schedule[i]["month"] - 1, schedule[i]["day"], schedule[i]["endHour"], schedule[i]["endMinutes"])
        			}
        		}
        	}
        	"use strict";
        	// ------ DO NOT CHANGE ------- //
        	$(".flot-bar,.flot-pie,.flot,.flot-multi").bind("plothover", function (event, pos, item) {
        		if (item) {
        			var y;
        			if(event.currentTarget.className === 'flot flot-bar'){
        				y = Math.round(item.datapoint[1]);
        			} else if(event.currentTarget.className === 'flot flot-pie') {
        				y = Math.round(item.datapoint[0])+"%";
        			} else if(event.currentTarget.className === 'flot flot-line'){
        				y = (Math.round(item.datapoint[1] * 1000)/1000);
        			} else {
        				y = (Math.round(item.datapoint[1]*1000)/1000)+"€";
        			}
        			$("#tooltip").remove();
        			showTooltip(pos.pageX, pos.pageY,"Value = "+y);
        		}
        		else {
        			$("#tooltip").remove();
        		}
        	});

        	function showTooltip(x, y, contents) {
        		$('<div id="tooltip">' + contents + '</div>').css( {
        			top: y + 5,
        			left: x + 10
        		}).appendTo("body").show();
        	}
        	$('.deleteRow').click(function(e){
        		e.preventDefault();
        		$(this).parents('tr').fadeOut();
        	});

        	$(".animateRow").click(function(e){
        		e.preventDefault();
        		var el = $(this).parents('tr');
        		var target = $($(this).data('target'));
        		var defaultColor = target.find('a.dropdown-toggle').css('color');
        		var titleindex = parseInt($(this).data('title'), 10)-1;
        		var userindex = parseInt($(this).data('user'), 10)-1;
        		var dateindex = parseInt($(this).data('date'), 10)-1;
        		var title = el.find('td:eq('+titleindex+')').html();
        		var user = el.find('td:eq('+userindex+') a').html();
        		var userContent = el.find('td:eq('+userindex+') a').data('content');
        		var date = el.find('td:eq('+dateindex+')').html();
        		el.css({
        			position:'absolute',
        			left:el.position().left,
        			top:el.position().top
        		});
        		el.animate({
        			left:target.position().left,
        			top:target.position().top,
        			width:target.width(),
        			height:target.height()
        		}, 1000, function(){
        			el.hide();
        			var value = parseInt(target.find('a.dropdown-toggle .label').html(), 10);
        			if(isNaN(value)){
        				value = 0;
        			}
        			target.find('a.dropdown-toggle .label').html(value+1);
        			if(target.find('.label').is(":hidden")){
        				target.find('.label').show();
        			}
        			target.find('a.dropdown-toggle').stop().animate({
        				backgroundColor:target.find('a.dropdown-toggle .label').css('backgroundColor'),
        				color:'#fff'
        			},300, function(){
        				target.find('a.dropdown-toggle').animate({
        				backgroundColor:target.css('background-color'),
        				color:defaultColor
        			}, 200, function(){
        				target.find('a.dropdown-toggle').css('background-color', '').css('color', '');
        			});
        			});
        		});
        		target.find('.dropdown-menu').append('<li class="custom"><div class="title">'+title+'<span>'+date+' by <a href="#" class="pover" data-title="'+user+'" data-content="'+userContent+'">'+user+'</a></span></div><div class="action"><div class="btn-group"><a href="#" class="tip btn btn-mini" title="Show order"><img src="img/icons/fugue/magnifier.png" alt=""></a><a href="#" class="tip btn btn-mini" title="Delete order"><img src="img/icons/fugue/cross.png" alt=""></a></div></div></li>');
        		$(".pover").popover();$(".tip").tooltip();
        	});
        	$(".mini > li > a").hover(function(e){
        	e.stopPropagation();
        	if(!$(this).parent().hasClass("open")){
        		$(this).find(".label").stop().animate({
        			top: '-10px'
        		},200, function(){
        			$(this).stop().animate({top: '-6px'},100);
        		});
        	}
        	}, function(){});


        	$('.main-nav > li.active > a').click(function(e){
        		if($(window).width() <= 767){
        			e.preventDefault();
        			if($(this).hasClass('open') && (!$(this).hasClass('toggle-collapsed'))){
        				$(this).removeClass('open');
        				$(this).parents('.main-nav').find('li').each(function(){
        					$(this).find('.collapsed-nav').addClass('closed');
        					$(this).hide();
        				});
        				$(this).parent().show();
        				$(this).parent().removeClass('open');
        			} else {
        				if($(this).hasClass('toggle-collapsed')){
        					$(this).parent().addClass('open');
        				}
        				if($(this).hasClass("open")){
        					$(this).parents('.main-nav').find('li').not('.active').each(function(){
        						$(this).find('.collapsed-nav').addClass('closed');
        						$(this).hide();
        					});
        					$(this).removeClass("open");
        				} else {
        					$(this).addClass('open');
        					$(this).parents('.main-nav').find('li').show();
        				}
        			}
        		}
        	});

        	$('.toggle-collapsed').each(function(){
        		if($(this).hasClass('on-hover')){
        			$(this).click(function(e){e.preventDefault();});
        			$(this).parent().hover(function(){
        				$(this).addClass("open");
        				$(this).find('.collapsed-nav').slideDown();
        				$(this).find('img').attr("src", 'img/toggle-subnav-up-white.png');
        			}, function(){
        				$(this).removeClass("open");
        				$(this).find('.collapsed-nav').slideUp();
        				$(this).find('img').attr("src", 'img/toggle-subnav-down.png');
        			});
        		} else {
        			$(this).click(function(e){
        				e.preventDefault();
        				if($(this).parent().find('.collapsed-nav').is(":visible")){
        					$(this).parent().removeClass("open");
        					$(this).parent().find('.collapsed-nav').slideUp();
        					$(this).find('img').attr("src", 'img/toggle-subnav-down.png');
        				} else {
        					$(this).parent().addClass("open");
        					$(this).parent().find('.collapsed-nav').slideDown();
        					$(this).find('img').attr("src", 'img/toggle-subnav-up-white.png');
        				}
        			});
        		}
        	});

        	$('.collapsed-nav li a').hover(function(){
        		if(!$(this).parent().hasClass('active')){
        			$(this).stop().animate({
        				marginLeft: '5px'
        			}, 300);
        		}
        	}, function(){
        	$(this).stop().animate({
        			marginLeft: '0'
        		}, 100);
        	});
        	$('a.preview').live('mouseover mouseout mousemove click',function(e){
        			if(e.type === 'mouseover'){
        				$('body').append('<div id="image_preview"><img src="'+$(this).attr('href')+'" width="150"></div>');
        				$("#image_preview").fadeIn();
        			} else if(e.type === 'mouseout') {
        				$("#image_preview").remove();
        			} else if(e.type === 'mousemove'){
        				$("#image_preview").css({
        					top:e.pageY+10+"px",
        					left:e.pageX+10+"px"
        				});
        			} else if(e.type === 'click'){
        				$("#image_preview").remove();
        			}
        		});

        	$('.sel_all').click(function(){
        		$(this).parents('table').find('.selectable-checkbox').attr('checked', this.checked);
        	});
        	// ------ END DO NOT CHANGE ------- //

        	// ------ PLUGINS ------- //
        	// - CALENDAR
        	var date = new Date();
        	var d = date.getDate();
        	var m = date.getMonth();
        	var y = date.getFullYear();

        	if($('.calendar').length > 0){
        		$('.calendar').fullCalendar({
        			header: {
        				left: 'prev,next,today',
        				center: 'title',
        				right: 'month,agendaWeek,agendaDay'
        			},
        			buttonText:{
        				today:'跳转到今天'
        			},
        			editable: true,
        			events: array
        		});
        	}

        	// - dataTables
        	if($('.dataTable').length > 0){
        		$('.dataTable').each(function(){
        			var opt = {
        				"sPaginationType": "bootstrap",
        					"oLanguage":{
        						"sSearch": "",
        						"sLengthMenu": "Limit: _MENU_"
        					}
        			};
        			if($(this).hasClass("dataTable-noheader")){
        				opt.bFilter = false;
        				opt.bLengthChange = false;
        			}
        			if($(this).hasClass("dataTable-nofooter")){
        				opt.bInfo = false;
        				opt.bPaginate = false;
        			}
        			if($(this).hasClass("dataTable-nosort")){
        				var column = $(this).data('nosort');
        				opt.aoColumnDefs =  [
        					{ 'bSortable': false, 'aTargets': [ column ] }
        				];
        			}
        			if($(this).hasClass('dataTable-tools')){
        				opt.sDom= 'T<"clear">lfrtip';
        				opt.oTableTools = {
        					"sSwfPath": "js/tableTools/swf/copy_csv_xls_pdf.swf"
        				};
        			}
        			$(this).dataTable(opt);
        			$('.dataTables_filter input').attr("placeholder", "Search here...");
        			$('.dataTables_length select').attr("class", "uniform");
        		});
        	}
        	// - validation
        	if($('.validate').length > 0){
        		$('.validate').validate({
        			errorPlacement:function(error, element){
        					element.parents('.controls').append(error);
        			},
        			highlight: function(label) {
        				$(label).closest('.control-group').removeClass('error success').addClass('error');
        			},
        			success: function(label) {
        				label.addClass('valid').closest('.control-group').removeClass('error success').addClass('success');
        			}
        		});
        	}
        	// - wizard
        	if($(".wizard").length > 0){
        		$(".wizard").formwizard({
        				formPluginEnabled: true,
        				validationEnabled: true,
        				focusFirstInput : false,
        				validationOptions: {
        				highlight: function(label) {
        					$(label).closest('.control-group').addClass('error');
        				},
        				success: function(label) {
        					label.addClass('valid').closest('.control-group').addClass('success');
        				}
        				},
        				formOptions :{
        					success: function(){
        					},
        					beforeSubmit: function(){
        						$('#myModal').modal('show');
        					},
        				dataType: 'json',
        				resetForm: true
        			}
        		});
        	}
        	// - tooltips
        	$(".tip").tooltip();
        	// - popover
        	$(".pover").popover();
        	$(".pover").click(function(e){
        		e.preventDefault();
        		if($(this).data('trigger') === "manual"){
        			$(this).popover('toggle');
        		}
        	});
        	$(".table-has-pover").live('mouseenter', function(){
        		$('.pover').popover();
        	});
        	// - growl-like notification
        	if($('.opengrowl').length > 0){
        		$(".opengrowl").click(function(e){
        			e.preventDefault();
        			var content = $(this).data('content');
        			if($(this).hasClass("hasheader")){
        				var head = $(this).data('header');
        				$.jGrowl(content, { header: head });
        			} else {
        				$.jGrowl(content);
        			}
        		});
        	}
        	// - fancybox
        	if($('.fancy').length > 0){
        		$('.fancy').live('mouseenter',function(){
        			$('.fancy').fancybox();
        		});
        	}
        	// - quickstats
        	if($('.small-chart').length > 0){
        		$('.small-chart').each(function(){
        			var color = "#" + $(this).data('color');
        			var stroke = "#" + $(this).data('stroke');
        			var type = $(this).data('type');
        			$(this).peity(type, {
        				colour: color,
        				colours: ['#dddddd', color],
        				diameter: 32,
        				strokeColour: stroke,
        				width: 60,
        				height:32
        			});
        		});
        	}
        	// - counter
        	if($('.counter').length > 0){
        		$('.counter').each(function(){
        			var max = $(this).data('max');
        			if(!max){
        				max = 100;
        			}
        			$(this).textareaCount({
        				'maxCharacterSize': max,
        				'displayFormat' : 'Characters left: #left'
        			});
        		});
        	}
        	// - uniform
        	if($('.uniform').length > 0){
        		$('.uniform').uniform({
        			radioClass: 'uniRadio'
        		});
        	}
        	// - chosen
        	if($('.cho').length > 0){
        		$(".cho").chosen({no_results_text: "No results matched"});
        	}
        	// - cleditor
        	if($('.cleditor').length > 0){
        		$(".cleditor").cleditor({width:'auto'});
        	}
        	// - spinner
        	if($('.spinner').length > 0){
        		$('.spinner').spinner();
        	}
        	// - timepicker
        	if($('.timepicker').length > 0){
        		$('.timepicker').timepicker({
        			defaultTime: 'current',
        			minuteStep: 1,
        			disableFocus: true,
        			template: 'dropdown'
        		});
        	}
        	// - tagsinput
        	if($(".tagsinput").length > 0){
        		$('.tagsinput').tagsInput({width:'auto', height:'auto'});
        	}
        	// - plupload
        	if($('.plupload').length > 0){
        		$('.plupload').pluploadQueue({
        			runtimes : 'html5,gears,flash,silverlight,browserplus',
        			url : 'js/plupload/upload.php',
        			max_file_size : '10mb',
        			chunk_size : '1mb',
        			unique_names : true,
        			resize : {width : 320, height : 240, quality : 90},
        			filters : [
        				{title : "Image files", extensions : "jpg,gif,png"},
        				{title : "Zip files", extensions : "zip"}
        			],
        			flash_swf_url : 'js/plupload/plupload.flash.swf',
        			silverlight_xap_url : 'js/plupload/plupload.silverlight.xap'
        		});
        		$(".plupload_header").remove();
        		$(".plupload_progress_container").addClass("progress").addClass('progress-striped');
        		$(".plupload_progress_bar").addClass("bar");
        		$(".plupload_button").each(function(){
        			if($(this).hasClass("plupload_add")){
        				$(this).attr("class", 'btn btn-primary pl_add btn-small');
        			} else {
        				$(this).attr("class", 'btn btn-success pl_start btn-small');
        			}
        		});
        	}
        	// - datepicker
        	if($('.datepick').length > 0){
        		$('.datepick').datepicker();
        	}
        	// - masked inputs
        	if($('.mask_date').length > 0){
        		$(".mask_date").inputmask("9999/99/99");
        	}
        	if($('.mask_phone').length > 0){
        		$(".mask_phone").inputmask("(999) 999-9999");
        	}
        	if($('.mask_serialNumber').length > 0){
        		$(".mask_serialNumber").inputmask("9999-9999-99");
        	}
        	if($('.mask_productNumber').length > 0){
        		$(".mask_productNumber").inputmask("AAA-9999-A");
        	}
        	// - slider
        	if($('.slider').length > 0){
        		$(".slider").each(function(){
        			var orient = $(this).data('orientation');
        			var min = $(this).data('min');
        			var max = $(this).data('max');
        			var step = $(this).data('step');
        			var range = $(this).data('range');
        			var rangestart = $(this).data('rangestart');
        			var rangestop = $(this).data('rangestop');


        			if(orient === ""){
        				orient = "horizontal";
        			}

        			var el = $(this);

        			if(range !== undefined){
        				$(this).find('.slide').slider({
        					range:true,
        					values:[rangestart, rangestop],
        					orientation: orient,
        					min: min,
        					max: max,
        					step: step,
        					slide: function( event, ui ) {
        						el.parent().find('.amount').html( ui.values[0]+" - "+ui.values[1] );
        					}
        				});
        				$( this ).find('.amount').html( $(this).parent().find('.slide').slider( "values",0 )+" - "+$(this).parent().find('.slide').slider( "values",1 ) );
        			} else {
        				$(this).find('.slide').slider({
        					orientation: orient,
        					min: min,
        					max: max,
        					step: step,
        					slide: function( event, ui ) {
        						el.parent().find('.amount').html( ui.value );
        					}
        				});
        				$( this ).find('.amount').html( $(this).parent().find('.slide').slider( "value" ) );
        			}

        		});
        	}
        }
	});
}
