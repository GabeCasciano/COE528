/** Bank App - COE528
    @author Gabriel Casciano, 500744076
    @since Nov, 25, 2019
    @version 1.0
 */
package coe528.func.Users;

/**
 *
 * The Manager class extends the User class, modifies the toString method
 */
public class Manager extends User {

    /** Normal Constructor
     *
     * @param username The intended username for the new Manager object
     * @param password The intended password for the new Manager Object
     * @throws Exception if the password format is not correct
     */
    public Manager(String username, String password) throws Exception{
        super(username, password);
    }

    /** Database load constructor
     *
     * @param id the id number to be associated to the manager
     * @param username the username to be associated with the manager
     * @param password the password to be associated with the manager
     * @throws Exception if the password format is in not correct
     */
    public Manager(int id, String username, String password) throws Exception{
        super(id, username, password);
    }

    /** Copy constructor
     *
     * @param manager the manager to be copied
     * @throws Exception exceptions are thrown based on the parent class, User
     */
    public Manager(Manager manager) throws Exception{
        super(manager.getId(), manager.getUsername(), manager.getPassword());
    }



    /**
     * Modified version of the default User toString(), specifies that this is a MANAGER
     *
     * @return Manager signifier and username
     */
    @Override
    public String toString() {
        return "Manager " + super.toString();
    }
}
