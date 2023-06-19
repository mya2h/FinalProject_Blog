package CS544.Dto;

public class LoginRequest {
    private String userName;
    private String password;

    // Constructors, getters, and setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
