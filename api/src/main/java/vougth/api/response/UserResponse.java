package vougth.api.response;

public class UserResponse {

    private Integer idUser;
    private String userName;
    private String password;

    public UserResponse(Integer idUser, String userName, String password) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
