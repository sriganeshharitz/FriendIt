package com.uttara.project.FriendIt.Services;

import com.uttara.project.FriendIt.Model.Status;
import com.uttara.project.FriendIt.Model.User;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidationServiceImpl implements PasswordValidationService {
    @Override
    public Status verifyPassword(User user) {
        if(user.getPassword().equals(user.getRepeatedPassword()))
            return Status.SUCCESS;
        else
            return Status.FAILURE;
    }
}
