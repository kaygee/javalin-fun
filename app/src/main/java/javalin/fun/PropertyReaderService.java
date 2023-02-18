package javalin.fun;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javalin.beans.Code;

public class PropertyReaderService {

  private static final Properties PROPERTIES = new Properties();

  static {
    try {
      var fileReader = new FileReader("config.properties");
      PROPERTIES.load(fileReader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    Javalin.create()
        .get(
            "/code",
            ctx -> {
              var product = ctx.queryParam("product");
              var role = ctx.queryParam("role");
              var environment = ctx.queryParam("env");

              var key = getKey(product, role, environment);
              if (PROPERTIES.containsKey(key)) {
                var value = (String) PROPERTIES.get(key);
                ctx.json(new Code(value));
              } else {
                ctx.status(HttpStatus.NOT_FOUND);
              }
            })
        .start(7070);
    ;
  }

  private static String getKey(String product, String role, String environment) {
    return product + "." + role + "." + environment;
  }
}
