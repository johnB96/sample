package com.bedalov.sample.web;

import com.bedalov.sample.transformation.Transformer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TransformationController {

    static final String TRANSFORM = "/transform";
    private final Transformer transformer;

    public TransformationController(Transformer transformer) {
        this.transformer = transformer;
    }

    @PostMapping(value = TRANSFORM)
    public TransformationResponse transform(@Valid @RequestBody TransformationRequest request) {
        Integer input = request.getInput();
        TransformationResponse transformationResponse = new TransformationResponse();
        transformationResponse.setOutput(transformer.transform(input));
        transformationResponse.setInput(input);
        return transformationResponse;
    }
}
