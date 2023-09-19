package HelloWorldDrop;

import HelloWorldDrop.core.Person;
import HelloWorldDrop.resources.HelloWorldResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import io.dropwizard.hibernate.HibernateBundle;
public class HelloWorldDropApplication extends Application<HelloWorldDropConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HelloWorldDropApplication().run(args);
    }

    @Override
    public String getName() {
        return "HelloWorldDrop";
    }


    private final HibernateBundle<HelloWorldDropConfiguration> hibernate = new HibernateBundle<HelloWorldDropConfiguration>(Person.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(HelloWorldDropConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<HelloWorldDropConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig()
                .build());

        bootstrap.addBundle(new MigrationsBundle<HelloWorldDropConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(HelloWorldDropConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(hibernate);

    }

    @Override
    public void run(final HelloWorldDropConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new HelloWorldResource());

        final PersonDAO dao = new PersonDAO(hibernate.getSessionFactory());
        environment.jersey().register(new UserResource(dao));
//        final JdbiFactory factory = new JdbiFactory();
//        final Jdbi jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
//        environment.jersey().register(new UserResource(jdbi));
    }

}
