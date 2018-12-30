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