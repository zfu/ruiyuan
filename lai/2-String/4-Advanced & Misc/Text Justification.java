/**
 * 
 Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

Input: words: ["This", "is", "an", "example", "of", "text", "justification."]    L: 16.

Output:[

          "This    is    an",

            "example  of text",

            "justification.  "

           ]

    

Input: words: [“This”, “is”, “my”]       L = 5

    Output: [

         “This ”,

         “is my”    

        ]    
 */

 //首先要做的就是确定每一行能放下的单词数，这个不难，就是比较n个单词的长度和加上n - 1个空格的长度跟给定的长度L来比较即可
//找到了一行能放下的单词个数，然后计算出这一行存在的空格的个数，是用给定的长度L减去这一行所有单词的长度和。
//得到了空格的个数之后，就要在每个单词后面插入这些空格，这里有两种情况，比如某一行有两个单词"to" 和 "a"，给定长度L为6
//如果这行不是最后一行，那么应该输出"to   a"，如果是最后一行，则应该输出 "to a  "，所以这里需要分情况讨论，最后一行的处理方法和其他行之间略有不同。
//最后一个难点就是，如果一行有三个单词，这时候中间有两个空，如果空格数不是2的倍数，那么左边的空间里要比右边的空间里多加入一个空格，那么我们只需要用总的空格数除以空间个数
//能除尽最好，说明能平均分配，除不尽的话就多加个空格放在左边的空间里"
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<String>();
        int index = 0;
        while (index < words.length) {
            //count：该行所有单词累计总长度
            int count = words[index].length();
            //last:该行最后一个词的index
            int last = index + 1;
            while (last < words.length) {
                //out of bound
                if (words[last].length() + count + 1 > maxWidth) break;
                //plus one for the space, if its a perfect fit it will fit
                count += 1 + words[last].length();
                last++;
            }
            StringBuilder builder = new StringBuilder();
            //append该行第一个单词
            builder.append(words[index]);
            //这一行除去第一个已经append的单词，共剩下几个词语：diff 个：从index到last-1
            int diff = last - index - 1;
           // if last line or number of words in the line is 1, left-justified
            //最后一行：每个单词中间一个空格， 剩余补上空白
            if (last == words.length || diff == 0) {
                for (int i = index+1; i < last; i++) {
                    builder.append(" ");
                    builder.append(words[i]);
                }
                for (int i = builder.length(); i < maxWidth; i++) {
                    builder.append(" ");
                }
            } else {
                //不是最后一行：middle justified
                //这一行总space的个数：（长度-累计单词总长度）
                //每个单词后面space的个数：（长度-累计单词总长度）/单词个数
                // r为需要平均分配到中间的空格总数
                int spaces = (maxWidth - count) / diff;
                int r = (maxWidth - count) % diff;
                for (int i = index+1; i < last; i++) {
                    for(int k=spaces; k > 0; k--) {
                        builder.append(" ");
                    }
                    if(r > 0) {
                        builder.append(" ");
                        r--;
                    }
                    builder.append(" ");
                    builder.append(words[i]);
                }
            }
            lines.add(builder.toString());
            index = last;
        }
        return lines;
    }
}

public class Solution {
    public ArrayList<String> fullJustify(String[] words, int L) {
      //Input your code here
      if (words == null || words.length == 0) {
        return new ArrayList<String>();
      }
      int wordsCount = words.length;
          ArrayList<String> result = new ArrayList<String>();
          int curLen = 0;
          int lastI = 0;
          for (int i = 0; i <= wordsCount; i++) {
              if (i == wordsCount || curLen + words[i].length() + i - lastI > L) {
                  StringBuffer buf = new StringBuffer();
                  int spaceCount = L - curLen;
                  int spaceSlots = i - lastI - 1;
                  if (spaceSlots == 0 || i == wordsCount) {
                      for(int j = lastI; j < i; j++){
                          buf.append(words[j]);
                          if(j != i - 1)
                              appendSpace(buf, 1);
                      }
                      appendSpace(buf, L - buf.length());
                  } else {
                      int spaceEach = spaceCount / spaceSlots;
                      int spaceExtra = spaceCount % spaceSlots;
                      for (int j = lastI; j < i; j++) {
                          buf.append(words[j]);
                          if (j != i - 1)
                              appendSpace(buf, spaceEach + (j - lastI < spaceExtra ? 1 : 0));
                      }
                  }
                  result.add(buf.toString());
                  lastI = i;
                  curLen = 0;
              }
              if (i < wordsCount)
                  curLen += words[i].length();
          }
          return result;
      }
  
      private void appendSpace(StringBuffer sb, int count) {
          for (int i = 0; i < count; i++)
              sb.append(' ');
      }
  }


  // Binary Search

  class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int[] values = new int[words.length];
        values[0]=words[0].length();
        for(int i=1;i<words.length;i++) values[i]=values[i-1]+words[i].length()+1;
        List<String> jList = new ArrayList<String>();
        int low=0,high=words.length,mid=0;
        int lag=0;
        while(low<words.length){
            int target=maxWidth+lag;
            int index=binarySearch(values,low,target);
            if(index<low) break;
            int remain=target-values[index];
            String str=words[low];
            //last string
            if(index==words.length-1){
                for(int j=low+1;j<=index;j++) str+=" "+words[j];
                for(int k=0;k<remain;k++) str+=" ";
            }
            else{
                if(index>low){
                    int[] spaces=new int[index-low];
                    int add=remain%(index-low);
                    Arrays.fill(spaces,(remain/(index-low))+1);
                    int i=0;
                    while(add-->0) spaces[i++]++;
                    for(int j=low+1;j<=index;j++){
                        for(int k=0;k<spaces[j-low-1];k++) str+=" ";
                        str+=words[j];
                    }                 
                }
                //single word - just add blank spaces at the end
                else{
                    for(int k=0;k<remain;k++) str+=" ";
                }
            }
            jList.add(str);
            lag=values[index]+1;
            low=index+1;
        }
        return jList;
    }
    private int binarySearch(int[] values,int start,int target){
        int low=start,high=values.length-1,mid=0;
        while(low<=high){
            mid=low+(high-low)/2;
            if(values[mid]>target) high=mid-1;
            else low=mid+1;
        }
        return high;
    }
}