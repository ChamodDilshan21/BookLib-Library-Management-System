package controller.book;

import model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookServices {
    boolean addBook(Book book) throws SQLException;

    boolean updateBook(Book book, int BookId) throws SQLException;

    Book searchBookById(int bookId) throws SQLException ;

    List<Book> searchBookByTitle(String title) throws SQLException;

    List<Book> searchBookByAuthor(String author) throws SQLException;

    List<Book> searchBookByGenre(String genre) throws SQLException;

    List<Book> searchBookByAvailability(String availability) throws SQLException;

    List<Book> getAllBooks() throws SQLException;

    boolean deleteBook(String bookId) throws SQLException;
}
