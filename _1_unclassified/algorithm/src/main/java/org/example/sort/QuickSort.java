package org.example.sort;

import com.sun.deploy.panel.ITreeNode;
import com.sun.scenario.effect.impl.sw.java.JSWBrightpassPeer;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.io.CharArrayReader;
import java.util.Arrays;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 快速排序算法
 *
 * 核心思想是，分治的思想。
 * 通过一个基准值，依据他把数组分为左右两个数组；左边的数组数字都比他小，右边的都比他大；通过不断交换数组左右两边的值来找到基准值的下标位置
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
 *  突然觉得快排好厉害。
 *  普通的算法，是在一次遍历中确定一个元素的位置；
 *  但是，快排在一次遍历中，也确立了一个元素的位置（只不过，这个位置在排序没完成的时候是没有意义的，因为他每次获得的不是最大也不是最小，
 *  甚至你不知道他是在数组中的第几，但是在全局中的位置却是确定的)，
 *  而且，还通过对换左右数组元素的方式，减少了左右数组的无序度，让左右数组的排序代价更少，相当于每一轮的排序都在为下一轮的排序做铺垫
 *  启示：
 *  1、目标的实现方式应该尽量追求这样的效果：既不影响当前节点的执行，也能不费什么额外代价地为下一节点的执行作铺垫
 *  2、从全局角度出发，减少那些当前看似有用实则对最后结果没有影响的东西的消耗；
 *
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
        //
        // quickSort1(arr,0,arr.length - 1);
        // System.out.println("quickSort1" + Arrays.toString(arr));
        //
        // quickSort(arr,0,arr.length - 1);
        // System.out.println(Arrays.toString(arr));
        // quickSort(arr,0,arr.length - 1);
        // System.out.println(Arrays.toString(arr));

        // quickSort2(arr,0,arr.length - 1);
        // System.out.println("quickSort3" + Arrays.toString(arr));
        // quickSort2(arr,0,arr.length - 1);
        // System.out.println("quickSort3" + Arrays.toString(arr));

        // quickSort3(arr,0,arr.length - 1);
        // System.out.println("quickSort3" + Arrays.toString(arr));
        // quickSort3(arr,0,arr.length - 1);
        // System.out.println("quickSort3" + Arrays.toString(arr));

        // quickSort4(arr,0,arr.length - 1);
        // System.out.println("quickSort4" + Arrays.toString(arr));
        // quickSort4(arr,0,arr.length - 1);
        // System.out.println("quickSort4" + Arrays.toString(arr));

        // arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        // quickSort5(arr,0,arr.length - 1);
        // System.out.println("quickSort5" + Arrays.toString(arr));
        // quickSort5(arr,0,arr.length - 1);
        // System.out.println("quickSort5" + Arrays.toString(arr));

        arr = new int[] { 23,12,1,3,4,56,43,22,43,11,32,122,12};
        quickSort7(arr,0,arr.length - 1);
        System.out.println("quickSort7" + Arrays.toString(arr));
        quickSort7(arr,0,arr.length - 1);
        System.out.println("quickSort7" + Arrays.toString(arr));



    }

    private static void quickSort7(int[] arr, int left, int right) {
        if (arr == null || left >= right) return;

        int leftP = left;
        int rightP = right;
        while (leftP < rightP) {
            while (rightP >leftP && arr[rightP] >= arr[left]) {
                rightP--;
            }

            while (leftP < rightP && arr[leftP] <= arr[left]) {
                leftP++;
            }

            int tmp = arr[rightP];
            arr[rightP] = arr[leftP];
            arr[leftP] = tmp;
        }

        int tmp = arr[rightP];
        arr[rightP] = arr[left];
        arr[left] = tmp;

        quickSort7(arr,left,rightP - 1);
        quickSort7(arr,rightP + 1,right);

    }

    private static void quickSort6(int[] arr, int left, int right) {
        if (arr == null || left >= right) return;

        int leftP = left;
        int rightP = right;
        while (leftP < rightP) {
            while (rightP > leftP && arr[rightP] >= arr[left]) {
                rightP--;
            }
            while (leftP < rightP && arr[leftP] <= arr[left]) {
                leftP++;
            }

            int tmp = arr[rightP];
            arr[rightP] = arr[leftP];
            arr[leftP] = tmp;
        }
        int tmp = arr[rightP];
        arr[rightP] = arr[left];
        arr[left] = tmp;

        quickSort6(arr,left,rightP - 1);
        quickSort6(arr,rightP + 1, right);
    }

    private static void quickSort5(int[] arr, int left, int right) {
        if (arr == null || left >= right) return;

        int leftP = left + 1;
        int rightP = right;
        while (leftP < rightP) {
            while (rightP > leftP && arr[rightP] >= arr[left]) {
                rightP--;
            }

            while(leftP < rightP && arr[leftP] <= arr[left]) {
                leftP++;
            }

            int tmp = arr[rightP];
            arr[rightP] = arr[leftP];
            arr[leftP] = tmp;
        }
        int tmp = arr[rightP];
        arr[rightP] = arr[left];
        arr[left] = tmp;

        quickSort5(arr,left,rightP - 1);
        quickSort5(arr,rightP + 1,right);
    }

    private static void quickSort4(int[] arr, int left, int right) {
        if (left >= right) return;

        int leftP = left + 1;
        int rightP = right;
        while (leftP < rightP) {
            while (rightP > leftP && arr[rightP] >= arr[left]) {
                rightP--;
            }

            while (leftP < rightP && arr[leftP] <= arr[left]) {
                leftP++;
            }

            int tmp = arr[rightP];
            arr[rightP] = arr[leftP];
            arr[leftP] = tmp;
        }

        int tmp = arr[rightP];
        arr[rightP] = arr[left];
        arr[left] = tmp;


        quickSort3(arr,left,rightP - 1);
        quickSort3(arr,rightP + 1, right);

    }

    /**
     * 突然觉得快排好厉害。
     * 普通的算法，是在一次遍历中确定一个元素的位置；
     * 但是，快排在一次遍历中，也确立了一个元素的位置（只不过，这个位置在排序没完成的时候是没有意义的，因为他每次获得的不是最大也不是最小，
     * 甚至你不知道他是在数组中的第几，但是在全局中的位置却是确定的)，
     * 而且，还通过对换左右数组元素的方式，减少了左右数组的无序度，让左右数组的排序代价更少，相当于每一轮的排序都在为下一轮的排序做铺垫
     *
     * 启示：
     * 1、目标的实现方式应该尽量追求这样的效果：既不影响当前节点的执行，也能不费什么额外代价地为下一节点的执行作铺垫
     * 2、从全局角度出发，减少那些当前看似有用实则对最后结果没有影响的东西的消耗；
     * @param arr
     * @param low
     * @param high
     */
    private static void quickSort3(int[] arr, int low, int high) {
        if (arr == null || low >= high) return;

        int leftP = low;
        int rightP = high;
        while (leftP < rightP) {
            while (leftP < rightP && arr[low] <= arr[rightP]) {
                rightP--;
            }
            while (leftP < rightP && arr[low] >= arr[leftP] ) {
                leftP++;
            }

            int tmp = arr[rightP];
            arr[rightP] = arr[leftP];
            arr[leftP] = tmp;
        }

        int tmp = arr[rightP];
        arr[rightP] = arr[low];
        arr[low] = tmp;

        quickSort3(arr,low,rightP - 1);
        quickSort3(arr,rightP + 1,high);
    }

    private static void quickSort2(int[] arr, int low, int high) {
        if (low >= high) return;

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

        int tmp = arr[low];
        arr[low] = arr[right];
        arr[right] = tmp;

        quickSort2(arr,low,right - 1);
        quickSort2(arr,right + 1,high);
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
