package hu.cafe.szaloczy;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import common.OpenPdf;
import dao.BillDao;
import model.Bill;
import javax.swing.JScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ViewBillsOrderPlacedDetails extends JFrame {

	
	
	JComboBox comboBox = new JComboBox();
	private JPanel contentPane;
	private JTable table;
	private JTextField txtDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBillsOrderPlacedDetails frame = new ViewBillsOrderPlacedDetails();
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
	public ViewBillsOrderPlacedDetails() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				tableDetails();
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Home");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Home().setVisible(true);
			}
		});
		SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String todayDate = dFormat.format(date);
		txtDate = new JTextField();
		txtDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableDetails();
			}
		});
		txtDate.setBounds(448, 124, 195, 20);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		txtDate.setText(todayDate);

		JLabel lblNewLabel_3 = new JLabel("*Click on row to open Bill");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblNewLabel_3.setBounds(530, 637, 407, 63);
		contentPane.add(lblNewLabel_3);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\logout.png"));
		btnNewButton.setBounds(1193, 19, 121, 28);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("View Bills & Order Placed Details");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(
				new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\View Bills & Order Placed Details.png"));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(28, 11, 253, 36);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Filter By Date");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(344, 126, 91, 14);
		contentPane.add(lblNewLabel_1);

	

		JLabel lblNewLabel_2 = new JLabel("Change Order By Id");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(743, 126, 127, 22);
		contentPane.add(lblNewLabel_2);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableDetails();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "INC", "DEC" }));
		comboBox.setBounds(880, 122, 228, 22);
		contentPane.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(255, 200, 911, 412);
		contentPane.add(scrollPane);
		
				table = new JTable();
				scrollPane.setViewportView(table);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						TableModel model = table.getModel();
						String id = model.getValueAt(index, 0).toString();
						OpenPdf.openById(id);
						
					}
				});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Mobile Number", "Email", "Date", "Total ", "Created By"
			}
		));

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("E:\\java\\Cafe Management System\\src\\images\\full-page-background.PNG"));
		lblNewLabel_4.setBounds(0, 0, 1350, 729);
		contentPane.add(lblNewLabel_4);
	}
	
	public void tableDetails() {
		String date = txtDate.getText();
		String incDec = (String) comboBox.getSelectedItem();
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		if(incDec.equals("INC")) {
			ArrayList<Bill> list = BillDao.getAllRecordsByInc(date);
			Iterator<Bill> itr = list.iterator();
			while(itr.hasNext()) {
				Bill billObj = itr.next();
				dtm.addRow(new Object[] {billObj.getId(), billObj.getName(),billObj.getMobileNumber(),
						billObj.getEmail(),billObj.getDate(),billObj.getTotal(),billObj.getCreatedBy()});
			}
		} else {
			ArrayList<Bill> list = BillDao.getAllRecordsByDesc(date);
			Iterator<Bill> itr = list.iterator();
			while(itr.hasNext()) {
				Bill billObj = itr.next();
				dtm.addRow(new Object[] {billObj.getId(), billObj.getName(),billObj.getMobileNumber(),
						billObj.getEmail(),billObj.getDate(),billObj.getTotal(),billObj.getCreatedBy()});
		}
	}
}

}
