package ebanking;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class ModifyProfile {
	/**
	 * This class contains every method used to modify profile of the Client
	 */
	JButton btnNewButton;

	public ModifyProfile(int user, String email, String phone, String address, String password1) {

		String modify_profile_query = "UPDATE user SET address = '" + address + "', phone = '" + phone + "', email = '"
				+ email + "', password = '" + password1 + "' WHERE iduser = '" + user + "' ";

		System.out.println(modify_profile_query);

		// insert new user into table
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
			Statement st = connection.createStatement();
			st.executeUpdate(modify_profile_query);
			System.out.println("Profile Updated");
			JOptionPane.showMessageDialog(btnNewButton, "your profile has been updated ");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(btnNewButton, "Phone number out of range");
			e1.printStackTrace();
		}
	}

}
