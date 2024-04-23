package restaurant.szaloczy.methods;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	
	public Connection connect(){
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","");
			return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return conn;
		}
	}
	
	public void DisConnect(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
