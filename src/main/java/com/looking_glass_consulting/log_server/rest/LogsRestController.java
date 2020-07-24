package com.looking_glass_consulting.log_server.rest;

import java.util.ArrayList;
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

import com.looking_glass_consulting.log_server.entity.Call;
import com.looking_glass_consulting.log_server.entity.Client;
import com.looking_glass_consulting.log_server.entity.Log;
import com.looking_glass_consulting.log_server.entity.SalesPerson;
import com.looking_glass_consulting.log_server.entity.dto.LogDTO;
import com.looking_glass_consulting.log_server.service.DbService;

@RestController
@RequestMapping("/api")
public class LogsRestController {

	@Autowired
	private DbService<Log> logsService;
	
	@GetMapping("/logs")
	public List<LogDTO> getLogs() {
		List<Log> logs =  logsService.get();
		List<LogDTO> logDTOs = new ArrayList<LogDTO>();
		
		for (Log log : logs) {
			logDTOs.add(new LogDTO(log));
		}
		
		return logDTOs;
	}
	
	@GetMapping("/logs/{logId}")
	public LogDTO getLog(@PathVariable int logId) {
		return new LogDTO(logsService.getSingle(logId));
	}
	
	@PostMapping("/logs")
	public LogDTO saveLog(@RequestBody LogDTO logDTO) {
		
		Log theLog = new Log(logDTO);
		
		theLog.setId(0);
		logsService.save(theLog);
		
		return new LogDTO(theLog);
	}
	
	@PutMapping("/logs")
	public LogDTO updateLog(@RequestBody LogDTO logDTO) {
		Log tempLog = logsService.getSingle(logDTO.getId());
		
		if (tempLog == null) {
			throw new RuntimeException("No such log");
		}
		
		Log theLog = new Log(logDTO);
		
		logsService.save(theLog);
		
		return new LogDTO(theLog);
	}
	
	@DeleteMapping("/logs/{logId}")
	public String deleteLog(@PathVariable int logId) {
		Log tempLog = logsService.getSingle(logId);
		
		if (tempLog == null) {
			throw new RuntimeException("No such log");
		}
		
		logsService.delete(logId);
		
		return "Deleted log with id - " + logId;
	}
}
