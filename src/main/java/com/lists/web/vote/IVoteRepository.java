package com.lists.web.vote;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by nick on 9/28/2018.
 */
public interface IVoteRepository extends CrudRepository<Vote, Integer> {

}
