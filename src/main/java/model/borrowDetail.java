package model;

import java.util.Date;

public class borrowDetail {

    public borrowDetail(String userId, String bookId, Date borrowDate, Date returnDate, Double fine, String returnStatus) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.returnStatus = returnStatus;
    }

    String recordId;
    String userId;
    String bookId;
    Date borrowDate;
    Date returnDate;
    Double fine;
    String returnStatus;

}
