package javalin.fun;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.javalin.http.Context;
import org.junit.jupiter.api.Test;

public class PropertyReaderServiceUnit {

  private final Context ctx = mock(Context.class);

  @Test
  public void getCanReturnOkForValidParams(){
    when(ctx.queryParam("env")).thenReturn("env");
    when(ctx.queryParam("product")).thenReturn("product");
    when(ctx.queryParam("role")).thenReturn("role");


  }

}
