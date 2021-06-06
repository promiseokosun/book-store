package com.renmoney.bookstore.response;

import com.renmoney.bookstore.constant.ResponseCodes;

import java.io.Serializable;

public class BaseResponse<E> implements Serializable {

    private static final long serialVersionUID = -3427784974967460030L;

    private Status status;

    private E entity;

    public BaseResponse() {
        this.status = new Status(ResponseCodes.REQUEST_SUCCESSFUL, "Request Successful");
    }

    public BaseResponse(Integer rc, String description) {
        this.status = new Status(rc, description);
    }

    public BaseResponse(Status status, E entity) {
        this.status = status;
        this.entity = entity;
    }

    public BaseResponse(Integer rc, String description, E entity) {
        this.status = new Status(rc, description);
        this.entity = entity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "BaseResponse{" + "status=" + status + ", entity=" + entity + '}';
    }

}

