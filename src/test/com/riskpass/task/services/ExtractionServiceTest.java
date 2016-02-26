package com.riskpass.task.services;

import com.riskpass.task.config.Config;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ExtractionServiceTest {

    private Config config = Config.ConfigBuilder.builder()
            .withCommaSymbol(";")
            .withQuoteSymbol("\"")
            .build();

    @Test
    public void extractPlainString() {
        ExtractionService extractionService = new ExtractionService(config);
        List<String> result = extractionService.extract("abcd");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("abcd", result.get(0));
    }

    @Test
    public void extractStringWithComma() {
        ExtractionService extractionService = new ExtractionService(config);
        List<String> result = extractionService.extract("abcd;  ef");
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("abcd", result.get(0));
        Assert.assertEquals("ef", result.get(1));
    }

    @Test
    public void extractStringWithQuotes() {
        ExtractionService extractionService = new ExtractionService(config);
        List<String> result = extractionService.extract("\"a;bc\"; d");
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("a;bc", result.get(0));
        Assert.assertEquals("d", result.get(1));
    }

    @Test
    public void extractStringWithSpaces() {
        ExtractionService extractionService = new ExtractionService(config);
        List<String> result = extractionService.extract("   abc   ;   d  ");
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("abc", result.get(0));
        Assert.assertEquals("d", result.get(1));
    }

    @Test
    public void extractStringWithSpacesInQuotes() {
        ExtractionService extractionService = new ExtractionService(config);
        List<String> result = extractionService.extract("\"   abc   \";\"   d  \"");
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("   abc   ", result.get(0));
        Assert.assertEquals("   d  ", result.get(1));
    }

    @Test(expected = IllegalStateException.class)
    public void illegalState() {
        ExtractionService extractionService = new ExtractionService(config);
        List<String> result = extractionService.extract("\"abc\"; \"d");
    }
}
