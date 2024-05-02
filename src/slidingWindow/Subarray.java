package slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class Subarray {

  public int numSubarraysWithSum(int[] nums, int goal) {
    return numSubarraysWithSumUtil(nums, goal) - numSubarraysWithSumUtil(nums, goal - 1);
  }

  public int numSubarraysWithSumUtil(int[] nums, int goal) {

    if (goal < 0) {
      return 0;
    }

    int l = 0;
    int r = 0;
    int sum = 0;
    int count = 0;

    while (r < nums.length) {
      sum += nums[r];

      // shrink window since sum > goal
      while (l <= r && sum > goal) {
        sum -= nums[l];
        l++;
      }

      // find count where sum < goal
      count += r - l + 1;
      r++;
    }
    return count;


  }

  public static int maxScore(int[] cardPoints, int k) {

    int leftSum = 0;
    int maxSum = 0;
    int leftIdx = 0;

    for (int i = 0; i < k; i++) {
      leftSum += cardPoints[i];
      maxSum = Math.max(maxSum, leftSum);
      leftIdx++;
    }

    int rightSum = 0;
    int rightIdx = cardPoints.length - 1;
    int netSum = 0;
    leftIdx -= 1;
    while (leftIdx > -1) {
      leftSum -= cardPoints[leftIdx];
      rightSum += cardPoints[rightIdx];
      netSum = leftSum + rightSum;
      rightIdx--;
      leftIdx--;
      maxSum = Math.max(maxSum, netSum);
    }

    return maxSum;

  }

  public static int longestOnes(int[] nums, int k) {
    int left = 0;
    int zeroCount = 0;
    int maxLength = 0;

    for (int right = 0; right < nums.length; right++) {
      if (nums[right] == 0) {
        zeroCount++;
      }

      while (zeroCount > k) {
        if (nums[left] == 0) {
          zeroCount--;
        }
        left++;
      }

      maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
  }


  /**
   * Updating bit: Within the loop, we update the bit variable. We check if the bitwise AND of bit
   * and the current element nums[i] is not equal to 0. If it's not equal to 0, it means there's a
   * common set bit between bit and nums[i], indicating that the current subarray is not nice. In
   * this case, we need to remove elements from the current subarray until there's no common set
   * bit. We do this by XORing bit with the elements starting from the idx until there's no common
   * set bit. This is achieved by the while loop.
   */

  public static int longestNiceSubarray(int[] nums) {
    int bit = 0;
    int res = 0, idx = 0;
    for (int i = 0; i < nums.length; i++) {
      while ((bit & nums[i]) != 0) {
        bit ^= nums[idx++];
      }

      bit |= nums[i];
      res = Math.max(res, i - idx + 1);
    }
    return res;
  }


  public int totalFruit(int[] fruits) {
    int left = 0;
    int maxLength = 0;
    Map<Integer, Integer> fruitCounts = new HashMap<>();

    for (int right = 0; right < fruits.length; right++) {
      fruitCounts.put(fruits[right], fruitCounts.getOrDefault(fruits[right], 0) + 1);

      while (fruitCounts.size() > 2) {
        fruitCounts.put(fruits[left], fruitCounts.get(fruits[left]) - 1);
        if (fruitCounts.get(fruits[left]) == 0) {
          fruitCounts.remove(fruits[left]);
        }
        left++;
      }

      maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
  }

  public static int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> map = new HashMap<>();

    int left = 0;
    int right = 0;
    int len = 0;

    while (right < s.length()) {
      char currentChar = s.charAt(right);
      if (map.containsKey(currentChar)) {
        left = Math.max(left, map.get(currentChar) + 1);
      }
      map.put(currentChar, right);
      len = Math.max(len, right - left + 1);
      right++;
    }
    return len;

  }

  public static int longestSubstring(String s, int k) {
    // Variable to store the length of the longest valid substring found so far
    int maxLen = 0;
    // Length of the input string
    int n = s.length();

    // Iterate over all possible character frequencies as the potential minimum frequency requirement
    for (int numUniqueTarget = 1; numUniqueTarget <= 26; numUniqueTarget++) {
      // Array to store the frequency of each character within the current window
      int[] count = new int[26];
      // Number of unique characters within the current window
      int numUnique = 0;
      int numNoLessThanK = 0; // Number of characters within the current window that appear at least k times
      int left = 0; // Left pointer of the sliding window
      int right = 0; // Right pointer of the sliding window

      // Slide the window from left to right until the end of the string is reached
      while (right < n) {
        // Expand the window by moving the right pointer
        if (numUnique <= numUniqueTarget) {
          int index = s.charAt(right) - 'a';
          if (count[index] == 0) {
            numUnique++; // Increment numUnique if encountering a new character
          }
          count[index]++; // Update the frequency count of the current character
          if (count[index] == k) {
            numNoLessThanK++; // Increment numNoLessThanK if the character count reaches k
          }
          right++; // Move the right pointer to expand the window
        } else { // Shrink the window by moving the left pointer
          int index = s.charAt(left) - 'a';
          if (count[index] == k) {
            numNoLessThanK--; // Decrement numNoLessThanK if a character's count drops below k
          }
          count[index]--; // Update the frequency count of the character leaving the window
          if (count[index] == 0) {
            numUnique--; // Decrement numUnique if a character's count becomes zero
          }
          left++; // Move the left pointer to shrink the window
        }

        // Check if the current window forms a valid substring
        if (numUnique == numUniqueTarget && numUnique == numNoLessThanK) {
          maxLen = Math.max(maxLen,
              right - left); // Update maxLen if a longer valid substring is found
        }
      }
    }

    return maxLen; // Return the length of the longest valid substring
  }

  public int minimumDeletions(int[] nums) {
    return 1;
  }

  public static void main(String[] args) {
    System.out.println(longestSubstring("aaabb", 3));
  }
}
