package restaurant.szaloczy;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import restaurant.szaloczy.methods.Connect;
import restaurant.szaloczy.methods.DisplayMethods;
import restaurant.szaloczy.methods.FoodsTM;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class HomePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DisplayMethods displayMethods = new DisplayMethods();
	private Connect connection = new Connect();
	private FoodsTM ftm;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		
		
		setTitle("Logged");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 564);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(0, 333, 814, 192);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnAddReservation = new JButton("Add reservation");
		btnAddReservation.setBounds(26, 13, 109, 65);
		panel.add(btnAddReservation);
		btnAddReservation.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				dispose();
				ReservationMenu rmenu = new ReservationMenu();
				rmenu.show();
			}
		});
		btnAddReservation.setForeground(Color.BLACK);
		btnAddReservation.setBackground(new Color(241, 57, 83));
		
		JButton btnViewReservation = new JButton("Note");
		btnViewReservation.setBounds(174, 89, 109, 65);
		panel.add(btnViewReservation);
		btnViewReservation.setForeground(Color.BLACK);
		btnViewReservation.setBackground(new Color(241, 57, 83));
		
		JButton btnExit_2 = new JButton("Exit to login");
		btnExit_2.setBounds(320, 89, 109, 65);
		panel.add(btnExit_2);
		btnExit_2.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
		        String changeStatusSql = "UPDATE waiter SET status = 0 WHERE status = 1 ";

		        try (Connection conn = connection.connect();
		             PreparedStatement pst = conn.prepareStatement(changeStatusSql)) {
		            
		        		int changedRows = pst.executeUpdate();

		            if (changedRows > 0) {
		            	Login login = new Login();
		            	 dispose();
		 				login.show();
		            } else {
		                JOptionPane.showMessageDialog(null, "Something went wrong");
		            }
		        } catch (Exception ee) {
		            System.out.println(ee.getMessage());
		        }
		       
			}
		});
		btnExit_2.setForeground(Color.BLACK);
		btnExit_2.setBackground(new Color(241, 57, 83));
		
		JButton btnBills = new JButton("Bills");
		btnBills.setForeground(Color.BLACK);
		btnBills.setBackground(new Color(241, 57, 83));
		btnBills.setBounds(320, 13, 109, 65);
		panel.add(btnBills);
		
		JButton btnCreateBill = new JButton("Create bill");
		btnCreateBill.setForeground(Color.BLACK);
		btnCreateBill.setBackground(new Color(241, 57, 83));
		btnCreateBill.setBounds(174, 13, 109, 65);
		panel.add(btnCreateBill);
		
		JButton btnFoodsdrinks = new JButton("Foods/Drinks");
		btnFoodsdrinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ftm = displayMethods.getAllDishes();
				FoodList fl = new FoodList(HomePage.this, ftm);
				fl.setVisible(true);
			}
		});
		btnFoodsdrinks.setForeground(Color.BLACK);
		btnFoodsdrinks.setBackground(new Color(241, 57, 83));
		btnFoodsdrinks.setBounds(26, 89, 109, 65);
		panel.add(btnFoodsdrinks);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.PINK);
		panel_1.setBounds(0, 0, 255, 328);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\restaurant\\images\\waiter.png"));
		lblNewLabel.setBounds(89, 21, 73, 88);
		panel_1.add(lblNewLabel);
		
		int currentWaiterId = displayMethods.getActiveWaiterId();
		String waiterName = displayMethods.getWaiterNameById(currentWaiterId);
		JLabel Name = new JLabel();
		Name.setFont(new Font("Tahoma", Font.BOLD, 13));
		Name.setBounds(129, 143, 90, 14);
		Name.setText(waiterName);
		panel_1.add(Name);
		
		JLabel id = new JLabel("waiter ID");
		id.setFont(new Font("Tahoma", Font.BOLD, 13));
		id.setBounds(129, 118, 90, 14);
		panel_1.add(id);
		id.setText(String.valueOf(currentWaiterId));
		
		String waiterMobile = displayMethods.getWaiterMobile();
		JLabel mobile = new JLabel("");
		mobile.setFont(new Font("Tahoma", Font.BOLD, 13));
		mobile.setBounds(129, 168, 90, 14);
		panel_1.add(mobile);
		mobile.setText(waiterMobile);
		
		JLabel lblNewLabel_1 = new JLabel("Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(96, 11, 90, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Waiter ID:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 119, 68, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Name:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(10, 143, 68, 14);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Mobile:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_2.setBounds(10, 168, 90, 14);
		panel_1.add(lblNewLabel_2_2);
		
		
		JLabel entryTimeLbl = new JLabel("shift starts:");
		entryTimeLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		entryTimeLbl.setBounds(10, 193, 90, 14);
		panel_1.add(entryTimeLbl);
		
		
		String entry = displayMethods.getEntryTime();
		JLabel Time = new JLabel("");
		Time.setBounds(129, 194, 90, 14);
		panel_1.add(Time);
		Time.setText(entry);
		
		JLabel welcomeMsg = new JLabel();
		welcomeMsg.setBounds(10, 259, 235, 31);
		panel_1.add(welcomeMsg);
		welcomeMsg.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeMsg.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeMsg.setText("Welcome " + waiterName +" !");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.PINK);
		panel_2.setBounds(265, 0, 549, 328);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblActiveTables = new JLabel();
		lblActiveTables.setText("Active tables");
		lblActiveTables.setHorizontalAlignment(SwingConstants.CENTER);
		lblActiveTables.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblActiveTables.setBounds(140, 11, 235, 31);
		panel_2.add(lblActiveTables);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 40, 529, 277);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 529, 277);
		panel_3.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table_load();
	}
	
	public void table_load() {
		try(Connection conn = connection.connect();
			PreparedStatement pst = conn.prepareStatement("SELECT name, tablenumber, appointment, guestsnumber FROM customer");
			ResultSet rs = pst.executeQuery()){
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
}
