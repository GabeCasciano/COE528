package coe528.lab3;

import java.util.List;

public class Odometer extends AbstractCounter{
    DigitCounter digitCounter;
    List<LinkedCounter> list;
    public Odometer(int size){
        if(size < 1)
            throw new IllegalArgumentException("size is too small");

        digitCounter = new DigitCounter();
        list.add(new LinkedCounter(digitCounter));//add digit counter to first linked counter


        /*initialize the list of counters in the following order(the following is also the counting order, r to l):
               -DigitCounter
               -Digit n
               -Digit n-1
               - ...
               -Digit 1
               -Digit 0
         */
        for(int i = 0; i < size; i++)
            list.add(new LinkedCounter(list.get(i)));
    }

    @Override
    public String count(){
        LinkedCounter temp = new LinkedCounter(list.get(list.size()));//we want the last index of the list (least significant digit)
        return temp.count();
    }

    @Override
    public void increment(){
        LinkedCounter temp = new LinkedCounter(list.get(list.size()));
        temp.increment();
    }

    @Override
    public void decrement() {
        LinkedCounter temp = new LinkedCounter(list.get(list.size()));
        temp.decrement();
    }
}
