package com.img.mockito;

import com.img.mockito.model.Dummy;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JunitIntegrationTesting {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRepository repository;
    private String baseUrl="http://localhost";

    private static RestTemplate restTemplate;

    private Dummy dummy;
    @BeforeAll
    public static void init(){
        restTemplate=new RestTemplate();
    }

    @BeforeEach
    public void setUp(){
        baseUrl=baseUrl+":"+port+"/dummy";
        dummy=new Dummy(1,"Praveen",25,"Male");
        repository.save(dummy);
    }
    @AfterEach
    public void drop(){
        repository.deleteAll();
    }

    @Test
    @Order(1)
    public void save(){
        Dummy response=restTemplate.postForObject(baseUrl+"/add",dummy,Dummy.class);
        assertAll(
                ()->assertNotNull(response),
                ()->assertEquals(dummy.getName(),response.getName()),
                ()->assertEquals(1,repository.findAll().size())
        );
    }

    @Test
    @Order(2)
    public void fetch(){
        List<Dummy> list=restTemplate.getForObject(baseUrl+"/fetch", List.class);
        assertAll(
                ()->assertNotNull(list),
                ()->assertEquals(1,repository.findAll().size())
        );
    }

    @Test
    @Order(3)
    public void fetchById(){
        Dummy response=restTemplate.getForObject(baseUrl+"/fetch/{id}", Dummy.class,dummy.getId());
        assertAll(
                ()->assertNotNull(response),
                ()->assertEquals("Praveen",response.getName())
        );
    }

    @Test
    @Order(4)
    public void update(){
        Dummy dummy1=new Dummy(1,"Praveen Kumar",25,"Male");
        restTemplate.put(baseUrl+"/update/{id}",dummy1,dummy1.getId());
        assertAll(
                ()->assertEquals("Praveen Kumar",dummy1.getName()),
                ()->assertEquals(1,repository.findAll().size())
        );
    }

    @Test
    @Order(5)
    public void delete(){
        restTemplate.delete(baseUrl+"/delete/{id}",dummy.getId());
        assertAll(
                ()->assertNull(null),
                ()->assertEquals(0,repository.findAll().size())
        );
    }
}
