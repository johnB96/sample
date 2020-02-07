package com.bedalov.sample.transformation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = TransformEnglishImpl.class)
@RunWith(SpringRunner.class)
public class TransformEnglishImplTest {

    @Autowired
    private Transformer transformer;

    @Test
    public void transform_givenIntMin_thenExpectedEqualsActual() {
        assertEquals("Negative two billion one hundred and forty seven million four hundred and eighty three thousand six hundred and forty eight",
                transformer.transform(Integer.MIN_VALUE));
    }

    @Test
    public void transform_givenIntMax_thenExpectedEqualsActual() {
        assertEquals("Two billion one hundred and forty seven million four hundred and eighty three thousand six hundred and forty seven",
                transformer.transform(Integer.MAX_VALUE));
    }

    @Test
    public void transform_givenZero_thenExpectedEqualsActual() {
        assertEquals("Zero",
                transformer.transform(0));
    }

    /**
     * Typically things that are strange like this I like to include a test since
     * if this changes, we get a early detection from why something breaks.
     * <p>
     * Here's more information on this: https://stackoverflow.com/q/5444611/4513382
     */
    @Test
    public void verifyIntUsesTwosComplement() {
        assertEquals(Integer.MIN_VALUE * -1, Integer.MIN_VALUE);
    }

    @Test
    public void transform_givenNumberThatEndsWithZeros_thenExpectedEqualsActual() {
        assertEquals("One thousand two hundred",
                transformer.transform(1200));
    }

    @Test
    public void transform_givenNumberThatEndsWithZeros_thenExpectedEqualsActual2() {
        assertEquals("One thousand two hundred and one",
                transformer.transform(1201));
    }

    @Test
    public void transform_givenNegativeNumber_thenExpectedEqualsActual() {
        assertEquals("Negative nineteen",
                transformer.transform(-19));
    }
}
