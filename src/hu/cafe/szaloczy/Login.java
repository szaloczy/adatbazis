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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.DbMethods;
import dao.UserDao;
import model.User;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {
	
	public String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
	
	private JButton btnLogin = new JButton();
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		btnLogin.setEnabled(false);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtPassword.setBounds(546, 255, 311, 24);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblNewLabel.setBounds(615, 97, 170, 64);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(420, 203, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(421, 256, 101, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtEmail.setBounds(547, 200, 309, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String password = txtPassword.getText();
				User user = null;
				user = UserDao.login(email, password);
				
				if(user == null) {
					JOptionPane.showMessageDialog(null, "<html><b style=\"color:red\">Incorrect Username or Password</b></html>","Message",JOptionPane.ERROR_MESSAGE);
				} else {
					if(user.getStatus().equals("false")) {
						ImageIcon icon = new ImageIcon("src/popupicons/wait.png");
						JOptionPane.showMessageDialog(null, "<html><b>Wait for Admin approval</b></html>","Message",JOptionPane.INFORMATION_MESSAGE,icon);
						clear();
					}
					if(user.getStatus().equals("true")) {
						setVisible(false);
						new Home().setVisible(true);
					}
				}
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLogin.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\login.png"));
		btnLogin.setBounds(475, 328, 114, 23);
		contentPane.add(btnLogin);
		btnLogin.setEnabled(false);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnClear.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\clear.png"));
		btnClear.setBounds(648, 328, 114, 23);
		contentPane.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to close this application","Select",JOptionPane.YES_NO_OPTION);
				if(a == 0) {
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnExit.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\exit small.png"));
		btnExit.setBounds(818, 327, 89, 23);
		contentPane.add(btnExit);
		
		JButton btnNewButton_3 = new JButton("Forgot Password ?");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ForgotPassword().setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_3.setBounds(472, 384, 148, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Signup");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Signup().setVisible(true);
			}
		});
		btnNewButton_3_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_3_1.setBounds(820, 384, 89, 23);
		contentPane.add(btnNewButton_3_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\first page background.PNG"));
		lblNewLabel_2.setBounds(0, 0, 1350, 729);
		contentPane.add(lblNewLabel_2);
	}
	
	public void clear() {
		txtEmail.setText("");
		txtPassword.setText("");
		btnLogin.setEnabled(false);
	}
	
	public void validateFields() {
		String email = txtEmail.getText();
		String password = txtPassword.getText();
		
		if(email.matches(emailPattern) && !password.equals("")) {
			btnLogin.setEnabled(true);
		} else {
			btnLogin.setEnabled(false);
		}
	}

}
