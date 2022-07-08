package searchSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Basic {


  /**
   * https://leetcode.com/problems/search-in-rotated-sorted-array/
   */
  public static int searchUnique(int[] nums, int key) {

    int start = 0;
    int end = nums.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;

      // Case 1 : The middle element matches the key
      if (nums[mid] == key) {
        return mid;
      }

      // Case 2 : The left half is sorted
      if (nums[start] <= nums[mid]) {
        // Case 2a : The key lies in the sorted half
        if (nums[start] <= key && key <= nums[mid]) {
          end = mid - 1;
        } else { // Case 2b: The key lies in the unsorted half
          start = mid + 1;
        }
      }
      // Case 3 : Right half is sorted
      else {
        // Case 3a: The key lies in the sorted half
        if (nums[mid] <= key && key <= nums[end]) {
          start = mid + 1;
        } else { // Case 3b: The key lies in the unsorted half
          end = mid - 1;
        }
      }
    }

    return -1;
  }

  public static int searchDuplicate(int[] nums, int key) {

    int start = 0;
    int end = nums.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;

      // Case 1 : The middle element matches the key
      if (nums[mid] == key) {
        return mid;
      }

      // Case 2 : The left half is sorted
      if (nums[start] < nums[mid]) {
        // Case 2a : The key lies in the sorted half
        if (nums[start] <= key && key <= nums[mid]) {
          end = mid - 1;
        } else { // Case 2b: The key lies in the unsorted half
          start = mid + 1;
        }
      } else if (nums[start] == nums[mid]) {

        while (mid >= start) {
          if (nums[start] == key) {
            return start;
          }
          start++;
        }
      }
      // Case 3 : Right half is sorted
      else if (nums[mid] < nums[end]) {
        // Case 3a: The key lies in the sorted half
        if (nums[mid] <= key && key <= nums[end]) {
          start = mid + 1;
        } else { // Case 3b: The key lies in the unsorted half
          end = mid - 1;
        }
      } else {
        while (end >= mid) {
          if (nums[mid] == key) {
            return mid;
          }
          end--;
        }
      }
    }

    return -1;
  }

  /**
   * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
   */
  public static int findMin(int[] nums) {

    return 0;
  }

  /**
   * https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/submissions/
   */

  public int minDifference(int[] nums) {

    Arrays.sort(nums);

    int min = Integer.MAX_VALUE;

    if (nums.length >= 4) {
      int max4posn = nums.length - 4;
      int max3posn = nums.length - 3;

      min = Math.min(nums[max4posn] - nums[0], nums[max3posn] - nums[1]);

      int min4posn = 3;
      int min3posn = 2;

      min = Math.min(min,
          Math.min(nums[nums.length - 1] - nums[min4posn], nums[nums.length - 2] - nums[min3posn]));

      return min;
    } else {

      return 0;

    }

  }

  /**
   * https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/submissions/
   */

  public int countKDifference(int[] nums, int k) {

    int cnt = 0;

    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {

      if (map.containsKey(nums[i] - k)) {
        cnt += map.get(nums[i] - k);
      }

      if (map.containsKey(nums[i] + k)) {
        cnt += map.get(nums[i] + k);
      }

      map.putIfAbsent(nums[i], 0);
      map.put(nums[i], map.get(nums[i]) + 1);

    }

    return cnt;
  }


  /**
   * https://practice.geeksforgeeks.org/problems/count-triplets-with-sum-smaller-than-x5549/1
   */
  public static long countTriplets(long arr[], int n, int sum) {

    Arrays.sort(arr);
    long count = 0;

    for (int i = 0; i < n; i++) {

      for (int j = i + 1; j < n; j++) {
        int k = n - 1;
        long val = arr[i] + arr[j];

        while (k > j && val + arr[k] >= sum) {
          k--;
        }

        count += k - j;

      }
    }

    return count;
  }

  /**
   * https://leetcode.com/problems/merge-intervals/submissions/
   */

  public static int[][] merge(int[][] nums) {

    sort(nums);
    return mergeUtil(nums);
  }

  private static void sort(int[][] nums) {

    Arrays.sort(nums, (o1, o2) -> o1[0] - o2[0]);

  }

  private static int[][] mergeUtil(int[][] nums) {

    ArrayList<int[]> ans = new ArrayList<>();

    ans.add(nums[0]);

    int ptr = 0;

    for (int i = 0; i < nums.length; i++) {

      int temp2[] = new int[2];
      temp2[0] = ans.get(ptr)[0];
      temp2[1] = ans.get(ptr)[1];

      if (temp2[1] >= nums[i][0]) {

        if (temp2[1] >= nums[i][1]) {
          temp2[1] = temp2[1];
        } else {
          temp2[1] = nums[i][1];
        }

        ans.set(ptr, temp2);
      } else {
        ptr++;
        ans.add(nums[i]);
      }

    }

    int[][] arr = new int[ans.size()][2];
    for (int i = 0; i < ans.size(); i++) {

      arr[i][0] = ans.get(i)[0];
      arr[i][1] = ans.get(i)[1];
    }

    return arr;
  }

  public static void main(String[] args) {
    // System.out.println(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}));
    //System.out.println(merge(new int[][]{{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}}));
    System.out.println(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}));

  }
}
