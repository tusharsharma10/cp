package trees.bst;

import java.util.LinkedList;
import java.util.Queue;
import trees.TreeNode;

public class BST {


  public TreeNode createBst(Integer arr[]) {

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

  /**
   * left=> root => right
   */
  public void inorder(TreeNode root) {
    if (root == null) {
      return;
    }
    inorder(root.left);
    System.out.print(root.val + ",");
    inorder(root.right);
  }

  /**
   * root => left => right
   */
  public void preorder(TreeNode root) {
    if (root == null) {
      return;
    }
    System.out.print(root.val + ",");
    preorder(root.left);
    preorder(root.right);
  }

  /**
   * left => right => root
   */
  public void postorder(TreeNode root) {
    if (root == null) {
      return;
    }
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.val + ",");
  }


  public void bfs(TreeNode root) {

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {

      TreeNode temp = q.poll();
      System.out.print(temp.val + ",");

      if (temp.left != null) {
        q.add(temp.left);
      }

      if (temp.right != null) {
        q.add(temp.right);
      }

    }

  }


  public int height(TreeNode root) {

    if (root == null) {
      return 0;
    }

    int lheight = height(root.left);
    int rheight = height(root.right);

    return Math.max(lheight, rheight) + 1;

  }

  public int minDepth(TreeNode root) {

    if (root == null) {
      return 0;
    }

    int height = 0;

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {

      int size = q.size();
      height++;

      // in 1 turn of queue all nodes of same height go inside
      for (int i = 0; i < size; i++) {
        TreeNode temp = q.poll();

        if (temp.left == null && temp.right == null) {
          return height;
        }

        if (temp.left != null) {
          q.add(temp.left);
        }

        if (temp.right != null) {
          q.add(temp.right);
        }
      }

    }
    return height;
  }

  int ans;

  public int diameterOfBinaryTree(TreeNode root) {

    ans = 1;

    depth(root);

    return ans - 1;
  }

  public int depth(TreeNode node) {

    if (node == null) {
      return 0;
    }

    int L = depth(node.left);

    int R = depth(node.right);

    ans = Math.max(ans, L + R + 1);

    return Math.max(L, R) + 1;

  }

}
