package ru.itis.servletlessontwo.mapper.impl;

import ru.itis.servletlessontwo.dto.request.SignUpRequest;
import ru.itis.servletlessontwo.dto.response.UserDataResponse;
import ru.itis.servletlessontwo.mapper.UserMapper;
import ru.itis.servletlessontwo.model.UserEntity;
import ru.itis.servletlessontwo.utils.AuthUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapperImpl implements UserMapper {
    @Override
    public UserEntity toEntity(SignUpRequest request) {
        return UserEntity.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .hashPassword(AuthUtils.hashPassword(request.getPassword()))
                .role(request.getRole())
                .build();
    }

    @Override
    public UserDataResponse toDto(UserEntity entity) {
        return UserDataResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .role(entity.getRole())
                .build();
    }

    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UserEntity.builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .username(rs.getString("username"))
                .hashPassword(rs.getString("hash_password"))
                .role(rs.getString("role"))
                .build();
    }
}
