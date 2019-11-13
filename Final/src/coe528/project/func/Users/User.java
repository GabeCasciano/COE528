package coe528.project.func.Users;

public abstract class User {
    private String username;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public User(User user){
        this.username = user.getUsername();
        this.password = user.getPassword(user.getUsername());
    }

    public String getUsername(){ return this.username; }
    public int getPasswordHash(){ return this.password.hashCode(); }
    public String getPassword(String username){ return (this.getUsername().equals(username))?password:null; }//haha
    public void setUsername(String username){ this.username = username; }
    public void setPassword(String password){ this.password = password; }

}
