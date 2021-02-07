package com.example.rest.service;

import com.example.rest.dao.PhoneBookDAO;
import com.example.rest.entity.PhoneBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {

    private PhoneBookDAO phoneBookDAO;

    @Autowired
    public PhoneBookServiceImpl(PhoneBookDAO phoneBookDAO) {
        this.phoneBookDAO = phoneBookDAO;
    }

    @Override
    @Transactional
    public List<PhoneBook> findByNumber(String number) {
        return phoneBookDAO.findByNumber(number);
    }
}
