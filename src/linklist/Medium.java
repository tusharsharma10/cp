package linklist;

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

  public static void main(String[] args) {
    ListNode ob = LinkList.arrayToList(new Integer[]{0,1,0,3,0,2,2,0});
    mergeNodes(ob);
  }

}
