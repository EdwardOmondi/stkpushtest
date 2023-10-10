package me.edward.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class Test {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping()
    ResponseEntity<Integer> test() {
        logger.info("Test.test");
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }
}
