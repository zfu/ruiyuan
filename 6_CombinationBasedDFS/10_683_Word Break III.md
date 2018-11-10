### 683. Word Break III
Give a dictionary of words and a sentence with all whitespace removed, return the number of sentences you can form by inserting whitespaces to the sentence so that each word can be found in the dictionary.

**Example**
```
Given a String CatMat
Given a dictionary ["Cat", "Mat", "Ca", "tM", "at", "C", "Dog", "og", "Do"]
return 3

we can form 3 sentences, as follows:
CatMat = Cat Mat
CatMat = Ca tM at
CatMat = C at Mat

Notice
Ignore case
```

```java
public class Solution {
    /*
     * @param : A string
     * @param : A set of word
     * @return: the number of possible sentences.
     */
    public int wordBreak3(String s, Set<String> dict) {
        // Write your code here
        int count = 0;
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0) {
            return count;
        }
        
        List<String> results = new ArrayList<>();
        
        dfsHelper(s, dict, 0, 0, new ArrayList<String>(), results);
        return results.size();
    }
    
    private void dfsHelper(String s, Set<String> dict, int startIndex, int count,
                          List<String> result, List<String> results) {
        if (startIndex == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (String str : result) {
                sb.append(str + " ");
            }
            sb.deleteCharAt(sb.length() - 1);
            if (!results.contains(sb.toString())) {
                results.add(sb.toString());
            }
        }
        
        for (int i = startIndex; i < s.length(); i++) {
            String substring = s.substring(startIndex, i + 1);
            if (dict.contains(substring)) {
                result.add(substring);
                dfsHelper(s, dict, i + 1, count, result, results);
                result.remove(result.size() - 1);
            }
        }
    }
}
//
//令狐冲
//更新于 9/4/2018, 10:40:43 PM
public class Solution {
    /*
     * @param : A string
     * @param : A set of word
     * @return: the number of possible sentences.
     */
    public int wordBreak3(String s, Set<String> dict) {
        int n = s.length();
        String lowerS = s.toLowerCase();
        
        Set<String> lowerDict = new HashSet<String>();
        for(String str : dict) {
            lowerDict.add(str.toLowerCase());
        }
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = i; j < n;j++){
                String sub = lowerS.substring(i, j + 1);
                if(lowerDict.contains(sub)){
                    dp[i][j] = 1;
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                for(int k = i; k < j; k++){
                    dp[i][j] += (dp[i][k] * dp[k + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
```