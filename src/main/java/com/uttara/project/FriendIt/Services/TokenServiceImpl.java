package com.uttara.project.FriendIt.Services;

import com.uttara.project.FriendIt.Model.*;
import com.uttara.project.FriendIt.Repositories.UserRepository;
import com.uttara.project.FriendIt.Repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private EmailService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder encoder;

    public String createTokenAndSendEmail(User user, PersonalInfo personalInfo, ProfileInfo profileInfo, MultipartFile multipartFile) throws IOException {

        VerificationToken token = new VerificationToken();
        user.setPassword(encoder.encode(user.getPassword()));
        token.setUser(user);
        byte[] pic= multipartFile.getBytes();
        Byte[] picture = new Byte[pic.length];
        System.out.println("Pic lenght is="+pic.length);
        for(int i=0;i<pic.length;i++){
            picture[i]=pic[i];
        }
        profileInfo.setPicture(picture);
        user.setPersonalInfo(personalInfo);
        user.setProfileInfo(profileInfo);
        personalInfo.setUser(user);
        profileInfo.setUser(user);
        userRepository.save(user);
        token.setToken(UUID.randomUUID().toString());
        String text = Constants.BODY+Constants.URI+token.getToken()+Constants.REGARDS;
        Status status = service.sendEmail(user.getEmail(),Constants.VERIFICATIONSUBJECT,text);
        if(status.equals(Status.SUCCESS)){
            verificationTokenRepository.save(token);
            return Constants.SUCCESS;
        }
        else {
            userRepository.delete(user);
            return "Error sending email.";
        }
    }

    public String verifyTokenAndRegister(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken.isPresent()){
            Optional<User> user = userRepository.findById(verificationToken.get().getUser().getId());
            if(user.isPresent()){
                User user1 = user.get();
                if(user1.getStatus().equals(Status.ACTIVE))
                    return "Email already verified";
                user1.setStatus(Status.ACTIVE);
                userRepository.save(user1);
                return Constants.SUCCESS;
            }
            else {
                return "The link has expired. Please register again";
            }
        }
        else {
            return "The link has expired. Please register again";
        }
    }
}
