import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    int[] arr1 = new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1};
    int[] arr2 = new int[]{4, 3, 5, 1, 2};
    System.out.println(longestSubarray(arr1));

    System.out.println(isIsomorphic("foo", "bar"));

  }


  public static int maxProfit(int[] prices) {
    int[] mem = new int[prices.length];
    Arrays.fill(mem, -1);
    return maxProfitRec(prices, 0, prices.length, mem);
  }

  public static int maxProfitRec(int[] prices, int curr, int n, int[] mem) {
    if (curr >= n) {
      return 0;
    }

    if (mem[curr] != -1) {
      return mem[curr];
    }
    int maxVal = 0;
    for (int i = curr + 1; i < n; i++) {
      if (prices[i] > prices[curr]) {
        int sellProfit = prices[i] - prices[curr] + maxProfitRec(prices, i + 2, n, mem);
        maxVal = Math.max(maxVal, sellProfit);
      }
    }

    maxVal = Math.max(maxVal, maxProfitRec(prices, curr + 1, n, mem));
    mem[curr] = maxVal;
    return mem[curr];

  }

  public int removeAlmostEqualCharacters(String word) {
    int count = 0;
    for (int i = 0; i < word.length() - 1; ) {
      char c = word.charAt(i);
      char d = word.charAt(i + 1);
      if (c == d || (c + 1) == d || (c - 1) == d) {
        count++;
        i += 2;
      } else {
        i++;
      }
    }
    return count;
  }


  public static boolean isIsomorphic(String s, String t) {

    Map<Character, Character> map = new HashMap<>();
    Map<Character, Character> revMap = new HashMap<>();
    if (s.length() != t.length()) {
      return false;
    }

    for (int i = 0; i < s.length(); i++) {
      char a = s.charAt(i);
      char b = t.charAt(i);

      if (!map.containsKey(a)) {
        if (revMap.containsKey(b)) {
          if (revMap.get(b) != a) {
            return false;
          }
        }
        map.put(a, b);
        revMap.put(b, a);
      } else {
        if (map.get(a) != b) {
          return false;
        }
      }

    }

    return true;
  }


  /**
   * To find the lexicographically smallest string after applying operations, we can use a
   * breadth-first search (BFS) approach to explore all possible transformations of the given
   * string. We'll perform two types of operations: rotation and addition. For each operation, we'll
   * generate a new string and add it to the queue for further exploration. We'll use a HashSet to
   * keep track of visited strings to avoid exploring the same string multiple times.
   */
  public String findLexSmallestString(String s, int a, int b) {
    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    String minString = s;

    queue.add(s);
    visited.add(s);

    while (!queue.isEmpty()) {
      String current = queue.poll();
      minString = min(minString, current);

      // Apply rotation operation
      String rotated = rotate(current, b);
      if (!visited.contains(rotated)) {
        queue.add(rotated);
        visited.add(rotated);
      }

      // Apply addition operation
      String added = add(current, a);
      if (!visited.contains(added)) {
        queue.add(added);
        visited.add(added);
      }
    }

    return minString;
  }

  private String rotate(String s, int b) {
    int n = s.length();
    b %= n;
    return s.substring(n - b) + s.substring(0, n - b);
  }

  public static int numberOfSubstrings(String s) {
    // Store count of 'a', 'b', and 'c' in the window
    int[] count = new int[3];
    int left = 0, right = 0, totalCount = 0;

    while (right < s.length()) {
      char ch = s.charAt(right);
      // Increment count of the current character
      count[ch - 'a']++;

      while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
        // Increment totalCount by the length of the remaining substring
        totalCount += s.length() - right;
        char leftChar = s.charAt(left);
        // Shrink the window from the left
        count[leftChar - 'a']--;
        left++;
      }

      right++;
    }

    return totalCount;
  }

  private String add(String s, int a) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (i % 2 == 1) {
        int digit = (ch - '0' + a) % 10;
        ch = (char) (digit + '0');
      }
      sb.append(ch);
    }
    return sb.toString();
  }

  private String min(String s1, String s2) {
    return s1.compareTo(s2) < 0 ? s1 : s2;
  }

  public static String evaluate(String s, List<List<String>> knowledge) {
    Map<String, String> knowledgeMap = new HashMap<>();
    for (List<String> pair : knowledge) {
      knowledgeMap.put(pair.get(0), pair.get(1));
    }

    StringBuilder result = new StringBuilder();
    StringBuilder keyBuilder = new StringBuilder();
    boolean inBracket = false;

    for (char ch : s.toCharArray()) {
      if (ch == '(') {
        inBracket = true;
        keyBuilder.setLength(0); // Reset the keyBuilder
      } else if (ch == ')') {
        inBracket = false;
        String key = keyBuilder.toString();
        result.append(knowledgeMap.getOrDefault(key, "?"));
      } else {
        if (inBracket) {
          keyBuilder.append(ch);
        } else {
          result.append(ch);
        }
      }
    }

    return result.toString();
  }

  public static String reverseParentheses(String s) {
    Stack<Integer> stack = new Stack<>();
    char[] chars = s.toCharArray();

    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == '(') {
        stack.push(i);
      } else if (chars[i] == ')') {
        reverseSubstring(chars, stack.pop() + 1, i - 1);
      }
    }

    StringBuilder sb = new StringBuilder();
    for (char c : chars) {
      if (c != '(' && c != ')') {
        sb.append(c);
      }
    }

    return sb.toString();
  }

  private static void reverseSubstring(char[] chars, int start, int end) {
    while (start < end) {
      char temp = chars[start];
      chars[start] = chars[end];
      chars[end] = temp;
      start++;
      end--;
    }
  }

  public static String getSmallestString(int n, int k) {
    StringBuilder sb = new StringBuilder();
    // Fill the string from left to right
    for (int i = 0; i < n; i++) {
      // Remaining value to distribute among remaining characters
      int remaining = k - (n - i - 1);
      if (remaining >= 26) {
        sb.append('z');
        k -= 26;
      } else {
        sb.append((char) (remaining + 'a' - 1));
        k -= remaining;
      }
    }
    return sb.reverse().toString();
  }

  public static int kthFactor(int n, int k) {
    int curr = 0;
    for (int i = 1; i <= n; i++) {
      if (n % i == 0) {
        curr++;
      }
      if (curr == k) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Till zeroCount is at max one we can take the window maxLength = Math.max(maxLength, right -
   * left);
   */
  public static int longestSubarray(int[] nums) {
    int maxLength = 0;
    int left = 0, right = 0;
    int zeroCount = 0;

    while (right < nums.length) {
      if (nums[right] == 0) {
        zeroCount++;
      }

      while (zeroCount > 1) {
        if (nums[left] == 0) {
          zeroCount--;
        }
        left++;
      }

      maxLength = Math.max(maxLength, right - left);
      right++;
    }

    return maxLength;
  }

  public static int maxConsecutiveAnswers(String answerKey, int k) {
    int max1 = findMaxConsecutiveLength(answerKey, 'T', k);
    int max2 = findMaxConsecutiveLength(answerKey, 'F', k);

    return Math.max(max1, max2);
  }

  public static int findMaxConsecutiveLength(String str, char ch, int k) {
    int right = -1;
    int left = -1;
    int n = str.length();

    int max = 0;
    int cnt = 0;

    while (true) {
      boolean f1 = true;
      boolean f2 = true;

      while (right < n - 1
          && cnt <= k) {
        max = Math.max(max, right - left);

        if (str.charAt(++right) == ch) {
          cnt++;
        }

        f1 = false;
      }

      //  release of elements in window untill count<=k
      while (cnt > k) {
        left++;

        if (str.charAt(left) == ch) {
          cnt--;
        }

        f2 = false;
      }

      if (f1 && f2) {
        break;
      }
    }

    max = Math.max(max, right - left);

    return max;
  }

  private static int longestSubarrayRecursive(int[] nums, int left, int right, boolean canRemove) {
    int maxLen = 0;
    int curLen = 0;
    for (int i = left; i <= right; i++) {
      if (nums[i] == 1) {
        curLen++;
      } else if (canRemove) {
        // Try removing this zero
        int leftLen = longestSubarrayRecursive(nums, left, i - 1, false);
        int rightLen = longestSubarrayRecursive(nums, i + 1, right, false);
        maxLen = Math.max(maxLen, leftLen + rightLen);
        curLen = 0;  // Reset current length for the next continuous sequence of 1's
        canRemove = false; // Once a zero is removed, no further removal is allowed in this branch
      } else {
        // Cannot remove more elements, update max length and reset current length
        maxLen = Math.max(maxLen, curLen);
        curLen = 0;
      }
    }
    // Update max length in case the sequence of 1's continues until the end
    maxLen = Math.max(maxLen, curLen);
    return maxLen;
  }

  public static String minRemoveToMakeValid(String s) {
    Stack<Integer> stack = new Stack<>();
    StringBuilder sb = new StringBuilder(s);

    for (int i = 0; i < sb.length(); i++) {
      char ch = sb.charAt(i);
      if (ch == '(') {
        stack.push(i);
      } else if (ch == ')') {
        if (!stack.isEmpty()) {
          stack.pop();
        } else {
          sb.setCharAt(i, '*');
        }
      }
    }

    // Mark invalid opening parentheses
    while (!stack.isEmpty()) {
      sb.setCharAt(stack.pop(), '*');
    }

    // Remove marked characters
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < sb.length(); i++) {
      if (sb.charAt(i) != '*') {
        result.append(sb.charAt(i));
      }
    }

    return result.toString();
  }

  public static int countSubIslands(int[][] grid1, int[][] grid2) {
    return 0;
  }

  public static int bestClosingTime(String customers) {
    int[] prefix = new int[customers.length() + 1];
    for (int i = 0; i < customers.length(); i++) {
      char c = customers.charAt(i);
      prefix[0] += c == 'Y' ? 1 : 0;
    }
    int minPenalty = prefix[0];
    int minIdx = 0;
    for (int i = 1; i < prefix.length; i++) {
      char c = customers.charAt(i - 1);
      int decrease = c == 'Y' ? 1 : -1;

      prefix[i] = prefix[i - 1] - decrease;
      if (minPenalty > prefix[i]) {
        minIdx = i;
        minPenalty = prefix[i];
      }
    }
    return minIdx;
  }

  /**
   * The zeroCount parameter in the dfsDistributeCookies method is used to keep track of the number
   * of "empty" slots in the distribute array. These "empty" slots represent the number of children
   * that have not been assigned any cookies yet.
   */
  public static int distributeCookies(int[] cookies, int k) {
    int[] distribute = new int[k];

    return dfsDistributeCookies(0, distribute, cookies, k, k);
  }

  private static int dfsDistributeCookies(int idx, int[] distribute, int[] cookies, int k,
      int zeroCount) {
    if (cookies.length - idx < zeroCount) {
      return Integer.MAX_VALUE;
    }

    // check the values of distribute array those should be minimum
    if (idx == cookies.length) {
      int unfairness = Integer.MIN_VALUE;
      for (int value : distribute) {
        unfairness = Math.max(unfairness, value);
      }
      return unfairness;
    }

    int minUnfairness = Integer.MAX_VALUE;
    for (int j = 0; j < k; j++) {
      if (distribute[j] == 0) {
        zeroCount -= 1;
      }
      distribute[j] += cookies[idx];

      minUnfairness = Math.min(minUnfairness,
          dfsDistributeCookies(idx + 1, distribute, cookies, k, zeroCount));

      // backtrack
      distribute[j] -= cookies[idx];

      if (distribute[j] == 0) {
        zeroCount += 1;
      }
    }

    return minUnfairness;
  }

  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> ans = new ArrayList<>();
    combinationSum3Backtrack(1, k, n, new ArrayList<>(), ans);
    return ans;
  }

  public void combinationSum3Backtrack(int start, int k, int n, List<Integer> path,
      List<List<Integer>> ans) {
    if (k == 0 && n == 0) {
      ans.add(new ArrayList<>(path));
      return;
    }
    if (k == 0 || n == 0) {
      return;
    }
    for (int i = start; i <= 9; i++) {
      if (i > n) {  // Pruning unnecessary branches
        break;
      }
      path.add(i);
      combinationSum3Backtrack(i + 1, k - 1, n - i, path, ans);
      path.remove(path.size() - 1);
    }
  }

  // keep pushing until pop number is accompanied
  public static boolean validateStackSequences(int[] pushed, int[] popped) {
    Stack<Integer> stack = new Stack<>();

    int idxPopped = 0;
    int idxPushed = 0;

    while (idxPushed < pushed.length) {
      stack.push(pushed[idxPushed]);
      while (!stack.isEmpty() && stack.peek() == popped[idxPopped]) {
        stack.pop();
        idxPopped++;
      }
      idxPushed++;
    }

    return idxPopped == popped.length && idxPushed == pushed.length;

  }

  public static boolean checkIfCanBreak(String s1, String s2) {
    char[] c1 = s1.toCharArray();
    char[] c2 = s2.toCharArray();

    Arrays.sort(c1);
    Arrays.sort(c2);
    boolean flag1 = true;
    boolean flag2 = true;
    for (int i = 0; i < c1.length; i++) {
      if (c1[i] > c2[i]) {
        flag2 = false;
      } else if (c1[i] < c2[i]) {
        flag1 = false;
      }
    }

    return flag1 || flag2;
  }

  public static int partitionString(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      if (!map.containsKey(s.charAt(i))) {
        map.put(s.charAt(i), 1);
      } else {
        count++;
        map.clear();
        map.put(s.charAt(i), 1);
      }

    }
    return count + 1;
  }

  public static int longestSubstring(String s, int k) {
    return longestSubstringHelper(s, k, 0, s.length());
  }

  public static int longestSubstringHelper(String s, int k, int start, int end) {
    if (end - start < k) {
      return 0;
    }

    Map<Character, Integer> countMap = new HashMap<>();

    // Count occurrences of each character in the substring
    for (int i = start; i < end; i++) {
      countMap.put(s.charAt(i), countMap.getOrDefault(s.charAt(i), 0) + 1);
    }

    for (int i = start; i < end; i++) {
      if (countMap.get(s.charAt(i)) < k) {
        // Split the substring at character i and recursively find longest substrings
        int left = longestSubstringHelper(s, k, start, i);
        int right = longestSubstringHelper(s, k, i + 1, end);
        return Math.max(left, right);
      }
    }
    // If all characters in the substring occur at least k times, return the length of the substring
    return end - start;
  }

  public static int[] findThePrefixCommonArray(int[] A, int[] B) {
    Set<Integer> set = new HashSet<>();

    int[] ans = new int[A.length];

    int count = 0;

    set.add(A[0]);
    set.add(B[0]);
    if (A[0] == B[0]) {
      count++;
    }
    ans[0] = count;
    for (int i = 1; i < A.length; i++) {

      if (A[i] == B[i]) {
        count++;
      } else {
        if (set.contains(A[i])) {
          count++;
        }

        if (set.contains(B[i])) {
          count++;
        }
      }
      set.add(A[i]);
      set.add(B[i]);
      ans[i] = count;
    }
    return ans;
  }

  public static int countSubarrays(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for (int n : nums) {
      set.add(n);
    }

    int t = set.size();
    int count = 0;

    for (int i = 0; i < nums.length; i++) {
      HashSet<Integer> tempSet = new HashSet<>();
      for (int j = i; j < nums.length; j++) {
        tempSet.add(nums[j]);
        if (tempSet.size() == t) {
          count++;
        }
      }
    }
    return count;
  }

  /**
   * If the count of characters with odd frequencies is less than or equal to K, it's possible to
   * construct K palindrome strings. This is because we can pair each character with an odd
   * frequency with another character with the same frequency to form palindromes. If there are
   * characters with odd frequencies left after pairing, we can use them as centers for additional
   * palindromes.
   */

  public static boolean canConstruct(String s, int k) {
    // Not enough characters to construct K palindromes
    if (s.length() < k) {
      return false;
    }

    // Frequency array for lowercase English letters
    int[] freq = new int[26];

    // Increment frequency of each character
    for (char c : s.toCharArray()) {
      freq[c - 'a']++;
    }

    // Count of characters with odd frequencies
    int oddCount = 0;
    for (int f : freq) {
      if (f % 2 != 0) {
        oddCount++;
      }
    }

    return oddCount <= k;
  }

  public static long dividePlayers(int[] skill) {
    Arrays.sort(skill);

    int reqdSize = skill.length / 2;

    Map<Integer, List<int[]>> map = new HashMap<>();

    int i = 0;
    int j = skill.length - 1;

    while (i < j) {
      int sum = skill[i] + skill[j];
      List<int[]> l;
      if (map.containsKey(sum)) {
        l = map.get(sum);
      } else {
        l = new ArrayList<>();
      }
      int[] temp = new int[2];
      temp[0] = skill[i];
      temp[1] = skill[j];
      l.add(temp);
      map.put(sum, l);
      i++;
      j--;
    }

    List<int[]> resList = null;
    for (Map.Entry<Integer, List<int[]>> e : map.entrySet()) {
      if (e.getValue().size() == reqdSize) {
        resList = e.getValue();
        break;
      }
    }

    if (resList == null) {
      return -1;
    }

    long sum = 0;
    for (int[] temp : resList) {
      sum += temp[0] * temp[1];
    }
    return sum;
  }

  public static List<Integer> findLonely(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> ans = new ArrayList<>();

    for (int x : nums) {
      map.put(x, map.getOrDefault(x, 0) + 1);
    }

    for (Map.Entry<Integer, Integer> e : map.entrySet()) {
      if (e.getValue() < 2 && !map.containsKey(e.getKey() - 1) && !map.containsKey(
          e.getKey() + 1)) {
        ans.add(e.getKey());
      }
    }
    return ans;
  }

  public static List<List<Integer>> findSubsequences(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> current = new ArrayList<>();
    recurse(nums, 0, current, result);
    return result;
  }

  private static void recurse(int[] nums, int start, List<Integer> current,
      List<List<Integer>> result) {

    if (current.size() >= 2) {
      result.add(new ArrayList<>(current));
    }

    // To avoid using the same element multiple times
    Set<Integer> used = new HashSet<>();
    for (int i = start; i < nums.length; i++) {
      if (!used.contains(nums[i]) && (current.isEmpty() || nums[i] >= current.get(
          current.size() - 1))) {
        used.add(nums[i]);
        current.add(nums[i]);
        recurse(nums, i + 1, current, result);
        // backtrack
        current.remove(current.size() - 1);
      }
    }
  }

  public static int numberOfSubarrays(int[] nums, int k) {
    return atMostK(nums, k) - atMostK(nums, k - 1);
  }

  private static int atMostK(int[] nums, int k) {
    int count = 0;
    int left = 0;

    for (int right = 0; right < nums.length; right++) {
      // check for odd
      if (nums[right] % 2 == 1) {
        k--;
      }

      while (k < 0) {
        if (nums[left] % 2 == 1) {
          k++;
        }
        left++;
      }

      count += right - left + 1;
    }
    return count;
  }

  public static void braceConverter(String s) {
    for (int i = 0; i < s.length(); i++) {
      s = s.replace('[', '{');
      s = s.replace(']', '}');
    }
    System.out.println(s);
  }
}
