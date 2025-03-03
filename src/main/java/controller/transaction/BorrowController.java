package controller.transaction;

import controller.book.BookController;
import db.DBConnection;
import model.Borrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowController {

    public boolean placeBorrow(Borrow borrow) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO borrow(borrowId,userId,borrowDate,returnDate,dateReturned) VALUES(?,?,?,?,?)");
            preparedStatement.setObject(1,borrow.getBorrowId());
            preparedStatement.setObject(2,borrow.getUserId());
            preparedStatement.setObject(3,borrow.getBorrowDate());
            preparedStatement.setObject(4,borrow.getReturnDate());
            preparedStatement.setObject(5,null);
            boolean isBorrowPlaced =  preparedStatement.executeUpdate()>0;
            if (isBorrowPlaced){
                boolean isBorrowDetailPlaced = new BorrowDetailController().addBorrowDetail(borrow.getBookList());
                if (isBorrowDetailPlaced){
                    boolean isBookStatusUpdated = new BookController().updateBookAvailability(borrow.getBookList());
                    if (isBookStatusUpdated){
                        connection.commit();
                        return true;
                    }
                }
            }
        } finally {
            connection.rollback();
            connection.setAutoCommit(true);
        }
        return false;
    }

    public String getNextBorrowId() throws SQLException {
        List<Borrow> borrowList = getAll();
        if (!borrowList.isEmpty()){
            int lastId = Integer.parseInt(borrowList.getLast().getBorrowId().replaceAll("\\B", ""));
            return String.format("%s%03d","B",(lastId+1));
        }
        return "B001";
    }

    public List<Borrow> getAll() throws SQLException {
        List<Borrow> borrowList = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement("SELECT * FROM borrowdetail;").executeQuery();
        while (resultSet.next()){
            borrowList.add(new Borrow(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return borrowList;
    }


}
