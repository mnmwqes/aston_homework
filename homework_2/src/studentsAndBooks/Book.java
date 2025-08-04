package studentsAndBooks;

import java.util.Objects;
import java.util.StringJoiner;

public class Book {
    private String title;
    private int pages;
    private int year;

    public Book(String title, int pages, int year) {
        this.title = title;
        this.pages = pages;
        this.year = year;
    }

    public int getPages() { return pages; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return title + ", " + year + " г. — " + pages + " стр.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return pages == book.pages && year == book.year && title.equals(book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, pages, year);
    }
}