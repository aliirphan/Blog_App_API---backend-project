package com.codewithirphan.blog.service.impl;

import com.codewithirphan.blog.payloads.ApiResponse;

public class UtilityService {
    public static ApiResponse createResponse(String message,boolean isSuccess){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(message);
        apiResponse.setSuccess(isSuccess);
        return apiResponse;
    }
}
