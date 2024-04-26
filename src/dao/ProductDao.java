package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Product;

public class ProductDao {
	public static void save(Product product) {
		String query = "INSERT INTO product(name, category, price) VALUES('"+product.getName()+"', '"+product.getCategory()+"', '"+product.getPrice()+"')";
		DbMethods.setDataOrDelete(query, "Product Added successfully");
	}
	
	public static ArrayList<Product> getAllRecords(){
		ArrayList<Product> arrayList = new ArrayList<>();
		
		try {
			ResultSet rs = DbMethods.getData("SELECT * FROM product");
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getString("Price"));
				arrayList.add(product);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return arrayList;
	}
	
	public static void update(Product product) {
		String query = "UPDATE product SET name='" + product.getName()+"',category='"+product.getCategory()+"', price='"+product.getPrice()+"' WHERE id = '"+product.getId()+"'";
		DbMethods.setDataOrDelete(query, "Product Updated Sucessfully");
	}
	
	public static void delete(String id) {
		String query = "DELETE FROM product WHERE id='"+id+"'";
		DbMethods.setDataOrDelete(query, "Product Deleted Successfully");
	}
	
	public static ArrayList<Product> getAllRecordsByCategory(String categoryName){
		ArrayList<Product> arrayList = new ArrayList<Product>();
		
		try {
			ResultSet rs = DbMethods.getData("SELECT * FROM product WHERE category='"+categoryName+"'");
			while(rs.next()) {
				Product product = new Product();
				product.setName(rs.getString("name"));
				arrayList.add(product);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return arrayList;
	}
	
	public static ArrayList<Product> fillterProductByName(String name, String categoryName){
		ArrayList<Product> arrayList = new ArrayList<Product>();
		
		try {
			ResultSet rs = DbMethods.getData("SELECT * FROM product WHERE name LIKE '%"+name+"%' AND categoryName='"+categoryName+"'");
			while(rs.next()) {
				Product product = new Product();
				product.setName(rs.getString("name"));
				arrayList.add(product);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return arrayList;
	}
	
	public static Product getProductByName(String name) {
		Product product = new Product();
		try {
			ResultSet rs = DbMethods.getData("SELECT * FROM product WHERE name='"+name+"'");
			while(rs.next()) {
				product.setName(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setPrice(rs.getString(4));
			}
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e);
		}
		
		return product;
	}
	
	
	
	
	
	
}
