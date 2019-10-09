package coe528.lab3;

public class LinkedCounter extends AbstractCounter{
    private AbstractCounter neighbor;
    private int counter;
    public LinkedCounter(AbstractCounter neighbor){
        super();
        this.neighbor = neighbor;
        counter = 0;
    }
    @Override
    public String count(){
        if(neighbor.getClass() != DigitCounter.class)
            return neighbor.count() + counter;
        return Integer.toString(counter);
    }

    @Override
    public void increment(){
        if(counter < 9)
            counter++;
        else{
            if(neighbor.getClass() != DigitCounter.class)
                neighbor.increment();
            counter = 0;
        }
    }

    @Override
    public void decrement(){
        if(counter > 0)
            counter--;
        else{
            if(neighbor.getClass() != DigitCounter.class)
                neighbor.decrement();
            counter = 9;
        }
    }
    @Override
    public void reset(){
       if(neighbor.getClass() != DigitCounter.class)
           neighbor.reset();
        counter = 0;
    }
}
