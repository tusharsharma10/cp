package binarySearch;

public class BinSearch {

  public static int search(int[] nums, int target) {
    int high = nums.length - 1;
    int low = 0;

    while (high >= low) {
      int mid = (low + high) / 2;
      if (nums[mid] > target) {
        high = mid - 1;
      } else if (nums[mid] < target) {
        low = mid + 1;
      } else {
        return mid;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    search(new int[]{5}, 5);
  }

}
