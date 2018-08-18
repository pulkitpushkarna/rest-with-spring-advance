package com.springdemo.springrestdochateos.events;

import com.springdemo.springrestdochateos.entities.Employee;
import com.springdemo.springrestdochateos.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class Bootstrap {

    @Autowired
    EmployeeRepository employeeRepository;

    @EventListener(ApplicationStartedEvent.class)
    public void init(){
        if(!employeeRepository.findAll().iterator().hasNext()) {
            IntStream.range(1,51).forEach(e->{
                Employee employee = new Employee();
                employee.setAge(20+e);
                employee.setName("name "+e);
                employee.setSalary(20000+(e*1000));
                employeeRepository.save(employee);
            });

        }
    }
}
