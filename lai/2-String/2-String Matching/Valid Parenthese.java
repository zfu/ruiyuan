/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. The brackets must close in the correct order.

Examples

"()" and "()[]{}", "[{()}()]" are all valid but "(]" and "([)]" are not.
 */

 //https://leetcode.com/problems/valid-parentheses/
public class Solution {
    public boolean isValid(String input) {
      // Write your solution here
      if (input == null || input.length() == 0) {
        return true;
      }
      Stack<Character> stack = new Stack<>();
      for (int i = 0; i < input.length(); i++) {
        char c = input.charAt(i);
        if (c == '(' || c == '[' || c == '{') {
          stack.push(c);
        } else if (!stack.isEmpty() && isValid(stack.peek(), c)){
          stack.pop();
        } else {
          return false;
        }
      }
      return true;
    }
    
    private boolean isValid(Character c1, Character c2) {
      if ((c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}')) {
        return true;
      }
      return false;
    }
  }
  