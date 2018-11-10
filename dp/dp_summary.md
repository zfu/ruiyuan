topic: dynamic programming  http://sgq626.blogspot.com/2015/10/
DP的题目有很多变种，变化，远远不止这里列出来的题目，应该熟练理解和掌握一些经典的DP算法思想， DP 变化的题目大部分都是通过这些经典的DP算法拓展来的。
DP 能解决的问题：
min/smallest/max/largest problem (longest/shortest problem)
total number of solution: combination, permutation
can/cannot (true / false)
sum 
经典的DP：
kadern’s algorithm: largest subarray sum/smallest subarray sum
Longest Increasing Subsequence: 1d, 2d, 3d
number of Combinations
number of permutations:
word break/jump game: subproblem: (i-end)
DP一般解题思路： 根据题意确定state， 然后看小一号问题n-1是什么，与n size的问题有什么联系，确定function。至于小一号问题怎么看，不同类型的题目小一号问题的取法不同。有直接size -1的小一号问题；有达到n可以由不同的subproblem来确定，直接相关的subproblem可能有多个，也并不一定是n-1,可能是n-k…（combination, permutation，climbing stairs）；还有一些有相互制约关系的问题，subproblem的取法必须体现相互制约的关系。 
DP：解题思路指引， 如果出了dp的题目，在面试的时候，最好在做题目之前把下面的几个点写在开始coding前：
state: dp[i] represent ….
function: dp[i] = f(dp[i-1])
answer:
base case:
filling order:
optimization: 
1d sequence:

 对这种1d sequence，特别是对于array, string这种问题，true or false的判断，通常的解题思路：整个问题是 0-size()-1, 子问题从后往前，看subarray/substr (i, size-1) 的子问题，最后的结果是i==0的时候的结果。或者从前往后看，subarray/substr(0-i) 的子问题，最后结果是i==size-1的时候的结果。
DP1: 
Given a byte array, which is an encoding of characters. Here is the rule:
a. If the first bit of a byte is 0, that byte stands for a one-byte character
b. If the first bit of a byte is 1, that byte and its following byte together stand for a two-byte character
Now implement a function to decide if the last character is a one-byte character or a two-byte character

 state: M[i] : if the last char is a one-byte character or two-byte char for the subarray(i-array.size()-1)
function: if (A[array.size()-1] == 1) two-byte char
          if (A[array.size()-1] == 0) {
            M[i] = M[i+2] if (A[i] == 1)
            M[i] = M[i+1] if (A[i] == 0)
          }
base case: M[array.size()-1] = true , M[array.size()-2] = (array[array.size()-2] == 0)
answer: M[0]
DP2: word break(true/false)
DP3: can jump(true/false)
DP4: palindrome partition(minimum cuts)

 DP5： longest increasing subsequence (1d)
length of longest increasing subsequence. 只涉及个数不涉及其他维度的问题。 
M[i] = max(M[j]) + 1, (A[j] < A[i])
answer: max(M[i])
follow up1: the number of increasing subsequence
M[i] = sum(M[j]),(A[j] < A[i])
answer: sum(M[i])
follow up2: Find the longest increasing sequence in an integer array(path)
很多dp的问题都是longest increasing subsequence的变形，要能从问题中抽象出来。 下面几道常见的题目都是以longest increasing subsequence的思想来的。
DP6: Set of points in 2-d space, how to find the largest subset of points such that, each of the lines conducted by any pair of points in the subset has positive slope     (LIS 2D)
(y1 - y2)/(x1-x2) > 0
sort by x, then find the LIS according to y. 

 DP7:Set of envelopes, each one has (length, width), we can put e1 into e2 iff e2.length > e1.length && e2.width > e1.width. What is the maximum number of envelopes we can put in one stack. (LIS 2D)
