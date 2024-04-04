package com.booking.service;


import com.booking.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private static PrintService printService = new PrintService();

    public static void createReservation(Scanner input, List<Person> personList, List<Service> serviceList, List<Reservation> reservationList){

        printService.showAllCustomer(personList);
        String customerId = getValidCustomerId(input, personList);

        printService.showAllEmployee(personList);
        String employeeId = getValidEmployeeId(input, personList);

        printService.showAllService(serviceList);
        List<String> serviceIds = getValidServiceId(input, serviceList);

        Customer customer = FindService.findCustomerById(customerId, personList);
        Employee employee = FindService.findEmployeeById(employeeId, personList);
        List<Service> selectedServices = FindService.findServicesById(serviceIds, serviceList);

        Reservation newReservation = new Reservation(customer, employee, selectedServices, "In Process");
        reservationList.add(newReservation);

        System.out.println("Booking Berhasil!");
        System.out.println("Total Biaya Booking: " + PrintService.formatCurrency((int) newReservation.getReservationPrice()));

    }

    private static String getValidCustomerId(Scanner input, List<Person> personList) {
        String customerId;
        do {
            System.out.println("Silahkan masukkan Customer-Id: ");
            customerId = input.nextLine();
        } while (!ValidationService.validateCustomerId(customerId, personList));

        return customerId;
    }

    private static String getValidEmployeeId(Scanner input, List<Person> personList) {
        String employeeId;
        do {
            System.out.println("Silahkan masukkan Employee-Id: ");
            employeeId = input.nextLine();
        } while (!ValidationService.validateEmployeeId(employeeId, personList));

        return employeeId;
    }

    private static List<String> getValidServiceId(Scanner input, List<Service> serviceList) {
        List<String> serviceIds = new ArrayList<>();
        String choice;
        do {
            System.out.println("Silahkan masukkan Service-Id: ");
            String serviceId = input.nextLine();
            if (ValidationService.validateServiceId(serviceId, serviceList)) {
                serviceIds.add(serviceId);
            }
            System.out.println("Ingin pilih service yang lain (Y/T)?");
            choice = input.nextLine();
        } while (choice.equalsIgnoreCase("Y"));

        return serviceIds;
    }
    private static String getValidReserveId(Scanner input, List<Reservation> reservationList) {
        String reserveId;
        do {
            System.out.println("Silahkan masukkan Reservation-Id: ");
            reserveId = input.nextLine();
        } while (!ValidationService.validateReserveId(reserveId, reservationList));

        return reserveId;
    }

    public static void editReservationWorkstage(Scanner input, List<Reservation> reservationList){
        printService.showRecentReservation(reservationList);
        String reservationId = getValidReserveId(input,reservationList);

        System.out.println("Selesaikan Rerservasi: ");
        String newWorkstage = input.nextLine();

        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId().equals(reservationId)) {
                reservation.setWorkstage(newWorkstage);
                System.out.println("Reservasi dengan Id " + reservationId + " sudah " + newWorkstage);
                break;
            }
        }

    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
