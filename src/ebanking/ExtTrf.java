package ebanking;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class ExtTrf {
	/**
	 * This class handles External transfers for the Client Gui
	 */

	int amount;
	int firstaccount, secondaccount;
	JButton btnNewButton1;
	Connection connection;
	int iduser, bal1, bal2;

	public ExtTrf(int firstaccount, int secondaccount, String amount) {

		this.amount = Integer.valueOf(amount);
		String get_balance1_query = "SELECT balance FROM bank_account WHERE account_no = '" + firstaccount + "'";
		System.out.println(get_balance1_query);
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
			Statement name = connection.createStatement();
			ResultSet rs = name.executeQuery(get_balance1_query);
			rs.next();
			int bal1 = rs.getInt("balance");
			this.bal1 = bal1;
			System.out.println("balance 1 = " + bal1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String get_balance2_query = "SELECT balance FROM bank_account WHERE account_no = '" + secondaccount + "'";
		System.out.println(get_balance2_query);
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
			Statement name = connection.createStatement();
			ResultSet rs = name.executeQuery(get_balance2_query);
			rs.next();
			int bal2 = rs.getInt("balance");
			System.out.println("balance 2 = " + bal2);
			this.bal2 = bal2;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// System.out.println("trfamount is: " + trfamount);
		System.out.println("transfer amount is: " + this.amount);
		if (this.amount > bal1) {
			JOptionPane.showMessageDialog(btnNewButton1, "You do not have sufficient funds");
		} else {
			int deduction = bal1 - this.amount;
			int addition = bal2 + this.amount;
			Connection connection;
			String deduction_query = "UPDATE bank_account SET balance = '" + deduction + "' WHERE account_no  = '"
					+ firstaccount + "' ";
			String addition_query = "UPDATE bank_account SET balance = '" + addition + "' WHERE account_no  = '"
					+ secondaccount + "' ";
			System.out.println(deduction_query);
			System.out.println(addition_query);
			try {
				// first balance deduction query
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
				Statement debit = connection.createStatement();
				debit.executeUpdate(deduction_query);
				System.out.println("debit from first account is successful");

				// Second balance deduction query
				Statement credit = connection.createStatement();
				credit.executeUpdate(addition_query);
				System.out.println("credit to first account is successful");

				JOptionPane.showMessageDialog(btnNewButton1, "You have credited Account No: " + secondaccount
						+ " \n with " + this.amount + " Euros \n And your balance is " + deduction + " Euros");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
