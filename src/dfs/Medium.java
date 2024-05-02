package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import trees.TreeNode;
import trees.Util;

public class Medium {

  public static void main(String[] args) {
    Integer[] arr = new Integer[]{1, 2, 3, null, 5, null, 4};
    TreeNode node = Util.createBst2(arr);
    System.out.println(findMinHeightTrees(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {3, 4}, {4, 5}}));
  }



  /**
   * https://leetcode.com/problems/minimum-height-trees/
   * NOT WORKING
   */
  public static List<Integer> findMinHeightTrees(int n, int[][] edges) {

    Set<Integer> ansList = new HashSet<>();
    Map<Integer, List<Integer>> adjMap = new HashMap<>();

    // edge case
    if (n == 1) {
      ansList.add(0);
      return ansList.stream().collect(Collectors.toList());
    }

    // create adjacencyMap
    for (int i = 0; i < edges.length; i++) {
      if (adjMap.containsKey(edges[i][0])) {
        List<Integer> adjList = adjMap.get(edges[i][0]);
        adjList.add(edges[i][1]);
        adjMap.put(edges[i][0], adjList);
      } else {
        List<Integer> adjList = new ArrayList<>();
        adjList.add(edges[i][1]);
        adjMap.put(edges[i][0], adjList);
      }
      if (adjMap.containsKey(edges[i][1])) {
        List<Integer> adjList = adjMap.get(edges[i][1]);
        adjList.add(edges[i][0]);
        adjMap.put(edges[i][1], adjList);
      } else {
        List<Integer> adjList = new ArrayList<>();
        adjList.add(edges[i][0]);
        adjMap.put(edges[i][1], adjList);
      }
    }

    // fill ansList
    for (Map.Entry<Integer, List<Integer>> e : adjMap.entrySet()) {
      List<Integer> adjList = e.getValue();
      if (adjList.size() >= 1) {
        for (int i = 0; i < adjList.size(); i++) {
          int element = adjList.get(i);
          if (adjMap.get(element).size() == 1) {
            ansList.add(e.getKey());
          }
        }
      }
    }

    return ansList.stream().collect(Collectors.toList());
  }

  /**
   * https://leetcode.com/problems/count-complete-tree-nodes/
   */
  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int count = 1;
    count += countNodes(root.left);
    count += countNodes(root.right);
    return count;
  }


  /**
   * Root to leaf
   */
  public static int sumNumbers(TreeNode root) {
    List<Integer> tempPath = new ArrayList<>();
    int sum = dfsSum(root, tempPath);
    return sum;
  }

  /**
   * https://leetcode.com/problems/sum-root-to-leaf-numbers/submissions/958073735/
   */
  public static int dfsSum(TreeNode root, List<Integer> tempPath) {
    if (root == null) {
      return 0;
    }

    int sum = 0;
    tempPath.add(root.val);

    // check when root node
    if (root.left == null && root.right == null) {
      int num = 0;
      for (int i = tempPath.size() - 1, j = 0; i >= 0 && j < tempPath.size(); i--, j++) {
        num += tempPath.get(i) * Math.pow(10, j);
      }
      sum += num;
    }

    sum += dfsSum(root.left, tempPath);
    sum += dfsSum(root.right, tempPath);
    tempPath.remove(tempPath.size() - 1);
    return sum;
  }

  /**
   * Path Sum
   */
  public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> ans = new ArrayList<>();
    pathSumRec(root, targetSum, ans, new ArrayList<>());
    return ans;
  }

  public static void pathSumRec(TreeNode root, int targetSum,
      List<List<Integer>> ans, List<Integer> tempPath) {
    if (root == null) {
      return;
    }

    targetSum -= root.val;
    tempPath.add(root.val);

    if (root.left == null && root.right == null && targetSum == 0) {
      ans.add(new ArrayList<>(tempPath));
    } else {
      pathSumRec(root.left, targetSum, ans, tempPath);
      pathSumRec(root.right, targetSum, ans, tempPath);
    }
    tempPath.remove(tempPath.size() - 1);

  }

}
