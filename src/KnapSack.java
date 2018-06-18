
/*рюкзак
﻿

Первая строка входа содержит целые числа 1≤W≤10^4 и 1≤n≤300 — вместимость рюкзака и число золотых слитков.
Следующая строка содержит n целых чисел 0≤w1,…,wn≤10^5,
задающих веса слитков. Найдите максимальный вес золота, который можно унести в рюкзаке.*/

import java.util.Scanner;

public class KnapSack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W = scanner.nextInt();
        int n = scanner.nextInt();
        int[] weight = new int[n];
        for (int i = 0; i < n; i++)
            weight[i] = scanner.nextInt();
        System.out.println(maxWeight(W,  weight));
    }

    public static int maxWeight(int W, int... arr) {
        int[][] res = new int[W+1][arr.length + 1];
        for (int i = 0; i <= arr.length ; i++)
            res[0][i] = 0;
        for (int j = 0; j <= W; j++)
            res[j][0] = 0;
        for (int i = 1; i <= arr.length; i++){
            for (int j = 1; j <= W ; j++){
                res[j][i] = res[j][i-1];
                if (arr[i - 1] <= j)
                    res[j][i] = Math.max(res[j][i], res[j - arr[i-1]][i-1] + arr[i-1]);
            }
        }
        return res[W][arr.length];
    }
}
