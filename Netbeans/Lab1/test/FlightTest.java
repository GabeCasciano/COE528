import org.junit.Test;
import static org.junit.Assert.*;
import lab1.Flight;

public class FlightTest{
    public static Flight testFlightOriginal = new Flight (1, "Toronto", "New York", "09/10/19 6:40pm", 100, 500.0) ;

    @Test
    public void testConstructor(){
        //Test both constructors
        Flight testFlight1 = new Flight(testFlightOriginal);
        assertEquals(testFlight1.getCapacity(), testFlightOriginal.getCapacity(), 0.0);
        assertEquals(testFlight1.getFlightNumber(), testFlightOriginal.getFlightNumber(), 0.0);
        assertEquals(testFlight1.getOrigin(), testFlightOriginal.getOrigin());
        assertEquals(testFlight1.getDestination(), testFlightOriginal.getDestination());
        assertEquals(testFlight1.getDepartureTime(), testFlightOriginal.getDepartureTime());
        assertEquals(testFlight1.getOriginalPrice(), testFlightOriginal.getOriginalPrice(), 0.0);
        assertEquals(testFlight1.getNumberOfSeatsLeft(), testFlightOriginal.getNumberOfSeatsLeft());

        testFlight1 = new Flight(testFlightOriginal.getFlightNumber(), testFlightOriginal.getOrigin(), testFlightOriginal.getDestination(), testFlightOriginal.getDepartureTime(), testFlightOriginal.getCapacity(), testFlightOriginal.getOriginalPrice());

        assertEquals(testFlight1.getCapacity(), testFlightOriginal.getCapacity(), 0.0);
        assertEquals(testFlight1.getFlightNumber(), testFlightOriginal.getFlightNumber(), 0.0);
        assertEquals(testFlight1.getOrigin(), testFlightOriginal.getOrigin());
        assertEquals(testFlight1.getDestination(), testFlightOriginal.getDestination());
        assertEquals(testFlight1.getDepartureTime(), testFlightOriginal.getDepartureTime());
        assertEquals(testFlight1.getOriginalPrice(), testFlightOriginal.getOriginalPrice(), 0.0);
        assertEquals(testFlight1.getNumberOfSeatsLeft(), testFlightOriginal.getNumberOfSeatsLeft());
    }
    @Test
    public void testInvalidConstructor(){

    }
    @Test
    public void bookASeatTest(){
        Flight testFlight1 = new Flight(testFlightOriginal);
        boolean occupiedReset = testFlight1.setCapacity(105, false);
        assertEquals(occupiedReset, false);//test that the number of seats is not being reset
        occupiedReset = testFlight1.setCapacity(testFlightOriginal.getCapacity(), false);
        assertEquals(testFlight1.getNumberOfSeatsLeft(), 100);//test that we can return the plane to its original size
        assertEquals(testFlight1.getCapacity(), 100);//test that we can return plane to its original state
        for(int i = testFlightOriginal.getCapacity(); i > 1; i--){ //test that we can book a whole plane
            boolean seatIsBooked = testFlight1.bookASeat();
            assertEquals(seatIsBooked, true);
        }
        assertEquals(testFlight1.getNumberOfSeatsLeft(), 1);//test to ensure that 99 seats have been booked
        assertEquals(testFlight1.bookASeat(), true);//test that we can book the last seat
        assertEquals(testFlight1.bookASeat(), false);//test that we can not over book the plane
    }
    @Test
    public void setCapacityTest(){ //this needs special testing because it has special functionality
        Flight testFlight1 = new Flight(testFlightOriginal);
        /*
        There are 4 Cases to tests:
            1 -> capacity - new capacity > numberOfEmpty, clear plane off
            2 -> capacity - new capacity < numberOfEmpty, clear plane off
            3 -> capacity - new capacity > numberOfEmpty, clear plane on
            4 -> capacity - new capacity < numberOfEmpty, clear plane on
         */
        //Case 1
        boolean occupiedReset = testFlight1.setCapacity( 105, false);
        assertEquals(occupiedReset, false);
        //reset object
        testFlight1 = new Flight(testFlightOriginal);

        //Case 2
        occupiedReset = testFlight1.setCapacity(95, false);
        assertEquals(occupiedReset, false);
        //reset object
        testFlight1 = new Flight(testFlightOriginal);

        //Case 3
        occupiedReset = testFlight1.setCapacity(105, true);
        assertEquals(occupiedReset, true);
        //reset object
        testFlight1 = new Flight(testFlightOriginal);

        //Case 4
        occupiedReset = testFlight1.setCapacity(95, true);
        assertEquals(occupiedReset, true);
    }
    //Both set origin and destination check if the destination and origin match and will throw an IllegalArgumentException
    @Test
    public void setOriginTest(){

    }
    @Test
    public void setDestinationTest(){

    }
    @Test
    public void setFlightNumberTest(){
        Flight testFlight1 = new Flight(testFlightOriginal);
        testFlight1.setFlightNumber(2);
        assertEquals(2, testFlight1.getFlightNumber(), 0.0);
    }
    @Test
    public void setDepartureTimeTest(){
        Flight testFlight1 = new Flight(testFlightOriginal);
        testFlight1.setDepartureTime("09/10/19 6:50pm");
        assertEquals("09/10/19 6:50pm", testFlight1.getDepartureTime());
    }
    @Test
    public void setOriginalPriceTest(){
        Flight testFlight1 = new Flight(testFlightOriginal);
        testFlight1.setOriginalPrice(300);
        assertEquals(300, testFlight1.getOriginalPrice(), 0.0);
    }
    @Test
    public void toStringTest(){
        Flight testFlight1 = new Flight(testFlightOriginal);
        assertEquals(testFlightOriginal.toString(), testFlight1.toString());
    }

    //ToDo: Create tests for all of the getter and setter functions;

}