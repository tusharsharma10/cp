package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {

  public static double maximumValue(Pair[] items, int n, int w) {
    Arrays.sort(items, new Comparator<Pair>() {
      @Override
      public int compare(Pair t1, Pair t2) {
        Double one = Double.valueOf(t2.value) / Double.valueOf(t2.weight);
        Double two = Double.valueOf(t1.value) / Double.valueOf(t1.weight);
        if (one > two) {
          return 1;
        } else {
          return -1;
        }
      }
    });

    double maxProfit = 0;

    for (int i = 0; i < items.length; i++) {
      if (w <= 0) {
        break;
      } else {
        if (w - items[i].weight < 0) {
          double ratio = Double.valueOf(items[i].value) / Double.valueOf(items[i].weight);
          maxProfit += (ratio * w);
          w = 0;
        } else {
          maxProfit += items[i].value;
          w -= items[i].weight;
        }
      }
    }

    return maxProfit;
  }

  public static void main(String[] args) {
    maximumValue(new Pair[]{new Pair(50, 40),
        new Pair(40, 50), new Pair(90, 25), new Pair(120, 100),
        new Pair(10, 30), new Pair(240, 45)}, 6, 200);
  }
}

class Pair {

  int weight;
  int value;

  Pair(int weight, int value) {
    this.weight = weight;
    this.value = value;
  }

}
