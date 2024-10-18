package assign09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;


public class HashTableTimer extends TimerTemplate {

	HashTable<StudentBadHash, Double> studentGpaTable;
	HashTable<String, Double> table;
	
//	HashTable<StudentMediumHash, Double> studentGpaTable;
//	HashTable<StudentGoodHash, Double> studentGpaTable;
	
	ArrayList<Integer> uids;

	private int timesToLoop = 1000;
	private int loopCount =0;
	private int collisionCount;
//	public  ArrayList<Integer> count;
	Random rnd = new Random();
	public HashTableTimer(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
	}
	
	@Override
	protected void setup(int n) {
		
		//generate random students
		//studentGpaTable = new HashTable<StudentBadHash, Double>();
//		studentGpaTable = new HashTable<StudentMediumHash, Double>();
//		studentGpaTable = new HashTable<StudentGoodHash, Double>();
		

//		
//		 uids = new ArrayList<>();
////		 count = new ArrayList<>();
//		for(int i =0; i<n;i++) {
//			uids.add(i);
////			count.add(null);
//		}
//		Collections.shuffle(uids);
		
		//THESE ARE THE TESTS FOR THE COUNT OF COLLISIONS
		
		
		
	}

@Override
protected void timingIteration(int n) {
//	studentGpaTable = new HashTable<StudentGoodHash, Double>();
	//studentGpaTable = new HashTable<StudentMediumHash, Double>();
//studentGpaTable = new HashTable<StudentBadHash, Double>();

	
	//JAVAS HASHMAP
	table = new HashTable<String, Double>();
	for(int i =0; i < n;i++) {
		
	
	String name = "";
	//makes a random string length 0-15
	for(int j =0; j< rnd.nextInt(15); j++) {
		name += (char)('a' + (rnd.nextInt(10)*i) % 26);
	}
	table.put(name, rnd.nextDouble());
	}
	
	
	
	
	
	
	//System.out.println("NEW TIMING ITERATION " + n);
	//loopCount =0;

	//for(int i =0; i<n; i++) {
	//	System.out.println(i);
//		String firstName = "";
//		String lastName = "";
//		for(int j =0; j< rnd.nextInt(15); j++) {
//			firstName += (char)('a' + (rnd.nextInt(10)*i) % 26);
//		}
//		for(int k =0; k< rnd.nextInt(15); k++) {
//			lastName += (char)('a' + (rnd.nextInt(10)*i) % 26);
//		}
//		String firstName = ((char)('A' + (i+rnd.nextInt(10)) % 26) + "" + (char)('a' + (rnd.nextInt(10)*i) % 26));
//		String lastName = ((char)('A' + (i+rnd.nextInt(10)) % 26) + "" + (char)('a' + (rnd.nextInt(10)*i) % 26));

//		System.out.println(firstName);
//		System.out.println(lastName);

		//remove and return the first index of uids array to get a new, uique UID every time
		//System.out.println("i: " + i  +", n: " + n);
		
		//studentGpaTable.put(new StudentBadHash(rnd.nextInt(n),firstName,lastName), rnd.nextDouble(4.0));
		//studentGpaTable.put(new StudentMediumHash(uids.get(i),firstName,lastName), rnd.nextDouble(4.0));
		//studentGpaTable.put(new StudentGoodHash(uids.get(i),firstName,lastName), rnd.nextDouble(4.0));
	//}
//	System.out.println(studentGpaTable.getCollisions());
//	count.set(loopCount, studentGpaTable.getCollisions()) ;
//	System.out.println(count.size());
//	collisionCount+= studentGpaTable.getCollisions();
//	loopCount++;
//	//System.out.println("loop count " + loopCount);
//	if(loopCount == timesToLoop) {
//		
//		System.out.println(n+", " + collisionCount/loopCount + ", "+ studentGpaTable.capacity);
//	//	System.out.println(studentGpaTable.capacity);
//		loopCount=0;
//
//	}

		
}

@Override
protected void compensationIteration(int n) {
	
}
public static void main(String[] args) {

	ArrayList<Integer> ns = new ArrayList<>();
	for (double n = 100; n <= 2000; n += 100) {
		ns.add((int) n);
	}

	int[] problemSizes = new int[ns.size()];
	for (int i = 0; i < problemSizes.length; i++) {
		problemSizes[i] = ns.get(i);
	}

	var timer = new HashTableTimer(problemSizes, 1000);
	var results = timer.run();
	System.out.println("n, time");
	for (var result : results) {
		//USE THIS TO COUNT NUMBER OF COLLISIONS
		//System.out.println(result.n() + ", " + (studentGpaTable.countFinal) );


		
		System.out.println(result.n() + ", " + (result.avgNanoSecs() / 2) );
		
		
		//THESE ARE THE TESTS FOR THE COUNT OF COLLISIONS(times to loop must be 1)
//		int i =0;
//		System.out.println(count.size());
//		System.out.println(result.n() + ", " + (getCollisions.get(i)) );
//		i++;

		
	}

}
	
}
