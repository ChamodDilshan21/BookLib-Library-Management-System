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

    public Book(String title, String author, String ISBN, String genre, String availability) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.availability = availability;
    }

    int bookId;
    String title;
    String author;
    String ISBN;
    String genre;
    String availability;
}
