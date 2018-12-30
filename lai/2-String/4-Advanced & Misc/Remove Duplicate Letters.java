/**
 * 
 Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:

Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
 */

 /**
  First, given "bcabc", the solution should be "abc". If we think about this problem intuitively, you would sort of go from the beginning of the string and start removing one if there is still the same character left and a smaller character is after it. Given "bcabc", when you see a 'b', keep it and continue with the search, then keep the following 'c', then we see an 'a'. Now we get a chance to get a smaller lexi order, you can check if after 'a', there is still 'b' and 'c' or not. We indeed have them and "abc" will be our result.

Come to the implementation, we need some data structure to store the previous characters 'b' and 'c', and we need to compare the current character with previous saved ones, and if there are multiple same characters, we prefer left ones. This calls for a stack.

After we decided to use stack, the implementation becomes clearer. From the intuition, we know that we need to know if there are still remaining characters left or not. So we need to iterate the array and save how many each characters are there. A visited array is also required since we want unique character in the solution. The line while(!stack.isEmpty() && stack.peek() > c && count[stack.peek()-'a'] > 0) checks that the queued character should be removed or not, like the 'b' and 'c' in the previous example. After removing the previous characters, push in the new char and mark the visited array.

Time complexity: O(n), n is the number of chars in string.
  */

public class Solution {
    /** credit: https://discuss.leetcode.com/topic/32259/java-solution-using-stack-with-comments/2 */
         public String removeDuplicateLetters(String s) {
             int[] res = new int[26]; //will contain number of occurences of character (i+'a')
             boolean[] visited = new boolean[26]; //will contain if character (i+'a') is present in current result Stack
             char[] ch = s.toCharArray();
             for (char c : ch) {  //count number of occurences of character
                 res[c - 'a']++;
             }
             Deque<Character> st = new ArrayDeque<>(); // answer stack
             int index;
             for (char c : ch) {
                 index = c - 'a';
                 res[index]--;   //decrement number of characters remaining in the string to be analysed
                 if (visited[index]) {
                     //if character is already present in stack, dont bother
                     continue;
                 }
                 //if current character is smaller than last character in stack which occurs later in the string again
                 //it can be removed and  added later e.g stack = bc remaining string abc then a can pop b and then c
                 while (!st.isEmpty() && c < st.peek() && res[st.peek() - 'a'] != 0) {
                     visited[st.pop() - 'a'] = false;
                 }
                 st.push(c); //add current character and mark it as visited
                 visited[index] = true;
             }
 
             StringBuilder sb = new StringBuilder();
             //pop character from stack and build answer string from back
             while (!st.isEmpty()) {
                 sb.insert(0, st.pop());
             }
             return sb.toString();
         }
 }

 
 public static class Solution2 {
    /**
     * Credit: https://discuss.leetcode.com/topic/31404/a-short-o-n-recursive-greedy-solution
     */
    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) {
                pos = i;
            }
            count[s.charAt(i) - 'a']--;
            if (count[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(
            s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }
}