package array.hashing;

import com.sun.xml.internal.ws.util.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hashing {

  public static void main(String[] args) {
    // threeSum(new int[]{0, 0, 0, 0});

    groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
  }

  public static List<List<String>> groupAnagrams(String[] strs) {

    List<List<String>> list = new ArrayList<>();

    HashMap<String, List<String>> map = new HashMap<>();

    for (int i = 0; i < strs.length; i++) {
      char[] c = strs[i].toCharArray();
      Arrays.sort(c);

      if (map.containsKey(String.copyValueOf(c))) {
        map.get(String.copyValueOf(c)).add(strs[i]);
      } else {
        map.put(String.copyValueOf(c), new ArrayList<>());
        map.get(String.copyValueOf(c)).add(strs[i]);
      }
    }

    for(List<String> l : map.values()){

      list.add(l);

    }

    return list;
  }


  public static List<List<Integer>> threeSum(int[] nums) {

    List<List<Integer>> list = new ArrayList<>();

    Arrays.sort(nums);

    for (int i = 0; i < nums.length; i++) {

      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      int j = i + 1;
      int k = nums.length - 1;

      while (k > j) {

        int sum = nums[i] + nums[j] + nums[k];
        if (sum == 0) {
          list.add(Arrays.asList(nums[i], nums[j], nums[k]));
          while (k > j && nums[j] == nums[j + 1]) {
            j++;
          }

          j++;
          k--;
        } else if (sum > 0) {
          k--;
        } else {
          j++;
        }
      }

    }

    return list;


  }


  public static List<List<Integer>> fourSum(int[] nums, int target) {

    List<List<Integer>> list = new ArrayList<>();

    return list;
  }

  public int[] twoSum(int[] nums, int t) {
    Map<Integer, Integer> map = new HashMap<>();
    int ans[] = new int[2];
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(t - nums[i])) {
        ans[0] = map.get(t - nums[i]);
        ans[1] = i;
        break;
      }
      map.put(nums[i], i);
    }
    return ans;
  }


  private boolean isAnagram(String a, String b) {

    boolean flag = false;

    char c1[] = a.toCharArray();
    char c2[] = b.toCharArray();

    Arrays.sort(c1);
    Arrays.sort(c2);

    return Arrays.equals(c1, c2);

  }

}
