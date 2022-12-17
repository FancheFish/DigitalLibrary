package com.library.dao;

import com.library.bean.EBook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class EBookDao {

    private final static String NAMESPACE = "com.library.dao.BookDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public EBook getEBook(final String ebookISBN) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getEBook", ebookISBN);
    }
}
