package org.zilker.implementation;

import java.sql.*;
import java.util.Calendar;
import java.util.logging.Logger;
import org.zilker.beans.CompanyDetails;
import org.zilker.beans.StudentDetails;
import org.zilker.dbutils.DBUtils;
import org.zilker.constants.Constants;

public class PlacementDAOImpl implements PlacementDAO {

	private static final Logger logger = Logger.getLogger(PlacementDAOImpl.class.getName());
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	public void enterStudentsPlaced(int companyid, int regno) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.VERIFY_PLACED_OR_NOT);
			rs = pst.executeQuery();
			if (rs.next()) {
				logger.severe("The student is already placed.");
				return;
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.INSERT_STUDENTS_PLACED);
			pst.setInt(1, companyid);
			pst.setInt(2, regno);
			pst.executeUpdate();
			pst = (PreparedStatement) conn.prepareStatement(Constants.UPDATE_PLACED_STATUS);
			pst.setString(1, "yes");
			pst.setInt(2, regno);
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.severe("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
	}

	public boolean checkEligibilty(int regno, int companyid) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			pst = conn.prepareStatement(Constants.CHECK_ELIGIBILITY);
			pst.setInt(1, regno);
			pst.setInt(2, companyid);
			rs = pst.executeQuery();
			if (conn != null) {
				logger.info("Connected");
			} else {
				logger.info("Not Connected");
			}
			if (!rs.next()) {
				logger.severe("You have entered the wrong details");
				return false;
			}
			if (rs.getFloat(1) < rs.getFloat(3) || rs.getInt(2) > rs.getInt(4)) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			logger.severe("Error connecting with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return false;
	}

	public void displayCompanyDetails(int companyid) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			if (companyid == 0) {
				pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_COMPANY_ALL);
			} else {
				pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_COMPANY_ONE);
				pst.setInt(1, companyid);
			}
			rs = pst.executeQuery();
			System.out.println("CompanyID\tName\tCGPACriteria\tArrearCriteria");
			while (rs.next()) {
				System.out.format("%6d%14s%14f%14d", rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4));
				System.out.println();
			}
		} catch (SQLException e) {
			logger.severe("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
	}

	public void displayStudentDetails(int regno) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			if (regno == 0) {
				pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_STUDENT_ALL);
			} else {
				pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_STUDENT_ONE);
				pst.setInt(1, regno);
			}
			rs = pst.executeQuery();
			System.out.println("RegNo Name      Department Email               CGPA     Arrears Status");
			while (rs.next()) {
				System.out.format("%6d%10s%7s%20s%11f%5d%7s", rs.getInt(1), rs.getString(2), rs.getString(7),
						rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getString(6));
				System.out.println();
			}
		} catch (SQLException e) {
			logger.severe("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
	}

	public void displayDetailsDepartment(int regno, String deptName) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			if (regno == 0) {
				pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_DEPARTMENT_ALL);
				pst.setString(1, deptName);
			} else {
				pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_DEPARTMENT_ONE);
				pst.setString(1, deptName);
				pst.setInt(2, regno);
			}
			rs = pst.executeQuery();
			System.out.println("RegNo Name      Department Email               CGPA     Arrears Status");
			while (rs.next()) {
				System.out.format("%6d%10s%7s%20s%11f%5d%7s", rs.getInt(1), rs.getString(2), rs.getString(7),
						rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getString(6));
				System.out.println();
			}
		} catch (SQLException e) {
			logger.severe("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
	}

	public void insertStudentDetails(StudentDetails studentdet) throws SQLException {
		String sql = "select * from student where regno = " + studentdet.getRegno();
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				logger.severe("The student entry is already present");
				return;
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.INSERT_STUDENT_DETAILS);
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
			logger.severe("Problem inserting the values into the database");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
	}

	public void insertCompanyDetails(CompanyDetails companydet) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.INSERT_COMPANY_DETAILS);
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
			logger.severe("Problem inserting the values into the database");
		} finally {
			DBUtils.closeConnection(conn, pst, null);
		}
	}

	public void updateStudentDetails(StudentDetails studentDetails) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.UPDATE_STUDENT_DETAILS);
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
			pst.setInt(9, studentDetails.getRegno());
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.severe("Problem updating the values into the database");
		} finally {
			DBUtils.closeConnection(conn, pst, null);
		}
	}

	public void updateCompanyDetails(CompanyDetails companyDetails) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.UPDATE_COMPANY_DETAILS);
			pst.setString(1, companyDetails.getName());
			pst.setInt(3, companyDetails.getArrearCriteria());
			pst.setFloat(2, companyDetails.getCgpaCriteria());
			pst.setString(4, "Raxit");
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			pst.setTimestamp(5, timestamp);
			pst.setInt(6, companyDetails.getCompanyid());
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info("Problem updating the values into the database");
		} finally {
			DBUtils.closeConnection(conn, pst, null);
		}
	}

	public boolean validateTeacher(String department, String password) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.VALIDATE_TEACHER);
			pst.setString(1, department);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			logger.severe("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return false;
	}

	public void noOfStudentsPlaced(String deptName) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			if (deptName.equals("")) {
				pst = (PreparedStatement) conn.prepareStatement(Constants.TOTAL_STUDENTS_PLACED);
				pst.setString(1, "yes");
				rs = pst.executeQuery();
				rs.next();
				logger.info("The number of students placed are : " + rs.getInt(1));
			} else {
				pst = (PreparedStatement) conn.prepareStatement(Constants.TOTAL_STUDENTS_PLACED_DEPARTMENT);
				pst.setString(1, deptName);
				pst.setString(2, "yes");
				rs = pst.executeQuery();
				rs.next();
				logger.info("The number of students placed from " + deptName + " department are : " + rs.getInt(1));
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
	}

	public void placementPercentage(String deptName) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			if (deptName.equals("")) {
				pst = (PreparedStatement) conn.prepareStatement(Constants.TOTAL_STUDENTS);
				rs = pst.executeQuery();
				rs.next();
				int totalCount = rs.getInt(1);
				pst = (PreparedStatement) conn.prepareStatement(Constants.TOTAL_STUDENTS_PLACED);
				pst.setString(1, "yes");
				rs = pst.executeQuery();
				rs.next();
				int placedCount = rs.getInt(1);
				int result = (placedCount * 100 / totalCount);
				logger.info("The total placement percentage is : " + result + "%");
			} else {
				pst = (PreparedStatement) conn.prepareStatement(Constants.TOTAL_STUDENTS_DEPARTMENT);
				pst.setString(1, deptName);
				rs = pst.executeQuery();
				rs.next();
				int totalCount = rs.getInt(1);
				pst = (PreparedStatement) conn.prepareStatement(Constants.TOTAL_STUDENTS_PLACED_DEPARTMENT);
				pst.setString(1, deptName);
				pst.setString(2, "yes");
				rs = pst.executeQuery();
				rs.next();
				int placedCount = rs.getInt(1);
				int result = (placedCount * 100 / totalCount);
				logger.info("The placement percentage from " + deptName + " department are : " + result + "%");
			}
		} catch (SQLException e) {
			logger.severe("Error retrieving values from MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
	}

}
