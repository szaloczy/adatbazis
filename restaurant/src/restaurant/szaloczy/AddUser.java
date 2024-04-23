package restaurant.szaloczy;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import restaurant.szaloczy.methods.*;

public class AddUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFFullname;
	private JTextField txtFMobile;
	private JTextField txtFUsername;
	private JTextField txtFPassword;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JLabel lblCreateNewUser;
	private JButton btnAdd;
	private JButton btnBackToLogin;
	private JButton btnReset;
	private Connect connection = new Connect();
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser frame = new AddUser();
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
	public AddUser() {
		setTitle("Create User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtFFullname = new JTextField();
		txtFFullname.setBounds(456, 68, 228, 28);
		contentPane.add(txtFFullname);
		txtFFullname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Full name :");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		lblNewLabel.setBounds(350, 68, 96, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblMobile = new JLabel("Mobile :");
		lblMobile.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		lblMobile.setBounds(371, 117, 75, 28);
		contentPane.add(lblMobile);
		
		txtFMobile = new JTextField();
		txtFMobile.setColumns(10);
		txtFMobile.setBounds(456, 117, 228, 28);
		contentPane.add(txtFMobile);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		lblUsername.setBounds(350, 168, 96, 28);
		contentPane.add(lblUsername);
		
		txtFUsername = new JTextField();
		txtFUsername.setColumns(10);
		txtFUsername.setBounds(456, 168, 228, 28);
		contentPane.add(txtFUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		lblPassword.setBounds(350, 221, 96, 28);
		contentPane.add(lblPassword);
		
		txtFPassword = new JTextField();
		txtFPassword.setColumns(10);
		txtFPassword.setBounds(456, 221, 228, 28);
		contentPane.add(txtFPassword);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 336, 404);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("E:\\java\\restaurant\\images\\addWaiter1.jpg"));
		lblNewLabel_1.setBounds(53, 91, 208, 178);
		panel.add(lblNewLabel_1);
		
		lblCreateNewUser = new JLabel("Create new User");
		lblCreateNewUser.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		lblCreateNewUser.setBounds(482, 11, 139, 28);
		contentPane.add(lblCreateNewUser);
		
		btnAdd = new JButton("Create");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = txtFUsername.getText(), password= txtFPassword.getText(), name=txtFFullname.getText(), mobile=txtFMobile.getText();
	
				String sqlp = "INSERT INTO waiter (`username`, `password`, `name`, `mobile`, `status`) VALUES('"+username+"','"+password+"','"+name+"','"+mobile+"','"+0+"')";
				try (Connection conn = connection.connect();
					 Statement st = conn.createStatement()){
					st.execute(sqlp);
					JOptionPane.showMessageDialog(null, "New waiter created successfully!");
					System.out.println("Command: "+sqlp);
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
				
				txtFFullname.setText("");
				txtFUsername.setText("");
				txtFMobile.setText("");
				txtFPassword.setText("");
			}
		});
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setBackground(new Color(241, 57, 83));
		btnAdd.setBounds(441, 271, 158, 35);
		contentPane.add(btnAdd);
		
		btnBackToLogin = new JButton("Back to login");
		btnBackToLogin.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login loginpage = new Login();
				loginpage.show();
			}
		});
		btnBackToLogin.setForeground(Color.BLACK);
		btnBackToLogin.setBackground(new Color(241, 57, 83));
		btnBackToLogin.setBounds(537, 337, 158, 35);
		contentPane.add(btnBackToLogin);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFPassword.setText("");
				txtFUsername.setText("");
				txtFMobile.setText("");
				txtFFullname.setText("");
				
			}
		});
		btnReset.setForeground(Color.BLACK);
		btnReset.setBackground(new Color(241, 57, 83));
		btnReset.setBounds(358, 337, 158, 35);
		contentPane.add(btnReset);
	}
}
