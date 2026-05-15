package com.techlab.ecommerce.util;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validador {
    public static int readInteger(Scanner sc, String message){
        while (true) {
            System.out.print(message);
            try {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número entero. Intentelo nuevamente");
                sc.nextLine();
            }
        }
    }

    public static double readDouble(Scanner sc, String message){
        while (true) {
            System.out.print(message);
            try {
                double value = sc.nextDouble();
                sc.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número entero (o con coma). Intentelo nuevamente");
                sc.nextLine();
            }
        }
    }

    public static String readString(Scanner sc, String message){
        System.out.print(message);
        return sc.nextLine();
    }
}
