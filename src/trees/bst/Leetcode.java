package trees.bst;

import trees.TreeNode;

public class Leetcode {

}





class Solution1 {

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int l = maxDepth(root.left);
    int r = maxDepth(root.right);

    return Math.max(l, r) + 1;
  }
}

/**
 * https://leetcode.com/problems/recover-binary-search-tree/submissions/
 */
class Solution2 {

  TreeNode first = null;
  TreeNode second = null;
  TreeNode prev = null;

  public void recoverTree(TreeNode root) {

    inorder(root);

    int x = first.val;
    first.val = second.val;
    second.val = x;
  }


  private void inorder(TreeNode root) {

    if (root == null) {
      return;
    }

    inorder(root.left);

    if (prev != null && prev.val > root.val) {

      if (first == null) {
        first = prev;
      }

      second = root;
    }

    prev = root;
    inorder(root.right);
  }

  class Solution3 {
    public boolean isBalanced(TreeNode root) {
      if (root == null) return true;
      return recursion(root) != -1;
    }

    public int recursion(TreeNode root) {
      if (root == null) return 0;

      int left = recursion(root.left);
      if (left == -1) return -1;
      int right = recursion(root.right);
      if (right == -1) return -1;

      if (Math.abs(left - right) > 1) {
        return -1;
      }
      return 1 + Math.max(left, right);
    }
  }
}
