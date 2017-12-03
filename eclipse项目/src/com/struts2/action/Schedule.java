package com.struts2.action;

public class Schedule {
	
	private String TeacherAccount;	
	private String Year;
	private String Month;
	private String Day;
	private String AllDay;
	private String StartHour;
	private String StartMinutes;
	private String EndHour;
	private String EndMinutes;
	private String Arrange;
	private String IsWatch;
	
	public String getIsWatch() {
		return IsWatch;
	}
	public void setIsWatch(String isWatch) {
		IsWatch = isWatch;
	}
	public String getAllDay() {
		return AllDay;
	}
	public void setAllDay(String allDay) {
		AllDay = allDay;
	}
	public String getTeacherAccount() {
		return TeacherAccount;
	}
	public void setTeacherAccount(String teacherAccount) {
		TeacherAccount = teacherAccount;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
	}
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
	public String getStartHour() {
		return StartHour;
	}
	public void setStartHour(String startHour) {
		StartHour = startHour;
	}
	public String getStartMinutes() {
		return StartMinutes;
	}
	public void setStartMinutes(String startMinutes) {
		StartMinutes = startMinutes;
	}
	public String getEndHour() {
		return EndHour;
	}
	public void setEndHour(String endHour) {
		EndHour = endHour;
	}
	public String getEndMinutes() {
		return EndMinutes;
	}
	public void setEndMinutes(String endMinutes) {
		EndMinutes = endMinutes;
	}
	public String getArrange() {
		return Arrange;
	}
	public void setArrange(String arrange) {
		Arrange = arrange;
	}
	
}
