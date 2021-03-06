package com.example.springsecurity;

import com.example.springsecurity.opa.OPAVoter;
import com.example.springsecurity.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Value("${opa.auth.url}")
    private String opaAuthURL;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .accessDecisionManager(accessDecisionManager())
                .anyRequest().authenticated().and().httpBasic();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        System.out.println(opaAuthURL);
        List<AccessDecisionVoter<? extends Object>> decisionVoters = Arrays
                .asList(new OPAVoter(opaAuthURL));
        System.out.println(new UnanimousBased(decisionVoters));
        return new UnanimousBased(decisionVoters);
    }
}
