package com.uttara.project.FriendIt.Services;

import com.uttara.project.FriendIt.Model.Status;
import com.uttara.project.FriendIt.Model.User;

public interface PasswordValidationService {
    public Status verifyPassword(User user);
}
