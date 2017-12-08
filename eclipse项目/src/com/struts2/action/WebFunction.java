package com.struts2.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

import java.sql.PreparedStatement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import database.DatabaseConnection;
import net.sf.json.JSONObject;

public class WebFunction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 3124965708023366083L;

	private HttpServletRequest request;
	private String result;

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	Map<String, Object> ret = new HashMap<String, Object>();
	Map<String, Object> session;

	private String TeacherAccount = "";

	/**
	 * 鎸夊鍚嶆悳鎼滄暀甯�
	 */
	public String SearchTeacher() {
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		String teacherName = request.getParameter("teacherName");
		Connection conn;
		Statement stmt;
		ResultSet rs;
		Connection conn_0;
		Statement stmt_0;
		ResultSet rs_0;

		try {
			conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select *from teacher where TeacherName='" + teacherName + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				ret.put("searchTeacherName", rs.getString("TeacherName"));
				ret.put("searchTeacherMoney", rs.getString("Money"));
				ret.put("searchTeacherFSRA", rs.getString("FSRA"));
				ret.put("searchTeacherCollege", rs.getString("College"));
				ret.put("searchTeacherPosition", rs.getString("Position"));
				ret.put("searchTeacherImage", rs.getString("Picture"));
				ret.put("searchTeacherPhone", rs.getString("Phone"));
				ret.put("searchTeacherBaiKe", rs.getString("BaiKe"));
				TeacherAccount = rs.getString("TeacherAccount");
				conn_0 = DatabaseConnection.getConnection();
				stmt_0 = conn_0.createStatement();
				session.put("searchTeacherId", TeacherAccount);
				ret.put("ret", "Success");
			} else {
				ret.put("ret", "NotFound");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		JSONObject json = JSONObject.fromObject(ret);
		
		result = json.toString();
		
		return SUCCESS;
	}

	public String addOrder() {
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String schedule = request.getParameter("schedule");
		String teacher = request.getParameter("teacher");
		String[] dateArray = date.split("-");
        String[] startArray = start.split(":");
        String[] endArray = end.split(":");
        String year = dateArray[0];
        String month = dateArray[1];
        String day = dateArray[2];
        String allDay = "false";
        String startHour = null;
        String startMinutes = null;
        String endHour = null;
        String endMinutes = null;
        
        if("".equals(start)) {
        	if("".equals(end)) {
        		allDay = "true";
        	}else {
        		startHour = "0";
        		startMinutes = "0";
        		endHour = endArray[0];
        		endMinutes = endArray[1];
        	}
        }else {
        	startHour = startArray[0];
    		startMinutes = startArray[1];
        	if("".equals(end)) {
        		endHour = "0";
        		endMinutes = "0";
        	}else {
        		endHour = endArray[0];
        		endMinutes = endArray[1];
        	}
        }
		Connection conn;
		Statement stmt;
		ResultSet rs;
		Connection conn_0;
		try {
			
			conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select *from teacher where TeacherName='" + teacher + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				
				TeacherAccount = rs.getString("TeacherAccount");		
				
				Student student = (Student) session.get("student");// 获取学生信息
				 
				conn_0 = DatabaseConnection.getConnection();
				String sql1_0 = "insert into arrange(EndMinutes,TeacherAccount,StudentAccount,AllDay,ArrangeReason,commitornot,SuccessFail,StudentName,StudentMajor,StudentPhone,ArrangeYear,ArrangeMonth,ArrangeDay,StartHour,StartMinutes,EndHour,TeacherName)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps_0 = (PreparedStatement) conn_0.prepareStatement(sql1_0);
				ps_0.setString(1, endMinutes);
				ps_0.setString(2, TeacherAccount);
				ps_0.setString(3, student.getStudentAccount());
				ps_0.setString(4, allDay);
				ps_0.setString(5, schedule);
				ps_0.setString(6, "true");
				ps_0.setString(7, "false");
				ps_0.setString(8, student.getStudentName());
				ps_0.setString(9, student.getMajor());
				ps_0.setString(10, student.getPhone()); 
				ps_0.setString(11, year);
				ps_0.setString(12, month);
				ps_0.setString(13, day);
				ps_0.setString(14, startHour);
				ps_0.setString(15, startMinutes);
				ps_0.setString(16, endHour);
				ps_0.setString(17, teacher);
				
				ps_0.executeUpdate();
				
				Connection conn_1;
				Statement stmt_1;
				ResultSet rs_1;
				conn_1 = DatabaseConnection.getConnection();
				stmt_1 = conn_1.createStatement();
				String sql_1 = "select *from arrange where StudentAccount='" + student.getStudentAccount() + "'";
				rs_1 = stmt_1.executeQuery(sql_1);
				ArrayList<Arrange> L = new ArrayList<Arrange>();
				while (rs_1.next()) {
					Arrange temp = new Arrange();
					temp.setTeacherAccount(rs_1.getString("TeacherAccount"));
					temp.setTeacherName(rs_1.getString("TeacherName"));
					temp.setStudentAccount(rs_1.getString("StudentAccount"));
					temp.setArrangeTime(rs_1.getString("AllDay"), rs_1.getString("ArrangeYear"),
							rs_1.getString("ArrangeMonth"), rs_1.getString("ArrangeDay"), rs_1.getString("StartHour"),
							rs_1.getString("StartMinutes"), rs_1.getString("EndHour"), rs_1.getString("EndMinutes"));
					temp.setArrangeReason(rs_1.getString("ArrangeReason"));
					temp.setSuccessFail(rs_1.getString("SuccessFail"));
					temp.setArrangeId(rs_1.getInt("ArrangeId"));
					L.add(temp);
				}
				session.put("studentOrder", L);

				ret.put("ret", "Success");
			} else {
				ret.put("ret", "NoTeacher");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS;
	}

	/**********
	 * 瀛︾敓鍙栨秷棰勭害
	 **********/
	public String cancelOrder() {
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		int ArrangeId = Integer.parseInt(request.getParameter("id"));
		Connection conn;
		try {
			Connection conn1;
			Statement stmt1;
			ResultSet rs1;
			conn1 = DatabaseConnection.getConnection();
			stmt1 = conn1.createStatement();
			String sql1 = "select *from arrange where ArrangeId='" + ArrangeId + "'";
			rs1 = stmt1.executeQuery(sql1);
			while (rs1.next()) {
				String flag = rs1.getString("SuccessFail");
				conn = DatabaseConnection.getConnection();
				String sql = " delete from arrange where ArrangeId=?";
				PreparedStatement pStatement = conn.prepareStatement(sql);
				pStatement.setInt(1, ArrangeId);
				pStatement.executeUpdate();

				@SuppressWarnings("unchecked")
				ArrayList<Arrange> studentOrder = (ArrayList<Arrange>) session.get("studentOrder");
				for (int i = 0; i < studentOrder.size(); i++) {
					if (Integer.parseInt(studentOrder.get(i).getArrangeId()) == ArrangeId) {
						studentOrder.remove(i);
						continue;
					}
				}

				session.put("studentOrder", studentOrder);

				if (flag.equals("false")) {
					ret.put("ret", "Success");
				} else {
					ret.put("ret", "SuccessOther");
				}
				JSONObject json = JSONObject.fromObject(ret);
				result = json.toString();
				return SUCCESS;
			}
			ret.put("ret", "Fail");
			JSONObject json = JSONObject.fromObject(ret);
			result = json.toString();
			return SUCCESS;
		} catch (Exception e) {

		}
		ret.put("ret", "Fail");
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS;
	}
	public String changeSchedule() {
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String schedule = request.getParameter("schedule");
		String TeacherAccount = (String) session.get("TeacherAccount");
		String[] dateArray = date.split("-");
        String[] startArray = start.split(":");
        String[] endArray = end.split(":");
        String year = dateArray[0];
        String month = dateArray[1];
        String day = dateArray[2];
        String allDay = "false";
        String startHour = null;
        String startMinutes = null;
        String endHour = null;
        String endMinutes = null;
        if("".equals(start)) {
        	if("".equals(end)) {
        		allDay = "true";
        	}else {
        		startHour = "0";
        		startMinutes = "0";
        		endHour = endArray[0];
        		endMinutes = endArray[1];
        	}
        }else {
        	startHour = startArray[0];
    		startMinutes = startArray[1];
        	if("".equals(end)) {
        		endHour = "0";
        		endMinutes = "0";
        	}else {
        		endHour = endArray[0];
        		endMinutes = endArray[1];
        	}
        }

        Connection conn;
        Statement stmt_0;
		ResultSet rs_0;
		try {
			conn = DatabaseConnection.getConnection();
			stmt_0 = conn.createStatement();
			String sql1_0 = "";
			if("true".equals(allDay)) {
				sql1_0 =  "select * from schedule where AllDay='" + allDay + "' and Arrange='" + schedule + "' and TeacherAccount='" + TeacherAccount + "' and Year='" + year + "' and Month='" + month + "' and Day='" + day + "'";
			}else {
				sql1_0 =  "select * from schedule where Arrange='" + schedule + "' and TeacherAccount='" + TeacherAccount + "' and Year ='" + year + "' and Month ='" + month + "' and Day='" + day + "' and StartHour='" + startHour + "' and  StartMinutes='" + startMinutes + "' and EndHour='" + endHour + "' and EndMinutes='" + endMinutes + "'";	
			}		
			
			rs_0 = stmt_0.executeQuery(sql1_0);
			if(rs_0.next()) {
				ret.put("ret", "Success");
				session.put("id", rs_0.getInt(1));
			}else {
				ret.put("ret", "Fail");
			}		
		} catch (Exception e) {
			ret.put("ret", "Fail");
		}
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS; 
	}
	public String fchangeSchedule() {
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String schedule = request.getParameter("schedule");
		String isWatch = request.getParameter("isWatch");
		int id = (int)session.get("id");
		
		String[] dateArray = date.split("-");
        String[] startArray = start.split(":");
        String[] endArray = end.split(":");
        String year = dateArray[0];
        String month = dateArray[1];
        String day = dateArray[2];
        String allDay = "false";
        String startHour = null;
        String startMinutes = null;
        String endHour = null;
        String endMinutes = null;
        if("".equals(start)) {
        	if("".equals(end)) {
        		allDay = "true";
        	}else {
        		startHour = "0";
        		startMinutes = "0";
        		endHour = endArray[0];
        		endMinutes = endArray[1];
        	}
        }else {
        	startHour = startArray[0];
    		startMinutes = startArray[1];
        	if("".equals(end)) {
        		endHour = "0";
        		endMinutes = "0";
        	}else {
        		endHour = endArray[0];
        		endMinutes = endArray[1];
        	}
        }
        Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			String sql1_0 = "update schedule set Year=?,AllDay=?,Month=?,Day=?,StartHour=?,StartMinutes=?,EndHour=?,EndMinutes=?,Arrange=?,IsWatch=? where id=?";

			PreparedStatement ps_0 = (PreparedStatement) conn.prepareStatement(sql1_0);
			ps_0.setString(1, year);
			ps_0.setString(2, allDay);
			ps_0.setString(3, month);
			ps_0.setString(4, day);
			ps_0.setString(5, startHour);
			ps_0.setString(6, startMinutes);
			ps_0.setString(7, endHour);
			ps_0.setString(8, endMinutes);
			ps_0.setString(9, schedule);
			ps_0.setString(10, isWatch);
			ps_0.setInt(11, id);
			ps_0.executeUpdate();
		
			ret.put("ret", "Success");  
			// session.put(, schedule);更新日程
		} catch (Exception e) {
			ret.put("ret", "Fail");
		}
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS; 
	}
	public String deleteSchedule() {
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String schedule = request.getParameter("schedule");
		String TeacherAccount = (String) session.get("TeacherAccount");
		String[] dateArray = date.split("-");
        String[] startArray = start.split(":");
        String[] endArray = end.split(":");
        String year = dateArray[0];
        String month = dateArray[1];
        String day = dateArray[2];
        String allDay = "false";
        String startHour = null;
        String startMinutes = null;
        String endHour = null;
        String endMinutes = null;
        if("".equals(start)) {
        	if("".equals(end)) {
        		allDay = "true";
        	}else {
        		startHour = "0";
        		startMinutes = "0";
        		endHour = endArray[0];
        		endMinutes = endArray[1];
        	}
        }else {
        	startHour = startArray[0];
    		startMinutes = startArray[1];
        	if("".equals(end)) {
        		endHour = "0";
        		endMinutes = "0";
        	}else {
        		endHour = endArray[0];
        		endMinutes = endArray[1];
        	}
        }
    
        Connection conn;
        Statement stmt_0;
        Connection conn1;
        Statement stmt_1;
		ResultSet rs_0;
		int rs_1;
		try {
			conn = DatabaseConnection.getConnection();
			stmt_0 = conn.createStatement();
			String sql1_0 = "";
			if("true".equals(allDay)) {
				sql1_0 =  "select * from schedule where AllDay='" + allDay + "' and Arrange='" + schedule + "' and TeacherAccount='" + TeacherAccount + "' and Year='" + year + "' and Month='" + month + "' and Day='" + day + "'";
			}else {
				sql1_0 =  "select * from schedule where Arrange='" + schedule + "' and TeacherAccount='" + TeacherAccount + "' and Year ='" + year + "' and Month ='" + month + "' and Day='" + day + "' and StartHour='" + startHour + "' and  StartMinutes='" + startMinutes + "' and EndHour='" + endHour + "' and EndMinutes='" + endMinutes + "'";	
			}		
			
			rs_0 = stmt_0.executeQuery(sql1_0);
		
			if(rs_0.next()) {
		        conn1 = DatabaseConnection.getConnection();
				stmt_1 = conn.createStatement();
			
				String sql1_1 = "delete from schedule where id=" + rs_0.getInt(1) + "";
				rs_1 = stmt_0.executeUpdate(sql1_1);
	
				ret.put("ret", "Success");	
			}else {
				ret.put("ret", "NotExist");
			}		
		} catch (Exception e) {
			ret.put("ret", "Success");
		}
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS; 
	} 
	public String addSchedule() {
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String schedule = request.getParameter("schedule");
		String isWatch = request.getParameter("isWatch");
		String TeacherAccount = (String) session.get("TeacherAccount");
		String[] dateArray = date.split("-");
        String[] startArray = start.split(":");
        String[] endArray = end.split(":");
        String year = dateArray[0];
        String month = dateArray[1];
        String day = dateArray[2];
        String allDay = "false";
        String startHour = null;
        String startMinutes = null;
        String endHour = null;
        String endMinutes = null;
        if("".equals(start)) {
        	if("".equals(end)) {
        		allDay = "true";
        	}else {
        		startHour = "0";
        		startMinutes = "0";
        		endHour = endArray[0];
        		endMinutes = endArray[1];
        	}
        }else {
        	startHour = startArray[0];
    		startMinutes = startArray[1];
        	if("".equals(end)) {
        		endHour = "0";
        		endMinutes = "0";
        	}else {
        		endHour = endArray[0];
        		endMinutes = endArray[1];
        	}
        }
        Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			String sql1_0 = "insert into schedule(Year,Month,Day,AllDay,StartHour,StartMinutes,EndHour,EndMinutes,Arrange,TeacherAccount,IsWatch)values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps_0 = (PreparedStatement) conn.prepareStatement(sql1_0);
			ps_0.setString(1, year);
			ps_0.setString(2, month);
			ps_0.setString(3, day);
			ps_0.setString(4, allDay);
			ps_0.setString(5, startHour);
			ps_0.setString(6, startMinutes);
			ps_0.setString(7, endHour);
			ps_0.setString(8, endMinutes);
			ps_0.setString(9, schedule);
			ps_0.setString(10, TeacherAccount);
			ps_0.setString(11, isWatch);
			ps_0.executeUpdate();
			ret.put("ret", "Success");
			// session.put(, schedule);更新日程
		} catch (Exception e) {
			ret.put("ret", "Fail");
		}
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS; 
	}

	public String teacherAgreeOrder() {
		String id = request.getParameter("id");

		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		String TeacherAccount = (String) session.get("TeacherAccount");
		Connection conn;
		Connection conn_1;
		Statement stmt_1;
		ResultSet rs_1;
		String year = "";
		String month = "";
		String day = "";
		String startMinutes = "";
		String startHour = "";
		String endHour = "";
		String endMinutes = "";
		String reson = "";// 棰勭害鐞嗙敱
		String student = "";
		String phone = "";
		String allDay = "";
		try {
			conn = DatabaseConnection.getConnection();
			String sql = "update arrange set SuccessFail=? where ArrangeId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Success");
			ps.setString(2, id);
			ps.executeUpdate();
			
			conn_1 = DatabaseConnection.getConnection();
			stmt_1 = conn_1.createStatement();
			String sql_1 = "select * from arrange where TeacherAccount='" + TeacherAccount + "'";
			rs_1 = stmt_1.executeQuery(sql_1);
		
			ArrayList<Arrange> L = new ArrayList<Arrange>();
			while (rs_1.next()) {

				if (rs_1.getString("SuccessFail").equals("false")) {
					Arrange temp = new Arrange();
					temp.setTeacherAccount(rs_1.getString("TeacherAccount"));
					temp.setStudentAccount(rs_1.getString("StudentAccount"));
					temp.setArrangeTime(rs_1.getString("AllDay"), rs_1.getString("ArrangeYear"),
							rs_1.getString("ArrangeMonth"), rs_1.getString("ArrangeDay"), rs_1.getString("StartHour"),
							rs_1.getString("StartMinutes"), rs_1.getString("EndHour"), rs_1.getString("EndMinutes"));
					temp.setArrangeReason(rs_1.getString("ArrangeReason"));
					temp.setSuccessFail(rs_1.getString("SuccessFail"));
					temp.setStudentName(rs_1.getString("StudentName"));
					temp.setStudentMajor(rs_1.getString("StudentMajor"));
					temp.setStudentPhone(rs_1.getString("StudentPhone"));
					temp.setArrangeId(rs_1.getInt("ArrangeId"));
					L.add(temp);
				}
				if (rs_1.getInt("ArrangeId") == Integer.parseInt(id)) {
					reson = rs_1.getString("ArrangeReason");
					year = rs_1.getString("ArrangeYear");
					month = rs_1.getString("ArrangeMonth");
					day = rs_1.getString("ArrangeDay");
				    startHour = rs_1.getString("StartHour");
				    startMinutes = rs_1.getString("StartMinutes");
				    endHour = rs_1.getString("EndHour");
				    endMinutes = rs_1.getString("EndMinutes");
					phone = rs_1.getString("StudentPhone");
					allDay = rs_1.getString("AllDay");
					student = rs_1.getString("StudentName");
					reson = student + "预约\r\n:" + "预约理由:" + reson + ". 联系方式：" + phone;
					
				}
			}
			ret.put("ret", "Success");
			session.put("teacherOrder", L);

			// 鏇存柊鏁欏笀鐨勬棩绋嬭〃
			Connection conn1;
			try {
				conn1 = DatabaseConnection.getConnection();
				String sql1 = "insert into schedule(Year,Month,Day,AllDay,StartHour,StartMinutes,EndHour,EndMinutes,Arrange,TeacherAccount,IsWatch)values(?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps1 = conn1.prepareStatement(sql1);
				ps1.setString(1, year);
				ps1.setString(2, month);
				ps1.setString(3, day);
				ps1.setString(4, allDay);
				ps1.setString(5, startHour);
				ps1.setString(6, startMinutes);
				ps1.setString(7, endHour);
				ps1.setString(8, endMinutes);
				ps1.setString(9, reson);
				ps1.setString(10, TeacherAccount);
				ps1.setString(11, "true");
				ps1.executeUpdate();
				ret.put("ret", "Success");
			} catch (Exception e) {
				ret.put("ret", "Fail");
			}

		} catch (Exception e) {
			ret.put("ret", "Fail");
		}

		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS;
	}

	/**********
	 * 鏁欏笀鎷掔粷棰勭害
	 **********/
	public String teacherCancelOrder() {
		String id = request.getParameter("id");
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		String TeacherAccount = (String) session.get("TeacherAccount");
		Connection conn;
		Connection conn_1;
		Statement stmt_1;
		ResultSet rs_1;
		try {
			conn = DatabaseConnection.getConnection();
			String sql = "update arrange set SuccessFail=? where ArrangeId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Fail");
			ps.setString(2, id);
			ps.executeUpdate();
			conn_1 = DatabaseConnection.getConnection();
			stmt_1 = conn_1.createStatement();
			String sql_1 = "select *from arrange where TeacherAccount='" + TeacherAccount + "'";
			rs_1 = stmt_1.executeQuery(sql_1);
			ArrayList<Arrange> L = new ArrayList<Arrange>();
			while (rs_1.next()) {
				if (rs_1.getString("SuccessFail").equals("false")) {
					Arrange temp = new Arrange();
					temp.setTeacherAccount(rs_1.getString("TeacherAccount"));
					temp.setStudentAccount(rs_1.getString("StudentAccount"));
					temp.setArrangeTime(rs_1.getString("AllDay"), rs_1.getString("ArrangeYear"),
							rs_1.getString("ArrangeMonth"), rs_1.getString("ArrangeDay"), rs_1.getString("StartHour"),
							rs_1.getString("StartMinutes"), rs_1.getString("EndHour"), rs_1.getString("EndMinutes"));
					temp.setArrangeReason(rs_1.getString("ArrangeReason"));
					temp.setSuccessFail(rs_1.getString("SuccessFail"));
					temp.setStudentName(rs_1.getString("StudentName"));
					temp.setStudentMajor(rs_1.getString("StudentMajor"));
					temp.setStudentPhone(rs_1.getString("StudentPhone"));
					temp.setArrangeId(rs_1.getInt("ArrangeId"));
					L.add(temp);
				}

			}
			ret.put("ret", "Success");
			session.put("teacherOrder", L);
		} catch (Exception e) {
			ret.put("ret", "Fail");
		}
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS;
	}

	/*********************
	 * 闅忔満鑾峰彇涓変釜鑰佸笀淇℃伅
	 *****************/
	public String chooseTeacher() {
		Connection conn;
		Statement stmt;
		ResultSet rs;
		Connection conn1;
		Statement stmt1;
		ResultSet rs1;
		
		try {
			conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select *from teacher";
			rs = stmt.executeQuery(sql);
			int line = 0;
			while (rs.next()) {
				line++;
			}
			Random random = new Random();
			int t1 = random.nextInt(line - 2);
			int t2 = t1 + 1;
			int t3 = t2 + 1;
			

			conn1 = DatabaseConnection.getConnection();
			stmt1 = conn1.createStatement();
			String sql1 = "select *from teacher";
			rs1 = stmt1.executeQuery(sql1);
			int i = 0;
			while (rs1.next()) {
				if (i == t1) {
					
					ret.put("teacherName1", rs1.getString("TeacherName"));
					ret.put("teacherInf1", rs1.getString("College") + " " + rs1.getString("Position"));
					ret.put("teacherImage1", rs1.getString("Picture"));

					ret.put("teacherHref1", rs1.getString("Baike"));

				}
				if (i == t2) {
					ret.put("teacherName2", rs1.getString("TeacherName"));
					ret.put("teacherInf2", rs1.getString("College") + " " + rs1.getString("Position"));
					ret.put("teacherImage2", rs1.getString("Picture"));
					ret.put("teacherHref2", rs1.getString("Baike"));
				}
				if (i == t3) {
					ret.put("teacherName3", rs1.getString("TeacherName"));
					ret.put("teacherInf3", rs1.getString("College") + " " + rs1.getString("Position"));
					ret.put("teacherImage3", rs1.getString("Picture"));
					ret.put("teacherHref3", rs1.getString("Baike"));
				}

				i++;
			}

			ret.put("ret", "Success");

		} catch (Exception e) {
			ret.put("ret", "Fail");
		}
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		
		return SUCCESS;
	}
	public String showSchedule() {
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		String TeacherAccount = (String) session.get("TeacherAccount");
		String isTeacher = (String) session.get("isTeacher");
		String StudentAccount = (String) session.get("StudentAccount");
		Connection conn_0;
		Statement stmt_0;
		ResultSet rs_0;
		try {
			conn_0 = DatabaseConnection.getConnection();
			stmt_0 = conn_0.createStatement();
			String sql_0 = "";
			if("true".equals(isTeacher)) {
				 sql_0 = "select *from schedule where TeacherAccount='" + TeacherAccount + "'";
			}else {
				TeacherAccount = (String) session.get("searchTeacherId");
				sql_0 = "select *from schedule where TeacherAccount='" + TeacherAccount + "'";
			}
			rs_0 = stmt_0.executeQuery(sql_0);
			int cnt = 0;
			ArrayList L = new ArrayList();
			while (rs_0.next()) {
				Map<String, Object> ele = new HashMap<String, Object>();
				if("true".equals(rs_0.getString("AllDay"))) {
					ele.put("title", rs_0.getString("Arrange"));
					ele.put("allDay", "true");
					ele.put("year", rs_0.getString("Year"));
					ele.put("month", rs_0.getString("Month"));
					ele.put("day", rs_0.getString("Day"));
					ele.put("isWatch", rs_0.getString("IsWatch"));
				}else {
					ele.put("title", rs_0.getString("Arrange"));
					ele.put("allDay", "false");
					ele.put("year", rs_0.getString("Year"));
					ele.put("month", rs_0.getString("Month"));
					ele.put("day", rs_0.getString("Day"));
					ele.put("startHour", rs_0.getString("StartHour")); 
					ele.put("startMinutes", rs_0.getString("StartMinutes"));
					ele.put("endHour", rs_0.getString("EndHour"));
					ele.put("endMinutes", rs_0.getString("EndMinutes"));	
					ele.put("isWatch", rs_0.getString("IsWatch"));
				}
				L.add(ele);
				cnt++;
			}
			ret.put("cnt", cnt);
			ret.put("schedule", L);

		}catch(Exception e) {
			
		}
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		
		return SUCCESS;
	}
	
	
