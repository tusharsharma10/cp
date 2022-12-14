package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Basic {

  public static int findKthLargest(int[] nums, int k) {
    Integer arr[] = new Integer[nums.length];
    for (int i = 0; i < nums.length; i++) {

      arr[i] = nums[i];
    }
    Arrays.sort(arr, Collections.reverseOrder());
    return arr[k - 1];
  }

  /**
   * Given an array which consists of only 0, 1 and 2. Sort the array without using any sorting
   * algo
   */
  public static void sortColors(int[] nums) {
    int cnt0 = 0;
    int cnt1 = 0;
    int cnt2 = 0;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        cnt0++;
      } else if (nums[i] == 1) {
        cnt1++;
      } else {
        cnt2++;
      }
    }

    int j = 0;

    while (cnt0 > 0) {
      nums[j] = 0;
      cnt0--;
      j++;
    }

    while (cnt1 > 0) {
      nums[j] = 1;
      cnt1--;
      j++;
    }

    while (cnt2 > 0) {
      nums[j] = 2;
      cnt2--;
      j++;

    }
  }

  /**
   * First Missing Positive
   */

  public static int firstMissingPositive(int[] nums) {

    HashSet<Integer> set = new HashSet<>();

    for (int i = 0; i < nums.length; i++) {
      set.add(nums[i]);
    }

    int i = 1;

    for (; i <= nums.length; i++) {
      if (!set.contains(i)) {
        return i;
      }
    }
    return i;
  }

  /**
   * https://leetcode.com/problems/missing-number/
   */
  public static int missingNumber(int[] nums) {
    int n = nums.length;
    int max = 0;

    int expectedSum = (n * (n + 1)) / 2;
    int sum = 0;

    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }

    return expectedSum - sum;
  }


  public static int[] twoSum(int[] numbers, int target) {

    int ans[] = new int[2];
    int i = 0;
    while (i < numbers.length) {

      int index = Arrays.binarySearch(numbers, target - numbers[i]);
      if (index > -1 && i != index) {
        ans[0] = i + 1;
        ans[1] = index + 1;
        Arrays.sort(ans);
        return ans;
      }
      i++;
    }

    return ans;

  }



  /*private static int findPivot(int[] nums, int start, int end) {

    int mid = (end - start + 1) / 2;

    if(mid < start ||  mid > end){
      return -1;
    }


      if(nums[mid] > nums[mid-1] && nums[mid+1] > nums[mid]){
      //  findPivot()
      }


  }*/

  private static int recBinSearch(int arr[], int s, int e, int t) {

    int mid = (e + s) / 2;

    if (mid < s || mid > e) {
      return -1;
    }

    if (arr[mid] == t) {
      return mid;
    } else if (arr[mid] > t) {
      return recBinSearch(arr, s, mid - 1, t);
    } else {
      return recBinSearch(arr, mid + 1, e, t);
    }

  }


  /**
   * rotate array 1 by left == rotate array -1 by right == rotate array -1+len right
   */
  public static void rotateArrayRight(int arr[], int posn) {

    if (posn < 0) {
      posn += arr.length;
    }
    posn = posn % arr.length;

    int k = arr.length - posn;

    arrayReverse(arr, 0, k - 1);
    arrayReverse(arr, k, arr.length - 1);
    arrayReverse(arr, 0, arr.length - 1);
  }

  /**
   * Reverse subset of array
   */

  public static void arrayReverse(int arr[], int l, int h) {

    while (h > l) {

      int temp = arr[l];
      arr[l] = arr[h];
      arr[h] = temp;

      l++;
      h--;
    }

  }

  /**
   * Kadanes algo
   */

  public int maxSubArray(int[] nums) {

    int maxSum = Integer.MIN_VALUE;
    int sum = 0;

    for (int i = 0; i < nums.length; i++) {

      sum += nums[i];

      if (sum > maxSum) {
        maxSum = sum;
      }

      if (sum < 0) {
        sum = 0;
      }

    }

    return maxSum;
  }

  /**
   * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/submissions/
   */

  public int removeDuplicates(int[] nums) {

    LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

    for (int i = 0; i < nums.length; i++) {

      if (map.containsKey(nums[i])) {
        int val = map.get(nums[i]) + 1;
        map.put(nums[i], val);
      } else {
        map.put(nums[i], 1);

      }

    }

    int j = 0;
    int cnt = 0;
    for (Map.Entry<Integer, Integer> e : map.entrySet()) {

      if (e.getValue() >= 2) {
        cnt += 2;
        nums[j] = e.getKey();
        nums[j + 1] = nums[j];
        j += 2;
      } else {

        nums[j] = e.getKey();
        cnt++;
        j++;
      }


    }

    return cnt;
  }

  /**
   * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/submissions/
   */

  public static int removeDuplicates2(int[] nums) {

    int pos = 0;
    int currNum = nums[0];
    int count = 0;

    int i = 0;
    while (i < nums.length) {

      // number in array comes first time
      if (currNum != nums[i]) {

        currNum = nums[i];
        nums[pos] = currNum;
        count = 1;
        pos++;
        i++;
      } else {

        // number in array comes 2nd time
        nums[pos] = currNum;
        pos++;
        count++;
        i++;

        // number in array comes more than twice, don't update the pos
        if (count == 2) {

          while (i < nums.length && currNum == nums[i]) {
            i++;
          }
        }

      }

    }

    return pos;
  }

  /**
   * https://leetcode.com/problems/shortest-distance-to-a-character/
   */

  public static int[] shortestToChar(String s, char c) {

    int prev = Integer.MIN_VALUE / 2;
    int[] ans = new int[s.length()];

    for (int i = 0; i < s.length(); i++) {

      if (s.charAt(i) == c) {
        prev = i;
      }

      ans[i] = i - prev;
    }

    prev = Integer.MAX_VALUE / 2;

    for (int i = s.length() - 1; i >= 0; i--) {

      if (s.charAt(i) == c) {
        prev = i;
      }

      ans[i] = Math.min(ans[i], prev - i);
    }

    return ans;
  }

  /**
   * https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1
   */

  public static boolean findPair(int arr[], int size, int n) {
    Arrays.sort(arr);
    int i = 0;
    int j = arr.length - 1;

    while (i < arr.length - 1) {

      boolean f = binSearch(arr, i + 1, arr.length - 1, n + arr[i]);

      if (f) {
        return true;
      }

      i++;
    }

    return false;
  }

  public static boolean binSearch(int[] arr, int low, int high, int k) {

    while (high >= low) {

      int mid = low + (high - low) / 2;

      if (k == arr[mid]) {
        return true;
      } else if (k < arr[mid]) {

        high = mid - 1;
      } else {
        low = mid + 1;
      }

    }

    return false;
  }

  public static int findPairs(int[] nums, int k) {
    Arrays.sort(nums);

    int i = 0;
    int cnt = 0;

    while (i < nums.length - 1) {

      boolean flag = binSearch(nums, i + 1, nums.length - 1, k + nums[i]);

      if (flag) {
        cnt++;
      }

      while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
        i++;
      }

      i++;
    }

    return cnt;
  }

  public static void main(String[] args) {

    int[] arr = {1, 1, 1, 1, 1};

    findPairs(arr, 0);
  }
}
