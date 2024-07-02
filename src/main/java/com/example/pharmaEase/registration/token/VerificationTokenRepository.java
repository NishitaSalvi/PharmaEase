package com.example.pharmaEase.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pharmaEase.user.User;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
    VerificationToken findTokenByUser(User user);
}
