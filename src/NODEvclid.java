import java.util.Scanner;

/*По данным двум числам 1≤a,b≤ 2*10^9 найдите их наибольший общий делитель.
Поиск с помощью алгоритма Евклида
 */
public class NODEvclid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        if ( a < 0 || b < 0 )
            throw new IllegalArgumentException();
        System.out.println(evclidNOD(a, b));
    }
    private static long evclidNOD(long a, long b){
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        return a > b ? evclidNOD(a % b, b) : evclidNOD(b % a, a);
    }
}
