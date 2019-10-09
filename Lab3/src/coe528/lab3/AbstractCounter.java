package coe528.lab3;

public abstract class AbstractCounter implements Counter{
    private int counter;
    public AbstractCounter(){
        counter = 0;
    }
    public AbstractCounter(AbstractCounter abstractCounter){
        counter = abstractCounter.getCounter();
    }
    public String count(){
        return Integer.toString(counter);
    }

    public void increment(){
        counter += (counter < 9)?1:-9;
    }

    public void decrement(){
        counter -= (counter > 0)?1:-9;
    }
    public void reset(){
        counter = 0;
    }
    public int getCounter(){
        return counter;
    }
}
