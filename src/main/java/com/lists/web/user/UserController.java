package com.lists.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nick on 9/25/2018.
 */

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private IUserStubRepository userStubRepository;

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String getUser(@PathVariable(value="username") String username, Model model) {

        UserStub user = userStubRepository.findOne(username);

        model.addAttribute("user", user);

        return "user";
    }

    @RequestMapping(params = {"username"}, method = RequestMethod.POST)
    public String createUser(@RequestParam("username") String username, Model model) {

        User user = new User(username, passwordEncoder.encode("password"), new ArrayList<>());
        jdbcUserDetailsManager.createUser(user);

        return "redirect:/user/" + user.getUsername();
    }
}
