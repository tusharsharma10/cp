import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import trees.TreeNode;
import trees.Util;

public class TreeMain {


  public static int sumNumbers(TreeNode root) {
    return sumNumbersHelper(root, 0) % 1003;
  }

  private static int sumNumbersHelper(TreeNode node, int currentSum) {
    if (node == null) {
      return 0;
    }

    // Update the current sum based on the current node's value
    currentSum = (currentSum * 10 + node.val) % 1003;

    // If the current node is a leaf, return the current sum
    if (node.left == null && node.right == null) {
      return currentSum;
    }

    // Recursively calculate the sum for left and right subtrees
    int leftSum = sumNumbersHelper(node.left, currentSum);
    int rightSum = sumNumbersHelper(node.right, currentSum);

    // Return the sum of both subtrees
    return (leftSum + rightSum) % 1003;
  }


  public static void main(String[] args) {
    Integer[] arr1 = new Integer[]{5, 1, null, 2, null, null};
    TreeNode root1 = Util.createBst2(arr1);
    // TreeNode res = convertBST(root1);

    System.out.println(sumNumbers(root1));

  }

  public static TreeNode convertBST(TreeNode root) {
    convertBSTDfs(root);
    return root;
  }

  static int sum = 0;

  private static void convertBSTDfs(TreeNode root) {
    if (root == null) {
      return;
    }

    convertBSTDfs(root.right);

    sum += root.val;
    root.val = sum;

    convertBSTDfs(root.left);
  }


  public static TreeNode replaceValueInTree(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    Map<TreeNode, Integer> sumMap = new LinkedHashMap<>();
    while (!q.isEmpty()) {
      int size = q.size();
      if (size == 1) {
        sumMap.put(q.peek(), 0);
      }
      if (size > 1) {
        computeCousinSum(sumMap, q.stream().collect(Collectors.toList()));
      }
      for (int i = 0; i < size; i++) {

        TreeNode temp = q.poll();
        if (temp.left != null) {
          q.add(temp.left);
        }

        if (temp.right != null) {
          q.add(temp.right);
        }
      }
    }

    buildTree(sumMap, root);
    root.val = 0;
    return root;
  }

  private static void buildTree(Map<TreeNode, Integer> sumMap, TreeNode root) {
    if (root == null) {
      return;
    }

    if (root.left != null) {
      root.left.val = sumMap.get(root) != null ? sumMap.get(root) : 0;
    }
    if (root.right != null) {
      root.right.val = sumMap.get(root) != null ? sumMap.get(root) : 0;
    }

    buildTree(sumMap, root.left);
    buildTree(sumMap, root.right);
  }

  public static void computeCousinSum(Map<TreeNode, Integer> sumMap, List<TreeNode> list) {
    /*for (TreeNode t : list) {
      for (TreeNode x : list) {
        if (t == x) {
          continue;
        }
        if (x.left != null) {
          sumMap.put(t, sumMap.getOrDefault(t, 0) + x.left.val);
        }
        if (x.right != null) {
          sumMap.put(t, sumMap.getOrDefault(t, 0) + x.right.val);
        }
      }
    }*/

    int cousinSum = 0;
    for (TreeNode t : list) {
      if (t.left != null) {
        cousinSum += t.left.val;
      }

      if (t.right != null) {
        cousinSum += t.right.val;
      }
    }

    for (TreeNode t : list) {
      int childSum = 0;
      if (t.left != null) {
        childSum += t.left.val;
      }

      if (t.right != null) {
        childSum += t.right.val;
      }
      if (cousinSum > childSum) {
        sumMap.put(t, cousinSum - childSum);
      } else {
        sumMap.put(t, 0);
      }
    }

  }


  public static List<Integer> pathInZigZagTree(int label) {
    List<Integer> path = new ArrayList<>();
    int level = (int) (Math.log(label) / Math.log(2)) + 1;

    while (label > 0) {
      path.add(label);
      int offset = (int) Math.pow(2, level - 1) - 1;
      label = (offset - label / 2) + offset / 2;
      level--;
    }

    Collections.reverse(path);
    return path;
  }

  private static void tracePath(TreeNode root, List<Integer> res, int label) {
    if (root == null) {
      return;
    }
    if (root.val == label) {
      res.add(label);
      return;
    }

    tracePath(root.left, res, label);
    res.add(root.val);
    tracePath(root.right, res, label);

  }

