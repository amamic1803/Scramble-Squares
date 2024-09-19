package com.amamic1803;
import org.junit.Assert;
import org.junit.Test;

public class MainTests {
    @Test
    public void testIsEven() {
        Assert.assertTrue(Main.isEven(2));
        Assert.assertFalse(Main.isEven(3));
    }
}
