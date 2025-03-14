package br.com.fiap.proj_bank_lm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String apresent(){
        return "Proj Bank LM - RM558843: Laura | RM558832: Maria Eduarda -> 2TDSPK";
    }

}
