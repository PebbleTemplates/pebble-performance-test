package io.pebbletemplates.benchmark;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.error.PebbleException;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

public class Pebble extends BaseBenchmark {

  private Map<String, Object> context;
  private PebbleEngine engine;

  @Setup
  public void setup() throws PebbleException {
    this.engine = new PebbleEngine.Builder()
        .autoEscaping(false)
        .build();
    this.context = this.getContext();
  }

  @Benchmark
  public String benchmark() throws PebbleException, IOException {
    PebbleTemplate template = this.engine.getTemplate("templates/stocks.pebble.html");
    StringWriter writer = new StringWriter();
    template.evaluate(writer, this.context);
    return writer.toString();
  }

}
