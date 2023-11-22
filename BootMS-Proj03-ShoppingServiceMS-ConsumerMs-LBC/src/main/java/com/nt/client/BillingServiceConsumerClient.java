package com.nt.client;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BillingServiceConsumerClient {
	@Autowired
	private LoadBalancerClient  client;
	
	public   ResponseEntity<String>  getBillingDetails(){
		 //  get Provider MS  Less Load Factor Service instance
		  ServiceInstance  SI=client.choose("Billing-Service");
		// gather  details  from Service Instance (Producer Ms)
		  URI uri=SI.getUri();
		  // prepre  complete URL  (URI+ path)
		  String url=uri+"/billing/api/info";
		  // create RestTemplate class object
		  RestTemplate  template=new RestTemplate();
		  //consume the Provider MS service
		  ResponseEntity<String> entity=template.getForEntity(url, String.class);
		  return entity;
		  
		}

}
