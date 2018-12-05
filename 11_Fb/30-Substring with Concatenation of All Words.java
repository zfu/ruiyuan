/*
You are given a string, s, and a list of words, words, that are all of the same length.
Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]
You should return the indices: [0,9].
(order does not matter).
 */

/**
 * 该题考察的是 Sliding Window. 基本解法还是在 Sliding Window Template 的基础上面进行修改。
 * 介于难度原因，相对改动较大，但是主要的思想与编码框架仍然是相同的，详见以下分析。
 * 
 * 因为words字符串数组中的字符有可能重复。因此需要使用HashMap - map保存单词以及单词出现的次数。 1.
 * 将字符串数组中的字符串存放在HashMap中，key为单词，value为单词出现的次数 2.
 * 由于单词的长度都是相同的，所以外循环的次数为单词的长度，内循环的次数为字符串s中以单词长度划分之后的次数.
 * 循环条件中，r指向s中要判断的单词的第一个字符，r每次增加一个单词的长度，r最多只能到s中最后一个单词的第一个字符的位置。 3.
 * 在内循环时,借助了一个HashMap - curr来保存窗口中的已经匹配的单词及其出现的次数， 如果当前的单词在 map
 * 中，则每当给该curr中添加一个单词时，会判断其出现的次数是否超过了hm中该单词的出现次数， 如果没超过，则表示匹配单词个数要增加count++,
 * 如果超过则需要缩小窗口。 同样，由于左窗口 begin 的那个单词并不一定与当前单词相等，因此也需要从left开始一个循环找到那个次数超了的单词，
 * 同时还需要将循环中的单词的次数在 curr 中减少。直到 map 中该单词的次数与 curr 中该单词的次数相等。
 * 之后需要判断curr中所有的单词个数和count是否与hm中所有的单词的个数和是否相等。 如果相等，表示从 begin 到 r
 * 的子串是words中字符串的一个连接，此时需要加入结果集，同时 curr 和 begin 需要改变。 如果在内循环中，突然有个单词不在 map
 * 中，则表示从 begin 到 r 的串都不符合条件， 则 begin = r + 单词长度，同时清空curr和count.
 */
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> rst = new ArrayList<>();
        if (s == null || words.length == 0 || s.length() < words.length * words[0].length()) {
            return rst;
        }

        int slen = s.length();
        // 单词个数
        int N = words.length;
        // 单词长度
        int wl = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        // 利用map记录字典中的各个单词以及对应的出现次数以便查询
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < wl; i++) {
            // 记录当前窗口中已经匹配的单词与对应出现的次数
            Map<String, Integer> currMap = new HashMap<>();
            // 记录 currMap 中已经匹配 map/words 中单词的个数
            int count = 0;
            // 窗口的左边界
            int begin = i;

            for (int r = i; r + wl <= slen; r += wl) {
                // 取一个单词
                String str = s.substring(r, r + wl);
                // 如果字典中包含该单词
                if (map.containsKey(str)) {
                    currMap.put(str, currMap.getOrDefault(str, 0) + 1);

                    if (currMap.get(str) <= map.get(str)) {
                        count++;
                    }
                    // 如果当前窗口中该单词的出现次数大于map中所记录的次数，则需要缩小窗口
                    while (currMap.get(str) > map.get(str)) {
                        String temp = s.substring(begin, begin + wl);
                        currMap.put(temp, currMap.get(temp) - 1);
                        begin += wl;

                        if (currMap.get(temp) < map.get(temp)) {
                            count--;
                        }
                    }
                    if (count == N) {
                        rst.add(begin);
                        String temp = s.substring(begin, begin + wl);
                        currMap.put(temp, currMap.get(temp) - 1);
                        begin += wl;
                        count--;
                    }
                } else {
                    // 如果字典中不包含该单词，则直接跳到下一个单词
                    currMap.clear();
                    count = 0;
                    begin = r + wl;
                }
            }
        }

        return rst;
    }
}

