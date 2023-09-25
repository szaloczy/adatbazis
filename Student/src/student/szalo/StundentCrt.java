package student.szalo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StundentCrt {
	private static final String fname = "students.txt";
	private List<Student> stdList;
	

	public StundentCrt() {
		this.stdList = new ArrayList<Student>();
		load();
	}
	
	public void addNewStd(int id, String name, String group) {
		Student newStd = new Student(id, name, group);
			stdList.add(newStd);
			save();
		
	}
	
	
	private void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fname));
			oos.writeObject(stdList);
			System.out.println("Students are saved");
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void searchByName(String name) {
		for (Student student : stdList) {
			if (name.equalsIgnoreCase(student.getName())) {
				System.out.println("Found! " + student);
				return;
			}
		}
		System.out.println("The searched student name is not exits!");
	}
	
	public void deleteById(int id) {
		Student removeStd = null;
		for (Student student : stdList) {
			if (student.getId() == id) {
				removeStd = student;
				break;
			}
		}
		
		if (removeStd != null) {
			stdList.remove(removeStd);
			System.out.println("Student deleted!" + removeStd);
			save();
		} else {
			System.out.println("This student id is not exits!");
		}
	}
	
	public void terminateDuplicatedID() {
		List<Student> newList = new ArrayList<Student>();
		for (Student student : stdList) {
			boolean isDuplicated = false;
			for (Student newStd : newList) {
				if (student.getId() == newStd.getId()) {
					isDuplicated = true;
					break;
				}
			}
			if (!isDuplicated) {
				newList.add(student);
			}
		}
		
		stdList = newList;
		System.out.println("Duplicated student is terminated");
		save();
	}
	
	private void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname));
			stdList =  (List<Student>) ois.readObject();
			ois.close();
			System.out.println("Students are uploaded");
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
