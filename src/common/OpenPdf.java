package common;

import java.io.File;

import javax.swing.JOptionPane;

public class OpenPdf {
	public static void openById(String id) {
		try {
			if((new File("E:\\"+id+".pdf")).exists()) {
				Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler E:\\"+id+".pdf");
			}else {
				JOptionPane.showMessageDialog(null, "File is not exists");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
