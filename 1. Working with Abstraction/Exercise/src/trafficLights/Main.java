package trafficLights;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a and b");
        System.out.print("a = ");
        int a = Integer.parseInt(scanner.nextLine());
        System.out.print("b = ");
        int b = Integer.parseInt(scanner.nextLine());

        System.out.print("Command ");
        String command = scanner.nextLine();
        int res = 0;
        if ("+".equals(command)) {
            int result = a + b;
            System.out.println(result);
        }else if ("-".equals(command)){
            int result = a - b;
            System.out.println(result);
        }else if ("*".equals(command)){
            int result = a * b;
            System.out.println(result);
        }else if ("/".equals(command)){
            int result = a / b;
            System.out.println(result);
        }
    }
}
