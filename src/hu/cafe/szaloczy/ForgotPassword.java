package hu.cafe.szaloczy;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

public class ForgotPassword extends JFrame {
	
	private JButton btnLogin = new JButton();
	private JButton btnSearch = new JButton();
	private String dbAnswer = null;
	private String email = null;
	private String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
	private JPanel panel;
	private JTextField txtEmail;
	private JTextField txtSecurityQuestion = new JTextField();
	private JTextField txtAnswer;
	private JPasswordField txtNewPassword;
	private JButton btnUpdate = new JButton();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
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
	public ForgotPassword() {
		btnUpdate.setEnabled(false);
		btnSearch.setEnabled(false);
		txtSecurityQuestion.setEditable(false);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Forgot Password?");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblNewLabel.setBounds(507, 89, 314, 63);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(367, 192, 84, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Security Question");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(367, 253, 111, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Your Answer");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(367, 311, 84, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Enter New Passwrod");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(367, 377, 123, 14);
		panel.add(lblNewLabel_1_3);
		
		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateEmail();
			}
		});
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtEmail.setBounds(517, 189, 314, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtSecurityQuestion = new JTextField();
		txtSecurityQuestion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtSecurityQuestion.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtSecurityQuestion.setColumns(10);
		txtSecurityQuestion.setBounds(517, 250, 314, 20);
		panel.add(txtSecurityQuestion);
		
		txtAnswer = new JTextField();
		txtAnswer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtAnswer.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtAnswer.setColumns(10);
		txtAnswer.setBounds(517, 308, 314, 20);
		panel.add(txtAnswer);
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtNewPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtNewPassword.setBounds(517, 374, 314, 20);
		panel.add(txtNewPassword);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				email = txtEmail.getText();
				User user = null;
				user = UserDao.getSecurityQuestion(email);
				
				if(user == null) {
					JOptionPane.showMessageDialog(null, "<html><b style=\"color:red\">Incorrect Email</bold></html>","Message",JOptionPane.ERROR_MESSAGE);
				} else {
					btnSearch.setEnabled(false);
					txtEmail.setEditable(false);
					dbAnswer = user.getAnswer();
					txtSecurityQuestion.setText(user.getSecurityQuestion());
					validateFields();
				}
			}
		});
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSearch.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\search.png"));
		btnSearch.setBounds(901, 192, 123, 23);
		panel.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnClear.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\clear.png"));
		btnClear.setBounds(623, 457, 111, 23);
		panel.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to close this Applicaiton","Select",JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnExit.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\exit small.png"));
		btnExit.setBounds(770, 457, 89, 23);
		panel.add(btnExit);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Signup().setVisible(true);
			}
		});
		btnSignup.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSignup.setBounds(485, 561, 89, 23);
		panel.add(btnSignup);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLogin.setBounds(759, 561, 113, 23);
		panel.add(btnLogin);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String answer = txtAnswer.getText();
				String newPassword = txtNewPassword.getText();
				if(answer.equals(dbAnswer)) {
					UserDao.update(email, newPassword);
					clear();
				} else {
					JOptionPane.showMessageDialog(null, "<html><b style=\"color:red\">Incorrect Answer</bold></html>","Message",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\save.png"));
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnUpdate.setBounds(485, 457, 111, 23);
		panel.add(btnUpdate);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\first page background.PNG"));
		lblNewLabel_2.setBounds(0, 0, 1350, 729);
		panel.add(lblNewLabel_2);
	}

	public void clear() {
		btnUpdate.setEnabled(false);
		btnSearch.setEnabled(false);
		txtEmail.setEditable(true);
		txtEmail.setText("");
		txtSecurityQuestion.setText("");
		txtAnswer.setText("");
		txtNewPassword.setText("");
	}
	
	public void validateEmail() {
		email = txtEmail.getText();
		if (email.matches(emailPattern)) {
			btnSearch.setEnabled(true);
		} else {
			btnSearch.setEnabled(false);
		}
	}
	
	public void validateFields() {
		String password = txtNewPassword.getText();
		String answer = txtAnswer.getText();
		String securityQuestion = txtSecurityQuestion.getText();
		
		if(!password.equals("") && !answer.equals("") && !securityQuestion.equals("")) {
			btnUpdate.setEnabled(true);
		} else 
			btnUpdate.setEnabled(false);
	}
}
