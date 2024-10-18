package assign03;

import java.util.Comparator;

public class Students implements Comparator<Students>{

	private String firstName;
	private String lastName;
	private int ID;
	public Students(String firstName, String lastName, int ID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = ID;
	}
	//@Override
	public boolean equals(Students other) {
		if((this.getFirstName().equals(other.getFirstName()) && (this.getLastName().equals(other.getLastName())) &&(this.getID()==(other.getID())))) {
			return true;
		}
		return false;
	}
	public String getFirstName() {
		return this.firstName;
	}
	private String getLastName() {
		return this.lastName;
	}
	private int getID() {
		return this.ID;
	}
//	@Override
//	public int compare(Students o1, Students o2) {
//		// TODO Auto-generated method stub
//		if(( o1).getLastName().equals(( o2).getLastName())) {
//			if(( o1).getFirstName().equals(( o2).getFirstName())) {
//				if(( o1).getID() > (o2).getID()) {
//					return 1;
//				}
//				else if((o1).getID() < ( o2).getID()) {
//					return -1;
//				}
//				return 0;
//			}
//			
//			return ( o1).getFirstName().compareTo(( o2).getFirstName());
//			}
//		return  (o1).getLastName().compareTo(( o2).getLastName());
//	}
//	public class OrderByStudent implements Comparator<Students>{
//		@Override
//		public int compare(Students o1, Students o2) {
//			// TODO Auto-generated method stub
//			if(( o1).getLastName().equals(( o2).getLastName())) {
//				if(( o1).getFirstName().equals(( o2).getFirstName())) {
//					if(( o1).getID() > (o2).getID()) {
//						return 1;
//					}
//					else if((o1).getID() < ( o2).getID()) {
//						return -1;
//					}
//					return 0;
//				}
//				
//				return ( o1).getFirstName().compareTo(( o2).getFirstName());
//				}
//			return  (o1).getLastName().compareTo(( o2).getLastName());
//		}
//	}

	@Override
	public int compare(Students o1, Students o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
