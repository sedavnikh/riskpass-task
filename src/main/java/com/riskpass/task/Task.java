package com.riskpass.task;

import com.riskpass.task.config.Config;
import com.riskpass.task.services.ExtractionService;

import java.util.regex.Pattern;

public class Task {
  public static void main(final String[] args) {
    if(args.length == 0) {
      System.err.println("No argument provided.");
      System.exit(1);
    }
    Config config = Config.ConfigBuilder.builder()
                            .withCommaSymbol(";")
                            .withQuoteSymbol("\"")
                            .build();

    ExtractionService extractionService = new ExtractionService(config);
    extractionService.extract(args[0]).forEach(System.out::println);
  }
}
