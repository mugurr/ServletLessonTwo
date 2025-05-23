package ru.itis.servletlessontwo.service;

import ru.itis.servletlessontwo.dto.request.SignInRequest;
import ru.itis.servletlessontwo.dto.request.SignUpRequest;
import ru.itis.servletlessontwo.dto.response.AuthResponse;
import ru.itis.servletlessontwo.dto.response.UserDataResponse;

public interface UserService {
    AuthResponse signUp(SignUpRequest request);

    AuthResponse signIn(SignInRequest request);

    AuthResponse checkAdmin(UserDataResponse user);
}
