package com.example.rest.dao;

import com.example.rest.entity.PhoneBook;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PhoneBookDAOImpl implements PhoneBookDAO {

    private EntityManager entityManager;

    @Autowired
    public PhoneBookDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<PhoneBook> findByNumber(String number) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("from PhoneBook where tel_num=:telNum", PhoneBook.class);
        query.setParameter("telNum", number);
        List<PhoneBook> list = query.getResultList();
        return list;
    }


}
