import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class Main10 {

  public static void main(String[] args) {
    /*List<Integer> x = Arrays.asList(-255, 369, 319, 77, 128, -202, -147, 282, -26, -489, -443,
        -401, 385, 465, -134, 126, 304, 179, 16, 112, 473, -467, 279, -233, 66, 76, 408, 148, -369,
        328, 138, -164, 492, -276, -326, 170, 168, 189, 13, 383, 341, 426, 219, 337, -62, -197, 263,
        338, -324, 261, 273, -74, -8, -133, 318, -100, 487, -196, -465, -495, -136, 94, -201, 491,
        204, 323, 156, -337, -99, 115, 179, 184, -249, 76, -396, 265, 500, -83, 270, 438, -418, 401,
        -184, -247, -203, 190, 191, -282, -248, 465, 146, 7, -381, 326, -409, 474, 186, -206, 447,
        17, 156, -273, 381, -307, -206, 164, -147, 58, -224, 284, 204, 267, 123, 141, -8, 225, -246,
        12, 399, -261, -104, 191, 390, 152, 313, -91, 8, -476, -363, -183, -280, -282, -431, 366,
        89, -166, -257, 132, 98, -387, 389, -219, -332, 227, 386, -33, 361, -308, -494, -33, 110,
        423, -465, -417, 496, -333, -259, 402, 36, 380, -385, -329, 283, 389, 396, -161, 466, -405,
        -293, 442, 259, 377, -386, -386, 329, 204, 438, 346, -185, -401, -219, 213, 351, -18, -20,
        364, 319, 187, 197, 122, -182, -126, -211, -448, 44, -360, -345, -147, 480, -387, 222, 92,
        -262, -409, 163, 323, -291, -61, -431, -288, -309, -490, -494, 328, -207, 398, 475, -228,
        -37, 44, 227, -371, -91, -440, 220, 39, -73, 80, -189, 37, 94, -96, -400, -380, 172, -179,
        -442, -119, 411, -184, 218, -18, 170, 430, -157, 345, 418, 390, -39, -85, 216, -197, -421,
        328, -311, 160, 432, 104, -419, -140, -115, -202, 58, 415, 473, -87, 475, 430, 114, -314,
        430, -419, 375, 258, 255, 42, -63, 54, -352, -337, -180, -31, 441, -382, -176, 209, -137,
        171, -89, 155, 421, 308, -153, 254, -210, -245, 373, -435, -29, -398, 326, 297, 81, -157,
        254, 52, 479, 356, -497, -16, 109, -353, -20, -122, -172, 23, 20, -344, 203, 372, -306, -9,
        238, -190, 495, 9, -2, 125, 150, -180, -340, -1, -347, -269, -181, -15, 83, -304, -365, 490,
        -475, 161, 422, 440, -414, 380, -446, -404, -352, -144, -297, -62, -23, -223, 359, 127, 183,
        -20, 93, -285, -477, 223, 1, 131, -359, -74, 321, 197, 452, -338, 367, -337, 183, -41, 218,
        -75, -212, 208, 188, -38, 91, 332, 388, -185, -247, 405, -390, -371, 313, -471, 457, 307,
        494, -467, -225, -3, -271, -164, -120, 101, 385, -12, 234, -368, -317, 167, 241, -494, -279,
        -288, 452, -499, 372, 464, 234, 16, 40, 264, -474, -400, 429, 33, 495, -285, 201, 190, 328,
        127, 286, 312, 100, -24, 409, -392, 183, -69, -352, -56, -304, -261, -296, -140, 453, 253,
        -215, 195, 288, -300, 10, -104, -491, 275, -275, 175, 24, 387, 408);*/

    /*System.out.println(
        openLock(new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"},
            "8888"));*/

//    System.out.println(maxDisjointIntervals(new int[][]{{1, 4}, {2, 3}, {4, 6}, {8, 9}}));
//    System.out.println(maxDisjointIntervals(new int[][]{{1, 9}, {2, 3}, {5, 7}}));

    int arr[][] = new int[][]{{5, 2}, {2, 11}, {5, 1}, {1, 3}};
    ArrayList<ArrayList<Integer>> A = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      ArrayList<Integer> temp = new ArrayList<>();
      temp.add(arr[i][0]);
      temp.add(arr[i][1]);
      A.add(temp);
    }

    System.out.println(magicPool(A));

  }


  public static boolean exist(char[][] board, String word) {

    char start = word.charAt(0);

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == start) {
          if (explore(board, i, j, word, 0, new boolean[board.length][board[0].length])) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private static boolean explore(char[][] board, int i, int j, String word, int count,
      boolean[][] vis) {

    if (count == word.length()) {
      return true;
    }

    // this is the core this stops further dfs calls returning false
    if (i >= board.length || j >= board[0].length || i < 0 || j < 0 || vis[i][j]
        || board[i][j] != word.charAt(
        count)) {
      return false;
    }

    vis[i][j] = true;

    boolean flag = explore(board, i + 1, j, word, count + 1, vis) ||
        explore(board, i - 1, j, word, count + 1, vis) ||
        explore(board, i, j + 1, word, count + 1, vis) ||
        explore(board, i, j - 1, word, count + 1, vis);

    //backtrack
    vis[i][j] = false;

    return flag;
  }


  public static int findMax(List<Integer> list) {
    int max = Integer.MIN_VALUE;

    for (int i = 0; i < list.size(); i++) {
      max = Math.max(max, list.get(i));
    }
    return max;
  }

  public static int magicPool(ArrayList<ArrayList<Integer>> A) {

    final int MOD = 1000000007;

    TreeMap<Integer, List<Integer>> map = new TreeMap<>();

    for (int i = 0; i < A.size(); i++) {
      int quality = A.get(i).get(0);
      int quantity = A.get(i).get(1);

      if (map.containsKey(quality)) {
        List<Integer> qList = map.get(quality);
        qList.add(quantity);
      } else {
        List<Integer> qList = new ArrayList<>();
        qList.add(quantity);
        map.put(quality, qList);
      }

    }

    long maxItems = 0;

    while (!map.isEmpty()) {

      Map.Entry<Integer, List<Integer>> e = map.pollLastEntry();
      int newQuality = e.getKey() / 2;

      int maxItem = findMax(e.getValue());

      maxItems = (maxItems % MOD + maxItem % MOD) % MOD;

      if (newQuality != 0) {
        if (map.containsKey(newQuality)) {
          List<Integer> l1 = map.get(newQuality);
          l1.add(e.getKey() * 2);
        } else {
          List<Integer> l1 = new ArrayList<>();
          l1.add(e.getKey() * 2);
          map.put(newQuality, l1);
        }

      }
      map.remove(e.getKey());
    }

    return (int) (maxItems % MOD);
  }

  public static int maxDisjointIntervals(int[][] intervals) {

    if (intervals == null || intervals.length == 0) {
      return 0;
    }

    // Sort intervals based on their ending points
    Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

    int count = 1; // Count of disjoint intervals
    int end = intervals[0][1]; // Ending point of the last added interval

    // Iterate through each interval
    for (int i = 1; i < intervals.length; i++) {
      // If the starting point of the current interval is greater than or equal to
      // the ending point of the last added interval, increment count and update end
      if (intervals[i][0] > end) {
        count++;
        end = intervals[i][1];
      }
    }

    return count;
  }

  public static int disjoint2(int[][] arr) {

    List<PairTime> list = new ArrayList<>();
    list.add(new PairTime(arr[0][0], arr[0][1]));

    int j = 0;
    int overlapCount = 0;

    for (int i = 1; i < arr.length; i++) {
      if (list.get(j).endTime >= arr[i][0] && list.get(j).endTime < arr[i][1]) {
        list.get(j).endTime = arr[i][1];
        overlapCount++;
      } else if (list.get(j).endTime > arr[i][1]) {
        continue;
      } else if (list.get(j).endTime < arr[i][0]) {
        list.add(new PairTime(arr[i][0], arr[i][1]));
        j++;
      }
    }
    return arr.length - overlapCount;
  }

  public static int disjoint(ArrayList<ArrayList<Integer>> A) {

    int[][] arr = new int[A.size()][2];

    for (int i = 0; i < A.size(); i++) {
      arr[i][0] = A.get(i).get(0);
    }

    List<PairTime> list = new ArrayList<>();
    list.add(new PairTime(arr[0][0], arr[0][1]));

    int j = 0;

    for (int i = 1; i < arr.length; i++) {
      if (list.get(j).endTime > arr[i][0] && list.get(j).endTime < arr[i][1]) {
        list.get(j).endTime = arr[i][1];
      } else if (list.get(j).endTime > arr[i][0] && list.get(j).endTime > arr[i][1]) {
        continue;
      } else if (list.get(j).endTime < arr[i][0]) {
        list.add(new PairTime(arr[i][0], arr[i][1]));
        j++;
      }
    }
    return list.size();
  }

  public static int solve(ArrayList<ArrayList<Integer>> A) {

    int[][] intervals = new int[A.size()][2];

    for (int i = 0; i < A.size(); i++) {
      intervals[i][0] = A.get(i).get(0);
      intervals[i][1] = A.get(i).get(1);
    }

    if (intervals == null || intervals.length == 0) {
      return 0;
    }

    // sort on start time
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

    PriorityQueue<Integer> heap = new PriorityQueue<>();

    // add endtime to heap
    heap.add(intervals[0][1]);

    // If the current meeting's start time is later than or equal to
    // the end time of the meeting at the top of the heap, update the end time
    // Otherwise, add the end time of the current meeting to the heap
    for (int i = 1; i < intervals.length; i++) {

      if (intervals[i][0] >= heap.peek()) {
        heap.poll();
      }
      heap.add(intervals[i][1]);
    }

    // Size of the heap represents the minimum number of conference rooms required
    return heap.size();


  }

  public static int candy(List<Integer> A) {

    Collections.sort(A);

    int currCandy = 1;
    int totalCandies = 1;
    for (int i = 1; i < A.size(); i++) {
      int num = A.get(i);

      if (num != A.get(i - 1)) {
        currCandy++;
      }
      totalCandies += currCandy;
    }

    return totalCandies;

  }

  public static int openLock(String[] deadends, String target) {
    Set<String> set = new HashSet<>();
    for (String deadend : deadends) {
      set.add(deadend);
    }

    Queue<String> q = new LinkedList<>();
    q.add("0000");
    int count = 0;

    while (!q.isEmpty()) {
      int n = q.size();
      for (int j = 0; j < n; j++) {
        String num = q.poll();

        if (num.equals(target)) {
          return count;
        }

        for (int i = 0; i < 4; i++) {
          int x = (num.charAt(i) - '0' + 1) % 10;
          int y = (num.charAt(i) - '0' - 1 + 10) % 10;

          StringBuilder str1 = new StringBuilder(num);
          StringBuilder str2 = new StringBuilder(num);

          str1.setCharAt(i, (char) (x + '0'));
          str2.setCharAt(i, (char) (y + '0'));

          String s1 = str1.toString();
          String s2 = str2.toString();

          if (!set.contains(s1)) {
            q.add(s1);
            set.add(s1); // Mark as visited
          }

          if (!set.contains(s2)) {
            q.add(s2);
            set.add(s2); // Mark as visited
          }
        }
      }
      count++;
    }
    return -1;
  }


  public static int countSubstring(String s) {
    int count = 0;
    int left = 0;
    int n = s.length();
    Map<Character, Integer> map = new HashMap<>();

    for (int right = 0; right < n; right++) {
      char curr = s.charAt(right);
      map.put(curr, map.getOrDefault(curr, 0) + 1);

      while (map.containsKey('a') && map.containsKey('b') && map.containsKey('c')) {
        count += n - right;
        char leftChar = s.charAt(left);
        map.put(leftChar, map.get(leftChar) - 1);
        if (map.get(leftChar) == 0) {
          map.remove(leftChar);
        }
        left++;
      }
    }

    return count;
  }

  public static int uniqueSubstrings(String input) {
    Map<Character, Integer> map = new HashMap<>();
    int left = 0;
    int right = 0;
    int n = input.length();
    int len = 0;

    while (right < n) {

      char x = input.charAt(right);

      if (!map.containsKey(x)) {
        map.put(x, 1);
      } else {
        map.put(x, map.get(x) + 1);
        while (map.get(x) > 1) {

          char y = input.charAt(left);
          map.put(y, map.get(y) - 1);

          if (map.get(y) == 0) {
            map.remove(y);
          }

          left++;
        }

      }

      len = Math.max(len, right - left + 1);
      right++;
    }
    return len;
  }


}

class PairTime {

  int startTime;
  int endTime;

  public PairTime(int s, int e) {
    this.startTime = s;
    this.endTime = e;
  }
}
