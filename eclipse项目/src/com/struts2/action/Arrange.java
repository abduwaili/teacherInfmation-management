package com.struts2.action;


/**
 * 瀛︾敓棰勭害淇℃伅
 */
public class Arrange {
	
	private String TeacherAccount;	//鏁欏笀璐﹀彿
	private String StudentAccount;	//瀛︾敓鐨勮处鍙�
	private String ArrangeReason;	//浜嬬敱
	private String SuccessFail;		//棰勭害鐘舵��
	private String StudentName;		//瀛︾敓鐨勭湡鏄鍚�
	private String StudentMajor;	//瀛︾敓鐨勪笓涓�
	private String StudentPhone;	//瀛︾敓鐨勭數璇�
	private Integer ArrangeId; //棰勭害缂栧彿						
    private String TeacherName;
    private String commitornot;//鏄惁纭鎻愪氦
    private String ArrangeTime;
    
    public String getArrangeTime() {
    	return ArrangeTime;
    }
    public void setArrangeTime(String AllDay, String Year, String Month, String Day, String StartHour, String StartMinutes, String EndHour, String EndMinutes) {
    	if("true".equals(AllDay)) {
    		ArrangeTime = Year + "-" + Month + "-" + Day + "  "+  "ALLDAY";
    	}else {
    		ArrangeTime = Year + "-" + Month + "-" + Day + "  " + StartHour + ":" + StartMinutes + "——" + EndHour + ":" + EndMinutes;
    	}
    }
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
