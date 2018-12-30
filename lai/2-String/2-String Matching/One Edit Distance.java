/**
 * Determine if two given Strings are one edit distance.

One edit distance means you can only insert one character/delete one character/replace one character to another character in one of the two given Strings and they will become identical.

Assumptions:

The two given Strings are not null
Examples:

s = "abc", t = "ab" are one edit distance since you can remove the trailing 'c' from s so that s and t are identical

s = "abc", t = "bcd" are not one edit distance
 */

 /**
   *
   * https://leetcode.com/problems/one-edit-distance/
 
  There are only 4 main cases to consider.

Both a equal. then return false
Length of the strings differ by more than 1 , then return false.
Length of strings differ by 1.
Length of strings are same.
In both case 3 and 4, there can be atmost one character difference.
We use two pointers to compare the strings character by character. We keep a flag to mark if we have already found a difference.
If we find another difference we return false.

Case 4 is pretty straighforward. When we find a difference, increment both the pointers i and j by 1 and compare the rest of the string.

For case 3. We need to only advance the pointer of the longer string. Once we do that the strings should be equals. Otherwise return false
  */

public class Solution {
    public boolean oneEditDistance(String source, String target) {
      // Write your solution here
      if (source.equals(target)) {
        return false;
      }
      int m = source.length();
      int n = target.length();
      if (Math.abs(m - n) > 1) {
        return false;
      }
      int i = 0, j = 0;
      boolean diffFound = false;
      while (i < m && j < n) {
        if (source.charAt(i) == target.charAt(j)) {
          i++;
          j++;
        } else {
          if (diffFound) {
            return false;
          }
          diffFound = true;
          if (m == n) {
            i++;
            j++;
          } else if (m < n) {
            j++;
          } else {
            i++;
          }
        }
      }
      return true;
    }
  }
  

  另外FB面经有一道比较狠的这个题的变形：

复制代码
class IntFileIterator {
  boolean hasNext();
  int next();
}

class{
  public boolean isDistanceZeroOrOne(IntFileIterator a, IntFileIterator b)；

}
// return if the distance between a and b is at most 1.. 
// Distance: minimum number of modifications to make a=b
// Modification:
//   1. change an int in a
//   2. insert an int to a
//   3. remove an int from a
复制代码
这题就是one edit distance的变形题，难点在于给的Iterator，事先不知道两个file
的长度，也不允许用extra space（所以不能转成两个string再比），那么一个个往前
跑的时候就得三种情况都考虑。。。。

我的做法：

复制代码
 1 public class Solution {
 2     class IntFileIterator {
 3         boolean hasNext();
 4         int next();
 5     }
 6     
 7     public boolean isDistanceZeroOrOne(IntFileIterator a, IntFileIterator b) {
 8         return check(a, b, 0);
 9     }
10   
11       public boolean check(IntFileIterator a, IntFileIterator b, int distance){
12           IntFileIterator aa = new InFileIterator(a); // copy of iterator a before next() function
13           IntFileIterator bb = new InFileIterator(b);
14           while (a.hasNext() && b.hasNext()) {
15               int s = a.next();
16               int t = b.next();
17               if(s != t){
18                   IntFileIterator aaa = new InFileIterator(a); //copy of iterator a after next() function
19                   IntFileIterator bbb = new InFileIterator(b);
20                   distance++;
21                   if(distance>1) return false;
22                   return check(aa, b, distance) || check(a, bb, distance) || check(aaa, bbb, distance);
23               }
24               else{
25                   return check(a, b, distance);
26               }
27           }
28           
29           if(distance == 1){
30               return !a.hasNext() && !b.hasNext();
31           }else { //(distance ==0)
32               IntFileIterator k = a.hasNext()? a : b;
33               int count = 0;
34               while (k.hasNext()) {
35                   k.next();
36                   count++;
37               }
38               return k<=1;
39           }
40       }
41 }
复制代码