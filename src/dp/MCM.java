package dp;

import java.util.*;

/**
 * Matrix chain multiplication
 */
public class MCM {


  public static int mcm(int[] p) {
    int n = p.length;
    Map<String, Integer> map = new HashMap<>();
    return solveMcm(p, 1, n - 1, map);
  }

  private static int solveMcm(int[] p, int start, int end, Map<String, Integer> map) {
    if (start >= end) {
      return 0;
    }

    String key = start + "-" + end;

    if (map.containsKey(key)) {
      return map.get(key);
    }

    int minCost = Integer.MAX_VALUE / 2;

    for (int k = start; k < end; k++) {
      int cost1 = solveMcm(p, start, k, map);
      int cost2 = solveMcm(p, k + 1, end, map);
      int costOfMultiplyingCost1AndCost2 = p[start - 1] * p[k] * p[end];
      int totalCost = cost1 + cost2 + costOfMultiplyingCost1AndCost2;
      minCost = Math.min(minCost, totalCost);
    }

    map.put(key, minCost);
    return minCost;
  }


  public static void main(String[] args) {
    System.out.println(mcm(new int[]{10, 15, 20, 25}));
  }

}
