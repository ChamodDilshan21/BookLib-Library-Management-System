package controller.book;

import db.DBConnection;
import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookController implements BookServices {

    @Override
    public boolean addBook(Book book) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book (title,author,ISBN,genre,availability) VALUES (?,?,?,?,?);");
        preparedStatement.setObject(1, book.getTitle());
        preparedStatement.setObject(2, book.getAuthor());
        preparedStatement.setObject(3, book.getISBN());
        preparedStatement.setObject(4, book.getGenre());
        preparedStatement.setObject(5, book.getAvailability());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean updateBook(Book book, int BookId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET title=?,author=?,ISBN=?,genre=?,availability=? WHERE bookId=?;");
        preparedStatement.setObject(1, book.getTitle());
        preparedStatement.setObject(2, book.getAuthor());
        preparedStatement.setObject(3, book.getISBN());
        preparedStatement.setObject(4, book.getGenre());
        preparedStatement.setObject(5, book.getAvailability());
        preparedStatement.setObject(6, BookId);
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public Book searchBookById(int bookId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM book WHERE bookId=" + bookId + ";");
        if (resultSet.next()) {
            return new Book(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }

    @Override
    public List<Book> searchBookByTitle(String title) throws SQLException {
        List<Book> bookList = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM book WHERE title LIKE '%" + title + "%';");
        while (resultSet.next()) {
            bookList.add(new Book(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return bookList;
    }

    @Override
    public List<Book> searchBookByAuthor(String author) throws SQLException {
        List<Book> bookList = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM book WHERE author LIKE '%" + author + "%';");
        while (resultSet.next()) {
            bookList.add(new Book(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return bookList;
    }

    @Override
    public List<Book> searchBookByGenre(String genre) throws SQLException {
        List<Book> bookList = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM book WHERE genre LIKE '%" + genre + "%';");
        while (resultSet.next()) {
            bookList.add(new Book(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return bookList;
    }

    @Override
    public List<Book> searchBookByAvailability(String availability) throws SQLException {
        List<Book> bookList = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM book WHERE availability ='" + availability + "';");
        while (resultSet.next()) {
            bookList.add(new Book(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return bookList;
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        List<Book> bookList = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM book;");
        while (resultSet.next()) {
            bookList.add(new Book(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return bookList;
    }

    @Override
    public boolean deleteBook(String bookId) throws SQLException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM book WHERE bookId='"+bookId+"'")>0;
    }
}
