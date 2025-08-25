interface TypeC {
    void connectWithTypeC();
}

class USBDevice {
    public void connectWithUSB() {
        System.out.println("Подключено через USB");
    }
}

class USBtoTypeCAdapter implements TypeC {
    private USBDevice usbDevice;

    public USBtoTypeCAdapter(USBDevice usbDevice) {
        this.usbDevice = usbDevice;
    }

    @Override
    public void connectWithTypeC() {
        System.out.print("Через адаптер: ");
        usbDevice.connectWithUSB();
    }
}

class Laptop {
    public void plugIn(TypeC device) {
        device.connectWithTypeC();
    }
}

public class Adapter {
    public static void main(String[] args) {
        Laptop laptop = new Laptop();
        USBDevice mouse = new USBDevice();

        TypeC adapter = new USBtoTypeCAdapter(mouse);

        laptop.plugIn(adapter);
    }
}