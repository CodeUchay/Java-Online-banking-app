package ebanking;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.util.*;
import java.time.*;

public class IntTrf {
	/**
	 * This class handles Internal transfers for the Client Gui
	 */
	int amount;
	int firstaccount, secondaccount;
	JButton btnNewButton;
	Connection connection, connection1, connection2;
	int iduser, bal1, bal2;
	
	public IntTrf(int firstaccount, int secondaccount, String amount) throws SQLException {
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
			JOptionPane.showMessageDialog(btnNewButton, "You do not have sufficient funds");
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

				JOptionPane.showMessageDialog(btnNewButton, "You have credited Account No: " + secondaccount
						+ " \n with " + this.amount + " Euros \n And your balance is " + addition + " Euros");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LocalDate date = LocalDate.now();
			Connection connection1;
			connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
			String trst_update_query1 = "INSERT into transaction (date, type,  amount, fr_account_no, to_account_no) VALUES ('"
					+ date + "','Credit','" + this.amount + "','" + firstaccount + "','" + secondaccount + "')";
			String trst_update_query2 = "INSERT into transaction (date, type, amount, fr_account_no, to_account_no) VALUES ('"
					+ date + "','Debit','" + this.amount + "','" + secondaccount + "','" + firstaccount + "')";
			Statement trst_update_query1_1 = connection1.createStatement();
			trst_update_query1_1.executeUpdate(trst_update_query1);
			Statement trst_update_query2_1 = connection1.createStatement();
			trst_update_query2_1.executeUpdate(trst_update_query2);
			System.out.println(trst_update_query1);
			System.out.println(trst_update_query2);

		}
	}

	public IntTrf(int theaccount, int amount) {
		String get_balance_query = "SELECT balance FROM bank_account WHERE account_no = '" + theaccount + "'";
		System.out.println(get_balance_query);
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
			Statement name = connection.createStatement();
			ResultSet rs = name.executeQuery(get_balance_query);
			rs.next();
			int bal1 = rs.getInt("balance");
			System.out.println("the balance is: " + bal1);
			int credit = amount + bal1;
			String credit_query = "UPDATE bank_account SET balance = '" + credit + "' WHERE account_no  = '"
					+ theaccount + "' ";
			System.out.println(credit_query);
			Statement stt = connection.createStatement();
			stt.executeUpdate(credit_query);
			System.out.println("credit to account is successful");

			LocalDate date = LocalDate.now();
			Connection connection2;
			connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
			String trst_update_query3 = "INSERT into transaction (date, type, amount, fr_account_no, to_account_no) VALUES ('"
					+ date + "','Credit','" + amount + "','Officer', '" + theaccount + "')";
			System.out.println("the transaction query is: " + trst_update_query3);
			Statement trst_update_query3_1 = connection2.createStatement();
			trst_update_query3_1.executeUpdate(trst_update_query3);
			System.out.println(trst_update_query3);

			JOptionPane.showMessageDialog(btnNewButton,
					"You have credited Account No: " + theaccount + " \n with " + amount + " Euros \n Thank You");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(btnNewButton, "You have input wrong details");
			e1.printStackTrace();
		}
	}
}
