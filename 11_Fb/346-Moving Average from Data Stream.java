/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/

class MovingAverage {
    Queue<Integer> q;
    int size;
    double sum;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        q = new LinkedList<>();
        this.size = size;
    }
    
    public double next(int val) {
        q.offer(val);
        sum += val;
        if (q.size() > size) sum -= q.poll();
        return (sum+ 0.0) /q.size();
    }
}