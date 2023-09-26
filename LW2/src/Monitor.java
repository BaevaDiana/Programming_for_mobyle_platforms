public class Monitor extends Device {

    private int resolutionX, resolutionY;

    public Monitor(String manufacturer, float price, String serialNumber,int resolutionX, int resolutionY){
        super(manufacturer,price,serialNumber);
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
    }

    private void setResolutionX(int resolutionX){
        this.resolutionX = resolutionX;
    }

    private void setResolutionY(int resolutionY){
        this.resolutionY = resolutionY;
    }

    private int getResolutionX(){
        return resolutionX;
    }

    private int getResolutionY(){
        return resolutionY;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "manufacturer='" + getManufacturer() + '\'' +
                ", serialNumber='" + getSerialNumber() + '\'' +
                ", price=" + getPrice() +
                ", resolutionX=" + resolutionX +
                ", resolutionY=" + resolutionY +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) { // не является ли объект-аргумент объектом другого класса
            return false;
        }
        Monitor monitor = (Monitor) obj;
        return Float.compare(monitor.getPrice(), getPrice()) == 0 &&
                getManufacturer().equals(monitor.getManufacturer()) &&
                getSerialNumber().equals(monitor.getSerialNumber()) &&
                resolutionX == monitor.resolutionX &&
                resolutionY == monitor.resolutionY;
    }



}
