package com.it_academy.fitness_secure_version2.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/greetings")
public class GreetingsController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok( "Hello from API");
    }

    @GetMapping("/buy")
    public ResponseEntity<String> sayGoodBay(){
        return ResponseEntity.ok("Good by");
    }
}

