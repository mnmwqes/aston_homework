import java.io.*;

class ExceptionFile extends Exception {
    public ExceptionFile(String message, Throwable cause) {
        super(message, cause);
    }
}

public class FileReadWrite {

    public static void writeToFile(String filename, String data) throws ExceptionFile {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(data);
        }
        catch (IOException e) {
            throw new ExceptionFile("Ошибка записи в файл: " + filename, e);
        }
    }

    public static String readFromFile(String filename) throws ExceptionFile {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        catch (IOException e) {
            throw new ExceptionFile("Ошибка чтения из файла: " + filename, e);
        }
        return content.toString();
    }

    public static void main(String[] args) {
        String filename = "test.txt";
        String data = "Привет, мир!";

        try {
            writeToFile(filename, data);
            System.out.println("Записано в файл.");

            String fileContent = readFromFile(filename);
            System.out.println("Содержимое файла:\n" + fileContent);
        }
        catch (ExceptionFile e) {
            System.err.println("Ошибка:\n" + e.getMessage());
            e.printStackTrace();
        }
    }
}
