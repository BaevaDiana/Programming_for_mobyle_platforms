package Examples.Others;
// разбор вопросов с собеседования

//// в.10
//public class Test implements Runnable {
//    public void run() {
//        System.out.println("Thread is in Running State");
//    }
//
//    public static void main(String args[]){
//        new Thread(new Test()).start(); // создание нового потока с выведением в консоль
//    }
//}

////в.17
//public class Test implements Runnable {
//    String x, y;
//
//    public void run() {
//        for (int i = 0; i < 10; i++)
//            synchronized (this) {
//                x = "Hello";
//                y = "Java";
//                System.out.println(x + " " + y + " ");
//            }
//    }
//
//    public static void main(String args[]) {
//       Test run = new Test();
//       Thread obj1 = new Thread(run);
//       Thread obj2 = new Thread(run);
//       obj1.start();
//       obj2.start();
//    }
////    Оба потока (obj1 и obj2) будут выполнять метод run().
////    Так как блок synchronized (this) синхронизирован на объекте run, только один поток будет выполнять блок кода внутри synchronized в любой момент времени.
////    В результате будет выведено Hello Java 20 раз
//}

//// в.23
//public class Test implements Runnable {
//    public void run() {
//      //код
//    }
//   ...
//  new Thread(new Test()).start(); // создание и запуск нового потока
//}

//// в.29
public class Test extends Thread {
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("A");
            System.out.println("B");
        }
    }
}

class ThreadDemo extends Thread {
    public void run() {
        for (int i = 0; i < 3; i++) {
                System.out.println("С");
                System.out.println("D");
        }
    }

    public static void main(String args[]){
        Test t1 = new Test();
        ThreadDemo t2 = new ThreadDemo();
        t1.start();
        t2.start();
    }
////    t1.start() запускает поток, который выполняет метод run() в классе Test. Этот поток выводит строки "A" и "B".
////    t2.start() запускает поток, который выполняет метод run() в классе ThreadDemo. Этот поток выводит строки "C" и "D".
////    Оба потока запускаются параллельно и чередуют выполнение в зависимости от решений планировщика потоков.
////    В результате будет вывод в консоль A B C D, но порядок не определен
}




