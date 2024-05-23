package com.javabootcamp.finalproject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin

public class HomeController {

    @RequestMapping("/")
    public ResponseEntity<String> home() {
        String htmlContent = "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Welcome</title>" +
                "</head>" +
                "<body>" +
                "<h1>Welcome to Our Blog!</h1>" +
                "<p>Here you'll find interesting articles on various relevant topics.</p>" +
                "</body>" +
                "</html>";
        return ResponseEntity.ok().body(htmlContent);
    }

    @RequestMapping("/**")
    public ResponseEntity<Object> nonExistentMapping(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested path does not exist");
    }
}
