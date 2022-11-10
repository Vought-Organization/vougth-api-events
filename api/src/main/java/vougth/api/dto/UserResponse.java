package vougth.api.dto;

public class UserResponse {
    private String userName;
    private String password;

    public UserResponse(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
