package com.nt.rest;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@RestController
@RequestMapping("/")
public class TicketBookingOperationsController {
	
	@GetMapping("/booking")
	@HystrixCommand(fallbackMethod = "dummyMethod", 
	
			commandProperties = {@HystrixProperty(name="circuitBreaker.enabled",value="true")})
	public ResponseEntity<String>bookTickets(){
		
		System.out.println("TicketBookingOperationsController.bookTickets()");
		if(new Random().nextInt(10)<8)
			throw new RuntimeException("Problem in ticket booking");
		
			return new ResponseEntity<String>("booked the tickets", HttpStatus.OK);
		
	}
	
	public ResponseEntity<String>dummyMethod(){
		return new ResponseEntity<String>("please try again later ... ", HttpStatus.OK);
	}
}
