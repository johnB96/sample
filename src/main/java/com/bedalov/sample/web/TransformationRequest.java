package com.bedalov.sample.web;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class TransformationRequest {

    @NotNull(message = "Null is not a valid input.")
    private final Integer input;

    @JsonCreator
    public TransformationRequest(@JsonProperty(value = "input", required = true) Integer input) {
        this.input = input;
    }

    public Integer getInput() {
        return input;
    }
}
