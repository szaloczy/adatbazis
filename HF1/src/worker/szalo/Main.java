package worker.szalo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ParseException {
		CrtWorker CWorker = new CrtWorker();
		selection(CWorker);
	}
	
	public static void selection(CrtWorker CWorker) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		while (true) {
			System.out.println("Chosse an option: ");
			System.out.println("0.exit\n"
					+ "1.Listing\n"
					+ "2.Insertion\n"
					+ "3.Deletion\n"
					+ "4.sort by code");
		int input = scanner.nextInt();
		scanner.nextLine();
		
		switch (input) {
		case 0: 
			System.exit(0);
			break;
		case 1:
			CWorker.list();
			break;
		case 2:
			System.out.println("Enter a code: ");
			int code = scanner.nextInt();
			System.out.println("Enter the name: ");
			String name = scanner.next();
			System.out.println("Enter the birthday: ");
			Date d = null ;
			d = sdf.parse(scanner.next());
			System.out.println("Enter the residence: ");
			String residence = scanner.next();
			System.out.println("Enter the iq level: ");
			int iq = scanner.nextInt();
			CWorker.Insertion(code, name, d, residence, iq);
			break;
		case 3:
			System.out.println("Enter a code");
			int removeCode = scanner.nextInt();
			CWorker.deleteByCode(removeCode);
			break;
		case 4:
			CWorker.sortByCode();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + input);
		}
		}
		
	}

}
