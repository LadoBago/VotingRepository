package home.learning.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import home.learning.voting.model.Vote;
import home.learning.voting.service.VoteService;

@RestController
public class VoteController {

	@Autowired
	public VoteController(VoteService voteService) {
		this.voteService = voteService;
	}

	private VoteService voteService;
	
	@RequestMapping(method = RequestMethod.POST, path = "/api/vote", produces = "text/plain;charset=UTF-8")
	@CrossOrigin()
	public ResponseEntity<String> post(@RequestBody Vote vote)
	{
		if (vote == null)
		{
			return ResponseEntity.badRequest().build();
		}
		
		voteService.enqueueVote(vote);
		return ResponseEntity.ok("Ok");
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/api/vote", produces = "application/json")
	public ResponseEntity<List<Object>> post()
	{		
		List<Object> list = voteService.getUnhandledVotes();
		return ResponseEntity.ok(list);
	}
}
