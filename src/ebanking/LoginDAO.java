package ebanking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JOptionPane;

public class LoginDAO {
	/**
	 * This class renders every Database query method needed for Client Login
	 */

	Connection connection;
	Random random;
	int role;

	LoginDAO() throws SQLException {

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");

	}

	public int checklogin(String email, String password) throws SQLException {

		PreparedStatement st = (PreparedStatement) connection
				.prepareStatement("Select email, password, type from user where email=? and password=?");

		st.setString(1, email);
		st.setString(2, password);
		System.out.println(st.toString());
		ResultSet rs = st.executeQuery();
		rs.next();
		role = rs.getInt("type");
		if (rs.getRow() == 1) {
			System.out.println("You have successfully logged in");

			return role;
		} else {
			System.out.println("Wrong Username & Password");
			return 0;
		}

	}

}
