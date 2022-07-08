package util;

import java.util.Random;

public class Util {

  public static void main(String[] args) {

    int arr[] = randomArrayGen(15, 90, 7);

    System.out.println();

  }

  public static int[] randomArrayGen(int minVal, int maxVal, int numVal) {
    Random r = new Random();
    return r.ints(numVal, minVal, maxVal).toArray();
  }


  private static int hcf(int a, int b) {

    if (b == 0) {
      return 1;
    }
    return hcf(b, a % b);
  }

  public static void printArr(int arr[]) {
    for (int x : arr) {
      System.out.print(x + " ");
    }
    System.out.println();
  }

}
