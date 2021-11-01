package greedyTimes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long capacity = Long.parseLong(scanner.nextLine());
        String[] input = scanner.nextLine().split("\\s+");

        //   var bag = new LinkedHashMap<String, LinkedHashMap<String, Long>>();

        Bag bag = new Bag(capacity); // създаваме нова чанта

        for (int i = 0; i < input.length; i += 2) {
            String item = input[i];
            long quantity = Long.parseLong(input[i + 1]);

            if (item.length() == 3) {
                //добавяме парите в торбата
                bag.addCash(item, quantity);
            } else if (item.toLowerCase().endsWith("gem")) {
                //добавяме скъп. камъни в торбата
                bag.addGems(item, quantity);
            } else if (item.toLowerCase().equals("gold")) {
                // добавяме златото
                bag.addGold(quantity);
            }


        }
        System.out.println(bag);
    }

}