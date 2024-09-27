package com.ensah.core.web.controllers;

import com.ensah.core.bo.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class PofileController {

    @GetMapping
    public ResponseEntity<String> getAllPersonRS() {
        return ResponseEntity.ok("Hello world!");
    }

}
