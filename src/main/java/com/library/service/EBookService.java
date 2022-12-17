package com.library.service;

import com.library.bean.EBook;
import com.library.dao.EBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EBookService {
    @Autowired
    private EBookDao ebookDao;

    public EBook getEBook(String ebookISBN) {
        return ebookDao.getEBook(ebookISBN);
    }

}