sort by length or width, then find the LIS according to width or length. 

 DP8:Set of boxes, each one has (length, width, height), we want to put them as a stack, Find the maximum height of the boxes. when we put one box b2 on another box b1, we need to guarantee that
b2.length < b1.length && b2.width < b1.width. (LIS 3D)
sort by length first then sort by width, and do LIS on height. 
M[i] : the maximum height of boxes 0-i, include i
function: M[i] = height[i] + max(M[j]), j < i && width[j] > width[i]
answer: max(M[i])

 class Box {
public:
 int length;
 int width;
 int height;
};
bool comp (Box b1, Box b2) {
 if (b1.length == b2.length) {
  return b1.width > b2.width;
 } 
 return b1.length > b2.length;
}
int maxHeight(vector<Box> &input) {
 if (input.size() == 0) return 0;
 sort(input.begin(), input.end(), comp);
 int M[input.size()];
 M[0] = input[0].height;
 int gmax = M[0];
 for (int i = 1; i <  input.size(); i++) {
  int tmp = 0;
  for (int j = i-1; j >= 0; j--) {
   if (input[i].width < input[j].width && input[i].length < input[j].length) {
    tmp = max(tmp, M[j]);
   } 
  }
  M[i] = input[i].height + tmp;
  gmax = max(gmax, M[i]);
 }
 return gmax;
}

 DP9: We have a list of records, each record has (start, end, weight). find a subset of the records, such that there is no overlap, and the sum of weight is maximized. (LIS 3D)
sort by end time, then do LIS on weight with no overlap records.
M[i] : maximum height from 0-i, including i 
class Interval {
 int start;
 int end;
 int weight;
 Interval(int s, int e, int w) {
  start = s;
  end = e;
  weight = w;
 }
};

 bool comp(Interval left, Interval right) {
 return left.end < right.end; 
}
int MaxWeight(vector<Interval> &input) {
 if (input.size() == 0) return 0;
 sort(input.begin(), input.end(), comp);
 //find LIS on weight with no overlap
 int M[input.size()];
 M[0] = input[0].weight;
 int gmax = M[0];
 for (int i = 1; i < input.size(); i++) {
  int tmp = 0;
  for (int j = i-1; j >= 0; j--) {
   if (input[j].end < input[i].start) {
    tmp = max(tmp, M[j]);
   }
  }
  M[i] = tmp + input[i].weight;
  gmax = max(gmax, M[i]);
 }
 return gmax;
}
可以发现DP8,9虽然题目不一样，但是解题过程完全相同。
DP10: We have a permutation of number 1 - n, we can delete one number and insert it to any other position, if we want to do several such operations to make the permutation as sorted, what is the minimal number of operations we will need? (LIS 1D )
find the LIS and the rst is the total size of the array - LIS
the maximum chars that don’ t need to do the operation is the LIS of the permutation array. 
int LIS(vector<int> &input) {
 if (input.size() == 0) return 0;
 int M[input.size()];
 M[0] = 1;
 int gmax = M[0];
 for (int i = 1; i < input.size(); i++) {
  int tmp = 0;
  for (int j = i-1; j >= 0; j--) {
   if (input[j] < input[i]) {
    tmp = max(tmp, M[j]);
   }
  }
  M[i] = tmp + 1;
  gmax = max(gmax, M[i]);
 }
 return gmax;
}

 int minOperation(vector<int> &input) {
 if (input.size() == 0) return 0;
 int num = LIS(input);
 return input.size() - num; 
}

 DP:  Largest subarray sum:
如果看到题目里面有subarray的问题，可以考虑能不能转化成LSS的解法来解。
int LSS(vector<int> &input) {
 if (input.size() == 0) {
  return 0;
 }
 int local = input[0];
 int global = input[0];
 for (int i = 1; i < input.size(); i++) {
  local = max(local, local+input[i]);
  global = max(global, local);
 }
 return global;
}
DP follow up: largest submatrix sum

 DP2: largest subarray product:
