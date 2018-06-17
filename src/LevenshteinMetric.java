/*расстояние редактирования


Вычислите расстояние редактирования двух данных непустых строк длины не более 100,
содержащих строчные буквы латинского алфавита.*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LevenshteinMetric {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(check(reader.readLine(), reader.readLine()));
    }

    public static int check(String in, String out) {
        int n = in.length()+1;
        int m = out.length()+1;
        int[][] arr = new int[m][n];
        for (int i = 0; i < n ; i++)
            arr[0][i] = i;
        for (int j = 0; j < m ; j++)
            arr[j][0] = j;
        for (int j = 1; j < m; j++){
            for (int i = 1; i < n; i++){
                int diff = in.charAt(i-1) == out.charAt(j-1) ? 0 : 1;
                arr[j][i] = Math.min(arr[j-1][i-1]+diff,
                        Math.min(arr[j][i-1]+1,arr[j-1][i]+1));
            }
        }
        return arr[m-1][n-1];
    }
}
