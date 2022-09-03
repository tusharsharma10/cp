package array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class PrefixSum {


  /**
   * https://leetcode.com/problems/find-pivot-index/
   */
  public static int pivotIndex(int[] nums) {

    int n = nums.length;
    int left[] = new int[n];
    int right[] = new int[n];

    int i = 1;
    int j = n - 2;

    left[0] = nums[0];
    right[n - 1] = nums[n - 1];

    while (i < n && j > -1) {

      left[i] = left[i - 1] + nums[i];
      right[j] = right[j + 1] + nums[j];

      i++;
      j--;
    }

    for (int x = 0; x < n; x++) {

      if (left[x] == right[x]) {
        return x;
      }

    }

    return -1;

  }


  /**
   *
   */
  public static int findMiddleIndex(int[] nums) {
    int n = nums.length;
    int left[] = new int[n];
    int right[] = new int[n];

    int i = 1;
    int j = n - 2;

    left[0] = nums[0];
    right[n - 1] = nums[n - 1];

    while (i < n && j > -1) {

      left[i] = left[i - 1] + nums[i];
      right[j] = right[j + 1] + nums[j];

      i++;
      j--;
    }

    for (int x = 0; x < n; x++) {

      if (left[x] == right[x]) {
        return x;
      }
    }

    return -1;
  }

  public static boolean canThreePartsEqualSum(int[] arr) {

    int sum = 0;
    for (int i : arr) {
      sum += i;
    }
    if (sum % 3 != 0) {
      return false;
    }

    int k = sum / 3;
    sum = 0;
    int count = 0;

    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      if (sum == k) {
        count++;
        sum = 0;
      }
    }
    return count >= 3 ? true : false;
  }


  public static int[] createPrefixSumArr(int nums[]) {

    int ans[] = new int[nums.length];
    ans[0] = nums[0];

    for (int i = 1; i < nums.length; i++) {

      ans[i] = ans[i - 1] + nums[i];
    }

    return ans;
  }

  public static int[] createPrefixSumArrFromBack(int nums[]) {

    int ans[] = new int[nums.length];
    ans[nums.length - 1] = nums[nums.length - 1];

    for (int i = nums.length - 2; i > -1; i--) {

      ans[i] = ans[i + 1] + nums[i];
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/subarray-sum-equals-k/ subarray is contiguous set of elements
   */

  public static int subarraySum(int[] nums, int k) {

    Map<Integer, Integer> mapOfPrefixArray = new HashMap<>();
    int[] prefix = new int[nums.length];
    prefix[0] = nums[0];
    mapOfPrefixArray.put(prefix[0], 1);

    int count = 0;

    if (nums[0] == k) {
      count = 1;
    }

    for (int i = 1; i < nums.length; i++) {
      prefix[i] = prefix[i - 1] + nums[i];

      if (prefix[i] == k) {
        count++;
      }

      if (mapOfPrefixArray.containsKey(prefix[i] - k)) {
        int val = mapOfPrefixArray.get(prefix[i] - k);
        count += val;
      }

      mapOfPrefixArray.put(prefix[i], mapOfPrefixArray.getOrDefault(prefix[i], 0) + 1);

    }

    return count;

  }


  /**
   * https://leetcode.com/problems/continuous-subarray-sum/
   */

  public boolean checkSubarraySumNaive(int[] nums, int k) {

    for (int i = 0; i < nums.length; i++) {
      int prefixSum = nums[i];
      for (int j = i + 1; j < nums.length; j++) {
        prefixSum += nums[j];
        if (prefixSum % k == 0) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * https://leetcode.com/problems/continuous-subarray-sum/submissions/
   */
  public static boolean checkSubarraySum(int[] nums, int k) {

    Map<Integer, Integer> map = new HashMap<>();

    int total = 0;
    map.put(0, -1);

    for (int i = 0; i < nums.length; i++) {

      total += nums[i];
      int rem = total % k;

      if (!map.containsKey(rem)) {
        map.put(rem, i);
      } else if (i - map.get(rem) > 1) {
        return true;
      }
    }

    return false;
  }

  /*private static int getKey(int a, int n, int k) {

    return (a - n) % k;
  }*/

  /**
   * https://leetcode.com/problems/max-consecutive-ones-iii/
   */

  public static int longestOnes(int[] arr, int k) {
    int i = 0;
    int j = 0;

    int countz = 0;
    int result = 0;

    while (i < arr.length) {
      if (arr[i] == 0) {
        countz++;
      }

      while (countz > k) {
        if (arr[j] == 0) {
          countz--;
        }
        j++;
      }

      int len = i - j + 1;

      result = Math.max(result, len);

      i++;
    }

    return result;
  }


  /**
   * https://leetcode.com/problems/maximize-the-confusion-of-an-exam/
   */

  public static int maxConsecutiveAnswers(String answerKey, int k) {
    return 1;
  }


  public static void main(String[] args) {
    System.out.println(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
  }

}
