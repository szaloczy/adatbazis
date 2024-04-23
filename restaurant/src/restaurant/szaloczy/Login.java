package restaurant.szaloczy;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Formatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import restaurant.szaloczy.methods.Connect;

public class Login extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFUsername;
	private JTextField txtFPassword;
	private Connect connection = new Connect();
	
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

	public Login() {
		setTitle("Login");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 446);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 387, 407);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("E:\\java\\restaurant\\images\\loginWaiter1.png"));
		lblNewLabel_1.setBounds(87, 95, 173, 184);
		panel.add(lblNewLabel_1);
		
		JButton btnCreateUser = new JButton("Create user");
		btnCreateUser.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddUser addUserPage = new AddUser();
				addUserPage.show();
			}
		});
		btnCreateUser.setForeground(Color.BLACK);
		btnCreateUser.setBackground(new Color(241, 57, 83));
		btnCreateUser.setBounds(588, 361, 158, 35);
		contentPane.add(btnCreateUser);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
					Formatter fmt = new Formatter();
			      Calendar cal = Calendar.getInstance();
			      fmt = new Formatter();
			      fmt.format("%tl:%tM", cal, cal);
				
					String username = txtFUsername.getText();
			        String password = txtFPassword.getText();
			        String sqlp = "SELECT * FROM waiter WHERE username=? and password=?";
			        String changeStatusSql = "UPDATE waiter SET status = 1 , entryTime = '" + fmt + "' WHERE username= ? ";
			     
			        try (Connection conn = connection.connect();
			             PreparedStatement pst = conn.prepareStatement(sqlp)) {
			            
			            pst.setString(1, username);
			            pst.setString(2, password);
			            ResultSet rs = pst.executeQuery();

			            if (rs.next()) {
			                PreparedStatement updatePst = conn.prepareStatement(changeStatusSql);
			                updatePst.setString(1, username);
			                updatePst.executeUpdate();
			                dispose();
			                HomePage homepage = new HomePage();
			                homepage.show();
			            } else {
			                JOptionPane.showMessageDialog(null, "Username or password is wrong!");
			            }
			        } catch (Exception ee) {
			            System.out.println(ee.getMessage());
			        }
			        
			}
			
		});
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(new Color(241, 57, 83));
		btnLogin.setBounds(484, 199, 158, 35);
		contentPane.add(btnLogin);
		
		txtFUsername = new JTextField();
		txtFUsername.setBounds(535, 53, 189, 20);
		contentPane.add(txtFUsername);
		txtFUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(414, 56, 88, 14);
		contentPane.add(lblNewLabel);
		
		txtFPassword = new JTextField();
		txtFPassword.setColumns(10);
		txtFPassword.setBounds(535, 105, 189, 20);
		contentPane.add(txtFPassword);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(414, 108, 88, 14);
		contentPane.add(lblPassword);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
		btnExit.setForeground(Color.BLACK);
		btnExit.setBackground(new Color(241, 57, 83));
		btnExit.setBounds(397, 361, 158, 35);
		contentPane.add(btnExit);
	}
}
