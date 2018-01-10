package org.zilker.implementation;

import java.sql.SQLException;

import org.zilker.beans.ContactDetails;

public interface ContactDAO {
	public void insertDetails(ContactDetails contactDetails) throws SQLException;

	public void insertNumbers(ContactDetails contactDetails) throws SQLException;

	public void updateDetails(ContactDetails contactDetails) throws SQLException;

	public void updateNumbers(ContactDetails contactDetails) throws SQLException;

	public void displaySorted(int flag) throws SQLException;

	public void displayAll() throws SQLException;

	public void displayOne(String firstname) throws SQLException;
	
	public int getCount() throws SQLException;
}
