package ebanking;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;

public class ClientDAO {
    /**  
	* This class renders every method needed for database for the Client 
	*/
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
    String firstname, lastname, email, address, password;
    int iduser, phone, account, balance, account_hst;
    JFrame history_frame;
    static JTable history_table;
    String[] columnNames = { "Date", "Amount", "From", "To" };
    StringBuilder stringBuilder;

    public ClientDAO(String email) throws SQLException {
        super();
        this.setEmail(email);
        this.setIdUser(getiduser());
    }

    public String getEmail() {
        return email;
    }

    // set the email of this Class
    public void setEmail(String email) throws SQLException {

        this.email = email;
        String get_user_query = "SELECT iduser FROM user WHERE email = '" + email + "'";
        System.out.println(get_user_query);
        Statement name = connection.createStatement();
        ResultSet rs = name.executeQuery(get_user_query);
        rs.next();
        int usr = rs.getInt("iduser");
        this.iduser = usr;
    }

    public JScrollPane showTrsHis(int from) {
        this.account_hst = from;
        history_frame = new JFrame("Client List");
        history_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        history_frame.setLayout(new BorderLayout());
        // TableModel tm = new TableModel();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        // DefaultTableModel model = new DefaultTableModel(tm.getData1(),
        // tm.getColumnNames());
        // table = new JTable(model);
        history_table = new JTable();
        history_table.setModel(model);
        history_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        history_table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(history_table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // String textvalue = textbox.getText();
        String date = "";
        String amount = "";
        String fr_account_no = "";
        String to_account_no = "";

        try {
            PreparedStatement pst = connection.prepareStatement(
                    "select * from transaction where fr_account_no ='" + from + "'OR to_account_no ='" + from + "'");
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                date = rs.getString("date");
                amount = rs.getString("amount");
                fr_account_no = rs.getString("fr_account_no");
                to_account_no = rs.getString("to_account_no");

                model.addRow(new Object[] { date, amount, fr_account_no, to_account_no });
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
        // history_frame.add(scroll);
        // history_frame.setVisible(true);
        // history_frame.setSize(500, 400);
        return scroll;

    }

    public void CreateCsv(int from) {
        this.account_hst = from;

        String date = "";
        String amount = "";
        String fr_account_no = "";
        String to_account_no = "";
        stringBuilder = new StringBuilder();
        stringBuilder.append("Date").append(",").append("Amount").append(",").append("From Account").append(",")
                .append("To Account").append("\n");

        try {
            PreparedStatement pst = connection.prepareStatement(
                    "select * from transaction where fr_account_no ='" + from + "'OR to_account_no ='" + from + "'");
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                date = rs.getString("date");
                amount = rs.getString("amount");
                fr_account_no = rs.getString("fr_account_no");
                to_account_no = rs.getString("to_account_no");

                stringBuilder.append(date).append(",").append(amount).append(",").append(fr_account_no).append(",")
                        .append(to_account_no).append("\n");
                i++;
            }
            stringBuilder.append("\n").append("Balance: ").append(this.getBalance(from) + "Euros");
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
            try (FileWriter fileWriter = new FileWriter("C:\\temp\\" + from + ".csv")) {

                fileWriter.write(stringBuilder.toString());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void setIdUser(int iduser) throws SQLException {

    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getFirstname() throws SQLException {

        String get_firstname_query = "SELECT firstname FROM user WHERE email = '" + email + "'";
        System.out.println(get_firstname_query);
        Statement name = connection.createStatement();
        ResultSet rs = name.executeQuery(get_firstname_query);
        rs.next();
        String fname = rs.getString("firstname");
        return fname;
    }

    public String getLastname() throws SQLException {

        String get_lastname_query = "SELECT lastname FROM user WHERE email = '" + email + "'";
        System.out.println(get_lastname_query);
        Statement name = connection.createStatement();
        ResultSet rs = name.executeQuery(get_lastname_query);
        rs.next();
        String lname = rs.getString("lastname");
        System.out.println("this works");
        return lname;

    }

    public int getiduser() {
        return this.iduser;
    }

    public int getIduser() {
        return this.iduser;

    }

    public String getAddress() throws SQLException {
        String get_address_query = "SELECT address FROM user WHERE email = '" + email + "'";
        System.out.println(get_address_query);
        Statement name = connection.createStatement();
        ResultSet rs = name.executeQuery(get_address_query);
        rs.next();
        String add = rs.getString("address");
        return add;

    }

    public String getPassword() throws SQLException {
        String get_phone_query = "SELECT password FROM user WHERE email = '" + email + "'";
        System.out.println(get_phone_query);
        Statement name = connection.createStatement();
        ResultSet rs = name.executeQuery(get_phone_query);
        rs.next();
        String pass = rs.getString("password");
        return pass;

    }

    public String getPhone() throws SQLException {
        String get_phone_query = "SELECT phone FROM user WHERE email = '" + email + "'";
        System.out.println(get_phone_query);
        Statement name = connection.createStatement();
        ResultSet rs = name.executeQuery(get_phone_query);
        rs.next();
        int phn = rs.getInt("phone");
        String str1 = Integer.toString(phn);
        return str1;

    }

    public ArrayList<Integer> getAccount() throws SQLException {
        ArrayList<Integer> listAccount = new ArrayList<Integer>();
        String get_account_query = "SELECT account_no FROM bank_account WHERE fk_user_id = '" + iduser + "'";
        System.out.println(get_account_query);
        Statement name = connection.createStatement();
        ResultSet rs = name.executeQuery(get_account_query);
        while (rs.next()) {
            int account_no = rs.getInt("account_no");
            listAccount.add(account_no);
            System.out.println(account_no + "\n");

        }
        return listAccount;
    }

    public int getBalance() throws SQLException {

        String get_balance_query = "SELECT balance FROM bank_account WHERE fk_user_id = '" + iduser + "'";
        System.out.println(get_balance_query);
        Statement name = connection.createStatement();
        ResultSet rs = name.executeQuery(get_balance_query);
        rs.next();
        int bal = rs.getInt("balance");
        return bal;
    }

    public int getBalance(int account_no) throws SQLException {

        String get_balance_query = "SELECT balance FROM bank_account WHERE account_no = '" + account_no + "'";
        System.out.println(get_balance_query);
        Statement name = connection.createStatement();
        ResultSet rs = name.executeQuery(get_balance_query);
        rs.next();
        int bal = rs.getInt("balance");
        return bal;
    }

}
