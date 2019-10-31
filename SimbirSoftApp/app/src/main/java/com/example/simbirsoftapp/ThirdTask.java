package com.example.simbirsoftapp;

import android.util.Log;

public class ThirdTask {

    private static final String LOG = ThirdTask.class.getSimpleName();

    //Метод для вывода ответов по задачам

    public static void answers(){
        ThirdTask task = new ThirdTask();
        task.lovePrint();                               //Печатаем i love java 10 раз
        task.moveManySteps();                           //Двигаемся по полю

        Shape[] figures = {new Square(5d),       //Создаем три фигуры и выводим их площади и периметры
        new Rectangle(2.3d,5.9d),
        new Circle(2d)};

        for (Shape s :figures) {
            String msg = "\nFigure type: " + s.getClass().getSimpleName() + "; \tPerimeter " + s.perimeter()
                    + "; \tArea " + s.area();
            printToConsoleAndLog(msg);
        }
    }

    //    2. Написать простое лямба-выражение в переменной myClosure, лямба-выражение должно выводить в консоль фразу "I love Java".
    //  Вызвать это лямба-выражение. Далее написать функцию, которая будет запускать заданное лямба-выражение заданное количество раз.
    //  Объявить функцию так: public void repeatTask (int times, Runnable task). Функция должна запускать times раз лямба-выражение task .
    //    Используйте эту функцию для печати «I love Java 10 раз.
    interface Printable {
        void print();
    }

    public   void repeatTask(int times, Runnable task) {
        for (int i = 0; i < times; i++) {
            task.run();
        }
    }

    public void lovePrint() {
        Printable myClosure = () -> {
            printToConsoleAndLog("I love Java");
        };
        myClosure.print();

        repeatTask(10,()->{
            myClosure.print();
        });
    }

    //  3. Условия: есть начальная позиция на двумерной плоскости, можно осуществлять
    //  последовательность шагов по четырем направлениям up, down, left, right.
    //  Размерность каждого шага равна 1.

    //  Задание:
    //        Создать enum Directions с возможными направлениями движения

    //        Создать метод, принимающий координаты и одно из направлений и
    //        возвращающий координату после перехода по направлению

    //        Создать метод, осуществлящий несколько переходов от первоначальной
    //        координаты и выводящий координату после каждого перехода.
    //        Для этого внутри метода следует определить переменную location с
    //        начальными координатами (0,0) и массив направлений, содержащий элементы
    //        [up, up, left, down, left, down, down, right, right, down, right],
    //        и програмно вычислить какие будут координаты у переменной location после
    //        выполнения этой последовательности шагов. Для вычисленеия результата каждого
    //        перемещения следует использовать созданный ранее метод перемещения на один шаг.


    enum Directions {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    class Coordinates {
        private int x;
        private int y;

        Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void incX() {
            x++;
        }

        public void decX() {
            x--;
        }

        public void incY() {
            y++;
        }

        public void decY() {
            y--;
        }

        void getCoordinates() {
            printToConsoleAndLog("x: " + x + "; y:" + y);
        }
    }

    public static void moveOneStep(Coordinates start, Directions direction) {
        switch (direction) {
            case UP:
                start.incY();
                break;
            case DOWN:
                start.decY();
                break;
            case LEFT:
                start.decX();
                break;
            case RIGHT:
                start.incX();
                break;
            default:
                System.out.println("Enter right direction");
                break;
        }
    }

    public void moveManySteps() {
        Coordinates location = new Coordinates(0, 0);
        Directions[] directions = {Directions.UP, Directions.UP, Directions.LEFT,
                Directions.DOWN, Directions.LEFT, Directions.DOWN, Directions.DOWN,
                Directions.RIGHT, Directions.RIGHT, Directions.DOWN, Directions.RIGHT};
        for (Directions d : directions) {
            moveOneStep(location, d);
            printToConsoleAndLog("You go " + d + ".\tYour coordinates ");
            location.getCoordinates();
        }
    }
//        Создать интерфейс Shape с двумя методами perimeter и area, выводящими
//        периметр и площадь фигуры соответственно, после чего реализовать и
//        использовать для вывода периметра и площади следующие классы, реализующие
//        интерфейс Shape:
//        Rectangle - прямоугольник с двумя свойствами: ширина и длина
//        Square - квадрат с одним свойством: длина стороны
//        Circle - круг с одним свойством: диаметр круга

    interface Shape {
        double perimeter();
        double area();
    }

    static class Rectangle implements Shape {
        private double width;
        private double height;

        Rectangle(double width, double height) {
            this.height = height;
            this.width = width;
        }

        @Override
        public double perimeter() {
            return (2 * (width + height));
        }

        @Override
        public double area() {
            return (width * height);
        }
    }

    static class Square implements Shape {
        private double length;

        Square(double length) {
            this.length = length;
        }

        @Override
        public double perimeter() {
            return (4 * length);
        }

        @Override
        public double area() {
            return (Math.pow(length, 2d));
        }
    }

    static class Circle implements Shape {
        private double diam;

        Circle(double diam) {
            this.diam = diam;
        }

        @Override
        public double perimeter() {
            return (Math.PI * diam);
        }

        @Override
        public double area() {
            return (Math.PI * Math.pow(diam, 2d) * 0.25d);
        }
    }

    public static void printToConsoleAndLog(String msg) {
        System.out.println(msg);
        Log.d(LOG,msg);
    }

}
