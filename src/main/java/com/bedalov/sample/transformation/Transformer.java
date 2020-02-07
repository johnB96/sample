package com.bedalov.sample.transformation;

public interface Transformer {

    /**
     * Transforms a given integer to a readable word.
     *
     * @param input integer to transform.
     * @return the input as readable word.
     */
    String transform(int input);

    /**
     * Implementing classes return their specific language.
     *
     * @return language of implementation.
     */
    String getLanguage();
}
