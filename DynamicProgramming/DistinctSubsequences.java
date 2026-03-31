/**

    find collection of chars from s in order, which matches t, subsequence -> char can be skipped no need to be continuos

    q1. is it guranteed to have an answer,
    q2. what is the lenght of s and t at max

    generating subseq = 2^n tc -> brute

    my current thought is -> we match char by char, if match -> either we pick that, or skip that, if not match -> skip, if we exhaust t by this logic, we do +1, otherwise not

if match -> 1 + f(s-1,t-1)
if not -> f(s-1, t)

if(t == 0) return 1; // found
if(s == 0) retunr 0; // not found
 */

class DistinctSubsequences {

    public int numDistinct(String s, String t) {

        int n = s.length();
        int m = t.length();

        if(n<m) return 0;

        // dp[i][j] = num of ways to formulate t[j:] using s[i] so this is a bottom up dp
        // answer is at dp[0][0]
        int[][] dp = new int[n+1][m+1];

        for(int i = 0; i<=n; i++){
            dp[i][m] = 1; // t string is empty
        }

        for(int j = 0; j<m; j++){
            dp[n][j] = 0; // no s tring
        }

        for(int i = n-1; i>=0; i--){
            for(int j = m-1; j>=0; j--){
                if(t.charAt(j) == s.charAt(i)){
                    dp[i][j] = dp[i+1][j+1] + dp[i+1][j]; //pick+notpick
                }else{
                    dp[i][j] = dp[i+1][j];  // only notpick
                }
            }
        }

        return dp[0][0];
    }
}