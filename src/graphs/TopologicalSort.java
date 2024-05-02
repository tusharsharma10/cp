package graphs;

import java.util.*;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class TopologicalSort {

  public static List<Integer> topologicalSort(int[][] edges, int e, int v) {
    List<Integer> ans = new ArrayList<>();
    boolean[] vis = new boolean[v];

    Map<Integer, List<Integer>> map = new HashMap<>();

    for (int i = 0; i < v; i++) {
      map.put(i, new ArrayList<>());
    }

    for (int i = 0; i < edges.length; i++) {
      int start = edges[i][0];
      int end = edges[i][1];

      if (map.containsKey(start)) {
        map.get(start).add(end);
      } else {
        List<Integer> l = new ArrayList<>();
        l.add(end);
        map.put(start, l);
      }

    }

    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < v; i++) {
      if (!vis[i]) {
        dfs(map, vis, i, stack);
      }

    }

    // pop from stack and this is the ordering
    while (!stack.isEmpty()) {
      ans.add(stack.pop());
    }

    return ans;

  }

  public static void dfs(Map<Integer, List<Integer>> map, boolean[] vis, int curr,
      Stack<Integer> stack) {

    vis[curr] = true;
    List<Integer> list = map.get(curr);
    for (int node : list) {
      if (!vis[node]) {
        dfs(map, vis, node, stack);

      }
    }

    // add in stack once dfs is completed for curr
    stack.push(curr);

  }

  /**
   * Lower the indegree of vertex node earlier it will be in topoSort
   */

  public static int[] topoSortBfs(int V, ArrayList<ArrayList<Integer>> adj) {
    int indegree[] = new int[V];

    // indegree number of edges coming towards a vertex
    for (int i = 0; i < V; i++) {
      for (int node : adj.get(i)) {
        indegree[node]++;
      }
    }

    Queue<Integer> q = new LinkedList<>();

    for (int i = 0; i < V; i++) {
      if (indegree[i] == 0) {
        q.add(i);
      }
    }

    int topo[] = new int[V];
    int i = 0;

    while (!q.isEmpty()) {
      int node = q.peek();
      q.remove();
      topo[i++] = node;
      // node is in your topo sort
      // so please remove it from the indegree

      for (int it : adj.get(node)) {
        indegree[it]--;
        if (indegree[it] == 0) {
          q.add(it);
        }
      }
    }

    return topo;
  }

  public static boolean canFinish(int numCourses, int[][] prerequisites) {
    Map<Integer, List<Integer>> map = new HashMap<>();

    for (int i = 0; i < numCourses; i++) {
      map.put(i, new ArrayList<>());
    }

    for (int i = 0; i < prerequisites.length; i++) {
      int start = prerequisites[i][0];
      int end = prerequisites[i][1];
      map.get(start).add(end);
    }

    int[] indegree = new int[numCourses];

    for (int i = 0; i < numCourses; i++) {
      for (int node : map.get(i)) {
        indegree[node]++;
      }
    }

    Queue<Integer> q = new LinkedList<>();

    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        q.add(i);
      }
    }

    List<Integer> topo = new ArrayList<>();

    while (!q.isEmpty()) {
      int node = q.poll();
      topo.add(node);

      for (int x : map.get(node)) {
        indegree[x]--;
        if (indegree[x] == 0) {
          q.add(x);
        }
      }

    }

    return topo.size() == numCourses ? true : false;

  }

  /**
   * Applied topo sort but why ?
   */

  public List<Integer> eventualSafeNodes(int[][] graph) {

    // creating map
    Map<Integer, List<Integer>> map = new HashMap<>();

    int numCourses = graph.length;

    for (int i = 0; i < numCourses; i++) {
      map.put(i, new ArrayList<>());
    }

    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[i].length; j++) {
        int end = graph[i][j];
        map.get(end).add(i);
      }

    }

    int[] indegree = new int[numCourses];

    for (int i = 0; i < numCourses; i++) {
      for (int node : map.get(i)) {
        indegree[node]++;
      }
    }

    Queue<Integer> q = new LinkedList<>();

    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        q.add(i);
      }
    }

    List<Integer> topo = new ArrayList<>();

    while (!q.isEmpty()) {
      int node = q.poll();
      topo.add(node);

      for (int x : map.get(node)) {
        indegree[x]--;
        if (indegree[x] == 0) {
          q.add(x);
        }
      }

    }

    Collections.sort(topo);
    return topo;
  }

  public static String getAlienLanguage(String[] dictionary, int k) {

    Map<Integer, Set<Integer>> map = new HashMap<>();

    for (int i = 0; i < k; i++) {
      map.put(i, new HashSet<>());
    }

    for (int i = 0; i < dictionary.length - 1; i++) {
      String s1 = dictionary[i];
      String s2 = dictionary[i + 1];
      int x = 0;
      while (x < s1.length() && x < s2.length()) {
        if (s1.charAt(x) != s2.charAt(x)) {
          map.get(s1.charAt(x) - 'a').add(s2.charAt(x) - 'a');
          break;
        }
        x++;
      }

    }

    int indegree[] = new int[k];

    for (int i = 0; i < k; i++) {
      for (int node : map.get(i)) {
        indegree[node]++;
      }
    }

    Queue<Integer> q = new LinkedList<>();

    for (int i = 0; i < k; i++) {
      if (indegree[i] == 0) {
        q.add(i);
      }
    }

    List<Integer> topo = new ArrayList<>();

    int i = 0;

    while (!q.isEmpty()) {
      int node = q.peek();
      q.remove();
      topo.add(node);
      // node is in your topo sort
      // so please remove it from the indegree

      for (int it : map.get(node)) {
        indegree[it]--;
        if (indegree[it] == 0) {
          q.add(it);
        }
      }
    }

    StringBuilder str = new StringBuilder();
    for (int val : topo) {
      char c = (char) (val + 'a');
      str.append(c);
    }

    return str.toString();
  }

  public static void main(String[] args) {
    getAlienLanguage(new String[]{"ccacacc", "babaaccb", "baacaaba", "aaabcbaabb"}, 4);
  }

}
