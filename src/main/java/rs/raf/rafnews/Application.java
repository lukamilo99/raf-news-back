package rs.raf.rafnews;

import org.glassfish.jersey.server.ResourceConfig;
import rs.raf.rafnews.configuration.MyBinder;
import rs.raf.rafnews.filter.CorsFilter;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Application extends ResourceConfig {

    public Application() {
        packages("rs.raf.rafnews.factory", "rs.raf.rafnews.repository", "rs.raf.rafnews.service", "rs.raf.rafnews.resource");
        register(new CorsFilter());
        register(new MyBinder());
    }
}