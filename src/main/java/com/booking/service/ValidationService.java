package com.booking.service;

import com.booking.models.*;

import java.util.List;

public class ValidationService {
    // Buatlah function sesuai dengan kebutuhan
    public static void validateInput(){

    }

    public static boolean validateCustomerId(String customerId, List<Person> personList){
        boolean isCustomerExist = personList.stream()
                .filter(person -> person instanceof Customer)
                .map(person -> (Customer) person)
                .anyMatch(customer -> customer.getId().equals(customerId));

        if (!isCustomerExist) {
            System.out.println("Customer yang dicari tidak tersedia.");
        }
        return isCustomerExist;
    }

    public static boolean validateEmployeeId(String employeeId, List<Person> personList){
        boolean isEmployeeExist = personList.stream()
                .filter(person -> person instanceof Employee)
                .map(person -> (Employee) person)
                .anyMatch(employee -> employee.getId().equals(employeeId));

        if (!isEmployeeExist) {
            System.out.println("Employee yang dicari tidak tersedia.");
        }
        return isEmployeeExist;
    }

    public static boolean validateServiceId(String serviceId, List<Service> serviceList){
        boolean isServiceExist = serviceList.stream()
                .anyMatch(service -> service.getServiceId().equals(serviceId));

        if (!isServiceExist) {
            System.out.println("Service yang dicari tidak tersedia.");
        }
        return isServiceExist;
    }

    public static boolean validateReserveId(String reserveId, List<Reservation> reservationList){
        boolean isReservationExist = reservationList.stream()
                .anyMatch(reservation -> reservation.getReservationId().equals(reserveId) && reservation.getWorkstage().equalsIgnoreCase("In Process"));

        if (!isReservationExist) {
            System.out.println("Reservation yang dicari tidak tersedia.");
        }
        return isReservationExist;
    }
}
