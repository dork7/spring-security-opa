package com.example.springsecurity.opa;

import org.springframework.http.HttpEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class OPAVoter implements AccessDecisionVoter<Object> {

    private String opaAuthURL;

    private RestTemplate restTemplate;

    public OPAVoter(String opaAuthURL) {
        this.opaAuthURL = opaAuthURL;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object obj, Collection<ConfigAttribute> attributes) {

        if (!(obj instanceof FilterInvocation)) {
            return ACCESS_ABSTAIN;
        }

        FilterInvocation filter = (FilterInvocation) obj;
        Map<String, String> headers = new HashMap<String, String>();

        for (Enumeration<String> headerNames = filter.getRequest().getHeaderNames(); headerNames.hasMoreElements();) {
            String header = headerNames.nextElement();
            headers.put(header, filter.getRequest().getHeader(header));
        }

        String[] path = filter.getRequest().getRequestURI().replaceAll("^/|/$", "").split("/");
        String jwtToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6InNhbG1hbiIsImlhdCI6MTUxNjIzOTAyMn0.u6nBhlILdZT04AM5BUWlwHUVXtiH-dsVpikBWUju27M";
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("auth", authentication);
        input.put("roles", authentication.getAuthorities());
        input.put("method", filter.getRequest().getMethod());
        input.put("path", path);
        input.put("headers", headers);
        input.put("jwt" , jwtToken);

        HttpEntity<?> request = new HttpEntity<>(new OPADataRequest(input));
        OPADataResponse response = restTemplate.postForObject(this.opaAuthURL, request, OPADataResponse.class);

        if (!response.getResult()) {
            return ACCESS_DENIED;
        }

        return ACCESS_GRANTED;
    }

}