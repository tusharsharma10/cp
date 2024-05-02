package slidingWindow;

import java.util.*;
import linklist.ListNode;

public class Revision {

  public static int numSubarraysSumLessThanB(ArrayList<Integer> A, int B) {
    int count = 0;
    int right = 0;
    int left = 0;
    int n = A.size();
    int sum = 0;

    while (right < n) {
      sum += A.get(right);

      while (left <= right && sum >= B) {
        sum -= A.get(left);
        left++;
      }

      count += right - left + 1;
      right++;
    }
    return count;
  }


  public int subArraysWithDistinctIntegersExactlyBCount(ArrayList<Integer> A, int B) {
    return subArraysWithDistinctIntegersLessThanBCount(A, B)
        - subArraysWithDistinctIntegersLessThanBCount(A, B - 1);
  }

  public int subArraysWithDistinctIntegersLessThanBCount(ArrayList<Integer> A, int B) {

    int right = 0;
    int left = 0;
    int n = A.size();

    Map<Integer, Integer> map = new HashMap<>();
    int count = 0;

    while (right < n) {

      map.put(A.get(right), map.getOrDefault(A.get(right), 0) + 1);

      while (map.size() > B) {
        int num = A.get(left);
        map.put(num, map.get(num) - 1);
        if (map.get(num) <= 0) {
          map.remove(num);
        }
        left++;
      }

      count += right - left + 1;

      right++;
    }

    return count;
  }

  /**
   * https://www.interviewbit.com/problems/pick-from-both-sides/
   */

  public static int pickFromSides(ArrayList<Integer> A, int B) {

    int n = A.size();
    int sum = 0;
    int right = n;
    int left = 0;
    int count = 0;
    int maxSum = Integer.MIN_VALUE;

    while (right >= 0 && count < B) {
      right--;
      int num = A.get(right);
      sum += num;
      count++;
      //maxSum = Math.max(maxSum, sum);
    }

    maxSum = Math.max(maxSum, sum);

    while (left < B) {
      int numLeft = A.get(left);
      int numRight = A.get(right);
      sum += numLeft - numRight;
      maxSum = Math.max(maxSum, sum);
      left++;
      right++;
    }
    return maxSum;
  }

  public static int solve(ListNode A, int B) {
    int count = 0;
    ListNode temp = A;

    int size = 0;

    while (temp != null) {
      temp = temp.next;
      size++;
    }

    int mid = (size / 2) + 1;
    temp = A;

    while (count < mid - B - 1) {
      temp = temp.next;
      count++;
    }

    return temp.val;

  }


  public static void main(String[] args) {
    System.out.println(pickFromSides(new ArrayList<>(
        Arrays.asList(-584, -714, -895, -512, -718, -545, 734, -886, 701, 316, -329, 786, -737,
            -687, -645, 185, -947, -88, -192, 832, -55, -687, 756, -679, 558, 646, 982, -519, -856,
            196, -778, 129, -839, 535, -550, 173, -534, -956, 659, -708, -561, 253, -976, -846, 510,
            -255, -351, 186, -687, -526, -978, -832, -982, -213, 905, 958, 391, -798, 625, -523,
            -586, 314, 824, 334, 874, -628, -841, 833, -930, 487, -703, 518, -823, 773, -730, 763,
            -332, 192, 985, 102, -520, 213, 627, -198, -901, -473, -375, 543, 924, 23, 972, 61,
            -819, 3, 432, 505, 593, -275, 31, -508, -858, 222, 286, 64, 900, 187, -640, -587, -26,
            -730, 170, -765, -167, 711, 760, -104, -333)), 32));
  }

}
