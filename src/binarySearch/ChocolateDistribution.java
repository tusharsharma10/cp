package binarySearch;

import java.util.ArrayList;
import java.util.*;

public class ChocolateDistribution {

  public int solve(ArrayList<ArrayList<Integer>> A, int B) {
    HashMap<Integer, ArrayList<Integer>> h = new HashMap();
    for (int i = 0; i < A.size(); i++) {
      int aa = A.get(i).get(0);
      int bb = A.get(i).get(1);
      if (h.containsKey(aa)) {
        h.get(aa).add(bb);
      } else {
        ArrayList<Integer> tt = new ArrayList<Integer>();
        tt.add(bb);
        h.put(aa, tt);
      }
    }
    // System.out.println(h);
    int l = 0;
    int r = B;
    int optimal = -1;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (isPossible(h, mid, B)) {
        // System.out.println(mid+" mid");
        optimal = Math.max(optimal, mid);
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    return optimal;

  }

  boolean isPossible(HashMap<Integer, ArrayList<Integer>> h, int y, int tot) {
    int pp = 0;
    for (Map.Entry<Integer, ArrayList<Integer>> e : h.entrySet()) {
      ArrayList<Integer> gg = e.getValue();
      int toti = y;
      for (int i = 0; i < gg.size(); i++) {
        if (toti - gg.get(i) >= 0) {
          toti = toti - gg.get(i);
        } else {
          break;
        }
      }
      pp += (y - toti);

    }
    if (pp <= tot) {
      return true;
    }

    return false;
  }
}

