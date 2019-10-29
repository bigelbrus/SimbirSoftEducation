package com.example.simbirsoftapp;

import android.util.Log;

import java.util.Arrays;

public class SecondTask {
    private static final String TAG = SecondTask.class.getSimpleName();

    //Метод для вывода ответов по задачам

    public static void answers(){
        averageOfTwo();                                         //1-я задача
        NameToConsole.printFullName();                          //2-я
        fibonacciSeq();                                         //3-я
        sortBubble(new int[]{1,3,6,1,2,16,9,0,14,167,4,122});   //4-я
        incNumInLine("abv12999");                               //5-я
        incNumInLine("abv");                                    //5-я задача с неверным аргументом
    }

        //    1. Определить две константы a и b типа Double, присвоить им любые значения.
        //    Вычислить среднее значение и сохранить результат в переменную average.

    private static void averageOfTwo() {

        final Double a = 13456d;
        final Double b = Double.MAX_VALUE;
        Double average = (a / 2) + (b / 2);
        Log.d(TAG, "Average of two " + average.toString());
    }

    //    2. Создать класс, и задать два любых строковых значения с названиями firstName и lastName.
    //    Далее необходимо вывести в консоль строку в формате "Full name: [firstName] [lastName]".

    private static class NameToConsole {
        static private final String firstName = "Arnold";
        static private final String lastName = "Stallone";

        static public void printFullName() {
            StringBuilder fullName = new StringBuilder("Full name: ")
                    .append(firstName)
                    .append(" ")
                    .append(lastName);
            System.out.println(fullName);
            Log.d(TAG,"Print full name " + fullName);
        }
    }

    //    3. Напишите программу для вывода первых 15 чисел последовательности Фибоначчи

    private static void fibonacciSeq() {
        StringBuilder answer = new StringBuilder();
        int length = 15;
        int first = 0;
        int second = 1;
        answer.append(first + " " + second + " ");
        for (int i = 0; i < length; i++) {
            int temp = second;
            second = first + second;
            first = temp;
            answer.append(second + " ");
        }
        Log.d(TAG,"First 15 Fibonacci numbers " + answer.toString());
    }

    //    4. Напишите программу для сортировки массива, использующую метод пузырька.
    //    Сотрировка должна происходить в отдельной функции, принимающей на вход исходный массив.

    private static void sortBubble(int[] arr) {
        Log.d(TAG,"Bubble sort. Enter array: " +  Arrays.toString(arr));

        int length = arr.length;
        boolean flag = true;

        while (flag) {
            flag = false;
            for (int j = 0; j < length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
        }
        Log.d(TAG,"Final array: " +  Arrays.toString(arr));
    }

    //    5. Напишите программу, решающую задачу: есть входящая строка формата "abc123", где сначала идет любая последовательность букв, потом число.
    //    Необходимо получить новую строку, в конце которой будет число на единицу больше предыдущего, то есть "abc124".

    private static void incNumInLine(String line) {
        int lineNumStart = 0;                                           //Позиция в линии начала последовательности чисел
        int number = 0;
        final int ASCII_NINE = 59;                                      //Код в таблице ASCII для цифры 9
        StringBuilder chars;

        for (int i = 0; i < line.length(); i++) {                       //Определяем начало последовательности чисел
            if (line.charAt(i) <= ASCII_NINE) {
                lineNumStart = i;
                break;
            }
        }
        chars = new StringBuilder(line.substring(0, lineNumStart));             //Выделяем последовательность символов
        try {
            number = Integer.parseInt(line.substring(lineNumStart));
            chars.append(++number);                                             //Добавляем увеличенную последовательность чисел
            Log.d(TAG,"Increment number in line. Enter line: " + line +
                    ". Answer: "+ chars.toString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid line entered." +
                    " Must be formated like abc123\n" + e.getMessage());        //Обработка на случай не верного ввода аргумента
            Log.d(TAG,"Increment number in line. Enter line: " + line +
                    ". Problem: "+ e.toString());
        }
    }
}


