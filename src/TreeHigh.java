import java.util.Scanner;

public class TreeHigh {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] parents = new int[size];
        for (int i = 0; i < size; i++)
            parents[i] = scanner.nextInt();
        int maxHigh = 0;
        for (int i=0 ; i< size; i++) {
            maxHigh = Math.max(maxHigh, high(i, parents));
        }
        System.out.println(maxHigh);
    }
    private static int high(int i, int[] par){
        if (i == -1)
            return 0;
        return high(par[i], par) +1;
    }
}
