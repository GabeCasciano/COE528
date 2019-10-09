package coe528.lab3;

import java.util.Scanner;

public class OdometerDriver {

    public static void main(String args[]) {
        try{
            System.out.println("Enter number of digits for odometer");
            Scanner s = new Scanner(System.in);
            int numOfDigits = s.nextInt();

            Odometer odometer = new Odometer(numOfDigits);

            //increment 130 times and print count
            for(int i = 0; i < 130; i++)
                odometer.increment();
            System.out.println(odometer.count());

            //decrement 30 times and print count
            for(int i = 0; i < 31; i++)
                odometer.decrement();
            System.out.println(odometer.count());

            //increment 1001 times and print count
            for(int i = 0; i < 1001; i++)
                odometer.increment();
            System.out.println(odometer.count());

            //decrement 1102 times and print count
            for(int i = 0; i < 1101; i++)
                odometer.decrement();
            System.out.println(odometer.count());

            //reset the counter
            odometer.reset();
            System.out.println(odometer.count());

            //decrement once and print
            odometer.decrement();
            System.out.println(odometer.count());

            //increment once and print
            odometer.increment();
            System.out.println(odometer.count());
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex);
        }
    }
}
