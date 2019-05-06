package com.dummy.step_definitions;

import com.dummy.pojos.Employee;
import com.dummy.utilities.ConfigurationReader;
import com.dummy.utilities.ExcelUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertEquals;


public class EmployeeStepDefinitions extends Commons  {

    ExcelUtil excelUtil;

    List<Map> mapList;

    @Given("Content type and Accept type is JSON")
    public void content_type_and_Accept_type_is_JSON() {
        requestSpecification = given().accept(ContentType.JSON).
                               and().
                                     contentType(ContentType.JSON);
    }

    @When("I send a GET request with <employees> pathParam")
    public void i_send_a_GET_request_with_employees_endpoint() {
        response = requestSpecification.basePath("employees").when().get();
    }

    @Then("Status code is {int}")
    public void status_code_is(int code) {
        assertEquals(code, response.getStatusCode());
    }

    @Then("Response JSON should contain all employee data")
    public void response_JSON_should_contain_all_employee_data() {
        System.out.println(response.asString());
        response.prettyPrint();


    }


    @Then("Response JSON should contain {int} employee data")
    public void response_JSON_should_contain_employee_data(int numberOfRecords) {
        JsonPath json = response.jsonPath();
        mapList = json.getList("",Map.class);
        System.out.println("mapList.size() = " + mapList.size());
        assertThat(numberOfRecords, equalTo(mapList.size()));

/*
        for (int i = 300 ; i < mapList.size(); i++){
            String id = (String) mapList.get(i).get("id");
            System.out.println("id = " + id);

            String url = "http://dummy.restapiexample.com/api/v1/delete/"+id;

            int code = when().delete(url).getStatusCode();
            System.out.println("code = " + code);
        }
*/

//        System.out.println(mapList.size());

//        for (Map map : mapList){
//            System.out.println("map.get(\"employee_name\") = " + map.get("employee_name"));

//    }


    }


    @Then("Delete all the employee data which is more than {int} from Response JSON")
    public void delete_all_the_employee_data_which_is_more_than_from_Response_JSON(int thresholdInQuantity) {
        JsonPath json = response.jsonPath();
        mapList = json.getList("",Map.class);

        for (int i = thresholdInQuantity ; i < mapList.size(); i++){
            String id = (String) mapList.get(i).get("id");
            System.out.println("id = " + id);

            String url = "http://dummy.restapiexample.com/api/v1/delete/"+id;

            int code = when().delete(url).getStatusCode();
            System.out.println("code = " + code);
        }
    }


    @When("I send a GET request for a single employee data with the ID {string}")
    public void i_send_a_GET_request_for_a_single_employee_data_with_the_ID(String id) {
        System.out.println("id = " + id);

        response = requestSpecification.given().basePath("employee/{id}").and().pathParam("id",id).
                                         when().get();

        response.prettyPrint();

    }

    @Then("Response JSON should contain the employee data with the ID {string}")
    public void response_JSON_should_contain_the_employee_data_with_the_ID(String id ) {
        // the response does not support the JSON format. That is why I used a different logics
        String expectedVal = "\"id\"" + ":" + "\"" + id + "\"";
        System.out.println("expectedVal = " + expectedVal);
        Assert.assertTrue(response.asString().contains(expectedVal));
    }

    @When("I send a GET request for a multiple employee data with the following {string} list")
    public void i_send_a_GET_request_for_a_multiple_employee_data_with_the_following_list(String id) {
        System.out.println("In process for getting : " + id);
        i_send_a_GET_request_for_a_single_employee_data_with_the_ID(id);
    }

    @Then("Response JSON should contain the employee data with the given {string} list")
    public void response_JSON_should_contain_the_employee_data_with_the_given_list(String id) {
        System.out.println("In process for verification: " + id);
        response_JSON_should_contain_the_employee_data_with_the_ID(id);
    }


    @Then("Delete all the employee data in the Response JSON")
    public void delete_all_the_employee_data_in_the_Response_JSON() {
        JsonPath jsonPath = new JsonPath(response.asString());
        List<String> IdList = jsonPath.getList("id");
        int inc = 0;
        System.out.println("idL = " + IdList.size());
        for (String id : IdList){
//            response =  requestSpecification.given().basePath("delete/{id}").and().pathParam("id",id).
//                                              when().delete();
            i_send_a_DELETE_request_for_a_single_employee_with_the_ID(Integer.parseInt(id));

            Assert.assertEquals(200, response.statusCode());
            Assert.assertTrue(response.asString().contains("successfully! deleted Records"));
            System.out.println(++inc + " done");
        }
    }

