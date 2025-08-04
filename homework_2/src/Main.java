import model.Student;
import model.Book;
import utils.DataGenerator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Student> students = DataGenerator.generateStudents(10);

        Optional<Book> result = students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .map(book -> {
                    System.out.println("Optional. Год выпуска: " + book.getYear() + ":");
                    return book;
                })
                .findFirst();

        System.out.println(result.map(b -> "Первая подходящая книга: " + b)
                .orElse("Книга не найдена."));
    }
}