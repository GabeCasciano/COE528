package coe528.lab3;

public interface Counter {
    /** - count
     * @return current value of this counter
     */
    String count();

    /** - increment
     *  use to increment value of this counter
     */
    void increment();

    /** - decrement
     *  use to decrement value of this counter
     */
    void decrement();

    /** - reset
     *  user to reset the value of this counter
     */
    void reset();

}
