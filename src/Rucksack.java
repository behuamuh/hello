import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Rucksack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quantity = scanner.nextInt();
        double maxWeigth = scanner.nextInt();
        Item[] items = new Item[quantity];
        for (int i = 0; i < quantity; i++)
            items[i] = new Item(scanner.nextDouble(), scanner.nextDouble());
        System.out.printf("%.3f", maxLoad(maxWeigth, items));
    }
    public static double maxLoad(double max, Item[] items){
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return (int) (o2.price*1000 - o1.price*1000);
            }
        });
        double result = 0;
        double load = 0;
        for (int i = 0; i < items.length; i++) {
            if (load == max)
                break;
            if (items[i].weigth + load <= max){
                load += items[i].weigth;
                result += items[i].value;
                continue;
            }
            else {
                double d = max - load;
                result += d * items[i].price;
                break;
            }
        }
        return Math.ceil(result * 1000) / 1000.0;
    }
}
class Item{
    public double value;
    public double weigth;
    public double price;

    public Item(double value, double weigth) {
        this.value = value;
        this.weigth = weigth;
        price = value / weigth;
    }
}