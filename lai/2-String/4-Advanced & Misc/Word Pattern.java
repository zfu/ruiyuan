/**
 * 
 Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Example 1:

Input: pattern = "abab", str = "redblueredblue"
Output: true
Example 2:

Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
Output: true
Example 3:

Input: pattern = "aabb", str = "xyzabcxzyabc"
Output: false
Notes:
You may assume both pattern and str contains only lowercase letters.
 */

 // https://leetcode.com/problems/word-pattern-ii/

 public class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
         Set<String> set = new HashSet<>();
         Map<Character, String> map = new HashMap<>();
         return backtrack(pattern, str, set, map, 0, 0);      
     }
     
     public boolean backtrack(String pattern, String str, Set<String> set, Map<Character, String> map,int patternLen, int strLen){
         if(patternLen == pattern.length() && strLen == str.length()) return true;
         if(patternLen == pattern.length() || strLen == str.length()) return false; 
         
         //get the first pattern from the current patternLen
         char c = pattern.charAt(patternLen);
         
         //when the pattern is in the map, that means we need to check whether the string match or not 
         if(map.containsKey(c)){
             String s = map.get(c);
             
             //this means that the value (string) in the map does not match the next substring of the string
             //and thus, this is not a valid pattern-string match, and we return false here 
             if(!str.startsWith(s, strLen)){
                 return false; 
             }
             
             //dont't forget to move both poimter at the same time patternLen alwayas +1 when move to the next stage 
             return backtrack(pattern, str, set, map, patternLen + 1, strLen +  s.length()); 
         }
         
         //the following code happen when we do not have the pattern c in the map yet
         //in this case, we will first assign an matched string to the current pattern, and we do that we cutting the str 
         //starting from the current pointer for str, add 1 each time to create a potential s for matching 
         for(int k = strLen; k < str.length(); k++){
             String s = str.substring(strLen, k + 1);
             //we skip the string if it is already in the set to avoid duplicate 
             if(set.contains(s)) continue;
             //if the string is not in the set, we put it in the map with the crrent pattern c 
             map.put(c, s);
             set.add(s);
             
             //backtracking from here 
             if(backtrack(pattern, str, set, map, patternLen+1, k+1)){
                 return true; 
             }
             
             //cleaning up this stage 
             map.remove(c);
             set.remove(s);
         }
         return false; 
     }
 }

 
 /**
  We can solve this problem using backtracking, we just have to keep trying to use a character in the pattern to match different length of substrings in the input string, keep trying till we go through the input string and the pattern.

For example, input string is "redblueredblue", and the pattern is "abab", first let's use 'a' to match "r", 'b' to match "e", then we find that 'a' does not match "d", so we do backtracking, use 'b' to match "ed", so on and so forth ...

When we do the recursion, if the pattern character exists in the hash map already, we just have to see if we can use it to match the same length of the string. For example, let's say we have the following map:

'a': "red"

'b': "blue"

now when we see 'a' again, we know that it should match "red", the length is 3, then let's see if str[i ... i+3] matches 'a', where i is the current index of the input string. Thanks to StefanPochmann's suggestion, in Java we can elegantly use str.startsWith(s, i) to do the check.

Also thanks to i-tikhonov's suggestion, we can use a hash set to avoid duplicate matches, if character a matches string "red", then character b cannot be used to match "red". In my opinion though, we can say apple (pattern 'a') is "fruit", orange (pattern 'o') is "fruit", so they can match the same string, anyhow, I guess it really depends on how the problem states.

The following code should pass OJ now, if we don't need to worry about the duplicate matches, just remove the code that associates with the hash set.
  */

  public class Solution {
  
    public boolean wordPatternMatch(String pattern, String str) {
      Map<Character, String> map = new HashMap<>();
      Set<String> set = new HashSet<>();
      
      return isMatch(str, 0, pattern, 0, map, set);
    }
    
    boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
      // base case
      if (i == str.length() && j == pat.length()) return true;
      if (i == str.length() || j == pat.length()) return false;
      
      // get current pattern character
      char c = pat.charAt(j);
      
      // if the pattern character exists
      if (map.containsKey(c)) {
        String s = map.get(c);
        
        // then check if we can use it to match str[i...i+s.length()]
        if (!str.startsWith(s, i)) {
          return false;
        }
        
        // if it can match, great, continue to match the rest
        return isMatch(str, i + s.length(), pat, j + 1, map, set);
      }
      
      // pattern character does not exist in the map
      for (int k = i; k < str.length(); k++) {
        String p = str.substring(i, k + 1);
  
        if (set.contains(p)) {
          continue;
        }
  
        // create or update it
        map.put(c, p);
        set.add(p);
        
        // continue to match the rest
        if (isMatch(str, k + 1, pat, j + 1, map, set)) {
          return true;
        }
  
        // backtracking
        map.remove(c);
        set.remove(p);
      }
      
      // we've tried our best but still no luck
      return false;
    }
    
  }