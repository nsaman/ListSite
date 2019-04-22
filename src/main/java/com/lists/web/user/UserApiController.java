package com.lists.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

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

    @RequestMapping(path="/api/user/attributes", method = RequestMethod.GET)
    public UserStub getUserAttributes() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ((authentication instanceof AnonymousAuthenticationToken)) {
            return null;
        }
        return userStubRepository.findOne(authentication.getName());
    }

    @RequestMapping(path="/api/users")
    public Set<ScrubbedUser> getUsers() {
        Set<ScrubbedUser> scrubbedUsers = new HashSet<>();

        userStubRepository.findAll().forEach( userStub -> scrubbedUsers.add(new ScrubbedUser(userStub.getUsername())));

        return scrubbedUsers;
    }
}
