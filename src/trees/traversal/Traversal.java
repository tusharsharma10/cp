package trees.traversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import sun.reflect.generics.tree.Tree;
import trees.TreeNode;
import trees.Util;

public class Traversal {

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    if (root == null) {
      return ans;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean reverse = false;

    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> temp = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        TreeNode tempNode = queue.poll();
        temp.add(tempNode.val);
        if (tempNode.left != null) {
          queue.add(tempNode.left);
        }

        if (tempNode.right != null) {
          queue.add(tempNode.right);
        }
      }
      if (reverse) {
        Collections.reverse(temp);
      }
      ans.add(temp);
      reverse = !reverse;
    }
    return ans;
  }

  public static List<Integer> getTopView(TreeNode root) {

    Set<Integer> widthSet = new HashSet<>();
    List<View> viewList = new ArrayList<>();

    List<Integer> ans = new ArrayList<>();

    if (root == null) {
      return ans;
    }

    View node = new View(root, 0);

    Queue<View> queue = new LinkedList<>();
    queue.add(node);

    while (!queue.isEmpty()) {
      View temp = queue.poll();

      if (!widthSet.contains(temp.posn)) {
        widthSet.add(temp.posn);
        viewList.add(temp);
      }

      if (temp.node.left != null) {
        queue.add(new View(temp.node.left, temp.posn - 1));
      }

      if (temp.node.right != null) {
        queue.add(new View(temp.node.right, temp.posn + 1));
      }

    }

    Collections.sort(viewList, new Comparator<View>() {
      @Override
      public int compare(View topView, View t1) {
        return topView.posn - t1.posn;
      }
    });

    return viewList.stream().map(v -> v.node.val).collect(Collectors.toList());

  }

  public static List<Integer> getBottomView(TreeNode root) {

    Set<Integer> widthSet = new HashSet<>();
    Map<Integer, TreeNode> viewMap = new HashMap<>();

    List<Integer> ans = new ArrayList<>();

    if (root == null) {
      return ans;
    }

    View node = new View(root, 0);

    Queue<View> queue = new LinkedList<>();
    queue.add(node);

    while (!queue.isEmpty()) {
      View temp = queue.poll();
      widthSet.add(temp.posn);
      viewMap.put(temp.posn, temp.node);

      if (temp.node.left != null) {
        queue.add(new View(temp.node.left, temp.posn - 1));
      }

      if (temp.node.right != null) {
        queue.add(new View(temp.node.right, temp.posn + 1));
      }

    }

    TreeMap<Integer, TreeNode> treeMap = new TreeMap<>(viewMap);

    for (TreeNode nodeX : treeMap.values()) {
      ans.add(nodeX.val);
    }
    return ans;
  }


  /**
   * https://leetcode.com/problems/binary-tree-right-side-view/submissions/1234514546/
   *
   * Binary Tree Right Side View
   */
  public static List<Integer> rightSideView(TreeNode root) {
    List<Integer> rightView = new ArrayList<>();
    if (root == null) {
      return rightView;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      TreeNode rightmost = null;

      // Traverse all nodes at the current level
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        // Keep updating the rightmost node at each level
        rightmost = node;
        // Enqueue the left and right children of the current node
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }

      // Add the value of the rightmost node to the result list
      rightView.add(rightmost.val);
    }

    return rightView;
  }


  public static void main(String[] args) {
    TreeNode root = Util.createBst2(new Integer[]{1, 2, 3, null, 4});
    System.out.println(rightSideView(root));
  }

  public int widthOfBinaryTree(TreeNode root) {
    Set<Integer> widthSet = new HashSet<>();

    Queue<View> queue = new LinkedList<>();
    queue.add(new View(root, 0));

    while (!queue.isEmpty()) {
      View view = queue.poll();
      widthSet.add(view.posn);

      if (view.node.left != null) {
        queue.add(new View(view.node.left, view.posn - 1));
      }

      if (view.node.right != null) {
        queue.add(new View(view.node.right, view.posn + 1));
      }

    }

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    for (int val : widthSet) {
      min = Math.min(val, min);
      max = Math.max(val, max);
    }

    return Math.abs(max - min);

  }


}

class View {

  TreeNode node;
  int posn;

  public View(TreeNode node, int posn) {
    this.node = node;
    this.posn = posn;
  }

}
