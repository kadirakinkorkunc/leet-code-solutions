package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * <a
 * href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">Longest Substring without Repeating Characters</a>
 */
public class LengthOfLongestSubstringWithoutRepeat {
    public static void main(String[] args) {
        System.out.println(calculate("pwwkew"));
    }

    private static int calculate(String text) {
        int leftWindow = 0, lengthOfLongestSubstring = 0;
        char[] textCharArr = text.toCharArray();
        Map<String, Integer> repeatCountForLettersInWindow = new HashMap<>();

        for (int rightWindow = 0; rightWindow < text.length(); rightWindow++) {
            String character = String.valueOf(textCharArr[rightWindow]);
            repeatCountForLettersInWindow.compute(character, (s, integer) -> integer == null ? 1 : (integer + 1));

            while (repeatCountForLettersInWindow.get(character) > 1) {
                String leftWindowChar = String.valueOf(textCharArr[leftWindow]);
                Integer leftWindowCharRepeatCount = repeatCountForLettersInWindow.get(leftWindowChar);
                repeatCountForLettersInWindow.put(leftWindowChar, leftWindowCharRepeatCount - 1);
                leftWindow++;
            }

            lengthOfLongestSubstring = Math.max(lengthOfLongestSubstring, rightWindow - leftWindow + 1);
        }

        return lengthOfLongestSubstring;
    }
}
