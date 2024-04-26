package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Category;

public class CategoryDao {
	public static void save(Category category) {
		String query = "INSERT INTO category (name) VALUES ('"+category.getName()+"')";
		DbMethods.setDataOrDelete(query, "Category Added successfully");
	}
	
	public static ArrayList<Category> getAllRecords() {
		ArrayList<Category> arrayList = new ArrayList<>();
		try {
			ResultSet rs = DbMethods.getData("SELECT * FROM category");
			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				arrayList.add(category);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return arrayList;
	}
	
	public static void delete(String id) {
		String query = "DELETE FROM category WHERE id='"+id+"'";
		DbMethods.setDataOrDelete(query, "Category deleted successfully");
		
	}
}
