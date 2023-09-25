package worker.szalo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class CrtWorker {
	private static final String fname = "workers.txt";
	private List<Worker> workerList;
	
	public CrtWorker() {
		this.workerList = new ArrayList<Worker>();
		load();
	}
	
	public void Insertion(int code, String name, Date bday, String residence, int iq) {
		Worker nWorker = new Worker(code, name, bday, residence, iq);
		workerList.add(nWorker);
		save();
	}
	
	private void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fname));
			oos.writeObject(workerList);
			System.out.println("Workers are saved!");
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void list() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname));
			@SuppressWarnings("unchecked")
			List<Worker> listedWorkers = (List<Worker>) ois.readObject();
			for (Worker worker : listedWorkers) {
				System.out.println(worker);
			}
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public void sortByCode() {
    	 
    	workerList.sort(Comparator.comparingInt(Worker::getCode));

    	save();
        System.out.println("Workerd are sorted!");
    }

	
	public void deleteByCode(int id) {
		Worker removeWr = null;
		for (Worker worker : workerList) {
			if (worker.getCode() == id) {
				removeWr = worker;
				break;
			}
		}

		if (removeWr != null) {
			workerList.remove(removeWr);
			System.out.println("Worker deleted!" + removeWr);
			save();
		} else {
			System.out.println("This Worker code is not exits!");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname));
			workerList = (List<Worker>) ois.readObject();
			ois.close();
			System.out.println("Workers are loaded! ");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
