package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Basic {

  /**
   * https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1 Given weights and
   * values of N items, we need to put these items in a knapsack of capacity W to get the maximum
   * total value in the knapsack. Note: Unlike 0/1 knapsack, you are allowed to break the item.
   */

  public static double fractionalKnapsack(int W, Item arr[], int n) {

    Arrays.sort(arr, (e1, e2) -> {

      double a = (double) e1.value / (double) e1.weight;
      double b = (double) e2.value / (double) e2.weight;

      if (a > b) {
        return -1;
      } else {
        return 1;
      }

    });

    int totalVal = 0;

    for (int i = 0; i < arr.length; i++) {

      if (W < arr[i].weight) {
        int val = (arr[i].value / arr[i].weight) * W;
        totalVal += val;
        W -= W;
      } else {
        totalVal += arr[i].value;
        W -= arr[i].weight;
      }

    }

    return totalVal;
  }


  /**
   * https://practice.geeksforgeeks.org/problems/choose-and-swap0531/1
   * A = "ccad"
   * Output: "aacd"
   * Explanation:
   * In ccad, we choose a and c and after
   * doing the replacement operation once we get,
   * aacd and this is the lexicographically
   * smallest string possible.
   */

  public static String chooseandswap(String s) {

    PriorityQueue<Character> pq = new PriorityQueue<Character>();

    // add in pq if not already exists
    for (int i = 0; i < s.length(); i++) {
      if (!pq.contains(s.charAt(i))) {
        pq.add(s.charAt(i));
      }
    }


    for (int i = 0; i < s.length(); i++) {
      char a = s.charAt(i);
      pq.remove(a);
      if (!pq.isEmpty() && Character.compare(a, pq.peek()) > 0) {
       //swap operation
        s = s.replace(a, '*');
        s = s.replace(pq.peek(), a);
        s = s.replace('*', pq.peek());
        break;
      }
    }

    return s;
  }

  /**
   * https://leetcode.com/problems/container-with-most-water/
   */

  public static int maxArea(int[] height) {

    int i = 0;
    int j = height.length - 1;

    int maxWater = 0;

    while (j > i) {

      int width = j - i;
      int ht = Math.min(height[i], height[j]);

      maxWater = Math.max(maxWater, ht * width);

      if (height[i] < height[j]) {
        i++;
      } else {
        j--;
      }
    }

    return maxWater;
  }

  /**
   *  https://leetcode.com/problems/maximum-69-number/submissions/
   */

  public int maximum69Number(int num) {

    String s = String.valueOf(num);

    int arr[] = new int[s.length()];

    for (int i = 0; i < s.length(); i++) {
      arr[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
    }

    for (int i = 0; i < s.length(); i++) {

      if (arr[i] == 9) {
        continue;
      } else {
        arr[i] = 9;
        break;
      }
    }

    StringBuilder str = new StringBuilder();

    for (int i : arr) {

      str.append(String.valueOf(i));
    }

    return Integer.parseInt(str.toString());
  }

  /**
   *  https://leetcode.com/problems/minimum-operations-to-make-the-array-increasing/
   */

  public static int minOperations(int[] arr) {

    int ops = 0;

    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i + 1] > arr[i]) {
        continue;
      } else {
        int diff = arr[i] - arr[i+1];
        arr[i + 1] = arr[i] + 1;
        ops += diff + 1;
      }
    }

    return ops;
  }

  /**
   * https://leetcode.com/problems/di-string-match/
   */

  public static int[] diStringMatch(String s) {

    int[] arr = new int[s.length() + 1];

    int max = arr.length - 1;
    int min = 0;

    for (int i = 0; i < s.length(); i++) {

      if (s.charAt(i) == 'I') {
        arr[i] = min;
        min++;
      } else {
        arr[i] = max;
        max--;
      }
    }
    arr[s.length()] = max;
    return arr;
  }

  /**
   * https://leetcode.com/problems/array-partition/
   */

  public static int arrayPairSum(int[] nums) {

    int sum = 0;

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i = 0; i < nums.length; i++) {
      pq.add(nums[i]);
    }

    while (!pq.isEmpty()) {

      int min1 = pq.poll();
      int min2 = pq.poll();

      sum += Math.min(min1, min2);
    }

    return sum;
  }

  public static void main(String[] args) {
   /* System.out.println(
        fractionalKnapsack(50, new Item[]{new Item(60, 10), new Item(100, 20), new Item(120, 30)},
            3));*/

    System.out.println(diStringMatch("IDID"));
  }
}

/**
 * https://leetcode.com/problems/container-with-most-water/
 */

class Item {

  int value, weight;

  Item(int x, int y) {
    this.value = x;
    this.weight = y;
  }
}