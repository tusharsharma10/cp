package monotonicStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class ValidParenthesis {

  /**
   * https://leetcode.com/problems/valid-parenthesis-string/description/
   */

  public static boolean checkValidString(String s) {

    Stack<Integer> openIdx = new Stack<>();
    Stack<Integer> starIdx = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        openIdx.push(i);
      } else if (c == '*') {
        starIdx.push(i);
      } else {
        if (!openIdx.isEmpty()) {
          openIdx.pop();
        } else if (!starIdx.isEmpty()) {
          starIdx.pop();
        } else {
          return false;
        }
      }
    }

    if (openIdx.isEmpty()) {
      return true;
    }

    if (!openIdx.isEmpty() && starIdx.isEmpty()) {
      return false;
    }

    while (!openIdx.isEmpty()) {
      if (starIdx.isEmpty()) {
        return false;
      } else {
        int openIdxPosn = openIdx.pop();
        int starIdxPosn = starIdx.pop();

        if (starIdxPosn < openIdxPosn) {
          return false;
        }
      }
    }

    return true;
  }


  public static List<Integer> MinimumCoins(int n) {
    List<Integer> ans = new ArrayList<>();

    while (n > 0) {
      if (n / 1000 >= 0 && n >= 1000) {
        n -= 1000;
        ans.add(1000);
      } else if (n / 500 >= 0 && n >= 500) {
        n -= 500;
        ans.add(500);
      } else if (n / 100 >= 0 && n >= 100) {
        n -= 100;
        ans.add(100);
      } else if (n / 50 >= 0 && n >= 50) {
        n -= 50;
        ans.add(50);
      } else if (n / 20 >= 0 && n >= 20) {
        n -= 20;
        ans.add(20);
      } else if (n / 10 >= 0 && n >= 10) {
        n -= 10;
        ans.add(10);
      } else if (n / 5 >= 0 && n >= 5) {
        n -= 5;
        ans.add(5);
      } else if (n / 2 >= 0 && n >= 2) {
        n -= 2;
        ans.add(2);
      } else if (n / 1 >= 0 && n >= 1) {
        n -= 1;
        ans.add(1);
      }
    }

    return ans;

  }

  public static void main(String[] args) {
    // System.out.println(checkValidString("(*))"));

    System.out.println(MinimumCoins(13));
  }

}
