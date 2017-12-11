<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.struts2.action.*"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>
            教师信息管理系统
        </title>
        <meta content="text/html; charset=utf-8" http-equiv="content-type"/>
        <meta name="viewport" content="width=device-width"/>
        <meta content="教师信息管理系统" name="description"/>
        <meta content="教师, 信息, 管理" name="keywords"/>
        <link href="css/normalize.css" rel="stylesheet" type="text/css"/>
        <link href="css/login_demo.css" rel="stylesheet" type="text/css"/>
        <link href="css/component.css" rel="stylesheet" type="text/css"/>
        <link href="css/content.css" rel="stylesheet" type="text/css"/>
        <link href="css/demo.css" rel="stylesheet" type="text/css"/>
        <link href="css/elastislide.css" rel="stylesheet" type="text/css"/>
        <link href="css/custom.css" rel="stylesheet" type="text/css"/>
        <link href="css/ns-default.css" rel="stylesheet" type="text/css"/>
        <link href="css/ns-style-attached.css" rel="stylesheet" type="text/css"/>
        <link href="css/simpleAlert.css" rel="stylesheet"/>
		<link rel="stylesheet" href="css/fullcalendar.css"/>
		<link rel="stylesheet" href="css/fullcalendar.print.css" media='print'/>
		<link rel="stylesheet" href="css/style_c.css"/>
            <script src="js/simpleAlert.js">
            </script>
            <script src="js/modernizr.custom.js">
            </script>
            <script src="js/jquery-3.2.1.js">
            </script>
            <script src="js/jquery-3.2.1.min.js">
            </script>
            <script src="js/config.js">
            </script>
            <script src="js/skel.min.js">
            </script>
            <script src="js/modernizr.custom.17475.js">
            </script>
            <script src="js/views.js">
            </script>
            <script src="js/jquery.min.js" type="text/javascript">
            </script>
			<script src="js/fullcalendar.min.js">
			</script>
			<script src="js/custom.js">
			</script>
			<script src="js/date.js">
			</script>
			
    </head>
    <body>
        <script>
            $(document).ready(function(){
            var isLogin= '<%=session.getAttribute("isLogin")%>';
            var isTeacher = '<%=session.getAttribute("isTeacher")%>';
            var student = document.getElementById("student");
            var teacher = document.getElementById("teacher");
            var noLogin = document.getElementById("Login");
            if(isLogin == "true"){
                noLogin.style.display = "none";
                if(isTeacher == "true"){
                    teacher.style.display = "block";
                }
                if(isTeacher == "false"){
                    student.style.display = "block";
                }
            }
            chooseTeacher();
            
        })
        </script>
        <nav id="nav">
            <ul>
                <li>
                    <a href="#top">
                        用戶首頁
                    </a>
                </li>
                <li>
                    <a href="#display">
                        校園风采展示
                    </a>
                </li>
                <li>
                    <a href="#portfolio">
                        今日教师推荐
                    </a>
                </li>
                <li>
                    <a href="#contact">
                        联系我们
                    </a>
                </li>
            </ul>
        </nav>
        <% Teacher teacher = (Teacher)session.getAttribute("teacher");
           Student student = (Student)session.getAttribute("student");
           ArrayList<Arrange> studentOrder = null;
           ArrayList<Arrange> teacherOrder = null;
           ArrayList<Schedule> teacherSchedule=null;
           studentOrder = (ArrayList<Arrange>)session.getAttribute("studentOrder");
           teacherOrder = (ArrayList<Arrange>)session.getAttribute("teacherOrder");
           if(teacher == null){
        	   teacher = new Teacher();
           }
           if(student == null){
        	   student = new Student();
           }
		   if(teacherOrder == null){
			   teacherOrder = new ArrayList<Arrange>();
		   }
		   if(studentOrder == null){
			   studentOrder = new ArrayList<Arrange>();
		   }
		   
        %>


        <div class="wrapper wrapper-style1 wrapper-first" id="teacher" style="display:none">
		<article class="container" id="top">
			<div class="row">
				<!--个人图片-->
				<div class="4u">
					<span class="me image image-full"> <img
						onmousedown="changeImg()"
						src=<%= "'" + teacher.getPicture()+ "'" %> />
					</span>
				</div>
				<!--个人信息-->
				<div class="8u">
					<form method="POST">
						<div class="row first-child">
							<div class="6u">
								<label for="teacherName"> 姓名 </label> <input id="teacherName"
									name="teacherName" placeholder="姓名"
									value=<%="'"+teacher.getTeacherName()+"'"%>
									style="background-color: #EEE" type="text" />
							</div>
							<div class="6u">
								<label for="teacherCollege"> 學院 </label> <input
									id="teacherCollege" name="teacherCollege" placeholder="学院"
									value=<%="'"+teacher.getCollege()+"'"%>
									style="background-color: #EEE" type="text" />
							</div>
						</div>
						<div class="row half">
							<div class="6u">
								<label for="teacherPosition"> 职称 </label> <input
									id="teacherPosition" name="teacherPositon" placeholder="职称"
									value=<%="'"+teacher.getPosition()+"'"%>
									style="background-color: #EEE" type="text" />
							</div>
							<div class="6u">
								<label for="teacherPhone"> 联系方式 </label> <input
									id="teacherPhone" name="teacherPhone"
									value=<%="'"+teacher.getPhone()+"'"%> placeholder="联系方式"
									style="background-color: #EEE" type="text" />
							</div>
						</div>
						<div class="row half">
							<div class="6u">
								<label for="teacherMoney"> 基金 </label> <input id="teacherMoney"
									name="teacherMoney" placeholder="职称"
									value=<%="'"+teacher.getMoney()+"'"%>
									style="background-color: #EEE" type="text" />
							</div>
							<div class="6u">
								<label for="teacherFSRA"> 科研成果 </label> <input id="teacherFSRA"
									name="teacherFRSA" value=<%="'"+teacher.getFSRA()+"'"%>
									placeholder="联系方式" style="background-color: #EEE" type="text" />
							</div>
						</div>
						</form>
				</div>
			</div>
			<div class="mockup-content"
				style="background-color: white; padding-bottom: 4em;">
				<div
					class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed">
					<button class="button" style="background: white" id="showSchedule"
						onclick="showSchedule()">显示日程</button>
				</div>
				<div
					class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed">
					<button class="button" style="background: white" type="button">
						增加日程</button>
					<div class="morph-content" style="overflow-y: scroll">
						<div>
							<div class="content-style-form content-style-form-1">
								<span class="icon icon-close" id="closeAddSchedule">
									Close the dialog </span>
								<h2 style="color: skyblue">
									<input name="isAddWatch" type="radio" value="true"> 可见
									<input name="isAddWatch" type="radio" value="false">
									不可见
								</h2>
								<form method="POST">
									<p>
										<label> 日程日期 </label> <input type="date" required
											name="scheduleDate">
									</p>
									<p>
										<label> 开始时间(默认00:00) </label> <input type="time" required
											name="addScheduleStart">
									</p>
									<p>
										<label> 结束时间(默认24:00) </label> <input type="time" required
											name="addScheduleEnd">
									</p>
									<p>
										<label> 内容 </label> <input type="text" required
											name="addScheduleContent">
									</p>
									<p>
										<button onclick="addSchedule()" type="submit">增加</button>
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- morph-button -->

				<div
					class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed">
					<button class="button" style="background: white" type="button">
						修改日程</button>
					<div class="morph-content" style="overflow-y: scroll">
						<div>
							<div class="content-style-form content-style-form-1">
								<span class="icon icon-close" id="closeChangeSchedule">
									Close the dialog </span>
								<h2 style="color: skyblue">
									请选择修改日程
								</h2>
								<form method="POST">
									<p>
										<label> 日程日期 </label> <input type="date" required
											name="changeScheduleDate">
									</p>

									<p>
										<label> 开始时间(默认00:00) </label> <input type="time" required
											name="changeScheduleStart">
									</p>
									<p>
										<label> 结束时间(默认24:00) </label> <input type="time" required
											name="changeScheduleEnd">
									</p>
									<p>
										<label> 日程内容 </label> <input type="text" required
											name="changeScheduleContent">
									</p>
									<p>
										<button onclick="changeSchedule()" type="submit">查询日程是否存在</button>
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div
					class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed">
					<button class="button" style="background: white" type="button">
						删除日程</button>
					<div class="morph-content" style="overflow-y: scroll">
						<div>
							<div class="content-style-form content-style-form-1">
								<span class="icon icon-close" id="closeDeleteSchedule">
									Close the dialog </span>
								<h2 style="color: skyblue">删除日程</h2>
								<form method="POST">
									<p>
										<label> 选择删除日程日期 </label> <input type="date" required
											name="deleteScheduleDate">
									</p>
									<p>
										<label> 开始时间(默认00:00) </label> <input type="time" required
											name="deleteScheduleStart">
									</p>
									<p>
										<label> 结束时间(默认24:00) </label> <input type="time" required
											name="deleteScheduleEnd">
									</p>
									<p>
										<label> 内容(默认选择所有相应日期日程) </label> <input type="text" required
											name="deleteScheduleCotent">
									</p>
									<p>
										<button onclick="deleteSchedule()" type="submit">删除</button>
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div
					class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed">
					<button class="button" style="background: white" type="button" onclick="upLoad()">
						更改图片</button>
					
						</div>
				<!-- morph-button -->
				<div
					class="morph-button morph-button-modal morph-button-modal-3 morph-button-fixed">
					<button class="button" style="background: white" type="button" onclick="changeTeacherInf()">
						更改信息</button>
				</div>

				<div
					class="morph-button morph-button-modal morph-button-modal-3 morph-button-fixed">
					<button class="button" style="background: white" type="button"
						onclick="signOut()">退出登录</button>
				</div>
			</div>
			<div class="box">
				<div class="box-head">
					<h3>CARLENDAR</h3>
				</div>
				<div class="box-content box-nomargin">
					<div class="calendar"></div>
				</div>
			</div>
			<div
					class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed">
					<button class="button" style="background: white;z-index:-999" type="button" id="fchange">
						修改日程</button>
					<div class="morph-content" style="overflow-y: scroll">
						<div>
							<div class="content-style-form content-style-form-1">
								<span class="icon icon-close" id="fcloseChangeSchedule">
									Close the dialog </span>
									<h2 style="color: skyblue">
									将此日期修改为
								</h2>
								<h2 style="color: skyblue">
									<input name="fisChangeWatch" type="radio" value="true">
									可见 <input name="fisChangeWatch" type="radio" value="false">
									不可见
								</h2>
								<form method="POST">
									<p>
										<label> 日程日期 </label> <input type="date" required
											name="fchangeScheduleDate">
									</p>

									<p>
										<label> 开始时间 </label> <input type="time" required
											name="fchangeScheduleStart">
									</p>
									<p>
										<label> 结束时间 </label> <input type="time" required
											name="fchangeScheduleEnd">
									</p>
									<p>
										<label> 日程内容 </label> <input type="text" required
											name="fchangeScheduleContent">
									</p>
									<p>
										<button onclick="fchangeSchedule()" type="submit">更改</button>
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
			<table >
                    <thead>
                        <tr>
                            <th>
                                预约时间
                            </th>
                            <th>
                                预约事由
                            </th>
                            <th>
                                学生姓名
                            </th>
                            <th>
                                学生专业
                            </th>
                            <th>
                                学生电话
                            </th>
                            <th>
                                同意预约
                            </th>
                            <th>
                                拒绝预约
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                    <%for(Arrange ele : teacherOrder){%>
                        <tr>
                            <td>
                                <%=ele.getArrangeTime()%>
                            </td>
                            <td>
                                <%=ele.getArrangeReason()%>
                            </td>
                            <td>
                                <%=ele.getStudentName() %>
                            </td>
                            <td>
                                <%=ele.getStudentMajor() %>
                            </td>
                            <td>
                                <%=ele.getStudentPhone() %>
                            </td>
                            <td>
                                <button onclick="teacherAgreeOrder($(this).val())" style="height:100%;width:100%;border:0;background-color:white;" value=<%="'"+ele.getArrangeId()+"'"%>>
                                    同意预约
                                </button>
                            </td>
                            <td>

                                <button onclick="teacherCancelOrder($(this).val())" style="height:100%;width:100%;border:0;background-color:white;" value=<%="'"+ele.getArrangeId()+"'"%>>
                                    拒绝预约
                                </button>
                            </td>
                            <%}%>
                        </tr>
                    </tbody>
                </table>

		</article>
	</div>
        <div class="wrapper wrapper-style1 wrapper-first" id="student" style="display:none">
            <article class="container" id="top">
                <div class="row">
                    <!--个人图片-->
                    <div class="4u">
                        <span class="me image image-full">
                            <img src=<%="'"+student.getPicture()+"'"%>/>
                        </span>
                    </div>
                    <!--个人信息-->
                    <div class="8u">
                        <form method="POST">
                            <div class="row first-child">
                                <div class="6u">
                                    <label for="studentName">
                                        姓名
                                    </label>
                                    <input id="studentName" name="studentName" value=<%="'"+student.getStudentName()+"'"%> placeholder="姓名" style="background-color:#EEE" type="text"/>
                                </div>
                                <div class="6u">
                                    <label for="studentCollege">

                                        學院
                                    </label>
                                    <input id="studentCollege" name="studentCollege" value=<%="'"+student.getCollege()+"'"%>  placeholder="学院" style="background-color:#EEE" type="text"/>
                                </div>
                            </div>
                            <div class="row half">
                                <div class="6u">
                                    <label for="studentMajor">
                                        專業
                                    </label>
                                    <input id="studentMajor" name="studentMajor" value=<%="'"+student.getMajor()+"'"%> placeholder="专业" style="background-color:#EEE" type="text"/>
                                </div>
                                <div class="6u">
                                    <label for="studentTel">
                                        联系方式
                                    </label>
                                    <input id="studentTel" name="studentTel" value=<%="'"+student.getPhone()+"'"%> placeholder="联系方式" style="background-color:#EEE" type="text"/>
                                </div>
                              </div>
                        </form>
                    </div>
                    </div>
                  <div class="mockup-content"
				style="background-color: white; padding-bottom: 4em;">
                <div
					class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed">
					<button class="button" style="background: white" type="button">
						添加预约</button>
					<div class="morph-content" style="overflow-y: scroll">
						<div>
							<div class="content-style-form content-style-form-1">
								<span class="icon icon-close" id="closeArrange">
									Close the dialog </span>
								<h2 style="color: skyblue">
									添加教师预约
								</h2>
								<form method="POST">
									<p>
										<label> 日程日期 </label> <input type="date" required
											name="arrangeDate">
									</p>
									<p>
										<label> 开始时间(默认00:00) </label> <input type="time" required
											name="arrangeStart">
									</p>
									<p>
										<label> 结束时间(默认24:00) </label> <input type="time" required
											name="arrangeEnd">
									</p>
									<p>
										<label> 预约教师姓名 </label> <input type="text" required
											name="arrangeTeacher">
									</p>
									<p>
										<label> 预约事由 </label> <input type="text" required
											name="arrangeContent">
									</p>
									<p>
										<button onclick="addOrder()" type="submit">增加</button>
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div
					class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed">
					<button class="button" style="background: white" type="button" onclick="upLoad()">
						更改图片</button>
					
						</div>
                    <div class="morph-button morph-button-modal morph-button-modal-3 morph-button-fixed">
                    <button class="button" style="background:white" type="button" onclick="changeStudentInf()">
                        更改信息
                    </button>
                </div>
               
                <div class="morph-button morph-button-modal morph-button-modal-3 morph-button-fixed">
                    <button class="button" style="background:white" type="button" onclick="signOut()">
                       退出登录
                    </button>
                </div>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>
                                预约时间
                            </th>
                            <th>
                                教师
                            </th>
                            <th>
                                审核状态
                            </th>
                            <th>
                                取消
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                            <%for(Arrange ele : studentOrder){%>
                        <tr>
                            <td>
                                <%=ele.getArrangeTime()%>
                            </td>
                            <td>
                                <%=ele.getTeacherName() %>
                            </td>
                            <%if (ele.getSuccessFail().equals("false") ){%>
                            <td>
                                审核中
                            </td>
                            <td>
                                <button type="button" onclick="cancelOrder($(this).val())" style="height:100%;width:100%;border:0;background-color:white;" id = <%="'"+ele.getArrangeId()+"'"%> value=<%="'"+ele.getArrangeId()+"'"%>>
                                    取消预约
                                </button>
                            </td>
                            <%}else if(ele.getSuccessFail().equals("Success")){%>
                                                        <td>
                                教师已通过
                            </td>
                            <td>
                                <button type="button" onclick="cancelOrder($(this).val())" style="height:100%;width:100%;border:0;background-color:white;" id = <%="'"+ele.getArrangeId()+"'"%> value=<%="'"+ele.getArrangeId()+"'"%>>
                                    不再显示
                                </button>
                            </td>
                            <%}else{ %>
                                 <td>
                                教师已拒绝
                            </td>
                            <td>
                                <button type="button" onclick="cancelOrder($(this).val())" style="height:100%;width:100%;border:0;background-color:white;" id = <%="'"+ele.getArrangeId()+"'"%> value=<%="'"+ele.getArrangeId()+"'"%>>
                                    不再显示
                                </button>
                            </td>
                            <%} %>
                            
                             </tr>
                            <%}%>
                    </tbody>
                </table>
            </article>
        </div>
        <!-- Home -->
        <div id="Login" style="display:block">
            <div class="wrapper wrapper-style1 wrapper-first">
                <article class="container" id="top">
                    <div class="row">
                        <div class="4u">
                            <span class="me image image-full">
                                <img alt="" src="images/me.jpg"/>
                            </span>
                        </div>
                        <div class="8u" style="padding-top:4em">
                            <header>
                                <h1>
                                    Hi~欢迎
                                    <strong>
                                        请先登录
                                    </strong>
                                    .
                                </h1>
                            </header>
                            <p>
                                这里是
                                <strong>
                                    教师信息管理系统
                                </strong>
                                可在本系统进行浏览教师信息，登录后进行个人信息管理
                            </p>
                        </div>
                    </div>
                </article>
            </div>
           <div class="mockup-content" style="background-color:white; padding-bottom: 4em;">
                 <div class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed">
                    <button class="button button-big" type="button">
                        Login.
                    </button>
                    <div class="morph-content" style="overflow-y:scroll">
                        <div>
                            <div class="content-style-form content-style-form-1">
                                <span class="icon icon-close" id="closeSignIn">
                                    Close the dialog
                                </span>
                                <h2 style="color: skyblue">
                                    <input name="isTeacherIn" type="radio" value="1">
                                        教师
                                        <input name="isTeacherIn" type="radio" value="0">
                                            学生
                                </h2>
                                <form method="POST">
                                    <p>
                                    <label>
                                        用户名
                                    </label>
                                    <input autofocus="autofocus" name="accountIn" required type="text">
                                    </p>
                                        <p>
                                            <label>
                                                密码
                                            </label>
                                            <input name="passwordIn" required type="password">

                                        </p>
                                        <p>
                                            <button onclick="signInCheck()" type="submit">
                                                Login
                                            </button>
                                        </p>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- morph-button -->
                <strong class="joiner">
                    or
                </strong>
                <div class="morph-button morph-button-modal morph-button-modal-3 morph-button-fixed">
                    <button class="button button-big" type="button">
                        Signup
                    </button>
                    <div class="morph-content" style="overflow-y:scroll;">
                        <div>
                            <div class="content-style-form content-style-form-2">
                                <span class="icon icon-close" id="closeSignUp">
                                    Close the dialog
                                </span>
                                <h2 style="color: skyblue">
                                    <input name="isTeacherUp" type="radio" value="1">
                                        教师
                                    <input name="isTeacherUp" type="radio" value="0">
                                            学生
                                </h2>
                                <form method="POST">
                                    <p>
                                        <label>
                                            用户名
                                        </label>
                                        <input autofocus="autofocus" name="accountUp" required type="text"/>
                                    </p>
                                 	
                                    <p>
                                        <label>
                                            密码
                                        </label>
                                        <input name="passwordUp" required type="password"/>
                                    </p>
                                    <p>
                                        <label>
                                            确认密码
                                        </label>
                                        <input name="passwordConfirm" required type="password"/>
                                    </p>
                                    <p>
                                        <label>
                                            真实姓名
                                        </label>
                                        <input name="realNameUp" required type="text"/>
                                    </p>
                                    <p>
                                        <label>
                                            学院
                                        </label>
                                        <input name="collegeUp" required type="text"/>
                                    </p>
                                    <p>
                                        <label>
                                            专业(学生填写)
                                        </label>
                                        <input name="majorUp"  type="text"/>
                                    </p>
                                    <p>
                                   		<label>
                                            职称(教师填写)
                                        </label>
                                        <input name="positionUp" type="text"/>
                                    </p>
                                    <p>
                                        <label>
                                            联系方式
                                        </label>
                                        <input name="telUp" required type="text"/>
                                    </p>
                                    <p>
                                        <button  onclick="signUpCheck()" type="submit">
                                            Sign Up
                                        </button>
                                    </p>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- morph-button -->
        </div>
       </div>
        <div class="container demo-1">
            <article id="display">
                <div class="main">
                    <header class="clearfix">
                        <h1>
                            风采展示
                            <span style="padding-top:1em">
                                規格嚴格      功夫到家
                            </span>
                        </h1>
                    </header>
                    <!-- Elastislide Carousel -->
                    <ul class="elastislide-list" id="carousel">
                        <li>
                            <a href="http://news.hit.edu.cn/d1/2a/c1510a184618/page.htm">
                                <img alt="image01" src="images/display/1.jpg"/>
                            </a>
                        </li>
                        <li>
                            <a href="http://news.hit.edu.cn/ce/7d/c1510a183933/page.htm">
                                <img alt="image02" src="images/display/2.jpg"/>
                            </a>
                        </li>
                        <li>
                            <a href="http://news.hit.edu.cn/ce/ff/c1510a184063/page.htm">
                                <img alt="image03" src="images/display/3.jpg"/>
                            </a>
                        </li>
                        <li>
                            <a href="http://news.hit.edu.cn/d6/d5/c1510a186069/page.htm">
                                <img alt="image04" src="images/display/4.jpg"/>
                            </a>
                        </li>
                    </ul>
                    <script src="js/jquerypp.custom.js" type="text/javascript">
                    </script>
                    <script src="js/jquery.elastislide.js" type="text/javascript">
                    </script>
                    <script type="text/javascript">
                        $( '#carousel' ).elastislide();
                    </script>
                    <div class="row" style="padding-top:6em">
                        <div class="12u">
                            <form method="POST">
                                <div>
                                    <div class="row half">
                                        <div class="12u">
                                            <input name="searchTearcherName" placeholder="请输入搜索教师的名字" style="background-color:white" type="text"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="12u" style="text-align:center">
                                            <button type="submit" class="button" onclick="search()">
                                                搜索
                                            </button>
                                            <button class="button button-alt form-button-reset">
                                                清空
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </article>
        </div>
        <div id="showSearchTeacher" style="display:none">

        </div>
        <!-- Portfolio -->
        <div class="wrapper wrapper-style3">
            <article id="portfolio">
                <header>
                    <h2 style="font-size: 4em ">
                        今日推荐教师
                    </h2>
                    <span>
                        师者， 所以传道授业解惑也
                    </span>
                </header>
                <div class="container">
                    <div class="row">
                        <div class="12u">
                        </div>
                    </div>
                    <div class="row" id="chooseTeacher">
                        <div class="4u" name="t1">
                            <article class="box box-style2">
                                <a class="image image-full" name="image">
                                    <img alt="" src="images/me.jpg"/>
                                </a>
                                <h3>
                                    <a>
                                        张永顺
                                    </a>
                                </h3>
                                <p>
                                    计算机学院 学委
                                </p>
                            </article>
                        </div>
                        <div class="4u" name="t2">
                            <article class="box box-style2">
                                <a class="image image-full">
                                    <img alt="" src="images/me.jpg"/>
                                </a>
                                <h3>
                                    <a>
                                        张永顺
                                    </a>
                                </h3>
                                <p>
                                    计算机学院 学委
                                </p>
                            </article>
                        </div>
                        <div class="4u" name="t3">
                            <article class="box box-style2">
                                <a class="image image-full">
                                    <img alt="" src="images/me.jpg"/>
                                </a>
                                <h3>
                                    <a>
                                        张永顺
                                    </a>
                                </h3>
                                <p>
                                    计算机学院 学委
                                </p>
                            </article>
                        </div>
                    </div>
                </div>
                <footer>
                    <button class="button button-big" onclick="chooseTeacher()">
                        换一批
                    </button>
                </footer>
            </article>
        </div>

        <!-- Contact -->
        <div class="wrapper wrapper-style4">
            <article id="contact">
                <header>
                    <h2>
                        請留下您的寶貴意見~
                    </h2>
                </header>
                <div>
                    <div class="row">
                        <div class="12u">
                            <form action="#" method="post">
                                <div>
                                    <div class="row half">
                                        <div class="6u">
                                            <input id="name" name="name" placeholder="Name" type="text"/>
                                        </div>
                                        <div class="6u">
                                            <input id="email" name="email" placeholder="Email" type="text"/>
                                        </div>
                                    </div>
                                    <div class="row half">
                                        <div class="12u">
                                            <input id="subject" name="subject" placeholder="Subject" type="text"/>
                                        </div>
                                    </div>
                                    <div class="row half">
                                        <div class="12u">
                                            <textarea id="message" name="message" placeholder="Message">
                                            </textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="12u">
                            <!--                 <a class="button form-button-submit" href="#">
                                                提交
                                            </a> -->
                                            <button onclick="FeedBack()"   class="button form-button-submit">提交</button>
                           <!--                  <a class="button button-alt form-button-reset" href="#">
                                                清空
                                            </a> -->
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <footer>
                    <p id="copyright">
                        © Copyright © 2013.Company name All rights reserved.
                    </p>
                </footer>
            </article>
        </div>
        <script src="js/classie.js">
        </script>
        <script src="js/uiMorphingButton_fixed.js">
        </script>
        <script>
            (function() {
                var docElem = window.document.documentElement, didScroll, scrollPosition;

                // trick to prevent scrolling when opening/closing button
                function noScrollFn() {
                    window.scrollTo( scrollPosition ? scrollPosition.x : 0, scrollPosition ? scrollPosition.y : 0 );
                }

                function noScroll() {
                    window.removeEventListener( 'scroll', scrollHandler );
                    window.addEventListener( 'scroll', noScrollFn );
                }

                function scrollFn() {
                    window.addEventListener( 'scroll', scrollHandler );
                }

                function canScroll() {
                    window.removeEventListener( 'scroll', noScrollFn );
                    scrollFn();
                }

                function scrollHandler() {
                    if( !didScroll ) {
                        didScroll = true;
                        setTimeout( function() { scrollPage(); }, 60 );
                    }
                };

                function scrollPage() {
                    scrollPosition = { x : window.pageXOffset || docElem.scrollLeft, y : window.pageYOffset || docElem.scrollTop };
                    didScroll = false;
                };

                scrollFn();

                [].slice.call( document.querySelectorAll( '.morph-button' ) ).forEach( function( bttn ) {
                    new UIMorphingButton( bttn, {
                        closeEl : '.icon-close',
                        onBeforeOpen : function() {
                           
                        },
                        onAfterOpen : function() {
                           
                        },
                        onBeforeClose : function() {
                           
                        },
                        onAfterClose : function() {
                           
                        }
                    } );
                } );

                // for demo purposes only
                [].slice.call( document.querySelectorAll( 'form button' ) ).forEach( function( bttn ) {
                    bttn.addEventListener( 'click', function( ev ) { ev.preventDefault(); } );
                } );
            })();
        </script>
        

    </body>
</html>
