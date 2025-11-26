package com.gbook.api.beans;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ResponseParserTest {

    @Inject
    ResponseParser responseParser;

    @Test
    public void testParseCleanUuid() {
        String uuid = "a33879c0-e578-483b-afe3-902cf4330d71";
        String result = responseParser.parse(uuid);
        Assertions.assertEquals(uuid, result);
    }

    @Test
    public void testParseUuidWithWhitespace() {
        String uuid = "  a33879c0-e578-483b-afe3-902cf4330d71  \n";
        String result = responseParser.parse(uuid);
        Assertions.assertEquals("a33879c0-e578-483b-afe3-902cf4330d71", result);
    }

    @Test
    public void testParseUuidInText() {
        String input = "Response from Gemini: a33879c0-e578-483b-afe3-902cf4330d71";
        String result = responseParser.parse(input);
        Assertions.assertEquals("a33879c0-e578-483b-afe3-902cf4330d71", result);
    }

    @Test
    public void testParseUuidInLongText() {
        String input = "Based on the situation, I choose option a33879c0-e578-483b-afe3-902cf4330d71 because it is safer.";
        String result = responseParser.parse(input);
        Assertions.assertEquals("a33879c0-e578-483b-afe3-902cf4330d71", result);
    }

    @Test
    public void testParseNoUuid() {
        String input = "I don't know what to do.";
        String result = responseParser.parse(input);
        Assertions.assertEquals(input, result);
    }
}
