package org.example.sort;

import java.io.CharArrayReader;
import java.util.Arrays;

/**
 * 归并排序
 *
 * 分治思想
 * 把数组分成两组，对数组进行归并
 * 递归处理
 * 只能用 后序遍历，
 *
 * 因为只有递归最底层的那一次归并结果才是正确的，后序遍历，使得之后的遍历依赖在之前的遍历结果上，最后的总结果才是正确的；
 * 如果是前序遍历，会使得之后的归并建立在之间的归并上，但是，之前的归并结果并不是完全正确的。（如果是，只需要一次归并就可以了，就不用搞什么递归、分治了）
 *
 * 时间复杂度
 * 每次不断 二分处理（可以n分处理） ，每一轮都需要遍历每一个元素。 O( n * log n)。这里不会退化为 ( n * n)
 *
 * 空间复杂度
 *  归并的时候需要创建临时数组，O(n)
 *
 *  稳定性
 *  不稳定  [5,5,5,5]
 *
 *
 * @author hongchuzhen
 * @date 1/20/2024
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        System.out.println(Arrays.toString(arr));
        int[] tmpArr = new int[arr.length];

        mergeSort(arr,0,arr.length - 1,tmpArr);
        System.out.println(Arrays.toString(arr));
        mergeSort(arr,0,arr.length - 1,tmpArr);
        System.out.println(Arrays.toString(arr));

        arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        System.out.println(Arrays.toString(arr));
        mergeSort1(arr,0,arr.length - 1,tmpArr);
        System.out.println(Arrays.toString(arr));
        mergeSort1(arr,0,arr.length - 1,tmpArr);
        System.out.println(Arrays.toString(arr));

    }

    private static void mergeSort1(int[] arr, int left, int right, int[] tmpArr) {
        if (arr == null || left >= right) return;

        int middle = left + ((right - left) >> 1);
        mergeSort1(arr,left,middle,tmpArr);
        mergeSort1(arr,middle + 1,right,tmpArr);

        int leftP = left;
        int rightP = middle + 1;
        int tmpP = 0;
        while (leftP <= middle && rightP <= right ) {
            if (arr[leftP] <= arr[rightP]) {
                tmpArr[tmpP] = arr[leftP];
                leftP++;
            }else {
                tmpArr[tmpP] = arr[rightP];
                rightP++;
            }
            tmpP++;
        }

        while (leftP <= middle) {
            tmpArr[tmpP] = arr[leftP];
            leftP++;
            tmpP++;
        }

        while (rightP <= right) {
            tmpArr[tmpP] = arr[rightP];
            rightP++;
            tmpP++;
        }

        for (int i = 0; i < tmpP; i++) {
            arr[left + i] = tmpArr[i];
        }


    }

    private static void mergeSort(int[] arr, int left, int right,int[] tmpArr) {
        if (arr == null || left >= right) return;

        int half = left + ((right - left) >> 1);
        mergeSort(arr,left,half,tmpArr);
        mergeSort(arr,half + 1,right,tmpArr);

        int leftP = left;
        int rightP = half + 1;
        int tmpP = 0;
        while (leftP <= half &&  rightP <= right) {
            if (arr[leftP] < arr[rightP]) {
                tmpArr[tmpP] = arr[leftP];
                leftP++;
            }else {
                tmpArr[tmpP] = arr[rightP];
                rightP++;
            }
            tmpP++;
        }

        while (leftP <= half) {
            tmpArr[tmpP] = arr[leftP];
            leftP++;
            tmpP++;
        }

        while (rightP <= right) {
            tmpArr[tmpP] = arr[rightP];
            rightP++;
            tmpP++;
        }

        for (int i = 0; i < tmpP; i++) {
            arr[left + i] = tmpArr[i];
        }
    }
}
