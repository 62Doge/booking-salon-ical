package com.booking.service;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Service;

import java.util.List;
import java.util.stream.Collectors;

public class FindService {
    public static Customer findCustomerById(String customerId, List<Person> personList) {
        return personList.stream()
                .filter(person -> person instanceof Customer && person.getId().equals(customerId))
                .map(person -> (Customer) person)
                .findFirst()
                .orElse(null);
    }

    public static Employee findEmployeeById(String employeeId, List<Person> personList) {
        return personList.stream()
                .filter(person -> person instanceof Employee && person.getId().equals(employeeId))
                .map(person -> (Employee) person)
                .findFirst()
                .orElse(null);
    }

    public static List<Service> findServicesById(List<String> serviceIds, List<Service> serviceList) {
        return serviceList.stream()
                .filter(service -> serviceIds.contains(service.getServiceId()))
                .collect(Collectors.toList());
    }
}
