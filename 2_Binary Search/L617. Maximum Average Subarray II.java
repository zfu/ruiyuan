/**
 * 617. Maximum Average Subarray II Given an array with positive and negative
 * numbers, find the maximum average subarray which length should be greater or
 * equal to given length k.
 * 
 * Example Given nums = [1, 12, -5, -6, 50, 3], k = 3
 * 
 * Return 15.667 // (-6 + 50 + 3) / 3 = 15.667
 * 
 * Notice It's guaranteed that the size of the array is greater or equal to k.
 */

public class Solution {
    /*
     * @param nums: an array with positive and negative numbers
     * 
     * @param k: an integer
     * 
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        // Binary search. Set a mid, find if one part of nums sum up larger than mid, if
        // yes, min = mid; if none of sum larger than mid, max = mid
        // Avg value must be in the middle of min and max.
        double max = Integer.MIN_VALUE;
        double min = Integer.MAX_VALUE;
        for (int i : nums) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        double error = max - min;
        while (error > 0.00001) {
            double mid = min + (max - min) / 2;
            if (checkExist(nums, k, mid)) {
                min = mid;
            } else {
                max = mid;
            }
            error = Math.abs(max - min);
        }
        return min;
    }

    public boolean checkExist(int[] nums, int k, double mid) {
        double sum = 0, prev = 0, min_sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i] - mid;
        if (sum >= 0)
            return true;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - mid;
            prev += nums[i - k] - mid;
            min_sum = Math.min(prev, min_sum);
            if (sum >= min_sum)
                return true;
        }
        return false;
    }
}

/**
 * Approach: Binary Search + Kadane's Algorithm Firstly, we know that the value
 * of the average could lie between the range (min, max). Here, min and max
 * refer to the minimum and the maximum values out of the given nums array. This
 * is because, the average can't be lesser than the minimum value and can't be
 * larger than the maximum value. But, in this case, we need to find the maximum
 * average of a subarray with at least k elements. The idea in this method is to
 * try to approximate(guess) the solution and to try to find if this solution
 * really exists. If it exists, we can continue trying to approximate the
 * solution even to a further precise value, but choosing a larger number as the
 * next approximation. But, if the initial guess is wrong, and the initial
 * maximum average value(guessed) isn't possible, we need to try with a smaller
 * number as the next approximate.
 *
 * Now, instead of doing the guesses randomly, we can make use of Binary Search.
 * With min and max as the initial numbers to begin with, we can find out the
 * mid of these two numbers given by (min+max)/2. Now, we need to find if a
 * subarray with length greater than or equal to k is possible with an average
 * sum greater than this mid value. To determine if this is possible in a single
 * scan, let's look at an observation. Suppose, there exist j elements, a1, a2,
 * a3..., aj ​​in a subarray within nums such that their average is greater than
 * mid. In this case, we can say that (a1+a2+a3...+aj)/j ≥ mid or
 * (a1+a2+a3...+aj) ≥ j*mid or (a1−mid)+(a2−mid)+(a3−mid)...+(aj−mid) ≥ 0 (our
 * code using this method) Thus, we can see that if after subtracting the mid
 * number from the elements of a subarray with more than k−1 elements, within
 * nums, (Here we use Kadane's Algorithm) if the sum of elements of this reduced
 * subarray is greater than 0, we can achieve an average value greater than mid.
 * Thus, in this case, we need to set the mid as the new minimum element and
 * continue the process. Otherwise, if this reduced sum is lesser than 0 for all
 * subarrays with greater than or equal to k elements, we can't achieve mid as
 * the average. Thus, we need to set mid as the new maximum element and continue
 * the process.
 *
 * Every time after checking the possibility with a new mid value, at the end,
 * we need to settle at some value as the average. But, we can observe that
 * eventually, we'll reach a point, where we'll keep moving near some same value
 * with very small changes. In order to keep our precision in control, we limit
 * this process to 1e-6 precision, by making use of error and continuing the
 * process till error becomes lesser than 0.000001 .
 *
 * Get more detail about Kadane's Algorithm here:
 * https://github.com/cherryljr/LintCode/blob/master/Maximum%20Subarray.java
 * Good Explanation here:
 * https://mp.weixin.qq.com/s?__biz=MzA5MzE4MjgyMw==&mid=2649458133&idx=1&sn=a27ec0ef7e2a871959598d19ba876573&chksm=887eebddbf0962cb491c35dade815424bc375cf4309929b4d6472eadebeb9561bbbeb411f5e4&mpshare=1&scene=1&srcid=0816fQEJf75jmt6zpk5Tn8W7&key=02a45e2d696653c0434edec9cf3297051d6b8b6658d00ceb1eb3d48a922a7b419b2d5d40fb4ed1ebe236b29e34636adce6272f73544d3dab66694eaf38927b72f2a0efa6549bc173a7881fe7f0b5ff49&ascene=0&uin=Mjg3NzU0NTM4MA%3D%3D&devicetype=iMac+MacBook9%2C1+OSX+OSX+10.12.6+build(16G29)&version=12020810&nettype=WIFI&fontScale=100&pass_ticket=XtCmvn2oZ%2BRx1xG9qCLAeSWvYYd04dPLV2OA7Ozh5i9TyZ71w%2FW9ciFosEayuRbw
 */
public class Solution {
    /*
     * @param nums: an array with positive and negative numbers
     * 
     * @param k: an integer
     * 
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0d;
        }

        int len = nums.length;
        double min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        // get the minimum and maximum element of this array, so we can do binary search
        // based on them.
        for (int i = 0; i < len; i++) {
            min = Math.min(min, (double) nums[i]);
            max = Math.max(max, (double) nums[i]);
        }

        double[] preSum = new double[len + 1];
        // Binary Search
        while (max - min > 1e-6) {
            double mid = min + (max - min) / 2;
            // calculate the prefix sum array (substract mid)
            for (int i = 0; i < len; i++) {
                preSum[i + 1] = preSum[i] + nums[i] - mid;
            }

            // Kadane's Algorithm
            // Using prefix sum to get the biggest sum subarray (length is no smaller than
            // k)
            double maxSum = Integer.MIN_VALUE;
            double minSum = 0;
            for (int i = k; i <= len; i++) {
                maxSum = Math.max(maxSum, preSum[i] - minSum);
                minSum = Math.min(minSum, preSum[i - k + 1]);
            }
            if (maxSum >= 0) {
                min = mid;
            } else {
                max = mid;
            }
        }

        return max;
    }
}

/**
 * 输入： [1,12,-5,-6,50,3], k = 4
 * 
 * 
 * 
 * 输出： 12.75
 * 
 * 
 * 
 * 说明：
 * 
 * 长度为4的子数组中，最大的平均值为12.75，（=(12 + -5 + -6 + 50)/4）
 * 
 * 
 * 长度为5的子数组中，最大的平均值为10.8，（=(12 + -5 + -6 + 50 + 3)/5）
 * 
 * 
 * 长度为6的子数组中，最大的平均值为9.16667。（所有数的平均值）
 * 
 * 
 * 因此返回12.75。
 * 
 * 
 * 
 * 
 * 
 * 解题思路分析
 * 
 * 
 * a. 可以枚举所有的长度大于等于k的子数组计算平均值，并对所有得到的平均值求最大值，这样可以做到时间复杂度O(n^2)，但是会超时。
 * 
 * 
 * 
 * 或许有同学会想到是不是可以只看长度为k的子数组，因为如果没有长度限制，那么显然最大平均值子数组就是数组中最大的数（长度为1的子数组），
 * 而且刚好样例给出的数据是满足长度为k的所有子数组的最大平均值随着k增大而减小的。
 * 
 * 
 * 
 * 很可惜这个想法是错误的，很容易举出反例，对于[1, -1, 1]，
 * 长度1子数组的最大平均值为1，长度2的为0，长度3的为1/3，如果题目给出k=2，则应输出返回1/3而非0。
 * 
 * 
 * 
 * 
 * 
 * b. 有些最值问题可以转化为判断问题从而用二分法求得答案。
 * 
 * 
 * 
 * 对于n个数a(0),a(1),……,a(n-1)，以及一个数A，如果存在一个子数组起始于i，长为L>=k，使得其平均值大于等于A，即(a(i)+a(i+1)+……+a(i+L-1))/L
 * >= A，那么我们所求的答案应当大于等于A；反之如果对于所有长度大于等于k的子数组，其平均值均小于A，那么我们所求的答案也必然小于A。
 * 
 * 
 * 
 * i. 如何判断是否存在长度至少为k的子数组，其平均值大于等于A？
 * 
 * 
 * 
 * 观察式子(a(i)+a(i+1)+……+a(i+L-1))/L >=
 * A，其等价于(a(i)-A)+(a(i+1)-A)+……+(a(i+L-1)-A)>=0，令b(0)=a(0)-A , b(1)=a(1)-A , ……
 * ,
 * b(n-1)=a(n-1)-A，那么判断a数组中是否存在长度至少为k的子数组平均值大于等于A，就变成了判断b数组中是否存在长度至少为k的子数组和大于等于0，
 * 只要求出b数组长度至少为k的子数组的最大和与0比较即可。
 * 
 * 
 * 
 * 
 * 
 * ii.
 * 求长度大于等于k的最大和子数组比原问题容易的多，令s为b的前缀和子数组，即s(i)=b(0)+b(1)+……+b(i-1)，且s(0)=0，那么b(j)到b(i-1)的区间和可表示为s(i)-s(j)，
 * 找长度大于等于k的最大和子数组等价于找i,j，满足i-j>=k，且使s(i)-s(j)最大。
 * 
 * 
 * 
 * 固定i，则要使s(i)-s(j)最大，s(j)应最小，同时也应满足j<=i-k，令p(i) = min{s(j)},j<=i-k，故 i
 * 固定时s(i)-s(j)的最大值为s(i)-p(i)，枚举所有i即可得到最终的最大值。因为s(i),p(i)均可通过递推得到，故时间复杂度为O(n)。
 * 
 * 
 * 
 * 
 * 
 * iii. 这样一来，我们就可以二分答案，二分的初始区间可以设置为[min{a(i)},i=0~n-1 ,
 * max{a(i)},i=0~n-1]，因为一组数的平均值不会小于这组数的最小值，也不会大于这组数的最大值。
 * 
 * 
 * 
 * 对于二分值A，通过前面讲的方法以O(n)的时间判断是否有子数组的平均值大于等于A，若有则答案大于等于A，若没有，则答案小于A。
 * 
 * 
 * 
 * 二分至区间长度小于所需精度，即可返回该值。时间复杂度为O(n*log((MAX-MIN) /
 * eps))，其中MIN、MAX分别为a数组的最小值和最大值，eps为要求的精度。
 * 
 * 
 * 
 * 
 * 
 * Follow up：本题亦有O(n)时间复杂度的算法（斜率优化/单调队列），但是比较难以理解，而且在面试中一般不会考，有兴趣的读者可以了解一下。
 */