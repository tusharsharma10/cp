package array.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Easy {


  public List<Integer> luckyNumbers(int[][] mat) {

    List<Integer> list = new ArrayList<>();
    int m = mat.length;
    int n = mat[0].length;

    int[] row = new int[m];
    int[] col = new int[n];

    for (int i = 0; i < m; i++) {
      row[i] = findMin(mat[i]);
    }

    for (int i = 0; i < n; i++) {
      int max = Integer.MIN_VALUE;
      for (int j = 0; j < m; j++) {
        if (max < mat[j][i]) {
          max = mat[j][i];
        }
      }

      col[i] = max;
    }

    for (int i = 0; i < m; i++) {

      for (int j = 0; j < n; j++) {
        if (row[i] == col[j]) {
          list.add(row[i]);
        }
      }
    }

    return list;

  }

  int findMin(int arr[]) {

    int min = Integer.MAX_VALUE;

    for (int i = 0; i < arr.length; i++) {

      if (arr[i] < min) {
        min = arr[i];
      }
    }

    return min;
  }

  public static int[] findDiagonalOrder(int[][] mat) {

    int m = mat.length;
    int n = mat[0].length;
    int[] ans = new int[m * n];
    boolean upFlag = true;
    int i = 0, j = 0, x = 0;

    while (i < m && j < n) {

      if (upFlag) {
        while (i > 0 && j < n - 1) {
          ans[x++] = mat[i--][j++];
        }
        //reached boundary
        ans[x++] = mat[i][j];
        //If we reach right boundary, move down, other wise move right.
        if (j == n - 1) {
          i++;
        } else {
          j++;
        }
      } else {
        while (i < m - 1 && j > 0) {
          ans[x++] = mat[i++][j--];
        }
        //reached boundary
        ans[x++] = mat[i][j];
        // if we reach bottom boundary move right
        if (i == m - 1) {
          j++;
        } else {
          i++;
        }
      }

      upFlag = !upFlag;
    }

    return ans;
  }

  public static void main(String[] args) {

    findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

  }
}
