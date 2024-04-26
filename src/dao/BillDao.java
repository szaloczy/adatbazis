package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Bill;

public class BillDao {
	public static String getId() {
		int id = 1;
		try {
			ResultSet rs = DbMethods.getData("SELECT max(id) FROM bill");
			if(rs.next()) {
				id = rs.getInt(1);
				id = id + 1;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return String.valueOf(id);
	}
	
	public static void save(Bill bill) {
		String query = "INSERT INTO bill VALUES('"+bill.getId()+"','"+bill.getName()+"','"+bill.getMobileNumber()+"','"+bill.getEmail()+"','"+bill.getDate()+"'"
				+ ",'"+bill.getTotal()+"','"+bill.getCreatedBy()+"')";
		DbMethods.setDataOrDelete(query, "Bill Details Added Sucessfully");
	}
	
	public static ArrayList<Bill> getAllRecordsByInc(String date){
		ArrayList<Bill> arrayList = new ArrayList<Bill>();
		try {
			ResultSet rs = DbMethods.getData("SELECT * FROM bill WHERE date LIKE '%"+date+"%'");
			while(rs.next()) {
				Bill bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setName(rs.getString("name"));
				bill.setMobileNumber(rs.getString("mobileNumber"));
				bill.setEmail(rs.getString("email"));
				bill.setDate(rs.getString("date"));
				bill.setTotal(rs.getString("total"));
				bill.setCreatedBy(rs.getString("createdBy"));
				arrayList.add(bill);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return arrayList;
	}
	
	public static ArrayList<Bill> getAllRecordsByDesc(String date){
		ArrayList<Bill> arrayList = new ArrayList<Bill>();
		try {
			ResultSet rs = DbMethods.getData("SELECT * FROM bill WHERE date like '%"+date+"%' ORDER BY id DESC");
			while(rs.next()) {
				Bill bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setName(rs.getString("name"));
				bill.setMobileNumber(rs.getString("mobileNumber"));
				bill.setEmail(rs.getString("email"));
				bill.setDate(rs.getString("date"));
				bill.setTotal(rs.getString("total"));
				bill.setCreatedBy(rs.getString("createdBy"));
				arrayList.add(bill);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return arrayList;
	}
}
