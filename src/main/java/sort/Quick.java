package sort;

import java.util.Arrays;

public class Quick {

  public static void main(String[] args) {
    //入口
      int[] arr = new int[]{2,1,8,6,5,3};
      qsort(arr, 0, arr.length-1);
    System.out.println(Arrays.toString(arr));
  }
    //排序函数
    public static void qsort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = partition(arr, left, right);
            System.out.println("数组为："+Arrays.toString(arr));
            System.out.println("mid为：" + mid);
            //分治+递归思维
            qsort(arr, left, mid-1);
            qsort(arr, mid+1, right);
        }
    }

    //核心分区函数
    private static int partition(int[] arr, int left, int right) {
      //选最右作为基准
//        int pivot = arr[right];
//        int i = left - 1;
//        for(int j = left; j<right; j++) {
//            if (arr[j] < pivot) {
        //核心是把小的往前挪
//                swap(arr, j, ++i);
//            }
//        }
//        swap(arr, i+1, right);
//      return 1+i;

        //选最左作为基准
        int pivot = arr[left];
        //从起始右边第一位开始比较
        int i = left+1;
        for(int j = left; j<=right; j++) {
            if (arr[j] < pivot) {
                swap(arr, j, i++);
            }
        }
        swap(arr, i-1, left);
        return i-1;
    }

    private static void swap(int[] arr, int i, int j) {
      //交换位置
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
