package com.dummy.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class JsonPathExamples {


    @Test
    public void test(){
        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com/";
        Response response = RestAssured.given().basePath("api/clusters").header("Authorization", AccessTokenExample.getAccessToken()).accept(ContentType.JSON).and().
                            contentType(ContentType.JSON).when().get();

//        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();

        Object id = jsonPath.get("id");
        System.out.println("id = " + id);

        List<String> idList = jsonPath.get("id");
        System.out.println("idList = " + idList);

        int idOfFirst = jsonPath.get("id[0]");
        System.out.println("idOfFirst = " + idOfFirst);


        Map<String, Object> map = jsonPath.getMap("[0]");
        System.out.println("map = " + map);
        System.out.println();
        
        for (String s : map.keySet()){
            System.out.print("key = " + s);
            System.out.println(" value" + map.get(s));
        }



/*
        List<Map> mapList = response.jsonPath().getList("$", Map.class);
        System.out.println(mapList.size());
        for (Map each: mapList)
            System.out.println(each);
*/
    }
}