/*
 * Hash map + two pointers i iterates string s, build unvisited = new
 * HashMap<>(map) for each i and try to clear unvisited words, add to result
 * when unvisited is empty
 */

// Method 1: Two map, copy to unvisited map for each possible index i, verify
// and add to result
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s.length() == 0 || words.length == 0)
            return result;
        int len = words[0].length();
        int n = words.length;

        Map<String, Integer> map = new HashMap<>();
        for (String word : words)
            map.put(word, map.containsKey(word) ? map.get(word) + 1 : 1);

        for (int i = 0; i < s.length() - n * len + 1; i++) { // all possible indices
            Map<String, Integer> unvisited = new HashMap<>(map);
            int match = 0; // number of words matched
            for (int j = 0; j < words.length; j++) {
                String word = s.substring(i + j * len, i + (j + 1) * len);
                if (!unvisited.containsKey(word) || unvisited.get(word) < 1) {
                    break;
                }
                unvisited.put(word, unvisited.get(word) - 1); // one more word matched
                match++;
            }
            if (match == n)
                result.add(i);
        }

        return result;
    }
}

// Method 2: Sliding window
class Solution {
    // [## idea ##]
    // Sliding Window

    // ask interviewer if words is empty, should I return empty list
    // words list contains duplicated word?

    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> res = new LinkedList<>();
        // input validation
        if (L.length == 0 || S.length() < L.length * L[0].length())
            return res;

        int N = S.length(), M = L.length, K = L[0].length();
        Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();

        for (String s : L) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        String str = null, tmp = null;
        // traverse all the posibilities
        for (int i = 0; i < K; i++) {
            int count = 0; // remark: reset count
            // l is the start point, r+k is the end point
            for (int l = i, r = i; r + K <= N; r += K) {
                str = S.substring(r, r + K);
                if (map.containsKey(str)) {
                    curMap.put(str, curMap.getOrDefault(str, 0) + 1);

                    if (curMap.get(str) <= map.get(str))
                        count++;
                    // move l
                    while (curMap.get(str) > map.get(str)) {
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        if (curMap.get(tmp) < map.get(tmp))
                            count--;
                    }

                    if (count == M) {
                        res.add(l);
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        count--;
                    }
                } else {
                    curMap.clear();
                    count = 0;
                    l = r + K;
                }
            }
            curMap.clear();
        }
        return res;
    }
}

// https://leetcode.com/problems/substring-with-concatenation-of-all-words/discuss/175458/Beats-80-Sliding-Window-Java-with-Explanations
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {

        if (s == null || s.length() == 0 || words == null || words.length == 0) // Corner cases.
            return new LinkedList<>();
        int wordLen = words[0].length(), n = s.length(), maxWindowSize = words.length * wordLen;
        List<Integer> result = new LinkedList<>(); // Result list.
        Map<String, Integer> wordDict = new HashMap<>(); // key: word in words; value: frequency of a word.
        for (String word : words) {
            wordDict.put(word, wordDict.getOrDefault(word, 0) + 1);
        }
        Map<String, Integer> windowDict; // key: word in the current window; value: frequency of a word

        for (int round = 0; round < wordLen; round++) { // wordLen rounds could cover all possible starting indices if
                                                        // we check wordLen characters by wordLen characters.
            windowDict = new HashMap<>();
            for (int i = round, j = round; j + wordLen <= n; j += wordLen) {
                String word = s.substring(j, j + wordLen);
                if (wordDict.containsKey(word)) {
                    windowDict.put(word, windowDict.getOrDefault(word, 0) + 1);
                }
                if ((j - i + 1) > maxWindowSize) { // Current window is over-sized.
                    word = s.substring(i, i + wordLen);
                    if (wordDict.containsKey(word)) {
                        windowDict.put(word, windowDict.get(word) - 1);
                    }
                    i += wordLen;
                }
                if (wordDict.equals(windowDict))
                    result.add(i);
            }
        }

        return result;
    }
}