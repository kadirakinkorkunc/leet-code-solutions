package SlidingWindow;

import java.util.Arrays;

/**
 * <a
 * href="https://leetcode.com/problems/two-sum/">Two Sum</a>
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] input = {2, 7, 11, 15};
        int target = 6;
        System.out.println(Arrays.toString(calculate(input, target)));
    }

    private static int[] calculate(int[] nums, int target) {
        int leftWindow = 0, rightWindow = 0;

        while (leftWindow == rightWindow || nums[leftWindow] + nums[rightWindow] != target) {
            if (rightWindow == (nums.length - 1)) {
                leftWindow++;
                rightWindow = 0;
            }
            rightWindow++;
        }

        return new int[]{leftWindow, rightWindow};
    }
}
