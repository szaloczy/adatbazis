package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbMethods {
	public static void setDataOrDelete(String query, String msg) {
		try {
			Connection conn = ConnectionProvider.getCon();
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			if(!msg.equals("")){
				JOptionPane.showMessageDialog(null, msg);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static ResultSet getData(String query) {
		try {
			Connection conn = ConnectionProvider.getCon();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			return rs;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e,"Message",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}
