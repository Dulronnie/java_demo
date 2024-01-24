package org.example.sort;

import com.sun.deploy.panel.ITreeNode;

import javax.sql.rowset.FilteredRowSet;
import javax.swing.*;
import java.util.Arrays;

/**
 * 希尔排序 = 插入排序的升级版 = 分组 + 插入排序
 * 插入排序每一轮插入的时候是从后往前移动有序区的每一个元素，
 * 如果无序的元素呈现递减的趋势，那么每一轮比较，有序区的所有元素都需要移动；
 *
 *
 * 希尔排序就是解决这个问题
 * 希尔排序在插入排序前，先尽量让数据呈现递增的趋势，具体的做法是
 * 按照一定的取数间隔，取出元素，组成新的小组，对小组进行插入排序；
 * 不断减小取数间隔，直到最后为1，也就是相当于插入排序
 *
 *
 *  时间复杂度分析
 *  时间复杂度不会分析。。。
 *  O(n * (1.3 - 2))
 *  在 O(n * n) 和 O(n * log n) 之间
 *
 *
 * @author hongchuzhen
 * @date 1/20/2024
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        System.out.println(Arrays.toString(arr));

        shellSort(arr);
        System.out.println(Arrays.toString(arr));
        shellSort(arr);
        System.out.println(Arrays.toString(arr));

        // arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        // System.out.println(Arrays.toString(arr));
        // shellSort1(arr);
        // System.out.println(Arrays.toString(arr));
        // shellSort1(arr);
        // System.out.println(Arrays.toString(arr));
        //
        // arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        // System.out.println(Arrays.toString(arr));
        // shellSort2(arr);
        // System.out.println(Arrays.toString(arr));
        // shellSort2(arr);
        // System.out.println(Arrays.toString(arr));

        // arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        // System.out.println(Arrays.toString(arr));
        // shellSort3(arr);
        // System.out.println(Arrays.toString(arr));
        // shellSort3(arr);
        // System.out.println(Arrays.toString(arr));

        arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        System.out.println(Arrays.toString(arr));
        shellSort4(arr);
        System.out.println(Arrays.toString(arr));
        shellSort4(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void shellSort4(int[] arr) {
        if (arr == null) return;

        for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                for ( ; j >= 0; j -= gap) {
                    if (arr[j] > tmp) {
                        arr[j + gap] = arr[j];
                    }else {
                        break;
                    }
                }
                j += gap;
                arr[j] = tmp;
            }
        }
    }

    private static void shellSort3(int[] arr) {
        if (arr == null) return;

        for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
            // 无序区元素
            for (int i = gap; i < arr.length; i++) {
                // 有序区元素
                int tmp = arr[i];
                int j = i - gap;
                for (; j >= 0; j -= gap) {
                    if (arr[j] > tmp) {
                        arr[j + gap] = arr[j];
                    }else {
                        break;
                    }
                }
                j += gap;
                arr[j] = tmp;
            }
        }
    }

    private static void shellSort2(int[] arr) {
        if (arr == null) return;

        for (int gap = arr.length / 2; gap >= 1;gap /= 2) {
            // 无序区元素
            for (int i = gap; i < arr.length; i++) {
                // 插入有序区
                int tmp = arr[i];
                int j = i - gap;
                for (; j >= 0; j -= gap) {
                    if (arr[j] > tmp) {
                        arr[j + gap] = arr[j];
                    }else {
                        break;
                    }
                }
                j += gap;
                arr[j] = tmp;
            }
        }

    }

    private static void shellSort1(int[] arr) {
        if (arr == null) return;


        int count = 0;
        // 插入排序相当于执行一次这里的 gap 为1的操作
        for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
            // 无序区的元素
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;

                // 对有序区的插入操作
                for (; j >= 0; j -= gap) {
                    if (arr[j] > tmp) {
                        arr[j + gap] = arr[j];
                    }else {
                        break;
                    }
                    count++;
                }
                j += gap;
                arr[j] = tmp;
            }
        }
        System.out.println(count);
    }

    private static void shellSort(int[] arr) {
        if (arr == null) return;

        for (int gap = arr.length; gap >= 1; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i - gap;
                int tmp = arr[i];
                for (; j >= 0; j -= gap) {
                    if (arr[j] > tmp) {
                        arr[j + gap] = arr[j];
                    }else {
                        break;
                    }
                }
                j += gap;
                arr[j] = tmp;
            }
        }
    }

}
