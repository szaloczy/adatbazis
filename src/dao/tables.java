package dao;

import javax.swing.JOptionPane;

public class tables {
	public static void main(String[] args) {
		try {
			String userTable = "CREATE TABLE user (id int AUTO_INCREMENT PRIMARY KEY, "
					+ "name varchar(200), "
					+ "email varchar(200), "
					+ "mobileNumber varchar(10), "
					+ "address varchar(200), "
					+ "password varchar(200), "
					+ "securityQuestion varchar(200), "
					+ "answer varchar(200), "
					+ "UNIQUE(email))";
			String adminDetails = "INSERT INTO user(name,email,mobileNumber,address,password,securityQuestion,answer,status) VALUES('Admin','admin@gmail.com','1234567890','Hungary','admin','What primary School did you attend?','ABC','true')";
			String categoryTable = "CREATE TABLE category(id int AUTO_INCREMENT PRIMARY KEY,"
					+ "name varchar(200))";
			String productTable = "CREATE TABLE product(id int AUTO_INCREMENT PRIMARY KEY, name varchar(200), category varchar(200), price varchar(200))";
			String billTable = "CREATE TABLE bill(id int PRIMARY KEY, name varchar(200), mobileNumber varchar(200), email varchar(200), date varchar(50), total varchar(200), createdBy varchar(200))";
			
		//	DbMethods.setDataOrDelete(userTable, "User Table Created Successfully");
		//	DbMethods.setDataOrDelete(adminDetails, "Admin details added Successfully");
		//	DbMethods.setDataOrDelete(categoryTable, "Category table created Successfully");
		//	DbMethods.setDataOrDelete(productTable, "Product table created Successfully");
			DbMethods.setDataOrDelete(billTable, "Bill table created Successfully");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
