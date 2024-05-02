package interview;

import java.util.Arrays;
import linklist.ListNode;
import util.Util;

public class InterviewFavorite {

  public static void main(String[] args) {
    // System.out.println(maxProductBrute(new int[]{2, 3, -2, 4}));
//    ListNode head = Util.createListNode(new Integer[]{0, 1, 2});
//    int k = 4;
//    System.out.println(Util.listToArray(rotateRight(head, k)));

    System.out.println(max(7));
  }


  public static int max(int n) {
    int[] result = new int[n + 1];
    result[0] = 0;
    result[1] = 1;

    for (int i = 0; i < result.length; i++) {
      if (i % 2 == 0) {
        result[i] = result[i / 2];
      } else {
        result[i] = result[i / 2] + result[i / 2 + 1];
      }
    }
    for (int a : result) {
      System.out.print(a + " ");
    }

    Arrays.sort(result);
    return result[result.length - 1];
  }

  public static char fun(String s, String t) {
    char result = '\0';

    int[] arr = new int[26];

    for (int i = 0; i < t.length(); i++) {
      int c = t.charAt(i) - 'a';
      arr[c] += 1;
    }

    for (int i = 0; i < s.length(); i++) {
      int c = s.charAt(i) - 'a';
      arr[c] -= 1;
    }

    for (int i = 0; i < 26; i++) {
      if (arr[i] > 0) {
        return (char) (i + 'a');
      }
    }
    return result;
  }


  /**
   * https://leetcode.com/problems/rotate-list/ Given the head of a linked list, rotate the list to
   * the right by k places.
   */
  public static ListNode rotateRight(ListNode head, int k) {
    ListNode newHead = head;
    // edge case when we don't want to rotate
    if (head == null || head.next == null || k == 0) {
      return head;
    }
    ListNode temp = head;
    ListNode prev = null;

    int size = 0;
    while (temp != null) {
      size++;
      temp = temp.next;
    }
    temp = head;
    k = k % size;

    // edge case when we don't want to rotate
    if (k == 0) {
      return head;
    }

    int i = 0;

    while (temp != null) {

      if (i == size - k) {
        prev.next = null;
        newHead = temp;
      }

      if (temp.next == null) {
        temp.next = head;
        break;
      }
      i++;
      prev = temp;
      temp = temp.next;
    }

    return newHead;
  }


  /**
   * https://leetcode.com/problems/maximum-product-subarray/
   */

  public static int maxProductBrute(int[] nums) {
    int ans = Integer.MIN_VALUE;

    for (int i = 0; i < nums.length; i++) {
      int tempProduct = nums[i];
      if (ans < tempProduct) {
        ans = tempProduct;
      }
      for (int j = i + 1; j < nums.length; j++) {
        tempProduct *= nums[j];
        if (ans < tempProduct) {
          ans = tempProduct;
        }
      }

    }

    return ans;
  }

  public static int maxProduct(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int max = nums[0];
    int currMax = nums[0];
    int currMin = nums[0];

    for (int i = 1; i < nums.length; i++) {
      int currMaxCopy = currMax;
      currMax = Math.max(Math.max(nums[i], currMax * nums[i]), currMin * nums[i]);
      currMin = Math.min(Math.min(nums[i], currMin * nums[i]), currMaxCopy * nums[i]);
      max = Math.max(max, currMax);
    }

    return max;
  }
}


