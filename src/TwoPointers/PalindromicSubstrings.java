package TwoPointers;

/**
 * <a
 * href="https://leetcode.com/problems/palindromic-substrings/">Palindromic Substrings</a>
 */
public class PalindromicSubstrings {

    public static void main(String[] args) {
//        String test = "abc";
        String test = "aaa";
        System.out.println(bruteForce(test));
        System.out.println(dpSolution(test));
    }

    /**
     * sliding window with two pointer(isPalindromic func)
     */
    private static int bruteForce(String s) {
        long startingTime = System.nanoTime();
        int rightWindow = 0, leftWindow = 0, countOfPalindromicSubstr = 0;
        char[] chars = s.toCharArray();
        while (rightWindow < chars.length) {
            if (isPalindromic(s.substring(leftWindow, rightWindow + 1).toCharArray())) countOfPalindromicSubstr++;

            if (rightWindow == chars.length - 1) {
                leftWindow++;
                rightWindow = leftWindow;
            } else {
                rightWindow++;
            }
        }
        System.out.println("bruteForce: " + (System.nanoTime() - startingTime));
        return countOfPalindromicSubstr;
    }

    private static boolean isPalindromic(char[] substring) {
        int head = 0, tail = substring.length - 1;
        while (head < tail) {
            if (substring[head] != substring[tail]) {
                return false;
            }
            head++;
            tail--;
        }
        return true;
    }


    /**
     * (all 1 chars) && (2 chars with same characters) are already palindrome
     * when len(substr) > 2 check first and last char and matrix for inner substring
     */
    private static int dpSolution(String s) {
        long startingTime = System.nanoTime();
        int len = s.length(), countOfPalindromicSubstr = 0;
        boolean[][] dpArr = new boolean[len][len];

        for (int i = 0; i < len; i++) { // len == 1, already a palindrome
            dpArr[i][i] = true;
            countOfPalindromicSubstr++;
        }

        for (int col = 1; col < len; col++) {
            for (int row = 0; row < col; row++) {
                boolean equalChar = s.charAt(col) == s.charAt(row);
                if ((row == col - 1 && equalChar) // len == 2
                        || (equalChar && dpArr[row + 1][col - 1]) // len > 2
                ) {
                    dpArr[row][col] = true;
                    countOfPalindromicSubstr++;
                }
            }
        }

        System.out.println("dpSolution:" + (System.nanoTime() - startingTime));
        return countOfPalindromicSubstr;
    }
}
