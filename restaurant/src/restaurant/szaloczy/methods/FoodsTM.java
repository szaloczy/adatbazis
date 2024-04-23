package restaurant.szaloczy.methods;

import javax.swing.table.DefaultTableModel;

public class FoodsTM extends DefaultTableModel {
	
	public FoodsTM(Object fieldNames[], int rows) {
		super(fieldNames, rows);
	}
	
	public boolean isCellEditable(int row, int col) {
		if(col == 0) {
			return true;
		}
		return false;
	}
	
	public Class<?> getColumnClass(int index){
		 if (index == 0 || index == 5) {
			return(Integer.class);
		}
		else {
			return(String.class);
		}
	}
}
