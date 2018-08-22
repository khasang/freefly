package io.khasang.freefly.controller;

import io.khasang.freefly.dto.SecurityLogDTO;
import io.khasang.freefly.entity.SecurityLog;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class SecurityLogControllerIntegrationTest {

    private final static String ROOT = "http://localhost:8080/securitylog";
    private final static String ADD = "/add";
    private final static String GET_BY_ID = "/get";
    private final static String DELETE_BY_ID = "/delete";

    @Test
    public void addSecurityLog() {
        SecurityLog securityLog = createSecurityLog();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SecurityLogDTO> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                SecurityLogDTO.class,
                securityLog.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    private SecurityLog createSecurityLog() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        SecurityLog securityLog = prefillSecurityLog();

        HttpEntity<SecurityLog> entity = new HttpEntity<>(securityLog, headers);
        RestTemplate template = new RestTemplate();
        SecurityLog createdSecurityLog = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                SecurityLog.class
        ).getBody();

        assertNotNull(createdSecurityLog);
        return createdSecurityLog;
    }

    private SecurityLog prefillSecurityLog() {
        SecurityLog securityLog = new SecurityLog();
        securityLog.setDate(LocalDate.of(2018, 2,17));

        return securityLog;
    }
}
