/**
 * 57. 3Sum Given an array S of n integers, are there elements a, b, c in S such
 * that a + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero.
 * 
 * Example For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:
 * 
 * (-1, 0, 1) (-1, -1, 2) Notice Elements in a triplet (a,b,c) must be in
 * non-descending order. (ie, a ≤ b ≤ c)
 * 
 * The solution set must not contain duplicate triplets.
 */

 
public class Solution {
    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] numbers) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(numbers);
        for (int i = 0; i <= numbers.length - 3; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            int j = i + 1, k = numbers.length - 1;
            while (j < k) {
                if (j > i + 1 && numbers[j] == numbers[j - 1]) {
                    j++;
                    continue;
                }
                if (k < numbers.length - 1 && numbers[k] == numbers[k + 1]) {
                    k--;
                    continue;
                }
                int sum = numbers[i] + numbers[j] + numbers[k];
                if (sum == 0) {
                    List<Integer> found = new ArrayList<>(Arrays.asList(new Integer[]{numbers[i], numbers[j], numbers[k]}));
                    res.add(found);
                    j++;
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }
}