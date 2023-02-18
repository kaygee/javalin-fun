package javalin.controllers;

import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import java.util.Optional;
import javalin.beans.Code;
import javalin.dao.CodeDao;

public class CodeController {

  public static Handler getCode =
      ctx -> {
        var product = ctx.queryParam("product");
        var role = ctx.queryParam("role");
        var environment = ctx.queryParam("env");

        CodeDao codeDao = CodeDao.instance();
        Optional<String> code = codeDao.getCode(product, role, environment);

        if (code.isPresent()) {
          ctx.json(new Code(code.get()));
        } else {
          ctx.status(HttpStatus.NOT_FOUND);
        }
      };
}
