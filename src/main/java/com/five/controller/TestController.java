package com.five.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/8 14:50
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public String test(){
        return "ok";
    }

}
