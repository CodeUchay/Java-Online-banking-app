package ebanking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ListClients {
	Connection con;
    ResultSet rs, rs1;
    Statement st, st1;
    PreparedStatement pst;
    String ids;
    Vector name, namelist;
    
	public Vector getClientList() throws SQLException {
		
		 
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select firstname, lastname from user");
        namelist = new Vector();
        String firstname = "";
        String lastname = "";
        while (rs.next()) {
        	firstname = rs.getString("firstname");
        	lastname = rs.getString("lastname");
            ids =  firstname +" "+ lastname;
            namelist.add(ids);
        }
        return   namelist;
  
	}
}
