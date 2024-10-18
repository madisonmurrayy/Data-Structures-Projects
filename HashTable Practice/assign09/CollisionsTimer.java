package assign09;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

public class CollisionsTimer extends CollisionTemplate{









//	HashTable<StudentBadHash, Double> studentGpaTable;
//	HashTable<StudentMediumHash, Double> studentGpaTable;
	HashTable<StudentGoodHash, Double> studentGpaTable;
	
	ArrayList<Integer> uids;

	private int loopCount =0;
	private static ArrayList<Integer> count;
	Random rnd = new Random();
	public CollisionsTimer(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
	}
	
	@Override
	protected void setup(int n) {
		//generate random students
//		studentGpaTable = new HashTable<StudentBadHash, Double>();
//		studentGpaTable = new HashTable<StudentMediumHash, Double>();
		studentGpaTable = new HashTable<StudentGoodHash, Double>();
		

		
		 uids = new ArrayList<>();
//		 count = new ArrayList<>();
		for(int i =0; i<n;i++) {
			uids.add(i);
//			count.add(null);
		}
		Collections.shuffle(uids);
		
		
		//THESE ARE THE TESTS FOR THE COUNT OF COLLISIONS
		
		
		
	}

@Override
protected void timingIteration(int n) {
	System.out.println("NEW TIMING ITERATION " + n);

	for(int i =0; i<n; i++) {
		String firstName = ((char)('A' + (i+rnd.nextInt(10)) % 26) + "" + (char)('a' + (rnd.nextInt(10)*i) % 26));
		String lastName = ((char)('A' + (i+rnd.nextInt(10)) % 26) + "" + (char)('a' + (rnd.nextInt(10)*i) % 26));

//		System.out.println(firstName);
//		System.out.println(lastName);

		//remove and return the first index of uids array to get a new, uique UID every time
		
//		studentGpaTable.put(new StudentBadHash(rnd.nextInt(n),firstName,lastName), rnd.nextDouble(4.0));
//		studentGpaTable.put(new StudentMediumHash(uids.get(i),firstName,lastName), rnd.nextDouble(4.0));
		studentGpaTable.put(new StudentGoodHash(rnd.nextInt(n),firstName,lastName), rnd.nextDouble(4.0));
		//System.out.println(studentGpaTable.getCollisions());
	}
	System.out.println(studentGpaTable.getCollisions());
//	count.set(loopCount, studentGpaTable.getCollisions()) ;
//	System.out.println(count.size());
//	loopCount++;

		
}

@Override
protected void compensationIteration(int n) {
	
}
public static void main(String[] args) {

	ArrayList<Integer> ns = new ArrayList<>();
	for (double n = 10; n <= 50; n += 10) {
		ns.add((int) n);
	}

	int[] problemSizes = new int[ns.size()];
	for (int i = 0; i < problemSizes.length; i++) {
		problemSizes[i] = ns.get(i);
	}

	var timer = new HashTableTimer(problemSizes, 1);
	var results = timer.run();
	System.out.println("n, time");
	for (var result : results) {
		//USE THIS TO COUNT NUMBER OF COLLISIONS
		//System.out.println(result.n() + ", " + (studentGpaTable.countFinal) );


		
		System.out.println(result.n() + ", " + (result.avgCollisions() / 2) );
		
		
		//THESE ARE THE TESTS FOR THE COUNT OF COLLISIONS(times to loop must be 1)
//		int i =0;
//		System.out.println(count.size());
//		System.out.println(result.n() + ", " + (count.get(i)) );
//		i++;

		
	}

}

@Override
protected int getCollisions() {
	// TODO Auto-generated method stub
	return studentGpaTable.getCollisions();
}
	
}