    @Then("Response JSON should contain no data")
    public void response_JSON_should_contain_no_data() {
        content_type_and_Accept_type_is_JSON();
        i_send_a_GET_request_with_employees_endpoint();
        Assert.assertTrue(response.asString().trim().contains("[]"));
    }



    @When("I send a DELETE request for a single employee with the ID {int}")
    public void i_send_a_DELETE_request_for_a_single_employee_with_the_ID(Integer id) {
        String idStr = String.valueOf(id);
        response =  requestSpecification.given().basePath("delete/{id}").and().pathParam("id",idStr).
                when().delete();
//        System.out.println(response.statusCode());
    }

    @Then("Response JSON should contain the expression {string}")
    public void response_JSON_should_contain_the_expression(String expectedExpression) {
        Assert.assertTrue(response.asString().contains(expectedExpression));
    }

/*
    @When("I send a POST request with the name {string} and the salary {string} and the age {string}")
    public void i_send_a_POST_request_with_the_name_and_the_salary_and_the_age(String name, String salary, String age) {

        System.out.println(name);
        System.out.println(salary);
        System.out.println(age);
        String url =  "http://dummy.restapiexample.com/api/v1/create";

        String str = "{ \"name\":\"" + name   + "\"," +
                     "\"salary\":\"" + salary + "\"," +
                     "\"age\":\""    + age    + "\"}";

        response = requestSpecification.basePath("create").
//                           queryParam("name", name).
//                           queryParam("employee_salary", Integer.parseInt(salary)).
//                           queryParam("employee_age", Integer.parseInt(age)).
                   body(str).
                   when().post();

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println(response.asString());

    }
*/


    @Then("Response JSON should contain the employee data")
    public void response_JSON_should_contain_the_employee_data() {
        response.prettyPrint();
    }


    @When("I send a POST request with the following information")
    public void i_send_a_POST_request_with_the_following_information(Map<String,String> payload) {
        response = requestSpecification.given().basePath("create").body(payload).
                                         when().post();
    }

    @Given("Excel filename and sheetname are given as {string} and {string}")
    public void excel_filename_and_sheetname_are_given_as_and(String filename, String sheetname) {
        System.out.println("filename = " + filename);
        System.out.println("sheetname = " + sheetname);
        String fileName = "D:\\HRB\\dummy-restapi-example\\src\\test\\resources\\" + filename;
        System.out.println("fileName = " + fileName);
        excelUtil = new ExcelUtil(fileName, sheetname);

    }



    @When("I send a POST request for a each record in the file")
    public void i_send_a_POST_request_for_a_each_record_in_the_file() {

        List<Map<String, String>> mapList = excelUtil.getDataList();

        for (int i = 0; i < mapList.size(); i++){
            Map map = new HashMap();
            map.put("name", mapList.get(i).get("employee_name"));
            map.put("salary", mapList.get(i).get("employee_salary"));
            map.put("age", mapList.get(i).get("employee_age"));

            i_send_a_POST_request_with_the_following_information(map);

//          There is a bug in here statusCode must be 201 since it is POST
            Assert.assertEquals(200, response.statusCode());
        }
    }


    @When("I send a POST request with the following information with POJO")
    public void i_send_a_POST_request_with_the_following_information_with_POJO(Map<String, String> map) throws IOException {
        String name = map.get("name");
        String salary = map.get("salary");
        String age = map.get("age");


        String str = "{ \"name\":\"" + name   + "\"," +
                     "\"salary\":\"" + salary + "\"," +
                     "\"age\":\""    + age    + "\"" +"}";

//        System.out.println("str = " + str);


        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(str, Employee.class);

//        System.out.println("employee = " + employee);
//        System.out.println("employee = " + employee.getName());

        response = requestSpecification.given().basePath("create").body(employee).
                when().post();

//        System.out.println("response.statusCode() = " + response.statusCode());


    }


    @When("I send a POST request for a each record in the file with POJO")
    public void i_send_a_POST_request_for_a_each_record_in_the_file_with_POJO() {

        List<Map<String, String>> mapList = excelUtil.getDataList();

        for (Map map : mapList){

            Employee employee =  new Employee();

            employee.setName((String)(map.get("employee_name")));
            employee.setSalary((String)(map.get("employee_salary")));
            employee.setAge((String)(map.get("employee_age")));

            response = requestSpecification.given().basePath("create").body(employee).
                    when().post();

            assertThat(response.statusCode(), is (200));


        }

    }


}
