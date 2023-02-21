package javalin.dao;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class CodeDao {

  private static CodeDao codeDao = null;

  private CodeDao() {}

  public static CodeDao instance() {
    if (null == codeDao) {
      codeDao = new CodeDao();
    }
    return codeDao;
  }

  public Optional<String> getCode(String product, String role, String environment) {
    Properties properties = new Properties();
    try {
      var resourceAsStream = getClass().getClassLoader().getResourceAsStream("config.properties");
      properties.load(resourceAsStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    var key = getKey(product, role, environment);
    if (properties.containsKey(key)) {
      return Optional.of((String) properties.get(key));
    }
    return Optional.empty();
  }

  private static String getKey(String product, String role, String environment) {
    return product + "." + role + "." + environment;
  }
}
