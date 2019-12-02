/** Bank App - COE528
 @author Gabriel Casciano, 500744076
 @since Nov, 25, 2019
 @version 1.0
 */
package coe528.func.Users;

/**
 * Abstract User Class
 */
public abstract class User {

    private String username = "";
    private String password = "";
    private int id = 0;
    private static int USER_ID = 1;

    /** Constructor
     *
     * @param username The intended username for the User
     * @param password The intended password for the User
     * @throws Exception If the password does not meet requirements, an Exception will be thrown
     */
    public User(String username, String password) throws Exception{
        this.username = username;
        this.id = USER_ID++;
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
        this.id = user.getId();
    }

    /** Database load constructor
     *
     * @param id the id to be associated with the user
     * @param username the username to be associated with the user
     * @param password the password to be associated with the user
     * @throws Exception if the password format is not correct
     */
    public User(int id, String username, String password) throws Exception{
        this.username = username;
        this.id = id;

        this.setPassword(password);

    }
    /** Default Constructor
     *
     */
    public User(){
        this.username = "";
        this.password = "";
        this.id = 0;
    }

    /** Use to get User's id
     *
     * @return Int, this.id
     */
    public int getId(){ return this.id; }

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
     * @throws Exception if the password matches the old password, if the password is less than 7 characters
     */
    public void setPassword(String password) throws Exception {
        if(!password.equals("")) {
            if (this.password != null) {
                if (password.hashCode() == getPasswordHash())
                    throw new Exception("Password matches the old password");
            }
            if (password.length() < 7 )
                throw new Exception("Password is too short");
        }
        this.password = password;

    } //checks for password issues

    /** Get String representation of User.
     *
     * @return String, User's username
     */
    @Override
    public String toString(){
        return "Username: " + this.username + " ID: " + this.id;
    }

    public boolean equals(User user){
        if(!this.password.isEmpty() || !this.username.isEmpty()) {
            if (this.username.equals(user.getUsername())) {
                if (this.password.equals(user.getPassword()))
                    return true;
            }
        }
        return false;
    }

}
