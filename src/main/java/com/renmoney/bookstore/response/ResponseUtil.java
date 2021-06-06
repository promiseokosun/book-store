package com.renmoney.bookstore.response;


import com.renmoney.bookstore.constant.ResponseCodes;

public class ResponseUtil {

    public static BaseResponse success(String description, Object entity) {
        BaseResponse baseResponse = new BaseResponse(ResponseCodes.REQUEST_SUCCESSFUL, description);
        baseResponse.setEntity(entity);
        return baseResponse;
    }

    public static BaseResponse response(Integer rc, String description, Object entity) {
        BaseResponse baseResponse = new BaseResponse(rc, description);
        baseResponse.setEntity(entity);
        return baseResponse;
    }

    public static BaseResponse invalidOrNullInput(String description) {
        BaseResponse baseResponse = new BaseResponse(ResponseCodes.BAD_INPUT_PARAM, description);
        return baseResponse;
    }

    public static BaseResponse userNameOrEmailExist() {
        return new BaseResponse(ResponseCodes.ACCOUNT_ALREADY_EXISTS, "User with the supplied user name or email exist");
    }
}

