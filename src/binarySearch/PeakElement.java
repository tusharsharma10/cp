package binarySearch;

import java.util.*;

public class PeakElement {

  public int findPeakElement(ArrayList<Integer> A) {
    return binSearch(A);
  }

  public int binSearch(ArrayList<Integer> A) {
    int left = 0;
    int right = A.size() - 1;

    while (left <= right) {
      int mid = (left + right) / 2;

      if (mid > 0 && A.get(mid) < A.get(mid - 1)) {
        right = mid - 1;
      } else if (mid < A.size() - 1 && A.get(mid) < A.get(mid + 1)) {
        left = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  public static int smallerOrEqualElements(ArrayList<Integer> A, int B) {
    int left = 0;
    int right = A.size() - 1;
    int posn = -1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (A.get(mid) > B) {
        right = mid - 1;
      } else if (A.get(mid) < B) {
        left = mid + 1;
      } else {
        posn = mid;
        break;
      }
    }
    if (posn == -1) {
      return left;
    }
    int r = posn + 1;
    while (r < A.size() && A.get(r) == B) {
      r++;
    }
    return r;
  }

  public static int perfectPeak(ArrayList<Integer> a) {

    int[] A = new int[a.size()];

    for (int i = 0; i < A.length; i++) {
      A[i] = a.get(i);
    }

    int n = A.length;

    // Array to store maximum element from 0 to n
    int[] leftMax = new int[n];
    leftMax[0] = A[0];
    for (int i = 1; i < n; i++) {
      leftMax[i] = Math.max(leftMax[i - 1], A[i]);
    }

    // Array to store minimum element from n to 0
    int[] rightMin = new int[n];
    rightMin[n - 1] = A[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      rightMin[i] = Math.min(rightMin[i + 1], A[i]);
    }

    // Check if there exists an index where A[i] is greater than leftMax[i] and smaller than rightMin[i]
    for (int i = 1; i < n - 1; i++) {
      if (A[i] > leftMax[i - 1] && A[i] < rightMin[i + 1]) {
        return 1;
      }
    }

    return 0;
  }

  public static void main(String[] args) {
    System.out.println(smallerOrEqualElements(new ArrayList<>(Arrays.asList(1, 3, 4, 4, 6)), 4));
  }

}
