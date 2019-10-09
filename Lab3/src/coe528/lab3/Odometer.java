package coe528.lab3;

import java.util.ArrayList;
import java.util.List;

public class Odometer extends AbstractCounter{
    DigitCounter digitCounter;
    List<LinkedCounter> list = new ArrayList<LinkedCounter>();
    public Odometer(int size){
        if(size < 1)
            throw new IllegalArgumentException("size is too small");

        digitCounter = new DigitCounter(size);
        list.add(new LinkedCounter(digitCounter));//add digit counter to first linked counter


        /*initialize the list of counters in the following order(the following is also the counting order, r to l):
               -DigitCounter
               -Digit n
               -Digit n-1
               - ...
               -Digit 1
               -Digit 0
         */
        for(int i = 0; i < size-1; i++)
            list.add(new LinkedCounter(list.get(i)));
    }

    @Override
    public String count(){
        return list.get(list.size()-1).count();
    }

    @Override
    public void increment(){
        list.get(list.size()-1).increment();
    }

    @Override
    public void decrement() {
        LinkedCounter temp = new LinkedCounter(list.get(list.size()-1));
        temp.decrement();
    }

    @Override
    public void reset(){
        list.get(list.size()-1).reset();
    }
}
