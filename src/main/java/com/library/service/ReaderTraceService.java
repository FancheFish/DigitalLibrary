package com.library.service;

import com.library.bean.ReaderTrace;
import com.library.dao.ReaderTraceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReaderTraceService {
    @Autowired
    private ReaderTraceDao readerTraceDao;

    public boolean addTrace(long reader_id, String behavior, String detail) {
        return readerTraceDao.addTrace(reader_id, behavior, detail)>0;
    }

    public ArrayList<ReaderTrace> getAllTraces() {
        return readerTraceDao.getAllTraces();
    }

}

