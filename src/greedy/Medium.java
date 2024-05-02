package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Medium {

  /**
   * https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/
   */

  public static int minPartitions(String n) {

    int len = n.length();

    if (len == 1) {
      return Integer.valueOf(n);
    }

    int ans = n.charAt(0) - '0';

    for (int i = 1; i < len; i++) {
      int currentDigit = n.charAt(i) - '0';
      if (currentDigit > ans) {
        ans = currentDigit;
      }
    }

    return ans;

  }

  /**
   * https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/
   */

  public static int minPairSum(int[] nums) {
    Arrays.sort(nums);
    boolean flag = true;
    int i = 0;
    int j = nums.length - 1;
    int max = Integer.MIN_VALUE;
    while (j > i) {
      max = Math.max(max, nums[i] + nums[j]);
      j--;
      i++;
    }
    return max;
  }

  /**
   * https://leetcode.com/problems/partition-labels/
   */

  public static List<Integer> partitionLabels(String s) {
    Map<Character, Integer> map = new HashMap<>();
    // filling impact of character's
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      map.put(ch, i);
    }
    // making of result
    List<Integer> res = new ArrayList<>();
    int prev = -1;
    int max = 0;

    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      max = Math.max(max, map.get(ch));
      if (max == i) {
        // partition time
        res.add(max - prev);
        prev = max;
      }
    }

    return res;
  }

  /**
   * https://leetcode.com/problems/maximum-number-of-coins-you-can-get/submissions/
   */

  public int maxCoins(int[] piles) {
    Arrays.sort(piles);

    int coins = 0;

    int x = piles.length / 3;

    int j = piles.length - 2;

    for (int i = 0; i < x; i++) {
      coins += piles[j];
      j -= 2;
    }

    return coins;
  }

  public static int largestSumAfterKNegations(int[] nums, int k) {
    int total = 0;
    int minAbs = Integer.MAX_VALUE;

    // Calculate the initial total and find the minimum absolute value
    for (int num : nums) {
      total += Math.abs(num);
      minAbs = Math.min(minAbs, Math.abs(num));
    }

    // If k is even, the result remains the same
    if (k % 2 == 0) {
      return total;
    }
    // If k is odd, subtract twice the minimum absolute value
    else {
      return total - 2 * minAbs;
    }
  }

  public static boolean canJump(int[] nums) {
    int lastIndex = nums.length - 1;

    for (int i = nums.length - 2; i >= 0; i--) {

      if (i + nums[i] >= lastIndex) {
        lastIndex = i;
      }
    }

    return lastIndex == 0;
  }

  /**
   * https://leetcode.com/problems/jump-game-ii/description/
   */
  public static int jump(int[] nums) {

    return 0;
  }

  public static boolean canPlaceFlowers(int[] flowerbed, int n) {
    int len = flowerbed.length;

    if (len == 1 && flowerbed[0] == 0) {
      n = n - 1;
      return n <= 0;
    }

    for (int i = 0; i < flowerbed.length; i++) {

      if (n <= 0) {
        return true;
      }

      if (i == 0 && i < len - 1 && flowerbed[i + 1] == 0 && flowerbed[i] == 0) {
        n = n - 1;
        flowerbed[i] = 1;
      }

      if (i != 0 && i < len - 1 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0
          && flowerbed[i] == 0) {
        n = n - 1;
        flowerbed[i] = 1;
      }

      if (i != 0 && i == len - 1 && flowerbed[i - 1] == 0 && flowerbed[i] == 0) {
        n = n - 1;
        flowerbed[i] = 1;
      }
    }

    return n == 0;

  }

  public static String largestOddNumber(String num) {
    int i = num.length() - 1;

    while (i >= 0) {
      int x = Integer.valueOf(String.valueOf(num.charAt(i)));
      if (x % 2 != 1) {
        num = num.substring(0, i);
      } else {
        return num;
      }
      i--;
    }

    return num;
  }

  /**
   * https://leetcode.com/problems/longest-subsequence-with-limited-sum/
   */

  public static int[] answerQueries(int[] nums, int[] queries) {
    int n = nums.length;
    int m = queries.length;

    Arrays.sort(nums);

    int[] answer = new int[m];

    for (int i = 0; i < m; i++) {
      int targetSum = queries[i];
      int currentSum = 0;
      int right = 0;

      while (right < n && currentSum + nums[right] <= targetSum) {
        currentSum += nums[right];
        right++;
      }

      answer[i] = right;
    }

    return answer;
  }

  public static void main(String[] args) {
    System.out.println(largestOddNumber("35427"));

    TreeSet<Integer> set = new TreeSet<>();
    set.pollFirst();
  }
}
