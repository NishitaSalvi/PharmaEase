package com.example.pharmaEase.user;

import java.util.List;
import java.util.Optional;

import com.example.pharmaEase.DTO.user.LoginDTO;
import com.example.pharmaEase.registration.RegistrationRequest;
import com.example.pharmaEase.response.LoginResponse;


public interface IUserService {
    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    LoginResponse loginUser(LoginDTO loginDTO);
    Optional<User> findByEmail(String email);

    void saveUserVerificationToken(User theUser, String verificationToken);

    String validateToken(String theToken);
}
