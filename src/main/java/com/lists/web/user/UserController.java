package com.lists.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getCreateUser() {
        return new ModelAndView("createUser", "userStub", new UserStub());
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String getUser(@PathVariable(value="username") String username, Model model) {

        UserStub user = userStubRepository.findOne(username);

        model.addAttribute("user", user);

        return "user";
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute("userStub")UserStub userStub,
                                   BindingResult result, ModelMap model) {

        User user = new User(userStub.getUsername(), passwordEncoder.encode("asdf"), AuthorityUtils.createAuthorityList("ROLE_VIEWER"));
        jdbcUserDetailsManager.createUser(user);

        return "redirect:/user/" + user.getUsername();
    }
}
