package restaurant.szaloczy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import restaurant.szaloczy.methods.FoodsTM;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FoodList extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private FoodsTM ftm;

	
	public FoodList(JFrame f, FoodsTM beftm) {
		super(f,"List of Dishes",true);
		ftm = beftm;
		
		setBounds(100, 100, 970, 606);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 954, 479);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 934, 457);
		panel.add(scrollPane);
		
		table = new JTable(ftm);
		scrollPane.setViewportView(table);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(-10, 490, 954, 77);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		TableColumn tc = null;
		for(int i = 0; i < 6; i++) {
			tc = table.getColumnModel().getColumn(i);
			if(i == 0 || i == 1) {
				tc.setPreferredWidth(50);
			} else if (i == 4) {
				tc.setPreferredWidth(150);
			} else {
				tc.setPreferredWidth(100);
			}
		}
		
		table.setAutoCreateRowSorter(true);
		@SuppressWarnings("unchecked")
		TableRowSorter<FoodsTM> trs = (TableRowSorter<FoodsTM>)table.getRowSorter(); 
		trs.setSortable(0, false);
	}
}
