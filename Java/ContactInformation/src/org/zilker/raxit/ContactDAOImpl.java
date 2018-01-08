package org.zilker.raxit;

import java.sql.*;
import java.util.Calendar;
import java.util.logging.Logger;

public class ContactDAOImpl implements ContactDAO {

	public static final Logger logger = Logger.getLogger(ContactDAOImpl.class.getName());

	public void insertDetails(ContactDetails contactDetails) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "insert into details(contactid, firstname, lastname, email, createdby, createdat) values(?,?,?,?,?,?)";
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactsapp", "root", "zilkeradmin");
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, contactDetails.getContactID());
			pst.setString(2, contactDetails.getFirstName());
			pst.setString(3, contactDetails.getLastName());
			pst.setString(4, contactDetails.getEmail());
			pst.setString(5, "Raxit");
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			pst.setTimestamp(6, timestamp);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info("Error connecting it with MySQL");
		} finally {
			pst.close();
			conn.close();
		}
	}

	public void insertNumbers(ContactDetails contactDetails) throws SQLException {
		Connection conn = null;
		PreparedStatement pst1 = null, pst2 = null, pst3 = null;
		String sql1 = "insert into home(contactid, homeareacode, homecountrycode, homenumber, createdby, createdat) values(?,?,?,?,?,?)";
		String sql2 = "insert into office(contactid, officeextensionno, officenumber, createdby, createdat) values(?,?,?,?,?)";
		String sql3 = "insert into mobile(contactid, mobilecountrycode, mobilenumber, createdby, createdat) values(?,?,?,?,?)";
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactsapp", "root", "zilkeradmin");
			pst1 = (PreparedStatement) conn.prepareStatement(sql1);
			pst2 = (PreparedStatement) conn.prepareStatement(sql2);
			pst3 = (PreparedStatement) conn.prepareStatement(sql3);
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			if (contactDetails.getOfficeExtensionCode() != null) {
				pst2.setInt(1, contactDetails.getContactID());
				pst2.setString(2, contactDetails.getOfficeExtensionCode());
				pst2.setString(3, contactDetails.getOfficeNumber());
				pst2.setString(4, "Raxit");
				pst2.setTimestamp(5, timestamp);
				pst2.executeUpdate();
			}
			if (contactDetails.getHomeAreaCode() != null) {
				pst1.setInt(1, contactDetails.getContactID());
				pst1.setString(2, contactDetails.getHomeAreaCode());
				pst1.setString(3, contactDetails.getHomeCountryCode());
				pst1.setString(4, contactDetails.getHomeNumber());
				pst1.setString(5, "Raxit");
				pst1.setTimestamp(6, timestamp);
				pst1.executeUpdate();
			}
			if (contactDetails.getMobileCountryCode() != null) {
				pst3.setInt(1, contactDetails.getContactID());
				pst3.setString(2, contactDetails.getMobileCountryCode());
				pst3.setString(3, contactDetails.getMobileNumber());
				pst3.setString(4, "Raxit");
				pst3.setTimestamp(5, timestamp);
				pst3.executeUpdate();
			}

		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			pst1.close();
			pst2.close();
			pst3.close();
			conn.close();
		}
	}

	public void updateDetails(ContactDetails contactDetails) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "update details set firstname = ?, lastname = ?, email = ?, updatedby = ?, updatedat = ? where contactid = " + contactDetails.getContactID();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactsapp", "root", "zilkeradmin");
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, contactDetails.getFirstName());
			pst.setString(2, contactDetails.getLastName());
			pst.setString(3, contactDetails.getEmail());
			pst.setString(4, "Raxit");
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			pst.setTimestamp(5, timestamp);
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			pst.close();
			conn.close();
		}
	}

	public void updateNumbers(ContactDetails contactDetails) throws SQLException {
		Connection conn = null;
		PreparedStatement pst1 = null, pst2 = null, pst3 = null;
		String sql1 = "update home set homeareacode = ?, homecountrycode = ?, homenumber = ?, updatedby = ?, updatedat = ? where contactid = " + contactDetails.getContactID();
		String sql2 = "update office set officeextensionno = ?, officenumber = ?, updatedby = ?, updatedat = ? where contactid = " + contactDetails.getContactID();
		String sql3 = "update office set mobilecountrycode = ?, mobilenumber = ?, updatedby = ?, updatedat = ? where contactid = " + contactDetails.getContactID();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactsapp", "root", "zilkeradmin");
			pst1 = (PreparedStatement) conn.prepareStatement(sql1);
			pst2 = (PreparedStatement) conn.prepareStatement(sql2);
			pst3 = (PreparedStatement) conn.prepareStatement(sql3);
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			if (contactDetails.getOfficeExtensionCode() != null) {
				pst2.setString(2, contactDetails.getOfficeExtensionCode());
				pst2.setString(3, contactDetails.getOfficeNumber());
				pst2.setString(4, "Raxit");
				pst2.setTimestamp(5, timestamp);
				pst2.executeUpdate();
			}
			if (contactDetails.getHomeAreaCode() != null) {
				pst1.setString(1, contactDetails.getHomeAreaCode());
				pst1.setString(2, contactDetails.getHomeCountryCode());
				pst1.setString(3, contactDetails.getHomeNumber());
				pst1.setString(4, "Raxit");
				pst1.setTimestamp(5, timestamp);
				pst1.executeUpdate();
			}
			if (contactDetails.getMobileCountryCode() != null) {
				pst3.setInt(1, contactDetails.getContactID());
				pst3.setString(2, contactDetails.getMobileCountryCode());
				pst3.setString(3, contactDetails.getMobileNumber());
				pst3.setString(4, "Raxit");
				pst3.setTimestamp(5, timestamp);
				pst3.executeUpdate();
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			pst1.close();
			pst2.close();
			pst3.close();
			conn.close();
		}
	}

	public void displayAll() throws SQLException {
		Connection conn = null;
		PreparedStatement pst1 = null, pst2 = null, pst3 = null, pst4 = null;
		ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null;
		String sql1 = "select * from details";
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactsapp", "root", "zilkeradmin");
			pst1 = (PreparedStatement) conn.prepareStatement(sql1);
			rs1 = pst1.executeQuery();
			while (rs1.next()) {
				System.out.println("\nContactID : " + rs1.getInt(1) + "\nFirst Name : " + rs1.getString(2) + "\nLast Name : "
						+ rs1.getString(3) + "\nEmailID : " + rs1.getString(4));
				String sql2 = "select * from home where contactid = " + rs1.getInt(1);
				String sql3 = "select * from office where contactid = " + rs1.getInt(1);
				String sql4 = "select * from mobile where contactid = " + rs1.getInt(1);
				pst2 = (PreparedStatement) conn.prepareStatement(sql2);
				rs2 = pst2.executeQuery();
				pst3 = (PreparedStatement) conn.prepareStatement(sql3);
				rs3 = pst3.executeQuery();
				pst4 = (PreparedStatement) conn.prepareStatement(sql4);
				rs4 = pst4.executeQuery();
				if (rs2.next()) {
					System.out.println("Home Area Code : " + rs2.getString(2) + "\nHome Country Code : " + rs2.getString(3)
							+ "\nHome Area Code : " + rs2.getString(4));
				}
				if (rs3.next()) {
					System.out.println(
							"Office Extension Code : " + rs3.getString(2) + "\nOffice Number : " + rs3.getString(3));
				}
				if (rs4.next()) {
					System.out.println(
							"Mobile Country Code : " + rs4.getString(2) + "\nMobile Number : " + rs4.getString(3));
				}
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			rs1.close();
			rs2.close();
			rs3.close();
			rs4.close();
			pst1.close();
			pst2.close();
			pst3.close();
			pst4.close();
			conn.close();
		}
	}

	public void displayOne(int contactid) throws SQLException {
		Connection conn = null;
		PreparedStatement pst1 = null, pst2 = null, pst3 = null, pst4 = null;
		ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null;
		String sql1 = "select * from details where contactid = " + contactid;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactsapp", "root", "zilkeradmin");
			pst1 = (PreparedStatement) conn.prepareStatement(sql1);
			rs1 = pst1.executeQuery();
			rs1.next();
			System.out.println("ContactID : " + rs1.getInt(1) + "\nFirst Name : " + rs1.getString(2) + "\nLast Name : "
					+ rs1.getString(3) + "\nEmailID : " + rs1.getString(4));
			String sql2 = "select * from home where contactid = " + rs1.getInt(1);
			String sql3 = "select * from office where contactid = " + rs1.getInt(1);
			String sql4 = "select * from mobile where contactid = " + rs1.getInt(1);
			pst2 = (PreparedStatement) conn.prepareStatement(sql2);
			rs2 = pst2.executeQuery();
			pst3 = (PreparedStatement) conn.prepareStatement(sql3);
			rs3 = pst3.executeQuery();
			pst4 = (PreparedStatement) conn.prepareStatement(sql4);
			rs4 = pst4.executeQuery();
			if (rs2.next()) {
				System.out.println("Home Area Code : " + rs2.getString(2) + "\nHome Country Code : " + rs2.getString(3)
						+ "\nHome Area Code : " + rs2.getString(4));
			}
			if (rs3.next()) {
				System.out.println("Office Extension Code : " + rs3.getString(2) + "\nOffice Number : " + rs3.getString(3));
			}
			if (rs4.next()) {
				System.out.println("Mobile Country Code : " + rs4.getString(2) + "\nMobile Number : " + rs4.getString(3));
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			rs1.close();
			rs2.close();
			rs3.close();
			rs4.close();
			pst1.close();
			pst2.close();
			pst3.close();
			pst4.close();
			conn.close();
		}
	}

	public void displaySorted(int flag) throws SQLException {
		Connection conn = null;
		PreparedStatement pst1 = null, pst2 = null, pst3 = null, pst4 = null;
		ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null;
		String sql1 = null;
		if (flag == 1) {
			sql1 = "select * from details order by firstname";
		} else {
			sql1 = "select * from details order by lastname";
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactsapp", "root", "zilkeradmin");
			pst1 = (PreparedStatement) conn.prepareStatement(sql1);
			rs1 = pst1.executeQuery();
			while (rs1.next()) {
				System.out.println("\nContactID : " + rs1.getInt(1) + "\nFirst Name : " + rs1.getString(2) + "\nLast Name : "
						+ rs1.getString(3) + "\nEmailID : " + rs1.getString(4));
				String sql2 = "select * from home where contactid = " + rs1.getInt(1);
				String sql3 = "select * from office where contactid = " + rs1.getInt(1);
				String sql4 = "select * from mobile where contactid = " + rs1.getInt(1);
				pst2 = (PreparedStatement) conn.prepareStatement(sql2);
				rs2 = pst2.executeQuery();
				pst3 = (PreparedStatement) conn.prepareStatement(sql3);
				rs3 = pst3.executeQuery();
				pst4 = (PreparedStatement) conn.prepareStatement(sql4);
				rs4 = pst4.executeQuery();
				if (rs2.next()) {
					System.out.println("Home Area Code : " + rs2.getString(2) + "\nHome Country Code : " + rs2.getString(3)
							+ "\nHome Area Code : " + rs2.getString(4));
				}
				if (rs3.next()) {
					System.out.println(
							"Office Extension Code : " + rs3.getString(2) + "\nOffice Number : " + rs3.getString(3));
				}
				if (rs4.next()) {
					System.out.println(
							"Mobile Country Code : " + rs4.getString(2) + "\nMobile Number : " + rs4.getString(3));
				}
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			rs1.close();
			rs2.close();
			rs3.close();
			rs4.close();
			pst1.close();
			pst2.close();
			pst3.close();
			pst4.close();
			conn.close();
		}
	}

}
