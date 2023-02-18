package javalin.fun;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import javalin.controllers.CodeController;

public class PropertyReaderService {

  public static void main(String[] args) {
    var app = Javalin.create().start(7070);
    app.get("/health", ctx -> ctx.status(HttpStatus.OK));
    app.get("/code", CodeController.getCode);
  }
}
