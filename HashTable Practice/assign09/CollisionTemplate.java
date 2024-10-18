package assign09;

import assign09.TimerTemplate.Result;

/**
 * Abstract class which can be used as a skeleton for perfoming timing tests
 * You provide the problem sizes, number of times to repeat the tests
 * and implementations for setup, the code to be timed, and compensation loop.
 * The run() method will setup, run the code, and report the avg time in NS
 * @Author: Ben Jones
 */
abstract public class CollisionTemplate {

    private int[] problemSizes; //what N's should we time?
    private int timesToLoop; //average the result of many trials

    /**
     * Create a timer
     * @param problemSizes array of N's to use
     * @param timesToLoop number of times to repeat the tests
     */
    public CollisionTemplate(int[] problemSizes, int timesToLoop){
        this.problemSizes = problemSizes;
        this.timesToLoop = timesToLoop;
    }

    protected abstract int getCollisions();
    /**
     * Do any work that needs to be done before your code can be timed
     * For example, fill in an array with N elements
     * @param n problem size to be timed
     */
    protected abstract void setup(int n);

    /**
     * The code to be timed
     * @param n the problem size to be timed
     */
    protected abstract void timingIteration(int n);

    /**
     * Any extra work done in timingIteration that should be subtracted out
     * when computing the time of what you actually care about
     * @param n problem size being timed
     */
    protected abstract void compensationIteration(int n);


    /**
     * Store the problem size + runtime together in 1 object
     * Ignore the "record" stuff", this is basically a class with 2 public members
     * @param n the problem size
     * @param avgNanoSecs average time in NS the "timingIteration" code took, in NS
     */
    record Result(int n, double avgCollisions){} //basically a class with 2 public members

    /** Time one iteration
     * @param n problem size
     * @return average time to run the timing experiment on this problem size
     */
    private Result timeIt(int n){

        //implemented in classes inheriting from this
        setup(n); 

//        //GC/JIT warm up
//        long startTime = System.nanoTime();
//        while(System.nanoTime() - startTime < 1000000000) { // empty block
//        }

        //actual timing code
        int avgCollisions = 0;
//        startTime = System.nanoTime();
        for(int i = 0; i < timesToLoop; i++){
            timingIteration(n);
            
        }
        avgCollisions = getCollisions();
//        long afterTimedCode = System.nanoTime();
        //compensation loop
        for(int i = 0; i < timesToLoop; i++){
            compensationIteration(n);
        }
//        long afterCompensationLoop = System.nanoTime();
//        long compensationTime = afterCompensationLoop - afterTimedCode;
//        long totalTimedCodeTime = afterTimedCode - startTime;
//        double averageTime = (double)(totalTimedCodeTime - compensationTime)/timesToLoop;
        double averageCollisions = (double)avgCollisions/timesToLoop;

        return new Result(n, averageCollisions);
    }

    /**
     * Time all problem sizes
     * @return Array of timing results
     */
    Result[] run(){
        var ret = new Result[problemSizes.length];
        for(int i = 0; i < problemSizes.length; i++){
            ret[i] = timeIt(problemSizes[i]);
        }
        return ret;
    }

}
