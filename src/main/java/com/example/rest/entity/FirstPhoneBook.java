package com.example.rest.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "first_phone_book")
public class FirstPhoneBook extends PhoneBook {

    public FirstPhoneBook() {
    }
}
