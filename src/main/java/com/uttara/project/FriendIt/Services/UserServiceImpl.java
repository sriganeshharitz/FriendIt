package com.uttara.project.FriendIt.Services;

import com.uttara.project.FriendIt.Model.Constants;
import com.uttara.project.FriendIt.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String checkUserExists(String email) {
        if(userRepository.findByEmail(email).isPresent()){
            return "Email already in use.";
        }
        else {
            return Constants.SUCCESS;
        }
    }
}
