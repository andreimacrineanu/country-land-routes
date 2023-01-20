package com.home.routing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoutingController {

    @Autowired
    private RoutingService routingService;

    @GetMapping("/{origin}/{destination}")
    public ResponseEntity<?> getRoute(@PathVariable String origin, @PathVariable  String destination){
        if(routingService.isValidCca3(origin)){
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid origin country."));
        }
        if(routingService.isValidCca3(destination)){
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid destination country."));
        }
        return routingService.findRoute(origin, destination).map(strings -> ResponseEntity.ok().body(new RouteResponse(strings)))
                .orElse(ResponseEntity.badRequest().build());
    }
}
