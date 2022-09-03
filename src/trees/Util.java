package trees;

import java.util.LinkedList;
import java.util.Queue;

public class Util {

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

}
