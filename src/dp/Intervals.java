package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Intervals {

  public static int[][] mergeInterval(int[][] intervals) {

    Arrays.sort(intervals, (l1, l2) -> l1[0] - l2[0]);

    List<int[]> ans = new ArrayList<>();
    ans.add(new int[]{intervals[0][0], intervals[0][1]});

    int i = 1;

    while (i < intervals.length) {
      int[] lastIn = ans.get(ans.size() - 1);

      if (lastIn[1] > intervals[i][0] && lastIn[1] > intervals[i][1]) {
      } else if (lastIn[1] >= intervals[i][0] && lastIn[1] < intervals[i][1]) {
        lastIn[1] = intervals[i][1];
      } else if (lastIn[1] < intervals[i][0]) {
        ans.add(intervals[i]);
      }
      i++;
    }

    int[][] res = new int[ans.size()][2];
    int j = 0;
    for (int[] x : ans) {
      res[j] = x;
      j++;
    }

    return res;
  }

  public int[][] insertInterval(int[][] intervals, int[] newInterval) {
    List<int[]> merged = new ArrayList<>();

    int i = 0;
    int n = intervals.length;

    // Add all intervals that end before the new interval starts
    while (i < n && intervals[i][1] < newInterval[0]) {
      merged.add(intervals[i]);
      i++;
    }

    // Merge overlapping intervals
    while (i < n && intervals[i][0] <= newInterval[1]) {
      newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
      newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
      i++;
    }

    merged.add(newInterval);

    // Add remaining intervals
    while (i < n) {
      merged.add(intervals[i]);
      i++;
    }

    return merged.toArray(new int[merged.size()][]);
  }

  public static List insert2(int[][] intervals, int[] newInterval) {

    newInterval[0] = Math.min(newInterval[0], newInterval[1]);
    newInterval[1] = Math.max(newInterval[0], newInterval[1]);

    ArrayList<int[]> ans = new ArrayList<>();

    ans.add(intervals[0]);

    int i = 1;

    while (i < intervals.length) {

      int[] lastIn = ans.get(ans.size() - 1);

      int[] current = intervals[i];

      if (lastIn[1] > current[0] && lastIn[1] > current[1]) {
      } else if (lastIn[1] >= current[0] && lastIn[1] < current[1]) {
        lastIn[1] = current[1];
      } else if (lastIn[1] < current[0]) {
        ans.add(current);
      }
      i++;
    }

    ArrayList<int[]> merged = new ArrayList<>();

    int j = 0;

    while (j < ans.size() && ans.get(j)[1] < newInterval[0]) {
      merged.add(ans.get(j));
      j++;
    }

    while (j < ans.size() && ans.get(j)[0] <= newInterval[1]) {
      newInterval[0] = Math.min(newInterval[0], ans.get(j)[0]);
      newInterval[1] = Math.max(newInterval[1], ans.get(j)[1]);
      j++;
    }

    merged.add(newInterval);

    while (j < ans.size()) {
      merged.add(ans.get(j));
      j++;
    }

    return merged;
  }


  public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {

    newInterval.start = Math.min(newInterval.start, newInterval.end);
    newInterval.end = Math.max(newInterval.start, newInterval.end);

    ArrayList<Interval> ans = new ArrayList<>();

    if (intervals == null || intervals.isEmpty()) {
      ans.add(newInterval);
      return ans;
    }

    ans.add(intervals.get(0));
    int i = 1;
    while (i < intervals.size()) {

      Interval lastIn = ans.get(ans.size() - 1);
      Interval current = intervals.get(i);

      if (lastIn.end > current.start && lastIn.end > current.end) {
      } else if (lastIn.end >= current.start && lastIn.end < current.end) {
        lastIn.end = current.end;
      } else if (lastIn.end < current.start) {
        ans.add(current);
      }
      i++;
    }

    ArrayList<Interval> merged = new ArrayList<>();

    int j = 0;

    while (j < ans.size() && ans.get(j).end < newInterval.start) {
      merged.add(ans.get(j));
      j++;
    }

    while (j < ans.size() && ans.get(j).start <= newInterval.end) {
      newInterval.start = Math.min(newInterval.start, ans.get(j).start);
      newInterval.end = Math.max(newInterval.end, ans.get(j).end);
      j++;
    }

    merged.add(newInterval);

    while (j < ans.size()) {
      merged.add(ans.get(j));
      j++;
    }

    return merged;

  }


  public int firstMissingPositive(int[] nums) {
    int n = nums.length;

    // Mark non-positive integers as n+1
    for (int i = 0; i < n; i++) {
      if (nums[i] <= 0) {
        nums[i] = n + 1;
      }
    }

    // Mark present positive integers by negating the value at their index
    for (int i = 0; i < n; i++) {
      int num = Math.abs(nums[i]);
      if (num <= n) {
        nums[num - 1] = -Math.abs(nums[num - 1]);
      }
    }

    // Find the first positive integer not marked as present
    for (int i = 0; i < n; i++) {
      if (nums[i] > 0) {
        return i + 1;
      }
    }

    // If all positive integers are present, return n + 1
    return n + 1;
  }


  public static ArrayList<Integer> plusOne(ArrayList<Integer> A) {

    int carry = 1;
    boolean flag = true;

    int n = A.size();
    int i = n - 1;

    while (i >= 0 && (flag || carry == 1)) {
      flag = false;
      int num = A.get(i);
      num += carry;

      if (num == 10) {
        carry = 1;
        A.set(i, 0);

      } else {
        A.set(i, num);
        carry = 0;
      }
      i--;
    }

    if (carry == 1) {
      A.add(0, 1);
    }

    ArrayList<Integer> ans = new ArrayList<>();
    boolean initFlag = true;
    for (int j = 0; j < A.size(); j++) {
      if (A.get(j) == 0 && initFlag) {
        continue;
      }
      initFlag = false;
      ans.add(A.get(j));
    }

    return ans;
  }

  public static void main(String[] args) {
//    System.out.println(
//        insert2(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 9}));
//
//    Set<Integer> result = new HashSet<>();
//    result.stream().collect(Collectors.toList());
    ArrayList<Integer> x = new ArrayList<>(Arrays.asList(9, 9, 9, 9, 9));
    System.out.println(plusOne(x));
  }

}
