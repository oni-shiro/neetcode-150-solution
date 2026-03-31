import java.util.Arrays;

public class UnboundedKnapsack {

    private int helper(int i, int amount, int[] coins, int[][] dp) {
        if (amount == 0)
            return 0;
        if (i == 0)
            return Integer.MAX_VALUE / 2;

        if (dp[i][amount] != -1)
            return dp[i][amount];

        int skip = helper(i - 1, amount, coins, dp);

        int take = Integer.MAX_VALUE / 2;
        if (coins[i - 1] <= amount) {
            take = 1 + helper(i, amount - coins[i - 1], coins, dp);
        }

        return dp[i][amount] = Math.min(skip, take);
    }

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        int ans = helper(n, amount, coins, dp);
        return ans >= Integer.MAX_VALUE / 2 ? -1 : ans;
    }

    public int coinChangeTab(int[] coins, int amount) {

        int n = coins.length;
        int INF = Integer.MAX_VALUE / 2;

        int[][] dp = new int[n + 1][amount + 1];

        // Base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int a = 1; a <= amount; a++) {
            dp[0][a] = INF;
        }

        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= amount; a++) {

                int skip = dp[i - 1][a];

                int take = INF;
                if (coins[i - 1] <= a) {
                    take = 1 + dp[i][a - coins[i - 1]];
                }

                dp[i][a] = Math.min(skip, take);
            }
        }

        int ans = dp[n][amount];
        return ans >= INF ? -1 : ans;
    }
}