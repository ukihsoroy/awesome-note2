package org.ko.web.flux.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    public Mono<ServerResponse> getUser(ServerRequest request) {
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> getUserCustomers(ServerRequest request) {
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        return ServerResponse.ok().build();
    }
}
