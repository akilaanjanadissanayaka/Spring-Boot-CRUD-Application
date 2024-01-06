package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public void run(String... args) throws Exception {
        Employee employee=new Employee();
        employee.setFirstName("Ramesh");
        employee.setLastName("Jagath");
        employee.setEmailId("hellooomail");
        employeeRepo.save(employee);

        Employee employee2=new Employee();
        employee2.setFirstName("akila");
        employee2.setLastName("anjana");
        employee2.setEmailId("hellooomail");
        employeeRepo.save(employee2);
    }
}
