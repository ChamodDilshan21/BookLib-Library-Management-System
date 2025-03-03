package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Borrow {

    public Borrow(String borrowId, Integer userId, String borrowDate, String returnDate) {
        this.borrowId = borrowId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    String borrowId;
    Integer userId;
    String borrowDate;
    String returnDate;
    List<BorrowDetail> bookList;

}
