package com.cg.snakeList.controller;


import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HomeController {

    @Value("${app.version}")
    private String appVersion;
    @GetMapping
    @RequestMapping("/")
    public Map getStatus(){
        Map map = new HashMap<String, String >();
        map.put("app-version", appVersion);
        return map;
    }
}
