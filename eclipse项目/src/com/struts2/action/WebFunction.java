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

public class WebFunction extends ActionSupport implements ServletRequestAware{

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
	
	Map<String, Object>ret = new HashMap<String, Object>();
	Map<String, Object>session;
	
	
	private String TeacherAccount="";

	

	
	
	/**
	 * 按姓名搜搜教师
	 */
	public String SearchTeacher()
	{
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
		String teacherName=request.getParameter("teacherName");
		Connection conn;	
		Statement stmt;	
		ResultSet rs;	
		Connection conn_0;	
		Statement stmt_0;	
		ResultSet rs_0;	
		 	
		 try {
			 conn=DatabaseConnection.getConnection();
			 stmt=conn.createStatement();
			 String sql="select *from teacher where TeacherName='"+teacherName+"'";
			 rs=stmt.executeQuery(sql);
			 if(rs.next())
			 {
				 ret.put("searchTeacherName", rs.getString("TeacherName"));
				 ret.put("searchTeacherMoney", rs.getString("Money"));
				 ret.put("searchTeacherFSRA", rs.getString("FSRA"));
				 ret.put("searchTeacherCollege", rs.getString("College"));
				 ret.put("searchTeacherPosition",rs.getString( "Position"));
				 ret.put("searchTeacherImage",rs.getString( "Picture"));
				 ret.put("searchTeacherPhone", rs.getString("Phone"));	 
				 TeacherAccount=rs.getString("TeacherAccount");
				 
				 conn_0=DatabaseConnection.getConnection();
				 stmt_0=conn_0.createStatement();
				 String sql_0="select *from schedule where TeacherAccount='"+TeacherAccount+"'";
				 rs_0=stmt_0.executeQuery(sql_0);
				 if(rs_0.next())
				 {
					 ret.put("searchMonAm", rs_0.getString("MonAm"));							 
					 ret.put("searchMonPm", "none");
					 ret.put("searchTueAm", rs_0.getString("TueAm"));					
					 ret.put("searchTuePm", rs_0.getString("TuePm"));
					 ret.put("searchWedAm", rs_0.getString("WedAm"));						
					 ret.put("searchWedPm", rs_0.getString("WedPm"));						
					 ret.put("searchThuAm", rs_0.getString("ThuAm"));
					 ret.put("searchThuPm", rs_0.getString("ThuPm"));
					 ret.put("searchFriAm", rs_0.getString("FriAm"));
					 ret.put("searchFriPm", rs_0.getString("FriPm"));
				 }
				 session.put("searchTeacherId", TeacherAccount);
				 ret.put("ret", "Success");
			 }else {
				ret.put("ret", "NotFound");
			}			 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS;
	}
	
	
	/**********
	 * 添加预约教师
	 **********/
	public String addOrder()
	{
		  ActionContext context=ActionContext.getContext();
		  session=context.getSession();
		  String orderTime=request.getParameter("orderTime");//预约时间点
		  String orderReason=request.getParameter("orderReason");//预约事由
		  String orderTeacher=request.getParameter("orderTeacher");//教师姓名
		  String orderPhone=request.getParameter("orderPhone");//学生电话
		  Connection conn;	
		  Statement stmt;	
		  ResultSet rs;
		  Connection conn_0;	
		  String[] time= {"MonPm","MonAm","TueAm","TuePm","WedAm","WedPm","ThuAm","ThuPm","FriAm","FriPm"};  
		  for(int i=0;i<time.length;i++)
		  {
			  if(time[i].equals(orderTime))
			  {
				  try {
						 conn=DatabaseConnection.getConnection();
						 stmt=conn.createStatement();
						 String sql="select *from teacher where TeacherName='"+orderTeacher+"'";
						 rs=stmt.executeQuery(sql);
						 if(rs.next())
						 {
							 TeacherAccount=rs.getString("TeacherAccount");//教师账号
							 
							//判断输入的时间点老师是否有时间=none
							 Connection conn1;	
							 Statement stmt1;	
							 ResultSet rs1;
							 conn1=DatabaseConnection.getConnection();
							 stmt1=conn1.createStatement();
							 String sql1="select *from schedule where TeacherAccount='"+TeacherAccount+"'";
							 rs1=stmt1.executeQuery(sql1);
							 while(rs1.next())
							 {
								 if(rs1.getString(orderTime).equals("none")==false)
								 {
									 ret.put("ret", "NoTime");//教师没时间
									 JSONObject json = JSONObject.fromObject(ret);
									 result = json.toString();
									return SUCCESS;
								 }
							 }
							 
							 //添加预约信息
							
							 Student student=(Student) session.get("student");//学生对象
							 conn_0=DatabaseConnection.getConnection();
							 String sql1_0="insert into arrange(TeacherName,TeacherAccount,StudentAccount,ArrangeTime,ArrangeReason,commitornot,SuccessFail,StudentName,StudentMajor,StudentPhone)values(?,?,?,?,?,?,?,?,?,?)";
							 PreparedStatement ps_0=(PreparedStatement) conn_0.prepareStatement(sql1_0);
							 ps_0.setString(1, orderTeacher);
							 ps_0.setString(2, TeacherAccount);
							 ps_0.setString(3, student.getStudentAccount());
							 ps_0.setString(4, orderTime);
							 ps_0.setString(5, orderReason);
							 ps_0.setString(6, "true");
							 ps_0.setString(7, "false");
							 ps_0.setString(8, student.getStudentName());
							 ps_0.setString(9, student.getMajor());
							 ps_0.setString(10, orderPhone);
							 ps_0.executeUpdate();
							 
							 //更新预约session
							 Connection conn_1;	
							 Statement stmt_1;			
							 ResultSet rs_1;
							 conn_1=DatabaseConnection.getConnection();	
							 stmt_1=conn_1.createStatement();
							 String sql_1="select *from arrange where StudentAccount='"+student.getStudentAccount()+"'";
							 rs_1=stmt_1.executeQuery(sql_1);
							 ArrayList<Arrange> L = new ArrayList<Arrange>();
							 while(rs_1.next())
							 {
								 Arrange temp = new Arrange();
								 temp.setTeacherAccount(rs_1.getString("TeacherAccount"));
								 temp.setTeacherName(rs_1.getString("TeacherName"));
								 temp.setStudentAccount(rs_1.getString("StudentAccount"));
								 temp.setArrangeTime(rs_1.getString("ArrangeTime"));
								 temp.setArrangeReason(rs_1.getString("ArrangeReason"));
								 temp.setSuccessFail(rs_1.getString("SuccessFail"));
								 temp.setArrangeId(rs_1.getInt("ArrangeId"));
								 L.add(temp);
							 }
							 session.put("studentOrder", L);
							 						 
							 ret.put("ret", "Success");
						 }else {
							 ret.put("ret", "NoTeacher");
						 } 
						 
					} catch (Exception e) {
						// TODO: handle exception
					}
				  break;
			  }else {
				  ret.put("ret", "ErrorTime");
			}
		  }
			
		  
		  
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS;
	}
	
	
	/**********
	 * 学生取消预约
	 **********/
	public String cancelOrder()
	{
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
		int ArrangeId=Integer.parseInt(request.getParameter("id"));
		Connection conn;	
	    try {
	    	 Connection conn1;	
			 Statement stmt1;	
			 ResultSet rs1;
			 conn1=DatabaseConnection.getConnection();
			 stmt1=conn1.createStatement();
			 String sql1="select *from arrange where ArrangeId='"+ArrangeId+"'";
			 rs1=stmt1.executeQuery(sql1);
			 while(rs1.next())
			 {
				 String flag = rs1.getString("SuccessFail");   
				 conn=DatabaseConnection.getConnection();	
			  	 String sql=" delete from arrange where ArrangeId=?";
			  	 PreparedStatement pStatement=conn.prepareStatement(sql);
			  	 pStatement.setInt(1, ArrangeId);
			  	 pStatement.executeUpdate();
			  		
			
			        @SuppressWarnings("unchecked")
					ArrayList<Arrange> studentOrder = (ArrayList<Arrange>)session.get("studentOrder");
			       for(int i=0;i<studentOrder.size();i++)
			       {
			    	   if(Integer.parseInt(studentOrder.get(i).getArrangeId())==ArrangeId)
			    	   {
			    		   studentOrder.remove(i);
			    		   continue;
			    	   }
			       }
			       
			       session.put("studentOrder", studentOrder);
			      
			        if(flag.equals("false")) {
			        	ret.put("ret", "Success");
			        }
			        else {
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
	
	
	
	/**********
	 * 教师增加日程
	 **********/
	public String addSchedule()
	{
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
		String  time=request.getParameter("time");//时间点
		String schedule=request.getParameter("schedule");//增加的日程
		String TeacherAccount=(String) session.get("TeacherAccount");//教师账号
		Connection conn;
		
		try {
				conn=DatabaseConnection.getConnection();
				String sql="update schedule set "+time+"=? where TeacherAccount=?";
				PreparedStatement ps= conn.prepareStatement(sql);
				ps.setString(1, schedule);
				ps.setString(2, TeacherAccount);
				ps.executeUpdate();
				ret.put("ret", "Success");
				session.put(time, schedule);
		} catch (Exception e) {
			ret.put("ret", "Fail");
		}	

		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS;
	}
	
	
	/**********
	 * 教师通过预约
	 **********/
	public String teacherAgreeOrder()
	{
		String id=request.getParameter("id");
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
		String TeacherAccount=(String) session.get("TeacherAccount");
		Connection conn;
		Connection conn_1;
		Statement stmt_1;
		ResultSet rs_1;
		String time="";
		String reson="";//预约理由
		String student="";
		try {
			conn=DatabaseConnection.getConnection();
			String sql="update arrange set SuccessFail=? where ArrangeId=?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, "Success");
			ps.setString(2, id);
			ps.executeUpdate();
			System.out.println("1");
			//更新教师的预约表
			 conn_1=DatabaseConnection.getConnection();	
			 stmt_1=conn_1.createStatement();
			 String sql_1="select * from arrange where TeacherAccount='"+TeacherAccount+"'";
			 rs_1=stmt_1.executeQuery(sql_1);
			 System.out.println("3");
			 ArrayList<Arrange> L = new ArrayList<Arrange>();
			 while(rs_1.next())
			 {    
				 System.out.println("2");
				 if(rs_1.getString("SuccessFail").equals("false"))
				 {
					 Arrange temp = new Arrange();
					 temp.setTeacherAccount(rs_1.getString("TeacherAccount"));
					 temp.setStudentAccount(rs_1.getString("StudentAccount"));
					 temp.setArrangeTime(rs_1.getString("ArrangeTime"));
					 temp.setArrangeReason(rs_1.getString("ArrangeReason"));
					 temp.setSuccessFail(rs_1.getString("SuccessFail"));
					 temp.setStudentName(rs_1.getString("StudentName"));
					 temp.setStudentMajor(rs_1.getString("StudentMajor"));
					 temp.setStudentPhone(rs_1.getString("StudentPhone"));
					 temp.setArrangeId(rs_1.getInt("ArrangeId"));
					 L.add(temp);
				 }	
				 if(rs_1.getInt("ArrangeId")==Integer.parseInt(id))
				 {
						 reson=rs_1.getString("ArrangeReason");
						 time=rs_1.getString("ArrangeTime");
						 student=rs_1.getString("StudentName");
						 reson = student + "预约\r\n:" + "预约理由:" + reson;
				 }
			 }
			 ret.put("ret", "Success");
			 session.put("teacherOrder", L);
			 
			//更新教师的日程表
			 Connection conn1;
				try {
						conn1=DatabaseConnection.getConnection();
						String sql1="update schedule set "+time+"=? where TeacherAccount=?";
						PreparedStatement ps1= conn1.prepareStatement(sql1);
						ps1.setString(1, reson);
						ps1.setString(2, TeacherAccount);
						ps1.executeUpdate();
						ret.put("ret", "Success");
						session.put(time, reson);
						System.out.println(time);
						System.out.println(reson);
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
	 * 教师拒绝预约
	 **********/
	public String teacherCancelOrder()
	{
		String id=request.getParameter("id");
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
		String TeacherAccount=(String) session.get("TeacherAccount");
		Connection conn;
		Connection conn_1;
		Statement stmt_1;
		ResultSet rs_1;
		try {
			conn=DatabaseConnection.getConnection();
			String sql="update arrange set SuccessFail=? where ArrangeId=?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, "Fail");
			ps.setString(2, id);
			ps.executeUpdate();
			 conn_1=DatabaseConnection.getConnection();	
			 stmt_1=conn_1.createStatement();
			 String sql_1="select *from arrange where TeacherAccount='"+TeacherAccount+"'";
			 rs_1=stmt_1.executeQuery(sql_1);
			 ArrayList<Arrange> L = new ArrayList<Arrange>();
			 while(rs_1.next())
			 {
				 if(rs_1.getString("SuccessFail").equals("false"))
				 {
					 Arrange temp = new Arrange();
					 temp.setTeacherAccount(rs_1.getString("TeacherAccount"));
					 temp.setStudentAccount(rs_1.getString("StudentAccount"));
					 temp.setArrangeTime(rs_1.getString("ArrangeTime"));
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
	 * 随机获取三个老师信息
	 *****************/
	public String chooseTeacher()
	{
		Connection conn;	
		Statement stmt;	
		ResultSet rs;
		Connection conn1;	
		Statement stmt1;	
		ResultSet rs1;

		 try {
			 conn=DatabaseConnection.getConnection();
			 stmt=conn.createStatement();
			 String sql="select *from teacher";
			 rs=stmt.executeQuery(sql); 
			 int line=0;
			 while(rs.next())
			 {
				 line++;
			 }
			 Random random=new Random();
			 int t1=random.nextInt(line-2);
			 int t2=t1+1;
			 int t3=t2+1;
			 System.out.println(line+" "+t1+" "+t2+" "+t3);

			 conn1=DatabaseConnection.getConnection();
			 stmt1=conn1.createStatement();
			 String sql1="select *from teacher";
			 rs1=stmt1.executeQuery(sql1); 
			 int i=0;
			 while(rs1.next())
			 {
				 if(i==t1)
				 {
					 System.out.println(rs1.getString("TeacherName"));
					 ret.put("teacherName1", rs1.getString("TeacherName"));
					 ret.put("teacherInf1", rs1.getString("College")+" "+rs1.getString("Position"));
					 ret.put("teacherImage1",rs1.getString("Picture"));

					 ret.put("teacherHref1",rs1.getString("Baike"));
					 
				 }
				 if(i==t2)
				 {
					 ret.put("teacherName2", rs1.getString("TeacherName"));
					 ret.put("teacherInf2", rs1.getString("College")+" "+rs1.getString("Position"));
					 ret.put("teacherImage2",rs1.getString("Picture"));
					 ret.put("teacherHref2",rs1.getString("Baike"));
				 }
				 if(i==t3)
				 {
					 ret.put("teacherName3", rs1.getString("TeacherName"));
					 ret.put("teacherInf3", rs1.getString("College")+" "+rs1.getString("Position"));
					 ret.put("teacherImage3",rs1.getString("Picture"));
					 ret.put("teacherHref3",rs1.getString("Baike"));
				 }
				 
				 i++;
			 }
			
			 ret.put("ret", "Success");
				 
		 }catch (Exception e) {
			 ret.put("ret", "Fail");
		}
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		System.out.println(result);
		return SUCCESS;
	}
}
