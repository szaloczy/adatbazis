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
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;

public class AddNewProduct extends JFrame {

	private JComboBox comboBox = new JComboBox();
	private JButton btnSave = new JButton();
	private JPanel contentPane = new JPanel();
	private JTextField txtName;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewProduct frame = new AddNewProduct();
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
	public AddNewProduct() {
		
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				ArrayList<Category> list = CategoryDao.getAllRecords();
				Iterator<Category> itr = list.iterator();
				while(itr.hasNext()) {
					Category categoryObj = itr.next();
					comboBox.addItem(categoryObj.getName());
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 134, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\logout.png"));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(553, 19, 103, 28);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New Product");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\new product.png"));
		lblNewLabel.setBounds(10, 11, 147, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(166, 92, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Category");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(166, 141, 81, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(166, 192, 46, 14);
		contentPane.add(lblNewLabel_1_2);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtName.setBounds(285, 89, 214, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		
		txtPrice = new JTextField();
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtPrice.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(285, 189, 214, 20);
		contentPane.add(txtPrice);
		

		comboBox = new JComboBox();
		comboBox.setBounds(285, 138, 214, 22);
		contentPane.add(comboBox);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product product = new Product();
				product.setName(txtName.getText()); 
				product.setCategory((String)comboBox.getSelectedItem());
				product.setPrice(txtPrice.getText());
				
				ProductDao.save(product);
				setVisible(false);
				new AddNewProduct().setVisible(true);
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSave.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\save.png"));
		btnSave.setBounds(216, 255, 113, 23);
		contentPane.add(btnSave);
		btnSave.setEnabled(false);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AddNewProduct().setVisible(true);			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnClear.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\clear.png"));
		btnClear.setBounds(364, 255, 113, 23);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\small-page-background.png"));
		lblNewLabel_2.setBounds(0, 0, 684, 361);
		contentPane.add(lblNewLabel_2);
		
	}
	
	public void validateFields() {
		String name = txtName.getText();
		String price = txtPrice.getText();
		if(!name.equals("") && !price.equals("")) {
			btnSave.setEnabled(true);
		} else {
			btnSave.setEnabled(false);
		}
	}
}
