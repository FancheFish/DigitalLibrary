package com.library.bean;

import java.io.Serializable;

public class ReaderTrace implements Serializable {

    private String trace_time;
    private long trace_id;
    private long reader_id;
    private String behavior;
    private String detail;

    public String getTrace_time() {
        return trace_time;
    }

    public void setTrace_time(String trace_time) {
        this.trace_time = trace_time;
    }

    public long getTrace_id() {
        return trace_id;
    }

    public void setTrace_id(long trace_id) {
        this.trace_id = trace_id;
    }

    public long getReader_id() {
        return reader_id;
    }

    public void setReader_id(long reader_id) {
        this.reader_id = reader_id;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
