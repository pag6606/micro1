package com.krugercorp.ec.micro1.controller;

import com.krugercorp.ec.micro1.bean.WelcomeBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Hola";
    }
@GetMapping("/welcome-with-object")
public WelcomeBean welcomeObject() {
    return new WelcomeBean("Hola Paul");
}
}
