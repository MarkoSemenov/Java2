import java.util.Arrays;

public class Lesson5 {

    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;
    static float [] array1 = new float[SIZE];
    static float [] array2 = new float[SIZE];
    static float [] a1 = new float[HALF];
    static float [] a2 = new float[SIZE];


    public static void main(String[] args)   {

        method1();
        method2();
        System.out.println("\n" + "Массивы равны: " + Arrays.equals(array1, array2));

    }

    public static void method1 (){

        System.out.println("\n" + "Метод 1");
        Arrays.fill(array1, 0, array1.length, 1);
        long a = System.currentTimeMillis();
        System.out.println("Начало: " + a);
        method3(array1, 0, array1.length);
        System.out.println("Конец: " + System.currentTimeMillis());
        System.out.println("Затраченное время: " + (System.currentTimeMillis() - a));

    }


    public static void method2 () {

        System.out.println("\n" + "Метод 2");
        Arrays.fill(array2, 0, array1.length, 1);

        long a = System.currentTimeMillis();
        System.out.println("Начало: " + a);

        System.arraycopy(array2, 0, a1, 0, HALF);
        System.arraycopy(array2, HALF, a2, HALF, HALF);

        Thread thread1 = new Thread(() -> {method3(a1, 0, HALF);}, "Поток1");
        Thread thread2 = new Thread(() -> {method3(a2, HALF, SIZE);}, "Поток2");
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Состояние потока: " + thread1.isAlive() + ". " + thread1.getName() + " завершил работу");
        System.out.println("Состояние потока: " + thread2.isAlive() + ". " + thread2.getName() + " завершил работу");

        System.arraycopy(a1, 0, array2, 0, HALF);
        System.arraycopy(a2, HALF, array2, HALF, HALF);

        System.out.println("Конец: " + System.currentTimeMillis());
        System.out.println("Затраченное время: " + (System.currentTimeMillis() - a));

    }

    public static void method3(float [] ar, int i, int size) {

        System.out.println(Thread.currentThread().getName() + " запущен: " + Thread.currentThread().isAlive());

        for (; i < size; i++) {
            ar[i] = (float) (ar[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

    }

}