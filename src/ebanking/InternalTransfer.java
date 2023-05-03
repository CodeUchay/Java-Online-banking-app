package ebanking;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;


public class InternalTransfer implements ActionListener {
	
	   int amount;
	  int firstaccount, secondaccount;
	  JButton btnNewButton;
	  Connection connection;
	  int iduser, bal1, bal2;
	  int trfamount = amount;
		
	  public InternalTransfer(int firstaccount, int secondaccount, String amount) {
	   
		this.firstaccount = firstaccount;
	    this.secondaccount = secondaccount;
	    this.amount = Integer.parseInt(amount);
	    
	    
	  }
	
	public void  actionPerformed(ActionEvent e){
		if (firstaccount != secondaccount) {
			
			String get_balance1_query = "SELECT balance FROM bank_account WHERE account_no = '"+ firstaccount +"'";
	        System.out.println(get_balance1_query);  
	        try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
				Statement name = connection.createStatement();
		        ResultSet rs = name.executeQuery(get_balance1_query);
		        rs.next();
		        int bal1 = rs.getInt("balance");
		        this.bal1 = bal1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        String get_balance2_query = "SELECT balance FROM bank_account WHERE account_no = '"+ secondaccount +"'";
	        System.out.println(get_balance2_query);  
	        try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
				Statement name = connection.createStatement();
		        ResultSet rs = name.executeQuery(get_balance2_query);
		        rs.next();
		        int bal2 = rs.getInt("balance");
		        this.bal2 = bal2;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        		if (trfamount > bal1) {JOptionPane.showMessageDialog(btnNewButton, "You do not have sufficient funds");}
	        		else { 
	        			int deduction = bal1 - trfamount;
	        			int addition = bal2 + trfamount;
	        			Connection connection;
	        			String deduction_query = "UPDATE bank_account SET balance = '"+ deduction +"' WHERE account_no  = '"+ firstaccount +"' ";
	        			String addition_query = "UPDATE bank_account SET balance = '"+ deduction +"' WHERE account_no  = '"+ firstaccount +"' ";
	        	        System.out.println(deduction_query);
	       			try {
	        				//first balance deduction query
	       					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
	        				Statement debit = connection.createStatement();
	        				debit.executeUpdate(deduction_query);
	        				System.out.println("debit from first account is successful");
	        				 
	        				//Second balance deduction query
		        			Statement credit = connection.createStatement();
		        			credit.executeUpdate(addition_query);
		        			System.out.println("credit to first account is successful");
	        				 
	        				 JOptionPane.showMessageDialog(btnNewButton, "You have credited Qccount: " + secondaccount + " \n with " + amount + "\n And your balance is " + addition+ "");
	        			} catch (SQLException e1) {
	        				// TODO Auto-generated catch block
	        				e1.printStackTrace();
	        			}}    
	        		  
	        		}
			
		else {JOptionPane.showMessageDialog(btnNewButton, "Choose correct Details or Amount");
		}
		
	}
	
}
