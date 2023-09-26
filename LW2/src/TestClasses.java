public class TestClasses {
    public static void main(String[] args) {

        Device[] devices = new Device[6];

        devices[0] = new Monitor("Dell",100,"26-02-20-23-17-39",1920,1080);
        devices[1] = new EthernetAdapter("Cisco", 1000, "00-11-22-33-44-55",150,"00:26:57:00:1f:02");
        devices[2] = new Monitor("Samsung", 200, "24-03-20-03-08-00",800,600);
        devices[3] = new EthernetAdapter("Realtek", 100, "AA-BB-CC-DD-EE-FF",2000,"94-08-53-6B-71-49");
        devices[4] = new Device("Samsung",50000,"RF8K21ALV5E");
        devices[5] = new Device("Xiaomi",11000,"A1B2C3D4F5");

        System.out.println(devices[0].toString() + "\n");
        System.out.println(devices[2].toString()+ "\n");
        System.out.println(devices[5].toString()+ "\n");


        if(devices[1].equals(devices[3])){
            System.out.println("Device1 is equals Device3, both EthernetAdapter");
        }
        else System.out.println("Device1 is not equals Device3");

        if (devices[0].equals(devices[2])){
            System.out.println("Device0 is equals Device2, both Monitor");
        }
        else System.out.println("Device0 is not equals Device2");

        if (devices[4].equals(devices[5])){
            System.out.println("Device4 is equals Device5");
        }
        else System.out.println("Device4 is not equals Device2");


//        for (Device device : devices) {
//            System.out.println(device);
//        }
    }
}
