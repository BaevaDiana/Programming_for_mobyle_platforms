package Examples.Multitrading;

//class Test extends Thread {
//    Test() {
//    }
//
//    Test(Runnable r) {
//        super(r);
//    }
//
//    public void run() {
//        System.out.println("Inside Thread");
//    }
//}
//
//class RunnableDemo implements Runnable {
//    public void run() {
//        System.out.println("Inside Runnable");
//    }
//}
//
//class ThreadDemo {
//    public static void main(String args[]) {
//        new Test().start();
//        new Test(new RunnableDemo()).start();
//    }
//}

////измененный класс, чтобы печатались оба сообщения
//class Test extends Thread {
//    private Runnable target; // Сохраняем переданный Runnable
//
//    Test() {
//    }
//
//    Test(Runnable r) {
//        super(r); // Передаём Runnable в базовый класс Thread
//        this.target = r; // Сохраняем ссылку на Runnable
//    }
//
//    @Override
//    public void run() {
//        if (target != null) {
//            target.run(); // Выполняем метод run() переданного Runnable
//        } else {
//            System.out.print("Inside Thread ");
//        }
//    }
//}
//
//class RunnableDemo implements Runnable {
//    public void run() {
//        System.out.print("Inside Runnable ");
//    }
//}
//
//class ThreadDemo {
//    public static void main(String[] args) {
//        new Test().start(); // Выведет: Inside Thread
//        new Test(new RunnableDemo()).start(); // Выведет: Inside Runnable
//    }
//}


public class Test extends Thread {
    Test(){
        System.out.print(" MyThread");
    }

    public void run(){
        System.out.print(" bar");
    }
    public void run(String s) {
        System.out.println(" baz");
    }
}
class ThreadDemo {
    public static void main(String[] args) {
        Thread t = new Test() {
            public void run() {
                System.out.print(" foo");
            }
        };
        t.start();
    }
}

