package org.zilker.raxit;

import java.sql.SQLException;

public interface ContactDAO {
	public void insertDetails(ContactDetails contactDetails) throws SQLException;
	public void insertNumbers(ContactDetails contactDetails) throws SQLException;
	public void updateDetails(ContactDetails contactDetails) throws SQLException;
	public void updateNumbers(ContactDetails contactDetails) throws SQLException;
	public void displaySorted(int flag) throws SQLException;
	public void displayAll() throws SQLException;
	public void displayOne(int contactid) throws SQLException;
}
