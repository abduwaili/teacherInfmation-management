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
    var isTeacher;
    var c = document.getElementById('closeSignIn');

    if (checkTeacher[1].checked) {
        isTeacher = checkTeacher[1].value;
    } else {
        isTeacher = checkTeacher[0].value;
    }
    if (username.replace(/(^s*)|(s*$)/g, "").length == 0 || password.replace(/(^s*)|(s*$)/g, "").length == 0 || isTeacher.replace(/(^s*)|(s*$)/g, "").length == 0) {
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
    var isTeacher;
    if (checkTeacher[1].checked) {
        isTeacher = checkTeacher[1].value;
        majorOrPosition = $("input[name=majorUp]").val();
    } else {
    	majorOrPosition = $("input[name=positionUp]").val();
        isTeacher = checkTeacher[0].value;
    }
    if (username.replace(/(^s*)|(s*$)/g, "").length == 0 || password.replace(/(^s*)|(s*$)/g, "").length == 0 || confirmPassword.replace(/(^s*)|(s*$)/g, "").length == 0 || college.replace(/(^s*)|(s*$)/g, "").length == 0 || majorOrPosition.replace(/(^s*)|(s*$)/g, "").length == 0 || phone.replace(/(^s*)|(s*$)/g, "").length == 0 || realName.replace(/(^s*)|(s*$)/g, "").length == 0) {
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
function addSchedule(time){
    var index = "input[name=" + time + "]";
    var schedule = $(index).val();
    var c = document.getElementById(time);
    var btnId = time + "Button";
    var btn = document.getElementById(btnId);
    if (schedule.replace(/(^s*)|(s*$)/g, "").length == 0) {
        c.click();
        var onlyChoseAlert = simpleAlert({
            "content": "请输入日程信息",
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
            time: time,
            schedule: schedule
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
                   
                    btn.innerHTML = "已增加";
                    btn.style.background = "pink";
                    btn.disabled = true;
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
function changeLoginStudentInf(){
    var name = $("input[name=loginName]").val();
    var phone= $("input[name=loginPhone]").val();
    var major = $("input[name=loginMajor]").val();
    var college = $("input[name=loginCollege]").val();
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
        url: "/xaingmu/changeLoginStudentInf",
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
function changeLoginTeacherInf(){
    var name = $("input[name=loginName]").val();
    var phone= $("input[name=loginPhone]").val();
    var position = $("input[name=loginPosition]").val();
    var college = $("input[name=loginCollege]").val();
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
        url: "/xaingmu/changeLoginStudentInf",
        data: {
            name: name,
            position: position,
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
            name: name,
            position: position,
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
