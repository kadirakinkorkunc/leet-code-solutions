package SlidingWindow;

/**
 * <a
 * href="https://leetcode.com/problems/minimum-size-subarray-sum/">Minimum Size Subarray Sum</a>
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int target = 1;
        int[] nums = {1, 4, 4};
        System.out.println(calculate(target, nums));
    }

    private static int calculate(int target, int[] nums) {
        int maxLimit = (int)(Math.pow(10, 9) + 1);
        int minElSize = maxLimit, leftWindow = 0, total = 0;
        for (int rightWindow = 0; rightWindow < nums.length;) {
            total += nums[rightWindow];

            while (total >= target) {
                minElSize = Math.min(minElSize, rightWindow - leftWindow + 1);
                total -= nums[leftWindow];
                leftWindow++;
            }

            rightWindow++;
        }

        return minElSize == maxLimit ? 0 : minElSize;
    }
}
