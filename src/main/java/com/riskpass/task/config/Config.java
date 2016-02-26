package com.riskpass.task.config;

import java.util.regex.Pattern;

public class Config {
  private Pattern commaPattern = null;
  private Pattern nextQuotePattern = null;
  private Pattern lastQuotePattern = null;

  private Config(String comma, String quote) {
    commaPattern = Pattern.compile(comma);
    nextQuotePattern = Pattern.compile(quote+"\\s*"+comma);
    lastQuotePattern = Pattern.compile(quote+"$");
  }

  public Pattern commaPattern() {
    return this.commaPattern;
  }

  public Pattern nextQuotePattern() {
    return this.nextQuotePattern;
  }

  public Pattern lastQuotePattern() {
    return this.lastQuotePattern;
  }


  public static class ConfigBuilder {

    private static final String DEFAULT_COMMA = ",";
    private static final String DEFAULT_QUOTE = "\"";

    private String comma = DEFAULT_COMMA;
    private String quote = DEFAULT_QUOTE;

    public static ConfigBuilder builder(){
      return new ConfigBuilder();
    }

    public ConfigBuilder withCommaSymbol(String commaSymbol) {
      this.comma = commaSymbol;
      return this;
    }

    public ConfigBuilder withQuoteSymbol(String quoteSymbol) {
      this.quote = quoteSymbol;
      return this;
    }

    public Config build() {
      return new Config(this.comma, this.quote);
    }

  }
}
