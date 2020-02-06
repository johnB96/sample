package com.bedalov.sample.web;


import javax.validation.constraints.NotNull;

public class TransformationRequest {

    @NotNull(message = "Null is not a valid input.")
    private Integer input;

    public Integer getInput() {
        return input;
    }

    public void setInput(Integer input) {
        this.input = input;
    }
}
