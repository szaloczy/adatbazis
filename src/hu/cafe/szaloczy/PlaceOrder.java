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
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PRAcroForm;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import common.OpenPdf;
import dao.BillDao;
import dao.CategoryDao;
import dao.ProductDao;
import model.Bill;
import model.Category;
import model.Product;

public class PlaceOrder extends JFrame {

	public JSpinner spinner = new JSpinner();
	public String userEmail;
	public int billId = 1;
	public int grandTotal = 0;
	public int productPrice = 0;
	public int productTotal = 0;
	public String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
	public String mobileNumberPattern = "^[0-9]*$";
	public JFormattedTextField tf = ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField();
	
	private JLabel lblGrandTotal = new JLabel();
	private JComboBox comboBox = new JComboBox();
	private JLabel lblBillId = new JLabel();
	private JButton btnGenerateBillPrint = new JButton();
	private JButton btnAddToCart = new JButton();
	private JPanel contentPane;
	private final JButton btnNewButton = new JButton("Home");
	private JTextField txtCustomerName;
	private JTextField txtMobileNumber;
	private JTextField txtEmail;
	private JTextField txtSearch;
	private JTable table;
	private JTextField txtProductName;
	private JTextField txtProductPrice;
	private JTextField txtProTotal;
	private JTable table2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaceOrder frame = new PlaceOrder();
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
	public PlaceOrder() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				billId = Integer.parseInt(BillDao.getId());
				lblBillId.setText(BillDao.getId());
				ArrayList<Category> list = CategoryDao.getAllRecords();
				
