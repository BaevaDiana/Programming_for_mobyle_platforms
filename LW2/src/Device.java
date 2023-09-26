public class Device {
    private String manufacturer, serialNumber;
    private float price;

    public Device(String manufacturer, float price, String serialNumber){
        this.manufacturer = manufacturer;
        this.price = price;
        this.serialNumber = serialNumber;
    }

    private void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;

    }

    private void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;

    }

    private void setPrice(float price){
        this.price = price;

    }

    public String getManufacturer(){
        return manufacturer;
    }

    public String getSerialNumber(){
        return serialNumber;
    }

    public float getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return "Device{" +
                "manufacturer='" + manufacturer + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", price=" + price +
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
        Device device = (Device) obj;
        return Float.compare(device.price, price) == 0 &&
                manufacturer.equals(device.manufacturer) &&
                serialNumber.equals(device.serialNumber);
    }

    // Метод "заменить"
    private void replaceDevice(){
        System.out.println(manufacturer + " был заменён.");
    }

    // Метод "распознать"
    private void recognizeDevice(){
        System.out.println(manufacturer + " был распознан.");
    }

    // Метод "установить драйвер"
    private void addDriver(){
        System.out.println("Для " + manufacturer + " был установлен драйвер.");
    }

    // Метод "удалить драйвер"
    private void removeDriver(){
        System.out.println("Для " + manufacturer + " был удалён драйвер.");
    }

}
