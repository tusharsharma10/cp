package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  public static void main(String[] args) {
    minPartitions("82734");
  }
}
