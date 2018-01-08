package org.zilker.raxit;

import java.sql.*;
import java.util.Calendar;
import java.util.logging.Logger;

public class PlacementDAOImpl implements PlacementDAO {

	public static final Logger logger = Logger.getLogger(PlacementDAOImpl.class.getName());

	public void enterStudentsPlaced(int companyid, int regno) throws SQLException {
		Connection conn = null;
		PreparedStatement pst1 = null, pst2 = null, pst3 = null;
		ResultSet rs = null;
		String sql1 = "insert into studentsplaced values(?,?)";
		String sql2 = "update student set placedstatus = ? where regno = ?";
		String sql3 = "select * from student where regno = " + regno;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "zilkeradmin");
			pst3 = (PreparedStatement) conn.prepareStatement(sql3);
			rs = pst3.executeQuery();
			if(rs.next()) {
				logger.info("The student is already placed.");
				return;
			}
			pst1 = (PreparedStatement) conn.prepareStatement(sql1);
			pst1.setInt(1, companyid);
			pst1.setInt(2, regno);
			pst1.executeUpdate();
			pst2 = (PreparedStatement) conn.prepareStatement(sql2);
			pst2.setString(1, "yes");
			pst2.setInt(2, regno);
			pst2.executeUpdate();
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			pst1.close();
			pst2.close();
			conn.close();
		}
	}

	public boolean checkEligibilty(int regno, int companyid) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select cgpa, arrears, cgpacriteria, arrearcriteria from student s, company c where s.regno = "
				+ regno + " and c.companyid = " + companyid;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "zilkeradmin");
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			rs.next();
			if (rs.getFloat(1) < rs.getFloat(3) || rs.getInt(2) > rs.getInt(4)) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info("Error in MySQL");
		} finally {
			rs.close();
			pst.close();
			conn.close();
		}
		return false;
	}

	public void displayCompanyDetails(int companyid) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		if (companyid == 0) {
			sql = "select * from company";
		} else {
			sql = "select * from company where companyid = " + companyid;
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "zilkeradmin");
			pst = (PreparedStatement) conn.prepareStatement(sql);
			rs = pst.executeQuery();
			System.out.println("CompanyID\tName\tCGPACriteria\tArrearCriteria");
			while (rs.next()) {
				System.out.format("%6d%14s%14f%14d", rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4));
				System.out.println();
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			rs.close();
			pst.close();
			conn.close();
		}
	}

	public void displayStudentDetails(int regno) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		if (regno == 0) {
			sql = "select * from student";
		} else {
			sql = "select * from student where regno = " + regno;
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "zilkeradmin");
			pst = (PreparedStatement) conn.prepareStatement(sql);
			rs = pst.executeQuery();
			System.out.println("RegNo Name      Department Email               CGPA     Arrears Status");
			while (rs.next()) {
				System.out.format("%6d%10s%7s%20s%11f%5d%7s", rs.getInt(1), rs.getString(2), rs.getString(7),
						rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getString(6));
				System.out.println();
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			rs.close();
			pst.close();
			conn.close();
		}
	}

	public void displayDetailsDepartment(int regno, String deptName) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		if (regno == 0) {
			sql = "select * from student where deptname = " + deptName;
		} else {
			sql = "select * from student where deptname = " + deptName + " and regno = " + regno;
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "zilkeradmin");
			pst = (PreparedStatement) conn.prepareStatement(sql);
			rs = pst.executeQuery();
			System.out.println("RegNo Name      Department Email               CGPA     Arrears Status");
			while (rs.next()) {
				System.out.format("%6d%10s%7s%20s%11f%5d%7s", rs.getInt(1), rs.getString(2), rs.getString(7),
						rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getString(6));
				System.out.println();
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			rs.close();
			pst.close();
			conn.close();
		}
	}

	public void insertStudentDetails(StudentDetails studentdet) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "insert into student(regno,name,email,cgpa,arrears,placedstatus,deptname,createdby,createdat) values(?,?,?,?,?,?,?,?,?)";
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "zilkeradmin");
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(2, studentdet.getName());
			pst.setString(3, studentdet.getEmail());
			pst.setString(6, studentdet.getPlacedStatus());
			pst.setString(7, studentdet.getDeptName());
			pst.setInt(1, studentdet.getRegno());
			pst.setInt(5, studentdet.getArrears());
			pst.setFloat(4, studentdet.getCgpa());
			pst.setString(8, "Raxit");
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			pst.setTimestamp(9, timestamp);
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info("Problem inserting the values into the database");
		} finally {
			pst.close();
			conn.close();
		}
	}

	public void insertCompanyDetails(CompanyDetails companydet) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "insert into company(companyid,name,cgpacriteria,arrearcriteria,createdby,createdat) values(?,?,?,?,?,?)";
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "zilkeradmin");
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(2, companydet.getName());
			pst.setInt(1, companydet.getCompanyid());
			pst.setInt(4, companydet.getArrearCriteria());
			pst.setFloat(3, companydet.getCgpaCriteria());
			pst.setString(5, "Raxit");
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			pst.setTimestamp(6, timestamp);
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info("Problem inserting the values into the database");
		} finally {
			pst.close();
			conn.close();
		}
	}

	public void updateStudentDetails(StudentDetails studentDetails) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "update student set name = ?, email = ?, cgpa = ?, arrears = ?, placedstatus = ?, deptname = ?, updatedby = ?, updatedat = ? where regno = "
				+ studentDetails.getRegno();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "zilkeradmin");
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, studentDetails.getName());
			pst.setString(2, studentDetails.getEmail());
			pst.setString(5, studentDetails.getPlacedStatus());
			pst.setString(6, studentDetails.getDeptName());
			pst.setInt(4, studentDetails.getArrears());
			pst.setFloat(3, studentDetails.getCgpa());
			pst.setString(7, "Raxit");
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			pst.setTimestamp(8, timestamp);
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info("Problem inserting the values into the database");
		} finally {
			pst.close();
			conn.close();
		}
	}

	public void updateCompanyDetails(CompanyDetails companyDetails) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "update company set name = ?, cgpacriteria = ?, arrearcriteria = ?, updatedby = ?, updatedat = ? where companyid = "
				+ companyDetails.getCompanyid();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "zilkeradmin");
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, companyDetails.getName());
			pst.setInt(3, companyDetails.getArrearCriteria());
			pst.setFloat(2, companyDetails.getCgpaCriteria());
			pst.setString(4, "Raxit");
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			pst.setTimestamp(5, timestamp);
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info("Problem updating the values into the database");
		} finally {
			pst.close();
			conn.close();
		}
	}

	public boolean validateTeacher(String department, String password) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from teacher where dept = ? and password = ?";
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "zilkeradmin");
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, department);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			rs.close();
			pst.close();
			conn.close();
		}
		return false;
	}

	public void noOfStudentsPlaced(String deptName) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "zilkeradmin");
			if (deptName.equals("")) {
				pst = (PreparedStatement) conn.prepareStatement("select count(*) from student where placedstatus = ?");
				pst.setString(1, "yes");
				rs = pst.executeQuery();
				rs.next();
				logger.info("The number of students placed are : " + rs.getInt(1));
			} else {
				pst = (PreparedStatement) conn
						.prepareStatement("select count(*) from student where deptname = ? and placedstatus = ?");
				pst.setString(1, deptName);
				pst.setString(2, "yes");
				rs = pst.executeQuery();
				rs.next();
				logger.info("The number of students placed from " + deptName + " department are : " + rs.getInt(1));
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			rs.close();
			pst.close();
			conn.close();
		}
	}

	public void placementPercentage(String deptName) throws SQLException {
		Connection conn = null;
		PreparedStatement pst1 = null, pst2 = null;
		ResultSet rs1 = null, rs2 = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "zilkeradmin");
			if (deptName.equals("")) {
				pst1 = (PreparedStatement) conn.prepareStatement("select count(*) from student");
				rs1 = pst1.executeQuery();
				rs1.next();
				int totalCount = rs1.getInt(1);
				pst2 = (PreparedStatement) conn.prepareStatement("select count(*) from student where placedstatus = ?");
				pst2.setString(1, "yes");
				rs2 = pst2.executeQuery();
				rs2.next();
				int placedCount = rs2.getInt(1);
				int result = (placedCount * 100 / totalCount);
				logger.info("The total placement percentage is : " + result + "%");
			} else {
				pst1 = (PreparedStatement) conn.prepareStatement("select count(*) from student where deptname = ?");
				pst1.setString(1, deptName);
				rs1 = pst1.executeQuery();
				rs1.next();
				int totalCount = rs1.getInt(1);
				pst2 = (PreparedStatement) conn
						.prepareStatement("select count(*) from student where deptname = ? and placedstatus = ?");
				pst2.setString(1, deptName);
				pst2.setString(2, "yes");
				rs2 = pst2.executeQuery();
				rs2.next();
				int placedCount = rs2.getInt(1);
				int result = (placedCount * 100 / totalCount);
				logger.info("The placement percentage from " + deptName + " department are : " + result + "%");
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			rs1.close();
			rs2.close();
			pst1.close();
			pst2.close();
			conn.close();
		}
	}

}
