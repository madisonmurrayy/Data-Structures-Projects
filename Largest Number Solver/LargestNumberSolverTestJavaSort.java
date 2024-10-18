package assign04;

import java.util.ArrayList;

import assign03.PriorityQueueTimerInsert;

public class LargestNumberSolverTestJavaSort extends TimerTemplate{
	
	ArrayList<Integer[]> integerList = new ArrayList<Integer[]>();

	public LargestNumberSolverTestJavaSort(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setup(int n) {
		
		Integer[] integers = new Integer[5];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < 5; j++) {
				integers[j] = (int) Math.random()*10;
			}
			integerList.add(integers);
		}
		
	}

	@Override
	protected void timingIteration(int n) {
		LargestNumberSolver.findKthLargest(integerList, (int) Math.random()*n+1);
		
	}

	@Override
	protected void compensationIteration(int n) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
 
		  ArrayList<Integer> ns = new ArrayList<>();
		  for(double n = 500; n <= 10000; n += 500){
		    ns.add((int)n);
		  }

		  //convert to int[]
		  int[] problemSizes = new int[ns.size()];
		  for(int i = 0; i < problemSizes.length; i++){
		    problemSizes[i] = ns.get(i);
		  }

		  var timer = new LargestNumberSolverTimer(problemSizes, 100);
		  var results = timer.run();
		  System.out.println("n, time");
		  for(var result: results){
		    System.out.println(result.n() + ", " + result.avgNanoSecs());
		  }
	}

}
