package com.struts2.action;

/**
 * 教师的日程表
 * @author dudu
 *
 */
public class Schedule {
	
	private String TeacherAccount;		//教师账号
	
	//星期一到星期天上下午
	private String MonAm;
	private String MonPm;
	private String TueAm;
	private String TuePm;
	private String WedAm;
	private String WedPm;
	private String ThuAm;
	private String ThuPm;
	private String FriAm;
	private String FriPm;
	public String getTeacherAccount() {
		return TeacherAccount;
	}
	public void setTeacherAccount(String teacherAccount) {
		TeacherAccount = teacherAccount;
	}
	public String getMonAm() {
		return MonAm;
	}
	public void setMonAm(String monAm) {
		MonAm = monAm;
	}
	public String getMonPm() {
		return MonPm;
	}
	public void setMonPm(String monPm) {
		MonPm = monPm;
	}
	public String getTueAm() {
		return TueAm;
	}
	public void setTueAm(String tueAm) {
		TueAm = tueAm;
	}
	public String getTuePm() {
		return TuePm;
	}
	public void setTuePm(String tuePm) {
		TuePm = tuePm;
	}
	public String getWedAm() {
		return WedAm;
	}
	public void setWedAm(String wedAm) {
		WedAm = wedAm;
	}
	public String getWedPm() {
		return WedPm;
	}
	public void setWedPm(String wedPm) {
		WedPm = wedPm;
	}
	public String getThuAm() {
		return ThuAm;
	}
	public void setThuAm(String thuAm) {
		ThuAm = thuAm;
	}
	public String getThuPm() {
		return ThuPm;
	}
	public void setThuPm(String thuPm) {
		ThuPm = thuPm;
	}
	public String getFriAm() {
		return FriAm;
	}
	public void setFriAm(String friAm) {
		FriAm = friAm;
	}
	public String getFriPm() {
		return FriPm;
	}
	public void setFriPm(String friPm) {
		FriPm = friPm;
	}
}
