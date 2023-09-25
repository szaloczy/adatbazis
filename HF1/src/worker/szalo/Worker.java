package worker.szalo;

import java.io.Serializable;
import java.util.Date;

public class Worker implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String name;
	private Date bday;
	private String residence;
	private int iq;
	
	public Worker(int code, String name, Date bday, String residence, int iq) {
		this.code = code;
		this.name = name;
		this.bday = bday;
		this.residence = residence;
		this.iq = iq;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBday() {
		return bday;
	}

	public void setBday(Date bday) {
		this.bday = bday;
	}

	public String getResidence() {
		return residence;
	}

	public void setResident(String residence) {
		this.residence = residence;
	}

	public int getIq() {
		return iq;
	}

	public void setIq(int iq) {
		this.iq = iq;
	}

	@Override
	public String toString() {
		return "Worker [code=" + code + ", name=" + name + ", bday=" + bday + ", residence=" + residence + ", iq=" + iq
				+ "]";
	}
		public int compareTo(Worker other) {
	        return Integer.compare(this.code, other.code);
	    }
	
}
