package ru.itis.servletlessontwo.repository;

import ru.itis.servletlessontwo.model.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findUserById(Long id);

    Optional<UserEntity> findUserByEmail(String email);

    Optional<UserEntity> findUserByUsername(String username);

    Optional<UserEntity> saveNewUser(UserEntity user);
}
