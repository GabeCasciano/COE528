package lab1;

public class NonMember extends Passenger {
    /**
     *
     * @param name
     * @param age
     */
    public NonMember(String name, int age){
        super(name, age);
    }

    /**
     *
     * @param nonMember
     */
    public NonMember(NonMember nonMember){ super(nonMember); }
    /**
     *
     * @param p
     * @return
     */
    public double applyDiscount(double p){
        if(this.getAge() > 65)
            return p * .90;
        else
            return p;
    }
}
