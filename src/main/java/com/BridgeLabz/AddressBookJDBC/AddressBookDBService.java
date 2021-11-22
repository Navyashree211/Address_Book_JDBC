/*
 * UC18: Ability to Retrieve Contact from the database that were added in a particular period .
 * 
 * @author : Navaya Shree
 * @since :  22-11-2021
 */
package com.BridgeLabz.AddressBookJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {
	static PreparedStatement pstmt;
	static Connection con;
	static ResultSet rs;

	public static List<ContactPerson> fetchContactPersonList() throws Exception {

		List<ContactPerson> details = new ArrayList<>();
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String qry = "select * from address_book.address_books   where date between Cast('2020-01-01' as date) and date (now())";
	try {
		con = ConnectionDB.createCP();

		pstmt = con.prepareStatement(qry);

		rs = pstmt.executeQuery();
		
		System.err.println("firstName-> " + "lastName-> " +  "address-> " + "    city-> "
				+ "state-> " + " zip-> " + "phoneNumber-> " + "date -> " );

		while (rs.next()) {   
			ContactPerson info = new ContactPerson();
			
			String firstName = rs.getString(1);
			info.setFirstName(firstName);
			
			String lastName = rs.getString(2);
			info.setLastName(lastName);
			
			String address = rs.getString(3);
			info.setAddress(address);
			
			String city = rs.getNString(4);
			info.setCity(city);
			
			String state = rs.getString(5);
			info.setState(state);
			
			long zip = rs.getLong(6);
			info.setZip(zip);
			
			long phoneNumber = rs.getLong(7);
			info.setPhoneNumber(phoneNumber);
			
			String date = rs.getString(8);
			info.setDate(date);
			
			details.add(info);
			System.out.println(info.toString());
			System.out.println();

		}

	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}

	finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (pstmt != null) {
			try {
				pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		System.out.println("Closed All Resources");
	}
	return details;

}
	
	public static void main(String[] args) throws Exception {
		AddressBookDBService.fetchContactPersonList();
	}
	
}