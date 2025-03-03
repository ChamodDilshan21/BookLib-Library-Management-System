package controller.transaction;

import db.DBConnection;
import model.BorrowDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BorrowDetailController {

    public boolean addBorrowDetail(List<BorrowDetail> borrowDetailList) throws SQLException {
        for (BorrowDetail borrowDetail : borrowDetailList){
            boolean isBorrowDetailAdded = addBorrowDetail(borrowDetail);
            if (!isBorrowDetailAdded){
                return false;
            }
        }
        return true;
    }

    public boolean addBorrowDetail(BorrowDetail borrowDetail) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO borrowdetail VALUES(?,?)");
        preparedStatement.setObject(1,borrowDetail.getBorrowId());
        preparedStatement.setObject(2,borrowDetail.getBookCode());
        return preparedStatement.executeUpdate()>0;
    }
}
