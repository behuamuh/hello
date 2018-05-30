import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*точки и отрезки
﻿

В первой строке задано два целых числа 1≤n≤50000 и 1≤m≤50000 — количество отрезков и точек на прямой,
соответственно. Следующие nn строк содержат по два целых числа aiai и bibi (ai≤bi) — координаты концов отрезков.
Последняя строка содержит mm целых чисел — координаты точек. Все координаты не превышают 10^8 по модулю.
Точка считается принадлежащей отрезку, если она находится внутри него или на границе.
Для каждой точки в порядке появления во вводе выведите, скольким отрезкам она принадлежит.*/
public class PointAndLeg {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] startLegs = new int[n];
        int[] endLegs = new int[n];
        for (int i = 0; i < n; i++) {
            startLegs[i] = scanner.nextInt();
            endLegs[i] = scanner.nextInt();
        }
        startLegs = mergeSort(startLegs, 0, startLegs.length - 1);
        endLegs = mergeSort(endLegs, 0 , endLegs.length - 1);
        for (int i = 0; i < m; i++){
            int point = scanner.nextInt();
            System.out.print(searchFirst(startLegs, point) - searchLast(endLegs, point)+ " ");
        }

    }
/*Алгоритм решения.
* Делаем 2 массива интов начала отрезков и концы. Сортируем их.
* И сразу при считывании точек проверяем колл-во вхождений.
* Это все отрезки, с началом не больше точки и концом не меньше.*/
    public static int[] mergeSort(int[]arr, int left, int rigth){
        if (rigth > left) {
            int middle = (rigth + left) / 2;
            return merge(mergeSort(arr, left, middle ), mergeSort(arr, middle + 1, rigth));
        }
        return new int[]{arr[rigth]};
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0; // indexes
        while (i < arr1.length && j < arr2.length){
            if (arr1[i] > arr2[j]){
                result[k] = arr2[j];
                j++;
            }
            else {
                result[k] = arr1[i];
                i++;
            }
            k++;
        }
        while (i < arr1.length){
            result[k] = arr1[i];
            i++;
            k++;
        }
        while (j < arr2.length){
            result[k] = arr2[j];
            j++;
            k++;
        }
        return result;
    }

    public static int searchFirst(int[] legs, int X){
        int l = -1;
        int r = legs.length;
        while (r > l +1){
            int m = (l + r)/2;
            if (legs[m] <= X)
                l = m;
            else
                r = m;
        }
        return l + 1;
    }
    public static int searchLast(int[] legs, int X){
        int l = -1;
        int r = legs.length ;
        while (r > l + 1){
            int m = (l + r)/2;
            if (legs[m] < X)
                l = m;
            else
                r = m;
        }
        return l + 1;
    }
}
