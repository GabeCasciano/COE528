package lab1;

public class Flight {

    private int flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private int capacity;
    private int numberOfSeatsLeft;
    private double originalPrice;

    /**Default Constructor
     *
     * @param flightNumber - Flight number
     * @param origin - Origin of the Flight
     * @param destination - Destination of Flight
     * @param departureTime - Departure Time of Flight
     * @param capacity - Capacity of Flight
     * @param originalPrice - Original price of Flight
     */
    public Flight(int flightNumber, String origin, String destination, String departureTime, int capacity, double originalPrice){
        try {
            if (origin.equals(destination))
                throw new IllegalArgumentException("Invalid, detination may not equal origin");
            else {
                this.flightNumber = flightNumber;
                this.origin = origin;
                this.destination = destination;
                this.departureTime = departureTime;
                this.capacity = capacity;
                this.numberOfSeatsLeft = capacity;
                this.originalPrice = originalPrice;
            }
        }
        catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    /**Copy Constructor
     *
     * @param flight
     */
    public Flight(Flight flight) {
        try{
            if(flight.getOrigin().equals(flight.getDestination())) {
                throw new IllegalArgumentException("DESTINATION and ORIGIN of flight # " + flight.getFlightNumber() + " are the same, Invalid Initialization");
            }
            else{
                this.flightNumber = flight.getFlightNumber();
                this.origin = flight.getOrigin();
                this.destination = flight.getDestination();
                this.departureTime = flight.getDepartureTime();
                this.capacity = flight.getCapacity();
                this.numberOfSeatsLeft = flight.getNumberOfSeatsLeft();
                this.originalPrice = flight.getOriginalPrice();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }
    }

    /**Use this function to book a seat
     *
     * @return boolean if true, seat is book, if false seat is not booked
     */
    public boolean bookASeat(){
        if(numberOfSeatsLeft > 0){
            numberOfSeatsLeft--;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     *
     * @return String form flight
     */
    @Override
    public String toString(){
        return "Flight: " + this.flightNumber + " " + this.origin + " to " + this.destination + ", " + this.departureTime + ", Original Price: $" + this.originalPrice;
    }

    /**
     *
     * @param flightNumber
     */
    public void setFlightNumber(int flightNumber){ this.flightNumber = flightNumber; }

    /**
     *
     * @param origin
     */
    public void setOrigin(String origin){
        try{
            if(!origin.equals(this.destination))
                this.origin = origin;
            else
                throw new IllegalArgumentException("DESTINATION and ORIGIN of flight # " + flightNumber + " are the same, Invalid Initialization");
        }
        catch(IllegalArgumentException e){
            System.out.println(e.toString());
        }
    }


    /**
     *
     * @param destination
     */
    public void setDestination(String destination){
        try{
            if(!destination.equals(this.destination))
                this.destination = destination;
            else
                throw new IllegalArgumentException("DESTINATION and ORIGIN of flight # " + flightNumber + " are the same, Invalid Initialization");
        }
        catch(IllegalArgumentException e){
            System.out.println(e.toString());
        }
    }

    /**
     *
     * @param departureTime
     */
    public void setDepartureTime(String departureTime){ this.departureTime = departureTime; }

    /**
     *
     * @param capacity
     * @param clearPlane - if true, plane is cleared, if false, plane isnt cleared unless its required.
     * @return
     */
    public boolean setCapacity(int capacity, boolean clearPlane){// true if numberOfSeats is adjusted, false if numberOfSeats is reset
        int temp = numberOfSeatsLeft + (this.capacity - capacity);
        if(temp > 0 && clearPlane != true){
            numberOfSeatsLeft = temp;
            this.capacity = capacity;
            return false;
        }
        else{

            this.capacity = capacity;
            numberOfSeatsLeft = capacity;
            return true;
        }
    }

    /**
     *
     * @param numberOfSeatsLeft
     */
    public void setNumberOfSeatsLeft(int numberOfSeatsLeft){ this.numberOfSeatsLeft = numberOfSeatsLeft; }

    /**
     *
     * @param originalPrice
     */
    public void setOriginalPrice(double originalPrice){ this.originalPrice = originalPrice; }

    /**
     *
     * @return
     */
    public int getFlightNumber(){ return flightNumber; }

    /**
     *
     * @return
     */
    public String getOrigin(){ return origin; }

    /**
     *
     * @return
     */
    public String getDestination(){ return destination; }

    /**
     *
     * @return
     */
    public String getDepartureTime(){ return  departureTime; }

    /**
     *
     * @return
     */
    public int getCapacity(){ return capacity; }

    /**
     *
     * @return
     */
    public int getNumberOfSeatsLeft(){ return numberOfSeatsLeft; }

    /**
     *
     * @return
     */
    public double getOriginalPrice(){ return originalPrice; }

}
