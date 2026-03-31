import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * thought : does any combination of words from dict create the actual word.
 * basically pick and choose word and repeat can happen (according to question)
 * so basically unbound knapsack
 * 
 * But this does not work, as order of picking up word matters -> this will fail
 * 
 * every possible substring, we check if exisits in list, then from there for
 * rest of the string we again call the function,
 */
public class WordBreak {

    // This is the intuitive sol TC - O(N^2) as for each index we are checking all the substrings, and for each substring we are doing O(N) work to create the substring
    // this is given assume lookup in set is O(1) and substring creaiton is O(1), other wise it will be O(N^3)
    private boolean canFormStringFromthisIndex(int id, Set<String> set, String s, int n, int[] dp) {
        if (id == n)
            return true; // whole string is complete, always true
        if (dp[id] != -1)
            return dp[id] == 1 ? true : false;
        for (int itr = id; itr < n; itr++) {
            String substr = s.substring(id, itr + 1); // till itr
            if (set.contains(substr) && canFormStringFromthisIndex(itr + 1, set, s, n, dp)) {
                dp[id] = 1;
                return true;
            }
        }

        dp[id] = 0;
        return false;
    }

    private boolean wordBreakTabulation(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // empty string can always be formed

        Set<String> set = new HashSet<>(wordDict);

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String substr = s.substring(j, i); // till i-1
                if (set.contains(substr) && dp[j]) {
                    dp[i] = true;
                }
            }
        }

        return dp[n];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        return canFormStringFromthisIndex(0, new HashSet<>(wordDict), s, s.length());
    }

}
