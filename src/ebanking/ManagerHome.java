package ebanking;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import java.io.File;
import java.io.FileNotFoundException;



public class ManagerHome extends JFrame {
	
	JPanel home;
	JLabel lbltitle1, lblformat, lblformat1, lblfrom, lblto, lbltitle2, lblformat2, lblfrom1, lblto2, lblaccount, lblacccount1;
	JTextField txtfrom, txtto, txtfrom1, txtto2, txtaccount, txtacccount1;
	JButton request, request2, btn;
	JComboBox list, list1, list2;
	FrontOfficeDAO client = new FrontOfficeDAO();
	ManagerHomeDAO customer = new ManagerHomeDAO();
	
	 
	ManagerHome () throws SQLException {
		//frames
	    this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\vivre.png"));
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setBounds(450, 190, 1014, 597);
	    this.setResizable(false);
	    this.setTitle("Manager Home");
	    setVisible(true);
	    getContentPane().setBackground(new Color(230,230,250));
	    getContentPane().setForeground(new Color(155,67,208));
	  
		home = new JPanel();
		home.setLayout(null);
		home.setBackground(new Color(221,160,221));
		home.setForeground(new Color(205,133,63));
		
		this.add(home);
		
		// Left hand side 
		lbltitle1 = new JLabel ("Customer deposits ");
		lbltitle1.setBounds(20, 20, 325, 50);
		lbltitle1.setFont(new Font("Times New Roman", Font.BOLD, 32));
		
		lblformat = new JLabel ("(YYYY-MM-DD)");
		lblformat.setBounds(100, 70, 170, 30);
		lblformat.setFont(new Font("arial", Font.BOLD, 16));
		
		lblfrom = new JLabel ("From: ");
		lblfrom.setBounds(20, 90, 325, 50);
		txtfrom = new JTextField();
		txtfrom.setBounds(100, 100, 170, 30);
		lblfrom.setFont(new Font("georgia", Font.PLAIN, 16));
		
		lblto = new JLabel ("To: ");
		lblto.setBounds(20, 150, 325, 50);
		txtto = new JTextField();
		txtto.setBounds(100, 160,  170, 30);
		lblto.setFont(new Font("georgia", Font.PLAIN, 16));
		
		lblaccount = new JLabel ("Account: ");
		lblaccount.setBounds(20, 210, 325, 50);
		txtaccount = new JTextField();
		txtaccount.setBounds(100, 220, 150, 30);
		lblaccount.setFont(new Font("georgia", Font.PLAIN, 16));
		
		request = new JButton ("Request");
		request.setBounds(20, 280, 100, 30);
		request.setBackground(new Color(216,191,216));
		request.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent ext){
		        	String from = txtfrom.getText();
		        	String to = txtto.getText();
		        	int account = Integer.valueOf(txtaccount.getText()).intValue();
		        	JScrollPane deposits;
					try {
						deposits = customer.showCstDpst(from, to, account);
						deposits.setBounds(250, 350, 400, 100);
			        	deposits.setVisible(true);
			        	deposits.setSize(500, 200);
			        	home.add(deposits);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
		        }
		        });
		
		home.add(lbltitle1);
		home.add(lblformat);
		home.add(lblfrom);
		home.add(txtfrom);
		home.add(lblto);
		home.add(txtto);
		home.add(request);
		home.add(lblaccount);
		home.add(txtaccount);
		
		// Right hand side 
		lbltitle2 = new JLabel ("Customer activities ");
		lbltitle2.setBounds(500, 20, 325, 50);
		lbltitle2.setFont(new Font("Times New Roman", Font.BOLD, 32));
		
		lblformat2 = new JLabel ("(YYYY-MM-DD)");
		lblformat2.setBounds(600, 70, 170, 30);
		lblformat2.setFont(new Font("arial", Font.BOLD, 16)); 
		
		lblfrom1 = new JLabel ("From: ");
		lblfrom1.setBounds(500, 90, 325, 50);
		txtfrom1 = new JTextField();
		txtfrom1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ext) {
				
			}
		});
		txtfrom1.setBounds(600, 100, 170, 30);
		lblfrom1.setFont(new Font("georgia", Font.PLAIN, 16));
		
		lblto2 = new JLabel ("To: ");
		lblto2.setBounds(500, 150, 325, 50);
		txtto2 = new JTextField();
		txtto2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ext) {
				
			}
		});
		txtto2.setBounds(600, 160,  170, 30);
		lblto2.setFont(new Font("georgia", Font.PLAIN, 16));
		
		lblacccount1 = new JLabel ("Account: ");
		lblacccount1.setBounds(500, 210, 325, 50);
		txtacccount1 = new JTextField();
		txtacccount1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ext) {
				
			}
		});
		
		
		txtacccount1.setBounds(600, 220, 150, 30);
		lblacccount1.setFont(new Font("georgia", Font.PLAIN, 16));
		
		request2 = new JButton ("Request");
		request2.setBounds(500, 280, 100, 30);
		request2.setBackground(new Color(216,191,216));
		
		request2.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent ext){
		        	String from = txtfrom1.getText();
		        	String to = txtto2.getText();
		        	String account = txtacccount1.getText();
		        	//Blank Document
		        	//XWPFDocument document = new XWPFDocument();
		        	//Write the Document in file system
		        	XWPFDocument document = new XWPFDocument();
		        	FileOutputStream out;
		        	ArrayList<Transaction> activity_list;
				try {
					 activity_list = customer.getActivity(from, to, account);
					 System.out.println("==>"+activity_list.size()); 
					 //module bank {
					//	requires java.sql;
					//	requires java.desktop;
					//	requires org.apache.poi.ooxml;
					//}
					
						 XWPFTable table = document.createTable();
						 try {
							 
						 out = new FileOutputStream(new File("C:\\temp\\stt_"+account+".docx"));
						 //create first row
						 XWPFTableRow tableRowOne = table.getRow(0);
						 tableRowOne.getCell(0).setText("Transaction ID");
						 tableRowOne.addNewTableCell().setText("Transaction Type");
						 tableRowOne.addNewTableCell().setText("Transaction Source");
						 tableRowOne.addNewTableCell().setText("Transaction Destination");
						 tableRowOne.addNewTableCell().setText("Transaction Amount");
						 tableRowOne.addNewTableCell().setText("Transaction Date");
						 for (int i = 0; i < activity_list.size(); i++) {
						 //create other row/s
						 XWPFTableRow tableRowTwo = table.createRow();
						 tableRowTwo.getCell(0).setText(""+activity_list.get(i).getId());
						 tableRowTwo.getCell(1).setText(""+activity_list.get(i).getType());
						 tableRowTwo.getCell(2).setText(""+activity_list.get(i).getSource());
						 tableRowTwo.getCell(3).setText(""+activity_list.get(i).getDest());
						 tableRowTwo.getCell(4).setText(""+activity_list.get(i).getAmount());
						 tableRowTwo.getCell(5).setText(""+activity_list.get(i).getDate());
						 } try {
						 document.write(out);
						 out.close();
						 System.out.println("create_table.docx written successully");
						 JOptionPane.showMessageDialog(btn, "The report has been saved in C://temp Location");
						 } catch (IOException e1) {
						 // TODO Auto-generated catch block
						 e1.printStackTrace();
						 }
						 } catch (FileNotFoundException e1) {
						 // TODO Auto-generated catch block
						 e1.printStackTrace();
						 }
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					 } catch (SQLException e2) {
					 // TODO Auto-generated catch block
					 e2.printStackTrace();
					 }
					 }
		        	 
		        });
		home.add(lbltitle2);
		home.add(lblformat2);
		home.add(lblfrom1);
		home.add(txtfrom1);
		home.add(lblto2);
		home.add(txtto2);
		home.add(request2);
		home.add(lblacccount1);
		home.add(txtacccount1);
		
	}
public static void main(String args[]) throws SQLException {
		
	ManagerHome x = new ManagerHome();
		x.setVisible(true);
}
}