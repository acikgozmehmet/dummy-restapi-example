package com.dummy.tests;

import io.restassured.path.json.JsonPath;

public class LoginTest {

    public void printDetails(String username, String password){

        System.out.println("username = " + username);
        System.out.print("password = " );

        for (int i =0; i < password.length(); i++)
            System.out.print("*");

    }

    public static void main(String [] args){

        String name = "David";
        String pass = "1234567890";

        String jsonStr = "{ \"username\":\"" + name   + "\"," +
                "\"password\":\"" + pass + "\"}";

        System.out.println("jsonStr = " + jsonStr);

        JsonPath jsonPath = new JsonPath(jsonStr);
        String username2 = jsonPath.get("username");
        String password2 = jsonPath.get("password");

        LoginTest loginTest =  new LoginTest();
        loginTest.printDetails(username2, password2);


    }

}
