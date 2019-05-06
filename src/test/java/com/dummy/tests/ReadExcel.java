package com.dummy.tests;

import com.dummy.step_definitions.Commons;
import com.dummy.utilities.ExcelUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcel extends Commons {

    public static void main(String[] args) {


        String fileName = "D:\\HRB\\dummy-restapi-example\\src\\test\\resources\\employee_data.xlsx";
        String sheetName = "data";
        ExcelUtil employeeData = new ExcelUtil(fileName, sheetName);

        List<Map<String, String>> listMap = employeeData.getDataList();



        for (int i = 0; i < 5 /*listMap.size()*/; i++){


            Map map = new HashMap();
//            map.put("id", (int) Double.parseDouble(listMap.get(i).get("id")));
            map.put("name", listMap.get(i).get("employee_name"));
            map.put("salary", (int) Double.parseDouble(listMap.get(i).get("employee_salary")));
            map.put("age", (int) Double.parseDouble(listMap.get(i).get("employee_age")));
//            map.put("profile_image", listMap.get(i).get("profile_image"));
//            System.out.println("map = " + map);

//            RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
//            RestAssured.basePath="create";


            String url =  "http://dummy.restapiexample.com/api/v1/create";
            Response response = RestAssured.given().accept(ContentType.JSON).and().contentType(ContentType.JSON).
                    and().body(map).
                    when().post(url);

            System.out.println( i + " response.statusCode() = " + response.statusCode());




        }
    }


}
