package restaurant.szaloczy.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayMethods extends Connect {
	
	public FoodsTM getAllDishes() {
		Object headers[] = {"Available","Id","Name","Sides","Calorie","Price"};
		FoodsTM ftm = new FoodsTM(headers,0);
		String sql = "SELECT * FROM food";
		try (Connection connection = connect();
			 Statement st = connection.createStatement();
			 ResultSet rs = st.executeQuery(sql)){
			while(rs.next()) {
				ftm.addRow(new Object[] {rs.getString(4), rs.getString(1), rs.getString(2), rs.getString(6), rs.getString(5), rs.getString(3)});
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return ftm;
	}
	
	public String getEntryTime() {
		String sql = "SELECT entryTime FROM waiter where status = 1";
		String entryTime = "";
		try(Connection connection = connect();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql)) {
			if (rs.next()) {
				entryTime = rs.getString("entryTime");
			}
			return entryTime;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "";
		}
	}
	
	public String getWaiterMobile() {
		String sql = "SELECT mobile FROM waiter where status = 1";
		String mobile = "";
		try(Connection connection = connect();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql)) {
			if (rs.next()) {
				mobile = rs.getString("mobile");
			}
			return mobile;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "";
		}
	}
	
	 public String getWaiterNameById(int waiterId) {
	        String sql = "SELECT name FROM waiter WHERE waiterId = ?";
	        try (Connection connection = connect();
	             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, waiterId);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                return resultSet.getString("name");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	 public int getActiveWaiterId() {
		 String sql = "SELECT waiterId FROM waiter WHERE status = 1";
		 int waiterId = 0;
		 try (Connection connection = connect();
			  Statement st = connection.createStatement();
			  ResultSet rs = st.executeQuery(sql)){
			 
			 if (rs.next()) {
				waiterId = rs.getInt("waiterId");
			}
			 return waiterId;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	 }
}
