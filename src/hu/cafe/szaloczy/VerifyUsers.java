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

import dao.UserDao;
import model.User;
import javax.swing.JScrollPane;

public class VerifyUsers extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerifyUsers frame = new VerifyUsers();
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
	public VerifyUsers() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				getAllRecords("");
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Verify Users");
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\verify users.png"));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(25, 11, 111, 37);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\logout.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Home().setVisible(true);
			}
		});
		btnNewButton.setBounds(1205, 19, 121, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(418, 71, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String email = txtEmail.getText();
				getAllRecords(email);
			}
		});
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtEmail.setBounds(526, 68, 270, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("*Click on row to change status");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblNewLabel_2.setBounds(418, 663, 520, 37);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(118, 132, 1171, 498);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String email = model.getValueAt(index, 2).toString();
				String status = model.getValueAt(index, 6).toString();
				if(status.equals("true")) {
					status = "false";
				} else {
					status = "true";
				}
				
				int a = JOptionPane.showConfirmDialog(null, "Do you want to change status of "+email+"", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0) {
					UserDao.changeStatus(email, status);
					setVisible(false);
					new VerifyUsers().setVisible(true);
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Email", "Mobile number", "Address", "Security question", "Status"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(104);
		table.getColumnModel().getColumn(2).setPreferredWidth(129);
		table.getColumnModel().getColumn(3).setPreferredWidth(102);
		table.getColumnModel().getColumn(5).setPreferredWidth(163);
		table.getColumnModel().getColumn(6).setPreferredWidth(142);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\full-page-background.PNG"));
		lblNewLabel_3.setBounds(0, 0, 1350, 729);
		contentPane.add(lblNewLabel_3);
	}
	
	public void getAllRecords(String email) {
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		ArrayList<User> list = UserDao.getAllRecords(email);
		Iterator<User> itr = list.iterator();
		while(itr.hasNext()) {
			User userObj = itr.next();
			if(!userObj.getEmail().equals("admin@gmail.com")) {
				dtm.addRow(new Object[] {userObj.getId(), userObj.getName(), userObj.getEmail(),
						   userObj.getMobilenumber(), userObj.getAddress(), userObj.getSecurityQuestion(), userObj.getStatus()});
				
				
			}
		}
	}

}
