package linklist;

import java.util.LinkedList;
import java.util.List;

public class Medium {


  public static ListNode mergeNodes(ListNode head) {
    ListNode temp = head;
    ListNode tempHead = head;
    int sum = 0;
    boolean flag = true;

    while (temp.val == 0) {
      temp = temp.next;
    }

    while (temp != null) {
      sum += temp.val;
      if (temp.val == 0) {
        tempHead.val = sum;
        tempHead.next = temp.next;
        tempHead = tempHead.next;
        sum = 0;
      }

      temp = temp.next;
    }

    return head;
  }


  /**
   * https://leetcode.com/problems/odd-even-linked-list/
   */

  public static ListNode oddEvenList(ListNode head) {

    if (head == null) {
      return head;
    }

    List<Integer> odd = new LinkedList<>();

    List<Integer> even = new LinkedList<>();

    int posn = 1;

    while (head != null) {

      if (posn % 2 == 0) {
        even.add(head.val);
      } else {
        odd.add(head.val);
      }

      head = head.next;
      posn++;
    }

    head = new ListNode(odd.get(0));
    ListNode temp = head;
    //temp = temp.next;

    int i = 1;
    int j = 0;

    while (i < odd.size()) {

      temp.next = new ListNode(odd.get(i));
      temp = temp.next;
      i++;
    }

    while (j < even.size()) {
      temp.next = new ListNode(even.get(j));
      temp = temp.next;
      j++;
    }

    return head;
  }

  public static void main(String[] args) {
    ListNode ob = LinkList.arrayToList(new Integer[]{0,1,0,3,0,2,2,0});
    mergeNodes(ob);
  }

}
