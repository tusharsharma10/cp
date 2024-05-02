package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Medium {

  /**
   * https://leetcode.com/problems/count-sorted-vowel-strings/
   */

  public static int countVowelStrings(int n) {

    int[] dpA = new int[n];
    int[] dpE = new int[n];
    int[] dpI = new int[n];
    int[] dpO = new int[n];
    int[] dpU = new int[n];
    dpA[0] = 1;
    dpE[0] = 1;
    dpI[0] = 1;
    dpO[0] = 1;
    dpU[0] = 1;

    int res = 5;
    for (int i = 1; i < n; i++) {
      dpA[i] = dpA[i - 1];
      dpE[i] = dpE[i - 1] + dpA[i];
      dpI[i] = dpE[i] + dpI[i - 1];
      dpO[i] = dpI[i] + dpO[i - 1];
      dpU[i] = dpO[i] + dpU[i - 1];

      if (i == n - 1) {
        res = dpA[i] + dpE[i] + dpI[i] + dpO[i] + dpU[i];
      }
    }

    return res;
  }

  /**
   * https://leetcode.com/problems/sort-integers-by-the-power-value/
   * The power of an integer x is defined as the number of steps needed to transform x into 1 using the following steps:
   *
   *     if x is even then x = x / 2
   *     if x is odd then x = 3 * x + 1
   *
   * For example, the power of x = 3 is 7 because 3 needs 7 steps to become 1 (3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1).
   */

  public static int getKth(int lo, int hi, int k) {

    Map<Integer, List<Integer>> map = new TreeMap<>();

    for (int i = lo; i <= hi; i++) {

      int steps = getSteps(i);
      if (!map.containsKey(steps)) {
        List<Integer> l = new LinkedList<>();
        l.add(i);
        map.put(steps, l);
      } else {
        List<Integer> l = map.get(steps);
        l.add(i);
        map.put(steps, l);
      }

    }

    int currPosn = 0;
    for (Entry<Integer, List<Integer>> e : map.entrySet()) {

      List<Integer> l = e.getValue();
      int listSize = l.size();


      if (currPosn + listSize > k) {
        int diff = k - currPosn - 1;
        return l.get(diff);
      } else if (currPosn + listSize == k) {
        return l.get(listSize - 1);
      }

      currPosn += listSize;
    }

    return -1;
  }

  private static int getSteps(int num) {

    int steps = 0;

    while (num != 1) {

      if (num % 2 == 0) {
        num /= 2;
      } else {
        num = num * 3 + 1;
      }

      steps++;
    }

    return steps;
  }


  /**
   * https://leetcode.com/problems/number-of-good-ways-to-split-a-string/
   * You are given a string s.
   *
   * A split is called good if you can split s into two non-empty strings sleft and
   * sright where their concatenation is equal to s (i.e., sleft + sright = s) and the
   * number of distinct letters in sleft and sright is the same.
   *
   * Return the number of good splits you can make in s.
   */

  public static int numSplits(String s) {

    Set<Character> set = new HashSet<>();
    int[] prefixArrFront = new int[s.length()];
    int[] prefixArrBack = new int[s.length()];
    prefixArrFront[0] = 1;
    set.add(s.charAt(0));

    for (int i = 1; i < s.length(); i++) {

      if (!set.contains(s.charAt(i))) {
        prefixArrFront[i] = 1 + prefixArrFront[i - 1];
      } else {
        prefixArrFront[i] = prefixArrFront[i - 1];
      }

      set.add(s.charAt(i));
    }

    set.removeAll(set);
    prefixArrBack[s.length() - 1] = 1;
    set.add(s.charAt(s.length() - 1));
    for (int i = s.length() - 2; i >= 0; i--) {

      if (!set.contains(s.charAt(i))) {
        prefixArrBack[i] = 1 + prefixArrBack[i + 1];
      } else {
        prefixArrBack[i] = prefixArrBack[i + 1];
      }
      set.add(s.charAt(i));
    }

    int count = 0;
    for (int i = 0; i < prefixArrFront.length - 1; i++) {
      if (prefixArrFront[i] == prefixArrBack[i + 1]) {
        count++;
      }
    }

    return count;
  }



  public static int buyAndSellStocks(int[] prices) {

    int buyPosn = 0;
    int sellPosn = 0;

    int profit = 0;

    for (int i = 1; i < prices.length; i++) {

      if (prices[i] >= prices[i - 1]) {
        sellPosn++;
      } else {
        profit += prices[sellPosn] - prices[buyPosn];
        buyPosn = i;
        sellPosn = i;
      }

    }

    profit += prices[sellPosn] - prices[buyPosn];
    return profit;
  }



  public static int maxProfit2(int[] prices, int fee) {

    int n = prices.length;

    int[][] dp = new int[n + 1][2];

    for (int i = n - 1; i >= 0; i--) {
      dp[i][1] = Math.max(dp[i + 1][0] - prices[i], dp[i + 1][1]);
      dp[i][0] = Math.max(dp[i + 1][1] + prices[i] - fee, dp[i + 1][0]);
    }

    return dp[0][1];
  }

  /**
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
   */
  public static int maxProfit(int[] prices, int fee) {

    int n = prices.length;

    int[][] dp = new int[n + 1][2];
    dp[0][0] = -prices[0];
    dp[0][1] = 0;
    for (int i = 0; i < n; i++) {
      // extra stock bought state
      dp[i + 1][0] = Math.max(dp[i][1] - prices[i], dp[i][0]);
      // buy sell settled state
      dp[i + 1][1] = Math.max(prices[i] + dp[i][0] - fee, dp[i][1]);

    }

    return dp[n][1];
  }

  /**
   * https://leetcode.com/problems/longest-increasing-subsequence/
   */

  public static int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n][n];

    int maxCount = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {
      int localMax = 1 + recLis(nums, i + 1, n-1, i);
      maxCount = Math.max(maxCount, localMax);
    }
    return maxCount;
  }

  private static int recLis(int[] nums, int currPosn, int size,int lastChosenPosn) {

    if (size < currPosn) {
      return 0;
    }

    if (nums[lastChosenPosn] >= nums[currPosn]) {
      return recLis(nums, currPosn + 1, size, lastChosenPosn);
    }

    int choice1 = recLis(nums, currPosn + 1, size, lastChosenPosn);
    int choice2 = recLis(nums, currPosn + 1, size, currPosn) + 1;

    return Math.max(choice1, choice2);
  }

  public int lengthOfLISDp(int[] nums) {
    int[] maxLengths = new int[nums.length];
    Arrays.fill(maxLengths, 1);

    int max = 1;
    // for each cell
    for(int i = 1; i < nums.length; i++) {
      // add upon previous cells, find max length
      for(int j = 0; j < i; j++) {
        // only nums[j] < nums[i] works
        if(nums[i] <= nums[j]) {
          continue;
        }

        maxLengths[i] = Math.max(maxLengths[i], maxLengths[j] + 1);
        max = Math.max(max, maxLengths[i]);
      }
    }

    return max;
  }

  /**
   * https://leetcode.com/problems/increasing-triplet-subsequence/
   */

  public boolean increasingTriplet(int[] nums) {
    int left = Integer.MAX_VALUE;
    int mid = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > mid) {
        return true;
      } else if (nums[i] < left) {
        left = nums[i];
      } else if (nums[i] > left && nums[i] < mid) {
        mid = nums[i];
      }
    }
    return false;
  }

  public int[] intersect(int[] nums1, int[] nums2) {
    Map<Integer, Integer> map1 = new HashMap<>();

    Map<Integer, Integer> map2 = new HashMap<>();

    for (int i = 0; i < nums1.length; i++) {
      if (!map1.containsKey(nums1[i])) {
        map1.put(nums1[i], 1);
      } else {
        map1.put(nums1[i], 1 + map1.get(nums1[i]));
      }
    }

    for (int i = 0; i < nums2.length; i++) {
      if (!map2.containsKey(nums2[i])) {
        map2.put(nums2[i], 1);
      } else {
        map2.put(nums2[i], 1 + map2.get(nums2[i]));
      }
    }

    List<Integer> l = new ArrayList<>();

    for (Map.Entry<Integer, Integer> e : map1.entrySet()) {

      if (map2.containsKey(e.getKey())) {
        int min = Math.min(e.getValue(), map2.get(e.getKey()));
        for (int i = 1; i <= min; i++) {
          l.add(e.getKey());
        }
      }

    }

    int[] ans = new int[l.size()];

    for (int i = 0; i < ans.length; i++) {
      ans[i] = l.get(i);
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/decode-ways/
   */

  public int numDecodings(String s) {
  return 1;
  }

  public static void main(String[] args) {
    //System.out.println(maxProfit2(new int[]{10,15,17,20,16,18,22,20,22,20,23,25},2));

    System.out.println(lengthOfLIS(new int[]{0,1,0,3,2,3}));

  }

}
