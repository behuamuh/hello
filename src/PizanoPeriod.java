import java.util.Scanner;

/*Даны целые числа 1≤n≤10181≤n≤1018 и 2≤m≤1052≤m≤105,
необходимо найти остаток от деления n-го числа Фибоначчи на m.*/

public class PizanoPeriod {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long fiboNumber = scanner.nextLong();
        int modul = scanner.nextInt();
        scanner.close();
        System.out.println(findPizano(fiboNumber, modul));
    }

    private static long findPizano(long n, int m) {
        if ((n < 1L) || (n > (long) 1e18)
                || (m < 2) || (m > 100000))
            throw new IllegalArgumentException();

        if (n == 1L)
            return 1L;
        //max period of pizano number <= 6*m
        int pizanoPeriod = 6 * m;
        int[] pizanos = new int[pizanoPeriod];
        pizanos[0] = 0;
        pizanos[1] = 1;
        for (int i = 2; i < pizanos.length; i++){
            pizanos[i] = (pizanos[i-1] + pizanos[i-2]) % m;
            if ((pizanos[i - 1] == 0) && (pizanos[i] == 1)) {
                // new period beginning
                pizanoPeriod = i - 1;
                break;
            }
        }

        return pizanos[(int) (n % pizanoPeriod)];
    }

}
