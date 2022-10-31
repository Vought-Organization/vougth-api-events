package vougth.api.dto;

public class CredencialDto {
    private String userName;
    private String password;

    public CredencialDto(String userName, String password) {
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
