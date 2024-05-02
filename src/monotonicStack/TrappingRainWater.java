package monotonicStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class TrappingRainWater {

  public static int trap(int[] height) {
    int n = height.length;
    int left[] = new int[n];
    int right[] = new int[n];

    left[0] = height[0];

    for (int i = 1; i < n; i++) {
      left[i] = Math.max(left[i - 1], height[i]);
    }

    right[n - 1] = height[n - 1];

    for (int i = n - 2; i >= 0; i--) {
      right[i] = Math.max(right[i + 1], height[i]);
    }

    int water = 0;

    for (int i = 0; i < n; i++) {
      water += (Math.min(left[i], right[i]) - height[i]);
    }

    return water;

  }


  public static int removeDuplicates(ArrayList<Integer> a) {
    if (a == null || a.isEmpty()) {
      return 0;
    }

    int n = a.size();
    int uniqueIndex = 0;
    boolean hasTwoOccurrences = false;

    for (int i = 1; i < n; i++) {
      int numi = a.get(i);
      int numUnique = a.get(uniqueIndex);

      if (numi != numUnique) {
        uniqueIndex++;
        a.set(uniqueIndex, numi);
        hasTwoOccurrences = false; // Reset the occurrence count
      } else if (!hasTwoOccurrences) {
        uniqueIndex++;
        a.set(uniqueIndex, numi);
        hasTwoOccurrences = true; // Set the occurrence count
      }
    }

    return uniqueIndex + 1;
  }

  public static int minimizeMaxDifference(ArrayList<Integer> A, ArrayList<Integer> B,
      ArrayList<Integer> C) {

    int minMaxDiff = Integer.MAX_VALUE;

    int i = 0, j = 0, k = 0;

    while (i < A.size() && j < B.size() && k < C.size()) {
      int diffAB = Math.abs(A.get(i) - B.get(j));
      int diffBC = Math.abs(B.get(j) - C.get(k));
      int diffCA = Math.abs(C.get(k) - A.get(i));

      int maxDiff = Math.max(diffAB, Math.max(diffBC, diffCA));
      minMaxDiff = Math.min(minMaxDiff, maxDiff);

      // Move the pointer of the array with the smallest value
      if (A.get(i) <= B.get(j) && A.get(i) <= C.get(k)) {
        i++;
      } else if (B.get(j) <= A.get(i) && B.get(j) <= C.get(k)) {
        j++;
      } else {
        k++;
      }
    }

    return minMaxDiff;
  }

  public static int removeElement(ArrayList<Integer> A, int B) {
    int n = A.size();
    int left = 0; // Pointer to track the position where we want to keep elements

    for (int i = 0; i < n; i++) {
      if (A.get(i) != B) {
        A.set(left, A.get(i)); // Move the current element to the left pointer's position
        left++;
      }
    }

    return left; // Number of elements left after removal

  }

  public static int atMost2Occurrences(ArrayList<Integer> a) {
    Collections.sort(a);
    HashMap<Integer, Integer> freqMap = new HashMap<>();
    int left = 0;
    int minSize = Integer.MAX_VALUE;

    for (int right = 0; right < a.size(); right++) {
      int num = a.get(right);
      freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

      while (freqMap.size() > 2) {
        int leftNum = a.get(left++);
        freqMap.put(leftNum, freqMap.get(leftNum) - 1);
        if (freqMap.get(leftNum) == 0) {
          freqMap.remove(leftNum);
        }
      }

      minSize = Math.min(minSize, right - left + 1);
    }

    return minSize - 1;
  }


  public static void main(String[] args) {

    System.out.println(atMost2Occurrences(new ArrayList<>(
        Arrays.asList(58, 38, 60, 24, 42, 30, 79, 17, 36, 91, 43, 89, 7, 41, 43, 65, 49, 47, 6, 91,
            30, 71, 51, 7, 2, 94, 49, 30, 24, 85, 55, 57, 41, 67, 77, 32, 9, 45, 40, 27, 24, 38, 39,
            19, 83, 30, 42, 34, 16, 40, 59, 5, 31, 78, 7, 74, 87, 22, 46, 25, 73, 71, 30, 78, 74,
            98, 13, 87, 91, 62, 37, 56))));

  }

  public static long getTrappedWater(int[] height) {
    int n = height.length;

    int left = 0;
    int right = n - 1;

    int res = 0;
    int maxLeft = 0;
    int maxRight = 0;

    while (left <= right) {
      if (height[left] <= height[right]) {
        if (height[left] >= maxLeft) {
          maxLeft = height[left];
        } else {
          res += maxLeft - height[left];
        }
        left++;
      } else {
        if (height[right] >= maxRight) {
          maxRight = height[right];
        } else {
          res += maxRight - height[right];
        }
        right--;
      }
    }

    return res;
  }


}
