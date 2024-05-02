import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import linklist.ListNode;

public class Main2 {


  public static void main(String[] args) {
    int[][] dp = new int[3][3];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }

    //System.out.println(maximumPopulation(new int[][]{{1950, 1961}, {1960, 1971}, {1970, 1981}}));

    // System.out.println(canBeIncreasing(new int[]{100, 21, 100}));

    //System.out.println(maximumUnits(new int[][]{{5, 10}, {2, 5}, {4, 7}, {3, 9}}, 10));
    // System.out.println(numComponents(Arrays.asList(0, 1, 2, 3), new int[]{0, 1, 3}));
    String[] arr = new String[]{"0201", "0101", "0102", "1212", "2002"};
    int[] arr2 = new int[]{1, 7};
    //  System.out.println(kthSmallestPrimeFraction(arr2, 1));

    //insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})
    // System.out.println(countSubstrings("aba", "baba"));

    System.out.println(stoneGameII(new int[]{2, 7, 9, 4, 4}));
  }


  public int mctFromLeafValues(int[] arr) {
    int[][] memo = new int[arr.length][arr.length];
    return helper(arr, 0, arr.length - 1, memo);
  }

  private int helper(int[] arr, int left, int right, int[][] memo) {
    if (left == right) {
      return 0; // Base case: Single leaf node, no cost
    }

    if (memo[left][right] != 0) {
      return memo[left][right]; // Return memoized result
    }

    int minCost = Integer.MAX_VALUE;

    for (int i = left; i < right; i++) {
      int leftCost = helper(arr, left, i, memo);
      int rightCost = helper(arr, i + 1, right, memo);

      int maxLeft = getMax(arr, left, i);
      int maxRight = getMax(arr, i + 1, right);

      int currentCost = leftCost + rightCost + (maxLeft * maxRight);
      minCost = Math.min(minCost, currentCost);
    }

    memo[left][right] = minCost; // Memoize the result
    return minCost;
  }

  private int getMax(int[] arr, int start, int end) {
    int max = Integer.MIN_VALUE;
    for (int i = start; i <= end; i++) {
      max = Math.max(max, arr[i]);
    }
    return max;
  }


  public int mctFromLeafValues2(int[] arr) {
    int n = arr.length;
    int[][] dp = new int[n][n];

    // Fill the dynamic programming matrix
    for (int len = 2; len <= n; len++) {

      for (int left = 0; left <= n - len; left++) {

        int right = left + len - 1;
        dp[left][right] = Integer.MAX_VALUE;

        for (int k = left; k < right; k++) {
          // Calculate cost for each split and update dp[left][right]
          dp[left][right] = Math.min(dp[left][right],
              dp[left][k] + dp[k + 1][right] + maxProduct(arr, left, k) * maxProduct(arr, k + 1,
                  right));
        }


      }


    }

    return dp[0][n - 1];
  }

  private int maxProduct(int[] arr, int start, int end) {
    int max = Integer.MIN_VALUE;
    for (int i = start; i <= end; i++) {
      max = Math.max(max, arr[i]);
    }
    return max;
  }

  /**
   * dfs(piles, idx + X, Math.max(M, X), memo): This is a recursive call to the dfs method, which
   * explores the possible moves of the game starting from the next index (idx + X). It calculates
   * the maximum number of stones the opponent (Lee) can obtain from the next state of the game.
   * Math.max(M, X) ensures that the new value of M is updated based on the maximum value between
   * the current M and X, as described in the problem statement.
   *
   * stones - dfs(piles, idx + X, Math.max(M, X), memo): This expression calculates the difference
   * between the number of stones Alex can obtain (stones) and the number of stones the opponent
   * (Lee) can obtain from the next state of the game. Subtracting Lee's potential stones from
   * Alex's potential stones helps Alex maximize his score, as the goal is to end with the most
   * stones.
   *
   * Math.max(maxStones, stones - dfs(piles, idx + X, Math.max(M, X), memo)): Finally, Math.max is
   * used to update maxStones with the maximum value between its current value and the calculated
   * value (stones - dfs(...)). This ensures that maxStones always stores the maximum number of
   * stones Alex can obtain from all possible moves.
   *
   * Overall, this expression helps Alex make the best move at each step of the game, considering
   * the potential moves of both players, to maximize his score.
   */
  public static int stoneGameII(int[] piles) {
    int length = piles.length;
    int[][] dp = new int[length + 1][length + 1];
    int[] sufsum = new int[length + 1];
    for (int i = length - 1; i >= 0; i--) {
      sufsum[i] = sufsum[i + 1] + piles[i];
    }
    return helper(dp, sufsum, 0, 1);
  }

  private static int helper(int[][] dp, int[] sufsum, int i, int M) {
    if (i == sufsum.length) {
      return 0;
    }
    if (2 * M >= sufsum.length - i) {
      return sufsum[i];
    }
    if (dp[i][M] != 0) {
      return dp[i][M];
    }
    int res = Integer.MAX_VALUE;
    for (int X = 1; X <= 2 * M; X++) {
      res = Math.min(res, helper(dp, sufsum, i + X, Math.max(X, M)));
    }
    dp[i][M] = sufsum[i] - res;
    return dp[i][M];
  }

  public static int countSubstrings(String s, String t) {
    int res = 0;
    for (int i = 0; i < s.length(); ++i) {
      res += helper(s, t, i, 0);
    }
    for (int j = 1; j < t.length(); ++j) {
      res += helper(s, t, 0, j);
    }
    return res;
  }


  public static int helper(String s, String t, int i, int j) {
    int res = 0;
    // pre is the previous number of consecutive same characters.
    int pre = 0;
    // cur is the current number of consecutive same characters.
    int cur = 0;

    int n = s.length();
    int m = t.length();

    while (i < n && j < m) {
      cur += 1;

      if (s.charAt(i) != t.charAt(j)) {
        pre = cur;
        cur = 0;
      }

      res += pre;

      i++;
      j++;
    }
    return res;
  }


  public static int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> merged = new ArrayList<>();
    int i = 0;
    int n = intervals.length;

    // Add all intervals that end before the new interval starts
    while (i < n && intervals[i][1] < newInterval[0]) {
      merged.add(intervals[i]);
      i++;
    }

    // Merge overlapping intervals
    while (i < n && intervals[i][0] <= newInterval[1]) {
      newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
      newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
      i++;
    }
    merged.add(newInterval);

    // Add remaining intervals
    while (i < n) {
      merged.add(intervals[i]);
      i++;
    }

    return merged.toArray(new int[merged.size()][]);
  }

  public static String findReplaceString(String s, int[] indexes, String[] sources,
      String[] targets) {
    StringBuilder str = new StringBuilder();
    Map<Integer, String[]> map = new LinkedHashMap<>();

    for (int i = 0; i < indexes.length; i++) {
      String temp = sources[i];
      int startIdx = indexes[i];
      int endIdx = startIdx + temp.length();
      String temp2;
      try {
        temp2 = s.substring(startIdx, endIdx);
      } catch (Exception e) {
        temp2 = null;
      }

      if (temp2 != null && temp2.equals(temp)) {
        String[] arr = new String[2];
        arr[0] = sources[i];
        arr[1] = targets[i];
        map.put(indexes[i], arr);
      }
    }

    for (int i = 0; i < s.length(); ) {
      if (map.containsKey(i)) {
        String[] temp = map.get(i);
        i += temp[0].length();
        str.append(temp[1]);
      } else {
        str.append(s.charAt(i));
        i += 1;
      }
    }

    return str.toString();
  }

  public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
    PriorityQueue<double[]> pq = new PriorityQueue<>((a1, a2) -> {
      if (a2[2] > a1[2]) {
        return -1;
      } else {
        return 1;
      }
    });

    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        double[] temp = new double[3];
        temp[0] = arr[i];
        temp[1] = arr[j];
        temp[2] = Double.valueOf(arr[i]) / Double.valueOf(arr[j]);
        pq.add(temp);
      }
    }

    int[] ans = new int[2];
    double[] res = null;
    while (k > 0) {
      res = pq.poll();
      k--;
    }

    ans[0] = (int) res[0];
    ans[1] = (int) res[1];

    return ans;
  }


  /**
   * 1 + 2 ..... 9 = 45 target = 29 hence diff = 16 now if 2 of the elements have -ve sign i.e. here
   * -1 & -7 they are reducing total 16 first they don't get added second they decrease the sum
   */

  public static int reachNumber(int target) {
    // Consider only positive target
    target = Math.abs(target);
    int step = 0;
    int current = 0;

    // Increment step until current reaches or exceeds the target
    while (current < target) {
      step++;
      current += step;
    }

    // If difference between current and target is even, return step
    if ((current - target) % 2 == 0) {
      return step;
    } else {
      // Adjust step to make the difference even
      while ((current - target) % 2 != 0) {
        step++;
        current += step;
      }
      return step;
    }


  }

  public static int openLock(String[] deadends, String target) {
    Set<String> deadendsSet = new HashSet<>();
    for (String deadend : deadends) {
      deadendsSet.add(deadend);
    }

    Queue<String> queue = new LinkedList<>();

    Set<String> visited = new HashSet<>();
    queue.add("0000");
    visited.add("0000");
    int level = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String current = queue.poll();
        if (deadendsSet.contains(current)) {
          continue;
        }
        if (current.equals(target)) {
          return level;
        }
        // Generate neighbors of the current combination
        for (int j = 0; j < 4; j++) {
          for (int k = -1; k <= 1; k += 2) {
            char[] digits = current.toCharArray();
            digits[j] = (char) (((digits[j] - '0') + k + 10) % 10 + '0');
            String neighbor = new String(digits);
            if (!visited.contains(neighbor) && !deadendsSet.contains(neighbor)) {
              queue.add(neighbor);
              visited.add(neighbor);
            }
          }
        }
      }
      level++;
    }

    // Target cannot be reached
    return -1;
  }

  public static int monotoneIncreasingDigits(int n) {
    char[] digits = Integer.toString(n).toCharArray();
    int len = digits.length;
    int marker = len; // Marker to store the position where the digits need to be set to 9

    // Iterate from right to left
    for (int i = len - 1; i > 0; i--) {
      if (digits[i - 1] > digits[i]) {
        digits[i - 1]--;
        marker = i; // Update the marker position
      }
    }

    // Set all digits after the marker to 9
    for (int i = marker; i < len; i++) {
      digits[i] = '9';
    }

    // Convert the char array back to integer
    return Integer.parseInt(new String(digits));
  }

  public static int numOfSubarrays(int[] arr, int k, int threshold) {
    int left = 0;
    int right = k - 1;
    int counter = 0;

    Map<Integer, Integer> map = new HashMap<>();
    int sum = 0;

    for (int i = left; i <= right; i++) {
      sum += arr[i];
    }
    map.put(right, sum);
    left++;
    right++;

    if (sum / k >= threshold) {
      counter++;
    }

    while (right < arr.length) {
      int currSum = map.get(right - 1) + arr[right] - arr[left - 1];
      map.put(right, currSum);
      if (currSum / (k) >= threshold) {
        counter++;
      }
      left++;
      right++;
    }
    return counter;
  }

  class Employee {

    public int id;
    public int importance;
    public List<Integer> subordinates;
  }

  public static int countPalindromicSubsequences(String s) {

    int[] left = new int[26];

    int[] right = new int[26];

    Arrays.fill(left, -1);
    int curr;

    for (int i = 0; i < s.length(); i++) {
      curr = s.charAt(i) - 'a';
      if (left[curr] == -1) {
        left[curr] = i;
      }
      right[curr] = i;
    }

    int ans = 0;
    boolean[] count;

    for (int i = 0; i < 26; i++) {
      if (left[i] == -1) {
        continue;
      }
      count = new boolean[26];
      for (int j = left[i] + 1; j < right[i]; j++) {
        if (!count[s.charAt(j) - 'a']) {
          count[s.charAt(j) - 'a'] = true;
          ans++;
        }
      }
    }

    return ans;
  }

  public static int getImportance(List<Employee> employees, int id) {
    Map<Integer, Employee> map = new HashMap<>();
    for (Employee emp : employees) {
      map.put(emp.id, emp);
    }
    return dfs(map, id);
  }

  private static int dfs(Map<Integer, Employee> map, int id) {
    int totalImportance = 0;

    Employee employee = map.get(id);
    totalImportance += employee.importance;

    for (int subordinateId : employee.subordinates) {
      totalImportance += dfs(map, subordinateId);
    }

    return totalImportance;
  }

  public static int numComponents(ListNode head, int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }

    int count = 0;
    ListNode cur = head;
    while (cur != null) {
      if (set.contains(cur.val) && (cur.next == null || !set.contains(cur.next.val))) {
        count++;
      }
      cur = cur.next;
    }
    return count;
  }

  public static int deleteAndEarn(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    // Step 1: Create a frequency map for the numbers
    Map<Integer, Integer> frequencyMap = new HashMap<>();
    for (int num : nums) {
      frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + num);
    }

    // Step 2: Apply dynamic programming to compute the maximum points
    int maxVal = frequencyMap.keySet().stream().max(Integer::compare).get();
    int[] dp = new int[maxVal + 1];
    for (int num : frequencyMap.keySet()) {
      dp[num] = frequencyMap.get(num);
    }

    for (int i = 2; i < dp.length; i++) {
      dp[i] = Math.max(dp[i - 1], dp[i - 2] + dp[i]);
    }

    return dp[maxVal];
  }

  public static int minimumMoves(String s) {
    int j = 0;
    while (j < s.length() && s.charAt(j) == 'O') {
      j++;
    }

    int moves = 0;

    for (; j < s.length() - 2; ) {
      if (s.charAt(j) == 'O') {
        j++;
        continue;
      }
      if (s.charAt(j) == 'X' || s.charAt(j + 1) == 'X' || s.charAt(j + 2) == 'X') {
        moves += 1;
        j += 3;
      }
    }

    if (j < s.length() && (s.charAt(j) == 'X')) {
      moves++;
      return moves;
    }
    j++;

    if (j < s.length() && (s.charAt(j) == 'X')) {
      moves++;
      return moves;
    }

    return moves;
  }

  public static int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {

    int sum = 0;

    if (k > numOnes) {
      sum += numOnes;
      k -= numOnes;
    } else {
      return k;
    }

    if (k > numZeros) {
      k -= numZeros;
    } else {
      return sum;
    }

    if (k > numNegOnes) {
      sum -= numNegOnes;
      k -= numNegOnes;
    } else {
      sum -= k;
      return sum;
    }

    return sum;
  }

  public static int minTimeToType(String word) {
    int sec = 0;
    for (int i = 0; i < word.length(); i++) {
      int dist1 = word.charAt(i) - 'a';
      int dist2 = 'z' - word.charAt(i) + 1;

      sec += Math.min(dist1, dist2) + 1;

    }

    return sec;
  }

  public static int maximumUnits(int[][] boxTypes, int truckSize) {
    Arrays.sort(boxTypes, (t1, t2) -> (t2[1]) - (t1[1]));
    int count = 0;
    for (int i = 0; i < boxTypes.length; i++) {
      if (truckSize <= 0) {
        break;
      }

      if (truckSize > boxTypes[i][0]) {
        truckSize -= boxTypes[i][0];
        count += boxTypes[i][0] * boxTypes[i][1];
      } else {
        count += truckSize * boxTypes[i][1];
        truckSize = 0;
      }
    }
    return count;
  }

  public static int findMissingNumberIndex(int[] nums) {
    int n = nums.length;

    // Create a boolean array to mark the presence of numbers from 0 to n
    boolean[] present = new boolean[n + 1];

    // Mark visited elements within the range [0, n]
    for (int num : nums) {
      if (num >= 0 && num <= n) {
        present[num] = true;
      }
    }

    // Find the first missing number
    for (int i = 0; i <= n; i++) {
      if (!present[i]) {
        return i; // The missing number
      }
    }

    // If all numbers within the range [0, n] are present, return n + 1
    return n + 1;
  }

  /**
   * By XORing the results of the XOR operation on the indices and the elements, we effectively
   * cancel out the numbers that are present twice (once in indices and once in elements), leaving
   * us with only the missing number.
   */
  public static int findMissingNumberXor(int[] nums) {
    int n = nums.length;
    int missing = n; // Initially assuming n is missing

    // XOR all indices from 0 to n
    for (int i = 0; i < n; i++) {
      missing ^= i;
    }

    // XOR all elements in the array
    for (int num : nums) {
      missing ^= num;
    }

    return missing;
  }

  public static int numDifferentIntegers(String word) {
    Set<String> set = new HashSet<>();
    for (int i = 0; i < word.length(); ) {
      char c = word.charAt(i);
      if (Character.isDigit(c)) {
        StringBuilder stringBuilder = new StringBuilder();
        while (i < word.length() && Character.isDigit(word.charAt(i))) {
          if (stringBuilder.length() >= 1 && stringBuilder.charAt(0) == '0') {
            stringBuilder.deleteCharAt(0);
          }
          stringBuilder.append(word.charAt(i));
          i++;
        }
        set.add(stringBuilder.toString());
      } else {
        i++;
      }
    }
    return set.size();
  }

  public static boolean canBeIncreasing(int[] nums) {
    int n = nums.length;
    boolean removed = false;

    for (int i = 1; i < n; i++) {
      if (nums[i] <= nums[i - 1]) {
        // Already removed an element before
        if (removed) {
          return false;
        }
        removed = true;

        // Try removing nums[i]
        if (i == 1 || nums[i] > nums[i - 2]) {
          // Removing nums[i] makes nums[0:i-1] strictly increasing
          nums[i - 1] = nums[i];
        } else {
          // Try removing nums[i - 1]
          // Removing nums[i - 1] makes nums[i - 1:i] strictly increasing
          nums[i] = nums[i - 1];
        }
      }
    }

    return true;
  }

  public static String makeFancyString(String s) {
    StringBuilder str = new StringBuilder();
    char currentChar = s.charAt(0);
    str.append(currentChar);
    int val = 1;

    for (int i = 1; i < s.length(); i++) {
      if (currentChar == s.charAt(i)) {
        val++;
      } else {
        currentChar = s.charAt(i);
        val = 1;
      }
      if (val < 3) {
        str.append(s.charAt(i));
      }
    }

    return str.toString();
  }

  public static boolean isThree(int n) {

    if (n <= 2) {
      return false;
    }

    int maxFactor = (int) Math.sqrt(n);
    int factors = 2;
    for (int i = 2; i <= maxFactor; i++) {
      if (n % i == 0) {
        if (n / i > maxFactor) {
          factors++;
        }

        factors++;
      }
    }
    return factors == 3 ? true : false;
  }

  public static boolean checkZeroOnes(String s) {
    int maxOnes = 0;
    int maxZeros = 0;

    int tempMaxOnes = 0;
    int tempMaxZeros = 0;

    if (s.length() == 1) {
      return Integer.valueOf(String.valueOf(s.charAt(0))) == 1 ? true : false;
    }

    int currentVal = Integer.valueOf(String.valueOf(s.charAt(0)));

    if (currentVal == 1) {
      maxOnes++;
      tempMaxOnes++;
    } else {
      maxZeros++;
      tempMaxZeros++;
    }

    for (int i = 1; i < s.length(); i++) {
      int val1 = Integer.valueOf(String.valueOf(s.charAt(i)));
      if (currentVal == 0 && val1 == 0) {
        tempMaxZeros++;

      } else if (currentVal == 1 && val1 == 1) {
        tempMaxOnes++;
      } else if (currentVal == 0 && val1 == 1) {
        tempMaxZeros = 0;
        tempMaxOnes = 1;
      } else {
        tempMaxZeros = 1;
        tempMaxOnes = 0;
      }
      currentVal = val1;
      maxOnes = Math.max(maxOnes, tempMaxOnes);
      maxZeros = Math.max(maxZeros, tempMaxZeros);

    }

    return maxOnes > maxZeros ? true : false;

  }

  public static String sortSentence(String s) {
    String[] str = s.split(" ");
    String[] ans = new String[str.length];

    for (int i = 0; i < str.length; i++) {
      String actualString = str[i].substring(0, str[i].length() - 1);
      int posn = Integer.valueOf(String.valueOf(str[i].charAt(str[i].length() - 1)));
      ans[posn - 1] = actualString;
    }
    StringBuilder x = new StringBuilder();
    x.append(ans[0]);
    for (int i = 1; i < ans.length; i++) {
      x.append(" ");
      x.append(ans[i]);
    }

    return x.toString();
  }

  public static int maximumPopulation(int[][] logs) {
    // Array to store population counts for each year (1950 to 2050)
    int[] population = new int[2051];

    // Increment population count for birth year and decrement for death year
    for (int[] log : logs) {
      population[log[0]]++;
      population[log[1]]--;
    }

    // Calculate cumulative population for each year
    for (int i = 1; i < population.length; i++) {
      population[i] += population[i - 1];
    }

    int maxPopulation = 0;
    int maxYear = 0;

    // Find the year with maximum population
    for (int i = 1950; i <= 2050; i++) {
      if (population[i] > maxPopulation) {
        maxPopulation = population[i];
        maxYear = i;
      }
    }

    return maxYear;
  }

  public int specialArray(int[] nums) {
    // Sort the input array in non-decreasing order
    Arrays.sort(nums);
    int n = nums.length;
    int left = 0;
    int right = n;

    while (left < right) {
      int mid = left + (right - left) / 2;
      int count = countGreaterOrEqual(nums, mid);
      if (count < mid) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    if (left == 0 || nums[n - left] < left) {
      return -1;
    } else {
      return left;
    }
  }

  private int countGreaterOrEqual(int[] nums, int target) {
    int count = 0;
    for (int num : nums) {
      if (num >= target) {
        count++;
      }
    }
    return count;
  }

  public static int maxLengthBetweenEqualCharacters(String s) {
    Map<Character, int[]> map = new HashMap<>();
    int maxLength = -1;
    for (int i = 0; i < s.length(); i++) {
      Character c = s.charAt(i);
      int[] arr;
      if (map.containsKey(c)) {
        arr = map.get(c);
        maxLength = Math.max(s.substring(arr[0] + 1, i).length(), maxLength);
        arr[1] = i;
        arr[2]++;
      } else {
        arr = new int[3];
        arr[0] = i;
        arr[1] = -1;
        arr[2] = 1;
      }
      map.put(c, arr);

    }
    return maxLength;

  }

  public int[] frequencySort(int[] nums) {
    int[] ans = new int[nums.length];
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    }

    PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((e1, e2) -> {
      if (e1.getValue() == e2.getValue()) {
        return e2.getKey() - e1.getKey();
      } else {
        return e1.getValue() - e2.getValue();
      }
    });

    for (Map.Entry<Integer, Integer> e : map.entrySet()) {
      pq.add(e);
    }

    int i = 0;
    while (!pq.isEmpty()) {
      Map.Entry<Integer, Integer> e = pq.poll();
      e.setValue(e.getValue() - 1);
      if (e.getValue() > 0) {
        pq.add(e);
      }
      ans[i] = e.getKey();
      i++;
    }

    return ans;
  }

  public String[] findRelativeRanks(int[] score) {
    int n = score.length;
    String[] ans = new String[n];
    PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e2[0] - e1[0]);

    for (int i = 0; i < n; i++) {
      int[] arr = new int[2];
      arr[0] = score[i];
      arr[1] = i;
      pq.add(arr);
    }

    int counter = 0;
    while (!pq.isEmpty()) {
      int[] arr = pq.poll();
      if (counter == 0) {
        ans[arr[1]] = "Gold Medal";
      } else if (counter == 1) {
        ans[arr[1]] = "Silver Medal";
      } else if (counter == 2) {
        ans[arr[1]] = "Bronze Medal";
      } else {
        ans[arr[1]] = String.valueOf(counter);
      }
      counter++;
    }
    return ans;
  }

  public static String addBinary(String a, String b) {
    StringBuilder result = new StringBuilder();
    int carry = 0;
    int i = a.length() - 1;
    int j = b.length() - 1;

    while (i >= 0 || j >= 0 || carry > 0) {
      int sum = carry;
      if (i >= 0) {
        sum += a.charAt(i--) - '0';
      }
      if (j >= 0) {
        sum += b.charAt(j--) - '0';
      }
      result.append(sum % 2);
      carry = sum / 2;
    }

    return result.reverse().toString();
  }

  public static int rob2(int[] nums) {
    int[] dp1 = new int[nums.length];
    int[] dp2 = new int[nums.length];
    Arrays.fill(dp1, Integer.MIN_VALUE);
    Arrays.fill(dp2, Integer.MIN_VALUE);

    if (nums.length == 1) {
      return nums[0];
    }

    if (nums.length == 2) {
      return Math.max(nums[0], nums[1]);
    }

    int c1 = rob2(nums, nums.length - 2, 0, dp1);
    int c2 = rob2(nums, nums.length - 1, 1, dp2);
    return Math.max(c1, c2);
  }

  public static int rob2(int[] nums, int start, int end, int[] dp) {
    if (start < end) {
      return 0;
    }

    if (dp[start] != Integer.MIN_VALUE) {
      return dp[start];
    }

    dp[start] = Math.max(nums[start] + rob2(nums, start - 2, end, dp),
        rob2(nums, start - 1, end, dp));

    return dp[start];
  }

  public static int rob1(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, Integer.MIN_VALUE);
    return rob1(nums, nums.length - 1, dp);
  }

  public static int rob1(int[] nums, int n, int[] dp) {
    if (n < 0) {
      return 0;
    }

    if (dp[n] != Integer.MIN_VALUE) {
      return dp[n];
    }

    dp[n] = Math.max(nums[n] + rob1(nums, n - 2, dp), rob1(nums, n - 1, dp));

    return dp[n];
  }

  public static int rob(int[] nums) {
    return rob(nums, nums.length - 1);
  }

  public static int rob(int[] nums, int n) {
    if (n < 0) {
      return 0;
    }

    int c1;
    int c2;

    if (n == nums.length - 1) {

    }

    if (n - 2 != 0) {
      c1 = rob(nums, n - 1);
      c2 = nums[n] + rob(nums, n - 2);
    } else {
      c1 = rob(nums, n - 1);
      c2 = 0;
    }

    return Math.max(c1, c2);
  }

  public int numTrees(int n) {
    // Create a memoization array to store the results of subproblems
    int[][] dp = new int[n + 1][n + 1];
    return countTrees(1, n, dp);
  }

  private int countTrees(int start, int end, int[][] dp) {
    // Base case: if start > end, there are no nodes, so return 1
    if (start > end) {
      return 1;
    }

    // Check if the result for the current range [start, end] is already computed
    if (dp[start][end] != 0) {
      return dp[start][end];
    }

    int total = 0;

    // Consider each number from start to end as the root
    for (int i = start; i <= end; i++) {
      // Recursively count the number of unique BSTs formed in the left subtree
      int leftCount = countTrees(start, i - 1, dp);
      // Recursively count the number of unique BSTs formed in the right subtree
      int rightCount = countTrees(i + 1, end, dp);
      // Multiply the counts of left and right subtrees to get the total number of unique BSTs formed with i as the root
      total += leftCount * rightCount;
    }

    // Store the computed result in the memoization array
    dp[start][end] = total;
    return total;
  }

  public static int minDistance(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }
    if (word1.equalsIgnoreCase(word2)) {
      return 0;
    }
    return minDistanceRec(word1, word2, word1.length(), word2.length(), dp);
  }

  public static int minDistanceRec(String word1, String word2, int m, int n, int[][] dp) {

    if (dp[m][n] != -1) {
      return dp[m][n];
    }

    if (m == 0) {
      dp[m][n] = n;
      return n;
    }
    if (n == 0) {
      dp[m][n] = m;
      return m;
    }

    if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
      dp[m][n] = minDistanceRec(word1, word2, m - 1, n - 1, dp);
      return dp[m][n];
    }

    int insert = minDistanceRec(word1, word2, m, n - 1, dp);
    int delete = minDistanceRec(word1, word2, m - 1, n, dp);
    int replace = minDistanceRec(word1, word2, m - 1, n - 1, dp);

    dp[m][n] = 1 + Math.min(insert, Math.min(delete, replace));
    return dp[m][n];

  }

  public static int furthestBuilding(int[] heights, int bricks, int ladders) {
    PriorityQueue<Integer> diffPq = new PriorityQueue<>();
    for (int i = 0; i < heights.length - 1; i++) {
      int diff = heights[i + 1] - heights[i];
      if (diff > 0) {
        if (ladders > 0) {
          diffPq.offer(diff);
          ladders--;
        } else if (!diffPq.isEmpty() && diff > diffPq.peek()) {
          bricks -= diffPq.poll();
          diffPq.offer(diff);
        } else {
          bricks -= diff;
        }
        if (bricks < 0) {
          return i;
        }
      }
    }
    return heights.length - 1;
  }

  public static int findLeastNumOfUniqueInts(int[] arr, int k) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int x : arr) {
      map.put(x, map.getOrDefault(x, 0) + 1);
    }

    PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
        (e1, e2) -> e1.getValue() - e2.getValue());

    for (Map.Entry<Integer, Integer> e : map.entrySet()) {
      pq.add(e);
    }

    for (int i = 1; i <= k; i++) {
      Map.Entry<Integer, Integer> e = pq.poll();
      e.setValue(e.getValue() - 1);
      if (e.getValue() > 0) {
        pq.add(e);
      }
    }

    return pq.size();
  }

  public static int uniquePathsWithObstacles(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    int[][] dp = new int[rows][cols];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }
    return uniquePathsWithObstacles(grid, rows - 1, cols - 1, dp);
  }

  public static int uniquePathsWithObstacles(int[][] grid, int row, int col, int[][] dp) {
    if (grid[0][0] == 1) {
      return 0;
    }

    if (row == 0 && col == 0) {
      return 1;
    }

    if (grid[row][col] == 1) {
      return 0;
    }

    if (col <= 0 || row <= 0) {
      return 0;
    }

    if (dp[row][col] != -1) {
      return dp[row][col];
    }

    int pathsLeft = uniquePathsWithObstacles(grid, row, col - 1, dp);
    int pathsUp = uniquePathsWithObstacles(grid, row - 1, col, dp);

    dp[row][col] = pathsUp + pathsLeft;

    return pathsUp + pathsLeft;
  }

  public static int uniquePathsWithObstaclesDP(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;

    // Create a 2D array to store the number of unique paths
    int[][] dp = new int[m][n];

    // Initialize the number of paths for the starting cell
    dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;

    // Initialize the number of paths for the first row and first column
    for (int i = 1; i < m; i++) {
      dp[i][0] = obstacleGrid[i][0] == 0 ? dp[i - 1][0] : 0;
    }
    for (int j = 1; j < n; j++) {
      dp[0][j] = obstacleGrid[0][j] == 0 ? dp[0][j - 1] : 0;
    }

    // Iterate through the grid to calculate the number of unique paths
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (obstacleGrid[i][j] == 0) {
          dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        } else {
          dp[i][j] = 0;
        }
      }
    }

    // Return the number of unique paths to the bottom-right cell
    return dp[m - 1][n - 1];
  }

  public static long largestPerimeter(int[] nums) {
    Arrays.sort(nums);
    if (nums.length < 3) {
      return -1;
    }

    long[] prefix = new long[nums.length];
    prefix[0] = nums[0];

    for (int i = 1; i < nums.length; i++) {
      prefix[i] = prefix[i - 1] + nums[i];
    }

    for (int i = nums.length - 1; i >= 1; i--) {
      if (i == 1) {
        return -1;
      }
      if (nums[i] < prefix[i - 1]) {
        return prefix[i];
      }
    }

    return -1;
  }

  public static int[] findingUsersActiveMinutes(int[][] logs, int k) {
    // at each minute how many users are taking actions ?
    int[] ans = new int[k];
    Map<Integer, Set<Integer>> map = new HashMap<>();
    for (int i = 0; i < logs.length; i++) {
      if (!map.containsKey(logs[i][0])) {
        Set<Integer> set = new HashSet<>();
        set.add(logs[i][1]);
        map.put(logs[i][0], set);
      } else {
        Set<Integer> set = map.get(logs[i][0]);
        set.add(logs[i][1]);
        map.put(logs[i][0], set);
      }
    }

    for (Map.Entry<Integer, Set<Integer>> e : map.entrySet()) {
      int uam = e.getValue() != null ? e.getValue().size() : 0;
      if (uam != 0 && ans.length >= uam) {
        ans[uam - 1] += 1;
      }
    }

    return ans;
  }

  public static List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
    List<Boolean> ans = new ArrayList<>();

    for (int i = 0; i < l.length; i++) {
      List<Integer> temp = new ArrayList<>();
      for (int j = l[i]; j <= r[i]; j++) {
        temp.add(nums[j]);
      }
      Collections.sort(temp);
      if (temp.size() < 2) {
        ans.add(false);
        continue;
      }
      int diff = temp.get(1) - temp.get(0);
      boolean val = true;
      for (int x = 2; x < temp.size(); x++) {
        if (diff != temp.get(x) - temp.get(x - 1)) {
          val = false;
          break;
        }
      }
      ans.add(val);
    }
    return ans;
  }

  public static int garbageCollection(String[] garbage, int[] travel) {
    int sum = 0;

    int[] arr = new int[3];
    Arrays.fill(arr, Integer.MAX_VALUE);

    for (int i = garbage.length - 1; i >= 0; i--) {
      String s = garbage[i];
      if (arr[0] != Integer.MAX_VALUE && arr[1] != Integer.MAX_VALUE
          && arr[2] != Integer.MAX_VALUE) {
        break;
      }
      if (arr[0] == Integer.MAX_VALUE && s.contains("M")) {
        arr[0] = i;
      }
      if (arr[1] == Integer.MAX_VALUE && s.contains("P")) {
        arr[1] = i;
      }
      if (arr[2] == Integer.MAX_VALUE && s.contains("G")) {
        arr[2] = i;
      }
    }

    int prefixTravel[] = new int[travel.length];
    prefixTravel[0] = travel[0];
    for (int i = 1; i < travel.length; i++) {
      prefixTravel[i] = prefixTravel[i - 1] + travel[i];
    }

    for (int i = 0; i < 3; i++) {
      int idx = arr[i];
      if (idx != Integer.MAX_VALUE && idx != 0) {
        sum += prefixTravel[idx - 1];
      }
    }

    for (int j = 0; j < garbage.length; j++) {
      sum += garbage[j].length();
    }

    return sum;
  }

  public static List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    boolean[] used = new boolean[nums.length];
    backtrackPermute(nums, used, new ArrayList<>(), result);
    return result;
  }

  private static void backtrackPermute(int[] nums, boolean[] used, List<Integer> current,
      List<List<Integer>> result) {
    // stop case
    if (current.size() == nums.length) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
        continue;
      }
      used[i] = true;
      current.add(nums[i]);
      backtrackPermute(nums, used, current, result);
      current.remove(current.size() - 1);
      used[i] = false;
    }
  }

  public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates);
    dfsCombinationSum2(candidates, target, 0, new ArrayList<>(), result);
    return result;
  }

  private static void dfsCombinationSum2(int[] candidates, int target, int start,
      List<Integer> current,
      List<List<Integer>> result) {
    if (target < 0) {
      return;
    }
    if (target == 0) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = start; i < candidates.length; i++) {
      if (i > start && candidates[i] == candidates[i - 1]) {
        continue;
      }
      current.add(candidates[i]);
      dfsCombinationSum2(candidates, target - candidates[i], i + 1, current, result);
      current.remove(current.size() - 1);
    }
  }

  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(candidates, target, 0, new ArrayList<>(), result);
    return result;
  }

  private static void dfs(int[] candidates, int target, int start, List<Integer> current,
      List<List<Integer>> result) {
    if (target < 0) {
      return;
    }
    if (target == 0) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = start; i < candidates.length; i++) {
      current.add(candidates[i]);
      dfs(candidates, target - candidates[i], i, current, result);
      current.remove(current.size() - 1);
    }
  }

  public static int maxIncreaseKeepingSkyline(int[][] grid) {
    int sum = 0;
    int n = grid.length;
    int col[] = new int[n];
    int row[] = new int[n];
    for (int i = 0; i < n; i++) {
      col[i] = Integer.MIN_VALUE;
      for (int j = 0; j < n; j++) {
        col[i] = Math.max(grid[j][i], col[i]);
      }
    }
    for (int i = 0; i < n; i++) {
      row[i] = Integer.MIN_VALUE;
      for (int j = 0; j < n; j++) {
        row[i] = Math.max(grid[i][j], row[i]);
      }
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        sum += Math.min(row[i], col[j]) - grid[i][j];
      }
    }
    return sum;
  }

  public static int findGcd(int a, int b) {
    while (a > 0 && b > 0) {
      if (a > b) {
        a = a % b;
      } else {
        b = b % a;
      }
    }
    if (a == 0) {
      return b;
    }
    return a;
  }

  public static int[][] divideArraySize3(int[] nums, int k) {
    int len = nums.length;
    if (len % 3 != 0) {
      return new int[0][0];
    }
    int[][] ans = new int[len / 3][3];
    boolean isPossible = true;
    Arrays.sort(nums);

    int idx = 0;
    for (int i = 0; i < nums.length; i += 3) {
      if (!isPossible) {
        break;
      }

      if (nums[i + 2] - nums[i] <= k) {
        for (int j = 0; j < 3; j++) {
          ans[idx][j] = nums[i + j];
        }
      } else {
        return new int[0][0];
      }
      idx++;
    }

    return ans;
  }

  public static int[] dailyTemperatures(int[] temperatures) {
    int ans[] = new int[temperatures.length];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < temperatures.length; i++) {
      while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
        int prevIndex = stack.pop();
        ans[prevIndex] = i - prevIndex;
      }
      stack.push(i);
    }
    return ans;
  }

  public static boolean uniqueOccurrences(int[] arr) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int num : arr) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    Set<Integer> set = new HashSet<>();

    for (int x : map.values()) {
      if (set.contains(x)) {
        return false;
      } else {
        set.add(x);
      }
    }

    return true;
  }

  public static int minOperations(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int n : nums) {
      map.put(n, map.getOrDefault(n, 0) + 1);
    }
    int ops = 0;
    for (Integer x : map.keySet()) {
      int val = map.get(x);
      if (val == 2) {
        ops++;
      } else if (val / 3 < 1) {
        return -1;
      } else if (val % 3 == 0) {
        ops += val / 3;
      } else if (val % 3 > 0 && val % 3 < 3) {
        ops += (val / 3) + 1;
      }
    }

    return ops == 0 ? -1 : ops;
  }

  public static int numberOfBeams(String[] bank) {
    int[] arr = new int[bank.length];

    for (int i = 0; i < bank.length; i++) {
      for (int j = 0; j < bank[i].length(); j++) {
        if (bank[i].charAt(j) == '1') {
          arr[i]++;
        }
      }
    }

    int res = 1;
    int count = 0;
    int idx = 0;

    while (idx < arr.length) {
      if (arr[idx] != 0) {
        res = arr[idx];
        idx++;
        break;
      }
      idx++;
    }

    for (int i = idx; i < arr.length; i++) {
      if (arr[i] != 0) {
        res *= arr[i];
        count += res;
        res = arr[i];
      }
    }

    return count;
  }

  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    boolean visited[] = new boolean[rooms.size()];
    Queue<Integer> q = new LinkedList<>();
    int x = 0;
    q.add(0);
    visited[0] = true;

    while (!q.isEmpty()) {
      int curr = q.poll();
      for (int i = 0; i < rooms.get(curr).size(); i++) {
        if (!visited[rooms.get(curr).get(i)]) {
          q.add(rooms.get(curr).get(i));
          visited[rooms.get(curr).get(i)] = true;
        }
      }
    }

    for (int i = 0; i < visited.length; i++) {
      if (!visited[i]) {
        return false;
      }
    }
    return true;
  }

  public static int[] findErrorNums(int[] nums) {
    int[] result = new int[2];
    int n = nums.length;

    for (int i = 0; i < n; i++) {
      while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
        swap(nums, i, nums[i] - 1);
      }
    }

    for (int i = 0; i < n; i++) {
      if (nums[i] != i + 1) {
        result[0] = nums[i];
        result[1] = i + 1;
      }
    }

    return result;
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static List<List<Integer>> groupThePeople(int[] groupSizes) {
    List<List<Integer>> res = new ArrayList<>();

    Map<Integer, List<Integer>> map = new HashMap<>();

    for (int i = 0; i < groupSizes.length; i++) {
      List<Integer> l1;
      if (!map.containsKey(groupSizes[i])) {
        l1 = new ArrayList<>();
        l1.add(i);
      } else {
        l1 = map.get(groupSizes[i]);
        l1.add(i);
      }
      if (l1.size() == groupSizes[i]) {
        res.add(new ArrayList<>(l1));
        l1.removeAll(l1);
      }
      map.put(groupSizes[i], l1);
    }

    return res;
  }

  public static boolean isStrictlyPalindromic(int n) {
    for (int i = 2; i <= n - 2; i++) {
      String str = convertFromBaseToBase(String.valueOf(n), 10, i);
      if (!isPalindrome(str)) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPalindrome(String s) {
    int i = 0;
    int j = s.length() - 1;

    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }

    return true;
  }

  public static String convertFromBaseToBase(String str, int fromBase, int toBase) {
    return Integer.toString(Integer.parseInt(str, fromBase), toBase);
  }

  public static int maximumScore(int a, int b, int c) {

    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, a);
    map.put(1, b);
    map.put(2, c);

    PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(
        (e1, e2) -> e2.getValue() - e1.getValue());

    maxHeap.addAll(map.entrySet());
    int score = 0;
    while (maxHeap.size() >= 2) {
      Map.Entry<Integer, Integer> e1 = maxHeap.poll();
      Map.Entry<Integer, Integer> e2 = maxHeap.poll();
      e1.setValue(e1.getValue() - 1);
      e2.setValue(e2.getValue() - 1);

      if (e1.getValue() > 0) {
        maxHeap.add(e1);
      }

      if (e2.getValue() > 0) {
        maxHeap.add(e2);
      }
      score++;
    }
    return score;
  }

  public static int longestSubarray(int[] nums, int limit) {

    TreeMap<Integer, Integer> minMap = new TreeMap<>();
    int left = 0;
    int result = 0;

    for (int right = 0; right < nums.length; right++) {
      minMap.put(nums[right], minMap.getOrDefault(nums[right], 0) + 1);

      while (minMap.lastKey() - minMap.firstKey() > limit) {
        minMap.put(nums[left], minMap.get(nums[left]) - 1);
        if (minMap.get(nums[left]) == 0) {
          minMap.remove(nums[left]);
        }

        left++;
      }

      result = Math.max(result, right - left + 1);
    }

    return result;
  }

  public static String longestDiverseString(int a, int b, int c) {

    PriorityQueue<int[]> maxHeap = new PriorityQueue<>((t1, t2) -> t2[1] - t1[1]);

    StringBuilder res = new StringBuilder();

    if (a > 0) {
      maxHeap.add(new int[]{'a', a});
    }

    if (b > 0) {
      maxHeap.add(new int[]{'b', b});
    }

    if (c > 0) {
      maxHeap.add(new int[]{'c', c});
    }

    while (!maxHeap.isEmpty()) {

      int[] first = maxHeap.poll();

      if (res.length() <= 1 || !(res.charAt(res.length() - 2) == res.charAt(res.length() - 1)
          && res.charAt(res.length() - 1) == first[0])) {
        first[1] -= 1;
        res.append((char) first[0]);
        if (first[1] > 0) {
          maxHeap.add(first);
        }
      } else {

        if (maxHeap.isEmpty()) {
          break;
        }

        int[] second = maxHeap.poll();
        second[1] -= 1;
        res.append((char) second[0]);
        if (second[1] > 0) {
          maxHeap.add(second);
        }
        maxHeap.add(first);
      }

    }

    return res.toString();
  }

  public static int minSetSize(int[] arr) {
    int n = arr.length;

    Map<Integer, Integer> countMap = new HashMap<>();

    for (int num : arr) {
      countMap.put(num, countMap.getOrDefault(num, 0) + 1);
    }

    PriorityQueue<Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(
        (e1, e2) -> e2.getValue() - e1.getValue());

    for (Entry<Integer, Integer> e : countMap.entrySet()) {
      maxHeap.add(e);
    }

    int counter = n;
    int minSize = 0;
    while (!maxHeap.isEmpty() && counter > n / 2) {
      counter -= maxHeap.poll().getValue();
      minSize++;

    }
    return minSize;
  }

  public static boolean carPooling(int[][] trips, int capacity) {

    int[] timeline = new int[1001];

    // Populate the timeline array with the net change in passengers at each location.
    for (int[] trip : trips) {
      // Passenger pickup
      timeline[trip[1]] += trip[0];
      // Passenger drop-off
      timeline[trip[2]] -= trip[0];
    }

    // Check the cumulative sum at each point on the timeline.
    int currentCapacity = 0;
    for (int passengersChange : timeline) {
      currentCapacity += passengersChange;
      if (currentCapacity > capacity) {
        // Capacity exceeded at some point
        return false;
      }
    }

    return true;
  }

  public static int[] rearrangeBarcodes(int[] barcodes) {

    int n = barcodes.length;

    List<Integer> result = new ArrayList<>();

    HashMap<Integer, Integer> numCount = new HashMap<>();

    // Count the frequency of each character.
    for (int c : barcodes) {
      numCount.put(c, numCount.getOrDefault(c, 0) + 1);
    }

    // Create a max heap (priority queue) based on character frequency.
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
        (a, b) -> numCount.get(b) - numCount.get(a));

    maxHeap.addAll(numCount.keySet());

    while (maxHeap.size() >= 2) {
      Integer first = maxHeap.poll();
      Integer second = maxHeap.poll();

      result.add(first);
      result.add(second);

      numCount.put(first, numCount.get(first) - 1);
      numCount.put(second, numCount.get(second) - 1);

      if (numCount.get(first) > 0) {
        maxHeap.add(first);
      }
      if (numCount.get(second) > 0) {
        maxHeap.add(second);
      }
    }

    // If there is a character left in the max heap, append it.
    if (!maxHeap.isEmpty()) {
      Integer lastChar = maxHeap.poll();
      if (numCount.get(lastChar) > 1) {
        return new int[]{};
      }
      result.add(lastChar);
    }

    int[] resArr = new int[result.size()];

    for (int i = 0; i < result.size(); i++) {
      resArr[i] = result.get(i);
    }

    return resArr;

  }

  public static String reorganizeString(String s) {

    int n = s.length();
    HashMap<Character, Integer> charCount = new HashMap<>();

    // Count the frequency of each character.
    for (char c : s.toCharArray()) {
      charCount.put(c, charCount.getOrDefault(c, 0) + 1);
    }

    // Create a max heap (priority queue) based on character frequency.
    PriorityQueue<Character> maxHeap = new PriorityQueue<>(
        (a, b) -> charCount.get(b) - charCount.get(a));

    maxHeap.addAll(charCount.keySet());

    // Build the result string.
    StringBuilder result = new StringBuilder();

    while (maxHeap.size() >= 2) {
      char first = maxHeap.poll();
      char second = maxHeap.poll();

      result.append(first);
      result.append(second);

      charCount.put(first, charCount.get(first) - 1);
      charCount.put(second, charCount.get(second) - 1);

      if (charCount.get(first) > 0) {
        maxHeap.add(first);
      }
      if (charCount.get(second) > 0) {
        maxHeap.add(second);
      }
    }

    // If there is a character left in the max heap, append it.
    if (!maxHeap.isEmpty()) {
      char lastChar = maxHeap.poll();
      if (charCount.get(lastChar) > 1) {
        return "";
      }
      result.append(lastChar);
    }

    return result.toString();

  }

  public static List<String> topKFrequent(String[] words, int k) {

    List<String> result = new ArrayList<>();

    Map<String, Integer> map = new HashMap<>();

    for (String word : words) {
      if (map.containsKey(word)) {
        map.put(word, map.get(word) + 1);
      } else {
        map.put(word, 1);
      }
    }

    PriorityQueue<Entry<String, Integer>> pq = new PriorityQueue<>(
        new Comparator<Entry<String, Integer>>() {
          @Override
          public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {

            if (e1.getValue() == e2.getValue()) {
              return e1.getKey().compareTo(e2.getKey());
            } else {
              return e2.getValue() - e1.getValue();
            }

          }
        });

    for (Entry<String, Integer> e : map.entrySet()) {
      pq.add(e);
    }

    for (int i = 0; i < Math.min(k, map.size()); i++) {
      result.add(pq.poll().getKey());
    }

    return result;
  }

  public static boolean isPossible(int[] nums) {

    HashMap<Integer, PriorityQueue<Integer>> subsequences = new HashMap<>();

    for (int num : nums) {
      if (!subsequences.containsKey(num - 1) || subsequences.get(num - 1).isEmpty()) {
        // If there is no subsequence ending at num - 1, start a new subsequence.
        if (!subsequences.containsKey(num)) {
          subsequences.put(num, new PriorityQueue<>());
        }
        // Length of the new subsequence is 1.
        subsequences.get(num).add(1);
      } else {
        // Extend an existing subsequence.
        int length = subsequences.get(num - 1).poll();
        if (!subsequences.containsKey(num)) {
          subsequences.put(num, new PriorityQueue<>());
        }
        // Increase the length of the subsequence.
        subsequences.get(num).add(length + 1);
      }
    }

    // Check if there is any subsequence with length less than 3.
    for (PriorityQueue<Integer> queue : subsequences.values()) {
      if (!queue.isEmpty() && queue.peek() < 3) {
        return false;
      }
    }

    return true;
  }

  public static List<Integer> findClosestElements(int[] arr, int k, int x) {

    List<Integer> result = new ArrayList<>();

    PriorityQueue<int[]> minHeap = new PriorityQueue<>((t1, t2) -> {
      if (Math.abs(t1[0] - t1[1]) == Math.abs(t2[0] - t2[1])) {
        return t1[0] - t2[0];
      } else {
        return Math.abs(t1[0] - t1[1]) - Math.abs(t2[0] - t2[1]);
      }
    });

    for (int i = 0; i < arr.length; i++) {
      int current[] = new int[2];
      current[0] = arr[i];
      current[1] = x;

      minHeap.add(current);

    }

    for (int i = 1; i <= k; i++) {
      int current[] = minHeap.poll();
      result.add(current[0]);
    }
    Collections.sort(result);
    return result;
  }

  public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<List<Integer>> result = new ArrayList<>();

    if (nums1.length == 0 || nums2.length == 0 || k == 0) {
      return result;
    }

    if (nums1.length == 1 && nums2.length == 1) {
      result.add(Arrays.asList(nums1[0], nums2[0]));
      return result;
    }

    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

    // last index of array stores index of the array
    for (int i = 0; i < Math.min(nums1.length, k); i++) {
      minHeap.add(new int[]{nums1[i], nums2[0], 0});
    }

    while (k > 0 && !minHeap.isEmpty()) {
      int[] current = minHeap.poll();
      result.add(Arrays.asList(current[0], current[1]));

      if (current[2] < nums2.length - 1) {
        minHeap.add(new int[]{current[0], nums2[current[2] + 1], current[2] + 1});
      }

      k--;
    }

    return result;
  }

  public static int sumSubarrayMins(int[] arr) {
    final int MOD = 1000000007;
    int n = arr.length;

    // Use long to avoid overflow
    long result = 0;

    Stack<Integer> stack = new Stack<>();
    int[] left = new int[n];
    int[] right = new int[n];

    // Calculate the index of the nearest smaller element to the left
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
        stack.pop();
      }
      left[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(i);
    }

    stack.clear();

    // Calculate the index of the nearest smaller element to the right
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
        stack.pop();
      }
      right[i] = stack.isEmpty() ? n : stack.peek();
      stack.push(i);
    }

    // Calculate the contribution of each element to the final result
    for (int i = 0; i < n; i++) {
      result = (result + (long) arr[i] * (i - left[i]) * (right[i] - i)) % MOD;
    }

    return (int) result;
  }

  public boolean isInterleave(String s1, String s2, String s3, int i, int j, Boolean[][] dp) {
    if (i == s1.length() && j == s2.length()) {
      return true;
    }

    if (dp[i][j] != null) {
      return dp[i][j];
    }

    if (s1.charAt(i) == s3.charAt(i + j)) {
      boolean f1 = isInterleave(s1, s2, s3, i + 1, j, dp);
      dp[i][j] = f1;
      if (f1) {
        return true;
      }
    }

    if (s2.charAt(j) == s3.charAt(i + j)) {
      boolean f2 = isInterleave(s1, s2, s3, i, j + 1, dp);
      dp[i][j] = f2;
      if (f2) {
        return true;
      }
    }

    dp[i][j] = false;
    return false;
  }

  public static void lcs(String a, String b) {

    StringBuilder str = new StringBuilder();

    int[][] dp = new int[a.length() + 1][b.length() + 1];

    for (int i = 0; i < a.length(); i++) {
      dp[i][0] = 0;
    }

    for (int i = 0; i < b.length(); i++) {
      dp[0][i] = 0;
    }

    for (int i = 1; i <= a.length(); i++) {
      for (int j = 1; j <= b.length(); j++) {
        if (a.charAt(i - 1) == b.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
          str.append(a.charAt(i - 1));
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    System.out.println(str);
  }

  public static String sortVowels(String s) {
    char[] c = s.toCharArray();

    List<Character> list = new ArrayList<>();

    for (int i = 0; i < c.length; i++) {
      if (c[i] == 'a' || c[i] == 'A' ||
          c[i] == 'e' || c[i] == 'E' || c[i] == 'i' || c[i] == 'I' || c[i] == 'o' || c[i] == 'O'
          || c[i] == 'u' || c[i] == 'U') {
        list.add(c[i]);
      }
    }

    Collections.sort(list);
    int j = 0;

    for (int i = 0; i < c.length; i++) {
      if (c[i] == 'a' || c[i] == 'A' ||
          c[i] == 'e' || c[i] == 'E' || c[i] == 'i' || c[i] == 'I' || c[i] == 'o' || c[i] == 'O'
          || c[i] == 'u' || c[i] == 'U') {
        c[i] = list.get(j);
        j++;
      }
    }
    return String.valueOf(c);
  }

  public static String countAndSay(int n) {
    if (n == 1) {
      return "1";
    }

    String s = countAndSay(n - 1);

    StringBuilder str = new StringBuilder();

    if (n == 1) {
      return "1";
    }

    if (s.length() == 1) {
      return "1" + s;
    }

    int counter = 1;

    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        counter++;
      } else {
        str.append(counter);
        str.append(s.charAt(i));
        counter = 1;
      }
    }

    str.append(counter);
    str.append(s.charAt(s.length() - 1));

    return str.toString();
  }

  public static String countAndSayUtil(int n) {
    String s = String.valueOf(n);

    StringBuilder str = new StringBuilder();

    if (n == 1) {
      return "1";
    }

    if (s.length() == 1) {
      return "1" + s;
    }

    int counter = 1;

    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        counter++;
      } else {
        str.append(counter);
        str.append(s.charAt(i));
        counter = 1;
      }
    }

    str.append(counter);
    str.append(s.charAt(s.length() - 1));

    return str.toString();
  }

  public static int nthUglyNumber(int n) {
    if (n <= 0) {
      return 0;
    }

    int[] uglyNumbers = new int[n];
    uglyNumbers[0] = 1;

    // pointers for multiples
    int i2 = 0, i3 = 0, i5 = 0;

    // updating nextMultiples
    int nextMultipleOf2 = 2, nextMultipleOf3 = 3, nextMultipleOf5 = 5;

    for (int i = 1; i < n; i++) {
      int nextUglyNumber = Math.min(nextMultipleOf2, Math.min(nextMultipleOf3, nextMultipleOf5));
      uglyNumbers[i] = nextUglyNumber;

      if (nextUglyNumber == nextMultipleOf2) {
        i2++;
        nextMultipleOf2 = uglyNumbers[i2] * 2;
      }

      if (nextUglyNumber == nextMultipleOf3) {
        i3++;
        nextMultipleOf3 = uglyNumbers[i3] * 3;
      }

      if (nextUglyNumber == nextMultipleOf5) {
        i5++;
        nextMultipleOf5 = uglyNumbers[i5] * 5;
      }
    }

    return uglyNumbers[n - 1];
  }

  public static boolean wordBreak(String s, List<String> wordDict) {

    StringBuilder str = new StringBuilder(s);

    for (int i = 0; i < wordDict.size(); i++) {
      String word = wordDict.get(i);
      while (str.indexOf(word) != -1) {
        int startPosn = str.indexOf(word);
        int endPosn = startPosn + word.length();
        str.delete(startPosn, endPosn);
      }

    }

    return str.length() == 0 ? true : false;

  }

  public int minSteps(String s, String t) {

    Map<Character, Integer> map1 = new HashMap<>();
    Map<Character, Integer> map2 = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      char key = s.charAt(i);
      boolean hasKey = map1.containsKey(key);

      if (hasKey) {
        map1.put(key, map1.get(key) + 1);
      } else {
        map1.put(key, 1);
      }

    }

    for (int i = 0; i < t.length(); i++) {
      char key = t.charAt(i);
      boolean hasKey = map2.containsKey(key);

      if (hasKey) {
        map2.put(key, map2.get(key) + 1);
      } else {
        map2.put(key, 1);
      }

    }

    int counter = 0;
    for (Character c : map1.keySet()) {
      int map1Res = map1.get(c);
      int map2Res = map2.containsKey(c) ? map2.get(c) : 0;
      int val = Math.abs(map1Res - map2Res);
      counter += val;
      map2.remove(c);
    }

    for (Character c : map2.keySet()) {
      int map2Res = map2.get(c);
      counter += map2Res;
    }

    return counter / 2;
  }


}
