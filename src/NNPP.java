import java.io.PrintWriter;
import java.util.Scanner;

/*наибольшая невозрастающая подпоследовательность


Дано целое число 1≤n≤105 и массив A[1…n], содержащий неотрицательные целые числа,
не превосходящие 109. Найдите наибольшую невозрастающую подпоследовательность в A.
В первой строке выведите её длину k, во второй — её индексы 1≤i1<i2<…<ik≤n (таким образом,
A[i1]≥A[i2]≥…≥A[in]).*/
public class NNPP {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = scanner.nextInt();
            nNPP(arr);
            //System.out.println(nPP(arr));
        }
        static void nNPP(int[] numbers){
            if (numbers == null || numbers.length < 1)
                throw new IllegalArgumentException();
            int size = numbers.length;
            int[] sequence = new int[size];
            for (int i = 0; i < size; i++)
                sequence[i] = Integer.MIN_VALUE;
            int[] position = new int[size];
            sequence[0] = numbers[0];
            position[0] = 0;
            for (int i = 1; i < size; i++){ // subseq search
                int pos = ceilIndex(sequence, i, numbers[i]);
                sequence[pos] = numbers[i];
                position[i] = pos;
                //System.out.println(pos);
            }
            int len = position[0];
            for (int i = 0; i < position.length; i++)// subseq size
                if (position[i] > len)
                    len = position[i];
            int[] result = new int[len + 1];
            int k = len;
            int key = Integer.MIN_VALUE;
            for (int i = size - 1; i >= 0; i--){ //subseq load
                if (position[i] == k && numbers[i] >= key){
                    result[k] = i + 1;
                    k--;
                    key = numbers[i];
                }
                if (k < 0)
                    break;
            }
            PrintWriter writer = new PrintWriter(System.out);
            writer.println(len + 1);
            for (int i: result  // subseq print
                 ) {
                writer.print(i + " ");
            }
            writer.println();
            writer.flush();
            writer.close();
        }

    private static int ceilIndex(int[] sequence,int size, int key) {
        int l = -1;
        int r = size + 1;
        while ( r > l + 1){
            int m = ( r + l) / 2;
            if (key > sequence[m])
                r = m;
            else
                l = m;
        }
        return r;

    }
}
