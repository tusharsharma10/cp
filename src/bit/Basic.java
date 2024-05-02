package bit;

public class Basic {


  /**
   * https://leetcode.com/problems/hamming-distance/
   */

  public static int hammingDistance(int x, int y) {
    return Integer.bitCount(x ^ y);
  }

  public static void main(String[] args) {

  }

}
