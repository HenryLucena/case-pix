package com.example.demo.exceptionHandler;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

public class StandardObjectException implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;
    private String msg;
    private String path;
    private Long timestamp;
    private List<String> errors;

    public StandardObjectException(Integer status, String msg, String path, Long timestamp) {
        this.status = status;
        this.msg = msg;
        this.path = path;
        this.timestamp = timestamp;
    }

    public StandardObjectException(Integer status, String msg, String path, Long timestamp, List<String> errors) {
        super();
        this.status = status;
        this.msg = msg;
        this.path = path;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public Long getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
