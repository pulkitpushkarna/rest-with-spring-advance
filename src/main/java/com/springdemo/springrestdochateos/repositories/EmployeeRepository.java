package com.springdemo.springrestdochateos.repositories;

import com.springdemo.springrestdochateos.entities.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "/person")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {

    Employee findByAge(@Param("age") Integer age);

    Employee findByName(@Param("name") String name);
}
