package ebanking;

import java.awt.BorderLayout;
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

public class ManagerHomeDAO {
	Connection connection, con1;
    ResultSet rs, rs1;
    Statement st, st1;
    PreparedStatement pst;
    JButton btn;
    String ids, btnNewButton;
    Vector<String> name, namelist;
    JFrame frame1, frame2;
    static JTable table, table1;
    
    String from;
    String firstname, lastname, email, address, password;
	int iduser, phone, account, balance, sum;
	JFrame history_frame;
	static JTable history_table;
	String[] columnNames = {"Date", "Amount", "From", "To"};
    StringBuilder stringBuilder;
    
    
    ManagerHomeDAO (){
	 /**  
	* This class renders every method needed for database for the Manager 
	*/
    }
    
    public JScrollPane showCstDpst (String from, String to, int account) throws SQLException  {
		
        history_frame = new JFrame("Deposit History");
        history_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        history_frame.setLayout(new BorderLayout());
        //TableModel tm = new TableModel();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        //DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());
        //table = new JTable(model);
        history_table = new JTable();
        history_table.setModel(model);
        history_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        history_table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(history_table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //String textvalue = textbox.getText();
        String date = "";
        String amount = "";
        String fr_account_no = "";
        String to_account_no = "";
        double sum=0;
        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
        	PreparedStatement pst = connection.prepareStatement("select * from transaction where to_account_no ='" + account + "' AND type = 'credit' AND date >= '"+from+"' AND date <= '"+to+"' ");
            ResultSet rs = pst.executeQuery();
            System.out.println(pst);
            int i = 0;
            while (rs.next()) {
            	date = rs.getString("date");
            	amount = rs.getString("amount");
            	fr_account_no = rs.getString("fr_account_no");
            	to_account_no = rs.getString("to_account_no");
            	
            	
            	 
            	System.out.println("==>"+amount);
            	sum += Double.valueOf(amount);
                model.addRow(new Object[]{date, amount, fr_account_no, to_account_no});
                i++;
                
            }
           
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Records Found");

				 JOptionPane.showMessageDialog(null, "The sum of Deposit in account: " +account+ " is =  " + sum);
            } else {
                System.out.println(i + " Records Found");
                JOptionPane.showMessageDialog(null, "The sum of Deposit in account: " +account+ " is =  " + sum);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
       // history_frame.add(scroll);
        //history_frame.setVisible(true);
       // history_frame.setSize(500, 400);
        return scroll;
        
	}
    
    public ArrayList<Transaction> getActivity (String from, String to, String account) throws SQLException {
    
    	ArrayList<Transaction> tr_lst = new ArrayList<Transaction>();
		 try {

	     con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
		 Statement st1 = con1.createStatement();
		 ResultSet rs1 = st1.executeQuery("SELECT * FROM transaction WHERE fr_account_no ='"+account+"' OR to_account_no = '"+account+"'AND date >= '"+from+"' AND date <='"+to+"'"); 
		 Transaction transaction;
		 System.out.println(rs1);
		 
		 while (rs1.next()) {
		 transaction = new Transaction();
		 transaction.setAmount(rs1.getInt("amount"));
		 transaction.setDate(rs1.getDate("date"));
		 transaction.setDest(rs1.getInt("to_account_no"));
		 transaction.setSource(rs1.getString("fr_account_no"));
		 transaction.setType(rs1.getString("type"));
		 transaction.setId(rs1.getInt("idtransaction"));
		 tr_lst.add(transaction);
		 }
		   } catch (SQLException e) {
		 e.printStackTrace();
		    }
		 return tr_lst;
		    }
		}
