package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Medium {


  /**
   * https://practice.geeksforgeeks.org/problems/duplicate-subtree-in-binary-tree/1 Given a binary
   * tree, find out whether it contains a duplicate sub-tree of size two or more, or not. size =
   * height
   */

  public static int dupSub(Node root) {

    HashMap<String, Integer> map = new HashMap<>();

    rec(root, map);

    // using string to find the pattern and finding the count of the same string
    for (String x : map.keySet()) {
      int val = map.get(x);
      if (val >= 2 && x.length() >= 2) {
        return 1;
      }
    }
    return 0;
  }

  public static String rec(Node root, HashMap<String, Integer> map) {

    if (root == null) {
      return "$";
    }

    String s = "";
    if (root.left == null && root.right == null) {
      s = String.valueOf(root.data);
      return s;
    }

    s = s + String.valueOf(root.data);
    s = s + rec(root.left, map);
    s = s + rec(root.right, map);

    if (!map.containsKey(s)) {
      map.put(s, 1);
    } else {

      int val = map.get(s);
      val++;
      map.put(s, val);
    }

    return s;
  }

  /**
   * https://practice.geeksforgeeks.org/problems/check-mirror-in-n-ary-tree1528/1
   *
   * Given two n-ary trees. Check if they are mirror images of each other or not. You are also given
   * e denoting the number of edges in both trees, and two arrays, A[] and B[]. Each array has 2*e
   * space separated values u,v denoting an edge from u to v for the both trees.
   */

  public static int checkMirrorTree(int n, int e, int[] A, int[] B) {
    return 1;
  }


  public static ArrayList<Integer> zigZagTraversal(Node root) {

    Stack<Node> stack = new Stack<>();
    ArrayList<Integer> ans = new ArrayList<>();

    if (root == null) {
      return ans;
    }
    // false -> print right to left - push left to right
    boolean flag = false;
    stack.push(root);

    while (!stack.isEmpty()) {
      // no. of nodes in the level
      int n = stack.size();
      flag = !flag;

      Stack<Node> child = new Stack<>();
      for (int i = 1; i <= n; i++) {

        Node temp = stack.pop();
        ans.add(temp.data);
        if (flag) {
          if (temp.left != null) {
            child.push(temp.left);
          }
          if (temp.right != null) {
            child.push(temp.right);
          }
        } else {
          if (temp.right != null) {
            child.push(temp.right);
          }
          if (temp.left != null) {
            child.push(temp.left);
          }
        }
      }
      stack = child;
    }
    return ans;
  }

  /**
   * https://practice.geeksforgeeks.org/problems/leaf-at-same-level/1
   */

  public static boolean check(Node root) {

    Queue<Node> q = new LinkedList<>();
    boolean flag = true;

    if (root == null) {
      return true;
    }
    q.add(root);

    while (!q.isEmpty()) {
      int n = q.size();

      boolean hasChild = false;
      boolean hasNoChild = false;

      for (int i = 1; i <= n; i++) {

        Node temp = q.poll();

        if (temp.left == null && temp.right == null) {
          hasNoChild = true;
          // continue;
        }

        if (temp.left != null) {
          q.add(temp.left);
          hasChild = true;
        }

        if (temp.right != null) {
          q.add(temp.right);
          hasChild = true;
        }

        if (hasChild && hasNoChild) {
          return false;
        }
      }
    }
    return flag;
  }

  /**
   * https://practice.geeksforgeeks.org/problems/sum-of-the-longest-bloodline-of-a-tree/1
   */
  public static int sumOfLongRootToLeafPath(Node root) {

    // ans[0] => keeping height
    // ans[1] => keeping the value
    ArrayList<Integer> ans = longPath(root);
    return ans.get(1);
  }

  public static ArrayList<Integer> longPath(Node root) {
    ArrayList<Integer> ans = new ArrayList<Integer>();
    if (root == null) {
      ans.add(0);
      ans.add(0);
      return ans;
    }
    if (root.left == null && root.right == null) {
      ans.add(1);
      ans.add(root.data);
      return ans;
    }
    ArrayList<Integer> l = longPath(root.left);
    ArrayList<Integer> r = longPath(root.right);

    if (l.get(0) > r.get(0)) {
      ans.add(l.get(0) + 1);
      ans.add(l.get(1) + root.data);
      return ans;
    }
    ans.add(r.get(0) + 1);
    ans.add(r.get(1) + root.data);
    return ans;

  }


  /**
   * https://practice.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-binary-tree/1
   */

  Node lca(Node root, int n1, int n2) {

    if (root == null) {
      return null;
    }

    if (root.data == n1 || root.data == n2) {
      return root;
    }

    Node left = lca(root.left, n1, n2);
    Node right = lca(root.right, n1, n2);

    if (left == null) {
      return right;
    }

    if (right == null) {
      return left;
    }

    return root;

  }

  public static int averageOfSubtree(TreeNode root) {

    if (root == null) {
      return 0;
    }

    List<Integer> count = new ArrayList<>(1);
    List<Integer> sum = new ArrayList<>(1);
    List<Integer> countNode = new ArrayList<>(1);
    count.add(0);
    countNode.add(1);
    sum.add(root.val);
    avgRec(root, count, sum, countNode);
    return count.get(0);
  }

  public static void avgRec(TreeNode root, List<Integer> count, List<Integer> sum,
      List<Integer> countNode) {

    if (root == null) {
      return;
    }

    avgRec(root.left, count, sum, countNode);
    avgRec(root.right, count, sum, countNode);

    int sum1 = sum.get(0);
    int countNode1 = countNode.get(0);

    if (sum1 / countNode1 == root.val) {
      count.set(0, count.get(0) + 1);
    }

    sum.set(0, root.val + sum.get(0));
    countNode.set(0, countNode.get(0) + 1);
  }

  public static void main(String[] args) {
    Node root = Util.createBst(new Integer[]{4, 2, 5, 7, 1, 2, 3, null, null, 6});
    System.out.println(sumOfLongRootToLeafPath(root));
  }


}

