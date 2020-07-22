package com.looking_glass_consulting.log_server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.looking_glass_consulting.log_server.entity.Client;
import com.looking_glass_consulting.log_server.service.DbService;

@RestController
@RequestMapping("/api")
public class ClientRestController {

	@Autowired
	private DbService<Client> clientService;
	
	@GetMapping("/clients")
	public List<Client> getClients() {
		List<Client> clients = clientService.get();
		
		return clients;
	}
	
	@GetMapping("/clients/{clientId}")
	public Client getClient(@PathVariable int clientId) {
		return clientService.getSingle((clientId));
	}
	
	@PostMapping("/clients")
	public Client saveClient(@RequestBody Client theClient) {
		theClient.setId(0);
		clientService.save(theClient);
		
		return theClient;
	}
	
	@PutMapping("/clients")
	public Client updateClient(@RequestBody Client theClient) {
		Client tempClient = clientService.getSingle(theClient.getId());
		
		if (tempClient == null) {
			throw new RuntimeException("No such client");
		}
		clientService.save(theClient);
		
		return theClient;
	}
	
	@DeleteMapping("clients/{clientId}")
	public String deleteLog(@PathVariable int clientId) {
		Client tempClient = clientService.getSingle(clientId);
		
		if (tempClient == null) {
			throw new RuntimeException("No such client");
		}
		
		clientService.delete(clientId);
		
		return "Deleted client with id - " + clientId;
	}
}
