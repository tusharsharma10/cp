package bfs;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import trees.TreeNode;

public class BFS {


  public static void main(String[] args) {

  }

  /**
   * https://leetcode.com/problems/binary-tree-right-side-view/
   */
  public static List<Integer> rightSideView(TreeNode root) {
    List<Integer> rightView = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        TreeNode temp = q.poll();
        // this means this is the right most node
        if (i == size - 1) {
          rightView.add(temp.val);
        }
        if (temp.left != null) {
          q.add(temp.left);
        }
        if (temp.right != null) {
          q.add(temp.right);
        }
      }
    }
    return rightView;
  }

  /**
   * Getting same level nodes strategy
   * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
   */
  public Node connect(Node root) {
    if (root == null) {
      return null;
    }
    Queue<Node> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        Node cur = q.poll();
        // setting next element
        if (i == size - 1) {
          cur.next = null;
        } else {
          cur.next = q.peek();
        }
        // adding elements in queue
        if (cur.left != null) {
          q.offer(cur.left);
        }
        if (cur.right != null) {
          q.offer(cur.right);
        }
      }
    }
    return root;
  }


  class Node {

    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }

  }

}
