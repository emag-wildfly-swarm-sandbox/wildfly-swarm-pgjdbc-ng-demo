package wildflyswarm.pgjdbcng;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

public class App {

  public static void main(String[] args) throws Exception {
    Container container = new Container();

    container.fraction(new DatasourcesFraction()
      .jdbcDriver("pgjdbc-ng", d -> d
        .driverModuleName("com.impossibl.pgjdbc-ng")
        .driverClassName("com.impossibl.postgres.jdbc.PGDriver")
        .driverXaDatasourceClassName("com.impossibl.postgres.jdbc.xa.PGXADataSource"))
      .dataSource("myDS", ds -> ds
        .driverName("pgjdbc-ng")
        .connectionUrl("jdbc:pgsql://localhost:5432/test")
        .userName("test")
        .password("test"))
    );

    JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
    deployment.addPackage(App.class.getPackage());

    container.start().deploy(deployment);
  }

}
