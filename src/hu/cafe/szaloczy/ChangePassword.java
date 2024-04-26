package hu.cafe.szaloczy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ChangePassword extends JFrame {
	
	public String userEmail;

	private JButton btnUpdate = new JButton();
	private JPanel contentPane;
	private JPasswordField txtOldPassword;
	private JPasswordField txtNewPassword;
	private JPasswordField txtConfirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
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
	public ChangePassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtOldPassword = new JPasswordField();
		txtOldPassword.setBounds(209, 86, 265, 22);
		contentPane.add(txtOldPassword);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBounds(208, 189, 265, 22);
		contentPane.add(txtConfirmPassword);
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.setBounds(209, 136, 265, 22);
		contentPane.add(txtNewPassword);
		
		JLabel lblNewLabel = new JLabel("Change Password");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\change Password.png"));
		lblNewLabel.setBounds(10, 11, 180, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnHome.setForeground(Color.BLACK);
		btnHome.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnHome.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\logout.png"));
		btnHome.setBounds(557, 15, 117, 26);
		contentPane.add(btnHome);
		
		JLabel lblNewLabel_1 = new JLabel("Old password:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(96, 88, 108, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New password:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(96, 138, 108, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Confirm password:");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(67, 195, 148, 14);
		contentPane.add(lblNewLabel_1_2);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldPassword = txtOldPassword.getText();
				String newPassword = txtNewPassword.getText();
				
				UserDao.changePassword(userEmail, oldPassword, newPassword);
				setVisible(false);
				new ChangePassword().setVisible(true);
			}
		});
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnUpdate.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\save.png"));
		btnUpdate.setBounds(168, 258, 132, 23);
		contentPane.add(btnUpdate);
		btnUpdate.setEnabled(false);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ChangePassword().setVisible(true);
			}
		});
		btnClear.setForeground(Color.BLACK);
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnClear.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\clear.png"));
		btnClear.setBounds(417, 258, 117, 23);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\small-page-background.png"));
		lblNewLabel_2.setBounds(0, 0, 684, 361);
		contentPane.add(lblNewLabel_2);
	}
}
