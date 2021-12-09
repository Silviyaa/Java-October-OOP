package MultipleImplementation;

import birthday.Birthable;
import birthday.Citizen;
import birthday.Identifiable;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Class[] citizenInterfaces = birthday.Citizen.class.getInterfaces();
        if (Arrays.asList(citizenInterfaces).contains(birthday.Birthable.class)
                && Arrays.asList(citizenInterfaces).contains(birthday.Identifiable.class)) {
            Method[] methods = birthday.Birthable.class.getDeclaredMethods();
            methods = birthday.Identifiable.class.getDeclaredMethods();
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            int age = Integer.parseInt(scanner.nextLine());
            String id = scanner.nextLine();
            String birthDate = scanner.nextLine();
            Identifiable identifiable = new birthday.Citizen(name,age,id,birthDate);
            Birthable birthable = new Citizen(name,age,id,birthDate);
            System.out.println(methods.length);
            System.out.println(methods[0].getReturnType().getSimpleName());
            System.out.println(methods.length);
            System.out.println(methods[0].getReturnType().getSimpleName());
        }
    }

}
