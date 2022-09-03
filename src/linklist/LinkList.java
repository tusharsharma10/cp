package linklist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class Node {

  public int val;
  public Node prev;
  public Node next;
  public Node child;
}

public class LinkList {

  public static ListNode arrayToList(Integer arr[]) {
    ListNode list = new ListNode(arr[0]);
    ListNode temp = list;
    for (int i = 1; i < arr.length; i++) {
      if (arr != null) {
        temp.next = new ListNode(arr[i]);
        temp = temp.next;
      }
    }

    return list;
  }


  static ListNode real = null;

  public static ListNode reverseList(ListNode head) {

    ListNode curr = head;
    ListNode prev = null;
    revList(curr, prev);

    return real;
  }

  static void revList(ListNode curr, ListNode prev) {

    if (curr.next == null) {
      real = curr;
      curr.next = prev;
      return;
    }

    revList(curr.next, curr);
    curr.next = prev;
  }


  public static ListNode reverseBetween(ListNode head, int left, int right) {

    int count = 0;

    ListNode temp = head;

    while (temp != null) {
      temp = temp.next;
      count++;
    }

    temp = head;
    int arr[] = new int[count];
    for (int i = 0; i < count; i++) {

      arr[i] = temp.val;
      temp = temp.next;

    }

    reverse(arr, left, right);

    ListNode curr = new ListNode(arr[0]);

    temp = curr;

    for (int i = 1; i < arr.length; i++) {
      temp.next = new ListNode(arr[i]);
      temp = temp.next;
    }

    return curr;

  }

  static void reverse(int[] arr, int l, int h) {

    while (h > l) {

      int temp = arr[l];
      arr[l] = arr[h];
      arr[h] = temp;

      l++;
      h--;
    }

  }

  public static boolean hasCycle(ListNode head) {

    if (head == null || head.next == null) {
      return false;
    }

    ListNode slow = head;
    ListNode fast = head;

    while (slow.next != null && fast != null && fast.next != null && fast.next.next != null) {

      slow = slow.next;
      fast = fast.next.next;

      if (fast == slow) {
        return true;
      }

    }

    return false;
  }


  /**
   * https://leetcode.com/problems/linked-list-cycle-ii/submissions/
   */
  public static ListNode detectCycle(ListNode head) {

    if (head == null) {
      return null;
    }

    ListNode slow = head;
    ListNode fast = head;

    do {
      if (slow.next != null) {
        slow = slow.next;
      } else {
        return null;
      }

      if (fast.next != null && fast.next.next != null) {
        fast = fast.next.next;
      } else {
        return null;
      }
    } while (slow != fast);

    slow = head;

    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }

    return fast;


  }

  public static ListNode deleteDuplicates(ListNode head) {
    ListNode temp = head;

    while (temp != null) {

      if (temp.next != null && temp.val == temp.next.val) {

        ListNode temp2 = temp;

        while (temp2.next != null && temp2.val == temp2.next.val) {
          temp2 = temp2.next;
        }

        temp.next = temp2.next;

      }

      if (temp == null) {
        break;
      }

      temp = temp.next;

    }

    return head;
  }

  /**
   * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
   */

  public static ListNode leaveDistinct(ListNode head) {

    Map<Integer, Integer> map = new LinkedHashMap<>();

    if (head == null) {
      return null;
    }

    ListNode temp = head;

    while (temp != null) {

      if (map.containsKey(temp.val)) {
        int val = map.get(temp.val) + 1;
        map.put(temp.val, val);
      } else {
        map.put(temp.val, 1);
      }

      temp = temp.next;

    }

    List<Integer> ans = new ArrayList<>();

    map.entrySet().forEach(e -> {
      if (e.getValue() == 1) {
        ans.add(e.getKey());
      }
    });

    if (ans.isEmpty()) {
      return null;
    }

    head = new ListNode(ans.get(0));
    temp = head;

    for (int i = 1; i < ans.size(); i++) {

      temp.next = new ListNode(ans.get(i));
      temp = temp.next;

    }

    return head;
  }

  public static void reorderList(ListNode head) {

    List<Integer> ans = new ArrayList<>();

    ListNode temp = head;

    while (temp != null) {

      ans.add(temp.val);
      temp = temp.next;

    }

    int i = 0;
    int j = ans.size() - 1;
    int curr = 0;

    temp = head;

    while (curr < ans.size()) {

      if (curr % 2 == 1) {

        temp.val = ans.get(j);
        j--;
      } else {
        temp.val = ans.get(i);
        i++;
      }

      temp = temp.next;
      curr++;
    }

  }

  /**
   * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/submissions/
   */

  public ListNode deleteMiddle1(ListNode head) {

    int count = 0;

    ListNode temp = head;

    while (temp != null) {

      temp = temp.next;
      count++;
    }

    temp = head;

    int i = 0;

    if (count == 1) {
      return null;
    }

    ListNode prev = temp;
    int size = count / 2;
    while (i < size) {

      prev = temp;
      temp = temp.next;
      i++;
    }

    prev.next = temp.next;

    return head;
  }

  public static ListNode deleteMiddle2(ListNode head) {

    ListNode slow = head;
    ListNode fast = head;
    ListNode prev = slow;

    if (head.next == null) {
      return null;
    }

    while (fast != null && fast.next != null) {

      prev = slow;
      fast = fast.next.next;
      slow = slow.next;

    }

    prev.next = slow.next;

    return head;
  }

  /**
   * https://leetcode.com/problems/remove-linked-list-elements/
   */
  public static ListNode removeElements(ListNode head, int val) {

    while (head != null && head.val == val) {
      head = head.next;
    }

    ListNode temp = head;
    ListNode prev = temp;

    while (temp != null) {

      if (temp.val == val) {

        while (temp != null && temp.val == val) {
          temp = temp.next;
        }

        prev.next = temp;
      }

      if (temp == null) {
        break;
      }

      prev = temp;
      temp = temp.next;
    }

    return head;
  }


  /**
   * https://leetcode.com/problems/intersection-of-two-linked-lists/
   */

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

    ListNode temp = headA;

    ListNode ans = null;

    while (temp != null) {

      temp.val = -temp.val;
      temp = temp.next;
    }

    while (headB != null) {

      if (headB.val < 0) {
        ans = headB;
        break;
      }

      headB = headB.next;
    }

    while (headA != null) {

      headA.val = -headA.val;
      headA = headA.next;
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/sort-list/
   */

  private static ListNode sortList(ListNode head) {

    if (head == null || head.next == null) {
      return head;
    }

    ListNode temp = null;
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {

      temp = slow;
      slow = slow.next;
      fast = fast.next.next;

    }

    temp.next = null;

    ListNode first = sortList(head);
    ListNode second = sortList(slow);

    return merge(first, second);

  }

  private static ListNode merge(ListNode first, ListNode second) {

    ListNode tempHead = new ListNode(0);
    ListNode curr = tempHead;

    while (first != null && second != null) {

      if (first.val < second.val) {

        curr.next = first;
        first = first.next;
      } else {
        curr.next = second;
        second = second.next;
      }
      curr = curr.next;
    }

    while (first != null) {
      curr.next = first;
      first = first.next;
      curr = curr.next;
    }

    while (second != null) {
      curr.next = second;
      second = second.next;
      curr = curr.next;
    }

    return tempHead.next;
  }


  /**
   * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
   */

  public Node flatten(Node head) {
    return new Node();
  }

  public static void main(String[] args) {
    Integer arr[] = {1, 2, 7, 3, 19, 25, 6};
    ListNode list = arrayToList(arr);
    list = sortList(list);
    System.out.println();

  }

}


