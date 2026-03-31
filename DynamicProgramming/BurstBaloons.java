/**
    THIS IS A DIVIDE n CONQUERE + INTERVAL DP

    here key thinking is instead of calculating linearly for a var, left->right or right-> left
    we partition, f[i] -> f[i-1] and f[i+1]

    Now for this problem, 
    which baloon to burst => makes the state dynamic for other neighbours which is wrong

    which baloon to burst last in range (i, j) => by logic kth baloon(say) neighbor become = i-1, j+1
    f(i,j) = f(i-1) + k*i-1*j+1 + f(j+1)
 */

class BurstBaloons {

    int helper(int l, int r, int[] nums, int[][] dp) {
        if (l > r) return 0;

        if (dp[l][r] != -1) return dp[l][r];

        int ans = 0;

        for (int k = l; k <= r; k++) {
            int coins = nums[l - 1] * nums[k] * nums[r + 1];

            coins += helper(l, k - 1, nums, dp)
                   + helper(k + 1, r, nums, dp);

            ans = Math.max(ans, coins);
        }

        return dp[l][r] = ans;
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;

        // padded array
        int[] arr = new int[n + 2];
        arr[0] = arr[n + 1] = 1;

        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];
        for (int[] row : dp) Arrays.fill(row, -1);

        return helper(1, n, arr, dp);
    }
}