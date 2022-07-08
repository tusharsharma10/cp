package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import trees.Node;

public class Medium {

  /**
   * Input: N = 5, arr[] = {2, 4, 1, 3, 5} Output: 3 Explanation: The sequence 2, 4, 1, 3, 5 has
   * three inversions (2, 1), (4, 1), (4, 3).
   */
  public static long inversionCount(long arr[], long N) {

    int c = 0;
    for (int i = 0; i < arr.length; i++) {

      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[i]) {
          c++;
        }
      }
    }
    return c;
  }

  /**
   * inversion count optimized
   */

  public static long inversionCount2(long arr[], long N) {
    int count = 0;
    int x = mergeSort(arr, 0, arr.length - 1);
    return x;
  }

  public static int mergeSort(long[] arr, int l, int h) {
    int c = 0;
    if (h > l) {
      int mid = (h + l) / 2;
      c += mergeSort(arr, l, mid);
      c += mergeSort(arr, mid + 1, h);
      c += merge(arr, l, mid, h);
    }

    return c;
  }

  private static int merge(long[] arr, int l, int mid, int h) {

    long[] left = new long[mid - l + 1];
    long[] right = new long[h - mid];
    int count = 0;

    int j = 0;
    for (int i = l; i <= mid; i++) {
      left[j] = arr[i];
      j++;
    }

    int k = 0;
    for (int i = mid + 1; i <= h; i++) {
      right[k] = arr[i];
      k++;
    }

    j = 0;
    k = 0;

    int i = l;

    while (j < left.length && k < right.length) {

      if (left[j] > right[k]) {
        arr[i] = right[k];
        count += left.length - j;
        k++;
      } else {
        arr[i] = left[j];
        j++;
      }
      i++;
    }

    while (j < left.length) {
      arr[i] = left[j];
      j++;
      i++;
    }

    while (k < right.length) {
      arr[i] = right[k];
      k++;
      i++;
    }
    return count;
  }


  /**
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ Correctamundo
   */

  public int maxProfit(int[] prices) {

    int maxProfit = 0;

    int minPrice = prices[0];

    for (int i = 1; i < prices.length; i++) {
      int profit = prices[i] - minPrice;
      maxProfit = Math.max(maxProfit, profit);

      if (prices[i] < minPrice) {
        minPrice = prices[i];
      }
    }
    return maxProfit;
  }

  /**
   * https://practice.geeksforgeeks.org/problems/count-pairs-with-given-sum5022/1
   *
   *
   * Given an array of N integers, and an integer K, find the number of pairs of elements in the
   * array whose sum is equal to K.
   */

  public static int getPairsCount(int[] arr, int n, int k) {

    Map<Integer, Integer> map = new HashMap<>();
    int count = 0;

    // Put all arr[i] in the map with their counts
    for (int i = 0; i < arr.length; i++) {

      if (!map.containsKey(arr[i])) {

        map.put(arr[i], 1);
      } else {
        map.put(arr[i], map.get(arr[i]) + 1);
      }

    }

    // traverse the array and add to the count by finding keys, actual count will be count/2
    // since 2 numbers are chosen to make a k and we need count of pairs.
    for (int i = 0; i < arr.length; i++) {
      int key = k - arr[i];

      int mapCount = 0;

      if (map.containsKey(key)) {
        mapCount = map.get(key);

        // edge case when k/2 == key
        if (key == arr[i]) {
          mapCount -= 1;
        }

      }

      count += mapCount;

    }

    return count / 2;

  }

  /**
   * https://practice.geeksforgeeks.org/problems/common-elements1132/1
   *
   * Intsersection of three arrays
   */

  public static ArrayList<Integer> commonElements(int A[], int B[], int C[], int n1, int n2,
      int n3) {
    ArrayList<Integer> ans = new ArrayList<>();

    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < A.length; i++) {
      map.put(A[i], 1);
    }

    for (int i = 0; i < B.length; i++) {
      if (map.containsKey(B[i]) && map.get(B[i]) == 1) {
        map.put(B[i], map.get(B[i]) + 1);
      }
    }

    for (int i = 0; i < C.length; i++) {
      if (map.containsKey(C[i]) && map.get(C[i]) == 2) {
        map.put(C[i], map.get(C[i]) + 1);
      }
    }

    for (int key : map.keySet()) {
      if (map.get(key) == 3) {
        ans.add(key);
      }
    }

    return ans;
  }

  /**
   * https://practice.geeksforgeeks.org/problems/subarray-with-0-sum-1587115621/1 Given an array of
   * positive and negative numbers. Find if there is a subarray (of size at-least one) with 0 sum.
   */

  public static boolean findsum(int arr[], int n) {
    boolean flag = false;
    HashSet<Integer> set = new HashSet<>();

    int sum = 0;

    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];

      if (sum == 0 || set.contains(sum)) {
        return true;
      }
      set.add(sum);
    }

    if (sum == 0) {
      return true;
    }

    return flag;
  }

  /**
   * https://practice.geeksforgeeks.org/problems/maximum-product-subarray3604/1
   */

  public static long maxProduct(int[] arr, int n) {
    long maxProduct = Integer.MIN_VALUE;
    long currProduct = 1;

    for (int i = 0; i < arr.length; i++) {

      currProduct *= arr[i];
      maxProduct = Math.max(maxProduct, currProduct);
      if (currProduct == 0) {
        currProduct = 1;
      }

    }



    /**
     * Why to use this loop
     */
    currProduct = 1;
    for (int i = arr.length - 1; i >= 0; i--) {

      currProduct *= arr[i];
      maxProduct = Math.max(maxProduct, currProduct);

      if (currProduct == 0) {
        currProduct = 1;
      }

    }

    return maxProduct;
  }

  /**
   * https://practice.geeksforgeeks.org/problems/longest-consecutive-subsequence2449/1
   *
   * Find the length of the longest sub-sequence such that elements in the subsequence are consecutive integers
   */

  public static int findLongestConseqSubseq(int arr[], int N) {

    Arrays.sort(arr);
    int count = 1;
    int maxCount = 1;

    for (int i = 0; i < arr.length - 1; i++) {

      int diff = arr[i + 1] - arr[i];

      if (diff == 0) {
        continue;
      }

      if (diff == 1) {
        count += 1;
        maxCount = Math.max(count, maxCount);
      } else {
        count = 1;
      }
    }
    return maxCount;
  }


  /**
   * https://leetcode.com/problems/majority-element-ii/
   *
   * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
   */

  public static List<Integer> majorityElement(int[] nums) {

    int n = nums.length;

    Map<Integer, Integer> map = new HashMap<>();

    List<Integer> ans = new ArrayList<>();

    for (int i = 0; i < n; i++) {

      if (!map.containsKey(nums[i])) {
        map.put(nums[i], 1);
      } else {
        map.put(nums[i], map.get(nums[i]) + 1);
      }

    }

    for (int x : map.keySet()) {
      if (map.get(x) > Math.floor(n / 3)) {
        ans.add(x);
      }
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
   *
   * You are given an array prices where prices[i] is the price of a given stock on the ith day.
   * Find the maximum profit you can achieve. You may complete at most two transactions.
   *
   * Input: prices = [3,3,5,0,0,3,1,4]
   * Output: 6
   * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
   * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
   */

  public static int maxProfit2(int[] prices) {
    return 1;
  }

  /**
   * https://practice.geeksforgeeks.org/problems/array-subset-of-another-array2317/1
   *
   * Given two arrays: a1[0..n-1] of size n and a2[0..m-1] of size m.
   * Task is to check whether a2[] is a subset of a1[] or not. Both the arrays can be sorted or unsorted.
   */

  public static String isSubset( long a1[], long a2[], long n, long m) {

    boolean flag = true;

    Set<Long> set = new HashSet<>();

    for (int i = 0; i < a1.length; i++) {
      set.add(a1[i]);
    }

    for (int i = 0; i < a2.length; i++) {
      if (!set.contains(a2[i])) {
        flag = false;
        break;
      }
    }

    if (flag) {
      return "Yes";
    }

    return "No";
  }

  /**
   * https://practice.geeksforgeeks.org/problems/triplet-sum-in-array-1587115621/1
   *
   * Given an array arr of size n and an integer X.
   * Find if there's a triplet in the array which sums up to the given integer X.
   *
   * Input:
   * n = 6, X = 13
   * arr[] = [1 4 45 6 10 8]
   * Output:1
   *
   * Explanation:
   * The triplet {1, 4, 8} in
   * the array sums up to 13.
   */

  public static boolean find3Numbers(int A[], int n, int X) {

    Arrays.sort(A);

    boolean flag = false;

    for (int i = 0; i < A.length; i++) {

      int j = i + 1;
      int k = A.length - 1;
      int val = X - A[i];
      while (k > j) {

        int currSum = A[j] + A[k];
        if (val > currSum) {
          j++;
        } else if (val < currSum) {
          k--;
        } else {
          return true;
        }
      }

    }

    return flag;
  }

  /**
   *  https://practice.geeksforgeeks.org/problems/smallest-subarray-with-sum-greater-than-x5651/1
   *  Input:
   *  A[] = {1, 4, 45, 6, 0, 19}
   *  x  =  51
   *
   *  Given an array of integers (A[])  and a number x, find the length of smallest subarray with sum greater than the given value.
   */

  public static int smallestSubWithSum(int a[], int n, int x) {

    int len = Integer.MAX_VALUE;

    int right = 0;
    int left = 0;

    int currSum = 0;

    while (right < n || left < n) {

      if (currSum <= x) {

        if (right == n) {
          break;
        }

        currSum += a[right];
        right++;

      } else if (currSum > x) {
        len = Math.min(len, right - left);
        currSum -= a[left];
        left++;
      }

    }

    return len;
  }





  public static void main(String[] args) {
    System.out.println(findLongestConseqSubseq(
        new int[]{6, 6, 2, 3, 1, 4, 1, 5, 6, 2, 8, 7, 4, 2, 1, 3, 4, 5, 9, 6}, 20));
  }
}
