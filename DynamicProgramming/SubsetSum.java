
public class SubsetSum {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return false;

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum % 2 == 1)
            return false;
        int halfsum = totalSum / 2;

        // int[][] dp = new int[n][halfsum+1];

        // for(int[] row: dp){
        // Arrays.fill(row, -1);
        // }
        // return helper(n-1, halfsum, nums, dp);

        return helperTabulaiton(nums, halfsum);
    }

    private boolean helper(int curr, int sum, int[] nums, int[][] dp) {
        if (curr < 0)
            return false;
        if (sum == 0)
            return true; // we can make the sum

        int currNum = nums[curr];
        if (dp[curr][sum] != -1)
            return dp[curr][sum] == 1 ? true : false;

        boolean ifPossible = false;
        if (currNum <= sum) {
            ifPossible = helper(curr - 1, sum - currNum, nums, dp);
        }

        boolean notPossible = helper(curr - 1, sum, nums, dp);

        boolean result = ifPossible || notPossible;
        dp[curr][sum] = result ? 1 : 0;
        return result;
    }

    private boolean helperTabulaiton(int[] nums, int sum) {
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int j = 0; j <= sum; j++) {
            dp[0][j] = false;
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            int curr = nums[i - 1];
            for (int j = 1; j <= sum; j++) {

                boolean possible = false;
                if (curr <= j) {
                    possible = dp[i - 1][j - curr];
                }
                boolean notPossible = dp[i - 1][j];

                dp[i][j] = possible || notPossible;
            }
        }

        return dp[n][sum];
    }
}