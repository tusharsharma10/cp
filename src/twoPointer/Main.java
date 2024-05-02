package twoPointer;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {


  /**
   * https://leetcode.com/problems/minimum-size-subarray-sum/
   */

  public static int minSubArrayLen(int target, int[] nums) {
    return 0;
  }

  public static String reverseOnlyLetters(String s) {
    int left = 0;
    int right = s.length() - 1;
    StringBuilder builder = new StringBuilder(s);
    while (left < right) {

      while (left < right && !isAlpha(s.charAt(left))) {
        left++;
      }

      while (left < right && !isAlpha(s.charAt(right))) {
        right--;
      }

      if (left < right) {
        char temp = s.charAt(left);
        builder.setCharAt(left, s.charAt(right));
        builder.setCharAt(right, temp);
        left++;
        right--;
      }

    }

    return builder.toString();
  }

  private static boolean isAlpha(char c) {

    if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
      return true;
    }

    return false;
  }

  public static int distinctAverages(int[] nums) {

    PriorityQueue<Integer> maxPq = new PriorityQueue<>();
    PriorityQueue<Integer> minPq = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer t1, Integer t2) {
        return t2 - t1;
      }
    });

    Set<Double> set = new HashSet<>();

    for (int i = 0; i < nums.length; i++) {
      maxPq.offer(nums[i]);
      minPq.offer(nums[i]);
    }

    while (!maxPq.isEmpty() && !minPq.isEmpty()) {
      int a = maxPq.poll();
      int b = minPq.poll();
      double ans = (Double.valueOf(a) + Double.valueOf(b)) / 2;
      set.add(ans);
    }

    return set.size();

  }

  public static void main(String[] args) {
    System.out.println(distinctAverages(new int[]{9, 5, 7, 8, 7, 9, 8, 2, 0, 7}));
  }

}
