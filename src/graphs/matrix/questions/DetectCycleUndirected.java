package graphs.matrix.questions;

import java.util.*;

public class DetectCycleUndirected {

  static class Node {

    int first;
    int second;

    public Node(int first, int second) {
      this.first = first;
      this.second = second;
    }
  }

  /**
   * Child Parent technique
   */

  static boolean checkForCycleBfs(ArrayList<ArrayList<Integer>> adj, int s, boolean vis[]) {
    Queue<Node> q = new LinkedList<>(); //BFS
    q.add(new Node(s, -1));
    vis[s] = true;

    // until the queue is empty
    while (!q.isEmpty()) {
      // source node and its parent node
      int node = q.peek().first;
      int currentParent = q.peek().second;
      q.remove();

      // go to all the adjacent nodes
      for (Integer curr : adj.get(node)) {
        if (vis[curr] == false) {
          q.add(new Node(curr, node));
          vis[curr] = true;
        }

        // if adjacent node is visited and is not its own parent node
        else if (currentParent != curr) {
          return true;
        }
      }
    }

    return false;
  }

  // function to detect cycle in an undirected graph
  public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
    boolean vis[] = new boolean[V];
    Arrays.fill(vis, false);
    int parent[] = new int[V];
    Arrays.fill(parent, -1);

    for (int i = 0; i < V; i++) {
      if (vis[i] == false) {
        if (checkForCycleBfs(adj, i, vis)) {
          return true;
        }
      }
    }

    return false;
  }

  static boolean checkForCycleDfs(ArrayList<ArrayList<Integer>> adj, int s, int parent,
      boolean visited[]) {
    visited[s] = true;
    // go to all adjacent nodes
    for (int adjacentNode : adj.get(s)) {
      if (!visited[adjacentNode]) {
        if (checkForCycleDfs(adj, adjacentNode, s, visited) == true) {
          return true;
        }
      }
      // if adjacent node is visited and is not its own parent node
      else if (adjacentNode != parent) {
        return true;
      }
    }
    return false;
  }

}