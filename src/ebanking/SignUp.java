package ebanking;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;  

public class SignUp extends JFrame  {
    /**
	 * This handles client sign up
	 */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtfirstname;
    private JTextField txtlastname;
    private JTextField txtemail;
    private JTextField tfaddress;
    private JTextField mob;
    private JPasswordField passwordField;
    private JButton btnNewButton, btncancel;
    

public SignUp () {
	
    this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\vivre.jpg"));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(450, 190, 1014, 597);
    this.setResizable(false);
    
    contentPane = new JPanel();
    //contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.setContentPane(contentPane);
   
    contentPane.setLayout(null);
    contentPane.setBackground(new Color(255,253,208));
    
    JLabel welcome = new JLabel("WELCOME TO VIVRE BANK REGISTRATION");
    welcome.setFont(new Font("Times New Roman", Font.PLAIN, 35));
    welcome.setBounds(150, 50, 900, 50);
    contentPane.add(welcome);

    JLabel instruction = new JLabel("Please Enter Your Details...");
    instruction.setFont(new Font("Times New Roman", Font.ITALIC, 22));
    instruction.setBounds(400, 90, 325, 50);
    contentPane.add(instruction);

    JLabel lblFirstName = new JLabel("First name");
    lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblFirstName.setBounds(58, 152, 99, 43);
    contentPane.add(lblFirstName);

    JLabel lblLastName = new JLabel("Last name");
    lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblLastName.setBounds(58, 243, 110, 29);
    contentPane.add(lblLastName);

    JLabel lblEmailAddress = new JLabel("Email\r\n address");
    lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblEmailAddress.setBounds(58, 324, 124, 36);
    contentPane.add(lblEmailAddress);

    txtfirstname = new JTextField();
    txtfirstname.setFont(new Font("Tahoma", Font.PLAIN, 32));
    txtfirstname.setBounds(214, 151, 228, 50);
    contentPane.add(txtfirstname);
    txtfirstname.setColumns(10);

    txtlastname = new JTextField();
    txtlastname.setFont(new Font("Tahoma", Font.PLAIN, 32));
    txtlastname.setBounds(214, 235, 228, 50);
    contentPane.add(txtlastname);
    txtlastname.setColumns(10);

    txtemail = new JTextField();
    txtemail.setFont(new Font("Tahoma", Font.PLAIN, 32));
    txtemail.setBounds(214, 320, 228, 50);
    contentPane.add(txtemail);
    txtemail.setColumns(10);

    tfaddress = new JTextField();
    tfaddress.setFont(new Font("Tahoma", Font.PLAIN, 32));
    tfaddress.setBounds(707, 151, 228, 50);
    contentPane.add(tfaddress);
    tfaddress.setColumns(10);

    JLabel lbladdress = new JLabel("Address");
    lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lbladdress.setBounds(542, 159, 99, 29);
    contentPane.add(lbladdress);
    
    JLabel lblphone = new JLabel("Phone No");
    lblphone.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblphone.setBounds(542, 245, 99, 24);
    contentPane.add(lblphone);
    
    JLabel lblpassword = new JLabel("Password");
    lblpassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblpassword.setBounds(542, 329, 139, 26);
    contentPane.add(lblpassword);
   
    mob = new JTextField();
    mob.setFont(new Font("Tahoma", Font.PLAIN, 32));
    mob.setBounds(707, 235, 228, 50);
    contentPane.add(mob);
    mob.setColumns(10);

    passwordField = new JPasswordField();
    passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
    passwordField.setBounds(707, 320, 228, 50);
    contentPane.add(passwordField);

    btnNewButton = new JButton("Register");
    btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
    btnNewButton.setBounds(218, 447, 200, 60);
    contentPane.add(btnNewButton);
    
    btncancel = new JButton("Cancel");
    btncancel.setFont(new Font("Tahoma", Font.PLAIN, 22));
    btncancel.setBounds(459, 447, 200, 60);
    contentPane.add(btncancel);
    
    //JButton ok = new JButton ();
    
    btnNewButton.addActionListener(new ActionListener() {
    	
    	
        private int phone;

		public void actionPerformed(ActionEvent e) {
            String firstname = txtfirstname.getText();
            String lastname = txtlastname.getText();
            String email = txtemail.getText();
            String address = tfaddress.getText();
             //int phone = Integer.valueOf(mob.getText());
            try {
            	int phone = Integer.valueOf(mob.getText());
            	this.phone = phone;
            	}
            	catch(Exception e9) {
            		 JOptionPane.showMessageDialog(btnNewButton, "Please complete form!!");
            	}
            
            int len = mob.getText().length();
			String password = passwordField.getText();
			int type=1;
            String msg = "" + firstname;
            int x = 0;
            msg += " \n";
            
            if (len != 9 || len > 9) {
                JOptionPane.showMessageDialog(btnNewButton, "Enter a valid 9 digit mobile number");
            }
           
            if  (firstname == "" || lastname == "" || address == "" || email == "" || password == "") {
            	 JOptionPane.showMessageDialog(btnNewButton, "Please complete form!!");
            }
                
             if  (len == 9 && firstname != "" && lastname != ""&& address != ""&& email != ""&& password != "") {
            	 UserDAO user;
     			try {
     				user = new UserDAO();
     				x = user.create(firstname, lastname, address, phone, email, password);
     				if (x == 0) {
                         JOptionPane.showMessageDialog(btnNewButton, "This is alredy exist");
                     } 
     			} catch (SQLException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
                    JOptionPane.showMessageDialog(btnNewButton,
                        "Welcome, " + msg + "Your account is sucessfully created");     
                    new Login();
                    dispose();
            } 
				
			
            

          
                
                
        }
        
            
        
    });
    			btncancel.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e)
		        {
		        	dispose();
		        	new Start();
		        }
		    });
    
}
			public static void main (String[] args) {
				
				(new SignUp()).setVisible(true);
			}
}