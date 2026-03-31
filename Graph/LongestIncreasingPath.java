public class LongestIncreasingPath {
    public static int[][] delta = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int longestPath(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];

        int absAns = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                absAns = Math.max(absAns, dfs(i, j, matrix, dp));
            }
        }

        return absAns;

    }

    private int dfs(int i, int j, int[][] matrix, int[][] dp) {

        if (dp[i][j] != 0)
            return dp[i][j];

        int n = matrix.length;
        int m = matrix[0].length;

        int ans = 1; // always longest paht can be node itself

        for (int d = 0; d < 4; d++) {
            int di = i + delta[d][0];
            int dj = j + delta[d][1];

            if ((di >= 0 && di < n) && (dj>=0 &&dj < m) && (matrix[di][dj] > matrix[i][j])) {
                ans = Math.max(ans, 1 + dfs(di, dj, matrix, dp));
            }
        }

        dp[i][j] = ans;
        return ans;
    }
}