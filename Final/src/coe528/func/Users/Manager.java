/** Bank App - COE528
    @author Gabriel Casciano, 500744076
    @since Nov,13, 2019
    @version 0.1
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
     * @throws Exception if the password format is not correct, or an exception will be thrown
     */
    public Manager(String username, String password) throws Exception{
        super(username, password);
    }

    /**
     *
     * @param id
     * @param username
     * @param password
     * @throws Exception
     */
    public Manager(int id, String username, String password) throws Exception{
        super(id, username, password);
    }

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
