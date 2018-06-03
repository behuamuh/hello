import java.util.Scanner;

/*наибольшая последовательнократная подпоследовательность
Дано целое число 1≤n≤10^3 и массив A[1…n] натуральных чисел,
не превосходящих 2⋅10^9. Выведите максимальное 1≤k≤n,
для которого найдётся подпоследовательность 1≤i1<i2<…<ik≤n длины k,
в которой каждый элемент делится на предыдущий (формально: для  всех 1≤j<k, A[ij]|A[ij+1]).*/

public class NVP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scanner.nextInt();
        System.out.println(nPP(arr));
    }
    public static int nPP(int[] arr){
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException();
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            temp[i] = 1;
            for (int j = 0; j <= i - 1; j++){
                if (arr[i] / arr[j] > 0 && arr[i] % arr[j] == 0)
                    if(temp[j] + 1 > temp[i])
                        temp[i] = temp[j] + 1;
            }
        }
        int result = temp[0];
        for (int i = 1; i < temp.length; i++)
            if (result < temp[i])
                result = temp[i];
        return result;
    }
}
