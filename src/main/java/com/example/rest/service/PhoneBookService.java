package com.example.rest.service;

import com.example.rest.entity.PhoneBook;

import java.util.List;

public interface PhoneBookService {
    List<? extends PhoneBook> findByNumber(String number);
}
