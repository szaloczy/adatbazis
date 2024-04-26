package hu.cafe.szaloczy;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;

public class ViewEditDeleteProduct extends JFrame {

	private JButton btnUpdate = new JButton();
	private JComboBox comboBoxCategory = new JComboBox();
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEditDeleteProduct frame = new ViewEditDeleteProduct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewEditDeleteProduct() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				ArrayList<Product> list = ProductDao.getAllRecords();
				Iterator<Product> itr = list.iterator();
				while(itr.hasNext()) {
					Product productObj = itr.next();
					dtm.addRow(new Object[] {productObj.getId(), productObj.getName(),productObj.getCategory(), productObj.getPrice()});
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("View, Edit &Delete Product");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\view edit delete product.png"));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 267, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(158, 222, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(158, 264, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Category");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(158, 312, 76, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(158, 356, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblId = new JLabel("00");
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setBounds(244, 222, 46, 14);
		contentPane.add(lblId);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtName.setBounds(244, 261, 198, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		comboBoxCategory = new JComboBox();
		comboBoxCategory.setBounds(244, 308, 198, 22);
		contentPane.add(comboBoxCategory);
		
		txtPrice = new JTextField();
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtPrice.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtPrice.setBounds(244, 353, 198, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product product = new Product();
				int id = Integer.parseInt(lblId.getText());
				product.setId(id);
				product.setName(txtName.getText());
				product.setCategory((String)comboBoxCategory.getSelectedItem());
				product.setPrice(txtPrice.getText());
				ProductDao.update(product);
				setVisible(false);
				new ViewEditDeleteProduct().setVisible(true);
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnUpdate.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\save.png"));
		btnUpdate.setBounds(136, 426, 112, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = lblId.getText();
				int a = JOptionPane.showConfirmDialog(null, "Do you want to delete this product","Select",JOptionPane.YES_NO_OPTION);
				if(a==0) {
					ProductDao.delete(id);
					setVisible(false);
					new ViewEditDeleteProduct().setVisible(true);
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnDelete.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\delete.png"));
		btnDelete.setBounds(282, 426, 107, 23);
		contentPane.add(btnDelete);
		btnDelete.setEnabled(false);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ViewEditDeleteProduct().setVisible(true);
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnClear.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\clear.png"));
		btnClear.setBounds(441, 426, 107, 23);
		contentPane.add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			
		});
		scrollPane.setBounds(781, 123, 559, 456);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String id = model.getValueAt(index, 0).toString();
				lblId.setText(id);
				String name = model.getValueAt(index, 1).toString();
				txtName.setText(name);
				String category = model.getValueAt(index, 2).toString();
				String price = model.getValueAt(index, 3).toString();
				txtPrice.setText(price);
				
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				comboBoxCategory.removeAllItems();
				
				comboBoxCategory.addItem(category);
				ArrayList<Category> categoryList = CategoryDao.getAllRecords();
				Iterator<Category> categoryItr = categoryList.iterator();
				while(categoryItr.hasNext()) {
					Category categoryObj = categoryItr.next();
					if(!categoryObj.getName().equals(category)) {
						comboBoxCategory.addItem(categoryObj.getName());
					}
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Category", "Price"
			}
		));
		
		JButton btnBckToHome = new JButton("Home");
		btnBckToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Home().setVisible(true);
			}
		});
		btnBckToHome.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\logout.png"));
		btnBckToHome.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnBckToHome.setBounds(1222, 11, 118, 30);
		contentPane.add(btnBckToHome);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\full-page-background.PNG"));
		lblNewLabel_6.setBounds(0, 0, 1350, 729);
		contentPane.add(lblNewLabel_6);
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
	}
	
	public void validateField() {
		String name = txtName.getText();
		String price = txtPrice.getText();
		String category = (String)comboBoxCategory.getSelectedItem();
		
		if(!name.endsWith("") && !price.equals("") && category != null) {
			btnUpdate.setEnabled(true);
		} else {
			//btnUpdate.setEnabled(false);
		}
	}
}
