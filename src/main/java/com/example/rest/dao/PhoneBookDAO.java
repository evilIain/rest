package com.example.rest.dao;

import com.example.rest.entity.PhoneBook;

import java.util.List;

public interface PhoneBookDAO {

    List<PhoneBook> findByNumber(String number);
}
