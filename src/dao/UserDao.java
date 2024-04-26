package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.User;

public class UserDao {
	public static void save(User user) {
		String query = "INSERT INTO user(name,email,mobileNumber,address,password,securityQuestion,answer,status)"
				+ " VALUES('"+user.getName()+"',"
						+ "'"+user.getEmail()+"',"
								+ "'"+user.getMobilenumber()+"',"
										+ "'"+user.getAddress()+"',"
												+ "'"+user.getPassword()+"',"
														+ "'"+user.getSecurityQuestion()+"',"
																+ "'"+user.getAnswer()+"','false')";
		
		DbMethods.setDataOrDelete(query, "Registered Successfully! Wait for Admin Approval!");
	}
	
	public static User login(String email, String password) {
		User user = null;
		try {
			ResultSet rs = DbMethods.getData("SELECT * FROM user WHERE email='"+email+"' AND password='"+password+"'");
			while(rs.next()) {
				user = new User();
				user.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return user;
	}
	
	public static User getSecurityQuestion(String email) {
		User user = null;
		try {
			ResultSet rs = DbMethods.getData("SELECT * FROM user WHERE email='"+email+"'");
			while(rs.next()) {
				user = new User();
				user.setSecurityQuestion(rs.getString("securityQuestion"));
				user.setAnswer(rs.getString("answer"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return user;
	}
	
	public static void update(String email, String newPassword) {
		String query = "UPDATE user SET password='"+newPassword+"' WHERE email='"+email+"'";
		DbMethods.setDataOrDelete(query, "Password changed Successfully");
	}
	
	public static ArrayList<User> getAllRecords(String email){
		ArrayList<User> arrayList = new ArrayList<User>();
		
		try {
			ResultSet rs = DbMethods.getData("SELECT * FROM USER WHERE email LIKE '%"+email+"%'");
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setMobilenumber(rs.getString("mobileNumber"));
				user.setAddress(rs.getNString("address"));
				user.setSecurityQuestion(rs.getString("securityQuestion"));
				user.setStatus(rs.getString("status"));
				arrayList.add(user);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return arrayList;
	}
	
	public static void changeStatus(String email, String status) {
		String query = "UPDATE user SET status='"+status+"' WHERE email = '"+email+"'";
		DbMethods.setDataOrDelete(query, "Status changed successfully");
	}
	
	public static void changePassword(String email, String oldPassword, String newPassword) {
		try {
			ResultSet rs = DbMethods.getData("SELECT * FROM user WHERE email='"+email+"' AND password ='"+ oldPassword+"'");
			if(rs.next()) {
				update(email,newPassword);
			} else {
				JOptionPane.showMessageDialog(null, "Old password is wrong");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
}
