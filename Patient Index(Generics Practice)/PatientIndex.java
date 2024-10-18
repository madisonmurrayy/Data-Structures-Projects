package assign02;

import java.util.Comparator;
import java.util.TreeMap;

public class PatientIndex {
	
	private TreeMap<UHealthID, String> map;
	Comparator<UHealthID> comp = (o1, o2) -> o1.toString().compareTo(o2.toString());;
	
	
	public PatientIndex(Comparator<UHealthID> comp) {
		comp = (o1, o2) -> o1.toString().compareTo(o2.toString());
	}
	
	public PatientIndex() {
		//Comparator<UHealthID> comparator = new Comparator<UHealthID>();
		//PatientIndex patientID = PatientIndex(new Comparator<UHealthID>);
		map = new TreeMap<>((o1, o2) -> o1.toString().compareTo(o2.toString()));
		
		
	}
	
//	public PatientIndex(Comparator<UHealthID> comp) {
//		comp = (o1, o2) -> o1.toString().compareTo(o2.toString());
//	}
	
	void addPatient(Patient p) {
		
		String name = p.getFirstName() + " " + p.getLastName();
		map.put(p.getUHealthID(), name);

	}
	
	void removePatient(Patient p) {
		if(map.containsKey(p.getUHealthID())) {
			//if(map.getValue(p.getUHealthID().equals(p.))
			map.remove(p.getUHealthID());
		}
	}
	
	public String getName(UHealthID id) {
		if(map.containsKey(id)) {
			return map.get(id);
		}
		return null;
	}
	

}
