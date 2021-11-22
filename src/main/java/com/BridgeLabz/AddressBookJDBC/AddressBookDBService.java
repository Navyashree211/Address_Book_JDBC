/*
 * UC17: To Update the contact information in the address book for a person and ensure that the contact information in the memory is Sycn with DB .
 *      - To retrieve the contact information from DB for a particular person .
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

	public static void updateData() throws Exception {
		String qry = "update address_book.address_books set city = 'Udaipur' where firstName ='Navya'";
		try {

			con = ConnectionDB.createCP();

			pstmt = con.prepareStatement(qry);
			System.out.println("Data Update");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<ContactPerson> fetchContactPersonList() throws Exception {

		List<ContactPerson> details = new ArrayList<>();

		String qry1 = "select * from address_book.address_books where firstName ='Navya'";
		try {
			con = ConnectionDB.createCP();

			pstmt = con.prepareStatement(qry1);

			rs = pstmt.executeQuery();

			System.err.println("firstName-> " + "lastName-> " + "address-> " + "    city-> " + "state-> " + " zip-> "
					+ "phoneNumber-> ");

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

				Double zip = rs.getDouble(6);
				info.setZip(zip);

				Double phoneNumber = rs.getDouble(7);
				info.setPhoneNumber(phoneNumber);

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
		
		AddressBookDBService.updateData();
		AddressBookDBService.fetchContactPersonList();
	}

}