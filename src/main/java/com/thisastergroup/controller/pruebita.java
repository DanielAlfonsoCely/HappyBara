package com.thisastergroup.controller;

import java.util.*;
public class pruebita {
    private static String items = "";
    public static void main(String[] args) {
        
        printNumbers("12&32&45");
        addItem("item1");
        addItem("item2");
        addItem("item3");
        addItem("item4");
        String[] itemsArray = items.split("&&");
        ArrayList<String> items = new ArrayList<>(Arrays.asList(itemsArray));
        System.out.println(items);
        for (String item : items) {
            System.out.println(item);
        }
    }


    public static void printNumbers(String input) {
        String[] numbers = input.split("&");
        for (String number : numbers) {
            System.out.println(number);
        }
    }
    public static void addItem(String item) {
        if(items.equals(""))items = item;
        else items = items +"&&" + item;
    }
}
