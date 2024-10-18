package assign03;

import java.util.ArrayList;

public class PriorityQueueTimerInsert extends TimerTemplate{
	
	SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();
	

	public PriorityQueueTimerInsert(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setup(int n) {
		// TODO Auto-generated method stub
		queue.clear();
		
		for(int i =0; i <n; i++) {
			queue.insert(i);
		}
		
	}

	@Override
	protected void timingIteration(int n) {
		// TODO Auto-generated method stub
		//System.out.println(n *Math.random()+1);
		int x= (int) (n *Math.random()+1);
		queue.insert(x);
		
		
	}

	@Override
	protected void compensationIteration(int n) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){

		  ArrayList<Integer> ns = new ArrayList<>();
		  for(double n = 100000; n <= 2000000; n += 100000){
		    ns.add((int)n);
		  }

		  //convert to int[]
		  int[] problemSizes = new int[ns.size()];
		  for(int i = 0; i < problemSizes.length; i++){
		    problemSizes[i] = ns.get(i);
		  }

		  var timer = new PriorityQueueTimerInsert(problemSizes, 100);
		  var results = timer.run();
		  System.out.println("n, time");
		  for(var result: results){
		    System.out.println(result.n() + ", " + result.avgNanoSecs());
		  }
	}

}
