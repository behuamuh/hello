import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
/*
* Максимум в скользящем окне
Найти максимум в каждом окне размера m данного массива чисел
A[1 . . . n].
Вход. Массив чисел A[1 . . . n] и число 1 ≤ m ≤ n.
Выход. Максимум подмассива A[i . . . i + m − 1] для всех 1 ≤ i ≤ n − m + 1.
Сложность алгоритма порядка n.
На основе стандартного стека
*/
public class MaxInGladeWindow2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            arr[i] = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println(Arrays.toString(maxInWindow(arr, m))
                .replace("[", "")
                .replace("]", "")
                .replace(",", ""));
    }
    private static int[] maxInWindow(int[] arr, int size){
        if (arr == null || size > arr.length || size == 0)
            throw new IllegalArgumentException();
        int[] result = new int[arr.length - size + 1];
        Stack<Integer> input = new Stack();
        int maxInput = Integer.MIN_VALUE;
        Stack<Integer> output = new Stack();
        Stack<Integer> maxOut = new Stack<>();
        for (int i = 0; i < size ; i++) {
            input.push(arr[i]);
            maxInput = Math.max(maxInput, arr[i]);
        }
        int pointer = size - 1 ;
        while (pointer < arr.length){
            //максимум окна
            result[pointer - size + 1] = Math.max(maxOut.empty() ? Integer.MIN_VALUE : maxOut.peek(), maxInput);
            //если стек выхода пуст
            if (output.empty()){
                //переносим стек входа в стек выхода
                while (!input.empty()){
                    int max = maxOut.empty() ? input.peek() : Math.max(input.peek(), maxOut.peek());
                    output.push(input.pop());
                    maxOut.push(max);
                    maxInput = Integer.MIN_VALUE;
                }
            }
            //сдвигаем окно
            pointer++;
            if (pointer < arr.length){
                output.pop();
                maxOut.pop();
                input.push(arr[pointer]);
                maxInput = Math.max(maxInput, arr[pointer]);
            }
        }
        return result;
    }
}
