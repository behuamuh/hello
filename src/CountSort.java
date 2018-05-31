import java.util.Scanner;

/*сортировка подсчётом


Первая строка содержит число 1≤n≤10^4, вторая — nn натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.*/
public class CountSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scanner.nextInt();
        int[] sortArr = countSort(arr);
        for (int i = 0; i < sortArr.length; i++)
            System.out.print(sortArr[i] + " ");
        System.out.println();
    }
/*Стабильная сортировка подсчетом*/
    private static int[] countSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return arr;
        int[] count = new int[11];
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            count[arr[i]]++;
        for (int i = 1; i < count.length; i++)
            count[i] = count[i] + count[i - 1];
        for (int j = arr.length - 1; j >= 0; j--){
            result[count[arr[j]] -1] = arr[j];
            count[arr[j]]--;
        }
        return result;
    }
}
