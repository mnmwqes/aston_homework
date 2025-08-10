import java.io.*;

// Собственное исключение
class FileProcessingException extends Exception {
    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class FileReadWriteExample {

    // Метод записи в файл
    public static void writeToFile(String filename, String data) throws FileProcessingException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(data);
        } catch (IOException e) {
            throw new FileProcessingException("Ошибка при записи в файл: " + filename, e);
        }
    }

    // Метод чтения из файла
    public static String readFromFile(String filename) throws FileProcessingException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new FileProcessingException("Ошибка при чтении из файла: " + filename, e);
        }
        return content.toString();
    }

    public static void main(String[] args) {
        String filename = "test.txt";
        String data = "Привет, мир!";

        try {
            writeToFile(filename, data);
            System.out.println("Данные записаны в файл.");

            String fileContent = readFromFile(filename);
            System.out.println("Содержимое файла:\n" + fileContent);

        } catch (FileProcessingException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}