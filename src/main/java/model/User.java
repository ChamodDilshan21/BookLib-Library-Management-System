package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {

    public User(String name, String contact, String membershipDate, Double balance) {
        this.name = name;
        this.contact = contact;
        this.membershipDate = membershipDate;
        this.balance = balance;
    }

    String userId;
    String name;
    String contact;
    String membershipDate;
    Double balance;
}
