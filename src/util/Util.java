package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import linklist.ListNode;

public class Util {


  public static ListNode createListNode(Integer[] arr) {
    ListNode head = new ListNode(arr[0]);
    ListNode temp = head;

    for (int i = 1; i < arr.length; i++) {
      temp.next = new ListNode(arr[i]);
      temp = temp.next;
    }
    return head;
  }

  public static List<Integer> listToArray(ListNode head) {
    List<Integer> arr = new ArrayList<>();
    while (head != null) {
      arr.add(head.val);
      head = head.next;
    }
    return arr;
  }


  public static int[] randomArrayGen(int minVal, int maxVal, int numVal) {
    Random r = new Random();
    return r.ints(numVal, minVal, maxVal).toArray();
  }


  private static int hcf(int a, int b) {

    if (b == 0) {
      return 1;
    }
    return hcf(b, a % b);
  }

  public static void printArr(int arr[]) {
    for (int x : arr) {
      System.out.print(x + " ");
    }
    System.out.println();
  }

  public static void braceConverter() {

    String s = "[[8,25,1],[60,61,1],[90,1,1],[4,3,1],[100,22,0],[8,4,0],[1,100,1],[60,65,0],[22,60,1],[100,8,1],[52,90,1],[65,28,0]]\n";

    for (int i = 0; i < s.length(); i++) {
      s = s.replace('[', '{');
      s = s.replace(']', '}');

    }
    System.out.println(s);
  }

  public static void main(String[] args) {

    braceConverter();

  }

}
