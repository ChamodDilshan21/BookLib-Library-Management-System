package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Book {

    public Book(String title, String author, String isbn, String genre, String availability) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.availability = availability;
    }

    Integer bookId;
    String title;
    String author;
    String isbn;
    String genre;
    String availability;
}
