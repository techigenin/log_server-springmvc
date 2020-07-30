package com.looking_glass_consulting.log_server.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.looking_glass_consulting.log_server.entity.Call;
import com.looking_glass_consulting.log_server.entity.dto.CallDTO;
import com.looking_glass_consulting.log_server.service.DbService;

@RestController
@RequestMapping("/api")
public class CallsRestController {

	@Autowired
	private DbService<Call> callService;
	
	@GetMapping("/calls")
	public List<CallDTO> getCalls() {
		List<Call> calls = callService.get();
		List<CallDTO> callDTOs = new ArrayList<CallDTO>();
		
		for (Call call : calls) {
			callDTOs.add(new CallDTO(call));
		}
		
		return callDTOs;
	}
	
	@GetMapping("/calls/{callId}")
	public CallDTO getCall(@PathVariable int callId) {
		return new CallDTO(callService.getSingle((callId)));
	}
	
	@PostMapping("/calls")
	public CallDTO saveCall(@RequestBody CallDTO callDTO) {
		Call theCall = new Call(callDTO);
		
		return new CallDTO(saveCall(theCall));
	}
	
	Call saveCall(Call theCall) {
		theCall.setId(0);
		callService.save(theCall);
		
		return theCall;		
	}
	
	@PutMapping("/calls")
	public CallDTO updateCall(@RequestBody CallDTO callDTO) {
		Call tempCall = callService.getSingle(callDTO.getCallId());
		
		if (tempCall == null) {
			throw new RuntimeException("No such call");
		}
		
		Call theCall = new Call(callDTO);
		
		callService.save(theCall);
		
		return new CallDTO(theCall);
	}
	
	@DeleteMapping("calls/{callId}")
	public String deleteLog(@PathVariable int callId) {
		Call tempCall = callService.getSingle(callId);
		
		if (tempCall == null) {
			throw new RuntimeException("No such call");
		}
		
		callService.delete(callId);
		
		return "Deleted call with id - " + callId;
	}
}
