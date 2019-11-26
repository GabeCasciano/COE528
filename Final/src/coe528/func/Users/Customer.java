/** Bank App - COE528
 @author Gabriel Casciano, 500744076
 @since Nov,13, 2019
 @version 0.1
 */
package coe528.func.Users;

/**
 *
 * The Customer class extends the User class, modifies toString method
 */
public class Customer extends User{

    /** Normal Constructor
     *
     * @param username The intended username for the new Customer object
     * @param password The intended password for the new Customer Object
     * @throws Exception if the password format is not correct, or an exception will be thrown
     */
    public Customer()throws Exception{
        super("", "");
    }

    public Customer(String username, String password) throws Exception{
        super(username, password);
    }

    public Customer(User customer) throws Exception{
        super(customer.getId(), customer.getUsername(), customer.getPassword());
    }

    /**
     *
     * @param id
     * @param username
     * @param password
     * @throws Exception
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