public String changeStudentInf() {
		
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		String StudentAccount=(String)session.get("StudentAccount");
		String StudentName=request.getParameter("name");
		String College=request.getParameter("college");
		String Major=request.getParameter("major");
		String Phone=request.getParameter("phone");
		Connection conn;
		
 		try {
			conn=DatabaseConnection.getConnection();
			String sql="update student set StudentName=? ,College=? ,Major=? ,Phone=? where StudentAccount=?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, StudentName);
			ps.setString(2, College);
			ps.setString(3, Major);
			ps.setString(4, Phone);
			ps.setString(5, StudentAccount);
			ps.executeUpdate();
			ret.put("ret", "Success");
			
			
			//更信息
			Statement stmt2;
			ResultSet rs;
			stmt2 = conn.createStatement();
			String sql2 = "select *from student where StudentAccount='" + StudentAccount + "' ";
			rs = stmt2.executeQuery(sql2);
			if (rs.next())
			{
				Student student = new Student();
				student.setStudentAccount(rs.getString("StudentAccount"));
				student.setStudentName(rs.getString("StudentName"));
				student.setMajor(rs.getString("Major"));
				student.setCollege(rs.getString("College"));
				student.setPhone(rs.getString("Phone"));
				student.setPicture(rs.getString("Picture"));
				session.put("student", student);
			}
		} catch (Exception e) {
	
			ret.put("ret", "Fail");
		}	
		
 		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS;
	}
	
	//淇敼鏁欏笀淇″績
	public String changeTeacherInf() {
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		String TeacherAccount=(String)session.get("TeacherAccount");
		String TeacherName=request.getParameter("name");
		String College=request.getParameter("college");
		String Position=request.getParameter("position");
		String Phone=request.getParameter("phone");
		String Money=request.getParameter("money");
		String FSRA=request.getParameter("fsra");
		Connection conn;
		
 		try {
			conn=DatabaseConnection.getConnection();
			String sql="update teacher set TeacherName=?, College=?, Position=?, Phone=?,Money=?,FSRA=? where TeacherAccount=?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, TeacherName);
			ps.setString(2, College);
			ps.setString(3, Position);
			ps.setString(4, Phone);
			ps.setString(5, Money);
			ps.setString(6, FSRA);
			ps.setString(7 , TeacherAccount);
			ps.executeUpdate();
			ret.put("ret", "Success");
			
			//更新信息
			Statement stmt;
			ResultSet rs;
			conn = DatabaseConnection.getConnection();
			stmt = conn.createStatement();
			String sql1 = "select *from teacher where TeacherAccount='" + TeacherAccount + "'";
			rs = stmt.executeQuery(sql1);
			if (rs.next()) 
			{
				Teacher teacher = new Teacher();
				teacher.setTeacherAccount(rs.getString("TeacherAccount"));
				teacher.setPhone(rs.getString("Phone"));
				teacher.setPicture(rs.getString("Picture"));
				teacher.setCollege(rs.getString("College"));
				teacher.setTeacherName(rs.getString("TeacherName"));
				teacher.setFSRA(rs.getString("FSRA"));
				teacher.setMoney(rs.getString("Money"));
				teacher.setPosition(rs.getString("Position"));
				teacher.setPhone(rs.getString("Phone"));
				session.put("teacher", teacher);
			}	
		} catch (Exception e) {
			ret.put("ret", "Fail");
		}	
		
 		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS;
	}
}
