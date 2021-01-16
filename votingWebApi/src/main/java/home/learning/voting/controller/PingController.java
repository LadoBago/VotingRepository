package home.learning.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import home.learning.voting.service.PingService;

@RestController
public class PingController {

	@Autowired
	public PingController(PingService pingService) {
		this.pingService = pingService;
	}

	private PingService pingService;
	
	@GetMapping(path="api/ping", produces = "text/plain")
	public ResponseEntity<String> get()
	{
		return ResponseEntity.ok(pingService.ping());
	}
	
}
