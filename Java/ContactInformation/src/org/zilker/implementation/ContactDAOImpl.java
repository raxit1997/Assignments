package org.zilker.implementation;

import java.sql.*;
import java.util.Calendar;
import java.util.logging.Logger;

import org.zilker.beans.ContactDetails;
import org.zilker.constants.Constants;
import org.zilker.dbutils.DBUtils;

public class ContactDAOImpl implements ContactDAO {

	public static final Logger logger = Logger.getLogger(ContactDAOImpl.class.getName());
	private Connection conn = null;
	private PreparedStatement pst = null, pst2 = null;
	private ResultSet rs = null, rs2 = null;

	public void insertDetails(ContactDetails contactDetails) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.INSERT_DETAILS);
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
			logger.info("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, null);
		}
	}

	public void insertNumbers(ContactDetails contactDetails) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.INSERT_HOME);
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			if (contactDetails.getHomeAreaCode() != null) {
				pst.setInt(1, contactDetails.getContactID());
				pst.setString(2, contactDetails.getHomeAreaCode());
				pst.setString(3, contactDetails.getHomeCountryCode());
				pst.setString(4, contactDetails.getHomeNumber());
				pst.setString(5, "Raxit");
				pst.setTimestamp(6, timestamp);
				pst.executeUpdate();
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.INSERT_OFFICE);
			if (contactDetails.getOfficeExtensionCode() != null) {
				pst.setInt(1, contactDetails.getContactID());
				pst.setString(2, contactDetails.getOfficeExtensionCode());
				pst.setString(3, contactDetails.getOfficeNumber());
				pst.setString(4, "Raxit");
				pst.setTimestamp(5, timestamp);
				pst.executeUpdate();
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.INSERT_MOBILE);
			if (contactDetails.getMobileCountryCode() != null) {
				pst.setInt(1, contactDetails.getContactID());
				pst.setString(2, contactDetails.getMobileCountryCode());
				pst.setString(3, contactDetails.getMobileNumber());
				pst.setString(4, "Raxit");
				pst.setTimestamp(5, timestamp);
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, null);
		}
	}

	public void updateDetails(ContactDetails contactDetails) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.UPDATE_DETAILS);
			pst.setString(1, contactDetails.getLastName());
			pst.setString(2, contactDetails.getEmail());
			pst.setString(3, "Raxit");
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			pst.setTimestamp(4, timestamp);
			pst.setString(5, contactDetails.getFirstName());
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, null);
		}
	}

	public void updateNumbers(ContactDetails contactDetails) throws SQLException {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactsapp", "root", "zilkeradmin");
			pst = (PreparedStatement) conn.prepareStatement(Constants.UPDATE_HOME);
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			if (contactDetails.getHomeAreaCode() != null) {
				pst.setString(1, contactDetails.getHomeAreaCode());
				pst.setString(2, contactDetails.getHomeCountryCode());
				pst.setString(3, contactDetails.getHomeNumber());
				pst.setString(4, "Raxit");
				pst.setTimestamp(5, timestamp);
				pst.executeUpdate();
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.UPDATE_OFFICE);
			if (contactDetails.getOfficeExtensionCode() != null) {
				pst.setString(2, contactDetails.getOfficeExtensionCode());
				pst.setString(3, contactDetails.getOfficeNumber());
				pst.setString(4, "Raxit");
				pst.setTimestamp(5, timestamp);
				pst.executeUpdate();
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.UPDATE_MOBILE);
			if (contactDetails.getMobileCountryCode() != null) {
				pst.setInt(1, contactDetails.getContactID());
				pst.setString(2, contactDetails.getMobileCountryCode());
				pst.setString(3, contactDetails.getMobileNumber());
				pst.setString(4, "Raxit");
				pst.setTimestamp(5, timestamp);
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, null);
		}
	}

	public void displayAll() throws SQLException {
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_ALL);
			rs = pst.executeQuery();
			while (rs.next()) {
				System.out.println("\nFirst Name : " + rs.getString(2) + "\nLast Name : " + rs.getString(3)
						+ "\nEmailID : " + rs.getString(4));
				pst2 = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_HOME);
				pst2.setInt(1, rs.getInt(1));
				rs2 = pst2.executeQuery();
				if (rs2.next()) {
					System.out.println("Home Area Code : " + rs2.getString(2) + "\nHome Country Code : "
							+ rs2.getString(3) + "\nHome Area Code : " + rs2.getString(4));
				}
				pst2 = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_OFFICE);
				pst2.setInt(1, rs.getInt(1));
				rs2 = pst2.executeQuery();
				if (rs2.next()) {
					System.out.println(
							"Office Extension Code : " + rs2.getString(2) + "\nOffice Number : " + rs2.getString(3));
				}
				pst2 = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_MOBILE);
				pst2.setInt(1, rs.getInt(1));
				rs2 = pst2.executeQuery();
				if (rs2.next()) {
					System.out.println(
							"Mobile Country Code : " + rs2.getString(2) + "\nMobile Number : " + rs2.getString(3));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
			DBUtils.closeConnection(conn, pst2, rs2);
		}
	}

	public void displayOne(String firstname) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_ONE);
			pst.setString(1, firstname);
			rs = pst.executeQuery();
			if (!rs.next()) {
				logger.warning("No such record with first name as : " + firstname);
				return;
			}
			System.out.println("\nFirst Name : " + rs.getString(2) + "\nLast Name : " + rs.getString(3) + "\nEmailID : "
					+ rs.getString(4));
			pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_HOME);
			pst.setInt(1, rs.getInt(1));
			rs = pst.executeQuery();
			if (rs.next()) {
				System.out.println("Home Area Code : " + rs.getString(2) + "\nHome Country Code : " + rs.getString(3)
						+ "\nHome Area Code : " + rs.getString(4));
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_OFFICE);
			pst.setInt(1, rs.getInt(1));
			rs = pst.executeQuery();
			if (rs.next()) {
				System.out
						.println("Office Extension Code : " + rs.getString(2) + "\nOffice Number : " + rs.getString(3));
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_MOBILE);
			pst.setInt(1, rs.getInt(1));
			rs = pst.executeQuery();
			if (rs.next()) {
				System.out.println("Mobile Country Code : " + rs.getString(2) + "\nMobile Number : " + rs.getString(3));
			}
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
	}

	public void displaySorted(int flag) throws SQLException {
		try {
			conn = DBUtils.getConnection();
			if (flag == 1) {
				pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_SORT_FIRST_NAME);
			} else {
				pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_SORT_LAST_NAME);
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				System.out.println("\nFirst Name : " + rs.getString(2) + "\nLast Name : " + rs.getString(3)
						+ "\nEmailID : " + rs.getString(4));
				pst2 = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_HOME);
				pst2.setInt(1, rs.getInt(1));
				rs2 = pst2.executeQuery();
				if (rs2.next()) {
					System.out.println("Home Area Code : " + rs2.getString(2) + "\nHome Country Code : "
							+ rs2.getString(3) + "\nHome Area Code : " + rs2.getString(4));
				}
				pst2 = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_OFFICE);
				pst2.setInt(1, rs.getInt(1));
				rs2 = pst2.executeQuery();
				if (rs2.next()) {
					System.out.println(
							"Office Extension Code : " + rs2.getString(2) + "\nOffice Number : " + rs2.getString(3));
				}
				pst2 = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_MOBILE);
				pst2.setInt(1, rs.getInt(1));
				rs2 = pst2.executeQuery();
				if (rs2.next()) {
					System.out.println(
							"Mobile Country Code : " + rs2.getString(2) + "\nMobile Number : " + rs2.getString(3));
				}
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
			DBUtils.closeConnection(conn, pst2, rs2);
		}
	}

	public int getCount() throws SQLException {
		try {
			conn = DBUtils.getConnection();
			pst = conn.prepareStatement(Constants.TOTAL_COUNT);
			rs = pst.executeQuery();
			return rs.getInt(1);
		} catch(SQLException e) {
			logger.warning("Error retrieving count from MySQL");
		}
		return 0;
	}

}
