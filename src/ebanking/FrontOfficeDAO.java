package ebanking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.event.*;

public class FrontOfficeDAO {
	Connection con, con1, connection;
	ResultSet rs, rs1;
	Statement st, st1;
	PreparedStatement pst;
	JButton btn;
	String ids;
	Vector<String> name, namelist;
	JFrame frame1, frame2;
	static JTable table, table1;
	String[] columnNames = { "First Name", "Last Name", "Address", "Email", "Phone Number" };
	String[] columnNames1 = { "Date", "Amount", "From", "To" };
	String[] columnNames2 = { "Account No", "Balance" };
	String from;

	FrontOfficeDAO() {
		/**
		 * This class renders every method needed for database for the Front Desk
		 * Officer
		 */
	}

	public ArrayList<Client> getClientList() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select firstname, lastname from user");
		ArrayList<Client> clientsList = new ArrayList<Client>();
		Client client;
		String firstname = "";
		String lastname = "";
		while (rs.next()) {
			client = new Client();
			firstname = rs.getString("firstname");
			lastname = rs.getString("lastname");
			client.setGivenname(firstname);
			client.setSurname(lastname);
			clientsList.add(client);
		}
		return clientsList;
	}

	public void showTableData(String first_name, String givenname) {

		frame1 = new JFrame("Client List");
		frame1.setLayout(new BorderLayout());
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		String firstname = "";
		String lastname = "";
		String address = "";
		String email = "";
		String phone = "";
		try {
			pst = con.prepareStatement(
					"select * from user where firstname ='" + first_name + "' AND lastname ='" + givenname + "' ");
			ResultSet rs = pst.executeQuery();
			int i = 0;
			if (rs.next()) {
				firstname = rs.getString("firstname");
				lastname = rs.getString("lastname");
				address = rs.getString("address");
				email = rs.getString("email");
				phone = rs.getString("phone");
				model.addRow(new Object[] { firstname, lastname, address, email, phone });
				i++;
			}
			if (i < 1) {
				JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if (i == 1) {
				System.out.println(i + " Record Found");
			} else {
				System.out.println(i + " Records Found");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		frame1.add(scroll);
		frame1.setVisible(true);
		frame1.setSize(600, 400);
	}

	public void showBal(String first_name, String surname) {
		frame2 = new JFrame("Account Balance");
		frame2.setLayout(new BorderLayout());
		DefaultTableModel model1 = new DefaultTableModel();
		model1.setColumnIdentifiers(columnNames2);
		table1 = new JTable();
		table1.setModel(model1);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table1.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table1);
		scroll.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		try {
			con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
			String get_balance_query = "SELECT balance, account_no FROM bank_account INNER JOIN user ON fk_user_id = iduser WHERE firstname = '"
					+ first_name + "' AND lastname = '" + surname + "'";
			System.out.println(get_balance_query);
			Statement get_bal = con1.createStatement();
			ResultSet rs = get_bal.executeQuery(get_balance_query);
			int i = 0;
			while (rs.next()) {
				int act;
				int bal;
				act = rs.getInt("account_no");
				bal = rs.getInt("balance");
				System.out.println(+act + " first");
				System.out.println(+bal + " 2nd");
				model1.addRow(new Object[] { act, bal });
				i++;
			}
			if (i < 1) {
				JOptionPane.showMessageDialog(null, "This User has no money in his account", "Alert",
						JOptionPane.ERROR_MESSAGE);
			}
			if (i == 1) {
				System.out.println(i + " Record Found");
			} else {
				System.out.println(i + " Records Found");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		frame2.add(scroll);
		frame2.setVisible(true);
		frame2.setSize(400, 200);
	}

	public JScrollPane getHistory(String fname, String lname) throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
		String get_acct_query = "SELECT account_no FROM bank_account INNER JOIN user ON fk_user_id = iduser WHERE firstname = '"
				+ fname + "' AND lastname = '" + lname + "'";
		System.out.println(get_acct_query);
		Statement name = connection.createStatement();
		ResultSet rs = name.executeQuery(get_acct_query);
		rs.next();
		int usr = rs.getInt("account_no");
		JFrame history_frame = new JFrame("Client List");
		history_frame.setLayout(new BorderLayout());
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames1);
		JTable history_table = new JTable();
		history_table.setModel(model);
		history_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		history_table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(history_table);
		scroll.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		String date = "";
		String amount = "";
		String fr_account_no = "";
		String to_account_no = "";
		try {
			PreparedStatement pst = connection.prepareStatement(
					"select * from transaction where fr_account_no ='" + usr + "'OR to_account_no ='" + usr + "'");
			ResultSet rst = pst.executeQuery();
			int x = 0;
			while (rst.next()) {
				date = rst.getString("date");
				amount = rst.getString("amount");
				fr_account_no = rst.getString("fr_account_no");
				to_account_no = rst.getString("to_account_no");
				model.addRow(new Object[] { date, amount, fr_account_no, to_account_no });
				x++;
			}
			if (x < 1) {
				JOptionPane.showMessageDialog(null, "This User has no transaction history", "Alert",
						JOptionPane.ERROR_MESSAGE);
			}
			if (x == 1) {
				System.out.println(x + " Record Found");
			} else {
				System.out.println(x + " Records Found");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return scroll;
	}
}
