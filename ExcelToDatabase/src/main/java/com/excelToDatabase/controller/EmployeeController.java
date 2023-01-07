package com.excelToDatabase.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excelToDatabase.domain.Employee;
import com.excelToDatabase.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	
	@Autowired
	 private EmployeeService employeeService;

	@PostMapping("/upload-employee-data")
    public ResponseEntity<?> uploademployeeData(@RequestParam("file")MultipartFile file){
		logger.info("Inside Employee data");
        this.employeeService.saveEmployeeToDatabase(file);
		logger.info(" Employee Class");

        
        return ResponseEntity
                .ok(Map.of("Message" , " employee data uploaded and saved to database successfully"));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getemployee(){
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.FOUND);
    }
}
