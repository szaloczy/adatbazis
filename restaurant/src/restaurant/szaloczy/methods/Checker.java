package restaurant.szaloczy.methods;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checker {
	
	public boolean isValidTableNumber(final JTextField field, final String fieldName) {
		final String s = readTextField(field);
		boolean b = isFilled(field, fieldName);
		if (b) {
			try {
				if (Integer.parseInt(s) < 1 && Integer.parseInt(s) > 5 ) {
					b = false;
				}
			} catch (IllegalArgumentException e) {
				message("The " + fieldName + " cell contains invalid table number! Table number must be 1, 2, 3, 4, 5", 0);
				b = false;
			}
		}
		return b;
	}
	
	public boolean isValidEmail(final JTextField field, final String fieldName) {
		final String s = readTextField(field);
		boolean b = isFilled(field, fieldName);
		if (b) {
			
				if (s.length() < 15) {
					b = false;
					message("The " + fieldName + " cell contains invalid email format! Correct email: exampl@gmail.com at least 15 character", 0);
				} 
		}
		return b;
	}
	
	public boolean isValidPhoneNumber(final JTextField field, final String fieldName) {
		final String s = readTextField(field);
		boolean b = isFilled(field, fieldName);
		
		return b;
	}
	
	public boolean isValidName(final JTextField field, final String fieldName) {
		final String s = readTextField(field);
		boolean b = isFilled(field, fieldName);
		if (b) {
			try {
				if (s.length() < 3) {
					b = false;
				}
			} catch (IllegalArgumentException e) {
				message("The " + fieldName + " cell contains invalid date! Name must be at least 3 charcter.", 0);
			}
		}
		return b;
	}
	

	
	public boolean isGoodInt(final JTextField field, final String fieldName) {
		final String s = readTextField(field);
		boolean b = isFilled(field, fieldName);
		if (b) {
			try {
				Integer.parseInt(s);
			} catch (NumberFormatException e) {
				message("The " + fieldName + " cell contains invalid number data!", 0);
				b = false;
			}
		}
		return b;
	}
	
	public boolean isFilled(final JTextField field, final String fieldName) {
		final String s = readTextField(field);
		if (s.length() > 0) {
			return true;
		} else {
			message("Error: the"+ fieldName + " cell is empty!",0);
			return false;
		}
	}
	
	int stringToInt(final String s) {
		int x = -1;
		try {
			x = Integer.valueOf(s);
		} catch (NumberFormatException e) {
			message("StrintoInt: " + e.getMessage(), 0);
		}
		return x;
	}
	
	public void message(final String msg, final int type) {
		JOptionPane.showMessageDialog(null, msg,"Program message",type);
	}
	
	public String readTextField(final JTextField textfield) {
		return textfield.getText();
	}
}
