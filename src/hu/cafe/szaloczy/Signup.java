package hu.cafe.szaloczy;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Signup extends JFrame {
	
	public String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
	public String mobileNumberPattern = "^[0-9]*$";

	private JButton btnSave;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtMobileNumber;
	private JTextField txtAddress;
	private JPasswordField txtPassword;
	private JTextField txtSecurityQuestion;
	private JTextField txtAnswer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Signup");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblNewLabel.setBounds(584, 95, 126, 65);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblName.setBounds(398, 163, 88, 36);
		contentPane.add(lblName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(398, 201, 88, 36);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Mobil");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(398, 254, 88, 36);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Address");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(398, 301, 88, 36);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Password");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(398, 360, 88, 36);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Security Question");
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_5.setBounds(376, 407, 126, 36);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Answer");
		lblNewLabel_1_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_6.setBounds(398, 454, 88, 36);
		contentPane.add(lblNewLabel_1_6);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtName.setBounds(528, 171, 349, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(528, 217, 343, 20);
		contentPane.add(txtEmail);
		
		txtMobileNumber = new JTextField();
		txtMobileNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtMobileNumber.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtMobileNumber.setColumns(10);
		txtMobileNumber.setBounds(528, 262, 343, 20);
		contentPane.add(txtMobileNumber);
		
		txtAddress = new JTextField();
		txtAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtAddress.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtAddress.setColumns(10);
		txtAddress.setBounds(528, 317, 343, 20);
		contentPane.add(txtAddress);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtPassword.setBounds(534, 368, 343, 20);
		contentPane.add(txtPassword);
		
		txtSecurityQuestion = new JTextField();
		txtSecurityQuestion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtSecurityQuestion.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtSecurityQuestion.setColumns(10);
		txtSecurityQuestion.setBounds(537, 428, 340, 20);
		contentPane.add(txtSecurityQuestion);
		
		txtAnswer = new JTextField();
		txtAnswer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtAnswer.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtAnswer.setColumns(10);
		txtAnswer.setBounds(528, 470, 349, 20);
		contentPane.add(txtAnswer);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setName(txtName.getText());
				user.setEmail(txtEmail.getText());
				user.setMobilenumber(txtMobileNumber.getText());
				user.setAddress(txtAddress.getText());
				user.setPassword(txtPassword.getText());
				user.setSecurityQuestion(txtSecurityQuestion.getText());
				user.setAnswer(txtAnswer.getText());
				
				UserDao.save(user);
				clear();
			}
		});
		btnSave.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\save.png"));
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSave.setBounds(469, 548, 89, 23);
		contentPane.add(btnSave);
		btnSave.setEnabled(false);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\clear.png"));
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnClear.setBounds(608, 548, 126, 23);
		contentPane.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to close this Application","Select",JOptionPane.YES_NO_OPTION);
				if( a == 0)
					System.exit(0);
			}
		});
		btnExit.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\exit small.png"));
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnExit.setBounds(765, 548, 126, 23);
		contentPane.add(btnExit);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ForgotPassword().setVisible(true);
			}
		});
		btnForgotPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnForgotPassword.setBounds(449, 601, 147, 23);
		contentPane.add(btnForgotPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLogin.setBounds(807, 601, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\first page background.PNG"));
		lblNewLabel_1.setBounds(0, 0, 1350, 729);
		contentPane.add(lblNewLabel_1);
	}
	
	public void clear() {
		txtName.setText("");
		txtEmail.setText("");
		txtMobileNumber.setText("");
		txtAddress.setText("");
		txtSecurityQuestion.setText("");
		txtAnswer.setText("");
		txtPassword.setText("");
		btnSave.setEnabled(false);
	}
	
	public void validateFields() {
		String name = txtName.getText();
		String email = txtEmail.getText();
		String mobileNumber = txtMobileNumber.getText();
		String address = txtAddress.getText();
		String password = txtPassword.getText();
		String securityQuestion = txtSecurityQuestion.getText();
		String answer = txtAnswer.getText();
		
		if(!name.equals("") && email.matches(emailPattern) && 
				mobileNumber.matches(mobileNumberPattern) &&
				mobileNumber.length() == 10 && 
				!address.equals("") && 
				!password.equals("") && 
				!securityQuestion.equals("") && 
				!answer.equals("")) {
			
			
			btnSave.setEnabled(true);
		} else {
			btnSave.setEnabled(false);
		}
	}
}
