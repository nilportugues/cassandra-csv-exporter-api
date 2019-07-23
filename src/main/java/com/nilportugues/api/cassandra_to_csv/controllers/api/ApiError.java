package com.nilportugues.api.cassandra_to_csv.controllers.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ApiError {
    @ApiModelProperty(name = "code")
    private String code;

    @ApiModelProperty(name = "message")
    private String message;


    public ApiError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}