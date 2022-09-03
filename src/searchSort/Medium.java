package searchSort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import linklist.ListNode;

public class Medium {


  /**
   * https://leetcode.com/problems/wiggle-sort-ii/
   *
   * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
   */

  public static void wiggleSort(int[] nums) {

    int a[] = nums.clone();
    Arrays.sort(a);
    int left = (nums.length - 1) / 2;
    int right = nums.length - 1;
    for (int i = 0; i < nums.length; i++) {
      if (i % 2 == 0) {
        // next
        nums[i] = a[left];
        left--;
      } else {
        //next max number
        nums[i] = a[right];
        right--;
      }
    }

  }



  public static void main(String[] args) {
    wiggleSort(new int[]{1, 5, 1, 1, 6, 4});
  }

}

