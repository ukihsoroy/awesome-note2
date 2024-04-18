package org.ko.web.flux.rout;

import org.ko.web.flux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouting {

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler) {
        return route(GET("/user").and(accept(APPLICATION_JSON)), userHandler::getUser)
                .andRoute(GET("/user/customers").and(accept(APPLICATION_JSON)), userHandler::getUserCustomers)
                .andRoute(DELETE("/user").and(accept(APPLICATION_JSON)), userHandler::deleteUser);
    }
}
