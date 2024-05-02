package twoPointer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import linklist.ListNode;
import org.junit.Test;

public class Medium {

  public static void main(String[] args) {
    System.out.println(reverseWords("a good   example"));
  }

  /**
   * https://leetcode.com/problems/reverse-words-in-a-string/
   */

  public static String reverseWords(String s) {
    s = s.trim();
    String arr[] = s.split("\\s");
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = arr.length - 1; i >= 0; i--) {
      if (arr[i] != "") {
        stringBuilder.append(arr[i]).append(" ");
      }
    }
    String str = stringBuilder.substring(0, stringBuilder.length() - 1);
    return str;
  }

  /**
   * https://leetcode.com/problems/partition-list/
   */
  public static ListNode partition(ListNode head, int x) {
    ListNode smallerHead = new ListNode(0);
    ListNode smaller = smallerHead;
    ListNode greaterHead = new ListNode(0);
    ListNode greater = greaterHead;

    while (head != null) {
      if (head.val < x) {
        smaller.next = head;
        smaller = smaller.next;
      } else {
        greater.next = head;
        greater = greater.next;
      }
      head = head.next;
    }

    greater.next = null;
    // join like this and return
    smaller.next = greaterHead.next;
    return smallerHead.next;
  }

  @Test
  public void removeElement() {

    int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};

    removeElement(nums, 2);
  }

  public int removeElement(int[] nums, int val) {
    int a = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != val) {
        nums[a] = nums[i];
        a++;
      }
    }
    return a;
  }


  @Test
  public void test2() {
    //compress(new char[]{'a','a','b','b','a','a','a'});
    compress(new char[]{'a', 'b', 'c'});
  }

  public int compress(char[] chars) {

    StringBuilder str = new StringBuilder();
    int counter = 1;

    if (chars.length == 1) {
      return 1;
    }

    for (int i = 0; i < chars.length - 1; i++) {

      if (chars[i] != chars[i + 1]) {
        str.append(chars[i]);
        if (counter > 1) {
          String valString = inserter(counter);
          str.append(valString);
        }
        counter = 1;
      } else {
        counter++;
      }
    }

    str.append(chars[chars.length - 1]);
    if (counter > 1) {
      String valString = inserter(counter);
      str.append(valString);
    }

    int i = 0;

    for (int j = 0; j < str.length(); j++) {
      chars[i] = str.charAt(j);
      i++;
    }

    return i;
  }

  public String inserter(int value) {
    StringBuilder str = new StringBuilder();

    while (value >= 1) {

      str.append(String.valueOf(value % 10));
      value /= 10;
    }
    return str.reverse().toString();
  }


}
