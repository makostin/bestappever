package com.mmf.rest.response;

/**
 * User: svetlana.voyteh
 * Date: 12.05.13
 */
public class ResponseDetails {

    private Integer code;
    private String message;
    private DataResponse data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }
}
