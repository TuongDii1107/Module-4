package com.sqc.academy.model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class Controller {
    @RequestMapping("/geeting") //API, endpoint
    public String hello (@RequestParam(defaultValue = "") String name,
                        @RequestParam(defaultValue = "Đà Nẵng") String diachi) {

        return "Hello " + name + "," + diachi;
    }
}
