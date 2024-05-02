package array;

import java.util.List;

public class NextGreaterPermutation {

  static void nextGreaterPermutation(int[] nums) {
    int n = nums.length;

    // Step 1: Find the pivot
    int pivotIndex = -1;
    for (int i = n - 1; i > 0; i--) {
      if (nums[i] > nums[i - 1]) {
        pivotIndex = i - 1;
        break;
      }
    }

    if (pivotIndex == -1) {
      // If no such pivot found, array is already in descending order, reverse it
      reverse(nums, 0, n - 1);
      return;
    } else {
      // Step 2: Find the successor
      int successorIndex = n - 1;
      while (nums[successorIndex] <= nums[pivotIndex]) {
        successorIndex--;
      }

      // Step 3: Swap pivot and successor
      int temp = nums[pivotIndex];
      nums[pivotIndex] = nums[successorIndex];
      nums[successorIndex] = temp;

      // Step 4: Reverse the portion of the array to the right of pivot
      reverse(nums, pivotIndex + 1, n - 1);
    }

  }

  static void reverse(int[] nums, int left, int right) {
    while (left < right) {
      int temp = nums[left];
      nums[left] = nums[right];
      nums[right] = temp;
      left++;
      right--;
    }
  }

  public static void main(String[] args) {
    nextGreaterPermutation(new int[]{1, 5, 8, 4, 7, 6, 5, 3, 1});
  }

}
