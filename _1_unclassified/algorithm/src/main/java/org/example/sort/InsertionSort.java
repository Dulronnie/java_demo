package org.example.sort;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

import javax.swing.*;
import java.util.Arrays;

/**
 * 插入排序
 * 假设前面的元素都是有序的；
 * 从无序区获取一个元素，在有序区进行遍历，找到适合自己的位置；
 * 把不适合自己的元素向后后移动
 * 把元素插入到找到的位置
 *
 * 时间复杂度分析
 * 一共比较 n 轮，每轮比较、移动 n 个元素，一共为 O(n * n)
 *
 *
 * @author hongchuzhen
 * @date 1/20/2024
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 23, 12, 1, 3, 4, 56, 43, 22, 43, 11, 32, 122, 12};
        System.out.println(Arrays.toString(arr));

        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));

        // insertionSort1(arr);
        // System.out.println(Arrays.toString(arr));
        // insertionSort1(arr);
        // System.out.println(Arrays.toString(arr));
        //
        // insertionSort2(arr);
        // System.out.println(Arrays.toString(arr));
        // insertionSort2(arr);
        // System.out.println(Arrays.toString(arr));
        //
        //
        // arr = new int[] { 23, 12, 1, 3, 4, 56, 43, 22, 43, 11, 32, 122, 12};
        // insertionSort3(arr);
        // System.out.println(Arrays.toString(arr));
        // insertionSort3(arr);
        // System.out.println(Arrays.toString(arr));
        //
        // arr = new int[] { 23, 12, 1, 3, 4, 56, 43, 22, 43, 11, 32, 122, 12};
        // insertionSort4(arr);
        // System.out.println(Arrays.toString(arr));
        // insertionSort4(arr);
        // System.out.println(Arrays.toString(arr));

        arr = new int[] { 23, 12, 1, 3, 4, 56, 43, 22, 43, 11, 32, 122, 12};
        System.out.println(Arrays.toString(arr));
        insertionSort6(arr);
        System.out.println(Arrays.toString(arr));
        insertionSort6(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static void insertionSort6(int[] arr) {
        if (arr == null) return;

        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            for (;j >= 0; j--) {
                if (arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                }else {
                    break;
                }
            }
            j += 1;
            arr[j] = tmp;
        }
    }

    private static void insertionSort5(int[] arr) {
        if (arr == null) return;

        // 无序区的元素
        for (int i = 1; i < arr.length; i++) {
            // 有序区的元素
            int tmp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                }else {
                    break;
                }
            }
            j++;
            arr[j] = tmp;
        }
    }

    private static void insertionSort4(int[] arr) {
        if (arr == null) return;

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                }else {
                    break;
                }
                count++;
            }
            j++;
            arr[j] = tmp;
        }

        System.out.println(count);
    }

    private static void insertionSort3(int[] arr) {
        if (arr == null) return;

        for (int i = 1; i < arr.length;i++) {
            int tmp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                }else {
                    break;
                }
            }
            j++;
            arr[j] = tmp;
        }
    }

    private static void insertionSort2(int[] arr) {
        if (arr == null) return;

        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            // j 有序区的每一位
            for (; j >= 0; j--) {
                if (arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                }else {
                    break;
                }
            }
            j++;
            arr[j] = tmp;
        }
    }

    private static void insertionSort1(int[] arr) {
        if (arr == null) return ;

        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                }else {
                    break;
                }
            }
            j++;
            arr[j] = tmp;
        }
    }

    private static void insertionSort(int[] arr) {
        if (arr == null) return;

        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                }else {
                    break;
                }
            }
            j++;
            arr[j] = tmp;
        }
    }


}
