package com.uttara.project.FriendIt.Model;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Email
    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    @Length(min = 6,max=65)
    private String password;
    @NotBlank
    @NotNull
    @Length(min = 6,max=65)
    private String repeatedPassword;
    @Enumerated(EnumType.STRING)
    private Role role=Role.PUBLIC;
    @Enumerated(EnumType.STRING)
    private Status status=Status.INACTIVE;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private PersonalInfo personalInfo;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private ProfileInfo profileInfo;

    public User(String email, String password, String repeatedPassword, Role role, Status status) {
        this.email = email;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.role = role;
        this.status = status;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public ProfileInfo getProfileInfo() {
        return profileInfo;
    }

    public void setProfileInfo(ProfileInfo profileInfo) {
        this.profileInfo = profileInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatedPassword='" + repeatedPassword + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
