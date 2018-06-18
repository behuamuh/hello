import java.util.Scanner;

/*калькулятор


У вас есть примитивный калькулятор, который умеет выполнять всего три операции с текущим числом x:
заменить x на 2x, 3x или x+1. По данному целому числу 1≤n≤10^5 определите минимальное число операций k,
необходимое, чтобы получить n из 1.
Выведите k и последовательность промежуточных чисел.*/
public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        calc(scanner.nextInt());
    }

    public static void calc(int n) {
        int[] a = new int[n+1];
        a[1] = 0;
        for (int i = 2; i <= n; i++){
            int k = i%3==0 && i/3>0 ? i/3 : i-1;
            int j = i%2==0 && i/2>0 ? i/2 : i-1;
            int min = k;
            if (a[min] > a[j]) min = j;
            if (a[min] > a[i-1]) min = i-1;
            a[i] = a[min] + 1;
        }
        System.out.println(a[n]);
        int k = a[n];
        StringBuilder builder = new StringBuilder("" + n);
        while (k > 0){
            k--;
            int x = n%3==0 && n/3>0 ? n/3 : n-1;
            int y = n%2==0 && n/2>0 ? n/2 : n-1;
            int z = n - 1;
            if (a[x] == k) n = x;
            else if (a[y] == k) n = y;
            else n = z;
            builder.insert(0, n + " ");
        }
        System.out.println(builder.toString().trim());
    }

}
