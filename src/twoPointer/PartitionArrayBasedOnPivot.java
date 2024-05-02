package twoPointer;

import java.util.ArrayList;
import java.util.List;

public class PartitionArrayBasedOnPivot {

  public int[] pivotArray(int[] nums, int pivot) {

    List<Integer> small = new ArrayList<>();

    List<Integer> great = new ArrayList<>();

    List<Integer> equals = new ArrayList<>();

    List<Integer> finalList = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] < pivot) {
        small.add(nums[i]);
      } else if (nums[i] == pivot) {
        equals.add(nums[i]);
      } else {
        great.add(nums[i]);
      }
    }

    for (int i = 0; i < small.size(); i++) {
      finalList.add(small.get(i));
    }

    for (int i = 0; i < equals.size(); i++) {
      finalList.add(equals.get(i));
    }

    for (int i = 0; i < great.size(); i++) {
      finalList.add(great.get(i));
    }

    int[] ans = new int[finalList.size()];

    for (int i = 0; i < ans.length; i++) {
      ans[i] = finalList.get(i);
    }

    return ans;
  }
}

