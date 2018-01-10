package org.zilker.constants;

public class Constants {

	public static final String INSERT_DETAILS = "insert into details(contactid, firstname, lastname, email, createdby, createdat) values(?,?,?,?,?,?)";
	public static final String INSERT_HOME = "insert into home(contactid, homeareacode, homecountrycode, homenumber, createdby, createdat) values(?,?,?,?,?,?)";
	public static final String INSERT_OFFICE = "insert into office(contactid, officeextensionno, officenumber, createdby, createdat) values(?,?,?,?,?)";
	public static final String INSERT_MOBILE = "insert into mobile(contactid, mobilecountrycode, mobilenumber, createdby, createdat) values(?,?,?,?,?)";
	public static final String UPDATE_DETAILS = "update details set lastname = ?, email = ?, updatedby = ?, updatedat = ? where firstname = ?";
	public static final String UPDATE_HOME = "update home set homeareacode = ?, homecountrycode = ?, homenumber = ?, updatedby = ?, updatedat = ? where contactid = ?";
	public static final String UPDATE_OFFICE = "update office set officeextensionno = ?, officenumber = ?, updatedby = ?, updatedat = ? where contactid = ?";
	public static final String UPDATE_MOBILE = "update mobile set mobilecountrycode = ?, mobilenumber = ?, updatedby = ?, updatedat = ? where contactid = ?";
	public static final String DISPLAY_ALL = "select * from details";
	public static final String DISPLAY_HOME = "select * from home where contactid = ?";
	public static final String DISPLAY_OFFICE = "select * from office where contactid = ?";
	public static final String DISPLAY_MOBILE = "select * from mobile where contactid = ?";
	public static final String DISPLAY_ONE = "select * from details where firstname = ?";
	public static final String DISPLAY_SORT_LAST_NAME = "select * from details order by firstname";
	public static final String DISPLAY_SORT_FIRST_NAME = "select * from details order by lastname";
	public static final String TOTAL_COUNT = "select count(*) from details";

}
