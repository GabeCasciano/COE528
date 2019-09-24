/*
 Test:
 -Create 2 flights
 -Display flights (using: org and dest from ticket and flight, through the passenger class)
 -Check a flight (same as above)
 -Create an array of passengers
 -Create a memeber
 -Create a nonmember
 -Book a seat with a member
 -Book a seat with a nonmember
 -
 */

import java.util.*;

public class Manager {

    private static List<Flight> flights = new ArrayList<Flight>();
    private static List<Ticket> tickets = new ArrayList<Ticket>();
    private static int flightNumber = flights.size();

    public static String createDepartureTime(int MM, int DD, int YYYY, int hour_24_format, int minute){
        return MM + "/" + DD + "/" + YYYY + " " + hour_24_format + ":" + minute;
    }
    public static void createFlights() {
        flights.add(new Flight(1, "Toronto", "Montreal", createDepartureTime(9, 25, 2019, 8, 30), 100, 200));

    }
    public static void displayAvailableFlights(String origin, String destination){
       for(Flight temp : flights){
           if(temp.getDestination().equals(destination) && temp.getOrigin().equals(origin))
               System.out.println(temp.toString());
       }
    }
    public static Flight getFlight(int FlightNumber){
        for(Flight temp : flights) {
            if (temp.getFlightNumber() == FlightNumber)
                return temp;
        }
        return (Flight)null;
    }
    public static void bookSeat(int flightNumber, Passenger passenger){
        Flight flight = new Flight(getFlight(flightNumber));
        if(flight != null){
            if(flight.bookASeat())
                tickets.add(new Ticket(passenger, flight));
        }
    }

    public static void main(String[] args){
        createFlights();
        Flight flight1 = new Flight (getFlight(1));
        displayAvailableFlights(flight1.getOrigin(), flight1.getDestination());
    }
}
