package ebanking;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

public class ClientHome extends JFrame {
	private JTabbedPane clienthomepane;
	private JPanel home, history, profile;
	private JLabel name1, transfers, account, account2, from1, from2, to1, to2, internal,
			external, amount1, amount2, account_no1, account_no2, account_no3, lblemail, phone, address, password1,
			password2;;
	private JComboBox selectaccount1, selectaccount2, selectaccount3, selectaccount4, selectaccount5;
	private JTextField amount_in, amount_ex, account_no, emailtxt, phonetxt, addresstxt, passwordtxt1;
	private JButton send1, send2, btnNewButton, view, save, create, savecsv;

	ClientDAO details, details1, details2;
	IntTrfSelection sel1, sel2;
	UserDAO creation;

	ClientHome(String email) throws SQLException {
		/**
		 * This class is the first page of the Client Actor
		 */
		details = new ClientDAO(email);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\vivre.png"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(450, 190, 1014, 597);
		this.setResizable(false);
		setVisible(true);
		getContentPane().setBackground(new Color(205, 133, 63));
		getContentPane().setForeground(new Color(155, 67, 208));
		UIManager.put("TabbedPane.selected", Color.black);

		// tabbed pane
		clienthomepane = new JTabbedPane();
		clienthomepane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		clienthomepane.setFocusable(false);
		clienthomepane.setBackground(new Color(205, 133, 63));
		clienthomepane.setForeground(new Color(139, 69, 19));

		// tabs
		home = new JPanel();
		home.setLayout(null);
		home.setBackground(new Color(255, 253, 208));
		home.setForeground(new Color(205, 133, 63));
		history = new JPanel();
		history.setLayout(null);
		history.setBackground(new Color(255, 253, 208));
		profile = new JPanel();
		profile.setLayout(null);
		profile.setBackground(new Color(255, 253, 208));

		// add tabs panel to frame
		clienthomepane.add("Home", home);
		clienthomepane.add("History", history);
		clienthomepane.add("Profile", profile);

		this.add(clienthomepane);

		// Creating the home inner panels

		// home inner panel 1
		JLabel welcome = new JLabel("Welcome " + details.getFirstname());
		welcome.setFont(new Font("Verdana", Font.PLAIN, 35));
		welcome.setBounds(10, 10, 900, 50);
		home.add(welcome);

		JLabel instruction = new JLabel("Balance");
		instruction.setFont(new Font("Times New Roman", Font.BOLD, 22));
		instruction.setBounds(20, 60, 325, 50);
		home.add(instruction);

		account = new JLabel("Account: ");
		account.setBounds(20, 90, 325, 50);
		selectaccount1 = new JComboBox();
		selectaccount1.setBounds(100, 105, 150, 20);
		// ArrayList<Integer> cars = new ArrayList<Integer>();

		final ArrayList<Integer> account_array = details.getAccount();
		for (int i = 0; i < account_array.size(); i++) {
			selectaccount1.addItem(account_array.get(i));

		}

		selectaccount1.addItemListener(new AccountSelection());

		home.add(account);
		home.add(selectaccount1);
		// home.add(balance);
		// home.add(balancefield);

		// home panel 2

		name1 = new JLabel("Account Name: " + details.getFirstname() + " " + details.getLastname());
		name1.setBounds(500, 70, 325, 50);
		// usersname = new JLabel ("getName ");
		// usersname.setBounds(600, 85, 150, 20);
		account_no1 = new JLabel("Account No: " + Integer.valueOf(account_array.get(0)));
		account_no1.setBounds(500, 100, 325, 50);
		if (account_array.size() > 1) {
			account_no2 = new JLabel("Account No 2: " + Integer.valueOf(account_array.get(1)));
			account_no2.setBounds(500, 125, 325, 50);
			home.add(account_no2);
		}
		if (account_array.size() > 2) {
			account_no3 = new JLabel("Account No 3: " + Integer.valueOf(account_array.get(2)));
			account_no3.setBounds(500, 150, 325, 50);
			home.add(account_no3);
		}

		home.add(name1);
		home.add(account_no1);

		transfers = new JLabel("Transfers ");
		transfers.setFont(new Font("Times New Roman", Font.BOLD, 22));
		transfers.setBounds(20, 200, 325, 50);

		home.add(transfers);

		// internal transfer label

		internal = new JLabel("Internal transfer: ");
		internal.setBounds(20, 235, 325, 50);
		home.add(internal);

		// internal transfer components

		from1 = new JLabel("From: ");
		from1.setBounds(20, 270, 150, 50);
		selectaccount2 = new JComboBox();
		selectaccount2.setBounds(150, 290, 150, 20);
		ArrayList<Integer> account_array1 = details.getAccount();
		for (int i = 0; i < account_array1.size(); i++) {
			selectaccount2.addItem(account_array1.get(i));

		}

		to1 = new JLabel("To: ");
		to1.setBounds(20, 315, 330, 50);
		selectaccount3 = new JComboBox();
		selectaccount3.setBounds(150, 330, 150, 20);
		ArrayList<Integer> account_array2 = details.getAccount();
		for (int i = 0; i < account_array2.size(); i++) {
			selectaccount3.addItem(account_array2.get(i));

		}

		amount1 = new JLabel("Amount: ");
		amount1.setBounds(20, 350, 325, 50);
		amount_in = new JTextField("");
		amount_in.setBounds(150, 365, 150, 20);

		send1 = new JButton("Send");
		send1.setBounds(20, 420, 65, 30);
		send1.setBackground(new Color(139, 69, 19));
		// send1.addActionListener(new InternalTransfer(select1, select2, amount));
		send1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int select1 = Integer.valueOf(selectaccount2.getSelectedItem().toString());
				int select2 = Integer.valueOf(selectaccount3.getSelectedItem().toString());
				String amountx = amount_in.getText();
				if (amountx.equals("")) {
					JOptionPane.showMessageDialog(btnNewButton, "Choose correct Details or Amount Greater than Zero");
				}
				if (select1 != select2 && Integer.parseInt(amountx) > 0) {
					try {
						new IntTrf(select1, select2, amountx);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Choose correct Details or Amount Greater than Zero");
				}
			}
		});

		home.add(from1);
		home.add(selectaccount2);
		home.add(to1);
		home.add(selectaccount3);
		home.add(amount1);
		home.add(amount_in);
		home.add(send1);

		// internal transfer label
		external = new JLabel("External transfer: ");
		external.setBounds(400, 240, 150, 50);
		home.add(external);

		// internal transfer components

		from2 = new JLabel("From: ");
		from2.setBounds(400, 270, 150, 50);
		selectaccount4 = new JComboBox();
		selectaccount4.setBounds(550, 290, 150, 20);
		ArrayList<Integer> account_array3 = details.getAccount();
		for (int i = 0; i < account_array3.size(); i++) {
			selectaccount4.addItem(account_array3.get(i));

		}
		to2 = new JLabel("To: ");
		to2.setBounds(400, 310, 150, 50);
		account_no = new JTextField("");
		account_no.setBounds(550, 325, 150, 20);
		amount2 = new JLabel("Amount: ");
		amount2.setBounds(400, 350, 150, 50);
		amount_ex = new JTextField("");

		amount_ex.setBounds(550, 365, 150, 20);
		send2 = new JButton("Send");
		send2.setBounds(400, 420, 65, 30);
		send2.setBackground(new Color(139, 69, 19));
		send2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ext) {
				int select3 = (int) selectaccount4.getSelectedItem();
				int select4 = (int) Integer.parseInt(account_no.getText());
				String amounty = amount_ex.getText();
				if (select3 != select4 && Integer.parseInt(amounty) > 0) {
					new ExtTrf(select3, select4, amounty);
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Choose correct Details or Amount Greater than Zero");
				}
			}
		});

		create = new JButton("Create New Account");
		create.setBounds(650, 200, 170, 30);
		create.setBackground(new Color(139, 69, 19));
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent crt) {
				if (account_array.size() < 3) {
					try {
						creation = new UserDAO();
						creation.createExtraAccount(details.getEmail());
						revalidate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(btnNewButton, "You have reached an Account ownership limit");
				}
			}
		});

		home.add(from2);
		home.add(selectaccount4);
		home.add(to2);
		home.add(account_no);
		home.add(amount2);
		home.add(amount_ex);
		home.add(send2);
		home.add(create);

		// Create history panel 1 content
		JLabel lblhistory = new JLabel("History");
		lblhistory.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblhistory.setBounds(20, 20, 325, 50);
		history.add(lblhistory);

		account2 = new JLabel("Select Account: ");
		account2.setBounds(20, 60, 325, 50);
		selectaccount5 = new JComboBox();
		selectaccount5.setBounds(150, 75, 150, 30);
		ArrayList<Integer> account_array4 = details.getAccount();
		for (int i = 0; i < account_array4.size(); i++) {
			selectaccount5.addItem(account_array4.get(i));
		}

		view = new JButton("View History");
		view.setBounds(20, 150, 150, 30);
		view.setBackground(new Color(139, 69, 19));
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent hst) {
				int select5 = (int) selectaccount5.getSelectedItem();
				JScrollPane his = details.showTrsHis(select5);
				his.setBounds(300, 250, 400, 100);
				his.setVisible(true);
				his.setSize(500, 200);
				history.add(his);
			}
		});
		savecsv = new JButton("Create Csv");
		savecsv.setBounds(200, 150, 150, 30);
		savecsv.setBackground(new Color(139, 69, 19));
		savecsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent csv) {
				int select6 = (int) selectaccount5.getSelectedItem();
				details.CreateCsv(select6);
				JOptionPane.showMessageDialog(btnNewButton, "Your file has been created and stored in: \n C:\\temp");
			}
		});
		// Add content to history panel 1
		history.add(account2);
		history.add(selectaccount5);
		history.add(view);
		history.add(savecsv);

		// Updating Profile

		JLabel update = new JLabel("Update Your Profile");
		update.setFont(new Font("Times New Roman", Font.BOLD, 22));
		update.setBounds(300, 30, 200, 20);
		profile.add(update);

		lblemail = new JLabel("Email:");
		lblemail.setBounds(300, 90, 100, 20);
		phone = new JLabel("Phone Number:");
		phone.setBounds(300, 140, 100, 20);
		address = new JLabel("Address");
		address.setBounds(300, 190, 100, 20);
		password1 = new JLabel("Password: ");
		password1.setBounds(300, 240, 100, 20);
		password2 = new JLabel("Confirm Password:");
		password2.setBounds(300, 290, 150, 20);

		// TextFields

		emailtxt = new JTextField(details.getEmail(), 30);
		emailtxt.setBounds(450, 90, 200, 30);
		phonetxt = new JTextField(details.getPhone(), 10);
		phonetxt.setBounds(450, 140, 200, 30);
		addresstxt = new JTextField(details.getAddress(), 60);
		addresstxt.setBounds(450, 190, 300, 30);
		passwordtxt1 = new JPasswordField(details.getPassword());
		passwordtxt1.setBounds(450, 240, 200, 30);
		// passwordtxt2 = new JTextField ();
		// passwordtxt2.setBounds(450, 290, 200, 30);

		save = new JButton("Save");
		save.setBounds(500, 300, 70, 30);
		save.setBackground(new Color(139, 69, 19));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent modify) {
				if (emailtxt.getText() != "" && addresstxt.getText() != "" && phonetxt.getText() != ""
						&& passwordtxt1.getText() != "") {
					new ModifyProfile(details.getIduser(), emailtxt.getText(), phonetxt.getText(), addresstxt.getText(),
							passwordtxt1.getText());

					// emailtxt.setText("");
					// phonetxt.setText("");
					// addresstxt.setText("");

				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Please fill all fields ");
				}
			}
		});
		profile.add(lblemail);
		profile.add(emailtxt);
		profile.add(phone);
		profile.add(phonetxt);
		profile.add(address);
		profile.add(addresstxt);
		profile.add(password1);
		profile.add(passwordtxt1);
		// profile.add(password2);
		// profile.add(passwordtxt2);
		profile.add(save);

	}

	public static void main(String args[]) throws SQLException {
		String mail = "aa@ff.fr";
		new ClientHome(mail);
	}
}
