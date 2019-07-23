package com.nilportugues.api.cassandra_to_csv.controllers.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel
public class ApiErrors {
    @ApiModelProperty
    private List<ApiError> errors = new ArrayList<>();

    public void add(ApiError error) {
        errors.add(error);
    }

    public List<ApiError> getErrors() {
        return errors;
    }
}
