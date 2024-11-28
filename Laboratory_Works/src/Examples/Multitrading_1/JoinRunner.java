package Examples.Multitrading_1;

public class JoinRunner {
    static {
        System.out.println("Старт потока main");
    }

    public static void main(String[] args) {
        JoinThread t1 = new JoinThread("First");
        JoinThread t2 = new JoinThread("Second");

        //запуск потоков
        t1.start();
        t2.start();
        try {
            t1.join();// поток main остановлен до окончания работы потока t1
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Имя текущего потока: " + Thread.currentThread().getName());
    }
}
