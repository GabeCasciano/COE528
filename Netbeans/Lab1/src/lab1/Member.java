package lab1;

public class Member extends Passenger {

    private int yearsOfMembership;

    /**
     *
     * @param yearsOfMembership
     * @param name
     * @param age
     */
    public Member(int yearsOfMembership, String name, int age){
        super(name, age);
        this.yearsOfMembership = yearsOfMembership;
    }

    /**
     *
     * @param member
     */
    public Member(Member member){
        super(member.getName(), member.getAge());
        this.yearsOfMembership = member.yearsOfMembership;
    }

    /**
     *
     * @param yearsOfMembership
     */
    public void setYearsOfMembership(int yearsOfMembership) { this.yearsOfMembership = yearsOfMembership; }

    /**
     *
     * @param p
     * @return
     */
    public double applyDiscount(double p){
        if(yearsOfMembership > 5)
            return p * .5;
        else if(yearsOfMembership > 1)
            return p * .90;
        else
            return p;
    }

}
