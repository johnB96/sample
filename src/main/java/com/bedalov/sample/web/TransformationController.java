package com.bedalov.sample.web;

import com.bedalov.sample.transformation.Transformer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TransformationController {

    static final String TRANSFORM_TO_ENGLISH = "/transformToEnglish";
    private final Transformer transformer;

    public TransformationController(Transformer transformer) {
        this.transformer = transformer;
    }

    @PostMapping(value = TRANSFORM_TO_ENGLISH)
    public TransformationResponse transformToEnglish(@Valid @RequestBody TransformationRequest request) {
        TransformationResponse transformationResponse = new TransformationResponse();
        Integer input = request.getInput();
        transformationResponse.setOutput(transformer.transformToEnglish(input));
        transformationResponse.setInput(input);
        return transformationResponse;
    }
}
