package TwoPointers;

import java.util.Arrays;

/**
 * <a
 * href="https://leetcode.com/problems/merge-sorted-array/">Merge Sorted Array</a>
 */
public class MergeSortedArray {

    /**
     * not mine but i guess this is the simplest and cleanest solution: <a href="https://leetcode.com/problems/merge-sorted-array/solutions/1189409/my-java-solution/">click</a>
     */
    public static void main(String[] args) {
//        int[] nums1 = {1,2,3,0,0,0};
        int[] nums1 = {0, 0, 3, 0, 0, 0, 0, 0, 0};
//        int[] nums1 = {-1,3,0,0,0,0,0};
//        int[] nums2= {2,5,6};
        int[] nums2 = {-1, 1, 1, 1, 2, 3};
//        int[] nums2 = {0,0,1,2,3};
        int n = nums2.length;
        int m = nums1.length - nums2.length;
        System.out.println(Arrays.toString(solution1(nums1, m, nums2, n)));
        System.out.println(Arrays.toString(solution2(nums1, m, nums2, n)));
    }

    private static int[] solution2(int[] nums1, int m, int[] nums2, int n) {
        /*
        * take the temp list to your hand and start comparing with the base list,
        * find the insertion index, shift the rest to right(if required) and insert the value
        */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nums1.length; j++) {
                if (nums2[i] < nums1[j]) {
                    insert(nums1, j, nums2[i]);
                    m++;
                    break;
                } else if (nums1[j] == 0 && j > m - 1) {
                    nums1[j] = nums2[i];
                    m++;
                    break;
                }

            }
        }

        return nums1;
    }

    private static void insert(int[] nums1, int index, int num) {
        // most time consuming place with 'anotherSolution' => %56
        System.arraycopy(nums1, index, nums1, index + 1, nums1.length - 1 - index);
        nums1[index] = num;
    }

    private static int[] solution1(int[] nums1, int m, int[] nums2, int n) {
        /* move nums2 to num1 then sort num1 with brute force */
        System.arraycopy(nums2, 0, nums1, m, nums2.length);

        int head = 0, tail = nums1.length - 1;
        while (head < tail) {
            if (nums1[head] > nums1[tail]) {
                swap(nums1, head, tail);
            }

            if (tail == head + 1) {
                head++;
                tail = nums1.length - 1;
            } else {
                tail--;
            }
        }
        return nums1;
    }


    private static void swap(int[] arr, int ind1, int ind2) {
        int temp = arr[ind2];
        arr[ind2] = arr[ind1];
        arr[ind1] = temp;
    }
}
