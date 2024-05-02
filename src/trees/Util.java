package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import linklist.ListNode;

public class Util {


  public static List<Integer> treeNodeToArray(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      TreeNode temp = q.poll();
      list.add(temp.val);
      if (temp.left != null) {
        q.add(temp.left);
      } else {
        list.add(null);
      }

      if (temp.right != null) {
        q.add(temp.right);
      } else {
        list.add(null);
      }
    }

    return list;
  }


  public static Node createBst(Integer arr[]) {

    //arr[0] = root
    Node root = new Node(arr[0]);

    Queue<Node> q = new LinkedList<>();
    q.add(root);
    int i = 0;

    while (!q.isEmpty() && i < arr.length) {

      Node temp = q.poll();

      if (i + 1 < arr.length && arr[i + 1] != null) {
        temp.left = new Node(arr[i + 1]);
        q.add(temp.left);
      }

      if (i + 2 < arr.length && arr[i + 2] != null) {
        temp.right = new Node(arr[i + 2]);
        q.add(temp.right);
      }

      i += 2;

    }
    return root;
  }

  public static TreeNode createBst2(Integer arr[]) {

    //arr[0] = root
    TreeNode root = new TreeNode(arr[0]);

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    int i = 0;

    while (!q.isEmpty() && i < arr.length) {

      TreeNode temp = q.poll();

      if (i + 1 < arr.length && arr[i + 1] != null) {
        temp.left = new TreeNode(arr[i + 1]);
        q.add(temp.left);
      }

      if (i + 2 < arr.length && arr[i + 2] != null) {
        temp.right = new TreeNode(arr[i + 2]);
        q.add(temp.right);
      }

      i += 2;

    }
    return root;
  }


  public static List<Integer> createBstArray(TreeNode root) {
    List<Integer> tree = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    tree.add(root.val);
    while (!q.isEmpty()) {

      TreeNode temp = q.poll();

      if (temp.left != null) {
        q.add(temp.left);
        tree.add(temp.left.val);
      } else {
        tree.add(null);
      }

      if (temp.right != null) {
        q.add(temp.right);
        tree.add(temp.right.val);
      } else {
        tree.add(null);
      }

    }

    return tree;
  }
}
