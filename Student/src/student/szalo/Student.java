package student.szalo;

import java.io.Serializable;

public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String group;
	
	public Student(int id, String name, String group) {
		this.id = id;
		this.name = name;
		this.group = group;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", Group=" + group + "]";
	}
	
	
	
	
}
