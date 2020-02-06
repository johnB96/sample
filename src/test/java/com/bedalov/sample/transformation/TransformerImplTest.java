package com.bedalov.sample.transformation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = TransformerImpl.class)
@RunWith(SpringRunner.class)
public class TransformerImplTest {

    @Autowired
    private Transformer transformer;

    @Test
    public void transformToEnglish_givenIntMin_thenExpectedEqualsActual() {
        assertEquals("Negative two billion one hundred and forty seven million four hundred and eighty three thousand six hundred and forty eight",
                transformer.transformToEnglish(Integer.MIN_VALUE));
    }

    @Test
    public void transformToEnglish_givenIntMax_thenExpectedEqualsActual() {
        assertEquals("Two billion one hundred and forty seven million four hundred and eighty three thousand six hundred and forty seven",
                transformer.transformToEnglish(Integer.MAX_VALUE));
    }

    @Test
    public void transformToEnglish_givenZero_thenExpectedEqualsActual() {
        assertEquals("Zero",
                transformer.transformToEnglish(0));
    }

    /**
     * Typically things that are strange like this I like to include a test since
     * if this changes, we get a early detection from why something breaks.
     *
     * Here's more information on this: https://stackoverflow.com/q/5444611/4513382
     */
    @Test
    public void verifyIntUsesTwosComplement() {
        assertEquals(Integer.MIN_VALUE * -1, Integer.MIN_VALUE);
    }

    @Test
    public void transformToEnglish_givenNumberThatEndsWithZeros_thenExpectedEqualsActual() {
        assertEquals("One thousand two hundred",
                transformer.transformToEnglish(1200));
    }

    @Test
    public void transformToEnglish_givenNumberThatEndsWithZeros_thenExpectedEqualsActual2() {
        assertEquals("One thousand two hundred and one",
                transformer.transformToEnglish(1201));
    }

    @Test
    public void transformToEnglish_givenNegativeNumber_thenExpectedEqualsActual() {
        assertEquals("Negative nineteen",
                transformer.transformToEnglish(-19));
    }
}
