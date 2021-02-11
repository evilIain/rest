package com.example.rest.service;

import com.example.rest.entity.PhoneBook;

import java.util.List;

public interface PhoneBookService {
    List<PhoneBook> findByNumber(String number);
}
