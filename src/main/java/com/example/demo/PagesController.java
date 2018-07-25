package com.example.demo;


import com.example.demo.model.MathService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PagesController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello from CNE training class!!!";
    }

    @GetMapping("/math/pi")
    public String renderPi(){
        return "3.141592653589793";
    }

    @GetMapping("/math/calculate")
    public int calculate(@RequestParam(required = false, defaultValue = "add") String operation, @RequestParam int x, @RequestParam int y) {
        MathService mathService =  new MathService();
        switch (operation) {
            case "add": return mathService.sum(x , y);
            case "subtract": return mathService.subtract(x , y);
            case "multiply": return mathService.multiply(x , y);
            case "divide": return mathService.divide(x,y);
            default: return mathService.sum(x , y);
        }
    }

    @PostMapping("/math/sum")
    public int sum(@RequestParam Map queryParams) {
        Collection<Integer> values = queryParams.values();
        MathService mathService = new MathService();
        int sum = 0;
        for (Object x: values) {
            int intX = Integer.valueOf((String) x);
            sum = mathService.sum(sum, intX);
        }
        return sum;
    }

    @RequestMapping("/math/volume/{length}/{breadth}/{height}")
    public String calculateVolume(@PathVariable int length, @PathVariable int breadth, @PathVariable int height){
        return String.format("The volume of a %d x %d x %d rectangle is %d", length, breadth, height, length * breadth * height);
    }
}
