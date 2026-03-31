package SlidingWindow;
import java.util.HashMap;
import java.util.Map;

public class StringPerm {
    private Map<Character, Integer> buildCharCountMap(String s) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        return charCountMap;
    }

    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> s1CharCount = buildCharCountMap(s1);
        int windowSize = s1.length();

        int requiredMatches = s1CharCount.size();

        int i = 0, j = 0;

        int s2Length = s2.length();

        while (j < s2Length) {

            char curr = s2.charAt(j);
            if (s1CharCount.containsKey(curr)) {
                s1CharCount.put(curr, s1CharCount.get(curr) - 1);

                if (s1CharCount.get(curr) == 0) {
                    requiredMatches--;
                }
            }

            if (j - i + 1 > windowSize) {
                char leftChar = s2.charAt(i);
                if (s1CharCount.containsKey(leftChar)) {
                    // check first,
                    if (s1CharCount.get(leftChar) == 0) {
                        requiredMatches++;
                    }
                    s1CharCount.put(leftChar, s1CharCount.get(leftChar) + 1);

                }
                i++;
            }
            j++;

            if (requiredMatches == 0) {
                return true;
            }
        }

        return false;
    }
}
