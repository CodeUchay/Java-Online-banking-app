package ebanking;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class IntTrfSelection implements ItemListener {
	JButton btnNewButton;
	Object item;
	int account_no_selected = Integer.valueOf((int) item);

	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			this.item = event.getItem();
		}
	}

	public int setSelectedAccount() {
		return this.account_no_selected;
	}

}