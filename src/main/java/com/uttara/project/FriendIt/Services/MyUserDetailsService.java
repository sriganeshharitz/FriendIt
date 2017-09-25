package com.uttara.project.FriendIt.Services;

import com.uttara.project.FriendIt.Model.MyUserPrincipal;
import com.uttara.project.FriendIt.Model.Status;
import com.uttara.project.FriendIt.Model.User;
import com.uttara.project.FriendIt.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(user.getStatus().equals(Status.INACTIVE))
                throw new UsernameNotFoundException(username);
            else
                System.out.println("Following credentials"+user);
                return new MyUserPrincipal(user);
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }
}
