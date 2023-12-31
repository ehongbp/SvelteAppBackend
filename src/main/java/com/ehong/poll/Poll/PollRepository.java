package com.ehong.poll.Poll;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {


    List <Poll> findAll();

    @Query("select p from Poll p where p.question = ?1")
    List<Poll> findPollByQuestion(String q);
}