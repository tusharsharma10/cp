import java.util.*;

public class InterviewBit {

  public static int minimize(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
    int minAbsoluteDiff = Integer.MAX_VALUE;

    int i = 0, j = 0, k = 0;
    int m = A.size(), n = B.size(), p = C.size();

    while (i < m && j < n && k < p) {
      int minVal = Math.min(A.get(i), Math.min(B.get(j), C.get(k)));
      int maxVal = Math.max(A.get(i), Math.max(B.get(j), C.get(k)));

      minAbsoluteDiff = Math.min(minAbsoluteDiff, maxVal - minVal);

      // Move the pointer of the array with the minimum value
      if (A.get(i) == minVal) {
        i++;
      } else if (B.get(j) == minVal) {
        j++;
      } else {
        k++;
      }
    }

    return minAbsoluteDiff;
  }


  public static int numSubarraysWithSum2(ArrayList<Integer> A, int B, int C) {
    int n = A.size();
    int left = 0;
    int right = 0;
    int sum = 0;
    int count = 0;

    while (right < n) {
      sum += A.get(right);

      while (left <= right && sum > C) {
        sum -= A.get(left);
        left++;
      }

      int tempRight = right;

      while (left <= right && sum >= B && sum <= C) {
        count += right - left + 1; // All subsequences ending at 'right' are valid
        sum -= A.get(left);
        left++;
      }

      right = tempRight;
      right++;
    }

    return count;
  }

  public static int numRange(ArrayList<Integer> A, int B, int C) {
    int ans = 0;
    int n = A.size();

    // base case
    if (n == 1) {
      if (A.get(0) >= B && A.get(0) <= C) {
        return 1;
      }
      return 0;
    }

    int j = 0;
    int sum = 0;
    int i = 0;

    while (i < n) {
      if (sum > C) {
        sum = 0;
        i++;
        j = i;
      } else {
        sum += A.get(j);
        j++;
      }
      if (sum >= B && sum <= C) {
        ans++;
      }

      if (j == n) {
        sum = 0;
        i++;
        j = i;
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    System.out.println(numRange(new ArrayList<>(
        Arrays.asList(76, 22, 81, 77, 95, 23, 27, 35, 24, 38, 15, 90, 19, 46, 53, 6, 77, 96, 100,
            85, 43, 16, 73, 18, 7, 66)), 98, 290));
  }

}
