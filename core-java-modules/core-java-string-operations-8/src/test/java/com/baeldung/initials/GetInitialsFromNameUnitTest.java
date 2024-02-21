package com.baeldung.initials;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetInitialsFromNameUnitTest {

    private InitialFinder initialFinder = new InitialFinder();

    @ParameterizedTest
    @CsvSource({"John F Kennedy,JFK", ",''", "'',''", "Not Correct   88text,NC", "michael jackson,MJ", "1test 2test, ''"})
    public void getInitialFromName_usingLoop(String input, String expected) {
        String initial = initialFinder.getInitialUsingLoop(input);
        assertEquals(expected, initial);
    }

    @ParameterizedTest
    @CsvSource({"John F Kennedy,JFK", ",''", "'',''", "Not Correct   88text,NC", "michael jackson,MJ", "1test 2test, ''"})
    public void getInitialFromName_usingStringTokenizer(String input, String expected) {
        String initial = initialFinder.getInitialUsingStringTokenizer(input);
        assertEquals(expected, initial);
    }

    @ParameterizedTest
    @CsvSource({"John F Kennedy,JFK", ",''", "'',''", "Not Correct   88text,NC", "michael jackson,MJ", "1test 2test, ''"})
    public void getInitialFromName_usingRegex(String input, String expected) {
        String initial = initialFinder.getInitialUsingRegex(input);
        assertEquals(expected, initial);
    }

    @ParameterizedTest
    @CsvSource({"John F Kennedy,JFK", ",''", "'',''", "Not Correct   88text,NC", "michael jackson,MJ", "1test 2test, ''"})
    public void getInitialFromName_usingStreamsAPI(String input, String expected) {
        String initial = initialFinder.getInitialUsingStreamsAPI(input);
        assertEquals(expected, initial);
    }
}

class InitialFinder {
    public String getInitialUsingLoop(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        String[] parts = name.split("\\s+");
        StringBuilder initials = new StringBuilder();
        for (String part : parts) {
            if (part.matches("[a-zA-Z].*")) {
                initials.append(part.charAt(0));
            }
        }
        return initials.toString().toUpperCase();
    }

    public String getInitialUsingStringTokenizer(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        StringTokenizer tokenizer = new StringTokenizer(name);
        StringBuilder initials = new StringBuilder();
        while (tokenizer.hasMoreTokens()) {
            String part = tokenizer.nextToken();
            if (part.matches("[a-zA-Z].*")) {
                initials.append(part.charAt(0));
            }
        }
        return initials.toString().toUpperCase();
    }

    public String getInitialUsingRegex(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        Pattern pattern = Pattern.compile("\\b[a-zA-Z]");
        Matcher matcher = pattern.matcher(name);
        StringBuilder initials = new StringBuilder();
        while (matcher.find()) {
            initials.append(matcher.group());
        }
        return initials.toString().toUpperCase();
    }

    public String getInitialUsingStreamsAPI(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        return Arrays.stream(name.split("\\s+"))
                .filter(part -> part.matches("[a-zA-Z].*"))
                .map(part -> part.substring(0, 1))
                .collect(Collectors.joining())
                .toUpperCase();
    }
}