package ebanking;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Start extends JFrame {
	/**
	 * This is the first page of the program
	 * It displays a Welcome page with a Login or Sign up option
	 */
	public JFrame start;
	public JPanel contentPane, startPanel1, startPanel2;
	public JLabel welcome;
	public JButton login, signup;

	public Start() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\vivre.jpg"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(450, 190, 800, 500);
		this.setResizable(false);
		setVisible(true);

		contentPane = new JPanel();
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 253, 208));

		JLabel welcome = new JLabel("WELCOME TO VIVRE BANK!!!");
		welcome.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		welcome.setBounds(150, 50, 900, 50);
		contentPane.add(welcome);

		JLabel instruction = new JLabel("Your Bank... Your Rules");
		instruction.setFont(new Font("Times New Roman", Font.ITALIC, 22));
		instruction.setBounds(300, 150, 325, 50);
		contentPane.add(instruction);

		login = new JButton("Login");
		login.setFont(new Font("Tahoma", Font.PLAIN, 20));
		login.setBounds(170, 300, 200, 60);
		contentPane.add(login);

		signup = new JButton("Sign Up");
		signup.setFont(new Font("Tahoma", Font.PLAIN, 20));
		signup.setBounds(400, 300, 200, 60);
		contentPane.add(signup);

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login();
			}
		});

		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SignUp().setVisible(true);
			}
		});
	}

	public static void main(String[] args) {

		(new Start()).setVisible(true);
	}
}
