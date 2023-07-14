package com.ehong.poll.Poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/poll")
public class PollController {

    public final PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping
    public List<Poll> getPolls(){
        return pollService.getPolls();
    }

    @PostMapping
    public void addPoll(@RequestBody Poll poll){
        pollService.addNewPoll(poll);
    }
}
