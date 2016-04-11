package edu.upc.eetac.dsa.todo;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * Created by Sergi1 on 24/02/2016.
 */
public class TodoResourceConfig extends ResourceConfig {
    public TodoResourceConfig() {
        packages("edu.upc.eetac.dsa.todo");
        packages("edu.upc.eetac.dsa.todo.auth");
        register(RolesAllowedDynamicFeature.class);
    }
}
