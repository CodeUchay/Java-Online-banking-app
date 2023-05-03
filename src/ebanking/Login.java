package ebanking;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
    /**
	 * This class is the Login GUI for the bank
	 */
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton, btncancel;
    private JLabel label;
    private JPanel contentPane;
     LoginDAO logincheck;
    
     //This was for testing purposes
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        setVisible(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(255,253,208));
        

        JLabel welcome = new JLabel("WELCOME TO VIVRE BANK LOGIN");
        welcome.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        welcome.setBounds(250, 50, 900, 50);
        contentPane.add(welcome);

        JLabel instruction = new JLabel("Please Enter Your Details...");
        instruction.setFont(new Font("Times New Roman", Font.ITALIC, 22));
        instruction.setBounds(400, 90, 325, 50);
        contentPane.add(instruction);
        
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBackground(Color.BLACK);
        lblemail.setForeground(Color.BLACK);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblemail.setBounds(250, 166, 193, 52);
        contentPane.add(lblemail);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(400, 400, 200, 60);
        
        btncancel = new JButton("Cancel");
        btncancel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btncancel.setBounds(650, 400, 200, 60);
        contentPane.add(btncancel);
        
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String email = textField.getText();
                String password = passwordField.getText();
                
                try {
					logincheck = new LoginDAO();
	                int x = logincheck.checklogin(email, password);
	                
	                if (x == 1) {
	                	dispose();
	                    new ClientHome(email);
	                    //ah.setTitle("Welcome");
	                    //ah.setVisible(true);
	                    JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
	                }
	                if (x == 2) {
	                	dispose();
	                	new FrontOfficeHome();
	                    
	                    JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged as a FDO");
	                }
	                if (x == 3) {
	                	dispose();
	                    new ManagerHome();
	                    //ah.setTitle("Welcome");
	                    //ah.setVisible(true);
	                    JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged as a Manager");
	                }
	                
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
                } 
            }
        );
        
    	btncancel.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	        {
	        	dispose();
	        	new Start();
	        	
	        }
	    });
        contentPane.add(btnNewButton);
        contentPane.add(btncancel); 
    }

		
		
	}
	
	

