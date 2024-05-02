package array;

import java.util.*;

public class Ksum {

  public static ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> A, int B) {

    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    Collections.sort(A);
    kSum(4, 0, B, new ArrayList<>(), ans, A);
    return ans;
  }

  private static void kSum(int k, int startIdx, int target, ArrayList<Integer> tempList,
      ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> A) {

    if (k == 2) {
      twoSum(startIdx, target, tempList, ans, A);
      return;
    }

    for (int j = startIdx; j <= A.size() - k; j++) { // Fixed loop termination condition

      if (j != startIdx && A.get(j).equals(A.get(j - 1))) {
        continue;
      }
      int num = A.get(j);
      tempList.add(num);
      kSum(k - 1, j + 1, target - num, tempList, ans, A);
      tempList.remove(tempList.size() - 1);

    }

  }

  private static void twoSum(int startIdx, int target, ArrayList<Integer> tempList,
      ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> A) {

    int left = startIdx;
    int right = A.size() - 1;

    while (left < right) {

      int sum = A.get(left) + A.get(right);
      if (sum < target) {
        left++;
      } else if (sum > target) {
        right--;
      } else {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.addAll(tempList);
        temp.add(A.get(left));
        temp.add(A.get(right));
        ans.add(temp); // Create a new instance to avoid reference issues
        left++;

        while (left < right && A.get(left).equals(A.get(left - 1))) {
          left++;
        }
      }
    }

  }

  public static void main(String[] args) {
    System.out.println(fourSum(new ArrayList<>(
        Arrays.asList(-13, 11, 30, 12, 20, 21, -13, 6, -4, 7, 5, -2, 19, -10, 4, 8, 1)), 79));
  }

}
