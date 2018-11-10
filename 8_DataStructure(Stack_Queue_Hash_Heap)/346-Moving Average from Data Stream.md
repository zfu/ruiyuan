### [346\. Moving Average from Data Stream](https://leetcode.com/problems/moving-average-from-data-stream/description/)

Difficulty: **Easy**



Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

**Example:**

```
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
```



#### Solution

Language: **Java**

```java
public class MovingAverage {
    private Queue<Integer> queue = null;
    private int size = 0;
    private double sum = 0;
    /*
    * @param size: An integer
    */public MovingAverage(int size) {
        // do intialization if necessary
        queue = new LinkedList<Integer> ();
        this.size = size;
    }

    /*
     * @param val: An integer
     * @return:  
     */
    public double next(int val) {
        // write your code here
        queue.offer(val);
        sum += val;
        if (queue.size() > this.size) {
            sum -= queue.poll();
        }
        return sum / queue.size();
    }
}
​
/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
```