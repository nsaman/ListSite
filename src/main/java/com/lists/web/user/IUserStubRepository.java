package com.lists.web.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

/**
 * Created by nick on 9/25/2018.
 */
public interface IUserStubRepository extends CrudRepository<UserStub, String> {

}
