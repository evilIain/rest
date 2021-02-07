package com.example.rest.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PhoneBook {

    @Id
    @Column(name="tel_num")
    private String telNum;

    @Column(name="add_date")
    private LocalDateTime addDate;


    public PhoneBook() {
    }

    public PhoneBook(String telNum) {
        this.telNum = telNum;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "telNum='" + telNum + '\'' +
                ", addDate=" + addDate +
                '}';
    }
}
