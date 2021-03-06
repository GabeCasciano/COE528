/** Bank App - COE528
 @author Gabriel Casciano, 500744076
 @since Nov, 25, 2019
 @version 1.0
 */
package coe528.func.Users;

/**
 * The Customer class extends the User class, modifies toString method
 */
public class Customer extends User{


    /** Empty Constructor
     *
     * @throws Exception the exception will not be thrown, dont worry
     */
    public Customer()throws Exception{
        super("", "");
    }

    /** Default Constructor
     *
     * @param username The intended username for the new Customer object
     * @param password The intended password for the new Customer Object
     * @throws Exception if the password format is not correct, an exception will be thrown
     */
    public Customer(String username, String password) throws Exception{
        super(username, password);
    }


    /** Copy Constructor
     *
     * @param customer The customer you want to copy
     * @throws Exception if the password format is not correct, an exception will be thrown
     */
    public Customer(User customer) throws Exception{
        super(customer.getId(), customer.getUsername(), customer.getPassword());
    }



    /** Manual Constructor
     *
     * @param id the id number this customer will be initialized with
     * @param username the username this customer will be initialized with
     * @param password the password this customer will be initizlized with
     * @throws Exception if the password format is not correct, an exception will be thrown
     */
    public Customer(int id, String username, String password) throws Exception{
        super(id, username, password);
    }

    /**
     * Modified version of the default User toString(), specifies that this is a CUSTOMER
     *
     * @return Customer signifier, and username
     */
    @Override
    public String toString() {
        return "Customer " +  super.toString();
    }

}
