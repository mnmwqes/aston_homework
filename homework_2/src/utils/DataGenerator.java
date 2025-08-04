package utils;

import studentsAndBooks.Book;
import studentsAndBooks.Student;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class DataGenerator {
    private static final String[] TITLES = {"Изучаем Java", "Программирование на Python", "Начальный курс: C и C++",
            "Основы SQL", "PHP. Подробное руководство", "Go для начинающих", "Самоучитель Kotlin"};

    public static List<Student> generateStudents(int count) {
        List<Student> students = new ArrayList<>();
        Random rand = new Random();
        for (int i = 1; i < count; i++) {
            List<Book> books = new ArrayList<>();
            for (int j = 1; j < 5; j++) {
                books.add(new Book(
                        "\n" + TITLES[rand.nextInt(TITLES.length)] + ". Издание №" + j,
                        100 + rand.nextInt(400),
                        1995 + rand.nextInt(30)
                ));
            }
            students.add(new Student("№" + i, books));
        }
        return students;
    }
}