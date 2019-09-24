//Helo World
public class Ticket {

    private static int ticketNumberMaster = 1;
    private Passenger passenger;
    private Flight flight;
    private double price;
    private int number;

    /**
     *
     * @param passenger
     * @param flight
     */
    public Ticket(Passenger passenger, Flight flight){
        this.passenger = passenger;
        this.flight = flight;
        this.price = passenger.applyDiscount(flight.getOriginalPrice());
        this.number = ticketNumberMaster;
        ticketNumberMaster++;
    }

    /**
     *
     * @param ticket
     */
    public Ticket(Ticket ticket){
        this.passenger = ticket.getPassenger();
        this.flight = ticket.getFlight();
        this.price = ticket.getPrice();
        this.number = ticket.getNumber();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return passenger.getName() + ", " + flight.toString() + ", Ticket Price: $" + price;
    }

    /**
     *
     * @return
     */
    public Passenger getPassenger(){ return this.passenger; }

    /**
     *
     * @return
     */
    public Flight getFlight(){ return this.flight; }

    /**
     *
     * @return
     */
    public double getPrice(){ return this.price; }

    /**
     *
     * @return
     */
    public int getNumber(){ return  this.number; }
}
