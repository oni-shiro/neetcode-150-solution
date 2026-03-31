package SlidingWindow;
import java.util.HashMap;
import java.util.Map;

/**
 * Is there only one possible answer? Or return the first one you find?
 * 
 * TFSDSKSD, SSD
 * 
 * 
 * S-2
 * D-1
 * 
 * SDS
 * S - 0
 * D - 0
 * FOUND = 3
 * 
 * TFSDS
 */

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {

        if(t.length() > s.length()) {
            return "";
        }
        
        Map<Character, Integer> tCharCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            tCharCount.put(c, tCharCount.getOrDefault(c, 0) + 1);
        }

        int requiredMatches = tCharCount.size();

        int le = 0, ri = 0;
        int foundSoFar = 0;
        int minLen = Integer.MAX_VALUE;
        int startIndex = 0;

        while(ri<s.length()){
            char curr = s.charAt(ri);

            if(tCharCount.containsKey(curr)){
                tCharCount.put(curr, tCharCount.get(curr)-1);
                
                // We can still pick this character
                if(tCharCount.get(curr) >= 0){
                    foundSoFar++;
                }
            }

            while(foundSoFar == requiredMatches){
                // Update the minimum length
                int windowSize = ri - le + 1;
                minLen = Math.min(minLen, windowSize);
                startIndex = le;

                // Try to contract the window from the left
                char leftChar = s.charAt(le);
                if(tCharCount.containsKey(leftChar)){
                    tCharCount.put(leftChar, tCharCount.get(leftChar)+1);

                    // We are removing a character which was required
                    if(tCharCount.get(leftChar) > 0){
                        foundSoFar--;
                    }
                }

                le++;
            }

            ri++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLen);
    }
}
