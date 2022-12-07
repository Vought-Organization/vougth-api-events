package vougth.api.response;

public class UserResponse {

    private Integer idUser;
    private String email;
    private String userName;
    private String password;

    public UserResponse(Integer idUser, String userName, String password, String email) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.email = email;

    }

    public Integer getIdUser() {
        return idUser;
    }

    public String getEmail() { return email; }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
