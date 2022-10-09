package TwoPointers;

import java.util.Arrays;

/**
 * <a
 * href="https://leetcode.com/problems/sort-colors/">Sort Colors</a>
 */
public class SortColors {

    public static void main(String[] args) {
        int[] colors = {2, 0, 2, 1, 1, 0};
//        int[] colors = {2, 0, 1};
//        int[] colors = {0, 2, 1};
        System.out.println(Arrays.toString(calculate(colors)));
    }

    /** it is more like sliding window instead of two pointers, submitted because it is my initial solution
    *  better solution can be found at <a href="https://leetcode.com/problems/sort-colors/solutions/2307871/clean-code-wiith-explaination-two-pointers-java-code/">here</a>
    **/
    private static int[] calculate(int[] colors) {
        int lenColors = colors.length;
        int head = 0, tail = lenColors - 1;

        while (head < tail) {
                if (colors[tail] < colors[head]) {
                    swap(colors, head, tail);
                }

                if (tail == head + 1) {
                    head++;
                    tail = lenColors - 1;
                } else {
                    tail--;
                }

        }

        return colors;
    }

    private static void swap(int[] arr, int ind1, int ind2) {
        int temp = arr[ind2];
        arr[ind2] = arr[ind1];
        arr[ind1] = temp;
    }
}
