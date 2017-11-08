package com.struts2.action;


/**
 * 学生预约信息
 */
public class Arrange {
	
	private String TeacherAccount;	//教师账号
	private String StudentAccount;	//学生的账号
	private String ArrangeTime;		//预约时间
	private String ArrangeReason;	//事由
	private String SuccessFail;		//预约状态
	private String StudentName;		//学生的真是姓名
	private String StudentMajor;	//学生的专业
	private String StudentPhone;	//学生的电话
	private Integer ArrangeId; //预约编号						
    private String TeacherName;
    private String commitornot;//是否确认提交
    
	public String getCommitornot() {
		return commitornot;
	}
	public void setCommitornot(String commitornot) {
		this.commitornot = commitornot;
	}
	public String getArrangeId() {
		return ArrangeId.toString();
	}
	public void setArrangeId(int arrangeId) {
//		if(arrangeId != null && !"".equals(arrangeId)) {
//			ArrangeId = Integer.parseInt(arrangeId);
//		}
		ArrangeId = arrangeId;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}


	public String getTeacherName() {
		return TeacherName;
	}
	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}
	public String getStudentMajor() { 
		return StudentMajor;
	}
	public void setStudentMajor(String studentMajor) {
		StudentMajor = studentMajor;
	}
	public String getStudentPhone() {
		return StudentPhone;
	}
	public void setStudentPhone(String studentPhone) {
		StudentPhone = studentPhone;
	}
	public String getTeacherAccount() {
		return TeacherAccount;
	}
	public void setTeacherAccount(String teacherAccount) {
		TeacherAccount = teacherAccount;
	}
	public String getStudentAccount() {
		return StudentAccount;
	}
	public void setStudentAccount(String studentAccount) {
		StudentAccount = studentAccount;
	}
	public String getArrangeTime() {
		return ArrangeTime;
	}
	public void setArrangeTime(String arrangeTime) {
		ArrangeTime = arrangeTime;
	}
	public String getArrangeReason() {
		return ArrangeReason;
	}
	public void setArrangeReason(String arrangeReason) {
		ArrangeReason = arrangeReason;
	}
	public String getSuccessFail() {
		return SuccessFail;
	}
	public void setSuccessFail(String successFail) {
		SuccessFail = successFail;
	}
	
	

}
