package net.guides.springboot2.springboot2jpacrudexample.controller;

import lombok.AllArgsConstructor;
import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@AllArgsConstructor
public class EmployeeController {
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId, @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("no employee with this id"));
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable("id") Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("no employee with this id"));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }




}

