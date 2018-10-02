package com.lists.config;

        import javax.sql.DataSource;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.Profile;
        import org.springframework.core.env.Environment;
        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
        import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.core.userdetails.User;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.provisioning.JdbcUserDetailsManager;

        import java.util.Arrays;

/**
 * Created by nick on 9/29/2018.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    Environment env;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }

//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth)
//            throws Exception {
//
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and().formLogin().loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/thing/1")
                .and().exceptionHandling().accessDeniedPage("/Access_Denied");
        if(Arrays.asList(env.getActiveProfiles()).contains("local"))
            http.csrf().disable();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
