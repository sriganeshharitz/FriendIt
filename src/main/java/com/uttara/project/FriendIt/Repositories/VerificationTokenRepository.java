package com.uttara.project.FriendIt.Repositories;

import com.uttara.project.FriendIt.Model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    public Optional<VerificationToken> findByToken(String token);
}
