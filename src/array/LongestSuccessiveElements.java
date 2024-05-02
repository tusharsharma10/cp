package array;

import java.util.HashSet;
import java.util.Set;

public class LongestSuccessiveElements {

  public static int longestSuccessiveElements(int[] a) {
    int n = a.length;
    if (n == 0) {
      return 0;
    }

    int longest = 1;
    Set<Integer> set = new HashSet<>();

    // put all the array elements into set
    for (int i = 0; i < n; i++) {
      set.add(a[i]);
    }

    // Find the longest sequence
    for (int num : set) {
      // if 'num' is a starting number
      if (!set.contains(num - 1)) {
        // find consecutive numbers
        int cnt = 1;
        int x = num;
        while (set.contains(x + 1)) {
          x = x + 1;
          cnt = cnt + 1;
        }
        longest = Math.max(longest, cnt);
      }
    }
    return longest;
  }

}
