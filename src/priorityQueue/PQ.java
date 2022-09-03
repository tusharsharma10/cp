package priorityQueue;

import java.util.PriorityQueue;

public class PQ {


  public static int findKthLargest(int[] nums, int k) {
    int ans = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i = 0; i < nums.length; i++) {
      pq.add(nums[i]);
    }

    for (int i = 0; i < nums.length - k; i++) {
      pq.poll();
    }

    ans = pq.poll();

    return ans;
  }

  /**
   * https://leetcode.com/problems/kth-largest-element-in-a-stream/
   */
//  public KthLargest(int k, int[] nums) {
//
//  }
//
//  public int Add(int val) {
//
//  }

  /**
   * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
   */

  public static int kthSmallest(int[][] matrix, int k) {
    int ans = Integer.MAX_VALUE;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        pq.add(matrix[i][j]);
      }
    }

    for (int i = 1; i <= k; i++) {
      ans = pq.poll();
    }

    return ans;
  }

  public static void main(String[] args) {
    findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
  }
}
