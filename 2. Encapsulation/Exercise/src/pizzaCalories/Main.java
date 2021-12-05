package pizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");

        String pizzaName = input[1];
        int numberOfToppings = Integer.parseInt(input[2]);
        Pizza pizza;

        try {
            pizza = new Pizza(pizzaName, numberOfToppings);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        input = scanner.nextLine().split("\\s+");

        String doughType = input[1];
        String doughBakingStyle = input[2];
        double doughWeight = Double.parseDouble(input[3]);

        try {
            Dough dough = new Dough(doughType, doughBakingStyle, doughWeight);
            pizza.setDough(dough);

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());
            return;
        }

        input = scanner.nextLine().split("\\s+");

        while (!input[0].equals("END")) {
            String toppingType = input[1];
            double toppingWeight = Double.parseDouble(input[2]);

            try {
                Topping topping = new Topping(toppingType, toppingWeight);
                pizza.addTopping(topping);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }

            input = scanner.nextLine().split("\\s+");
        }

        System.out.printf("%s - %.2f", pizza.getName(), pizza.getOverallCalories());
    }
}
