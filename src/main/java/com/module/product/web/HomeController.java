package com.module.product.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by on 2017/5/9.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() { return "home"; }

}
