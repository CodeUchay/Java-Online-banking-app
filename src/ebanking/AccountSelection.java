package ebanking;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class AccountSelection implements ItemListener {
	//This class selects accounts from the client and then gets the balance from the database
	JButton btnNewButton;
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			Object item = event.getItem();
			String get_balance_query = "SELECT balance FROM bank_account WHERE account_no = '" + item + "'";
			System.out.println(get_balance_query);

			try {
				Statement name;
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root",
						"root");
				name = connection.createStatement();
				ResultSet rs;
				try {
					rs = name.executeQuery(get_balance_query);
					try {
						rs.next();
						try {
							int bal = rs.getInt("balance");
							JOptionPane.showMessageDialog(btnNewButton, "Your balance is: " + bal);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
