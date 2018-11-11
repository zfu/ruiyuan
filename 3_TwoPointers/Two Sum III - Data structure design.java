/**
 * 
 * 607. Two Sum III - Data structure design
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example
add(1); add(3); add(5);
find(4) // return true
find(7) // return false
 */


public class TwoSum {
    /*
     * @param number: An integer
     * @return: nothing
     */
    ArrayList<Integer> nums = new ArrayList<>();
    
    public void add(int number) {
        // write your code here
        nums.add(number);
    }

    /*
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        // write your code here
        Collections.sort(nums);
        for (int i = 0, j = nums.size() - 1; i < j;) {
            if (nums.get(i) + nums.get(j) == value) {
                return true;
            } else if (nums.get(i) + nums.get(j) < value) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}

public class TwoSum {
    private Map<Integer, Integer> map = null;
    public TwoSum() {
        map = new HashMap<>();
    }
    /**
     * @param number: An integer
     * @return: nothing
     */
    public void add(int number) {
        // write your code here
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        // write your code here
        for (int num : map.keySet()) {
            int diff = value - num;
            if (map.containsKey(diff)) {
                if (diff != value || map.get(diff) > 1) {
                    return true;
                }
            }
        }
        return false;
    }
}