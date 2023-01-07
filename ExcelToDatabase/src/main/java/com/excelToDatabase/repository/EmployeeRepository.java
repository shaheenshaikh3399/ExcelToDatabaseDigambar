package com.excelToDatabase.repository;

import com.excelToDatabase.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
