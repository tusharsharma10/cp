package array;

import java.util.HashMap;
import java.util.Map;

public class FindAllSubArraysWithGivenSum {

  public static int findAllSubarraysWithGivenSum(int arr[], int k) {
    // size of the given array.
    int n = arr.length;

    Map<Integer, Integer> map = new HashMap<>();

    int preSum = 0, cnt = 0;

    // Setting 0 in the map.
    map.put(0, 1);

    for (int i = 0; i < n; i++) {

      // add current element to prefix Sum:
      preSum += arr[i];

      // Calculate x-k:
      int remainingSum = preSum - k;

      if (map.containsKey(remainingSum)) {
        cnt += map.get(remainingSum);
      }
      map.put(preSum, map.getOrDefault(preSum, 0) + 1);
    }
    return cnt;
  }


  int maxLen(int A[], int n) {
    // Your code here
    HashMap<Integer, Integer> mpp = new HashMap<>();

    int maxlen = 0;
    int sum = 0;

    for (int i = 0; i < n; i++) {

      sum += A[i];

      if (sum == 0) {
        maxlen = i + 1;
      } else {
        if (mpp.get(sum) != null) {
          maxlen = Math.max(maxlen, i - mpp.get(sum));
        } else {
          mpp.put(sum, i);
        }
      }
    }
    return maxlen;
  }


}
