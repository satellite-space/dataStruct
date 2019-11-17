package com.wxy.search.listsearch;

/**
 * <p>
 *     线性表查找
 *         1. 顺序表查找
 *         2. 有序表查找
 *            <1> 折半查找(二分法查找)
 *            <2> 插值查找
 *            <3> 婓波那契查找
 * </p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/17 17:24
 */
public class ListSearch {

    private static int[] f;

    /**
     *  顺序表查找
     * @param arr 待查找的数组
     * @param key 关键字
     * @return 在数组中的下标
     */
    public static int searchOfSequential(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (key == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     *  顺序表查找优化,此方法需要数组拥有头结点
     * @param arr 待查找的数组
     * @param key 关键字
     * @return 在数组中的下标
     */
    public static int searchOfSequential2(int[] arr, int key) {
        int i = arr.length;
        arr[0] = key;
        while (key != arr[i]) {
            i--;
        }
        return (i < 0) ? -1 : i;
    }

    /**
     *  折半查找关键字
     * @param arr 待查找的数组
     * @param key 关键字
     * @return 在数组中的下标
     */
    public static int searchOfBinary(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     *  插值查找关键字
     * @param arr 待查找的数组
     * @param key 关键字
     * @return 在数组中的下标
     */
    public static int searchOfInterpolation(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (key - arr[low]) / (arr[high] - arr[low]);
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     *  插值查找关键字
     * @param arr 待查找的数组
     * @param key 关键字
     * @return 在数组中的下标
     */
    public static int searchOfFibonacci(int[] arr, int key) {
        initFibonacci();
        int low = 0;
        int high = arr.length - 1;
        int k = 0;

        while (arr.length > f[k]) {
            k++;
        }
        for (int i = arr.length - 1; i < f[k] - 1; i++) {
            arr[i] = arr[arr.length - 1];
        }

        while (low <= high) {
            int mid = low + f[k-1] - 1;
            if (key < arr[mid]) {
                high = mid - 1;
                k--;
            } else if (key > arr[mid]) {
                low = mid + 1;
                k = k - 2;
            } else {
                return  (mid <= arr.length - 1) ? mid : arr.length - 1;
            }
        }
        return -1;
    }

    private static void initFibonacci() {
        int a = 1;
        int b = 1;
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < 10; i++) {
            int c = a + b;
            f[i] = c;
            a = b;
            b = c;
        }
    }
}
