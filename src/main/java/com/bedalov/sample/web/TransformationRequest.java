package com.bedalov.sample.web;


import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotNull;

public class TransformationRequest {

    @NotNull(message = "Null is not a valid input.")
    private Integer input;

    @JsonCreator
    public TransformationRequest(Integer input) {
        this.input = input;
    }

    public Integer getInput() {
        return input;
    }
}
