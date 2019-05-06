package com.dummy.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.org.apache.bcel.internal.classfile.Unknown;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Employee {
    private String name;
    private String salary;
    private String age;



    public static void main(String [] args){
        Employee employee1 =  new Employee();
        employee1.setName("Mehmet");
        employee1.setSalary("10000");
        employee1.setAge("43");

        Employee employee2 = new Employee();
        employee2.setName("Mehmet");
        employee2.setName("10000");
        employee2.setAge("43");

        if (employee1.equals(employee2))
            System.out.println("Same");
        else
            System.out.println("Not same");

    }
}