int LSP(vector<int> &input) {
 if (input.size() == 0) {
  return 0;
 }
 int local_max = input[0];
 int local_min = input[0];
 int global_max = input[0];
 for (int i = 1; i < input.size(); i++) {
  int tmp = local_max;
  local_max = max(input[i],max(local_min * input[i], local_max * input[i]));
  local_min = min(input[i],min(local_min * input[i], tmp * input[i]));
  global_max = max(global_max, local_max);
 }
 return global_max;
}
follow up: largest submatrix product
DP3:  0,1 Bit  array
largest subarray sum, smallest subarray sum, longest subarray sum to target 
3.   longest subarray sum to target 
(sum[i] - sum[j] == target)  -> the length of the subarray is i - j
using unordered_map<int, int> key: presum, value: index(to compute length)
get all the presum 
store the presum into hashtable if presum - target is not in the hashtable, otherwise get the length. 
int LongestSumTarget(vector<int> &input) {
 if (input.size() == 0) return 0;
 int presum = 0;
 int gmax = 0;
 unordered_map<int, int> imap;
 for (int i = 0; i < input.size(); i++) {
 presum += input[i];
 //case1: 0-i == target
 if (presum == target) {
 gmax = max(gmax, i);
}
 //case 2: the array is from j-i
 if (imap.find(presum[i] - target) == imap.end()) {
 imap[presum[i]] = i;
} else {
 gmax = max(gmax, i - imap[presum[i]-target]);
}

 }
return gmax;
}
time complexity: O(n), space complexity: O(n)

 DP3: Longest increasing subarray
state: M[i] : longest increasing subarray from 0-i, including i
function: M[i] = M[i-1]+1 (if input[i] > input[i-1]) otherwise, = 1
base case: M[0] = 1;
result: gmax(M[i])
optimization: int prev;
test case: n = 1; 
int LongestIncreasingSubarray(vector<int> &input) {
 if (input.size() == 0) return 0;
 int prev = 1;
 int gmax = 1;
 for (int i = 1; i < input.size(); i++) {
 if (input[i] - input[i-1] > 0) {
 prev += 1;
} else {
 prev = 1;
}
gmax = max(gmax, prev);
}
return gmax;
}
follow up 1: longest increasing subpath in binary tree
recursion
什么时候选择用 up-bottom ？ 什么时候选择用 bottom-up?

 up-bottom : 从上到下的path， 或者问题中涉及parent 节点的关系。
bottom-up: 适合除上面up-bottom的特殊问题的所有问题。

 up-bottom ？？？
void preorder(TreeNode* &root, int subpath, int gmax) {
 if (root == NULL) return;
 gmax = max(gmax, subpath);
 if (!root->left && root->left->val > root->val) {
  return preorder(root->left, subpath+1, gmax);
} else {
 return preorder(root->left, 1, gmax);
}
if (!root->right && root->right->val > root->val) {
 return preorder(root->right, subpath+1, gmax);
} else {
 return preorder(root->right, 1, gmax);
}
}
int subpath(TreeNode* &root) {
 if (root == NULL) return 0;
 int gmax = 1;
 preorder(root, 1, gmax);
 return gmax;
}
整个感觉bottom up的方法写起来更简单，
bottom up?
//return  the longest increasing subpath of root, including root
node: left, right, 1
int helper(TreeNode* &root, int &gmax) {
 if (root == NULL) return 0;
 int left = helper(root->left, gmax);
 int right = helper(root->right, gmax);
 int tmp = 1; //local max
 if (!root->left  && root->left->val > root->val) {
 left += 1;
 tmp = max(tmp, left);
}
if (!root->right && root->right->val > root->val) {
 right += 1；
 tmp = max(tmp, right);
}
gmax = max(gmax, tmp);
return tmp;//if root->left and root->right is both less than root, then return 1
}
time complexity:O(n) 
follow-up 2: longest increasing path in 2d matrix
dfs 走遍所有以任何一个在matrix里面节点为起始点的路径，找这些路径中的longest increasing path.
 
