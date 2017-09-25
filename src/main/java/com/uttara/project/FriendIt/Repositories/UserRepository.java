package com.uttara.project.FriendIt.Repositories;

import com.uttara.project.FriendIt.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    public Optional<User> findByEmail(String email);
}
