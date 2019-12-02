package com.wxy.sort;

/**
 * <p>排序</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/27 19:22
 */
public class Sort {
    public static void main(String[] args) {
        int[] arr = {15, 36, 25, 4, 9, 5, 16, 74, 39, 86, 22, 24, 18, 7, 64};
        // bubbleSort0(arr);
        // bubbleSort1(arr);
        // bubbleSort2(arr);
        // selectSort(arr);
        // insertSort(arr);
        // shellSort(arr);

        // 堆排序
        // int[] haepArr = {0, 15, 36, 25, 4, 9, 5, 16, 74, 39, 86, 22, 24, 18, 7, 64};
        // heapSort(haepArr);
        // print(haepArr);

        // mergeSort(arr, 0, arr.length - 1);
        quickSort(arr, 0, arr.length - 1);
        print(arr);
    }

    /**
     * 快速排序
     * @param arr 数组
     * @param low 起始下标
     * @param high 截止下标
     */
    private static void quickSort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int index = partition(arr, low, high);
        quickSort(arr, low, index-1);
        quickSort(arr, index+1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        int temp = arr[low];
        while (low < high) {
            while (temp <= arr[high] && low < high) {
                high--;
            }
            arr[low] = arr[high];
            while (temp >= arr[low] && low < high) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[high] = temp;
        return high;
    }


    /**
     *  归并排序
     * @param m 原数组
     * @param i 起始下标
     * @param l 终止下标
     */
    private static void mergeSort(int[] m, int i, int l) {
        if (i < l) {
            int n = (i+l)/2;
            mergeSort(m, i, n);
            mergeSort(m, n+1, l);
            merge(m, i, n, l);
        }
    }

    /**
     * 合并
     * @param m 原数组
     * @param low 起点下标
     * @param mid 中间下标
     * @param high 终止下标
     */
    private static void merge(int[] m, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <=high) {
            if (m[i] < m[j]) {
                temp[k++] = m[i++];
            } else {
                temp[k++] = m[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = m[i++];
        }

        while (j <= high) {
            temp[k++] = m[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            m[low+x] = temp[x];
        }
    }

    /**
     * 堆排序
     * @param arr
     */
    private static void heapSort(int[] arr) {
        for (int i = (arr.length-1)/2; i > 0; i--) {
            heapAdjust(arr, i, arr.length-1);
        }
        for (int i = arr.length; i > 1; i--) {
            int temp = arr[i-1];
            arr[i-1] = arr[1];
            arr[1] = temp;
            heapAdjust(arr, 1, i - 2);
        }
    }

    /**
     * 堆调整
     * @param arr 待调整的堆数组
     * @param s 下标
     * @param m 长度
     */
    private static void heapAdjust(int[] arr, int s, int m) {
        int temp = arr[s];
        for (int j = 2*s; j <= m; j *= 2) {
            if (j < m && arr[j] < arr[j+1]) {
                j++;
            }
            if (temp >= arr[j]) break;
            arr[s] = arr[j];
            s = j;
        }
        arr[s] = temp;
    }

    /**
     * 希尔排序
     * @param arr
     */
    private static void shellSort(int[] arr) {
        int increment = arr.length;
        do {
            increment = increment/3 + 1;
            for (int i = increment; i < arr.length; i++) {
                if (arr[i] < arr[i-increment]) {
                    int temp = arr[i];
                    int j;
                    for (j = i - increment; j >= 0 && arr[j] > temp; j -= increment) {
                        arr[j + increment] = arr[j];
                    }
                    arr[j+increment] = temp;
                }
            }
        } while (increment > 1);
    }

    /**
     * 插入排序
     * @param arr
     */
    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i-1]) {
                int temp = arr[i];
                int j;
                for (j = i - 1; j >=0 && arr[j] > temp; j--) {
                    arr[j+1] = arr[j];
                }
                arr[j+1] = temp;
            }
        }
    }

    /**
     * 简单选择排序
     * @param arr
     */
    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }

    /**
     * 优化的冒泡排序
     * @param arr
     */
    private static void bubbleSort2(int[] arr) {
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            flag = false;
            for (int j = arr.length-1; j > i; j--) {
                if (arr[j-1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    flag = true;
                }
            }
        }
    }

    /**
     * 真正的冒泡排序
     * @param arr
     */
    private static void bubbleSort1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length-1; j > i; j--) {
                if (arr[j-1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

    /**
     * 简单冒泡排序(不是真正的冒泡排序，属于比较交换排序)
     * @param arr
     */
    private static void bubbleSort0(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private static void print(int[] arr) {
        for (int n : arr) {
            System.out.print(n + " ");
        }
    }
}
