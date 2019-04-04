package com.lists.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by nick on 4/5/2019.
 */

@RestController
public class UserApiController {
    @Autowired
    private IUserStubRepository userStubRepository;

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(path="/api/user", method = RequestMethod.POST, consumes={"application/json"})
    public void createUser(HttpServletRequest httpServletRequest, @Valid @RequestBody NewUserRequest newUserRequest) {

        User user = new User(newUserRequest.getUsername(), passwordEncoder.encode(newUserRequest.getPassword()), AuthorityUtils.createAuthorityList("ROLE_VIEWER"));
        jdbcUserDetailsManager.createUser(user);

        try {
            httpServletRequest.login(newUserRequest.getUsername(),newUserRequest.getPassword());
        } catch (Exception e) {
            throw new RuntimeException("Could not log in the user=" + newUserRequest.getUsername() + " after creation", e);
        }
    }
}
