import java.util.Arrays;
import java.util.Scanner;

/*
* Максимум в скользящем окне
Найти максимум в каждом окне размера m данного массива чисел
A[1 . . . n].
Вход. Массив чисел A[1 . . . n] и число 1 ≤ m ≤ n.
Выход. Максимум подмассива A[i . . . i + m − 1] для всех 1 ≤ i ≤ n − m + 1.
Сложность алгоритма порядка n.
На основе своего стека на массивах
*/
public class MaxInGladeWindow {
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
        // реализуем очередь из двух стеков размера окна
        // максимум окна будет макс из максимумов двух стеков
        //стек входа
        MyStack stack1 = new MyStack(size);
        //стек выхода
        MyStack stack2 = new MyStack(size);
        for (int i = 0; i < size ; i++)
            stack1.addElement(arr[i]);
        int pointer = size - 1 ;
        while (pointer < arr.length){
            //максимум окна
            result[pointer - size + 1] = Math.max(stack1.max(), stack2.max());
            //если стек выхода пуст
            if (stack2.isEmpty()){
                //переносим стек входа в стек выхода
                while (!stack1.isEmpty()){
                    stack2.addElement(stack1.deleteElement());
                }
            }
            //сдвигаем окно
            pointer++;
            if (pointer < arr.length){
                stack2.deleteElement();
                stack1.addElement(arr[pointer]);
            }
        }
        return result;
    }
}

class MyStack {
    /*Стек с поддержкой максимума фиксированного размера на основе 2х массивов*/
    private int mSize;
    private int[] stackArray;
    private int[] maxArray;
    private int top;

    public MyStack(int m) {
        this.mSize = m;
        stackArray = new int[mSize];
        maxArray = new int[mSize];
        top = -1;
    }
    public void print(){
        System.out.println(Arrays.toString(stackArray));
        System.out.println(Arrays.toString(maxArray));
    }
    public void addElement(int element) {
        if (!isFull()) {
            int max = isEmpty() ? element : Math.max(max(), element);
            stackArray[++top] = element;
            maxArray[top] = max;
        }
    }

    public int deleteElement() {
        if (!isEmpty())
            return stackArray[top--];
        return  Integer.MIN_VALUE;
    }

    public int readTop() {
        return stackArray[top];
    }
    public int max(){
        if (!isEmpty())
            return maxArray[top];
        return Integer.MIN_VALUE;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == mSize - 1);
    }
}

