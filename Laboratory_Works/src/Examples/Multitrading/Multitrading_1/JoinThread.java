package Examples.Multitrading.Multitrading_1;

public class JoinThread extends Thread{
    public JoinThread(String name) {
        super(name);
    }

    public void run() {
        String nameT = getName();
        long timeout = 0;
        System.out.println("Старт потока - " + nameT);
        try {
            switch (nameT) {
                case "First": timeout = 2_000;
                case "Second": timeout = 1_000;
                default: timeout = 0; break;
            }
            Thread.sleep(timeout);
            System.out.println("Завершение потока - " + nameT);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
