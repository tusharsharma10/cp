package graphs;

import java.util.*;

public class Dijkstra {

  public static List<Integer> dijkstra(int[][] edge, int vertices, int edges, int source) {

    List<Integer> ans = new ArrayList<>();

    Map<Integer, List<DijkstraNode>> map = new HashMap<>();

    for (int i = 0; i < vertices; i++) {
      map.put(i, new ArrayList<>());
    }

    for (int i = 0; i < edge.length; i++) {
      int start = edge[i][0];
      int end = edge[i][1];
      int wt = edge[i][2];

      DijkstraNode d1 = new DijkstraNode(end, wt);
      List<DijkstraNode> list1 = map.get(start);
      list1.add(d1);

      DijkstraNode d2 = new DijkstraNode(start, wt);
      List<DijkstraNode> list2 = map.get(end);
      list2.add(d2);

    }

    PriorityQueue<DijkstraNode> pq = new PriorityQueue<>(new Comparator<DijkstraNode>() {
      @Override
      public int compare(DijkstraNode d1, DijkstraNode d2) {
        return d2.weight - d1.weight;
      }
    });

    int[] dist = new int[vertices];
    Arrays.fill(dist, Integer.MAX_VALUE / 2);
    dist[source] = 0;
    // boolean[] vis = new boolean[vertices];
    pq.add(new DijkstraNode(source, 0));

    while (!pq.isEmpty()) {
      DijkstraNode temp = pq.poll();
      int src = temp.val;
      for (DijkstraNode node : map.get(src)) {
        if (dist[node.val] > dist[temp.val] + node.weight) {
          dist[node.val] = dist[temp.val] + node.weight;
          pq.add(new DijkstraNode(node.val, dist[node.val]));

        }
      }

    }

    for (int x : dist) {
      ans.add(x);
    }
    return ans;

  }

  /**
   * https://leetcode.com/problems/shortest-path-in-binary-matrix/submissions/1237862550/
   */

  public int shortestPathBinaryMatrix(int[][] grid) {

    if (grid == null || grid.length == 0 || grid[0].length == 0 || grid[0][0] == 1
        || grid[grid.length - 1][grid[0].length - 1] == 1) {
      return -1;
    }

    int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    Queue<int[]> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    queue.offer(new int[]{0, 0, 1});
    visited.add("0,0");

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int x = cell[0];
      int y = cell[1];
      int steps = cell[2];
      // reached the end hence return
      if (x == grid.length - 1 && y == grid[0].length - 1) {
        return steps;
      }

      for (int[] dir : directions) {
        int nx = x + dir[0];
        int ny = y + dir[1];
        if (nx >= 0 && ny >= 0 && nx < grid.length && ny < grid[0].length && grid[nx][ny] == 0
            && !visited.contains(nx + "," + ny)) {
          queue.offer(new int[]{nx, ny, steps + 1});
          visited.add(nx + "," + ny);
        }
      }
    }
    return -1;
  }


  /**
   * https://leetcode.com/problems/path-with-minimum-effort/
   */
  public int minimumEffortPath(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    int[][] dist = new int[n][m];

    for (int i = 0; i < n; i++) {
      Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
    }

    // diff,row,col
    PriorityQueue<int[]> pq = new PriorityQueue<>((t1, t2) -> t1[0] - t2[0]);
    pq.add(new int[]{0, 0, 0});
    int[][] directions = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    while (!pq.isEmpty()) {
      int[] mat = pq.poll();
      int diff = mat[0];
      int row = mat[1];
      int col = mat[2];

      if (row == n - 1 && col == m - 1) {
        return diff;
      }

      for (int i = 0; i < 4; i++) {
        int newRow = row + directions[i][0];
        int newCol = col + directions[i][1];

        if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < m) {
          int newEffort = Math.max(Math.abs(grid[row][col] - grid[newRow][newCol]), diff);

          if (newEffort < dist[newRow][newCol]) {
            dist[newRow][newCol] = newEffort;
            pq.add(new int[]{newEffort, newRow, newCol});
          }
        }

      }

    }
    return 0;
  }

  public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    // Create the adjacency list to depict airports and flights in
    // the form of a graph.
    Map<Integer, List<DijkstraNode>> adj = new HashMap<>();

    for (int i = 0; i < n; i++) {
      adj.put(i, new ArrayList<>());
    }

    int m = flights.length;

    for (int i = 0; i < m; i++) {
      adj.get(flights[i][0]).add(new DijkstraNode(flights[i][1], flights[i][2]));
    }

    // Create a queue which stores the node and their distances from the
    // source in the form of {stops, {node, dist}} with ‘stops’ indicating
    // the no. of nodes between src and current node.

    Queue<int[]> q = new LinkedList<>();

    q.add(new int[]{0, src, 0});

    // Distance array to store the updated distances from the source.
    int[] dist = new int[n];
    for (int i = 0; i < n; i++) {
      dist[i] = Integer.MAX_VALUE / 2;
    }

    dist[src] = 0;

    // Iterate through the graph using a queue like in Dijkstra with
    // popping out the element with min stops first.
    while (!q.isEmpty()) {
      int[] it = q.poll();
      int stops = it[0];
      int node = it[1];
      int cost = it[2];

      // We stop the process as soon as the limit for the stops reaches.
      if (stops > k) {
        continue;
      }

      for (DijkstraNode iter : adj.get(node)) {
        int adjNode = iter.val;
        int edW = iter.weight;

        // We only update the queue if the new calculated dist is
        // less than the prev and the stops are also within limits.
        if (cost + edW < dist[adjNode] && stops <= k) {
          dist[adjNode] = cost + edW;
          q.add(new int[]{stops + 1, adjNode, cost + edW});
        }
      }
    }
    // If the destination node is unreachable return ‘-1’
    // else return the calculated dist from src to dst.
    if (dist[dst] == Integer.MAX_VALUE / 2) {
      return -1;
    }
    return dist[dst];

  }


  public static void main(String[] args) {

    System.out.println(findCheapestPrice(4,
        new int[][]{{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}}, 0, 3, 1));


    /*dijkstra(
        new int[][]{{0, 1, 7}, {0, 2, 1}, {0, 3, 2}, {1, 2, 3}, {1, 3, 5}, {1, 4, 1}, {3, 4, 7}}, 5,
        7, 0);*/
  }

}

class DijkstraNode {

  int val;
  int weight;

  public DijkstraNode(int val, int weight) {
    this.val = val;
    this.weight = weight;
  }

}