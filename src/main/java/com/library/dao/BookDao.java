package com.library.dao;

import com.library.bean.Book;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDao {

    private final static String NAMESPACE = "com.library.dao.BookDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public int matchBook(final String searchWord) {
        String search = "%" + searchWord + "%";
        return sqlSessionTemplate.selectOne(NAMESPACE + "matchBook", search);
    }

    public int advancedMatchBook(@Param("searchName")final String searchName, @Param("searchAuthor")final String searchAuthor, @Param("searchISBN")final String searchISBN, @Param("searchCallname")final String searchCallname, @Param("searchPublish")final String searchPublish) {
//        String search = "%" + searchName + "%";
//        return sqlSessionTemplate.selectOne(NAMESPACE + "advancedMatchBook", search);

//        List<String> searchList = new ArrayList<>();
//        searchList.add("%" + searchName + "%");
//        searchList.add("%" + searchAuthor + "%");
//        searchList.add("%" + searchISBN + "%");
//        searchList.add("%" + searchCallname + "%");
//        searchList.add("%" + searchPublish + "%");
//        return sqlSessionTemplate.selectOne(NAMESPACE + "advancedMatchBook", searchList);

        HashMap<String,String> searchMap= new HashMap<>();
        searchMap.put("name", "%" + searchName + "%");
        searchMap.put("author", "%" + searchAuthor + "%");
        searchMap.put("ISBN", "%" + searchISBN + "%");
        searchMap.put("call_name", "%" + searchCallname + "%");
        searchMap.put("publish", "%" + searchPublish + "%");
        return sqlSessionTemplate.selectOne(NAMESPACE + "advancedMatchBook", searchMap);

//        String search = "name like %" + searchName + "% and author like %" + searchAuthor + "% and ISBN like %" + searchISBN + "% and call_name like %" + searchCallname + "% and publish like %" + searchPublish + "%";
//        return sqlSessionTemplate.selectOne(NAMESPACE + "advancedMatchBook", search);
    }

    public ArrayList<Book> queryBook(final String searchWord) {
        String search = "%" + searchWord + "%";
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "queryBook", search);
        return (ArrayList<Book>) result;
    }

    public ArrayList<Book> advancedQueryBook(@Param("searchName")final String searchName, @Param("searchAuthor")final String searchAuthor, @Param("searchISBN")final String searchISBN, @Param("searchCallname")final String searchCallname, @Param("searchPublish")final String searchPublish) {
//        String search = "%" + searchName + "%";
//        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "advancedQueryBook", search);
//        return (ArrayList<Book>) result;

//        List<String> searchList = new ArrayList<>();
//        searchList.add("%" + searchName + "%");
//        searchList.add("%" + searchAuthor + "%");
//        searchList.add("%" + searchISBN + "%");
//        searchList.add("%" + searchCallname + "%");
//        searchList.add("%" + searchPublish + "%");
//        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "advancedQueryBook", searchList);
//        return (ArrayList<Book>) result;

        HashMap<String,String> searchMap= new HashMap<>();
        searchMap.put("name", "%" + searchName + "%");
        searchMap.put("author", "%" + searchAuthor + "%");
        searchMap.put("ISBN", "%" + searchISBN + "%");
        searchMap.put("call_name", "%" + searchCallname + "%");
        searchMap.put("publish", "%" + searchPublish + "%");
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "advancedQueryBook", searchMap);
        return (ArrayList<Book>) result;

//        String search = "name like %" + searchName + "% and author like %" + searchAuthor + "% and ISBN like %" + searchISBN + "% and call_name like %" + searchCallname + "% and publish like %" + searchPublish + "%";
//        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "advancedMatchBook", search);
//        return (ArrayList<Book>) result;
    }

    public ArrayList<Book> getAllBooks() {
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "getAllBooks");
        return (ArrayList<Book>) result;
    }

    public int addBook(final Book book) {
        return sqlSessionTemplate.insert(NAMESPACE + "addBook", book);
    }

    public Book getBook(final long bookId) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getBook", bookId);
    }

    public int editBook(final Book book) {
        return sqlSessionTemplate.update(NAMESPACE + "editBook", book);
    }

    public int deleteBook(final long bookId) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteBook", bookId);
    }
}
