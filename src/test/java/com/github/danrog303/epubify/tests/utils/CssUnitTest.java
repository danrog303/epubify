package com.github.danrog303.epubify.tests.utils;

import com.github.danrog303.epubify.utils.CssUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CssUnitTest {
    @Test
    public void CssUnit_CorrectlyFormattedUnit_ReturnWithoutChanges() {
        var cssUnit = new CssUnit("10em");
        Assertions.assertEquals("10em", cssUnit.getUnit());
    }

    @Test
    public void CssUnit_AlmostCorrectlyFormattedUnit_SuccessfullyFixFormatting() {
        Assertions.assertEquals("10cm", new CssUnit("     10    CM     ").getUnit());
    }

    @Test
    public void CssUnit_CorrectlyFormattedButNotExistingUnit_ThrowIllegalArgumentException() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new CssUnit("10me"));
    }

    @Test
    public void CssUnit_InvalidUnitFormatting_ThrowIllegalArgumentException() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new CssUnit(" mjko "));
    }
}
