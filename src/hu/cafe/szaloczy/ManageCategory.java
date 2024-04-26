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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.CategoryDao;
import model.Category;
import javax.swing.JScrollPane;

public class ManageCategory extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtName;
	JButton btnSave = new JButton();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCategory frame = new ManageCategory();
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
	public ManageCategory() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				ArrayList<Category> list = CategoryDao.getAllRecords();
				Iterator<Category> itr = list.iterator();
				
				while(itr.hasNext()) {
					Category categoryObj = itr.next();
					dtm.addRow(new Object[] {categoryObj.getId(), categoryObj.getName()});
				}
			}
		});
		btnSave .setEnabled(false);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 134, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Home().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\logout.png"));
		btnNewButton.setBounds(550, 6, 122, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Manage Category");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\category.png"));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 206, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("View Category");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(435, 48, 155, 14);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(351, 72, 323, 258);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String id = model.getValueAt(index, 0).toString();
				String name = model.getValueAt(index, 1).toString();
				
				int a = JOptionPane.showConfirmDialog(null, "Do you want to Delete "+name+" Category","Select",JOptionPane.YES_NO_OPTION);
				if(a == 0) {
					CategoryDao.delete(id);
					setVisible(false);
					new ManageCategory().setVisible(true);
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Category"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(182);
		table.getColumnModel().getColumn(1).setPreferredWidth(223);
		
		JLabel lblNewLabel_2 = new JLabel("*Click on row to delete Category");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(410, 334, 238, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Add New Category");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(105, 68, 125, 14);
		contentPane.add(lblNewLabel_3);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateField();
			}
		});
		txtName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtName.setBounds(47, 93, 229, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Category category = new Category();
				category.setName(txtName.getText());
				CategoryDao.save(category);
				setVisible(false);
				new ManageCategory().setVisible(true);
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSave.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\save.png"));
		btnSave.setBounds(26, 142, 131, 36);
		contentPane.add(btnSave);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ManageCategory().setVisible(true);
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnClear.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\clear.png"));
		btnClear.setBounds(193, 142, 111, 36);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\small-page-background.png"));
		lblNewLabel_4.setBounds(0, 0, 684, 361);
		contentPane.add(lblNewLabel_4);
	}
	
	public void validateField() {
		String category = txtName.getText();
		if(!category.equals("")) {
			btnSave.setEnabled(true);
		} else {
			btnSave.setEnabled(false);
		}
	}
}
