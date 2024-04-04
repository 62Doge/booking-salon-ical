package com.booking.service;

import java.text.DecimalFormat;
import java.util.List;

import com.booking.models.*;

public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public void showRecentReservation(List<Reservation> reservationList){
        int num = 1;
        System.out.printf("| %-4s | %-6s | %-11s | %-15s | %-15s | %-15s |\n",
                "No.", "ID", "Nama Customer", "Service", "Total Biaya", "Workstage");
        System.out.println("+===================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-5s | %-13s | %-15s | %-15s | %-15s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), formatCurrency((int) reservation.getReservationPrice()), reservation.getWorkstage());
                num++;
            }
        }
    }

    public void showHistoryReservation(List<Reservation> reservationList){
        int num = 1;
        double totalProfit = 0;

        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                "No.", "ID", "Nama Customer", "Service", "Total Biaya", "Workstage");
        System.out.println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                    num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), formatCurrency((int) reservation.getReservationPrice()), reservation.getWorkstage());
            if (reservation.getWorkstage().equalsIgnoreCase("Finish")){
                totalProfit += reservation.getReservationPrice();
            }
            num++;
        }
        System.out.printf("| %-45s | %-33s |", "Total Keuntungan", formatCurrency((int) totalProfit));
        System.out.println();
        System.out.println("+========================================================================================+");
        System.out.println();

    }

    public void showAllCustomer(List<Person> personList){
        int num = 1;
        System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s | %-15s |\n",
                "No.", "ID", "Nama", "Alamat", "Membership", "Uang");
        System.out.println("+====================================================================================+");
        for (Person person : personList) {
            if (person instanceof Customer){
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                        num, person.getId(), person.getName(), person.getAddress(), ((Customer) person).getMember().getMembershipName(), formatCurrency((int) ((Customer) person).getWallet()));
                num++;
            }

        }
    }

    public void showAllEmployee(List<Person> personList){
        int num = 1;
        System.out.printf("| %-4s | %-6s | %-11s | %-15s | %-15s |\n",
                "No.", "ID", "Nama", "Alamat", "Pengalaman");
        System.out.println("+=================================================================+");
        for (Person person : personList) {
            if (person instanceof Employee){
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s |\n",
                        num, person.getId(), person.getName(), person.getAddress(), ((Employee) person).getExperience());
                num++;
            }

        }
    }



    public void showAllService(List<Service> serviceList){
        int num = 1;
        System.out.printf("| %-4s | %-7s | %-18s | %-15s |\n",
                "No.", "ID", "Nama", "Harga");
        System.out.println("+=======================================================+");
        for (Service service : serviceList) {
            System.out.printf("| %-4s | %-7s | %-18s | %-15s |\n",
                    num, service.getServiceId(), service.getServiceName(), formatCurrency((int) service.getPrice()));
            num++;
        }
    }

    public static String formatCurrency(int money) {
        DecimalFormat formatter = new DecimalFormat("Rp#,###,##0.00");
        return formatter.format(money);
    }
}
