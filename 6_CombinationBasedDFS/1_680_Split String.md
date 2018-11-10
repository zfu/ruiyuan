# 680. Split String


#### 680. Split String

Give a string, you can choose to split the string after one character or two adjacent characters, and make the string to be composed of only one character or two characters. Output all possible results.

#### Example

Given the string `"123"`  
return `[["1","2","3"],["12","3"],["1","23"]]`

### DFS\(组合类\):

* 考虑这个题目, 要么取一个, 要么取两个
* 如果取一个的话, 那么下一层循环就是本层的字符串-1
* 如果取两个的话, 那么下一层循环就是本层的字符串-2
* 当字符串变成空的时候, 就把结果写到最后的全部结果里面

```java
public class Solution {
    public List<List<String>> splitString(String s) {
        List<List<String>> result = new ArrayList<>();
        dfs(s, new ArrayList<>(), result);
        return result;
    }
    
    private void dfs(String s, List<String> buffer, List<List<String>> result) {
        if (s.length() == 0) {
            result.add(new ArrayList<>(buffer));
        }
        
        for (int i = 1; i <= 2 && i <= s.length(); i++) {
            String next = s.substring(0, i);
            buffer.add(next);
            dfs(s.substring(i), buffer, result);
            buffer.remove(buffer.size() - 1);
        }
    }
}
```

