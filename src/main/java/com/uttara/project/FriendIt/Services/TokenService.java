package com.uttara.project.FriendIt.Services;

import com.uttara.project.FriendIt.Model.PersonalInfo;
import com.uttara.project.FriendIt.Model.ProfileInfo;
import com.uttara.project.FriendIt.Model.Status;
import com.uttara.project.FriendIt.Model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TokenService {
    public String createTokenAndSendEmail(User user, PersonalInfo personalInfo, ProfileInfo profileInfo, MultipartFile multipartFile) throws IOException;
    public String verifyTokenAndRegister(String token);
}
