package br.com.fiap.proj_bank_lm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/")
@SpringBootApplication
public class App {

	@GetMapping
	public String apresent(){
		return "Proj Bank LM - RM558843: Laura | RM558832: Maria Eduarda";
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