				Iterator<Category> itr = list.iterator();
				while(itr.hasNext()) {
					Category categoryObj = itr.next();
					comboBox.addItem(categoryObj.getName());
				}
				String category = (String)comboBox.getSelectedItem();
				productNameByCategory(category);
			}
		});
		//userEmail = email;
		tf.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Place Order");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\place order.png"));
		lblNewLabel.setBounds(22, 11, 145, 35);
		contentPane.add(lblNewLabel);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Home().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\logout.png"));
		btnNewButton.setBounds(1207, 13, 120, 31);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Bill ID:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(64, 106, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblBillId = new JLabel("00");
		lblBillId.setForeground(new Color(255, 255, 255));
		lblBillId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblBillId.setBounds(137, 106, 46, 14);
		contentPane.add(lblBillId);
		
		JLabel lblNewLabel_3 = new JLabel("Customer Details:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(64, 146, 119, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Name");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(64, 197, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtCustomerName = new JTextField();
		txtCustomerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtCustomerName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtCustomerName.setBounds(64, 230, 197, 20);
		contentPane.add(txtCustomerName);
		txtCustomerName.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Mobile number:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_5.setBounds(64, 275, 135, 14);
		contentPane.add(lblNewLabel_5);
		
		txtMobileNumber = new JTextField();
		txtMobileNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtMobileNumber.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtMobileNumber.setBounds(67, 316, 194, 20);
		contentPane.add(txtMobileNumber);
		txtMobileNumber.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Email:");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_6.setBounds(64, 366, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 validateField();
			}
		});
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(67, 405, 194, 20);
		contentPane.add(txtEmail);
		
		JLabel lblNewLabel_7 = new JLabel("Category:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7.setBounds(310, 107, 83, 14);
		contentPane.add(lblNewLabel_7);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String category = (String)comboBox.getSelectedItem();
				productNameByCategory(category);
			}
		});
		comboBox.setBounds(310, 143, 224, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_8 = new JLabel("Search:");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_8.setBounds(310, 198, 57, 14);
		contentPane.add(lblNewLabel_8);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String name = txtSearch.getText();
				String category = (String)comboBox.getSelectedItem();
				filterProductByName(name, category);
			}
		});
		txtSearch.setBounds(310, 231, 224, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String productName = model.getValueAt(index, 0).toString();
				Product product = ProductDao.getProductByName(productName);
				txtProductName.setText(product.getName());
				txtProductPrice.setText(product.getPrice());
				spinner.setValue(1);
				txtProTotal.setText(product.getPrice());
				productPrice = Integer.parseInt(product.getPrice());
				productTotal = Integer.parseInt(product.getPrice());
				btnAddToCart.setEnabled(true);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name"
			}
		));
		table.setBounds(310, 286, 224, 319);
		contentPane.add(table);
		
		JLabel lblNewLabel_9 = new JLabel("Name:");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_9.setBounds(677, 107, 46, 14);
		contentPane.add(lblNewLabel_9);
		
		txtProductName = new JTextField();
		txtProductName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtProductName.setBounds(677, 144, 251, 20);
		contentPane.add(txtProductName);
		txtProductName.setColumns(10);
		txtProductName.setEditable(false);
		
		JLabel lblNewLabel_10 = new JLabel("Price:");
		lblNewLabel_10.setForeground(new Color(255, 255, 255));
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_10.setBounds(979, 107, 46, 14);
		contentPane.add(lblNewLabel_10);
		
		txtProductPrice = new JTextField();
		txtProductPrice.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtProductPrice.setBounds(979, 144, 251, 20);
		contentPane.add(txtProductPrice);
		txtProductPrice.setColumns(10);
		txtProductPrice.setEditable(false);
		
		JLabel lblNewLabel_11 = new JLabel("Quantity:");
		lblNewLabel_11.setForeground(new Color(255, 255, 255));
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_11.setBounds(677, 198, 63, 14);
		contentPane.add(lblNewLabel_11);
		
		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int quantity = (Integer)spinner.getValue();
				if(quantity <= 1) {
					spinner.setValue(1);
					quantity = 1;
				}
				
				productTotal = productPrice * quantity;
				txtProTotal.setText(String.valueOf(productTotal));
			}
		});
		spinner.setFont(new Font("Times New Roman", Font.BOLD, 14));
		spinner.setBounds(677, 231, 251, 20);
		contentPane.add(spinner);
		
		JLabel lblNewLabel_12 = new JLabel("Total:");
		lblNewLabel_12.setForeground(new Color(255, 255, 255));
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_12.setBounds(979, 198, 46, 14);
		contentPane.add(lblNewLabel_12);
		
		txtProTotal = new JTextField();
		txtProTotal.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtProTotal.setBounds(979, 231, 251, 20);
		contentPane.add(txtProTotal);
		txtProTotal.setColumns(10);
		txtProTotal.setEditable(false);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearProductFields();
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnClear.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\clear.png"));
		btnClear.setBounds(677, 266, 105, 35);
		contentPane.add(btnClear);
		
		btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtProductName.getText();
				String price = txtProductPrice.getText();
				String quantity = String.valueOf(spinner.getValue());
				DefaultTableModel dtm = (DefaultTableModel)table2.getModel();
				dtm.addRow(new Object[] {name, price, quantity, productTotal});
				grandTotal = grandTotal + productTotal;
				lblGrandTotal.setText(String.valueOf(grandTotal));
				validateField();
				clearProductFields();
				
			}
		});
		btnAddToCart.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAddToCart.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\add to cart.png"));
		btnAddToCart.setBounds(1095, 266, 135, 35);
		contentPane.add(btnAddToCart);
		btnAddToCart.setEnabled(false);
		
		table2 = new JTable();
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table2.getSelectedRow();
				int a = JOptionPane.showConfirmDialog(null, "Do you want to remove this product","Select",JOptionPane.YES_NO_OPTION);
				if(a == 0) {
					TableModel model = table.getModel();
					String total = model.getValueAt(index, 3).toString();
					grandTotal = grandTotal - Integer.parseInt(total);
					lblGrandTotal.setText(String.valueOf(grandTotal));
					((DefaultTableModel) table2.getModel()).removeRow(index);
				}
			}
		});
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Price", "Total", "Quantity"
			}
		));
		table2.setBounds(677, 316, 548, 290);
		contentPane.add(table2);
		
		JLabel lblNewLabel_13 = new JLabel("Grand Total:");
		lblNewLabel_13.setForeground(new Color(255, 255, 255));
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_13.setBounds(677, 658, 91, 14);
		contentPane.add(lblNewLabel_13);
		
		lblGrandTotal = new JLabel("00");
		lblGrandTotal.setForeground(new Color(255, 255, 255));
		lblGrandTotal.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGrandTotal.setBounds(805, 658, 46, 14);
		contentPane.add(lblGrandTotal);
		
		JButton btnGenerateBillPrint = new JButton("Generate Bill & Print");
		btnGenerateBillPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerName = txtCustomerName.getText();
				String customerMobileNumber = txtMobileNumber.getText();
				String customerEmail = txtEmail.getText();
				SimpleDateFormat dFormat = new SimpleDateFormat("dd=MM=yyyy");
				Date date = new Date();
				String todayDate = dFormat.format(date);
				String total = String.valueOf(grandTotal);
				String createdBy = userEmail;
				
				Bill bill = new Bill();
				bill.setId(billId);
				bill.setName(customerName);
				bill.setMobileNumber(customerMobileNumber);
				bill.setEmail(customerEmail);
				bill.setDate(todayDate);
				bill.setTotal(total);
				bill.setCreatedBy(createdBy);
				BillDao.save(bill);
				
				//Creating document
				String path = "E:\\";
				com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
				try {
					PdfWriter.getInstance(doc, new FileOutputStream(path+""+billId+".pdf"));
					doc.open();
					Paragraph cafeName = new Paragraph("																				Cafe Management System\n");
					doc.add(cafeName);
					Paragraph starLine = new Paragraph("****************************************************************************************************************");
					doc.add(starLine);
					Paragraph paragraph3 = new Paragraph("\tBill ID: " + billId +"\nCustomer Name:"+customerName+ "Total Paid: " + grandTotal);
					doc.add(paragraph3);
					doc.add(starLine);
					PdfPTable tb1 = new PdfPTable(4);
					tb1.addCell("Name");
					tb1.addCell("Price");
					tb1.addCell("Quantity");
					tb1.addCell("Total");
					for(int i=0; i < table2.getRowCount(); i++) {
						String n = table2.getValueAt(i, 0).toString();
						String d = table2.getValueAt(i, 1).toString();
						String r = table2.getValueAt(i, 2).toString();
						String q = table2.getValueAt(i, 3).toString();	
						tb1.addCell(n);
						tb1.addCell(d);
						tb1.addCell(r);
						tb1.addCell(q);
					}
					doc.add(tb1);
					doc.add(starLine);
					Paragraph thanksMsg = new Paragraph("Thank you for your purchase");
					doc.add(thanksMsg);
					
					OpenPdf.openById(String.valueOf(billId));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e);
				}
				doc.close();
				setVisible(false);
				new PlaceOrder().setVisible(true); //Pass paramter
			}
		});
		btnGenerateBillPrint.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnGenerateBillPrint.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\generate bill & print.png"));
		btnGenerateBillPrint.setBounds(1033, 642, 197, 35);
		contentPane.add(btnGenerateBillPrint);
		//btnGenerateBillPrint.setEnabled(false);
		
		JLabel lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\full-page-background.PNG"));
		lblNewLabel_15.setBounds(0, 0, 1350, 718);
		contentPane.add(lblNewLabel_15);
	}  
	
	public void productNameByCategory(String categoryName) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<Product> list = ProductDao.getAllRecordsByCategory(categoryName);
		Iterator<Product> itr = list.iterator();
		while(itr.hasNext()) {
			Product productObj = itr.next();
			dtm.addRow(new Object[] {productObj.getName()});
		}
	}
	
	public void filterProductByName(String name, String categoryName) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<Product> list = ProductDao.fillterProductByName(name,categoryName);
		Iterator<Product> itr = list.iterator();
		while(itr.hasNext()) {
			Product productObj = itr.next();
			dtm.addRow(new Object[] {productObj.getName()});
		}
	}
	
	public void clearProductFields() {
		txtProductName.setText("");
		txtProductPrice.setText("");
		spinner.setValue(1);
		txtProTotal.setText("");
		btnAddToCart.setEnabled(false);
	}
	
	public void validateField() {
		String customerNameString = txtCustomerName.getText();
		String customerMobileNumber = txtMobileNumber.getText();
		String customerEmail = txtEmail.getText();
		if(!customerEmail.equals("") && customerMobileNumber.matches(mobileNumberPattern) && 
				customerMobileNumber.length() == 10 && customerEmail.matches(emailPattern) && grandTotal > 0) {
			btnGenerateBillPrint.setEnabled(true);
		} else {
			btnGenerateBillPrint.setEnabled(false);
		}
	}
	
	/*
	
	public PlaceOrder(String userEmail) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Place Order");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\place order.png"));
		lblNewLabel.setBounds(22, 11, 145, 35);
		contentPane.add(lblNewLabel);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Home(userEmail).setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\logout.png"));
		btnNewButton.setBounds(1207, 13, 120, 31);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Bill ID:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(64, 106, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("00");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(137, 106, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Customer Details:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(64, 146, 119, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Name");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(64, 197, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtCustomerName.setBounds(64, 230, 197, 20);
		contentPane.add(txtCustomerName);
		txtCustomerName.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Mobile number:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_5.setBounds(64, 275, 75, 14);
		contentPane.add(lblNewLabel_5);
		
		txtMobileNumber = new JTextField();
		txtMobileNumber.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtMobileNumber.setBounds(67, 316, 194, 20);
		contentPane.add(txtMobileNumber);
		txtMobileNumber.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Email:");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_6.setBounds(64, 366, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(67, 405, 194, 20);
		contentPane.add(txtEmail);
		
		JLabel lblNewLabel_7 = new JLabel("Category:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7.setBounds(310, 107, 83, 14);
		contentPane.add(lblNewLabel_7);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(310, 143, 224, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_8 = new JLabel("Search:");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_8.setBounds(310, 198, 57, 14);
		contentPane.add(lblNewLabel_8);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(310, 231, 224, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name"
			}
		));
		table.setBounds(310, 286, 224, 319);
		contentPane.add(table);
		
		JLabel lblNewLabel_9 = new JLabel("Name:");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_9.setBounds(677, 107, 46, 14);
		contentPane.add(lblNewLabel_9);
		
		txtProductName = new JTextField();
		txtProductName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtProductName.setBounds(677, 144, 251, 20);
		contentPane.add(txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Price:");
		lblNewLabel_10.setForeground(new Color(255, 255, 255));
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_10.setBounds(979, 107, 46, 14);
		contentPane.add(lblNewLabel_10);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtPrice.setBounds(979, 144, 251, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Quantity:");
		lblNewLabel_11.setForeground(new Color(255, 255, 255));
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_11.setBounds(677, 198, 63, 14);
		contentPane.add(lblNewLabel_11);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Times New Roman", Font.BOLD, 14));
		spinner.setBounds(677, 231, 251, 20);
		contentPane.add(spinner);
		
		JLabel lblNewLabel_12 = new JLabel("Total:");
		lblNewLabel_12.setForeground(new Color(255, 255, 255));
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_12.setBounds(979, 198, 46, 14);
		contentPane.add(lblNewLabel_12);
		
		txtProTotal = new JTextField();
		txtProTotal.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtProTotal.setBounds(979, 231, 251, 20);
		contentPane.add(txtProTotal);
		txtProTotal.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\clear.png"));
		btnNewButton_1.setBounds(677, 266, 105, 35);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Add to Cart");
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1_1.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\add to cart.png"));
		btnNewButton_1_1.setBounds(1095, 266, 135, 35);
		contentPane.add(btnNewButton_1_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Price", "Total", "Quantity"
			}
		));
		table_1.setBounds(677, 316, 548, 290);
		contentPane.add(table_1);
		
		JLabel lblNewLabel_13 = new JLabel("Grand Total:");
		lblNewLabel_13.setForeground(new Color(255, 255, 255));
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_13.setBounds(677, 658, 91, 14);
		contentPane.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("00");
		lblNewLabel_14.setForeground(new Color(255, 255, 255));
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_14.setBounds(805, 658, 46, 14);
		contentPane.add(lblNewLabel_14);
		
		JButton btnNewButton_2 = new JButton("Generate Bill & Print");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_2.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\generate bill & print.png"));
		btnNewButton_2.setBounds(1033, 642, 197, 35);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\full-page-background.PNG"));
		lblNewLabel_15.setBounds(0, 0, 1350, 718);
		contentPane.add(lblNewLabel_15);
	}*/
}
