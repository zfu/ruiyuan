/**
 Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]

Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */

 /**
  Naive Solution: Time:  O(n^2*k) with n the total number of words in the "words" array and k the average length of each word: check each combination see if it's palindrome. TLE of course.

 

Better Solution: Time: O(n*k^2)

think of a word A which contains two part, 

1.   A = XX + B,    XX is palindrome, then "B_reverse + XX + B" will make a palindrome, find if B_reverse exists in the the list

2.   A = C + XX ,   then "C + XX + C_reverse" will make a palindrome, find if C_reverse exists in the list,

To ensure quick search, use HashMap 

 

Be careful about duplicate search:  [abcd, dcba],

in first iteration, we look at word abcd, at iteration where sndHalf == "", we add {0,1}

in second iteration, we look at word dcba, at iteration where fstHaf == "", we also add {0, 1}, duplicates
  */
public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
          List<List<Integer>> res = new ArrayList<List<Integer>>();
          if (words==null || words.length==0) return res;
          Map<String, Integer> map = new HashMap<>();
          for (int i=0; i<words.length; i++) {
              map.put(words[i], i);
          }
          for (int i=0; i<words.length; i++) {
              int len = words[i].length();
              for (int j=0; j<=words[i].length(); j++) {
                  String fstHalf = words[i].substring(0, j);
                  String sndHalf = words[i].substring(j);
                  
                  if (isPalindrome(fstHalf)) {
                      String sndHalfRev = new StringBuffer(sndHalf).reverse().toString();
                      if (map.containsKey(sndHalfRev) && map.get(sndHalfRev)!=i) { //"aaaa" case
                          ArrayList<Integer> item = new ArrayList<Integer>();
                          item.add(map.get(sndHalfRev));
                          item.add(i);
                          res.add(new ArrayList<Integer>(item));
                      }
                  }
                  if (isPalindrome(sndHalf)) {
                      String fstHalfRev = new StringBuffer(fstHalf).reverse().toString();
                      if (map.containsKey(fstHalfRev) && map.get(fstHalfRev)!=i && sndHalf.length()!=0) {
                          ArrayList<Integer> item = new ArrayList<Integer>();
                          item.add(i);
                          item.add(map.get(fstHalfRev));
                          res.add(new ArrayList<Integer>(item));
                      }
                  }
              }
          }
          return res;
      }
      
      public boolean isPalindrome(String str) {
          int r = str.length()-1;
          int l = 0;
          while (l <= r) {
              if(str.charAt(l++) != str.charAt(r--)) return false;
          }
          return true;
      }
  }
  