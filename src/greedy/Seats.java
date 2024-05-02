package greedy;

import java.util.*;

public class Seats {


  public static int bulbs(ArrayList<Integer> A) {
    int cost = 0;

    for (int x : A) {
      // for every cost we toggle the bits
      if (cost % 2 != 0) {
        x = 1 - x;
      }
      // increase the cost
      if (x % 2 != 1) {
        cost++;
      }
    }

    return cost;
  }

  public static int seats(String A) {
    List<Integer> pos = new ArrayList<>();

    for (int i = 0; i < A.length(); i++) {
      if (A.charAt(i) == 'x') {
        pos.add(i);
      }
    }

    int n = pos.size();
    long ans = 0;

    int mid = n / 2;

    // k positions seating will be available, since the nearest one will be taken by the first
    int k = 1;

    for (int i = mid - 1; i >= 0; i--) {
      // pos.get(mid) - pos.get(i) distance between the 2
      // -k means bagal ki seat par baitho, godi main nahi
      ans = (ans + pos.get(mid) - pos.get(i) - k) % 10000003;
      k++;
    }
    k = 1;

    for (int i = mid + 1; i < n; i++) {
      ans = (ans + pos.get(i) - pos.get(mid) - k) % 10000003;
      k++;
    }

    return (int) (ans % 10000003);
  }


  public static void main(String[] args) {
    System.out.println(seats("....x..xx...x.."));
  }

}
