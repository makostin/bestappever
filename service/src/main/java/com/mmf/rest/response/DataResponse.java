package com.mmf.rest.response;

import com.mmf.business.domain.Schedule;

import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 12.05.13
 */
public class DataResponse<T> {
    
    private List<T> response;

    public DataResponse() {
    }

    public DataResponse(List<T> list) {
        response = list;
    }

    public List<T> getResponse() {
        return response;
    }

    public void setResponse(List<T> response) {
        this.response = response;
    }
}
