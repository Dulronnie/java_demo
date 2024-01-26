package org.example.sort;

import jdk.nashorn.internal.parser.Lexer;

import java.io.CharArrayReader;
import java.util.Arrays;

/**
 *  堆排序 = 构建大顶堆（小顶堆） + [交换顶点元素 + 调整大顶堆]
 *
 *  1、把无序数组构建成一个大顶堆，此时，根节点就是最大的值
 *  2、取第一个元素，将其与无序数组的最后一个元素交换，调整最大堆
 *  3、重复执行第二部，直到只剩下一个节点
 *
 *  （一般升序用大顶堆，降序用小顶堆）
 *
 *  用数组(开始节点为 0 )表示完全二叉树的逻辑：
 *  根节点  :  i
 *  左子节点: 2i + 1
 *  右子节点: 2i + 2
 *
 *  最大堆构建：
 *  1、假设父节点的值最大
 *  2、如果左节点的值比父节点的值大，交换左节点与父节点的值（记录左节点）
 *  3、如果有节点的值比父节点的值大，交换右节点与父节点的值（记录右节点）
 *  4、如果记录的节点与父节点不一致，说明该节点发生了调整；需要对该节点的子树进行调整（递归）
 *
 *  构建注意点：
 *  1、只需要调整根节点，不需要调整叶子节点；
 *  2、从下往上（这里不能从上往下。从定义可知，根节点大于或等于左右节点；从下往上，可以保证上一层的子节点都是最大的，从而最后根节点是最大的；如果是从上往下，只能保证根节点是最高一层的三个节点中最大的一个）
 *  3、从左到右（随便）
 *
 *  最后一个非叶子节点： n / 2 - 1
 *  推导：(todo)
 *  自己画一个只有三个节点的完全二叉树
 *
 *  调整最大堆的顺序：从上往下（从下往上当然也可以，只是浪费不必要的性能）
 *  因为已经构造过了，说明剩下的元素中，最大的元素在根节点的左右节点之中；可以直接从上往下调整
 *
 *  时间复杂度分析：
 *  最好、最坏、平均时间复杂度都是 O(n * log n)
 *
 *  空间复杂度：
 *  O(1)
 *
 * @author hongchuzhen
 * @date 1/23/2024
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

        // arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        // System.out.println(Arrays.toString(arr));
        // heapSort1(arr);
        // System.out.println(Arrays.toString(arr));
        // heapSort1(arr);
        // System.out.println(Arrays.toString(arr));
        //
        // arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        // System.out.println(Arrays.toString(arr));
        // heapSort3(arr);
        // System.out.println(Arrays.toString(arr));
        // heapSort3(arr);
        // System.out.println(Arrays.toString(arr));

        arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        System.out.println(Arrays.toString(arr));
        heapSort5(arr);
        System.out.println(Arrays.toString(arr));
        heapSort5(arr);
        System.out.println(Arrays.toString(arr));



    }

    private static void heapSort5(int[] arr) {
        if (arr == null) return;

        for (int i = arr.length / 2 - 1; i >= 0; i--)  {
            heapify5(arr,i,arr.length - 1);;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[i];
            arr[i] = arr[0];
            arr[0] = tmp;

            heapify5(arr,0,i - 1);
        }
    }

    private static void heapify5(int[] arr, int parent, int lastIndex) {
        int largest = parent;
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;

        if (left <= lastIndex && arr[left] > arr[parent]) {
            largest = left;
        }

        if (right <= lastIndex && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != parent) {
            int tmp = arr[largest];
            arr[largest] = arr[parent];
            arr[parent] = tmp;

            heapify(arr,largest,lastIndex);
        }
    }

    private static void heapSort4(int[] arr) {
        if (arr == null) return;

        // 构建大顶堆。从最后一个非叶子节点开始
        for (int i = arr.length / 2; i >= 0; i--) {
            heapify4(arr,i,arr.length - 1);
        }

        // 无序区元素处理。摘一个，调整一次
        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[i];
            arr[i] = arr[0];
            arr[0] = tmp;

            heapify4(arr,0,i);
        }

    }

    private static void heapify4(int[] arr, int parent, int lastIndex) {
        int largest = parent;
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;

        // 父节点与左节点是否需要交换
        if (left <= lastIndex && arr[left] > arr[parent]) {
            largest = left;
        }

        // 父节点与右节点是否需要交换
        if (right <= lastIndex && arr[right] > arr[largest]) {
            largest = right;
        }

        // 如果左节点或者右节点发生了调整，则该节点所在的子树也需要调整
        if (largest != parent) {
            heapify4(arr,largest,parent);
        }

    }

    private static void heapSort3(int[] arr) {
        if (arr == null) return;

        for (int i = arr.length / 2 - 1;i >= 0; i--) {
            heapify3(arr,i,arr.length - 1);
        }

        // 无序区元素
        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            heapify3(arr,0,i - 1);
        }
    }

    private static void heapify3(int[] arr, int parent, int lastIndex) {
        int largest = parent;
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;

        if (left <= lastIndex && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right <= lastIndex && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != parent) {
            int tmp = arr[parent];
            arr[parent] = arr[largest];
            arr[largest] = tmp;

            heapify3(arr,largest,lastIndex);
        }
    }

    private static void heapSort1(int[] arr) {
        if (arr == null) return;

        // 构建最大堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify1(arr,i,arr.length - 1);
        }

        for (int i = 0; i < arr.length;i++) {
            int tmp = arr[0];
            arr[0] = arr[arr.length - 1 -i];
            arr[arr.length - 1 - i] = tmp;

            // 调整最大堆
            heapify1(arr,0,arr.length - 1 - i - 1);
        }
    }

    private static void heapify1(int[] arr, int parent, int lastIndex) {
        int largest = parent;
        int left = parent + 1;
        int right = parent + 2;

        if (left <= lastIndex && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right <= lastIndex && arr[right] > arr[largest]) {
            largest = right;
        }

        // 如果父节点不是最大值，说明父节点与左节点或右节点发生了调整。该节点所在的子树需要调整
        if (largest != parent) {
            int tmp = arr[largest];
            arr[largest] = arr[parent];
            arr[parent] = tmp;

            // 递归处理该节点的子树
            heapify1(arr,largest,lastIndex);
        }
    }

    private static void heapSort(int[] arr) {
        if (arr == null) return ;

        // 先构造一次最大堆。从下往上
        for (int i = arr.length / 2 - 1;i >= 0; i--) {
            heapify(arr,i,arr.length - 1);
        }

        // 无序区元素处理。获取最大值，调整最大堆。因为已经构造过了，说明剩下的元素中，最大的元素在根节点的左右节点之中；可以直接从上往下调整
        for (int i = arr.length - 1; i > 0; i--) {
            // 把根节点的值与无序数组的最后一位交换
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            heapify(arr,0,i - 1);
        }
    }

    /**
     * 调整最大堆，保证该节点
     *
     * @param arr 待排序数组
     * @param parent 需要调整的父节点下标
     * @param n 无序区的终点下标
     */
    private static void heapify(int[] arr, int parent,int n) {
        // 假设父节点就是最大值
        int largest = parent;
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;

        // 如果左节点比父节点大
        if (left <= n && arr[left] > arr[largest]) {
            largest = left;
        }

        // 如果右节点比父节点大
        if (right <= n && arr[right] > arr[largest]) {
            largest = right;
        }

        // 如果
        if (largest != parent) {
            int tmp = arr[parent];
            arr[parent] = arr[largest];
            arr[largest] = tmp;

            heapify(arr,largest,n);
        }
    }

}
