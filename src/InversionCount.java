import java.util.Scanner;

/*Первая строка содержит число 1≤n≤10^5, вторая — массив A[1…n],
содержащий натуральные числа, не превосходящие 10^9.
Необходимо посчитать число пар индексов 1≤i<j≤n, для которых A[i]>A[j].
(Такая пара элементов называется инверсией массива.
Количество инверсий в массиве является в некотором смысле его мерой неупорядоченности: например,
в упорядоченном по неубыванию массиве инверсий нет вообще, а в массиве,
упорядоченном по убыванию, инверсию образуют каждые два элемента.)*/

public class InversionCount {
    private static long count = 0L;//Counter
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++){
            arr[i] = scanner.nextInt();
        }
        mergeSort(arr, 0, size - 1);
        System.out.println(count);
    }
    public static int[] mergeSort(int[]arr, int left, int rigth){
        if (rigth > left) {
            int middle = (rigth + left) / 2;
            return merge(mergeSort(arr, left, middle ), mergeSort(arr, middle + 1, rigth));
        }
        return new int[]{arr[rigth]};
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0; // indexes
        while (i < arr1.length && j < arr2.length){
            if (arr1[i] > arr2[j]){
                result[k] = arr2[j];
                j++;
                count+=arr1.length - i;// if ar2[j] < ar1[i] then < ar1[i+1] ... < ar1[ar1.length - 1]
            }
            else {
                result[k] = arr1[i];
                i++;
            }
            k++;
        }
        while (i < arr1.length){
            result[k] = arr1[i];
            i++;
            k++;
        }
        while (j < arr2.length){
            result[k] = arr2[j];
            j++;
            k++;
        }
        return result;
    }
}
