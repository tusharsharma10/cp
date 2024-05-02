package monotonicStack;

import java.util.*;

public class NextGreaterElement {

  public static int[] nextGreaterElement(int[] arr, int n) {
    int[] result = new int[n];
    Stack<Integer> stack = new Stack<>();

    for (int i = n - 1; i >= 0; i--) {

      while (!stack.isEmpty() && arr[i] >= stack.peek()) {
        stack.pop();
      }

      // If stack is empty, there is no greater element to the right
      if (stack.isEmpty()) {
        result[i] = -1;
      } else {
        // The next greater element is the top element of the stack
        result[i] = stack.peek();
      }

      // Push arr[i] onto the stack for the next iteration
      stack.push(arr[i]);
    }

    return result;

  }

  public static int[] nextGreaterElementCircularArray(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    Stack<Integer> stack = new Stack<>();

    // Initialize result array with -1
    Arrays.fill(result, -1);

    // Iterate over the circular array twice
    for (int i = 0; i < 2 * n; i++) {
      int num = nums[i % n];

      // Pop elements from the stack until finding an element greater than num
      while (!stack.isEmpty() && nums[stack.peek()] < num) {
        result[stack.pop()] = num;
      }

      // Push the current index onto the stack for the next iteration
      if (i < n) {
        stack.push(i);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    nextGreaterElement(new int[]{5, 5, 5, 5, 5}, 5);
  }

}
