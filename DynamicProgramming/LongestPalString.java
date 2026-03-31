public class LongestPalString {
    public String longestPalindrome(String s) {
        int n = s.length();
        int st = 0;
        int en = 0;

        // All the centers
        for (int i = 0; i < n; i++) {
            int lenOdd = expand(i, i, s, n); // center is at the ith character
            int lenEven = expand(i, i + 1, s, n); // center is at gap, so left right is 2 characters
            int maxLen = Math.max(lenOdd, lenEven);

            // bigger than prev
            if (maxLen > en - st) {
                st = i - (maxLen - 1) / 2; // start from odd case
                en = i + maxLen / 2; // end from even case, covers both
            }
        }
        return s.substring(st, en + 1);
    }

    private int expand(int le, int ri, String s, int n) {
        while (le >= 0 && ri < n && s.charAt(le) == s.charAt(ri)) {
            // if we were asked to count the substrings,
            // we just do a count++ here, and then store the odd and even part and sum them up at the end ( this is for count substring problem, not for longest palindromic substring)
            le--;
            ri++;
        }
        return ri - le - 1;
    }
}
