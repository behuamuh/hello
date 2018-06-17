import java.util.Arrays;
import java.util.Scanner;

/*наибольшая невозрастающая подпоследовательность


Дано целое число 1≤n≤105 и массив A[1…n], содержащий неотрицательные целые числа,
не превосходящие 109. Найдите наибольшую невозрастающую подпоследовательность в A.
В первой строке выведите её длину k, во второй — её индексы 1≤i1<i2<…<ik≤n (таким образом,
A[i1]≥A[i2]≥…≥A[in]).*/
public class NNVP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scanner.nextInt();
        nPP(arr);
    }
    public static void nPP(int[] arr){
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException();
        int[] temp = new int[arr.length];
        int[] prev = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            temp[i] = 1;
            prev[i] = -1;
            for (int j = 0; j <= i - 1; j++){
                if (arr[i] <= arr[j])
                    if(temp[j] + 1 > temp[i]) {
                        temp[i] = temp[j] + 1;
                        prev[i] = j;
                    }
            }
        }

        int result = 0;
        for (int i = 1; i < temp.length; i++)
            if (temp[result] < temp[i])
                result = i;
        int[] res = new int[temp[result]];
        for (int i = res.length - 1; i >=0; i--) {
            res[i] = result;
            result = prev[result];
        }
        System.out.println(res.length);
        for (int i = 0; i< res.length; i++)
            System.out.print(res[i] + 1 + " ");

    }
}
