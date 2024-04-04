package com.booking.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    //   workStage (In Process, Finish, Canceled)
    private static int counter = 1;

    public Reservation(Customer customer, Employee employee, List<Service> services,
                       String workstage) {
        this.reservationId = generateReservId(counter);
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.reservationPrice = calculateReservationPrice();
        this.workstage = workstage;
        counter++;
    };

    private double calculateReservationPrice(){
        double totalServicePrice = 0;

        for (Service service : services) {
            totalServicePrice += service.getPrice();
        }

        if (customer.getMember().getMembershipName().equalsIgnoreCase("Silver")) {
            return totalServicePrice - (totalServicePrice * 0.05);
        } else if (customer.getMember().getMembershipName().equalsIgnoreCase("Gold")) {
            return totalServicePrice - (totalServicePrice * 0.1);
        } else {
            return totalServicePrice;
        }
    }
    public String generateReservId(int counter){
        String reservIDFormatted = String.format("Rsv-%02d", counter);
        return reservIDFormatted;
    }

}
