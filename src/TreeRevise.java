import trees.TreeNode;
import trees.Util;

public class TreeRevise {

  static int max = Integer.MIN_VALUE;

  static int findMaxPathSum(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftMaxPath = Math.max(0, findMaxPathSum(root.left));
    int rightMaxPath = Math.max(0, findMaxPathSum(root.right));

    max = Math.max(max, leftMaxPath + rightMaxPath + root.val);

    return Math.max(leftMaxPath, rightMaxPath) + root.val;
  }

  static int maxPathSum(TreeNode root) {
    findMaxPathSum(root);
    return max;
  }

  public static void main(String[] args) {
    Integer[] arr = new Integer[]{1, 2, 3, null, null, 4, null, null, 5};
    TreeNode root = Util.createBst2(arr);
    System.out.println(maxPathSum(root));
  }

}
