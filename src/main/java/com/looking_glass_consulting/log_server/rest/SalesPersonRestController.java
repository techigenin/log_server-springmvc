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

import com.looking_glass_consulting.log_server.entity.SalesPerson;
import com.looking_glass_consulting.log_server.service.DbService;

@RestController
@RequestMapping("/api")
public class SalesPersonRestController {

	@Autowired
	private DbService<SalesPerson> salesPersonService;
	
	@GetMapping("/salesPersons")
	public List<SalesPerson> getSalesPersons() {
		List<SalesPerson> salesPersons = salesPersonService.get();
		
		return salesPersons;
	}
	
	@GetMapping("/salesPersons/{salesPersonId}")
	public SalesPerson getSalesPerson(@PathVariable int salesPersonId) {
		return salesPersonService.getSingle((salesPersonId));
	}
	
	@PostMapping("/salesPersons")
	public SalesPerson saveSalesPerson(@RequestBody SalesPerson theSalesPerson) {
		theSalesPerson.setSalesPersonId(0);
		salesPersonService.save(theSalesPerson);
		
		return theSalesPerson;
	}
	
	@PutMapping("/salesPersons")
	public SalesPerson updateSalesPerson(@RequestBody SalesPerson theSalesPerson) {
		SalesPerson tempSalesPerson = salesPersonService.getSingle(theSalesPerson.getSalesPersonId());
		
		if (tempSalesPerson == null) {
			throw new RuntimeException("No such salesPerson");
		}
		salesPersonService.save(theSalesPerson);
		
		return theSalesPerson;
	}
	
	@DeleteMapping("salesPersons/{salesPersonId}")
	public String deleteLog(@PathVariable int salesPersonId) {
		SalesPerson tempSalesPerson = salesPersonService.getSingle(salesPersonId);
		
		if (tempSalesPerson == null) {
			throw new RuntimeException("No such salesPerson");
		}
		
		salesPersonService.delete(salesPersonId);
		
		return "Deleted salesPerson with id - " + salesPersonId;
	}
}
