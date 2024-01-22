package org.example.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 最符合常识的一种排序，我的第一个排序
 * <p>
 * 每次挑出最大，最小的那个，跟第 n 位元素交换位置
 * <p>
 * 时间复杂度
 *  O(n * n)
 * <p>
 * 空间复杂度
 *  O(1)
 *
 * @author hongchuzhen
 * @date 1/20/2024
 */
public class selectionSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        System.out.println(Arrays.toString(arr));
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));

        selectionSort(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        selectionSort1(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static void selectionSort1(int[] arr) {
        if (arr == null ) return;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] <= arr[i]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    private static void selectionSort(int[] arr) {
        if (arr == null) return;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] >  arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }
}
