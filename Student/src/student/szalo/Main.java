package student.szalo;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		StundentCrt stdCrt = new StundentCrt();
		Selection(stdCrt);
		
	}
	
	private static void Selection(StundentCrt stdCrt) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Select one function: \n\t"
					+ "0.Exit\n\t"
					+ "1.Add new student\n\t"
					+ "2.Search by name\n\t"
					+ "3.Delete by id\n\t"
					+ "4.Terminate duplicated id");
			int input = scanner.nextInt();
			scanner.nextLine();
			
			switch (input) {
			case 0: 
				System.exit(0);
			case 1: 
				System.out.println("Enter the id: ");
				int id = scanner.nextInt();
				System.out.println("Enter a name: ");
				String name = scanner.next();
				System.out.println("Enter a group: ");
				String group = scanner.next();
				stdCrt.addNewStd(id, name, group);
				break;
			case 2:
				System.out.println("Enter a student name: ");
				stdCrt.searchByName(scanner.next());
				break;
			case 3:
				System.out.println("Enter the id: ");
				stdCrt.deleteById(scanner.nextInt());
				break;
			case 4:
				stdCrt.terminateDuplicatedID();
				break;
			default:
				System.out.println("Invalid input !");
			}
			
		}
		
	}

}
