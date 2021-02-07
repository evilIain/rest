package com.example.rest.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "second_phone_book")
public class SecondPhoneBook extends PhoneBook{

    public SecondPhoneBook() {
    }
}
