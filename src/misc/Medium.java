package misc;

import java.util.Arrays;
import java.util.Random;

public class Medium {



  public static void printArray(int a[]) {
    Arrays.stream(a).forEach(x -> {
      System.out.print(x + ",");
    });
    System.out.println();
  }

  public static void main(String[] args) {
  }

}
