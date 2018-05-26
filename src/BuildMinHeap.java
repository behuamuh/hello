import java.util.Scanner;

/*Построение кучи
Переставить элементы заданного массива чисел так, чтобы он удовле-
творял свойству мин-кучи.
Вход. Массив чисел A[0...n−1].
Выход.Переставить элементы массива так, чтобы выполнялись неравенства
A[i]≤A[2i+ 1] и A[i]≤A[2i+ 2] для всех i
Вывести кол-во перестановок и индексы переставляемых узлов
*/
public class BuildMinHeap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++){
            arr[i] = scanner.nextInt();
        }
        buildHeap(arr);
    }
    public static void buildHeap(int[] arr){
        if (arr == null || arr.length < 2) {
            System.out.println(0);
            return;
        }
        StringBuilder result = new StringBuilder();
        int[] count = {0};
        for (int i = (arr.length - 1) / 2 ; i >= 0; i--){
            result.append(siftDown(arr, i, count));

        }
        System.out.println(count[0]);
        System.out.println(result.toString());
    }
    public static String siftDown(int[] heap, int i, int[] count){
        if (i >= heap.length)
            throw new IllegalArgumentException();
        StringBuilder result = new StringBuilder();
        int lChild, rChild;
        while ((lChild = 2*i + 1) < heap.length){
            rChild = 2*i + 2;
            int min = lChild;
            if (rChild < heap.length && heap[rChild] < heap[lChild])
                min = rChild;
            if (heap[min] < heap[i]){
                int temp = heap[min];
                heap[min] = heap[i];
                heap[i] = temp;
                result.append(i + " " + min + System.lineSeparator());
                count[0]++;
                i = min;
            }
            else
                break;
        }
        return result.toString();
    }
}
