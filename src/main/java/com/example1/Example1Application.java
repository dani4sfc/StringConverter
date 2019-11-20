package com.example1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Example1Application {
		

	public static void main(String[] args) {
				
		SpringApplication.run(Example1Application.class, args);
		
	}
	
	
@Component

//Class which calls the service method
    public class CommandLineAppStartupRunner implements org.springframework.boot.CommandLineRunner {
        @Autowired
        private TimeService myService;
        
        private String param = "[H1] 0:15.025";

        @Override
        public void run(String...args) throws Exception {
         String result = myService.TimeConverter(param);
         
         System.out.println(result);

        }
    }
	
}
