package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

public class MenuService {
    private static List<Person> personList = PersonRepository.getAllPerson();
    private static List<Service> serviceList = ServiceRepository.getAllService();
    private static List<Reservation> reservationList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    private static PrintService printService = new PrintService();


    public static void mainMenu() {
        String[] mainMenuArr = {"Show Data", "Create Reservation", "Complete/cancel reservation", "Exit"};
        String[] subMenuArr = {"Recent Reservation", "Show Customer", "Show Available Employee", "Show Reservation History + Total Profit", "Back to main menu"};

        int optionMainMenu;
        int optionSubMenu;

        boolean backToMainMenu = false;
        boolean backToSubMenu = false;
        do {
            PrintService.printMenu("Main Menu", mainMenuArr);
            System.out.print("Masukkan menu: ");
            optionMainMenu = Integer.valueOf(input.nextLine());
            switch (optionMainMenu) {
                case 1:
                    do {
                        PrintService.printMenu("Show Data", subMenuArr);
                        System.out.print("Masukkan menu: ");
                        optionSubMenu = Integer.valueOf(input.nextLine());
                        // Sub menu - menu 1
                        switch (optionSubMenu) {
                            case 1:
                                // panggil fitur tampilkan recent reservation
                                printService.showRecentReservation(reservationList);

                                break;
                            case 2:
                                // panggil fitur tampilkan semua customer
                                printService.showAllCustomer(personList);

                                break;
                            case 3:
                                // panggil fitur tampilkan semua employee
                                printService.showAllEmployee(personList);
                                break;
                            case 4:
                                // panggil fitur tampilkan history reservation + total keuntungan
                                printService.showHistoryReservation(reservationList);
                                break;
                            case 0:
                                backToSubMenu = true;
                            default:
                                System.out.println("Input salah, pilih menu yang sesuai");
                                break;
                        }
                    } while (!backToSubMenu);
                    break;
                case 2:
                    // panggil fitur menambahkan reservation
                    ReservationService.createReservation(input, personList, serviceList, reservationList);

                    break;
                case 3:
                    // panggil fitur mengubah workstage menjadi finish/cancel
                    ReservationService.editReservationWorkstage(input, reservationList);
                    break;
                case 0:
                    backToMainMenu = true;
                    break;
                default:
                    System.out.println("Input salah, pilih menu yang sesuai");
                    break;
            }
        } while (!backToMainMenu);

    }
}
