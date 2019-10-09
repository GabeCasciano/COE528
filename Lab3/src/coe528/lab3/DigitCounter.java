package coe528.lab3;

public class DigitCounter extends AbstractCounter{
    private int counter;
    public DigitCounter(){
        super();
    }
    public DigitCounter(int counter){
        this.counter = counter;
    }
    public void setCounter(int counter){
        this.counter = counter;
    }
}
