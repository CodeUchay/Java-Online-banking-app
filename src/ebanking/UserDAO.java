package ebanking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class UserDAO {

     Connection connection;
     Random random;
     JButton btnNewButton;

     UserDAO() throws SQLException {
          /**
           * This class creates a new user in the database when a client signs up for the
           * bank
           */
          connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
     }

     public int create(String firstname, String lastname, String address, int phone, String email, String password)
               throws SQLException {
          String create_user_query = "INSERT INTO user (firstname, lastname, address, phone, email, password) values('"
                    + firstname + "','" + lastname + "','" + address + "','" +
                    phone + "','" + email + "','" + password + "')";

          System.out.println(create_user_query);

          // insert new user into table
          Statement user = connection.createStatement();
          int x = user.executeUpdate(create_user_query);

          // insert new bank account into table
          random = new Random();
          int account_no = (random.nextInt(900000000)) + 1000000000;
          System.out.println("Account Created");

          // get iduser
          PreparedStatement st = (PreparedStatement) connection.prepareStatement(
                    "Select iduser from user where email=\"" + email + "\" and password=\"" + password + "\"");

          System.out.println(st.toString());
          ResultSet iduser = st.executeQuery();
          System.out.println("==>" + iduser);
          iduser.next();
          int userid = iduser.getInt("iduser");
          String create_bankacct_query = "INSERT INTO bank_account (account_no, balance, fk_user_id) values('"
                    + account_no + "','" + 0 + "' ,'" + userid + "')";

          System.out.println("Account No Created");
          Statement bank = connection.createStatement();
          bank.executeUpdate(create_bankacct_query);
          return x;
     }

     public void createExtraAccount(String email) throws SQLException {
          random = new Random();
          int account_no = (random.nextInt(900000000)) + 1000000000;
          System.out.println("Account Number Created");

          // get iduser
          PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("Select iduser from user where email=\"" + email + "\"");

          System.out.println(st.toString());
          ResultSet iduser = st.executeQuery();
          System.out.println("==>" + iduser);
          iduser.next();
          int userid = iduser.getInt("iduser");
          String create_bankacct_query = "INSERT INTO bank_account (account_no, balance, fk_user_id) values('"
                    + account_no + "','" + 0 + "' ,'" + userid + "')";

          System.out.println("Account No Created");
          Statement bank = connection.createStatement();
          bank.executeUpdate(create_bankacct_query);
          JOptionPane.showMessageDialog(btnNewButton, "You have created a new account\n And the Account Number is: "
                    + account_no + "\n It will be available at your next login after verification");
     }

     public int update(String firstname, String lastname, String address, int phone, String email, String password)
               throws SQLException {
          String update_user_query = "UPDATE user SET address = '" + address + "', phone = '" + phone + "', email = '"
                    + email + "', address = '" + password + "' WHERE firstname = '" + firstname + "' AND lastname = '"
                    + lastname + "'";

          System.out.println(update_user_query);

          // insert new user into table
          Statement user = connection.createStatement();
          int x = user.executeUpdate(update_user_query);
          return x;
     }

}
