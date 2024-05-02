import java.util.*;

public class AtMost2Occurrences {

  public int solve(ArrayList<Integer> A) {
    int n = A.size();
    int ans = n;
    int r = -1;
    int countocc = 0;

    HashMap<Integer, Integer> hash = new HashMap<>();

    for (int i : A) {
      if (hash.get(i) == null) {
        hash.put(i, 0);
      }
      hash.put(i, hash.get(i) + 1);
      if (hash.get(i) == 3) {
        countocc++;
      }
    }

    for (int i = 0; i < n; i++) {

      while (r + 1 < n && countocc > 0) {
        r++;
        hash.put(A.get(r), hash.get(A.get(r)) - 1);
        if (hash.get(A.get(r)) == 2) {
          countocc--;
        }
      }
      if (countocc == 0) {
        ans = Math.min(Math.abs(r - i + 1), ans);
      }
      if (hash.get(A.get(i)) == null) {
        hash.put(A.get(i), 0);
      }
      hash.put(A.get(i), hash.get(A.get(i)) + 1);
      if (hash.get(A.get(i)) == 3) {
        countocc++;
      }
    }
    return ans;
  }

  public static ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {

    int left = 0;
    int right = 1;
    int n = A.size();

    Map<Integer, Integer> map = new HashMap<>();
    ArrayList<Integer> ans = new ArrayList<>();

    int prev = A.get(left);
    map.put(prev, map.getOrDefault(prev, 0) + 1);

    while (right < B) {
      int num = A.get(right);
      map.put(num, map.getOrDefault(num, 0) + 1);
      right++;
    }
    left++;
    ans.add(map.size());
    while (left <= n - B) {

      map.put(prev, map.get(prev) - 1);

      if (map.get(prev) <= 0) {
        map.remove(prev);
      }

      int numLeft = A.get(left);
      int numRight = A.get(right);

      map.put(numRight, map.getOrDefault(numRight, 0) + 1);
      ans.add(map.size());
      prev = numLeft;
      left++;
      right++;

    }

    return ans;

  }


  public static int distinctNumbers(ArrayList<Integer> A, int B) {

    HashMap<Integer, Integer> freqMap = new HashMap<>();

    for (int num : A) {
      freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    }

    ArrayList<Integer> distinctNums = new ArrayList<>(freqMap.keySet());
    Collections.sort(distinctNums, (a, b) -> freqMap.get(b) - freqMap.get(a));

    int minXOROps = 0;
    for (int i = 1; i < distinctNums.size() && freqMap.size() > B; i++) {
      int diff = distinctNums.get(i - 1) ^ distinctNums.get(i);
      minXOROps += diff * freqMap.get(distinctNums.get(i));
      freqMap.put(distinctNums.get(i),
          freqMap.get(distinctNums.get(i)) + freqMap.get(distinctNums.get(i - 1)));
      freqMap.remove(distinctNums.get(i - 1));
    }

    return minXOROps;
  }


  public static void main(String[] args) {
    System.out.println(dNums(new ArrayList<>(Arrays.asList(5, 12, 14, 34, 61, 72, 78, 87)), 3));
  }

}

