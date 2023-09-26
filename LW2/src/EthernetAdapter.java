public class EthernetAdapter extends Device {

    private int speed;
    private String mac;

    public EthernetAdapter(String manufacturer, float price, String serialNumber,int speed, String mac){
        super(manufacturer,price,serialNumber);
        this.speed = speed;
        this.mac = mac;
    }

    private void setSpeed(int speed){
        this.speed = speed;
    }

    private void setMac(String mac){
        this.mac = mac;
    }

    private int getSpeed(){
        return speed;
    }

    private String getMac(){
        return mac;
    }

    @Override
    public String toString() {
        return "EthernetAdapter{" +
                "manufacturer='" + getManufacturer() + '\'' +
                ", serialNumber='" + getSerialNumber() + '\'' +
                ", price=" + getPrice() +
                ", speed=" + speed +
                ", string='" + mac + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { // не является ли объект-аргумент ссылкой на текущий объект
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) { // не является ли объект-аргумент объектом другого класса
            return false;
        }
        EthernetAdapter ethernetAdapter = (EthernetAdapter) obj;
        return Float.compare(ethernetAdapter.getPrice(), getPrice()) == 0 &&
                getManufacturer().equals(ethernetAdapter.getManufacturer()) &&
                getSerialNumber().equals(ethernetAdapter.getSerialNumber()) &&
                speed == ethernetAdapter.speed &&
                mac.equals(ethernetAdapter.mac);
    }

}
