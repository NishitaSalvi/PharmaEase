package com.example.pharmaEase.service;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.example.pharmaEase.config.MessageStrings;
import com.example.pharmaEase.exception.AuthenticationFailException;
import com.example.pharmaEase.registration.token.VerificationToken;
import com.example.pharmaEase.registration.token.VerificationTokenRepository;
import com.example.pharmaEase.user.User;
import com.example.pharmaEase.utils.Helper;

@Service
@RequiredArgsConstructor

public class AuthenticationService {

    public final VerificationTokenRepository repository;

    public void saveConfirmationToken(VerificationToken authenticationToken) {
        repository.save(authenticationToken);
    }

    public VerificationToken getToken(User user) {
        return repository.findTokenByUser(user);
    }

    public User getUser(String token) {
        VerificationToken authenticationToken = repository.findByToken(token);
        if (Helper.notNull(authenticationToken)) {
            if (Helper.notNull(authenticationToken.getUser())) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }

    public void authenticate(String token) throws AuthenticationFailException {
        if (!Helper.notNull(token)) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Helper.notNull(getUser(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
        }
    }
}