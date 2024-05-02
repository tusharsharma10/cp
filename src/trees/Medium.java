package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import sun.reflect.generics.tree.Tree;

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

      Stack<Node> curr = new Stack<>();
      for (int i = 1; i <= n; i++) {

        Node temp = stack.pop();
        ans.add(temp.data);
        if (flag) {
          if (temp.left != null) {
            curr.push(temp.left);
          }
          if (temp.right != null) {
            curr.push(temp.right);
          }
        } else {
          if (temp.right != null) {
            curr.push(temp.right);
          }
          if (temp.left != null) {
            curr.push(temp.left);
          }
        }
      }
      stack = curr;
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

      boolean hascurr = false;
      boolean hasNocurr = false;

      for (int i = 1; i <= n; i++) {

        Node temp = q.poll();

        if (temp.left == null && temp.right == null) {
          hasNocurr = true;
          // continue;
        }

        if (temp.left != null) {
          q.add(temp.left);
          hascurr = true;
        }

        if (temp.right != null) {
          q.add(temp.right);
          hascurr = true;
        }

        if (hascurr && hasNocurr) {
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

  static class pair {

    int non = 0;
    int sum = 0;
  }

  static int count = 0;

  public static int averageOfSubtree(TreeNode root) {
    aos(root);
    return count;
  }

  public static pair aos(TreeNode root) {
    if (root == null) {
      return new pair();
    }
    pair left = aos(root.left);
    pair right = aos(root.right);
    pair self = new pair();
    self.sum = left.sum + right.sum + root.val;
    self.non = left.non + right.non + 1;
    int avg = (self.sum) / (self.non);
    if (avg == root.val) {
      count++;
    }
    return self;
  }

  int sum = 0;

  public int sumEvenGrandparent(TreeNode root) {
    seg(root, null, null);
    return sum;
  }

  public void seg(TreeNode root, TreeNode parent, TreeNode grandParent) {
    if (root == null) {
      return;
    }
    if (grandParent != null && (grandParent.val & 1) == 0) {
      sum += root.val;
    }
    seg(root.left, root, parent);
    seg(root.right, root, parent);
  }

  static int sumA = 0;
  public static TreeNode bstToGst(TreeNode root) {
    sumA = totalSum(root);
    changeTree(root);
    return root;
  }
  public static int totalSum(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = totalSum(root.left);
    int right = totalSum(root.right);
    return left + right + root.val;
  }
  public static void changeTree(TreeNode root) {
    if (root == null) {
      return;
    }
    changeTree(root.left);
    int temp = root.val;
    root.val = sumA;
    sumA -= temp;
    changeTree(root.right);
  }

  /**
   * https://leetcode.com/problems/count-good-nodes-in-binary-tree/
   */
  public static int goodNodes(TreeNode root) {
    int max = Integer.MIN_VALUE;
    return totalNode(root, max);
  }

  public static int totalNode(TreeNode root, int max) {
    if (root == null) {
      return 0;
    }

    int currentCount = root.val >= max ? 1 : 0;
    currentCount += totalNode(root.left, Math.max(root.val, max));
    currentCount += totalNode(root.right, Math.max(root.val, max));
    return currentCount;
  }


  /**
   * https://leetcode.com/problems/create-binary-tree-from-descriptions/
   */

  public static TreeNode createBinaryTree(int[][] descriptions) {
    TreeNode root = null;

    class Child {

      Integer leftChild;
      Integer rightChild;

      public Child() {
        leftChild = null;
        rightChild = null;
      }

      public Child(Integer leftChild, Integer rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
      }
    }

    Set<Integer> childSet = new HashSet<>();

    for (int i = 0; i < descriptions.length; i++) {

      childSet.add(descriptions[i][1]);

    }

    int rootVal = -1;

    for (int i = 0; i < descriptions.length; i++) {

      if (!childSet.contains(descriptions[i][0])) {
        root = new TreeNode(descriptions[i][0]);
      }

    }

    Map<Integer, Child> parentMap = new HashMap<>();

    for (int i = 0; i < descriptions.length; i++) {

      if (!parentMap.containsKey(descriptions[i][0])) {
        Child c = new Child();

        if (descriptions[i][2] == 1) {
          c.leftChild = descriptions[i][1];
        } else {
          c.rightChild = descriptions[i][1];
        }

        parentMap.put(descriptions[i][0], c);
      } else {

        Child c = parentMap.get(descriptions[i][0]);
        if (descriptions[i][2] == 1) {
          c.leftChild = descriptions[i][1];
        } else {
          c.rightChild = descriptions[i][1];
        }
        parentMap.put(descriptions[i][0], c);
      }

    }

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {

      TreeNode temp = q.poll();

      if (!parentMap.containsKey(temp.val)) {
        continue;
      }

      if (parentMap.get(temp.val).leftChild != null) {
        TreeNode left = new TreeNode(parentMap.get(temp.val).leftChild);
        temp.left = left;
        q.add(left);
      }

      if (parentMap.get(temp.val).rightChild != null) {
        TreeNode right = new TreeNode(parentMap.get(temp.val).rightChild);
        temp.right = right;
        q.add(right);
      }

    }

    return root;
  }






  public static void bfs(int start, int[][] mat) {

    Queue<Integer> q = new LinkedList<>();
    boolean visited[] = new boolean[mat.length];
    int v = mat.length;
    q.add(start);
    visited[start] = true;

    while (!q.isEmpty()) {

      int x = q.poll();
      System.out.print(x + "->");

      for (int i = 0; i < v; i++) {
        if (mat[x][i] == 1 && !visited[i]) {

          q.add(i);
          visited[i] = true;

        }
      }

    }
    System.out.println();

  }


  public static void treeToArray(TreeNode root){

    List<Integer> list = new ArrayList<>();

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    list.add(root.val);
    while (!q.isEmpty()) {

      TreeNode temp = q.poll();

      if (temp.left != null) {
        q.add(temp.left);
        list.add(temp.left.val);
      } else {
        list.add(null);
      }

      if (temp.right != null) {
        q.add(temp.right);
        list.add(temp.right.val);
      } else {
        list.add(null);
      }

    }

    System.out.println(list);
  }

  /**
   * https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
   */
  public static TreeNode lcaDeepestLeaves(TreeNode root) {
    if (root.left == null && root.right == null) {
      return root;
    }
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    int height = height(root);
    int level = 0;

    int n1 = -1;
    int n2 = -1;

    while (!q.isEmpty()) {
      int n = q.size();

      if (level == height - 1) {
        if (n > 1) {
          n1 = q.poll().val;
          while (q.size() > 1) {
            q.poll();
          }

          n2 = q.poll().val;
        } else if (n == 1) {
          return new TreeNode(q.poll().val);
        }
        break;
      }

      for (int i = 1; i <= n; i++) {

        if (q.isEmpty()) {
          break;
        }

        TreeNode temp = q.poll();
        //n1 = temp.val;
        if (temp.left != null) {
          q.add(temp.left);
        }

        if (temp.right != null) {
          q.add(temp.right);
        }
      }

      level++;
    }

    return lca(root, n1, n2);

  }

  private static int height(TreeNode root) {

    if (root == null) {
      return 0;
    }

    int l = height(root.left);
    int r = height(root.right);

    return Math.max(l, r) + 1;
  }

  private static TreeNode lca(TreeNode root, int n1, int n2) {

    if (root == null) {
      return null;
    }

    if (root.val == n1 || root.val == n2) {
      return root;
    }

    TreeNode left = lca(root.left, n1, n2);
    TreeNode right = lca(root.right, n1, n2);

    if (left == null) {
      return right;
    }

    if (right == null) {
      return left;
    }
    return root;
  }


  /**
   * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
   */
  public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

    List<List<Integer>> ans = new ArrayList<>();

    if (root == null) {
      return ans;
    }

    boolean flag = false;

    Stack<TreeNode> stack = new Stack<>();

    stack.add(root);


    while (!stack.isEmpty()) {

      int n = stack.size();
      flag = !flag;

      Stack<TreeNode> curr = new Stack<>();
      List<Integer> list = new ArrayList<>();

      for (int i = 1; i <= n; i++) {
        TreeNode temp = stack.pop();
        list.add(temp.val);
        if (flag) {
          if (temp.left != null) {
            curr.push(temp.left);
          }
          if (temp.right != null) {
            curr.push(temp.right);
          }
        } else {
          if (temp.right != null) {
            curr.push(temp.right);
          }
          if (temp.left != null) {
            curr.push(temp.left);
          }
        }

      }

      ans.add(list);
      stack = curr;

    }

    return ans;
  }


  /**
   * https://leetcode.com/problems/kth-smallest-element-in-a-bst/submissions/
   */

  public int kthSmallest(TreeNode root, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    inorder(root, pq);

    for (int i = 1; i < k; i++) {

      pq.poll();

    }

    return pq.poll();
  }

  public void inorder(TreeNode root, PriorityQueue<Integer> pq) {

    if (root == null) {
      return;
    }

    inorder(root.left, pq);
    pq.add(root.val);
    inorder(root.right, pq);

  }


  /**
   * https://leetcode.com/problems/path-sum/
   */

  public static boolean hasPathSum(TreeNode root, int targetSum) {
    Set<Integer> allPathsSum = new HashSet<>();
    calculateSum(root, allPathsSum, 0);

    if (allPathsSum.contains(targetSum)) {
      return true;
    }
    return false;
  }

  private static void calculateSum(TreeNode root, Set<Integer> allPathsSum, int currSum) {

    if (root == null) {
      return;
    }

    if (root.left == null && root.right == null) {
      allPathsSum.add(root.val + currSum);
      return;
    }

    calculateSum(root.left, allPathsSum, currSum + root.val);
    calculateSum(root.right, allPathsSum, currSum + root.val);
  }

  /**
   *  https://leetcode.com/problems/binary-tree-paths/
   */

  public static List<String> binaryTreePaths(TreeNode root) {
    List<String> ans = new ArrayList<>();
    List<List<Integer>> list = new ArrayList<>();
    recPaths(root, list, new ArrayList<>());

    for (List<Integer> l : list) {

      StringBuilder str = new StringBuilder();

      for (int i = 0; i < l.size(); i++) {
        int x = l.get(i);
        if (i == l.size() - 1) {
          str.append(x);
          break;
        }

        str.append(x);
        str.append("->");
      }

      ans.add(str.toString());
    }

    return ans;
  }

  private static void recPaths(TreeNode root, List<List<Integer>> list, List<Integer> path) {

    if (root == null) {
      return;
    }

    if (root.left == null && root.right == null) {
      path.add(root.val);
      List<Integer> temp = new ArrayList<>(path);
      list.add(temp);
      path.remove(path.size() - 1);
      return;
    }

    path.add(root.val);
    recPaths(root.left, list, path);
    recPaths(root.right, list, path);
    path.remove(path.size() - 1);
  }

  /**
   * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/submissions/
   */

  /**
   * https://leetcode.com/problems/binary-tree-tilt/
   */

  static int sum2 = 0;
  static int left = 0;
  static int right = 0;

  public static int findTilt(TreeNode root) {
    rec(root);
    return sum2;
  }

  private static int rec(TreeNode root) {

    if (root == null) {
      return 0;
    }

    int leftTree = rec(root.left);
    int rightTree = rec(root.right);

    sum2 += Math.abs(leftTree - rightTree);
    int total = leftTree + rightTree + root.val;

    return total;

  }


  public static void main(String[] args) {
//    TreeNode root = Util.createBst2(new Integer[]{5, 9, 6, null, null, 5, 8});
//    System.out.println(goodNodes(root));
//    TreeNode root = createBinaryTree(new int[][]{{8,25,1},{60,61,1},{90,1,1},{4,3,1},{100,22,0},{8,4,0},{1,100,1},{60,65,0},{22,60,1},{100,8,1},{52,90,1},{65,28,0}});
//    treeToArray(root);

    //  TreeNode root = Util.createBst2(new Integer[]{1,2,3,null,4,6,null,15,5,10,null,null,null,null,7,11,null,8,12,null,null,null,9,13,14});
    TreeNode root = Util.createBst2(new Integer[]{4,2,9,3,5,null,7});

    System.out.println(findTilt(root));
  }

}

