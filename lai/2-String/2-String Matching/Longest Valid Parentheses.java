/**
 * 
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.  

Example

")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */

 /**
  * 
  https://leetcode.com/problems/longest-valid-parentheses/solution/

  Instead of finding every possible string and checking its validity, we can make use of stack while scanning the given string to check if the string scanned so far is valid, and also the length of the longest valid string. In order to do so, we start by pushing -1−1 onto the stack.

For every \text{‘(’}‘(’ encountered, we push its index onto the stack.

For every \text{‘)’}‘)’ encountered, we pop the topmost element and subtract the current element's index from the top element of the stack, which gives the length of the currently encountered valid string of parentheses. If while popping the element, the stack becomes empty, we push the current element's index onto the stack. In this way, we keep on calculating the lengths of the valid substrings, and return the length of the longest valid string at the end.
  */

  public class Solution {

    public int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}

/**
 * In this approach, we make use of two counters leftleft and rightright. First, we start traversing the string from the left towards the right and for every \text{‘(’}‘(’ encountered, we increment the leftleft counter and for every \text{‘)’}‘)’ encountered, we increment the rightright counter. Whenever leftleft becomes equal to rightright, we calculate the length of the current valid string and keep track of maximum length substring found so far. If rightright becomes greater than leftleft we reset leftleft and rightright to 00.

Next, we start traversing the string from right to left and similar procedure is applied.
 */

public class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}

/**
 * Approach 2: Using Dynamic Programming
Algorithm

This problem can be solved by using Dynamic Programming. We make use of a \text{dp}dp array where iith element of \text{dp}dp represents the length of the longest valid substring ending at iith index. We initialize the complete \text{dp}dp array with 0's. Now, it's obvious that the valid substrings must end with \text{‘)’}‘)’. This further leads to the conclusion that the substrings ending with \text{‘(’}‘(’ will always contain '0' at their corresponding \text{dp}dp indices. Thus, we update the \text{dp}dp array only when \text{‘)’}‘)’ is encountered.

To fill \text{dp}dp array we will check every two consecutive characters of the string and if

\text{s}[i] = \text{‘)’}s[i]=‘)’ and \text{s}[i - 1] = \text{‘(’}s[i−1]=‘(’, i.e. string looks like ``.......()" \Rightarrow‘‘.......()"⇒

\text{dp}[i]=\text{dp}[i-2]+2 dp[i]=dp[i−2]+2

We do so because the ending "()" portion is a valid substring anyhow and leads to an increment of 2 in the length of the just previous valid substring's length.

\text{s}[i] = \text{‘)’}s[i]=‘)’ and \text{s}[i - 1] = \text{‘)’}s[i−1]=‘)’, i.e. string looks like ``.......))" \Rightarrow‘‘.......))"⇒

if \text{s}[i - \text{dp}[i - 1] - 1] = \text{‘(’}s[i−dp[i−1]−1]=‘(’ then

\text{dp}[i]=\text{dp}[i-1]+\text{dp}[i-\text{dp}[i-1]-2]+2 dp[i]=dp[i−1]+dp[i−dp[i−1]−2]+2

The reason behind this is that if the 2nd last \text{‘)’}‘)’ was a part of a valid substring (say sub_ssub 
s
​	
 ), for the last \text{‘)’}‘)’ to be a part of a larger substring, there must be a corresponding starting \text{‘(’}‘(’ which lies before the valid substring of which the 2nd last \text{‘)’}‘)’ is a part (i.e. before sub_ssub 
s
​	
 ). Thus, if the character before sub_ssub 
s
​	
  happens to be \text{‘(’}‘(’, we update the \text{dp}[i]dp[i] as an addition of 22 in the length of sub_ssub 
s
​	
  which is \text{dp}[i-1]dp[i−1]. To this, we also add the length of the valid substring just before the term "(,sub_s,)" , i.e. \text{dp}[i-\text{dp}[i-1]-2]dp[i−dp[i−1]−2].
 */

public class Solution {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}