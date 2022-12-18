package com.library.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ReaderTraceDao {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    private final static String NAMESPACE = "com.library.dao.ReaderTraceDao.";

    public int addTrace(final long reader_id, final String behavior, final String detail) {
        Map<String, Object> map = new HashMap<>();
        map.put("reader_id", reader_id);
        map.put("behavior", behavior);
        map.put("detail", detail);
        return sqlSessionTemplate.insert(NAMESPACE + "addTrace", map);
    }

}
