package com.excelToDatabase.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excelToDatabase.domain.Employee;
import com.excelToDatabase.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private static Logger logger = LoggerFactory.getLogger(EmployeeService.class);


	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void saveEmployeeToDatabase(MultipartFile file ) {
		logger.info("Inside saveEmployeeToDatabase method");

		
			logger.info("Inside if statement method");

            try {
                List<Employee> Employees = ExcelUploadService.getEmployeesDataFromExcel(file.getInputStream());
                logger.info("Size of the Employee: {}", Employees.size() );
                
                this.employeeRepository.saveAll(Employees);
                
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
    		logger.info("outside saveEmployeeToDatabase method");

        
		logger.info("ending saveEmployeeToDatabase method");

    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }
	
}
