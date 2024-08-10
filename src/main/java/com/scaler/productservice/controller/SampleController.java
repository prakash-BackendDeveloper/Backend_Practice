package com.scaler.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say")
public class SampleController {
    @GetMapping("/hello/{Person1}/{Person2}/{times}")
    public String sayHello(@PathVariable("Person1") String name1,@PathVariable("Person2")
                           String name2, @PathVariable("times") int times){
        String output="";
        for(int i=0;i<times;i++){
            if(i%2==0) {
                output = output + " hello " + name1 + "\n";
            }
            else{
                output = output + " hello " + name2 + "\n";
            }
        }
        return output;
    }

    @GetMapping("/bye")
    public String sayBye(){
        return "Bye Everyone";
    }
}
