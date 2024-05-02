import java.util.ArrayList;
import java.util.*;

public class BackTracking {


  public static ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    // Sort the input array to handle duplicates
    Collections.sort(A);
    backtrack(A, new ArrayList<>(), new boolean[A.size()], result);
    return result;
  }

  private static void backtrack(ArrayList<Integer> A, ArrayList<Integer> current, boolean[] used,
      ArrayList<ArrayList<Integer>> result) {
    // If the current permutation has the same size as the input array, add it to the result
    if (current.size() == A.size()) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = 0; i < A.size(); i++) {
      // Skip if the number is already used or if it's a duplicate and the previous one is not used
      if (used[i] || (i > 0 && A.get(i).equals(A.get(i - 1)) && !used[i - 1])) {
        continue;
      }
      // Add the current number to the permutation
      current.add(A.get(i));
      used[i] = true;
      // Recursively generate permutations
      backtrack(A, current, used, result);
      // Backtrack: remove the last added number and mark it as unused
      current.remove(current.size() - 1);
      used[i] = false;
    }
  }


  /**
   * Amount of water stored at index =  min(left,right) - heightOfIndex
   */
  public static int trap(final List<Integer> A) {

    int[] left = new int[A.size()];
    int[] right = new int[A.size()];

    int maxLeft = 0;
    for (int i = 0; i < left.length; i++) {
      maxLeft = Math.max(maxLeft, A.get(i));
      left[i] = maxLeft;
    }

    int maxRight = 0;
    for (int i = right.length - 1; i >= 0; i--) {
      maxRight = Math.max(maxRight, A.get(i));
      right[i] = maxRight;
    }

    int water = 0;

    for (int i = 0; i < A.size(); i++) {
      int min = Math.min(right[i], left[i]);
      water += min - A.get(i);
    }

    return water;

  }

  public static int isValid(String A) {

    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < A.length(); i++) {
      char c = A.charAt(i);

      if (stack.isEmpty() && isClosingChar(c)) {
        return 0;
      }

      if (!stack.isEmpty() && isClosingChar(c)) {
        char c2 = stack.peek();

        if (charMatches(c2, c)) {
          stack.pop();
        } else {
          return 0;
        }
      }

      if (isOpeningChar(c)) {
        stack.push(c);
      }

    }

    if (stack.isEmpty()) {
      return 1;
    }

    return 0;
  }

  private static boolean isClosingChar(char c) {
    return c == ')' || c == '}' || c == ']';
  }

  private static boolean isOpeningChar(char c) {
    return c == '(' || c == '{' || c == '[';
  }

  private static boolean charMatches(char c1, char c2) {

    if ((c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']')) {
      return true;
    }

    return false;

  }


  public static ArrayList<Integer> nextGreater(List<Integer> A) {

    int[] ans = new int[A.size()];
    Arrays.fill(ans, -1);
    Stack<Integer> stack = new Stack<>();

    for (int i = A.size() - 1; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek() <= A.get(i)) {
        stack.pop();
      }

      if (stack.isEmpty()) {
        stack.push(A.get(i));
      } else if (stack.peek() > A.get(i)) {
        ans[i] = stack.peek();
        stack.push(A.get(i));
      }
    }

    ArrayList<Integer> res = new ArrayList<>();

    for (int x : ans) {
      res.add(x);
    }

    return res;
  }

  /**
   * https://www.interviewbit.com/problems/redundant-braces/
   */

  public int braces(String A) {

    Stack<Character> stack = new Stack<>();

    // Iterate through the characters of the expression
    for (char ch : A.toCharArray()) {
      if (ch == ')') {
        // Pop elements from the stack until '(' is encountered
        char top = stack.pop();
        boolean isRedundant = true;

        while (top != '(') {
          if (top == '+' || top == '-' || top == '*' || top == '/') {
            isRedundant = false;
          }
          top = stack.pop();
        }

        // If there are no operators between '(' and ')', braces are redundant
        if (isRedundant) {
          return 1;
        }
      } else {
        // Push non ')' characters to the stack
        stack.push(ch);
      }
    }

    // No redundant braces found
    return 0;
  }


  public static int solve(List<Integer> A, List<Integer> B) {
    int count = 0;
    int time = 0;
    int[] prefix = new int[A.size()];
    prefix[0] = 0;

    for (int i = 0; i < A.size(); i++) {
      int patience = A.get(i);

      if (patience < time) {
        count++;
        continue;
      }
      time += B.get(i);
    }

    return count;

  }

  public ArrayList<Integer> nearestHotel(List<List<Integer>> A,
      List<List<Integer>> B) {

    ArrayList<Integer> ans = new ArrayList<>();
    int n = A.size();
    int m = A.get(0).size();
    int[][] mat = new int[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        mat[i][j] = A.get(i).get(j);
      }
    }

    int colSize = -1;
    int rowSize = -1;

    for (int i = 0; i < B.size(); i++) {
      int r = B.get(i).get(0) - 1;
      int c = B.get(i).get(1) - 1;

      colSize = Math.max(colSize, c);
      rowSize = Math.max(rowSize, r);
    }

    int[][] dp = new int[rowSize][colSize];

    Map<String, Integer> map = new HashMap<>();

    int[][] distances = bfsDistance(mat);

    for (int i = 0; i < B.size(); i++) {
      int r = B.get(i).get(0) - 1;
      int c = B.get(i).get(1) - 1;

      ans.add(distances[r][c]);
    }

    return ans;
  }

  public static int[][] bfsDistance(int[][] mat) {

    int[][] dir = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    int n = mat.length;
    int m = mat[0].length;

    int[][] distances = new int[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        distances[i][j] = -1;
      }
    }

    Queue<int[]> q = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (mat[i][j] == 1) {
          q.add(new int[]{i, j});
          distances[i][j] = 0;
        }
      }
    }

    while (!q.isEmpty()) {
      int[] cell = q.poll();
      int curR = cell[0];
      int curC = cell[1];

      for (int d = 0; d < 4; d++) {
        int nrow = curR + dir[d][0];
        int ncol = curC + dir[d][1];

        if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && distances[nrow][ncol] == -1) {
          q.add(new int[]{nrow, ncol});
          distances[nrow][ncol] = distances[curR][curC] + 1;
        }
      }

    }
    return distances;
  }

  /**
   * Maxed Arrays
   */

  public ArrayList<Long> solve(ArrayList<Integer> A) {
    int n = A.size();
    ArrayList<Long> result = new ArrayList<>();

    // Calculate counts of elements less than and greater than each element
    long[] lessThan = new long[n];
    long[] greaterThan = new long[n];

    // Calculate counts of elements less than each element
    for (int i = 0; i < n; i++) {
      int count = 0;
      for (int j = i - 1; j >= 0; j--) {
        if (A.get(j) < A.get(i)) {
          count++;
        }
      }
      lessThan[i] = count;
    }

    // Calculate counts of elements greater than each element
    for (int i = 0; i < n; i++) {
      int count = 0;
      for (int j = i + 1; j < n; j++) {
        if (A.get(j) > A.get(i)) {
          count++;
        }
      }
      greaterThan[i] = count;
    }

    // Calculate the sum of lengths of maxed subarrays for each element
    for (int i = 0; i < n; i++) {
      long sum = lessThan[i] * greaterThan[i];
      result.add(sum);
    }

    return result;
  }


  public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {

    Stack<Integer> stack = new Stack<>();

    int[] ans = new int[A.size()];
    Arrays.fill(ans, -1);

    stack.push(A.get(0));

    for (int i = 1; i < A.size(); i++) {

      while (!stack.isEmpty() && stack.peek() >= A.get(i)) {
        stack.pop();
      }

      if (stack.isEmpty()) {
        stack.push(A.get(i));
      } else if (stack.peek() < A.get(i)) {
        ans[i] = stack.peek();
        stack.push(A.get(i));
      }

    }

    ArrayList<Integer> res = new ArrayList<>();

    for (int x : ans) {
      res.add(x);
    }

    return res;
  }


  public int threeSumClosest(ArrayList<Integer> A, int B) {
    Collections.sort(A);

    int minDiff = Integer.MAX_VALUE;
    int sum = -1;

    for (int i = 0; i < A.size() - 2; i++) {
      int left = i + 1;
      int right = A.size() - 1;

      while (left < right) {
        int numLeft = A.get(left);
        int numRight = A.get(right);
        int tempSum = A.get(i) + numLeft + numRight;

        if (minDiff > Math.abs(tempSum - B)) {
          sum = tempSum;
          minDiff = Math.abs(tempSum - B);
        }

        if (tempSum < B) {
          left++;
        } else if (tempSum > B) {
          right--;
        } else {
          return tempSum;
        }

      }

    }
    return sum;
  }

  public static int removeDuplicates(List<Integer> A) {

    Set<Integer> set = new HashSet<>();

    for (int x : A) {
      set.add(x);
    }

    A.removeAll(A);

    for (int x : set) {
      A.add(x);
    }

    return set.size();
  }



  public static void main(String[] args) {
    /*System.out.println(nextGreater(
        Arrays.asList(8, 23, 22, 16, 22, 7, 7, 27, 35, 27, 32, 20, 5, 1, 35, 28, 20, 6, 16, 26, 48,
            34)));*/

    System.out.println(removeDuplicates(Arrays.asList(1, 2, 2, 3, 3)));

  }

}
