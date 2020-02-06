package com.bedalov.sample.transformation;

public interface Transformer {

    /**
     * Transforms a given integer to the English equivalent.
     *
     * @param input integer to transform.
     * @return the input as English.
     */
    String transformToEnglish(int input);
}
