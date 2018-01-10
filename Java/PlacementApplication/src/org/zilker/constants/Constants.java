package org.zilker.constants;

public class Constants {

	public static final String INSERT_STUDENTS_PLACED = "insert into studentsplaced values(?,?)";
	public static final String UPDATE_PLACED_STATUS = "update student set placedstatus = ? where regno = ?";
	public static final String VERIFY_PLACED_OR_NOT = "select * from studentsplaced where regno = ?";
	public static final String CHECK_ELIGIBILITY = "select cgpa, arrears, cgpacriteria, arrearcriteria from student, company where regno = ? and companyid = ?";
	public static final String DISPLAY_COMPANY_ALL = "select * from company";
	public static final String DISPLAY_COMPANY_ONE = "select * from company where companyid = ?";
	public static final String DISPLAY_STUDENT_ALL = "select * from student";
	public static final String DISPLAY_STUDENT_ONE = "select * from student where regno = ";
	public static final String DISPLAY_DEPARTMENT_ALL = "select * from student where deptname = ?";
	public static final String DISPLAY_DEPARTMENT_ONE = "select * from student where deptname = ? and regno = ?";
	public static final String INSERT_STUDENT_DETAILS = "insert into student(regno,name,email,cgpa,arrears,placedstatus,deptname,createdby,createdat) values(?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_COMPANY_DETAILS = "insert into company(companyid,name,cgpacriteria,arrearcriteria,createdby,createdat) values(?,?,?,?,?,?)";
	public static final String UPDATE_STUDENT_DETAILS = "update student set name = ?, email = ?, cgpa = ?, arrears = ?, placedstatus = ?, deptname = ?, updatedby = ?, updatedat = ? where regno = ?";
	public static final String UPDATE_COMPANY_DETAILS = "update company set name = ?, cgpacriteria = ?, arrearcriteria = ?, updatedby = ?, updatedat = ? where companyid = ?";
	public static final String VALIDATE_TEACHER = "select * from teacher where dept = ? and password = ?";
	public static final String TOTAL_STUDENTS_PLACED = "select count(*) from student where placedstatus = ?";
	public static final String TOTAL_STUDENTS_PLACED_DEPARTMENT = "select count(*) from student where deptname = ? and placedstatus = ?";
	public static final String TOTAL_STUDENTS = "select count(*) from student";
	public static final String TOTAL_STUDENTS_DEPARTMENT = "select count(*) from student where deptname = ?";
	public static final String DEAN_DEPARTMENT_NAME = "DEAN";
	public static final String ADMIN_DEPARTMENT_NAME = "ADMIN";
}
