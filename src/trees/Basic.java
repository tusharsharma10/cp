package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class Basic {


  /**
   * https://practice.geeksforgeeks.org/problems/mirror-tree/1
   */
  void mirror(Node node) {
    node = mirrorRec(node);
  }

  Node mirrorRec(Node root) {

    if (root == null) {
      return null;
    }

    Node left = mirrorRec(root.left);
    Node right = mirrorRec(root.right);

    root.left = right;
    root.right = left;

    return root;

  }


  /**
   * https://practice.geeksforgeeks.org/problems/left-view-of-binary-tree/1
   */

  public static ArrayList<Integer> leftView(Node root) {
    ArrayList<Integer> ans = new ArrayList<>();

    if (root == null) {
      return ans;
    }

    Queue<Node> queue = new LinkedList<>();

    queue.add(root);

    while (!queue.isEmpty()) {

      // number of nodes at current level
      int n = queue.size();

      // Traverse all nodes of current level
      for (int i = 1; i <= n; i++) {
        Node temp = queue.poll();

        // Print the left most element at
        // the level
        if (i == 1) {
          ans.add(temp.data);
        }

        // Add left node to queue
        if (temp.left != null) {
          queue.add(temp.left);
        }

        // Add right node to queue
        if (temp.right != null) {
          queue.add(temp.right);
        }
      }
    }

    return ans;
  }


  /**
   * ArrayList<Integer> rightView(Node node) {
   *
   *     }
   */

  public static ArrayList<Integer> rightView(Node node) {

    if(node == null){
      return new ArrayList<>();
    }

    ArrayList<Integer> ans = new ArrayList<>();
    Queue<Node> q = new LinkedList<>();
    q.add(node);

    while (!q.isEmpty()) {

      int n = q.size();

      for (int i = 1; i <= n; i++) {

        Node temp = q.poll();

        if (i == n) {
          ans.add(temp.data);
        }

        if (temp.left != null) {
          q.add(temp.left);
        }

        if (temp.right != null) {
          q.add(temp.right);
        }
      }

    }

    return ans;
  }


  /**
   * https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1
   */

  public static ArrayList<Integer> topView(Node root) {

    // position vs value
      TreeMap<Integer, Integer> map = new TreeMap<>();

      if (root == null) {

        return new ArrayList<>();
      }

      class Point {

        Node root;
        int posn;

        Point(int posn, Node root) {

          this.posn = posn;
          this.root = root;
        }

      }

      Queue<Point> q = new LinkedList<>();

      q.add(new Point(0, root));

      while (!q.isEmpty()) {

        Point temp = q.poll();

        if (!map.containsKey(temp.posn)) {

          map.put(temp.posn, temp.root.data);

        }

        if (temp.root.left != null) {

          Point left = new Point(temp.posn - 1, temp.root.left);
          q.add(left);
        }

        if (temp.root.right != null) {

          Point right = new Point(temp.posn + 1, temp.root.right);
          q.add(right);
        }

      }

      ArrayList<Integer> ans = new ArrayList<>();

      for (Integer k : map.keySet()) {

        int val = map.get(k);

        ans.add(val);

      }

      return ans;
    }

  /**
   * https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
   */

  public static ArrayList<Integer> bottomView(Node root) {

    TreeMap<Integer, Integer> map = new TreeMap<>();
    ArrayList<Integer> ans = new ArrayList<>();

    class Point {

      int posn;
      Node node;

      Point(int posn, Node node) {
        this.posn = posn;
        this.node = node;
      }

    }

    Queue<Point> q = new LinkedList<>();
    q.add(new Point(0, root));

    while (!q.isEmpty()) {

      Point temp = q.poll();

      map.put(temp.posn, temp.node.data);

      if (temp.node.left != null) {
        q.add(new Point(temp.posn - 1, temp.node.left));
      }

      if (temp.node.right != null) {
        q.add(new Point(temp.posn + 1, temp.node.right));
      }

    }

    for (int key : map.keySet()) {
      int data = map.get(key);
      ans.add(data);
    }

    return ans;
  }

  /**
   * https://practice.geeksforgeeks.org/problems/check-for-balanced-tree/1#
   */

  public static boolean isBalanced(Node root) {

    if (root == null) {
      return true;
    }

    int lHeight = heightOfTree(root.left);
    int rHeight = heightOfTree(root.right);

    if (Math.abs(lHeight - rHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right)) {
      return true;
    }

    return false;
  }

  /**
   * Height of binary tree
   */

  public static int heightOfTree(Node root) {

    if (root == null) {
      return 0;
    }

    int lHeight = heightOfTree(root.left);
    int rHeight = heightOfTree(root.right);

    return Math.max(lHeight, rHeight) + 1;

  }

  /**
   * https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1
   */
  public static Node binaryTreeToDoublyLinkedList(Node root) {

    ArrayList<Node> list = new ArrayList<>();

    binToDll(root, list);

    Node head = new Node(list.get(0).data);

    Node temp = head;

    for (int i = 1; i < list.size(); i++) {

      Node next = list.get(i);
      temp.right = next;
      next.left = temp;

      temp = next;

    }

    return head;
  }

  private static void binToDll(Node root, ArrayList<Node> list) {

    if (root == null) {
      return;
    }

    binToDll(root.left, list);

    list.add(root);

    binToDll(root.right, list);

  }




  public static void main(String[] args) {

  }

}




