package restaurant.szaloczy;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.DbUtils;
import restaurant.szaloczy.methods.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class ReservationMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2833413773431873020L;
	private JPanel contentPane;
	private JTextField txtFCustomerName;
	private JTextField txtFMobile;
	private JTextField txtFEmail;
	private JTable table;
	private JTextField txtFSearch;
	private JTextField txtFNoGuests;
	private Connect connection = new Connect();
	private JTextField txtFAppointment;
	private Connection conn = connection.connect();
	private Checker checker = new Checker();
	private JTextField textFieldTableNo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					ReservationMenu frame = new ReservationMenu();
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
	public ReservationMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 637);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Reservation");
		title.setForeground(new Color(241, 57, 83));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("MS Gothic", Font.BOLD, 30));
		title.setBounds(411, 11, 218, 38);
		contentPane.add(title);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 84, 352, 355);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 54, 121, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("mobile:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 87, 131, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Customer email:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 129, 121, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Reserved table:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(10, 259, 121, 14);
		panel.add(lblNewLabel_1_2_1);
		
		
		txtFCustomerName = new JTextField();
		txtFCustomerName.setBounds(141, 47, 152, 20);
		panel.add(txtFCustomerName);
		txtFCustomerName.setColumns(10);
		
		txtFMobile = new JTextField();
		txtFMobile.setColumns(10);
		txtFMobile.setBounds(141, 86, 152, 20);
		panel.add(txtFMobile);
		
		txtFEmail = new JTextField();
		txtFEmail.setColumns(10);
		txtFEmail.setBounds(141, 128, 152, 20);
		panel.add(txtFEmail);
		
		txtFNoGuests = new JTextField();
		txtFNoGuests.setColumns(10);
		txtFNoGuests.setBounds(141, 171, 152, 20);
		panel.add(txtFNoGuests);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("guests:");
		lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_3.setBounds(10, 172, 131, 14);
		panel.add(lblNewLabel_1_2_3);
		
		txtFAppointment = new JTextField();
		txtFAppointment.setColumns(10);
		txtFAppointment.setBounds(141, 216, 152, 20);
		panel.add(txtFAppointment);
		
		JLabel lblNewLabel_1_2_3_1 = new JLabel("appointment:");
		lblNewLabel_1_2_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_3_1.setBounds(10, 213, 131, 14);
		panel.add(lblNewLabel_1_2_3_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Free tables:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1.setBounds(10, 308, 121, 14);
		panel.add(lblNewLabel_1_2_1_1);
		
		textFieldTableNo = new JTextField();
		textFieldTableNo.setColumns(10);
		textFieldTableNo.setBounds(141, 258, 152, 20);
		panel.add(textFieldTableNo);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if (checker.isValidName(txtFCustomerName, "Customer name") &&
					checker.isValidPhoneNumber(txtFMobile, "Phone number") &&
					checker.isValidEmail(txtFEmail, "Email") &&
					checker.isValidTableNumber(textFieldTableNo, "Table number") &&
					checker.isGoodInt(textFieldTableNo, "Guest number")) {
				
				try(Connection conn = connection.connect();
					PreparedStatement pst = conn.prepareStatement("INSERT INTO customer (name, mobile, email, tablenumber, appointment, guestsnumber)"
							+ " VALUES(?, ?, ?, ?, ?, ?)")){
					pst.setString(1,txtFCustomerName.getText());
					pst.setString(2, txtFMobile.getText());
					pst.setString(3, txtFEmail.getText());
					pst.setInt(4, Integer.parseInt(textFieldTableNo.getText()));
					pst.setString(5, txtFAppointment.getText());
					pst.setInt(6, Integer.parseInt(txtFNoGuests.getText()));
					pst.executeUpdate();
					table_load();
					JOptionPane.showMessageDialog(null,"Reservation Added");
					txtFCustomerName.setText("");
					txtFNoGuests.setText("");
					txtFMobile.setText("");
					txtFEmail.setText("");
					textFieldTableNo.setText("");
					txtFAppointment.setText("");
					txtFNoGuests.setText("");
					
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
			}
		});
		btnSave.setBounds(10, 450, 130, 38);
		btnSave.setBackground(new Color(241, 57, 83));
		contentPane.add(btnSave);
		
		JButton btnReset = new JButton("Back to Main");
		btnReset.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomePage hpage = new HomePage();
				hpage.show();
			}
		});
		btnReset.setBounds(899, 549, 111, 38);
		btnReset.setBackground(new Color(241, 57, 83));
		contentPane.add(btnReset);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(193, 450, 146, 38);
		btnClear.setBackground(new Color(241, 57, 83));
		contentPane.add(btnClear);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(364, 82, 646, 357);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table_load();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.PINK);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 522, 359, 65);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Table number:");
		lblNewLabel_1_2_2.setBounds(34, 21, 112, 17);
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1_2_2);
		
		txtFSearch = new JTextField();
		txtFSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sql = "SELECT name, mobile, email, tablenumber, appointment, guestsnumber FROM customer WHERE tablenumber = ?";
				try (PreparedStatement pst = conn.prepareStatement(sql)) {
					String tableId = txtFSearch.getText();
					pst.setInt(1, Integer.parseInt(tableId));
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()) {
						String name = rs.getString(1);
						String mobile =rs.getString(2);
						String email = rs.getString(3);
						String tablenumber = rs.getString(4);
						String appointment = rs.getString(5);
						String guestsnumber = rs.getString(6);
						
						txtFCustomerName.setText(name);
						txtFMobile.setText(mobile);
						txtFEmail.setText(email);
						textFieldTableNo.setText(tablenumber);
						txtFAppointment.setText(appointment);
						txtFNoGuests.setText(guestsnumber);
						
						
					}
					
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		txtFSearch.setBounds(181, 21, 131, 20);
		txtFSearch.setColumns(10);
		panel_1.add(txtFSearch);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE customer SET name = ?,  mobile= ?, email= ?, tablenumber= ?, appointment= ?, guestsnumber= ? Where tablenumber= ?";
				try (PreparedStatement pst = conn.prepareStatement(sql)){
					pst.setString(1, txtFCustomerName.getText());
					pst.setString(2, txtFMobile.getText());
					pst.setString(3, txtFEmail.getText());
					pst.setString(4, textFieldTableNo.getText());
					pst.setString(5, txtFAppointment.getText());
					pst.setString(6, txtFNoGuests.getText());
					pst.setString(7, txtFSearch.getText());
					if (pst.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(null, "Records are updated successfully!");
		
						txtFCustomerName.setText("");
						txtFNoGuests.setText("");
						txtFMobile.setText("");
						txtFEmail.setText("");
						textFieldTableNo.setText("");
						txtFAppointment.setText("");
						txtFNoGuests.setText("");
						table_load();
					} else {
						System.out.println("Someting went wrong: " + sql);
					}
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnUpdate.setBounds(432, 450, 197, 38);
		btnUpdate.setBackground(new Color(241, 57, 83));
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM customer WHERE tablenumber= ?";
				try (Connection conn = connection.connect();
					 PreparedStatement pst = conn.prepareStatement(sql)){
					pst.setString(1,textFieldTableNo.getText());
					pst.execute();
					table_load();
					txtFCustomerName.setText("");
					txtFNoGuests.setText("");
					txtFMobile.setText("");
					txtFEmail.setText("");
					textFieldTableNo.setText("");
					txtFAppointment.setText("");
					txtFNoGuests.setText("");
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnDelete.setBounds(692, 450, 187, 38);
		btnDelete.setBackground(new Color(241, 57, 83));
		contentPane.add(btnDelete);
		
	}
	
	public void table_load() {
		try(Connection conn = connection.connect();
			PreparedStatement pst = conn.prepareStatement("SELECT name, mobile, email, tablenumber, appointment, guestsnumber FROM customer");
			ResultSet rs = pst.executeQuery()){
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
}
