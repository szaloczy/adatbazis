package hu.cafe.szaloczy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {
	
	public String email;

	private JButton btnMC = new JButton();
	private JButton btnNP = new JButton();
	private JButton btnVEDP = new JButton(); 
	private JButton btnVU = new JButton(); 
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to Logout","Select",JOptionPane.YES_NO_OPTION);
				if(a == 0) {
					setVisible(false);
					new Login().setVisible(true);
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\logout.png"));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(317, 21, 114, 39);
		contentPane.add(btnNewButton);
		
		JButton btnPlaceOreder = new JButton("Place Order");
		btnPlaceOreder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new PlaceOrder().setVisible(true);
			}
		});
		btnPlaceOreder.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\place order.png"));
		btnPlaceOreder.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPlaceOreder.setBounds(457, 21, 162, 39);
		contentPane.add(btnPlaceOreder);
		
		JButton btnViewBill_1 = new JButton("View Bill & Order Placed Details");
		btnViewBill_1.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\View Bills & Order Placed Details.png"));
		btnViewBill_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnViewBill_1.setBounds(629, 21, 268, 39);
		contentPane.add(btnViewBill_1);
		
		JButton btnViewBill = new JButton("Change Password");
		btnViewBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword().setVisible(true);
			}
		});
		btnViewBill.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\change Password.png"));
		btnViewBill.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnViewBill.setBounds(939, 21, 206, 39);
		contentPane.add(btnViewBill);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to close Application","Select",JOptionPane.YES_NO_OPTION);
				if(a == 0) {
					System.exit(0);
				}
			}
		});
		btnExit.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\exit.png"));
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnExit.setBounds(1182, 22, 131, 39);
		contentPane.add(btnExit);
		
		btnMC = new JButton("Manage Category");
		btnMC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageCategory().setVisible(true);
			}
		});
		btnMC.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnMC.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\category.png"));
		btnMC.setBounds(301, 665, 206, 34);
		contentPane.add(btnMC);
		
		btnNP = new JButton("New Product");
		btnNP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddNewProduct().setVisible(true);
			}
		});
		btnNP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNP.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\new product.png"));
		btnNP.setBounds(541, 665, 162, 34);
		contentPane.add(btnNP);
		
		btnVEDP = new JButton("View Edit & Delete Product");
		btnVEDP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewEditDeleteProduct().setVisible(true);
			}
		});
		btnVEDP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnVEDP.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\view edit delete product.png"));
		btnVEDP.setBounds(760, 663, 245, 34);
		contentPane.add(btnVEDP);
		
		btnVU = new JButton("Verify Users");
		btnVU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VerifyUsers().setVisible(true);
			}
		});
		btnVU.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnVU.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\verify users.png"));
		btnVU.setBounds(1039, 664, 184, 34);
		contentPane.add(btnVU);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\home-background-image.png"));
		lblNewLabel.setBounds(0, 0, 1350, 729);
		contentPane.add(lblNewLabel);
	}
	/*
	public Home(String userEmail) {
		email = userEmail;
		
		if (!email.equals("admin@gmail.com")) {
			btnNP.setVisible(false);
			btnMC.setVisible(false);
			btnVEDP.setVisible(false);
			btnVU.setVisible(false);
		}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to Logout","Select",JOptionPane.YES_NO_OPTION);
				if(a == 0) {
					setVisible(false);
					new Login().setVisible(true);
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\logout.png"));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(90, 22, 114, 39);
		contentPane.add(btnNewButton);
		
		JButton btnPlaceOreder = new JButton("Place Order");
		btnPlaceOreder.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\place order.png"));
		btnPlaceOreder.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPlaceOreder.setBounds(230, 22, 162, 39);
		contentPane.add(btnPlaceOreder);
		
		JButton btnViewBill_1 = new JButton("View Bill & Order Placed Details");
		btnViewBill_1.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\View Bills & Order Placed Details.png"));
		btnViewBill_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnViewBill_1.setBounds(402, 22, 268, 39);
		contentPane.add(btnViewBill_1);
		
		JButton btnViewBill = new JButton("Change Password");
		btnViewBill.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\change Password.png"));
		btnViewBill.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnViewBill.setBounds(680, 22, 206, 39);
		contentPane.add(btnViewBill);
		
		JButton btnChangeSecurityQuestion = new JButton("Change Security Question");
		btnChangeSecurityQuestion.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\change Security Question.png"));
		btnChangeSecurityQuestion.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnChangeSecurityQuestion.setBounds(908, 22, 260, 39);
		contentPane.add(btnChangeSecurityQuestion);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to close Application","Select",JOptionPane.YES_NO_OPTION);
				if(a == 0) {
					System.exit(0);
				}
			}
		});
		btnExit.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\exit.png"));
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnExit.setBounds(1178, 22, 131, 39);
		contentPane.add(btnExit);
		
		btnMC = new JButton("Manage Category");
		btnMC.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnMC.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\category.png"));
		btnMC.setBounds(301, 665, 206, 34);
		contentPane.add(btnMC);
		
		btnNP = new JButton("New Product");
		btnNP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNP.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\new product.png"));
		btnNP.setBounds(541, 665, 162, 34);
		contentPane.add(btnNP);
		
		btnVEDP = new JButton("View Edit & Delete Product");
		btnVEDP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnVEDP.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\view edit delete product.png"));
		btnVEDP.setBounds(731, 665, 245, 34);
		contentPane.add(btnVEDP);
		
		btnVU = new JButton("Verify Users");
		btnVU.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnVU.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\verify users.png"));
		btnVU.setBounds(986, 665, 184, 34);
		contentPane.add(btnVU);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\home-background-image.png"));
		lblNewLabel.setBounds(0, 0, 1350, 729);
		contentPane.add(lblNewLabel);
	}*/

}