dp2: shortest list of integers, sum of square to the target.
For example, 14 = 1^2 + 2^2 + 3^2, (1, 2, 3) is the smallest.
一开始想这个问题的角度是combination， 从[0-sqrt(n)]中，取出最小的combination，但是这很明显不是combination, combination思想解决的问题是从N中取出K个的取法，是解决total问题，但是这里是解决最小值问题。
如何去想一个最小最大值类型的问题呢？ 
dp: 将问题从大化小地思考，但是写的时候是从小到大的去递推，如何将这个问题从大化小？14 -》小？ 如果14问题太大，如果去掉一个因子 3， 问题就变小了，14-9 = 5，那么dp[14] = dp[5] + 1(3)；同样可以去掉1，2，14的list of intergers可以通过3种可能得到，那么如何得到最短的？直接取这3种可能的最小值就可以了。这样就可以得到递推公式。
last element picked: 
14 -》dp[14-1]  + 1
14 -》dp[14-4]  + 1
14 -> dp[14-9]  + 1
dp[i] : shortest list of intergers sum of square to i
dp[i] = min(dp[i - k^2]) + 1 (k: 1- sqrt(x))
base case: 1 -> 1
result: dp[target]
int shortestList(int target) {
 int M[target+1]; //target 必须包含在内0-target
 
 

 }
dp3: paint house: 
存在限制关系：必须在dp的时候要确定最后那个house paint的颜色，才能处理限制条件。
state:  dp[i][k] :  represent the lowest cost of ith house and it paints with color k; 
function: dp[i][k] =min( dp[i-1][m](m!=k)) +cost[i][k]
base  case:
dp4:  Given a String s, we can insert characters at any places, what is the least number of insertions we can do to make it a palindrome?
“abca” → “abcba”   insert 1
“abcdc” → “abcdcba” insert 2
palindrome: 字符串首尾相同。按照首尾字符是否相同来进行求解。
state: dp[i][j] : least number of insertions to make substring i-j to be palindrome;
function: if (s[i] == s[j]) dp[i][j] = dp[i+1][j-1]
               if (s[i] != s[j]) dp[i][j] = min(dp[i+1][j], dp[i][j-1]) + 1
filling direction: from bottom up and from left to right
answer: dp[0][n-1]
dp5: decode ways
A → ‘1’
B → ‘2’
..
Z → ‘26’

 “1112” → AAAB
            → KL
            → AKB
            → AAL
            → KAB
state: dp[i] : number of ways to decode from 0-i,including i
function: dp[i] =  dp[i-1] (if input[i] != 0)
dp[i-2] (if input[i-1] != 0 && num(input[i-1][i]) < 26  && num(input[i-1][i]) > 0)
answer: dp[size-1]
base case: dp[0] = 1
filling order: 
optimization: two variables
string DP:  2d sequence
2 strings: M[I][J] means substring s1(0-i), s2(0-j)
state transition function: 具体问题具体分析
DP1: Interleaving String
state: opt[i][j] : if the substring of s3 [0- (i+j)] is formed by interleaving of s1 [0-i] and s2 [0-j]
function: opt[i][j] = true if (opt[i][j-1] && s3[i+j] == s2[j]) || opt[i-1][j](if s3[i+j] == s1[i])
                      false (otherwise)
base case: i == 0 : s3 (i+j ) == s (j) 
     j == 0 : s3[i+j] == s1[i]
