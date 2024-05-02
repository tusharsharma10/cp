package graphs.matrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import trees.TreeNode;

public class GraphAlgosMatrix {

  public void dfs(int start, int mat[][], int v) {
    boolean visited[] = new boolean[mat.length];
    dfsRec(start, visited, mat, v);
    System.out.println();
  }

  private void dfsRec(int start, boolean visited[], int mat[][], int v) {

    visited[start] = true;
    System.out.print(start + "->");

    for (int i = 0; i < v; i++) {
      if (mat[start][i] == 1 && !visited[i]) {
        dfsRec(i, visited, mat, v);
      }
    }

  }

  public void bfs(int start, int v, int mat[][]) {

    boolean visited[] = new boolean[v];
    Queue<Integer> q = new LinkedList<>();
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

  /**
   * https://leetcode.com/problems/all-paths-from-source-to-target/
   */
  public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> result = new ArrayList<>();
    int n = graph.length;
    dfs(graph, n, 0, new ArrayList<Integer>(), result);
    return result;

  }

  /**
   * https://leetcode.com/problems/surrounded-regions/
   */

  public void solve(char[][] board) {

    int rows = board.length;
    int cols = board[0].length;

    if (rows == 0 || cols == 0) {
      return;
    }
    if (rows < 3 || cols < 3) {
      return;
    }

    for (int i = 0; i < rows; i++) {

      // using DFS on only boundary elements
      if (board[i][0] == 'O') {
        DFS(board, i, 0);
      }

      if (board[i][cols - 1] == 'O') {
        DFS(board, i, cols - 1);
      }

    }

    for (int i = 1; i < cols - 1; i++) {
      // using DFS on only boundary elements
      if (board[0][i] == 'O') {
        DFS(board, 0, i);
      }

      if (board[rows - 1][i] == 'O') {
        DFS(board, rows - 1, i);
      }
    }

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        // convert other left over 0's to X
        if (board[i][j] == 'O') {
          board[i][j] = 'X';
        }
        // since all 0's connected to boundary 0 therefore element is converted back to 0
        if (board[i][j] == '*') {
          board[i][j] = 'O';
        }
      }
    }

  }

  private void DFS(char[][] grid, int r, int c) {
    int rows = grid.length;
    int cols = grid[0].length;
    if (r < 0 || c < 0 || r > rows - 1 || c > cols - 1 || grid[r][c] != 'O') {
      return;
    }

    grid[r][c] = '*';
    DFS(grid, r + 1, c);
    DFS(grid, r - 1, c);
    DFS(grid, r, c + 1);
    DFS(grid, r, c - 1);
  }

  public static void dfs(int[][] graph, int n, int i, List<Integer> path,
      List<List<Integer>> result) {
    if (i >= n) {
      return;
    }
    path.add(i);
    if (i == n - 1) {
      result.add(new ArrayList(path));
    }
    for (int j = 0; j < graph[i].length; j++) {
      dfs(graph, n, graph[i][j], path, result);
    }
    //backtrack
    path.remove(path.size() - 1);
  }

  /**
   * https://leetcode.com/problems/number-of-islands/
   */


  /**
   * https://leetcode.com/problems/max-area-of-island/
   */

  public static int maxAreaOfIsland(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    boolean[][] visited = new boolean[m][n];
    int maxIslandSize = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1 && !visited[i][j]) {
          maxIslandSize = Math.max(maxIslandSize, explore(i, j, grid, visited));
        } else {
          visited[i][j] = true;
        }
      }
    }
    return maxIslandSize;
  }

  private static int explore(int i, int j, int[][] grid, boolean[][] visited) {
    int m = grid.length;
    int n = grid[0].length;

    if (grid[i][j] == 0 || visited[i][j] == true) {
      return 0;
    }

    visited[i][j] = true;
    int size = 1;
    if (i + 1 < m) {
      size += explore(i + 1, j, grid, visited);
    }
    if (i - 1 >= 0) {
      size += explore(i - 1, j, grid, visited);
    }
    if (j + 1 < n) {
      size += explore(i, j + 1, grid, visited);
    }
    if (j - 1 >= 0) {
      size += explore(i, j - 1, grid, visited);
    }
    return size;
  }

  public static void main(String[] args) {
    // List<List<Integer>> ans = allPathsSourceTarget(new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}});
    System.out.println();
  }

}
