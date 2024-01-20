package org.example.sort;

import java.util.Arrays;

/**
 * 冒泡排序 （杰哥当时提了一个应用场景的问题，我的答案是代码简单，可用性强）
 *
 * 每一轮比较都会在数组的后部，获得一个正确排序的元素，即为冒泡
 *
 * 两两比较，不断后移
 * 1、 n 个元素需要比较 n 轮
 * 2、 每一轮比较的范围是从第一个开始 到 最后一个无序元素
 *
 * 时间复制度分析
 * O(n * n)
 *
 * 空间复杂度分析
 * O(1) 只需要几个辅助变量，与 n 无关
 *
 * @author hongchuzhen
 * @date 1/20/2024
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        System.out.println(Arrays.toString(arr));

        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static void bubbleSort(int[] arr) {
        if (arr == null) return;

        // 如果无序区的数组是有序的，那么就不用再排序了
        boolean tag;
        for (int i = 0; i < arr.length; i++) {
            tag = true;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] >= arr[j]) {
                    int tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
                tag = false;
            }

            if (tag) {
                break;
            }
        }
    }
}
