package org.example.sort;

import java.util.Arrays;

/**
 * 快速排序算法
 *
 * 核心思想是，分治的思想。
 * 找到一个基准值，依据他把数组分为两个数组；左边的数组数字都比他小，右边的都比他大
 * 在每个数组里重复这样的操作
 *
 * 每一层划分数组的操作 都需要遍历一遍数组 (n),数组被递归地二分，(log n),时间复杂度为 ( n * log n );
 * 如果每一次划分数组的时候，都恰好把数组分成了一个完整的数组和一个空的数组，也就是二叉树退化为链表， 最坏时间复杂度为 ( n * n)
 *
 * 1、找到基准值（中间值，一般取数组第一个数)
 * 2、找到左边比基准值大的数组下标（左指针）；找到右边第一个比基准值小的坐标（右指针）；交换这两个数
 * 3、左右指针相遇的那个节点（相遇点）的值与基准值交换
 * 4、每次寻找基准值的时候，应该从右边开始，再从左边开始。
 *
 * （需要保证左右指针相遇的那个节点的值小于基准值。
 *   如果先从左指针开始，右指针又不用移动，相遇点的值就有可能比基准值要大，从而达不到基准值的基本要求。
 *   左指针停下来，1，到达数组边界；2，找到比基准值更大的值；
 *  右指针停下来的的原因，1，到达数组边界，2，找到比基准值更小的值）
 *
 * @author hongchuzhen
 * @date 1/19/2024
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        System.out.println(Arrays.toString(arr));

        quickSort(arr,0,arr.length - 1);

        System.out.println(Arrays.toString(arr));

        quickSort1(arr,0,arr.length - 1);
        System.out.println("quickSort1" + Arrays.toString(arr));

        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    public static void quickSort(int[] arr, int low, int high) {
        if (high <= low) return;

        int left = low;
        int right = high;
        while (left < right) {
            while (right > left && arr[right] >= arr[low]) {
                right--;
            }

            while (left < right && arr[left] <= arr[low]) {
                left++;
            }

            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
        }

        // left 和 right 相等
        int tmp = arr[low];
        arr[low] = arr[right];
        arr[right] = tmp;


        quickSort(arr,low,right - 1);
        quickSort(arr,right + 1,high);
    }


    private static void quickSort1(int[] arr, int low, int high) {
        if (high <= low) return;

        int left = low;
        int right = high;
        while (left < right) {
            while (right > left && arr[right] >= arr[low]){
                right--;
            }
            while (left < right && arr[left] <= arr[low]) {
                left++;
            }
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
        }

        int tmp = arr[low];
        arr[low] = arr[right];
        arr[right] = tmp;

        quickSort1(arr,low,right - 1);
        quickSort1(arr,right + 1,high);
    }


}
