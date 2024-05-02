package monotonicStack;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import linklist.ListNode;

public class Practice {


  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Map<Integer, Integer> nextGreaterMap = new HashMap<>();
    Deque<Integer> stack = new LinkedList<>();

    // Iterate through nums2 to find the next greater element for each element

    for (int num : nums2) {
      while (!stack.isEmpty() && stack.peek() < num) {
        nextGreaterMap.put(stack.pop(), num);
      }
      stack.push(num);
    }

    // For any remaining elements in the stack, they do not have a next greater element
    while (!stack.isEmpty()) {
      nextGreaterMap.put(stack.pop(), -1);
    }

    // Build the result array based on the map
    int[] result = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
      result[i] = nextGreaterMap.get(nums1[i]);
    }

    return result;
  }

  public int[] nextGreaterElements2(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    Arrays.fill(result, -1);

    Deque<Integer> stack = new LinkedList<>();

    // Iterate through nums array twice to cover all possible next greater elements
    for (int i = 0; i < 2 * n; i++) {
      // Treat nums array as circular
      int num = nums[i % n];
      while (!stack.isEmpty() && nums[stack.peek()] < num) {
        // If the current element is greater than the element at the top of the stack,
        // update the result array for the corresponding index
        result[stack.pop()] = num;
      }
      if (i < n) {
        // Push the index of the current element onto the stack only during the first iteration
        stack.push(i);
      }
    }

    return result;
  }

  public static ListNode removeNodes(ListNode head) {

    if (head == null || head.next == null) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    ListNode curr = head;

    Deque<ListNode> deque = new LinkedList<>();

    while (curr != null) {
      while (!deque.isEmpty() && curr.val > deque.peek().val) {
        deque.pop();
      }
      deque.push(curr);
      curr = curr.next;
    }

    ListNode temp = dummy;
    while (!deque.isEmpty()) {
      // Use pollLast() to maintain the order
      ListNode last = deque.pollLast();
      temp.next = last;
      temp = temp.next;
    }

    return dummy.next;
  }

  public static void main(String[] args) {

  }


}
