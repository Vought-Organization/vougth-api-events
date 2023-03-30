package vougth.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
@Entity @Builder @AllArgsConstructor @NoArgsConstructor @Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "email", nullable = false) @Email
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "profile_photo")
    private String photoProfile;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @CPF private String cpf;
    private boolean organize;
    private String telefone;
    private String cep;

    public Integer getIdUser() {
        return idUser;
    }
    public String getUserName() {
        return userName;
    }
    public String getNickname() {
        return nickname;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getPhotoProfile() {
        return photoProfile;
    }
    public String getCpf() {
        return cpf;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getCep() {
        return cep;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhotoProfile(String photoProfile) {
        this.photoProfile = photoProfile;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean isOrganize() {
        return organize;
    }
    public void setOrganize(boolean organize) {
        this.organize = organize;
    }
}
