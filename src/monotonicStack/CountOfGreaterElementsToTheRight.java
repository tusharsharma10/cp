package monotonicStack;

import java.util.ArrayList;
import java.util.List;

public class CountOfGreaterElementsToTheRight {

  public static List<Integer> countGreaterElements(int[] nums) {
    List<Integer> result = new ArrayList<>();
    int n = nums.length;
    // Store the count of greater elements for each element
    int[] count = new int[n];

    // Create an array of indices from 0 to n-1
    Integer[] indices = new Integer[n];
    for (int i = 0; i < n; i++) {
      indices[i] = i;
    }

    // Merge sort algorithm to count greater elements
    mergeSort(nums, indices, count, 0, n - 1);

    // Convert count array to a list
    for (int num : count) {
      result.add(num);
    }

    return result;
  }

  private static void mergeSort(int[] nums, Integer[] indices, int[] count, int low, int high) {
    if (low < high) {
      int mid = low + (high - low) / 2;
      mergeSort(nums, indices, count, low, mid);
      mergeSort(nums, indices, count, mid + 1, high);
      merge(nums, indices, count, low, mid, high);
    }
  }

  private static void merge(int[] nums, Integer[] indices, int[] count, int low, int mid,
      int high) {
    int[] tempIndices = new int[high - low + 1];
    int left = low;
    int right = mid + 1;
    int index = 0;
    int rightCount = 0; // Count of greater elements to the right

    // Merge the two sorted subarrays while counting greater elements
    while (left <= mid && right <= high) {
      if (nums[indices[right]] < nums[indices[left]]) {
        tempIndices[index++] = indices[right++];
        rightCount++;
      } else {
        count[indices[left]] += rightCount;
        tempIndices[index++] = indices[left++];
      }
    }

    // Copy remaining elements from the left subarray
    while (left <= mid) {
      count[indices[left]] += rightCount;
      tempIndices[index++] = indices[left++];
    }

    // Copy remaining elements from the right subarray
    while (right <= high) {
      tempIndices[index++] = indices[right++];
    }

    // Copy sorted indices back to the original array
    System.arraycopy(tempIndices, 0, indices, low, tempIndices.length);
  }

  public static void main(String[] args) {
    int[] nums = {3, 4, 2, 7, 5, 8, 10, 6};
    List<Integer> result = countGreaterElements(nums);
    System.out.println("Count of greater elements to the right: " + result);
  }
}
