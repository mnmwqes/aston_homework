interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Загрузка " + filename);
    }

    @Override
    public void display() {
        System.out.println("Отображение " + filename);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

public class Proxy {
    public static void main(String[] args) {
        Image img = new ProxyImage("photo.jpg");

        System.out.println("Первый вызов:");
        img.display();

        System.out.println("Второй вызов:");
        img.display();
    }
}
