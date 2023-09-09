package HelloWorldDrop;

import HelloWorldDrop.resources.HelloWorldResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class HelloWorldDropApplication extends Application<HelloWorldDropConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HelloWorldDropApplication().run(args);
    }

    @Override
    public String getName() {
        return "HelloWorldDrop";
    }

    @Override
    public void initialize(final Bootstrap<HelloWorldDropConfiguration> bootstrap) {
        // TODO: application initialization

    }

    @Override
    public void run(final HelloWorldDropConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new HelloWorldResource());
    }

}
