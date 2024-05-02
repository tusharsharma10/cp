package monotonicStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SumSubarrayMinimum {

  public static long subArrayRanges(int[] nums) {
    long sum = 0;
    for (int i = 0; i < nums.length; i++) {
      int min = nums[i];
      int max = nums[i];
      for (int j = i; j < nums.length; j++) {
        min = Math.min(min, nums[j]);
        max = Math.max(max, nums[j]);
        sum += max - min;
      }

    }

    return sum;
  }

  public int sumSubarrayMins1(int[] arr) {
    int sum = 0;
    int modVal = 1000000007;
    for (int i = 0; i < arr.length; i++) {
      int min = arr[i];
      for (int j = i; j < arr.length; j++) {
        min = Math.min(min, arr[j]);
        sum = ((sum % modVal) + min) % modVal;
      }
    }

    return sum;
  }

  /**
   * Difficult
   */
  public int sumSubarrayMins(int[] arr) {
    // As the result should be modulo 10^9 + 7
    long MOD = 1000000007;
    long sum = 0;
    int n = arr.length;
    Stack<Integer> stack = new Stack<>();
    int[] left = new int[n];
    int[] right = new int[n];

    // Calculate the nearest smaller element to the left for each element
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
        stack.pop();
      }
      left[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(i);
    }

    // Clear the stack
    stack.clear();

    // Calculate the nearest smaller element to the right for each element
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
        stack.pop();
      }
      right[i] = stack.isEmpty() ? n : stack.peek();
      stack.push(i);
    }

    // Calculate the contribution of each element to the sum of subarray minimums
    for (int i = 0; i < n; i++) {
      sum = (sum + (long) arr[i] * (i - left[i]) * (right[i] - i)) % MOD;
    }

    return (int) sum;
  }

  public static List<List<Integer>> generateSubarrays(int[] arr) {
    List<List<Integer>> subarrays = new ArrayList<>();

    // Generate all subarrays
    for (int i = 0; i < arr.length; i++) {
      for (int j = i; j < arr.length; j++) {
        List<Integer> subarray = new ArrayList<>();
        for (int k = i; k <= j; k++) {
          subarray.add(arr[k]);
        }
        subarrays.add(subarray);
      }
    }

    return subarrays;
  }

  public static void main(String[] args) {
    subArrayRanges(new int[]{1, 2, 3});
  }

}
