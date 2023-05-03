package ebanking;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;

public class FrontOfficeHome extends JFrame {
	JTabbedPane officehomepane;
	JPanel home, clientlist, history;
	JLabel lblcredit, lblaccount, lblamount, lblselect;
	JTextField txtaccount, txtamount;
	JButton send, info, btnNewButton, bal, view;
	JComboBox list, list1, list2;
	FrontOfficeDAO client = new FrontOfficeDAO();

	FrontOfficeHome() throws SQLException {
		/**
		 * This is the first GUI of the Front Desk Officer
		 */

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(450, 190, 1014, 597);
		this.setResizable(false);
		setVisible(true);
		getContentPane().setBackground(new Color(75, 235, 140));
		getContentPane().setForeground(new Color(155, 67, 208));

		officehomepane = new JTabbedPane();
		officehomepane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		officehomepane.setFocusable(false);
		officehomepane.setBackground(new Color(50, 168, 82));
		// officehomepane.setForeground(new Color(4, 125, 37));

		// tabs
		home = new JPanel();
		home.setLayout(null);
		home.setBackground(new Color(130, 232, 171));
		home.setForeground(new Color(205, 133, 63));
		clientlist = new JPanel();
		clientlist.setLayout(null);
		clientlist.setBackground(new Color(130, 232, 171));
		clientlist.setForeground(new Color(205, 133, 63));
		history = new JPanel();
		history.setLayout(null);
		history.setBackground(new Color(130, 232, 171));

		// add tabs panel to frame
		officehomepane.add("Home", home);
		officehomepane.add("Clients", clientlist);
		// officehomepane.add("History", history);
		officehomepane.setVisible(true);
		officehomepane.show(true);
		this.add(officehomepane);

		JLabel instruction = new JLabel("Credit An Account");
		instruction.setFont(new Font("Times New Roman", Font.BOLD, 32));
		instruction.setBounds(20, 10, 325, 50);
		home.add(instruction);

		lblaccount = new JLabel("Account No: ");
		lblaccount.setBounds(20, 90, 325, 50);
		txtaccount = new JTextField();
		txtaccount.setBounds(100, 100, 170, 30);

		lblamount = new JLabel("Amount: ");
		lblamount.setBounds(20, 150, 325, 50);
		txtamount = new JTextField();
		txtamount.setBounds(100, 160, 170, 30);

		// send button for internal transfer
		send = new JButton("Send");
		send.setBounds(20, 220, 100, 30);
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ext) {
				int selectaccount = (int) Integer.parseInt(txtaccount.getText());
				int ammount = (int) Integer.parseInt(txtamount.getText());

				if (ammount > 0) {
					new IntTrf(selectaccount, ammount);
				} else if (ammount < 0) {
					JOptionPane.showMessageDialog(btnNewButton, "Choose correct Details or Amount Greater than Zero");
				}
			}
		});
		home.add(lblaccount);
		home.add(txtaccount);
		home.add(lblamount);
		home.add(txtamount);
		home.add(send);

		// client list
		System.out.println("==>" + client.getClientList());
		// JList<String> lists = new JList<String>(client.getClientList());
		// home.add(new JScrollPane(lists));
		// lists.setVisible(true);
		// lists.setBounds(300, 110, 150, 20);
		Vector<String> vector = new Vector<String>();
		System.out.println(client.getClientList().size());
		for (int i = 0; i < client.getClientList().size(); i++) {
			vector.add(client.getClientList().get(i).getGivenname() + " " + client.getClientList().get(i).getSurname());
		}
		System.out.println("=0=>" + vector);
		list = new JComboBox<String>(vector);
		list.setBounds(170, 90, 160, 25);
		clientlist.add(list);

		JLabel instruction2 = new JLabel("Fetch Client Information");
		instruction2.setFont(new Font("Serif", Font.BOLD, 30));
		instruction2.setBounds(30, 20, 350, 40);
		lblselect = new JLabel("Select name: ");
		lblselect.setBounds(30, 90, 120, 26);
		lblselect.setFont(new Font("arial", Font.BOLD, 15));
		info = new JButton("information");
		info.setBounds(30, 160, 120, 30);
		info.addActionListener((new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource() == info) {

					try {
						String firstname;
						String surname;
						firstname = client.getClientList().get(list.getSelectedIndex()).getGivenname();
						surname = client.getClientList().get(list.getSelectedIndex()).getSurname();
						client.showTableData(firstname, surname);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}));
		bal = new JButton("Balance");
		bal.setBounds(30, 200, 100, 30);
		bal.addActionListener((new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource() == bal) {

					try {
						String firstname;
						String surname;
						firstname = client.getClientList().get(list.getSelectedIndex()).getGivenname();
						surname = client.getClientList().get(list.getSelectedIndex()).getSurname();
						client.showBal(firstname, surname);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}));

		view = new JButton("View History");
		view.setBounds(30, 240, 150, 30);
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent hst) {
				if (hst.getSource() == view) {
					String firstname;
					String surname;
					try {
						firstname = client.getClientList().get(list.getSelectedIndex()).getGivenname();
						surname = client.getClientList().get(list.getSelectedIndex()).getSurname();
						JScrollPane his = client.getHistory(firstname, surname);

						his.setBounds(300, 250, 400, 100);
						his.setVisible(true);
						his.setSize(500, 200);
						clientlist.add(his);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

		clientlist.add(instruction2);
		clientlist.add(lblselect);
		;
		clientlist.add(info);
		clientlist.add(bal);
		clientlist.add(view);

	}

	public static void main(String args[]) throws SQLException {

		FrontOfficeHome x = new FrontOfficeHome();
		x.setVisible(true);
	}
}