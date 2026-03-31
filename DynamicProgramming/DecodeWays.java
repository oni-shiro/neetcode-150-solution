import java.util.Arrays;

public class DecodeWays {
    /**
     * 
     * 124
     * 1,2,4
     * 1,24
     * 12,4
     * 
     * for each digit either decode alone or decode with next digit, for 0 it can
     * not be start
     */

    // decodeRecursive signifies how many ways we can
    // further decode standing at curr index, so f(x) = f(x+1) + f(x+2)
    private int decodeRecursive(int curr, String s, int n, int[] dp) {
        if (curr == n)
            return 1;
        if (s.charAt(curr) == '0')
            return 0; // invalid

        if (dp[curr] != -1)
            return dp[curr];
        int waysToDecodeFromHere = decodeRecursive(curr + 1, s, n, dp);

        // Remember substring excludes the end so basically we are taking curr,curr+1
        if (curr + 1 < n && validTwoDigits(s.substring(curr, curr + 2))) {
            waysToDecodeFromHere += decodeRecursive(curr + 2, s, n, dp);
        }

        return dp[curr] = waysToDecodeFromHere;
    }

    private boolean validTwoDigits(String sub) {
        int num = Integer.parseInt(sub);
        return num >= 10 && num <= 26;
    }

    public int numDecodings(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return decodeRecursive(0, s, s.length(), dp);
    }
}
