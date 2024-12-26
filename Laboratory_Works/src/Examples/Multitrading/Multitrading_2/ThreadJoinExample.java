package Examples.Multitrading.Multitrading_2;

public class ThreadJoinExample {
    public static void main(String[] args) {
        // создание потоков
        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        Thread t3 = new Thread(new MyRunnable(), "t3");

        t1.start();
        try {
            t1.join(2000); // главный поток main ждёт 2 секунды
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        /** запускаем второй поток только после 2-секундного ожидания первого потока
         * или когда он умрет/закончит выполнение */
        t2.start();
        try {
            t1.join(); // главный поток main ждёт завершения выполнения t1
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /** запускаем 3-й поток только после того,
         * как 1-й закончит своё выполнение */
        t3.start();

        /** даём всем потокам возможность закончить выполнение
         * перед тем как главный поток (программа) завершит свое выполнение*/
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Все потоки отработали, завершаем работу");
    }
}