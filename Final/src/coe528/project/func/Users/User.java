/** Bank App - COE528
 @author Gabriel Casciano, 500744076
 @since Nov,13, 2019
 @version 0.1
 */
package coe528.project.func.Users;

/**
 * Abstract User Class
 */
public abstract class User {

    private String username;
    private String password;

    /** Constructor
     *
     * @param username The intended username for the User
     * @param password The intended password for the User
     * @throws Exception If the password does not meet requirements, an Exception will be thrown
     */
    public User(String username, String password) throws Exception{
        this.username = username;
        this.setPassword(password);
    }

    /** Copy Constructor
     *
     * @param user The User that we intend to copy
     * @throws Exception If the password does not meet requirements, an Exception will be thrown
     */
    public User(User user) throws Exception{
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());
    }

    /** Default Constructor
     *
     */
    public User(){
        this.username = "";
        this.password = "";
    }

    /** Use to get User's username
     *
     * @return String, this.username
     */
    public String getUsername(){ return this.username; }

    /** Use to get a hashcode of User's password
     *
     * @return int, hashcode of this.password
     */
    public int getPasswordHash(){ return this.password.hashCode(); }

    /** Use to get User's password
     *
     * @return String, this.password
     */
    public String getPassword(){ return password; }

    /** Use to set User's username
     *
     * @param username String, username to be set
     */
    public void setUsername(String username){ this.username = username; }

    /** Use to set User's password. password must not equal the previous password, must not be void, and must not be
     * less than 8 characters.
     *
     * @param password String, password to be set
     * @throws Exception
     */
    public void setPassword(String password) throws Exception {
        if(this.password == null)
            throw new Exception("Null is impossible? you should never see this exception");
        if(password.hashCode() == getPasswordHash())
            throw new Exception("Password matches the old password");
        if(password.trim().length() < 8)
            throw new Exception("Password is too short");

        this.password = password;

    } //checks for password issues

    /** Get String representation of User.
     *
     * @return String, User's username
     */
    @Override
    public String toString(){
        return "Username: " + this.username;
    }

}
