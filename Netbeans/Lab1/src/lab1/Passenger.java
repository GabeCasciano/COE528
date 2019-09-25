package lab1;

public abstract class Passenger {
    private String name;
    private int age;

    /**
     *
     * @param name
     * @param age
     */
    public Passenger(String name, int age){
        this.name = name;
        this.age = age;
    }

    /**
     *
     * @param passenger
     */
    public Passenger(Passenger passenger){
        this.age = passenger.getAge();
        this.name = passenger.getName();
    }

    /**
     *
     * @param p
     * @return
     */
    public abstract double applyDiscount(double p);

    /**
     *
     * @param name
     */
    public void setName(String name){ this.name = name; }

    /**
     *
     * @param age
     */
    public void setAge(int age){ this.age = age; }

    /**
     *
     * @return
     */
    public String getName(){ return name; }

    /**
     *
     * @return
     */
    public int getAge(){ return age; }
}
