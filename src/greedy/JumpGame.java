package greedy;

public class JumpGame {

  public static boolean canJumpGreedy(int[] nums) {
    int currentFinalPosn = nums.length - 1;
    int n = nums.length;

    for (int i = n - 2; i >= 0; i--) {

      if (i + nums[i] >= currentFinalPosn) {
        currentFinalPosn = i;
      }
    }

    return currentFinalPosn == 0;

  }

  public static boolean canJump(int[] nums) {
    Boolean[] dp = new Boolean[nums.length];
    return canJumpRec(nums, 0, nums.length - 1, dp);
  }

  public static boolean canJumpRec(int[] nums, int posn, int end, Boolean[] dp) {
    if (posn >= end) {
      return true;
    }

    if (dp[posn] != null) {
      return dp[posn];
    }

    int jumpMax = nums[posn];
    if (jumpMax < 1) {
      return false;
    }

    for (int i = 1; i <= jumpMax; i++) {
      boolean flag = canJumpRec(nums, posn + i, end, dp);
      dp[posn] = flag;
      if (flag) {
        return true;
      }
    }

    return false;
  }


  /**
   * the idea is to calculate farthest jump from each array index and the repetitively doing the
   * same.
   */

  public static int jumpGame2(int[] nums) {
    int minJumps = 0;
    int n = nums.length;
    int r = 0;
    int l = 0;

    while (r < n - 1) {
      int farthest = 0;
      for (int i = l; i <= r; i++) {
        farthest = Math.max(farthest, i + nums[i]);
      }
      l = r + 1;
      r = farthest;
      minJumps += 1;
    }
    return minJumps;
  }

  public static void main(String[] args) {
    System.out.println(jumpGame2(new int[]{2, 3, 1, 1, 4}));
  }

}
