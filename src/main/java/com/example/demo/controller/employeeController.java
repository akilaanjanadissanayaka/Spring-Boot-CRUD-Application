package com.example.demo.controller;

import com.example.demo.exeption.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class employeeController {
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeRepo.findAll();
    }

    @PostMapping
    public Employee saveEmploee(@RequestBody Employee employee){
        return employeeRepo.save(employee);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee=employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist"));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee ( @PathVariable long id,@RequestBody Employee employeeDetails){
        Employee updateEmployee= employeeRepo.findById(id)
            .orElseThrow(()->new ResourceNotFoundException("Employee not exist with id: "+id));
        updateEmployee.setFirstName (employeeDetails.getFirstName());
        updateEmployee.setLastName (employeeDetails.getLastName());
        updateEmployee.setEmailId (employeeDetails.getEmailId());
        employeeRepo.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee (@PathVariable long id) {
        Employee employee= employeeRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee not exist with id: " +id));
        employeeRepo.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
