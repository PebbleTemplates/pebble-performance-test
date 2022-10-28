package io.pebbletemplates.benchmark;

import io.pebbletemplates.pebble.error.PebbleException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpectedOutputTest {

    @BeforeAll
    public static void beforeClass() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void testPebbleOutput() throws IOException, PebbleException {
        Pebble pebble = new Pebble();
        pebble.setup();
        assertOutput(pebble.benchmark());
    }

    private void assertOutput(final String output) throws IOException {
        assertEquals(readExpectedOutputResource(), output.replaceAll("\\s", ""));
    }

    private String readExpectedOutputResource() throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(ExpectedOutputTest.class.getResourceAsStream("/expected-output.html")))) {
            for (;;) {
                String line = in.readLine();
                if (line == null) {
                  break;
                }
                builder.append(line);
            }
        }
        // Remove all whitespaces
        return builder.toString().replaceAll("\\s", "");
    }

}
