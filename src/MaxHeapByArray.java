import java.util.Scanner;

/*очередь с приоритетами


Первая строка входа содержит число операций 1≤n≤10^5.
Каждая из последующих nn строк задают операцию одного из следующих двух типов:

Insert xx, где 0≤x10^9 — целое число;
ExtractMax.
Первая операция добавляет число xx в очередь с приоритетами, вторая — извлекает максимальное число и выводит его.*/

public class MaxHeapByArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quantity = scanner.nextInt();
        MaxHeap heap = new MaxHeap(quantity);
        for (int i = 0; i < quantity ; i++){
            String oper = scanner.next();
            if ("Insert".equals(oper))
                heap.insert(scanner.nextInt());
            else if ("ExtractMax".equals(oper))
                heap.extract();
            else {
                System.out.println("Unknow command!");
                return;
            }

        }

    }
}
class MaxHeap{
    private final int maxSize;
    private final int[] heap;
    private int size;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        heap = new int[maxSize];
        size = 0;
    }
    public void insert(int num){
        if(size >= maxSize ){
            System.out.println("Heap is FULL!");
            return;
        }
        heap[size] = num;
        int point = size;
        while (point > 0 ){
            int parent = (point -1) / 2;
            if (heap[point] > heap[parent]) {
                heap[point] = heap[parent];
                heap[parent] = num;
                point = parent;
            }
            else
                break;
        }
        size++;
    }
    public void extract(){
        if (size < 1){
            System.out.println("Heap is EMPTY!");
            return;
        }
        System.out.println(heap[0]);
        int num = heap[size - 1];
        size--;
        int point = 0;
        heap[point] = num;
        int child;
        while ((child = 2*point +1) < size){
            if (child + 1 < size )
                child = heap[child] > heap[child + 1] ? child : child + 1;
            if (heap[point] < heap[child]){
                heap[point] = heap[child];
                heap[child] = num;
                point = child;
            }
            else
                break;
        }
    }

}
