package com.krugercorp.ec.micro1.controllers;

import com.krugercorp.ec.micro1.Micro1Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Micro1Application.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicControllerIntegrationTest {

    private static final String LOCALHOST= "http://localhost:";
    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();
    private URI url;
    @Test
    public void welcomeIntegrationTest() throws  Exception{
        ResponseEntity<String> response = template
                .getForEntity(LOCALHOST+port +"/welcome", String.class);
        Assert.assertEquals(response.getBody(),"Hola");
    }
}
