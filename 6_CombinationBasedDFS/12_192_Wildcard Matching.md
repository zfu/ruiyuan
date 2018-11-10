### 192. Wildcard Matching
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

**Example**
```
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
```

```java

令狐冲
更新于 10/5/2018, 12:48:29 AM
使用深度优先搜索 + 记忆化的版本。

用一个二维的 boolean 数组来当记忆化数组，记录 s 从 sIndex 开始的后缀 能够匹配上 p 从 pIndex 开始的后缀
public class Solution {
    /**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        boolean[][] memo = new boolean[s.length()][p.length()];
        boolean[][] visited = new boolean[s.length()][p.length()];
        return isMatchHelper(s, 0, p, 0, memo, visited);
    }
    
    private boolean isMatchHelper(String s, int sIndex,
                                  String p, int pIndex,
                                  boolean[][] memo,
                                  boolean[][] visited) {
        // 如果 p 从pIdex开始是空字符串了，那么 s 也必须从 sIndex 是空才能匹配上
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }
        
        // 如果 s 从 sIndex 是空，那么p 必须全是 * 
        if (sIndex == s.length()) {
            return allStar(p, pIndex);
        }
        
        if (visited[sIndex][pIndex]) {
            return memo[sIndex][pIndex];
        }
        
        char sChar = s.charAt(sIndex);
        char pChar = p.charAt(pIndex);
        boolean match;
        
        if (pChar == '*') {
            match = isMatchHelper(s, sIndex, p, pIndex + 1, memo, visited) ||
                isMatchHelper(s, sIndex + 1, p, pIndex, memo, visited);
        } else {
            match = charMatch(sChar, pChar) &&
                isMatchHelper(s, sIndex + 1, p, pIndex + 1, memo, visited);
        }
        
        visited[sIndex][pIndex] = true;
        memo[sIndex][pIndex] = match;
        return match;
    }
    
    private boolean charMatch(char sChar, char pChar) {
        return (sChar == pChar || pChar == '?');
    }
    
    private boolean allStar(String p, int pIndex) {
        for (int i = pIndex; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}

/**
 * Approach 1: Recursion (Time Limit Exceeded)
 * Regular Expression Matching 的改版
 * 如果掌握了正则表达式那道题目的话，这道题目相对来说还会更加简单一下。
 * 做法基本相同。
 * 这里我们其实可以直接写出 DP 做法了，但是为了大家的理解与分析方便，
 * 这边仍然给出了 递归版本 的答案。
 */
class Solution {
    public boolean isMatch(String s, String p) {
        return process(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    private boolean process(char[] s, char[] p, int i, int j) {
        // 当 p 中没有剩余字符可以匹配的时候，如果 s 中还有剩余字符，则直接 return false
        if (j == p.length) {
            return i == s.length;
        }
        // 当 s 中没有剩余字符可以匹配的时候，如果 p 中还有剩余字符的话，需要检查是不是都是 '*'
        // 不是的话，则无法完成匹配
        if (i == s.length) {
            for (; j < p.length; j++) {
                if (p[j] != '*') {
                    return false;
                }
            }
            return true;
        }

        // 单字符匹配
        if (p[j] == '?' || s[i] == p[j]) {
            return process(s, p, i + 1, j + 1);
        } 
        // '*'为空 或者 匹配多个字符
        else if (p[j] == '*') {
            return (process(s, p, i, j + 1) || process(s, p, i + 1, j));
        } 
        // 无法匹配
        else {
            return false;
        }
    }
}

/**
 * Approach 2: DP
 * 由 递归版本 可得：
 * dp[i][j] 表示 s[i...s.length-1] 与 p[j...p.length-1] 是否能够匹配
 * dp[i][j] 的状态依赖于 dp[i+1][j], dp[i][j+1], dp[i+1][j+1] 这三个状态
 * 即该矩阵是 从下到上，从右到左 进行填充的，最终需要的状态为 dp[0][0]
 * 对此我们需要对 dp 矩阵进行一个初始化,使得我们可以根据初始化之后的 base case 推测出矩阵中的其他状态。
 * 这里需要初始化的状态为：最后一行以及最后一列
 *
 * 时间复杂度：O(mn)
 */
class Solution {
    public boolean isMatch(String s, String p) {
        char[] sArray = s.toCharArray();
        char[] pArray = p.toCharArray();
        int slen = sArray.length, plen = pArray.length;
        boolean[][] dp = new boolean[slen + 1][plen + 1];

        // Initialize the last column
        dp[slen][plen] = true;
        // Initialize the last row
        for (int j = plen - 1; j >= 0; j--) {
            if (pArray[j] == '*') {
                dp[slen][j] = true;
            } else {
                break;
            }
        }

        // Function
        for (int i = slen - 1; i >= 0; i--) {
            for (int j = plen - 1; j >= 0; j--) {
                if (pArray[j] == sArray[i] || pArray[j] == '?') {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (pArray[j] == '*') {
                    dp[i][j] = dp[i + 1][j] || dp[i][j + 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[0][0];
    }
}

/**
 * Approach 3: Two Pointers
 * The basic idea is to have one pointer for the string and one pointer for the pattern.
 * 
 * We need the traverse the String and compare the sth character in String with the pth character in pattern.
 * There are only four situation here:
 *  1. str.charAt(s) == pattern.charAt(p)
 *  In this situation, we need move pNode and sNode to the next position and continue the process.
 *  2. str.charAt(s) != pattern.charAt(p) && pattern.charAt(p) == '?'.
 *  Cuz '?' Matches any single character, the situation is the same as situation 1. sNode++ and pNode++ then continue the process.
 *  3. str.charAt(s) != pattern.charAt(p) && pattern.charAt(p) == '*'.
 *  Cuz '*' Matches any sequence of characters.
 *  Here, we use starIndex to store the current position of pNode (the position of character '*')
 *  and use match to store the current position of sNode (the starting position of String to match '*').
 *  Then we only need to move the pNode to the next position (cuz '*' can alsoo matches the empty sequence, sNode needn't to move).
 *  4. str.charAt(s) != pattern.charAt(p) && starIndex != -1.
 *  It means that we have encountered a '*' in pattern and we stored the position of it.
 *  If the character in String has matched the '*' already, the match will move to the next position (match++), then sNode = match.
 *  This is very important, you can think about this case: String - "abcdabcd", Pattern - "a*bcd" then you will understand it.
 *  Set pNode as the next position of '*' (pNode = starIndex + 1).
 *  if the situation isn't in above, we will return false.
 * After traverse the String, we also need to check for remaining characters in pattern.
 * If the remaining character is '*', then p++.
 * At the end, we only need to check whether the pNode has traverse to the end or not.
 *
 * Time complexity is O(p*s), where p and s are the lengths of the pattern and input strings. 
 */
class Solution {
    public boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIndex = -1;
        while (s < str.length()) {
            // advancing both pointers
            if (p < pattern.length() && (pattern.charAt(p) == '?'
                    || str.charAt(s) == pattern.charAt(p))) {
                s++;
                p++;
            }
            // '*' found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*') {
                starIndex = p;
                match = s;
                p++;
            }
            // we have store a '*' position in pattern string, advancing string pointer
            else if (starIndex != -1) {
                p = starIndex + 1;
                match++;
                s = match;
            }
            // current pattern pointer is not star, last patter pointer was not '*'
            // characters do not match
            else {
                return false;
            }
        }

        // check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*') {
            p++;
        }

        return p == pattern.length();
    }
}
```