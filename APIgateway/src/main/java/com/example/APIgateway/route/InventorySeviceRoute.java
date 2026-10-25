
package com.example.APIgateway.route;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class InventorySeviceRoute {

    @Value("${INVENTORY_SERVICE_URL:http://localhost:5454}")
    private String inventoryServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> inventoryVenueRoute() {
        return GatewayRouterFunctions.route("inventory-venue")
                .route(
                        RequestPredicates.path(
                                "/api/v1/inventory/venue/{venueId}"
                        ),
                        HandlerFunctions.http()
                )
                .before(BeforeFilterFunctions.uri(
                        URI.create(inventoryServiceUrl)
                ))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryEventRoute() {
        return GatewayRouterFunctions.route("inventory-event")
                .route(
                        RequestPredicates.path(
                                "/api/v1/inventory/event/{eventId}"
                        ),
                        HandlerFunctions.http()
                )
                .before(BeforeFilterFunctions.uri(
                        URI.create(inventoryServiceUrl)
                ))
                .build();
    }
}
