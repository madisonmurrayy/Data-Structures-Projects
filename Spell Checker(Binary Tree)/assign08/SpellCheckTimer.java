package assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;


public class SpellCheckTimer extends TimerTemplate {

	BinarySearchTree<Integer> ints;
	TreeSet<Integer> trees;
	ArrayList<Integer> toAdd;
	Random rnd = new Random(19);
	public SpellCheckTimer(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
	}
	
	@Override
	protected void setup(int n) {
		//reset before we add each time
		ints = new BinarySearchTree<>();
		toAdd = new ArrayList<Integer>();
		trees = new TreeSet<>();
		
		
		//random ints 0-n
		for(int i =0; i<n; i++) {
			toAdd.add(i);
		}
		
		
		//Collections.shuffle(toAdd);
		//System.out.println(toAdd.toString());

		//add to tree
	    //trees.addAll(toAdd);
		
		//add to binary tree
		ints.addAll(toAdd);
		
		
		
		
	}

@Override
protected void timingIteration(int n) {
	
	//add to tree
	    //ints.addAll(toAdd);
		
		//add to binary tree
	//trees.addAll(toAdd);
	
	
	//timing for trees addAll
//	for(int i =0; i<n; i++) {
//		trees.add(rnd.nextInt(n));
//	}
	
	//timing for binary addAll
//	for(int i =0; i<n; i++) {
//		ints.add(rnd.nextInt(n));
//	}
	
//	//timing for trees contains
//	for(Integer i: trees) {
//		trees.contains(i);
//	}
	
//	//timing for binary contains
	for(int i =0; i< n; i++) {
		ints.contains(i);
	}
	
	//try to just look for the greatest element(n)
}

@Override
protected void compensationIteration(int n) {
	
}
public static void main(String[] args) {

	ArrayList<Integer> ns = new ArrayList<>();
	for (double n = 1000; n <= 10000; n += 1000) {
		ns.add((int) n);
	}

	int[] problemSizes = new int[ns.size()];
	for (int i = 0; i < problemSizes.length; i++) {
		problemSizes[i] = ns.get(i);
	}

	var timer = new SpellCheckTimer(problemSizes, 100);
	var results = timer.run();
	System.out.println("n, time");
	for (var result : results) {
		System.out.println(result.n() + ", " + (result.avgNanoSecs() / 2) );
	}

}
	
}
