package com.uttara.project.FriendIt.Services;

import com.uttara.project.FriendIt.Model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public Status sendEmail(String to,String subject,String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        try {
            mailSender.send(simpleMailMessage);
            return Status.SUCCESS;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return Status.FAILURE;
        }
    }
}
