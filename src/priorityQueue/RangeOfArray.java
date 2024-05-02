package priorityQueue;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

public class RangeOfArray {


  final int N = 100005;
  ArrayList<ArrayList<Integer>> fact;
  int[] a, p;
  int n;

  public RangeOfArray() {
    fact = new ArrayList<ArrayList<Integer>>();
    a = new int[N];
    p = new int[N];
    for (int i = 0; i < N; i++) {
      fact.add(new ArrayList<Integer>());
    }
    for (int i = 2; i < N; i++) {
      for (int j = i; j < N; j += i) {
        fact.get(j).add(i);
      }
    }
  }

  public int solve(ArrayList<Integer> A) {
    n = A.size();
    Set<Integer> s = new HashSet<Integer>();

    for (int i = 0; i < n; i++) {
      s.add(A.get(i));
    }

    n = 0;

    for (int x : s) {
      a[n] = x;
      n++;
    }

    Arrays.sort(a, 0, n);

    PriorityQueue<pair> pq = new PriorityQueue<pair>();
    pair Pair;

    int mx = 0;

    for (int i = 0; i < n; i++) {
      int firstDivisor = fact.get(a[i]).get(0);
      p[i] = 0;
      Pair = new pair(firstDivisor, i);
      pq.add(Pair);
      mx = Math.max(mx, firstDivisor);
    }

    // added first divisor and index in priority queue and computed the max among them
    // now will calculate probable ans
   // for elements put in the queue we will check whether it's all factors of any one number are not == size that is are not used
    // important point here is when to stop looping and adding elements in the list

    int ans = mx - pq.peek().F;

    while (pq.isEmpty() == false) {
      int idx = pq.poll().S;
      if ((p[idx] + 1) == fact.get(a[idx]).size()) {
        break;
      }
      p[idx]++;
      Pair = new pair(fact.get(a[idx]).get(p[idx]), idx);
      pq.add(Pair);
      mx = Math.max(mx, fact.get(a[idx]).get(p[idx]));
      ans = Math.min(ans, mx - pq.peek().F);

    }

    return ans;
  }


  public static void main(String[] args) {
    RangeOfArray r = new RangeOfArray();
    r.solve(new ArrayList<>(Arrays.asList(20, 30, 50, 67, 92, 105)));
  }

}

class pair implements Comparable {

  int F, S;

  pair(int f, int s) {
    F = f;
    S = s;
  }

  @Override
  public int compareTo(Object o) {
    pair p1 = (pair) this;
    pair p2 = (pair) o;
    return (p1.F != p2.F) ? p1.F - p2.F : p1.S - p2.S;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof pair)) {
      return false;
    }
    pair p1 = (pair) this;
    pair p2 = (pair) o;
    return p1.F == p2.F && p1.S == p2.S;
  }

  @Override
  public int hashCode() {
    return this.F + 97 * this.S;
  }
}
