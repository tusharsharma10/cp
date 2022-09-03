package stack;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class Basic {


  /**
   * https://leetcode.com/problems/valid-parentheses/submissions/
   */
  public static boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    stack.push(s.charAt(0));
    int i = 1;

    while (i < s.length()) {

      if (!stack.isEmpty() && checkParen(stack.peek(), s.charAt(i))) {
        stack.pop();
      } else {
        stack.push(s.charAt(i));
      }
      i++;
    }

    if (!stack.isEmpty()) {
      return false;
    } else {
      return true;
    }
  }

  public static boolean checkParen(char a, char b) {

    if ((a == '(' && b == ')') || (a == '{' && b == '}') || (a == '[' && b == ']')) {
      return true;
    }

    return false;
  }

  /**
   * https://leetcode.com/problems/next-greater-element-i/
   */

  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    HashMap<Integer, Integer> map = new HashMap<>();
    Stack<Integer> stack = new Stack<>();
    stack.push(-1);
    map.put(nums2[nums2.length - 1], -1);
    for (int i = nums2.length - 1; i >= 0; i--) {

      while (!stack.isEmpty() && stack.peek() < nums2[i]) {

        stack.pop();
      }

      if (stack.isEmpty()) {
        map.put(nums2[i], -1);
      } else {
        map.put(nums2[i], stack.peek());
      }

      stack.push(nums2[i]);
    }

    int ans[] = new int[nums1.length];

    for (int i = 0; i < ans.length; i++) {

      ans[i] = map.get(nums1[i]);

    }

    return ans;
  }


  /**
   * https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
   */

  public static int maxDepth(String s) {

    Stack<Character> stack = new Stack<>();
    int count = 0;

    for (int i = 0; i < s.length(); i++) {

      if (s.charAt(i) == '(' || s.charAt(i) == ')') {
        stack.push(s.charAt(i));
      }

    }

    int max = 0;

    while (!stack.isEmpty()) {

      char c = stack.pop();
      if (c == ')') {
        count++;
        max = Math.max(max, count);
      } else if (c == '(') {
        count--;
      }

    }

    return max;
  }

  /**
   *
   */

  public static String removeDuplicates(String s) {
    Stack<Character> st = new Stack<>();
    st.push(s.charAt(0));
    for (int i = 1; i < s.length(); i++) {

      if (!st.isEmpty() && st.peek() == s.charAt(i)) {
        st.pop();
      } else {
        st.push(s.charAt(i));
      }
    }

    StringBuilder str = new StringBuilder();

    while (!st.isEmpty()) {
      str.append(st.pop());
    }

    return str.reverse().toString();
  }



  public static void main(String[] args) {
    System.out.println(removeDuplicates("abbaca"));
  }

}
