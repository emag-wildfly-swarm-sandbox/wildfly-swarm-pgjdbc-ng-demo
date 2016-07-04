package wildflyswarm.pgjdbcng;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.SQLException;

@Path("/")
public class MyController {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String get() throws NamingException {
    Context ctx = new InitialContext();
    DataSource ds = (DataSource) ctx.lookup("jboss/datasources/myDS");

    String result;
    try (Connection conn = ds.getConnection()) {
      result = String.format("{\"value\":\"%s\"}", conn);
    } catch (SQLException e) {
      e.printStackTrace();
      result = "{\"value\":\"fail\"}";
    }

    return result;
  }

}
