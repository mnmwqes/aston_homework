package utils;

import model.Book;
import model.Student;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class DataGenerator {
    private static final String[] TITLES = {"Java", "C++", "Python", "Kotlin", "Scala", "Go", "Rust"};

    public static List<Student> generateStudents(int count) {
        List<Student> students = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            List<Book> books = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                books.add(new Book(
                        TITLES[rand.nextInt(TITLES.length)] + " Book " + j,
                        100 + rand.nextInt(400),
                        1995 + rand.nextInt(30)
                ));
            }
            students.add(new Student("Student_" + i, books));
        }
        return students;
    }
}