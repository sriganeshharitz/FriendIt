package com.uttara.project.FriendIt.Model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Entity
public class ProfileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    @NotBlank
    @NotNull
    private String moreAboutUser;
    @Lob
    private Byte[] picture;
    @OneToOne
    private User user;

    public String getMoreAboutUser() {
        return moreAboutUser;
    }

    public void setMoreAboutUser(String moreAboutUser) {
        this.moreAboutUser = moreAboutUser;
    }

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProfileInfo(String moreAboutUser, Byte[] picture, User user) {
        this.moreAboutUser = moreAboutUser;
        this.picture = picture;
        this.user = user;
    }

    public ProfileInfo() {
    }

    @Override
    public String toString() {
        return "ProfileInfo{" +
                "moreAboutUser='" + moreAboutUser + '\'' +
                ", picture=" + Arrays.toString(picture) +
                ", user=" + user +
                '}';
    }

}
