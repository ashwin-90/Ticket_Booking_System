
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
public class BookingServiceRoute {

    @Value("${BOOKING_SERVICE_URL:http://localhost:8081}")
    private String bookingServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> bookingRoutes() {
        return GatewayRouterFunctions.route("booking-service")
                .route(
                        RequestPredicates.POST("/api/v1/booking"),
                        HandlerFunctions.http()
                )
                .before(BeforeFilterFunctions.uri(
                        URI.create(bookingServiceUrl)
                ))
                .build();
    }
}
