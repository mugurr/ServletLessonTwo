package ru.itis.servletlessontwo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.itis.servletlessontwo.dto.request.SignInRequest;
import ru.itis.servletlessontwo.dto.request.SignUpRequest;
import ru.itis.servletlessontwo.dto.response.AuthResponse;
import ru.itis.servletlessontwo.dto.response.UserDataResponse;
import ru.itis.servletlessontwo.mapper.UserMapper;
import ru.itis.servletlessontwo.model.UserEntity;
import ru.itis.servletlessontwo.repository.UserRepository;
import ru.itis.servletlessontwo.service.UserService;
import ru.itis.servletlessontwo.utils.AuthUtils;

import java.util.Optional;

import static ru.itis.servletlessontwo.model.UserEntity.ADMIN_ROLE;

@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public AuthResponse signUp(SignUpRequest request) {
        if(request.getEmail() == null || request.getEmail().isBlank())
            return response(1, "Empty email", null);

        if(request.getPassword() == null || request.getPassword().isBlank())
            return response(2, "Empty password", null);

        if(request.getUsername() == null || request.getUsername().isBlank())
            return response(3, "Empty username", null);

        if(!AuthUtils.checkEmail(request.getEmail()))
            return response(4, "Invalid email", null);

        if(!AuthUtils.checkPassword(request.getPassword()))
            return response(5, "Weak password", null);


        if(userRepository.findUserByEmail(request.getEmail()).isPresent())
            return response(6, "Email taken", null);

        if(userRepository.findUserByUsername(request.getUsername()).isPresent())
            return response(7, "Nickname taken", null);

        Optional<UserEntity> optionalUser = userRepository.saveNewUser(userMapper.toEntity(request));

        if(optionalUser.isEmpty())
            return response(50, "Database process error", null);

        return response(0, "OK", userMapper.toDto(optionalUser.get()));
    }

    @Override
    public AuthResponse signIn(SignInRequest request) {
        if(request.getEmail() == null || request.getEmail().isBlank())
            return response(1, "Empty email", null);

        if(request.getPassword() == null || request.getPassword().isBlank())
            return response(2, "Empty password", null);

        if(!AuthUtils.checkEmail(request.getEmail()))
            return response(4, "Invalid email", null);

        Optional<UserEntity> optionalUser = userRepository.findUserByEmail(request.getEmail());

        if(optionalUser.isEmpty())
            return response(8, "Email not found", null);

        UserEntity user = optionalUser.get();

        if(!AuthUtils.verifyPassword(request.getPassword(), user.getHashPassword()))
            return response(9, "Password mismatch", null);

        return response(0, "OK", userMapper.toDto(user));
    }

    private AuthResponse response(int status, String statusDesc, UserDataResponse user) {
        return AuthResponse.builder()
                .status(status)
                .statusDesc(statusDesc)
                .user(user)
                .build();
    }

    @Override
    public AuthResponse checkAdmin(UserDataResponse user) {
        if (user.getRole().equals(ADMIN_ROLE)) {
            return response(0, "OK", user);
        } else {
            return response(10, "You are not admin :(", null);
        }
    }
}