answer:  opt[s1.size()][s2.size()]
order of filling the solution: 00 -> s1.size() s2.size() -> from left to right, from right to left; 
optimization : space --> only need the previous row and previous column, may be just need two array to store the result.

 bool isInterleaved(string s1, string s2, string s3) {
 if (s1.size() + s2.size() != s3.size()) {
  return false;
 }
 bool M[s1.size()+1][s2.size()+1];
 //base case
 M[0][0] = true;
 for (int i = 1; i <= s1.size(); i++) {
  M[i][0] =  (s1.substr(0,i) == s3.substr(0,i)) ? true : false;
 }
 for (int i = 1; i <= s2.size(); i++) {
  M[0][i] = (s2.substr(0,i) == s3.substr(0,i)) ? true : false;
 }
 //function
 for (int i = 1; i <= s1.size(); i++) {
  for (int j = 1; j <= s2.size(); j++) {
   if (s3[i+j-1] == s2[j-1] && M[i][j-1] || (s3[i+j-1] == s1[i-1] && M[i-1][j]) {
    M[i][j] = true;
   } else {
    M[i][j] = false;
   }
  }
 }
 return M[s1.size()][s2.size()];
}
DP2: edit distance
state: M[i][j] : minimum distance from s1(0,i) to s2(0,j)

 function: M[i][j] = M[i-1][j-1] if (s1[i] == s2[j])
                    min(M[i][j-1], M[i-1][j-1], M[i-1][j]) + 1, otherwise

 base case： i = 0: M[0][j] = j;
   j = 0: M[i][0] = i;
answer: M[s1.size()-1][s2.size()-1]
filling direction： 00-mn : left to right, up to bottom
optimization: 


int EditDistance(string &s1, string &s2) {
 if (s1.size() == 0 && s2.size() == 0) return 0;
 int M[s1.size()+1][s2.size()+1];
 for (int j = 0; j <= s2.size(); j++) {
  M[0][j] = j;
 }
 for (int i = 1; i <= s1.size(); i++) {
  M[i][0] = i;
  for (int j = 1; j <= s2.size(); j++) {
   if (s1[i-1] == s2[j-1]) {
    M[i][j] = M[i-1][j-1];
   } else {
    M[i][j] = min(min(M[i][j-1], M[i-1][j-1]), M[i-1][j]) + 1;
   }
  }
 }
 return M[s1.size()][s2.size()];
}

 DP3: distinct sebsequence
匹配function不容易想到。
state: M[i][j] : the number of distinct subse of T(0,i) in S(0,j);
function: M[i][j] = M[i-1][j-1] + M[i][j-1] if (T[i] == S[j])
                    M[i][j-1] otherwise
base case : M[0][0] = 1
answer: M[t.size()][s.size()]
filling direction: left to right, up to bottom
optimization: 

 int numDistinct(string s, string t) {
 if (s.size() < t.size()) return 0;
 int M[t.size()+1][s.size()+1];
 M[0][0] = 1;
 for (int j = 1; j <= s.size(); j++) {
  M[0][j] = 1;
 }
 for (int i = 1; i <= t.size(); i++) {
  for (int j = 1; j <= s.size(); j++) {
   M[i][0] = 0;
   M[i][j] = M[i-1][j-1];
   if (t[i-1] == s[j-1]) {
    M[i][j] += M[i][j-1];
   } 
  }
 }
 return M[t.size()][s.size()];
}

 DP4：
Matrix: 
DP1:  largest submatrix sum (2D keden algorithm)
matrix确定4个点就可以确定一个submatrix，我们可以固定两个条件，另外两个条件待求，我们可以选择固定left, right, 这样Left - right之间的subsum就固定了，只需要再找到up, bottom的row就行， up, bottom的row通过1D keden algorithm来找。time complexity: O(n^3), space complexity:O(n)
int maxSubsum(int* helper,) {
 int local_max = helper[0];
 int gmax = helper[0];
 for (int i =1 ; i  <= row; i++) {
 local_max = max(local_max+ helper[i], helper[i]);
 gmax = max(local_max, gmax);
}
return gmax;
}
 gmax = INT_MIN;
for (int left = 0; left < colums; left++) {
int helper[rows] = {0};//store the sum between left column and right column 
  for (int right = left; right < columns; right++) {
   for (int i = 0; i < rows; i++) {
  helper[i] += matrix[i][right];
}
int lmax = maxSubsum(helper, row);
gmax = max(lmax, gmax);
}
}
return gmax;

 DP2:  largest square with 1
DP3: largest rectangle with 1
DP4:  submatrix sum(lintcode)

 中心开花 Interval: 1d array, but need 2d array space, M[i][j] : subproblem (i -j) merge stones, cutting ropes
Combination ： 
combination是最常见的DP思想的题目，通常的thought是combination(i,j) = combination(i-1, j-1) + combination(i,j-1) : 从j 里选i，选j 和不选j, 这两种方法的sum就是最后的结果。
combination DP思想的应用: 
有的dp题目看起来很奇怪，感觉找不到思路，其实里面是有combination思想的，
比如上面的DP3, subsequence 的个数，其实就是一个combination的问题，M[i][j], t(0-i), s(0-j), 可以转化成从s j 里面选i，使得这i个字母组成的string  == t i. 与combination唯一的不同点在于：classic 的combination 的每一个元素都是对等的，但是这个里面有字符匹配的关系，匹配成功才对等，否则那两个不能匹配， 所以需要分情况讨论： 
 if (s[j] == t[i]) 说明最后那个字母可以匹配，M[i][j] = M[i-1][j-1](匹配最后一个字符) + M[i][j-1](不匹配最后那个字符); if (s[i] != t[j]) 只能不去匹配最后那个字符。   
比如： 经典的coins问题：
{1，2，5，10，25} cents, 去匹配target，coins可以用多次，找到组合的数目。
这个题目在dfs里面做过找出所有result的题，但是这里问combination的数目，所以我们不需要用dfs去找， 在dfs过程中可以cache一些值，避免重复的计算，所以用dp就可以了。 
DP[i][k] : how many ways to get k using cents with largest cent[ i].
从 cent[0-i],里面选出一些cents使得sum = k, 也是combination的变形，涉及到combination多次选择的问题， 利用combination的思想，我们可以至少选一次最后那个cents, 也可以不选最后那个cents,
 dp[i][k] = dp[i][k-cents[i]](至少选一次最后那个cents) + dp[i-1][k](不选最后那个cents)
int combinationCoins(vector<int> &coins, int target) {
 if (target == 0) return 1;
 int M[coins.size()][target];
 for (int i = 0; i < coins.size(); i++) {
  M[i][0] = 1;
 }
 for (int j = 1; j < target; j++) {
  M[0][j] = 0;
 }
 for (int i = 1; i < coins.size(); i++) {
  for (int j = 1; j <= target; j++) {
   if (i > 0) {
    M[i][j] = M[i-1][j];
   } 
   if (j >= coins[i]) {
    M[i][j] = M[i][j-coins[i]];
   }
  }
 }
 return M[coins.size()-1][target];
}
optimization:2d space to 1d space: 1个 1d array 表示当前row，、
Permutation: 
另一种在dp里面比较常见的是permutation: permutation的思想跟combination的思想是不一样的， permutation在于做事情的顺序不同结果不同，permutation 在dp里面常见的是sum， 常见的题型：
climbing stairs： 跳一步，跳两步，....跳的顺序不同，结果是不同的，dp[i] = dp[i-1] + dp[i-2]， 跳一步过来 + 跳两步过来，并且跳的顺序有影响。
robot path: 从上面下来，从左边过来，上一步过来的路径不同，结果不同，dp[i][j] = dp[i-1][j] + dp[i][j-1]
小偷问题： 
相邻的不同偷，其实跟climbing stair思想差不多。 
上面string 里面的 DP1,DP2其实都是属于Permutation问题。
permutation的思路是做这件事情有哪些途径可以做到，最后结果就是这些路径之和。




怎么从recursion 转化到 dp？  