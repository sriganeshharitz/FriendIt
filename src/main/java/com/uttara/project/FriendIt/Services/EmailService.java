package com.uttara.project.FriendIt.Services;

import com.uttara.project.FriendIt.Model.Status;

public interface EmailService {
    public Status sendEmail(String to, String subject, String text);
}
