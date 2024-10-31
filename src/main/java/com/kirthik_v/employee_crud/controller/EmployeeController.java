package com.kirthik_v.employee_crud.controller;

import com.kirthik_v.employee_crud.exceptions.UserNotFoundException;
import com.kirthik_v.employee_crud.model.Employee;
import com.kirthik_v.employee_crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;


    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable Long id){
       return employeeRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }
    @PutMapping("/employee/{id}")
    public Employee editEmployee(@PathVariable Long id,@RequestBody Employee newEmployee){
       return employeeRepository.findById(id)
               .map(employee -> {
                   employee.setName(newEmployee.getName());
                   employee.setAge(newEmployee.getAge());
                   employee.setAddress(newEmployee.getAddress());
                   employee.setGender(newEmployee.getGender());
                   employee.setPhone(newEmployee.getPhone());
                   return employeeRepository.save(employee);
               }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/employee/{id}")
    String deleteUser(@PathVariable Long id){
        if(!employeeRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }

            employeeRepository.deleteById(id);
            return "Employee with id:"+id+" is deleted";

    }
}
