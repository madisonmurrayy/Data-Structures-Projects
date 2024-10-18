package assign05;

import java.util.ArrayList;

import assign04.LargestNumberSolverTimer;

public class ArrayListSorterTimer extends TimerTemplate {

	ArrayList<Integer> arr = new ArrayList<Integer>();
	
	public ArrayListSorterTimer(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setup(int n) {
		// TODO Auto-generated method stub
		arr = ArrayListSorter.generateAscending(n);
		
		
	}

	@Override
	protected void timingIteration(int n) {
		// TODO Auto-generated method stub
		ArrayListSorter.mergesort(arr);
		
	}

	@Override
	protected void compensationIteration(int n) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		 
		  ArrayList<Integer> ns = new ArrayList<>();
		  for(double n = 40000; n <= 300000; n += 20000){
		    ns.add((int)n);
		  }

		  //convert to int[]
		  int[] problemSizes = new int[ns.size()];
		  for(int i = 0; i < problemSizes.length; i++){
		    problemSizes[i] = ns.get(i);
		  }

		  var timer = new ArrayListSorterTimer(problemSizes, 100);
		  var results = timer.run();
		  System.out.println("n, time");
		  for(var result: results){
		    System.out.println(result.n() + ", " + result.avgNanoSecs());
		  }
	}

}
