package com.example.apigateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayFallbackController {
    @RequestMapping("/fallback/user-service")
    public ResponseEntity<String> userServiceFallback(){
        return ResponseEntity.status(503).body("User service unavailable - fallback");
    }
}
