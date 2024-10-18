package assign10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import assign08.SpellCheckTimer;

public class PriorityQueueTimer extends TimerTemplate {

	PriorityQueue<Integer> ints;
	// add a list of n random ints to this in setup
	List<Integer> intsToAdd;
	PriorityQueue<Integer> intsCmp;
	// add the same list of n random ints but with a comparator object

	ArrayList<Integer> uids;

	private int timesToLoop = 1000;
	private int loopCount = 0;
	private int collisionCount;
//	public  ArrayList<Integer> count;
	Random rnd = new Random(10);

	public PriorityQueueTimer(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
	}

	@Override
	protected void setup(int n) {
		intsToAdd = new ArrayList<Integer>();
		// fill ints to be added with n different random objects
		for (int i = 0; i < n; i++) {
			intsToAdd.add(i);
		}
		Collections.shuffle(intsToAdd);

		// heap with natural ordering
		//ints = new BinaryMaxHeap<Integer>(intsToAdd);

		// heap with cmp ordering(backwawrds sorting)
		//intsCmp = new BinaryMaxHeap<Integer>(intsToAdd, (o1, o2) -> (o2.compareTo(o1)));

	}

	@Override
	protected void timingIteration(int n) {
		//ints = new BinaryMaxHeap<Integer>(intsToAdd);
		//intsCmp = new BinaryMaxHeap<Integer>(intsToAdd, (o1, o2) -> (o2.compareTo(o1)));


//		//ADD
//		for(int i =0; i<n;i++) {
//			//natural
//			//ints.add(rnd.nextInt());
//			
//			//comp
//			//intsCmp.add(rnd.nextInt());
//		}
		
		
//		//PEEK
//		
//		for(int i =0; i<n/2;i++) {
//
//		//natural
//		//ints.peek();
//			intsCmp.extractMax();
//		
//		//cmp
//		//intsCmp.peek();
//		
//		}
//		
//		//extractMax
		

		//natural
//		ints.extractMax();
		
		//cmp
		//intsCmp.extractMax();
		
//		
//		//FIND K LARGEST
//		
		//heapNatural(set k to n/2)
		FindKLargest.findKLargestSort(intsToAdd, n);
//		
//		//heapCmp
//		FindKLargest.findKLargestHeap(intsToAdd, n/2, (o1,o2) -> (o2.compareTo(o1)));
//		
//		//javaNatural
		//FindKLargest.findKLargestSort(intsToAdd, 100);
//		
//		//javaCmp
//		FindKLargest.findKLargestSort(intsToAdd, n/2, (o1,o2) -> (o2.compareTo(o1)));

		
	}

	@Override
	protected void compensationIteration(int n) {
		intsCmp = new BinaryMaxHeap<Integer>(intsToAdd, (o1, o2) -> (o2.compareTo(o1)));

	}

	public static void main(String[] args) {

		ArrayList<Integer> ns = new ArrayList<>();
		for (double n = 1000; n <= 20000; n += 1000) {
			ns.add((int) n);
		}

		int[] problemSizes = new int[ns.size()];
		for (int i = 0; i < problemSizes.length; i++) {
			problemSizes[i] = ns.get(i);
		}

		var timer = new PriorityQueueTimer(problemSizes, 10);
		var results = timer.run();
		System.out.println("n, time");
		for (var result : results) {
			System.out.println(result.n() + ", " + (result.avgNanoSecs() / 2) );
			
		}

	}

}
