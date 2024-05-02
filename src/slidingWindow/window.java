package slidingWindow;

import java.util.HashSet;
import java.util.Set;

public class window {

  public static void main(String[] args) {
    /*System.out.println(minSubArrayLen(7, new int[]{
        2, 3, 1, 2, 4, 3
    }));*/

    //System.out.println(lengthOfLongestSubstring("pwwkew"));

    System.out.println(characterReplacement("AABABBA", 1));
  }

  /**
   * https://leetcode.com/problems/longest-repeating-character-replacement/
   */
  public static int characterReplacement(String s, int k) {
    int[] charCount = new int[26];
    // maxCount to keep track of the maximum count of any character in the current window.
    int maxCount = 0;
    // maxLength to keep track of the length of the longest substring with the same character.
    int maxLength = 0;
    int start = 0;

    for (int end = 0; end < s.length(); end++) {
      charCount[s.charAt(end) - 'A']++;
      maxCount = Math.max(maxCount, charCount[s.charAt(end) - 'A']);

      // window size - maxCount > k => means the number of characters that forms the minority group are greater than k hence change the window
      if (end - start + 1 - maxCount > k) {
        charCount[s.charAt(start) - 'A']--;
        start++;
      }

      maxLength = Math.max(maxLength, end - start + 1);
    }

    return maxLength;
  }

  public static int lengthOfLongestSubstring(String s) {
    int n = s.length();
    int maxLength = 0;
    int left = 0;
    int right = 0;
    Set<Character> charSet = new HashSet<>();

    while (right < n) {
      if (!charSet.contains(s.charAt(right))) {
        charSet.add(s.charAt(right));
        maxLength = Math.max(maxLength, right - left + 1);
        right++;
      } else {
        charSet.remove(s.charAt(left));
        left++;
      }
    }

    return maxLength;
  }

  public static int minSubArrayLen(int target, int[] nums) {

    int left = 0;
    int right = 0;
    int minLength = Integer.MAX_VALUE;
    int sum = 0;

    while (left <= right) {

      if (sum < target) {
        sum += nums[right];
        right++;

      } else {
        minLength = Math.min(minLength, right - left + 1);
        sum = sum - nums[left];
        left++;

      }

    }

    if (minLength == Integer.MAX_VALUE) {
      return 0;
    }

    return minLength;
  }


}
