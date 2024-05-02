package trees.medium;

import trees.TreeNode;

public class HeightOfBinaryTree {

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);

    return 1 + Math.max(left, right);
  }


  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }

    return isBalanced(root.right) && isBalanced(root.left) && Math.abs(
        maxDepth(root.right) - maxDepth(root.left)) <= 1;
  }


  int maxDiameter = 0;

  public int diameterOfBinaryTree(TreeNode root) {
    calculateDiameter(root);
    return maxDiameter;
  }

  private int calculateDiameter(TreeNode node) {
    if (node == null) {
      return 0;
    }

    // Calculate the height of the left subtree
    int leftHeight = calculateDiameter(node.left);

    // Calculate the height of the right subtree
    int rightHeight = calculateDiameter(node.right);

    // Update the maximum diameter found so far
    maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

    // Return the height of the current node
    return 1 + Math.max(leftHeight, rightHeight);
  }


  /**
   * https://leetcode.com/problems/symmetric-tree/
   *
   * Symmetric Tree
   */

  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isMirror(root.left, root.right);
  }

  private boolean isMirror(TreeNode leftSubtree, TreeNode rightSubtree) {
    // If both nodes are null, they are symmetric
    if (leftSubtree == null && rightSubtree == null) {
      return true;
    }
    // If one node is null and the other is not, they are not symmetric
    if (leftSubtree == null || rightSubtree == null) {
      return false;
    }
    // Check if the values of the nodes are equal and if their subtrees are symmetric
    return (leftSubtree.val == rightSubtree.val) && isMirror(leftSubtree.left, rightSubtree.right)
        && isMirror(leftSubtree.right, rightSubtree.left);
  }


  /**
   * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
   *
   * Lowest Common Ancestor of a Binary Tree
   */

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // Base case: If the root is null or matches either p or q, return root
    if (root == null || root == p || root == q) {
      return root;
    }

    // Recursively search for p and q in the left and right subtrees
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);

    // If both left and right are non-null, it means the current root is the LCA
    if (left != null && right != null) {
      return root;
    }
    // If only left is non-null, return left
    if (left != null) {
      return left;
    }
    // If only right is non-null, return right
    if (right != null) {
      return right;
    }
    // If neither left nor right is non-null, return null
    return null;
  }


}
