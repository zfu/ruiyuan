/**
 * 846. Multi-keyword Sort 
 * 
 * 
 * Given n students(number from 1 to n) and their
 * examination grades. There are two keywords, examination grades and student
 * id. Order the array according to the first keyword (Descending), if the first
 * key is the same, it is sorted by the second keyword (ascending).
 * 
 * Example Given [[2,50],[1,50],[3,100]], return [[3,100],[1,50],[2,50]]
 */

public class Solution {
    /**
     * @param array: the input array
     * @return: the sorted array
     */
    public int[][] multiSort(int[][] array) {
        // Write your code here
        if (array == null || array.length == 0) {
            return new int[][]{};
        }
        
        Arrays.sort(array, (a, b) -> {
           if (a[1] == b[1]) {
               return a[0] - b[0];
           } 
           return b[1] - a[1];
        });
        return array;
    }
}

public class Solution {
    /**
     * @param array: the input array
     * @return: the sorted array
     */
    public int[][] multiSort(int[][] array) {
        // Write your code here
        Arrays.sort(array, new Comparator<int[]>(){
            public int compare(int[] l, int[] r) {
                if (l[1] != r[1]) {
                    return r[1] - l[1];
                }
                return l[0] - r[0];
            }
        });
        return array;
    }
}