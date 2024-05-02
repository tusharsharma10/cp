package slidingWindow.striver;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Questions {

  /**
   * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
   */

  public static int maxScore(int[] cardPoints, int k) {
    int leftSum = 0;
    int rightSum = 0;

    int left = 0;
    int right = cardPoints.length - 1;

    for (int i = left; i < k; i++) {
      leftSum += cardPoints[i];
      left++;
    }

    int maxSum = leftSum;

    for (int i = 0; i < k; i++) {
      leftSum -= cardPoints[k - i - 1];
      rightSum += cardPoints[right - i];
      int tempSum = leftSum + rightSum;
      maxSum = Math.max(maxSum, tempSum);
    }

    return maxSum;
  }

  /**
   * https://leetcode.com/problems/longest-substring-without-repeating-characters/
   */

  public static int longestSubstringWithoutRepeatingChars(String s) {
    int left = 0;
    int right = 0;

    int maxLength = 0;

    Set<Character> set = new HashSet<>();

    while (right < s.length()) {
      char c = s.charAt(right);
      if (!set.contains(c)) {
        set.add(c);
        right++;
        maxLength = Math.max(maxLength, set.size());
      } else {
        set.remove(s.charAt(left));
        left++;
      }
    }
    return maxLength;
  }

  /**
   * https://leetcode.com/problems/max-consecutive-ones-iii/
   */

  public static int maxConsecutiveOnes(int[] nums, int k) {
    int left = 0;
    int right = 0;
    int len = 0;
    int numZeros = 0;

    while (right < nums.length) {
      // Expand the window while the number of zeros is less than or equal to k
      if (nums[right] == 0) {
        numZeros++;
      }
      while (numZeros > k) {
        if (nums[left] == 0) {
          numZeros--;
        }
        left++;
      }
      // Update the maximum length
      len = Math.max(len, right - left + 1);
      right++;
    }
    return len;
  }

  /**
   * https://www.naukri.com/code360/problems/fruits-and-baskets_985356?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
   *
   *
   * Two baskets each basket can contain one type of fruit
   */


  public static int findMaxFruits(int[] arr, int n) {
    Map<Integer, Integer> map = new HashMap<>();
    int left = 0;
    int right = 0;
    int len = 0;

    while (right < n) {
      int type = arr[right];
      map.put(type, map.getOrDefault(type, 0) + 1);

      while (map.size() > 2) {
        if (map.get(arr[left]) > 0) {
          map.put(arr[left], map.get(arr[left]) - 1);
        }

        if (map.get(arr[left]) == 0) {
          map.remove(arr[left]);
        }
        left++;
      }

      len = Math.max(len, right - left + 1);
      right++;
    }
    return len;
  }

  /**
   * https://www.naukri.com/code360/problems/longest-substring-with-at-most-k-distinct-characters_2221410?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
   * Longest Substring with At Most K Distinct Characters
   */

  public static int kDistinctChars(int k, String str) {
    Map<Character, Integer> map = new HashMap<>();
    int left = 0;
    int right = 0;
    int n = str.length();
    int len = 0;

    while (right < n) {
      char c = str.charAt(right);
      map.put(c, map.getOrDefault(c, 0) + 1);

      while (map.size() > k) {
        char x = str.charAt(left);
        map.put(x, map.get(x) - 1);

        if (map.get(x) == 0) {
          map.remove(x);
        }
        left++;
      }

      len = Math.max(len, right - left + 1);
      right++;
    }

    return len;
  }


  /**
   * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/ Number of
   * Substrings Containing All Three Characters
   */

  public static int numberOfSubstrings(String s) {
    int[] lastseen = new int[]{-1, -1, -1};
    int n = s.length();
    int count = 0;
    for (int i = 0; i < n; i++) {
      lastseen[s.charAt(i) - 'a'] = i;
      count += 1 + Math.min(lastseen[0], Math.min(lastseen[1], lastseen[2]));
    }
    return count;

  }


  /**
   * Will lead to TLE
   */
  public static int numberOfSubstringsNaive(String s) {
    int n = s.length();
    int count = 0;

    for (int i = 0; i < n; i++) {
      Set<Character> set = new HashSet<>();
      for (int j = i; j < n; j++) {
        set.add(s.charAt(j));
        if (set.size() == 3) {
          count++;
        }
      }
    }
    return count;
  }

  /**
   * https://leetcode.com/problems/longest-repeating-character-replacement/
   *
   * Longest Repeating Character Replacement
   */

  public static int characterReplacement(String s, int k) {
    int maxFreq = 0;
    int len = 0;
    int n = s.length();
    int left = 0;
    int right = 0;
    int maxLength = 0;
    Map<Character, Integer> map = new HashMap<>();

    while (right < n) {
      char curr = s.charAt(right);
      map.put(curr, map.getOrDefault(curr, 0) + 1);
      maxFreq = Math.max(maxFreq, map.get(curr));
      len = right - left + 1;

      if (len - maxFreq > k) {
        char currLeft = s.charAt(left);
        map.put(currLeft, map.get(currLeft) - 1);
        if (map.get(currLeft) == 0) {
          map.remove(currLeft);
        }
        left++;
      }

      maxLength = Math.max(maxLength, right - left + 1);
      right++;
    }
    return maxLength;
  }

  /**
   * https://leetcode.com/problems/binary-subarrays-with-sum/
   *
   * Binary Subarrays With Sum
   */


  /**
   * Finds Num of subarrays where sum is <= Goal
   */
  public static int numSubarraysWithSumLessThanEqualToGoal(int[] nums, int goal) {
    int currSum = 0;
    int left = 0;
    int right = 0;
    int n = nums.length;
    int count = 0;

    if (goal < 0) {
      return 0;
    }

    while (right < n) {
      currSum += nums[right];

      while (currSum > goal) {
        currSum -= nums[left];
        left++;
      }

      count += right - left + 1;
      right++;
    }

    return count;
  }

  public static int numSubarraysWithSum(int[] nums, int goal) {
    return numSubarraysWithSumLessThanEqualToGoal(nums, goal)
        - numSubarraysWithSumLessThanEqualToGoal(nums, goal - 1);
  }

  /**
   * https://leetcode.com/problems/count-number-of-nice-subarrays/
   *
   * Count Number of Nice Subarrays
   */

  public int numberOfNiceSubarraysUtil(int[] nums, int k) {
    int oddCount = 0;
    int subarrayCount = 0;
    int left = 0;
    int right = 0;
    int n = nums.length;

    while (right < n) {
      if (nums[right] % 2 == 1) {
        oddCount++;
      }

      while (oddCount > k) {
        if (nums[left] % 2 == 1) {
          oddCount -= 1;
        }
        left++;
      }

      subarrayCount += right - left + 1;
      right++;
    }
    return subarrayCount;
  }

  public int numberOfNiceSubarrays(int[] nums, int k) {
    return numberOfNiceSubarraysUtil(nums, k) - numberOfNiceSubarraysUtil(nums, k - 1);
  }

  public static void main(String[] args) {
    System.out.println(numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
  }


}

