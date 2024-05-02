package binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class X {

  private static int digitSum(int n) {
    int sum = 0;
    while (n != 0) {
      sum += n % 10;
      n /= 10;
    }
    return sum;
  }

  public static int smallestIntegerWithDigitSum(int n) {
    for (int i = 1; i <= n * 9; i++) {
      if (digitSum(i) == n) {
        return i;
      }
    }
    return -1;
  }

  public static int smallestIntegerWithDigitSum2(int n) {
    if (n <= 9) {
      return n;
    }

    StringBuilder sb = new StringBuilder();
    int remainingSum = n;

    while (remainingSum > 9) {
      sb.append(9);
      remainingSum -= 9;
    }

    sb.append(remainingSum);

    return Integer.parseInt(sb.reverse().toString());
  }

  public static int pow(int x, int n, int d) {

    int num = powRec(x, n);
    int a = 0;
    if (num < 0) {
      a = num % d;
      a += d;
      return a % d;
    }
    return num % d;
  }

  public static int powRec(int x, int n) {
    if (n == 0) {
      return 1;
    }

    return x * powRec(x, n - 1);
  }


  public static int minDistance(String A, String B) {
    int m = A.length();
    int n = B.length();
    return minDistanceRec(A, B, m, n);
  }

  private static int minDistanceRec(String a, String b, int m, int n) {
    if (m == 0 && n == 0) {
      return 0;
    }

    if (m == 0 && n != 0 || n == 0 && m != 0) {
      return Integer.MAX_VALUE;
    }

    int c1 = Integer.MAX_VALUE;
    int c2 = Integer.MAX_VALUE;
    if (a.charAt(m - 1) == b.charAt(n - 1)) {
      c1 = minDistanceRec(a, b, m - 1, n - 1);
    } else {
      c2 = 1 + Math.min(minDistanceRec(a, b, m, n - 1),
          Math.min(minDistanceRec(a, b, m - 1, n), minDistanceRec(a, b, m - 1, n - 1)));
    }

    int ans = Math.min(c1, c2);
    return ans;
  }

  private static int minDistanceTab(String a, String b) {
    int m = a.length();
    int n = b.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
      dp[i][0] = i;
    }

    for (int j = 0; j <= n; j++) {
      dp[0][j] = j;
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (a.charAt(i - 1) == b.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
        }
      }
    }

    return dp[m][n];
  }


  public static int longestConsecutive(final List<Integer> A) {

    TreeSet<Integer> set = new TreeSet<>();

    set.addAll(A);

    int prev = set.first();
    int maxCount = 1;
    int count = 1;
    for (int x : set) {
      if (x == prev) {
        continue;
      }
      if (prev == x - 1) {
        count++;
      } else {
        count = 1;
      }
      prev = x;
      maxCount = Math.max(maxCount, count);
    }
    return maxCount;
  }


  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    ksum(4, 0, 0, nums, new ArrayList<>(), ans);
    return ans;
  }

  private void ksum(int k, int startingIndex, long target, int[] nums, List<Integer> list,
      List<List<Integer>> ans) {

    if (k == 2) {
      twoSum(startingIndex, target, nums, list, ans);
      return;
    }

    // for k = 3 j = 0 to j <= len - 3
    for (int j = startingIndex; j <= nums.length - k; j++) {
      if (j != startingIndex && nums[j] == nums[j - 1]) {
        continue;
      }
      list.add(nums[j]);
      ksum(k - 1, j + 1, target - nums[j], nums, list, ans);
      //backtrack
      list.remove(list.size() - 1);
    }
  }

  private void twoSum(int startingIndex, long target, int[] nums, List<Integer> list,
      List<List<Integer>> ans) {

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
        // adding above k sum elements
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
  }


  public static ArrayList<Integer> maxone(ArrayList<Integer> A, int B) {

    ArrayList<Integer> ans = new ArrayList<>();

    int left = 0;
    int right = 0;

    int zeroCount = 0;

    int minIndex = -1;
    int maxIndex = -1;

    int maxLen = 0;

    while (right < A.size()) {
      int num = A.get(right);

      if (num == 0) {
        zeroCount++;

        /*if (maxLen < (right - left + 1)) {
          minIndex = right;
          maxIndex = left;
          maxLen = right - left + 1;
        }*/

      }

      while (zeroCount > B) {
        if (A.get(left) == 0) {
          zeroCount--;
        }
        left++;
      }

      if (maxLen < (right - left + 1)) {
        maxLen = right - left + 1;
        minIndex = left;
        maxIndex = right;
      }

      right++;
    }

    for (int i = minIndex; i <= maxIndex; i++) {
      ans.add(i);
    }

    return ans;
  }


  /*public static int mcm(int[] p) {
    int n = p.length;
    Map<String, Integer> map = new HashMap<>();
    return solveMcm(p, 1, n - 1, map);
  }

  private static int solveMcm(int[] p, int start, int end, Map<String, Integer> map) {
    if (start >= end) {
      return 0;
    }

    String key = start + "-" + end;

    if (map.containsKey(key)) {
      return map.get(key);
    }

    int minCost = Integer.MAX_VALUE / 2;

    for (int k = start; k < end; k++) {
      int cost1 = solveMcm(p, start, k, map);
      int cost2 = solveMcm(p, k + 1, end, map);
      int costOfMultiplyingCost1AndCost2 = p[start - 1] * p[k] * p[end];
      int totalCost = cost1 + cost2 + costOfMultiplyingCost1AndCost2;
      minCost = Math.min(minCost, totalCost);
    }

    map.put(key, minCost);
    return minCost;
  }*/

  public static ArrayList<Integer> solve(ArrayList<Integer> A) {

    ArrayList<Integer> ans = new ArrayList<>();

    int right = A.size() - 1;
    int left = 0;
    int idx = -1;

    while (left <= right) {

      int num1 = A.get(left);
      int num2 = A.get(right);

      if (num2 > num1) {
        idx = left;
        break;
      }
      left++;

    }

    if (idx == -1) {
      ans.addAll(A);
      return ans;
    }

    for (int i = 0; i < idx; i++) {
      ans.add(A.get(i));
    }

    for (int i = A.size() - 1; i >= idx; i--) {
      ans.add(A.get(i));
    }

    return ans;
  }

  public static void main(String[] args) {

    //System.out.println(maxone(new ArrayList<>(Arrays.asList(1, 1, 0, 1, 1, 0, 0, 1, 1, 1)), 1));

    System.out.println(solve(
        new ArrayList<>(Arrays.asList(4, 1, 3, 2))));

    //System.out.println(pow(-105, 1, 20));

    /*System.out.println(longestConsecutive(new ArrayList<>(
        Arrays.asList(97, 86, 67, 19, 107, 9, 8, 49, 23, 46, -4, 22, 72, 4, 57, 111, 20, 52, 99, 2,
            113, 81, 7, 5, 21, 0, 47, 54, 76, 117, -2, 102, 34, 12, 103, 69, 36, 51, 105, -3, 33,
            91, 37, 11, 48, 106, 109, 45, 58, 77, 104, 60, 75, 90, 3, 62, 16, 119, 85, 63, 87, 43,
            74, 13, 95, 94, 56, 28, 55, 66, 92, 79, 27, 42, 70))));*/

    //System.out.println(pow(5, 5, 20));
  }
}
