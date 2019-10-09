package coe528.lab3;

public class OdometerDriver {

    public static void main(String args[]){
        Odometer odometer = new Odometer(3);

        System.out.println(odometer.digitCounter.count());
    }
}
