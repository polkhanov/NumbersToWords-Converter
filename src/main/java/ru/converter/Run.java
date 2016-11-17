package ru.converter;

import java.util.Scanner;

class Run {
    public static void main(String[] args) {


        Converter dict = new Converter();
        System.out.println("Введите число: ");
        Scanner sc = new Scanner(System.in);
        String value = sc.nextLine();
        System.out.println(dict.getFullNumber(value));

    }
}
