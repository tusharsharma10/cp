package array;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
   * Find the length of the longest sub-sequence such that elements in the subsequence are
   * consecutive integers
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
   * Input: prices = [3,3,5,0,0,3,1,4] Output: 6 Explanation: Buy on day 4 (price = 0) and sell on
   * day 6 (price = 3), profit = 3-0 = 3. Then buy on day 7 (price = 1) and sell on day 8 (price =
   * 4), profit = 4-1 = 3.
   */

  public static int maxProfit2(int[] prices) {
    return 1;
  }

  /**
   * https://practice.geeksforgeeks.org/problems/array-subset-of-another-array2317/1
   *
   * Given two arrays: a1[0..n-1] of size n and a2[0..m-1] of size m. Task is to check whether a2[]
   * is a subset of a1[] or not. Both the arrays can be sorted or unsorted.
   */

  public static String isSubset(long a1[], long a2[], long n, long m) {

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
   * Given an array arr of size n and an integer X. Find if there's a triplet in the array which
   * sums up to the given integer X.
   *
   * Input: n = 6, X = 13 arr[] = [1 4 45 6 10 8] Output:1
   *
   * Explanation: The triplet {1, 4, 8} in the array sums up to 13.
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
   * https://practice.geeksforgeeks.org/problems/smallest-subarray-with-sum-greater-than-x5651/1
   * Input: A[] = {1, 4, 45, 6, 0, 19} x  =  51
   *
   * Given an array of integers (A[])  and a number x, find the length of smallest subarray with sum
   * greater than the given value.
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


  /**
   * https://leetcode.com/problems/longest-consecutive-sequence/
   */

  public static int longestConsecutive(int[] nums) {

    if (nums.length == 0) {
      return 0;
    }

    Set<Integer> set = new TreeSet<>();

    for (int x : nums) {
      set.add(x);
    }

    int ans = 1;

    int len = 1;
    int prev = Integer.MIN_VALUE;

    for (int x : set) {

      if (prev != Integer.MIN_VALUE && x == prev + 1) {
        len++;
        ans = Math.max(ans, len);
      } else {
        len = 1;
      }
      prev = x;
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/perfect-squares/
   */

  public static int numSquares(int n) {

    int start = (int) Math.sqrt(n);
    int[] dp = new int[n + 1];

    for (int i = 1; i <= start; i++) {
      // nearby squares 1,4,9......
      int square = i * i;

      for (int j = square; j <= n; j++) {
        // base case
        if (i == 1) {
          dp[j] = j;
          continue;
        }
        //
        dp[j] = Math.min(dp[j], 1 + dp[j - square]);
      }
    }
    return dp[n];
  }


  /**
   * https://leetcode.com/problems/single-number/
   *
   * XOR If input bits are the same, then the output will be false(0) else true(1). XOR table: X.
   */

  public static int singleNumber(int[] nums) {
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum = sum ^ nums[i];
    }
    return sum;
  }

  /**
   * https://leetcode.com/problems/4sum-ii/ Given four integer arrays nums1, nums2, nums3, and nums4
   * all of length n, return the number of tuples (i, j, k, l) such that:
   *
   * 0 <= i, j, k, l < n nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
   */

  public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

    int n = nums1.length;
    if (n == 0) {
      return 0;
    }
    int res = 0;

    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        map.put(nums1[i] + nums2[j], 1 + map.getOrDefault(nums1[i] + nums2[j], 0));
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        res += map.getOrDefault(-1 * (nums3[i] + nums4[j]), 0);
      }
    }
    return res;

  }

  /**
   * https://leetcode.com/problems/4sum/
   */


  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    ksum(4, nums, 0, new ArrayList<>(), target + 0, ans);
    return ans;
  }

  private void ksum(int k, int[] nums, int startingIndex, List<Integer> list, long target,
      List<List<Integer>> ans) {
    if (k == 2) {
      int l = startingIndex;
      int r = nums.length - 1;
      while (l < r) {
        int sum = nums[l] + nums[r];
        if (sum < target) {
          l++;
        } else if (sum > target) {
          r--;
        } else {
          List<Integer> temp = new ArrayList<>();
          for (int j : list) {
            temp.add(j);
          }
          temp.add(nums[l]);
          temp.add(nums[r]);
          ans.add(temp);
          l++;
          while (l < r && nums[l] == nums[l - 1]) {
            l++;
          }
        }
      }
      return;
    }

    // for k = 3 j = 0 to j <= len - 3
    for (int j = startingIndex; j <= nums.length - k; j++) {
      if (j != startingIndex && nums[j] == nums[j - 1]) {
        continue;
      }
      list.add(nums[j]);
      ksum(k - 1, nums, j + 1, list, target - nums[j], ans);
      //backtrack
      list.remove(list.size() - 1);
    }
  }

  /**
   * https://leetcode.com/problems/longest-common-prefix/submissions/
   */

  public static String longestCommonPrefix(String[] strs) {

    if (strs.length == 0) {
      return "";
    }

    if (strs.length == 1) {
      return strs[0];
    }

    String ans = "";
    int minLen = Integer.MAX_VALUE;

    int max = 0;

    for (int i = 0; i < strs.length; i++) {
      minLen = Math.min(minLen, strs[i].length());
      ;
    }

    int localMax = 0;
    String localAns = "";

    for (int i = 0; i < minLen; i++) {
      char c = strs[0].charAt(i);
      boolean flag = true;

      for (String s : strs) {
        if (s.charAt(i) == c) {
          continue;
        } else {
          flag = false;
          break;
        }
      }

      if (flag) {
//        localMax = localMax + 1;
//        localAns += String.valueOf(c);
        ans += c;
      }

      if (!flag) {

       /* if (max <= localMax) {
          max = localMax;
          ans = localAns;
        }
        localMax = 0;
        localAns = "";*/

        break;
      }

    }

   /* if (max <= localMax) {
      max = localMax;
      ans = localAns;
    }*/

    return ans;
  }

  /**
   * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
   */

  public int strStr(String haystack, String needle) {
    return haystack.indexOf(needle);
  }

  /**
   * https://leetcode.com/problems/divide-two-integers/
   */

  public static int divide(int dividend, int divisor) {

    if (dividend >= Integer.MAX_VALUE) {
      dividend = Integer.MAX_VALUE;
    }

    if (dividend <= Integer.MIN_VALUE) {
      dividend = Integer.MIN_VALUE;
    }

    if (dividend == Integer.MIN_VALUE && divisor == -1) {
      return Integer.MAX_VALUE;
    }

    int ans = dividend / divisor;

    return ans;
  }


  /**
   * https://leetcode.com/problems/plus-one/submissions/
   */

  public int[] plusOne(int[] digits) {
    int posn = digits.length;
    List<Integer> ans = new ArrayList<>();
    ans.add(0);

    for (int x : digits) {
      ans.add(x);
    }

    while (ans.get(posn) == 9) {

      ans.set(posn, 0);
      posn--;
    }

    ans.set(posn, 1 + ans.get(posn));

    if (ans.get(0) == 0) {
      int[] arr = new int[ans.size() - 1];
      for (int i = 0; i < arr.length; i++) {
        arr[i] = ans.get(i + 1);
      }
      return arr;
    } else {

      int[] arr = new int[ans.size()];

      for (int i = 0; i < arr.length; i++) {
        arr[i] = ans.get(i);
      }
      return arr;
    }

  }

  /**
   * https://leetcode.com/problems/permutations/
   */

  public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> s = new ArrayList<>();
    for (int x : nums) {
      s.add(x);
    }
    Set<List<Integer>> set = new HashSet<>();
    permuteRec(s, new ArrayList<>(), set);
    for (List<Integer> x : set) {
      ans.add(x);
    }
    return ans;
  }

  private static void permuteRec(List<Integer> s, List<Integer> s1, Set<List<Integer>> set) {

    if (s.size() == 0) {
      List<Integer> temp = new ArrayList<>(s1);
      set.add(temp);
      return;
    }

    for (int i = 0; i < s.size(); i++) {
      int x = s.get(i);
      List<Integer> left = s.subList(0, i);
      List<Integer> right = s.subList(i + 1, s.size());
      List<Integer> combine = new ArrayList<>();
      combine.addAll(left);
      combine.addAll(right);
      s1.add(x);
      permuteRec(combine, s1, set);
      s1.remove(s1.size() - 1);
    }

  }

  /**
   * https://leetcode.com/problems/combinations/
   */

  public static List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    combinations(n, k, ans, temp);
    return ans;
  }

  public static void combinations(int n, int k, List<List<Integer>> ans, List<Integer> temp) {

    if (k == 0) {
      ans.add(new ArrayList(temp));
      return;
    }

    if (n == 0) {
      return;
    }

//         We are not taking n
    combinations(n - 1, k, ans, temp);

//         We are taking n
    temp.add(n);
    combinations(n - 1, k - 1, ans, temp);
    temp.remove(temp.size() - 1);
  }


  /**
   * https://leetcode.com/problems/set-matrix-zeroes/
   *
   * this uses some weird trick hence is not intuitive, best is o(m+n) solution
   */

  public static void setZeroes(int[][] matrix) {

    boolean isCol = false;

    for (int i = 0; i < matrix.length; i++) {

      if (matrix[i][0] == 0) {
        isCol = true;
      }

      for (int j = 1; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }

    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix[0].length; j++) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) {
          matrix[i][j] = 0;
        }
      }
    }

    if (matrix[0][0] == 0) {
      // make the columns 0 for row = 0
      for (int i = 0; i < matrix[0].length; i++) {
        matrix[0][i] = 0;
      }
    }

    if (isCol == true) {
      for (int i = 0; i < matrix.length; i++) {
        matrix[i][0] = 0;
      }
    }

  }

  /**
   * https://leetcode.com/problems/third-maximum-number/
   */

  public static int thirdMax(int[] nums) {

    Map<Integer, Integer> map = new HashMap<>();

    for (int x : nums) {
      map.put(x, 1 + map.getOrDefault(x, 0));
    }

    if (map.size() < 3) {
      return findMax(nums);
    }

    int max1 = Integer.MIN_VALUE;
    int max2 = Integer.MIN_VALUE;
    int max3 = Integer.MIN_VALUE;

    for (int i = 0; i < nums.length; i++) {
      int currNum = nums[i];

      if (currNum > max1 && currNum > max2 && currNum > max3) {
        max3 = max2;
        max2 = max1;
        max1 = currNum;
      }

      if (currNum < max1 && currNum > max2 && currNum > max3) {
        max3 = max2;
        max2 = currNum;
      }

      if (currNum < max1 && currNum < max2 && currNum > max3) {
        max3 = currNum;
      }

    }

    return max3;
  }

  /**
   * https://leetcode.com/problems/add-strings/
   */

  public String addStrings(String num1, String num2) {

    BigInteger b1 = new BigInteger(num1);
    BigInteger b2 = new BigInteger(num2);

    BigInteger sum = b1.add(b2);

    return sum.toString();
  }

  private static int findMax(int[] nums) {

    int max = Integer.MIN_VALUE;

    for (int x : nums) {
      max = Math.max(max, x);
    }

    return max;
  }

  public static String reversePrefix(String word, char ch) {

    int idx = word.indexOf(ch);

    String s1 = word.substring(0, idx + 1);
    StringBuilder str = new StringBuilder(s1);
    str.reverse();
    s1 = str.toString();
    String s2 = word.substring(idx + 1);

    return s1 + s2;
  }


  public static void main(String[] args) {
    System.out.println(reversePrefix("abcdefd", 'd'));
  }

}
