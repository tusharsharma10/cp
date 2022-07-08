package trees.bst;

import trees.TreeNode;

public class MainTree {

  public static void main(String[] args) {
    BST b = new BST();
    TreeNode root = b.createBst(new Integer[]{33,12,56,8,25,27,28,29,30});

    System.out.println(b.height(root));

  }
}
