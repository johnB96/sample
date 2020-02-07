package com.bedalov.sample.transformation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TransformEnglishImpl implements Transformer {

    private static final String[] FIRST_NINETEEN = {
            "",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen"
    };

    private static final String[] TENS_NAMES = {
            "",
            "ten",
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"
    };

    private static final String ENGLISH = "English";
    private static final String HUNDRED = " hundred";
    private static final String THOUSAND = " thousand";
    private static final String MILLION = " million";
    private static final String BILLION = " billion";
    private static final String ZERO = "Zero";
    private static final String NEGATIVE = "Negative ";
    private static final String SPACE = " ";
    private static final String AND = " and ";
    private static final int INT_MIN_HELPER = Integer.MAX_VALUE - 7;

    /**
     * {@inheritDoc}.
     * <p>
     * Just a note about the transition to data types, since the Javax validations have eliminated
     * a need to check null, we can safely use int here. Default handing of null for a primitive
     * int type changes the input to 0 so just having null be a bad request is more deterministic
     * since we can't determine the user intention with calling transform with null.
     *
     * @param input integer to transform.
     * @return
     */
    @Override
    public String transform(int input) {
        if (input == 0) {
            return ZERO;
        }
        if (input == Integer.MIN_VALUE) {
            return handleIntMin();
        }
        if (input < 0) {
            return NEGATIVE + transformUnsigned(-1 * input);
        }
        return uppercaseFirstLetter(transformUnsigned(input));
    }

    @Override
    public String getLanguage() {
        return ENGLISH;
    }

    private String uppercaseFirstLetter(String val) {
        return StringUtils.capitalize(val);
    }

    /**
     * Integer.MIN_VALUE does not work with the approach used above since
     * Integer.MIN_VALUE * -1 == Integer.MIN_VALUE. Instead, we use a number
     * that we can more easily work with and that ends in zero, then add the
     * eight word on at the end.
     */
    private String handleIntMin() {
        return NEGATIVE + transformUnsigned(INT_MIN_HELPER) + SPACE + FIRST_NINETEEN[8];
    }

    /**
     * Transforms an unsigned int, intended to be called recursively.
     *
     * @param unsignedInput primitive unsigned int.
     * @return unsigned English value.
     */
    private String transformUnsigned(int unsignedInput) {
        if (unsignedInput <= 19) {
            return FIRST_NINETEEN[unsignedInput];
        }
        if (unsignedInput <= 99) {
            int remainder = unsignedInput % 10;
            if (remainder == 0) {
                return TENS_NAMES[unsignedInput / 10];
            } else {
                return TENS_NAMES[unsignedInput / 10] + SPACE + transformUnsigned(remainder);
            }
        }
        if (unsignedInput <= 999) {
            return transformUnsignedPortion(unsignedInput, 100, HUNDRED, AND);
        }
        if (unsignedInput <= 999_999) {
            return transformUnsignedPortion(unsignedInput, 1_000, THOUSAND, SPACE);
        }
        if (unsignedInput <= 999_999_999) {
            return transformUnsignedPortion(unsignedInput, 1_000_000, MILLION, SPACE);
        }
        return transformUnsignedPortion(unsignedInput, 1_000_000_000, BILLION, SPACE);
    }

    private String transformUnsignedPortion(int unsignedInput, int divisor, String magnitude, String conjunction) {
        int remainder = unsignedInput % divisor;
        if (remainder == 0) {
            return transformUnsigned(unsignedInput / divisor) + magnitude;
        }
        return transformUnsigned(unsignedInput / divisor) + magnitude + conjunction + transformUnsigned(remainder);
    }
}