  public static List<List<Integer>> levelOrderBottom(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    List<List<Integer>> ans = new ArrayList<>();
    int level = 0;

    while (!q.isEmpty()) {

      List<Integer> temp = new ArrayList<>();
      int size = q.size();

      for (int i = 0; i < size; i++) {
        TreeNode t = q.poll();
        temp.add(t.val);

        if (t.left != null) {
          q.add(t.left);
        }

        if (t.right != null) {
          q.add(t.right);
        }

      }
      level++;
      ans.add(temp);
    }

    Collections.reverse(ans);

    return ans;
  }


  public List<Integer> largestValues(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    if (root == null) {
      return new ArrayList<>(0);
    }
    q.add(root);
    int level = 0;
    while (!q.isEmpty()) {
      int size = q.size();
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < size; i++) {
        TreeNode temp = q.poll();
        max = Math.max(max, temp.val);
        if (temp.left != null) {
          q.add(temp.left);
        }
        if (temp.right != null) {
          q.add(temp.right);
        }
      }
      ans.add(max);
      level++;
    }

    return ans;
  }

  public static boolean isEvenOddTree(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    boolean flag = true;
    if (root.val % 2 == 0) {
      return false;
    }
    int level = 0;

    while (!q.isEmpty()) {
      int size = q.size();
      Integer prev = null;

      for (int i = 0; i < size; i++) {
        TreeNode temp = q.poll();
        // odd level temp.val should be even
        if (level % 2 == 1) {
          if (temp.val % 2 != 0 || (prev != null && prev <= temp.val)) {
            return false;
          }
        } else {

          if (temp.val % 2 != 1 || (prev != null && prev >= temp.val)) {
            return false;
          }
        }

        prev = temp.val;

        if (temp.left != null) {
          q.add(temp.left);
        }
        if (temp.right != null) {
          q.add(temp.right);
        }
      }
      level++;
    }
    return flag;
  }

  static int maxPathLength = 0;

  public int longestZigZag(TreeNode root) {
    AtomicInteger count = new AtomicInteger(-1);
    AtomicInteger maxCount = new AtomicInteger(0);
    dfs(count, true, root, maxCount);
    return maxCount.get();
  }

  public void dfs(AtomicInteger count, boolean right, TreeNode start, AtomicInteger maxCount) {
    if (start != null) {
      count.set(count.get() + 1);
      right = !right;
      dfs(count, right, !right ? start.right : start.left, maxCount);
      right = !right;
      count.set(count.get() + 1);
      dfs(count, right, right ? start.left : start.right, maxCount);
    } else {
      maxCount.set(Math.max(maxCount.get(), count.get()));
      count.set(-1);
    }
  }


  public TreeNode trimBST(TreeNode root, int low, int high) {
    if (root == null) {
      return null;
    }

    // Recursively trim left and right subtrees
    root.left = trimBST(root.left, low, high);
    root.right = trimBST(root.right, low, high);

    // If root value is less than low, prune left subtree
    if (root.val < low) {
      return root.right;
    }

    // If root value is greater than high, prune right subtree
    if (root.val > high) {
      return root.left;
    }

    // If root value is within range, keep it and return
    return root;
  }

  public static int[] findFrequentTreeSum(TreeNode root) {
    Map<Integer, Integer> freqMap = new HashMap<>();
    recFindFrequentTreeSum(root, freqMap);
    int maxFreq = Integer.MIN_VALUE;
    for (Map.Entry<Integer, Integer> e : freqMap.entrySet()) {
      maxFreq = Math.max(maxFreq, e.getValue());
    }
    List<Integer> ans = new ArrayList<>();
    for (Map.Entry<Integer, Integer> e : freqMap.entrySet()) {
      if (e.getValue() == maxFreq) {
        ans.add(e.getKey());
      }
    }

    int[] arr = new int[ans.size()];
    for (int i = 0; i < ans.size(); i++) {
      arr[i] = ans.get(i);
    }
    return arr;
  }

  private static int recFindFrequentTreeSum(TreeNode root, Map<Integer, Integer> freqMap) {
    if (root == null) {
      return 0;
    }

    int sum =
        root.val + recFindFrequentTreeSum(root.left, freqMap) + recFindFrequentTreeSum(root.right,
            freqMap);

    freqMap.put(sum, freqMap.getOrDefault(sum, 0) + 1);

    return sum;
  }

  public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) {
      return true;
    } else if (root1 == null || root2 == null || root1.val != root2.val) {
      return false;
    }

    // Check without flipping
    boolean withoutFlip = flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);

    // Check with flipping
    boolean withFlip = flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);

    return withoutFlip || withFlip;
  }

  public static int maxLevelSum(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    int maxSum = Integer.MIN_VALUE;
    int minLevel = 1;
    int level = 1;
    while (!q.isEmpty()) {
      int sum = 0;
      int size = q.size();
      for (int i = 0; i < size; i++) {
        TreeNode temp = q.poll();
        sum += temp.val;

        if (temp.left != null) {
          q.add(temp.left);
        }
        if (temp.right != null) {
          q.add(temp.right);
        }
      }
      if (sum > maxSum) {
        maxSum = sum;
        minLevel = level;
      }
      level++;
    }
    return minLevel;
  }

  public static int rob1(TreeNode root) {
    Map<TreeNode, Integer> map = new HashMap<>();
    return rob(root, map);
  }

  public static int rob(TreeNode root, Map<TreeNode, Integer> map) {
    if (root == null) {
      return 0;
    }

    int c1;
    int c2;

    if (map.containsKey(root)) {
      return map.get(root);
    }

    c1 = root.val + (root.left == null ? 0 : rob(root.left.left, map) + rob(root.left.right, map))
        + (
        root.right == null ? 0 : rob(root.right.left, map) + rob(
            root.right.right, map));
    c2 = rob(root.left, map) + rob(root.right, map);

    map.put(root, Math.max(c1, c2));

    return Math.max(c1, c2);

  }

  public static List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return new ArrayList<>();
    }
    return generateTrees(1, n);
  }

  private static List<TreeNode> generateTrees(int start, int end) {
    List<TreeNode> trees = new ArrayList<>();
    if (start > end) {
      trees.add(null);
      return trees;
    }

    for (int i = start; i <= end; i++) {
      List<TreeNode> leftSubtrees = generateTrees(start, i - 1);
      List<TreeNode> rightSubtrees = generateTrees(i + 1, end);

      for (TreeNode left : leftSubtrees) {
        for (TreeNode right : rightSubtrees) {
          TreeNode root = new TreeNode(i);
          root.left = left;
          root.right = right;
          trees.add(root);
        }
      }
    }
    return trees;
  }

  public static TreeNode constructMaximumBinaryTree(int[] nums) {
    if (nums == null || nums.length == 0) {
      return null;
    }
    TreeNode root = constructMaximumBinaryTreeRec(nums, 0, nums.length);
    return root;
  }

  public static TreeNode constructMaximumBinaryTreeRec(int[] nums, int start, int end) {
    if (start >= end) {
      return null;
    }

    int[] max = findMax(nums, start, end);
    int pivot = max[1];

    TreeNode root = new TreeNode(max[0]);

    root.left = constructMaximumBinaryTreeRec(nums, start, pivot);
    root.right = constructMaximumBinaryTreeRec(nums, pivot + 1, end);

    return root;
  }

  public static int[] findMax(int[] nums, int start, int end) {
    int ans[] = new int[2];
    ans[0] = Integer.MIN_VALUE;
    for (int i = start; i < end; i++) {

      if (ans[0] < nums[i]) {
        ans[0] = nums[i];
        ans[1] = i;
      }

    }
    return ans;
  }

  public static int sumRootToLeaf(TreeNode root) {
    List<String> leafs = new ArrayList<>();
    inorderLeafs(root, "", leafs);
    int sum = 0;
    for (String x : leafs) {
      Integer a = Integer.valueOf(Integer.toString(Integer.parseInt(x, 2), 10));
      sum += a;
    }
    return sum;
  }

  public static void inorderLeafs(TreeNode root, String path, List<String> leafs) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      path += root.val;
      leafs.add(path);
      return;
    }
    path += root.val;
    inorderLeafs(root.left, path, leafs);
    inorderLeafs(root.right, path, leafs);

  }

  public static boolean isCousins(TreeNode root, int x, int y) {
    TreeNode xParent = findParent(root, x);
    TreeNode yParent = findParent(root, y);
    int xDepth = findDepth(root, x, 0);
    int yDepth = findDepth(root, y, 0);

    if (xParent != yParent && xDepth == yDepth) {
      return true;
    }

    return false;
  }

  public static TreeNode findParent(TreeNode root, int val) {
    if (root == null) {
      return null;
    }
    if (root.left != null && root.left.val == val || root.right != null && root.right.val == val) {
      return root;
    }
    TreeNode left = findParent(root.left, val);
    TreeNode right = findParent(root.right, val);

    if (left != null) {
      return left;
    }
    return right;
  }

  public static int findDepth(TreeNode root, int val, int depth) {
    if (root == null) {
      return -1;
    }
    if (root.val == val) {
      return depth;
    }

    int leftDepth = findDepth(root.left, val, depth + 1);
    int rightDepth = findDepth(root.right, val, depth + 1);

    if (leftDepth != -1) {
      return leftDepth;
    }

    return rightDepth;
  }


  public static TreeNode increasingBST(TreeNode root) {
    PriorityQueue<TreeNode> pq = new PriorityQueue<>((r1, r2) -> r1.val - r2.val);
    inorderBST(root, pq);
    TreeNode newRoot = pq.peek();
    TreeNode parent = pq.poll();
    while (!pq.isEmpty()) {
      TreeNode temp = pq.poll();
      TreeNode newNode = new TreeNode(temp.val);
      parent.right = newNode;
      parent = newNode;
    }

    return newRoot;
  }

  private static void inorderBST(TreeNode root, PriorityQueue<TreeNode> pq) {
    if (root == null) {
      return;
    }
    inorderBST(root.left, pq);
    pq.add(root);
    inorderBST(root.right, pq);
  }

  public static int amountOfTime(TreeNode root, int start) {
    Map<Integer, Set<Integer>> adjList = new HashMap<>();
    convertTreeToGraph(root, adjList);

    Set<Integer> visited = new HashSet<>();
    Queue<Integer> q = new LinkedList<>();
    q.add(root.val);

    int time = -1;

    while (!q.isEmpty()) {
      time++;
      for (int i = q.size(); i > 0; i--) {
        int currentNode = q.poll();
        visited.add(currentNode);

        if (adjList.containsKey(currentNode)) {
          for (int neighbor : adjList.get(currentNode)) {
            if (!visited.contains(neighbor)) {
              q.add(neighbor);
            }
          }
        }
      }
    }
    return time;
  }

  public static void convertTreeToGraph(TreeNode root, Map<Integer, Set<Integer>> adjList) {

    if (root == null) {
      return;
    }
    if (root.left != null) {
      adjList.computeIfAbsent(root.val, k -> new HashSet<>()).add(root.left.val);
      adjList.computeIfAbsent(root.left.val, k -> new HashSet<>()).add(root.val);
    }
    if (root.right != null) {
      adjList.computeIfAbsent(root.val, k -> new HashSet<>()).add(root.right.val);
      adjList.computeIfAbsent(root.right.val, k -> new HashSet<>()).add(root.val);
    }

    convertTreeToGraph(root.left, adjList);
    convertTreeToGraph(root.right, adjList);

  }

  public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
    List<Integer> l1 = new ArrayList<>();
    List<Integer> l2 = new ArrayList<>();
    inorder(root1, l1);
    inorder(root2, l2);
    if (l1.size() != l2.size()) {
      return false;
    }
    for (int i = 0; i < l1.size(); i++) {
      if (!l1.get(i).equals(l2.get(i))) {
        return false;
      }
    }
    return true;
  }

  public static void inorder(TreeNode root, List<Integer> list) {
    if (root == null) {
      return;
    } else if (root.left == null && root.right == null) {
      list.add(root.val);
      return;
    }
    inorder(root.left, list);
    inorder(root.right, list);
  }

  public static TreeNode subtreeWithAllDeepest(TreeNode root) {
    Pair pair = dfs(root);
    return pair.node;
  }

  private static Pair dfs(TreeNode node) {
    if (node == null) {
      return new Pair(null, 0);
    }

    Pair left = dfs(node.left);
    Pair right = dfs(node.right);

    int depth = Math.max(left.depth, right.depth) + 1;

    if (left.depth == right.depth) {
      return new Pair(node, depth);
    } else if (left.depth > right.depth) {
      return new Pair(left.node, depth);
    } else {
      return new Pair(right.node, depth);
    }
  }

  private static class Pair {

    TreeNode node;
    int depth;

    public Pair(TreeNode node, int depth) {
      this.node = node;
      this.depth = depth;
    }
  }

  public static TreeNode reverseOddLevels(TreeNode root) {
    bfs(root, 0);
    return root;
  }

  public static void bfs(TreeNode root, int level) {

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    Stack<Integer> stack = new Stack<>();

    while (!queue.isEmpty()) {
      int size = queue.size();
      level = (int) (Math.log(size) / Math.log(2));

      for (int i = 0; i < size; i++) {
        TreeNode temp = queue.poll();

        if (!stack.isEmpty() && level % 2 == 1) {
          temp.val = stack.pop();
        }

        if (temp.left != null) {
          queue.add(temp.left);
        }

        if (temp.right != null) {
          queue.add(temp.right);
        }

        if (level % 2 == 0) {
          if (temp.left != null) {
            stack.push(temp.left.val);
          }

          if (temp.right != null) {
            stack.push(temp.right.val);
          }
        }
      }
    }

  }
}
