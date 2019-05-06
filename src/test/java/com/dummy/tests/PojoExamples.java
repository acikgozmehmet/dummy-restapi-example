package com.dummy.tests;

import com.dummy.pojos.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gherkin.deps.com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;

public class PojoExamples {

    @Test

    public void testGson(){

        Person person = new Person();
        person.setName("Daniel");
        person.setSurname("Jackson");
        person.setGender("male");
        person.setRegion("Earth");

        System.out.println("person = " + person);

        Gson gson = new Gson();

        String jsonString = gson.toJson(person);
        System.out.println("jsonString = " + jsonString);



        // deserialization
        String myJsonString = "{\"name\":\"Daniel\"," +
                              "\"surname\":\"Jackson\"," +
                              "\"gender\":\"male\"," +
                              "\"region\":\"Earth\"}";

        Person myPerson = gson.fromJson(myJsonString, Person.class);
        System.out.println("myPerson = " + myPerson);
    }


    @Test
    public void testJackson() throws IOException {

        Person person = new Person();
        person.setName("Daniel");
        person.setSurname("Jackson");
        person.setGender("male");
        person.setRegion("Earth");

        System.out.println("person = " + person);


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);
        System.out.println("json = " + json);


        Person person1 = objectMapper.readValue(json, Person.class);
        System.out.println("person1 = " + person1);

    }

}
