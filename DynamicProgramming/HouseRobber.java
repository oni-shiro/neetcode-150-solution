
import java.util.Arrays;

public class HouseRobber {

    private int robViaRecursion(int curr, int[] nums, int[] dp) {
        if (curr >= nums.length) {
            return 0;
        }

        if (dp[curr] != -1)
            return dp[curr];

        int pick = nums[curr] + robViaRecursion(curr + 2, nums, dp);
        int notPick = robViaRecursion(curr + 1, nums, dp);

        return dp[curr] = Math.max(pick, notPick);
    }

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 0);
        return robViaRecursion(0, nums, dp);
    }

    /**
     * 
     * For Circular House Robber, we can not rob the first and last house together, so we can
     * rob either from 0 to n-2 or from 1 to n-1, and take the maximum of both
     */
}
