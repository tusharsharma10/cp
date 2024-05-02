package priorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

  public static int largestSumAfterKNegations(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int total = 0;

    // Initialize the min heap and calculate the initial total
    for (int num : nums) {
      minHeap.offer(num);
      total += num;
    }

    // Negate the smallest elements from the min heap until k becomes 0
    while (k > 0) {
      // Get the smallest element
      int min = minHeap.poll();
      // Negate the element and subtract twice its value
      total -= 2 * min;
      // Add the negated value back to the heap
      minHeap.offer(-min);
      k--;
    }

    return total;
  }


  public static List<Integer> replaceWithRank(List<Integer> arr, int n) {
    int[] sol = new int[n];

    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
      @Override
      public int compare(int[] t1, int[] t2) {
        return t1[0] - t2[0];
      }
    });

    for (int i = 0; i < arr.size(); i++) {
      int[] a = new int[2];
      a[0] = arr.get(i);
      a[1] = i;
      pq.add(a);
    }

    int rank = 1;
    int prev = Integer.MIN_VALUE;
    while (!pq.isEmpty()) {
      int[] temp = pq.poll();
      if (prev != temp[0]) {
        sol[temp[1]] = rank;
      } else {
        sol[temp[1]] = rank - 1;
        rank--;
      }
      prev = temp[0];
      rank++;
    }

    List<Integer> ans = new ArrayList<>();

    for (int x : sol) {
      ans.add(x);
    }
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(largestSumAfterKNegations(new int[]{3, -1, 0, 2}, 3));
  }
}
