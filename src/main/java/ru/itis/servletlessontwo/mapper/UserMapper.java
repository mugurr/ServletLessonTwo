package ru.itis.servletlessontwo.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.servletlessontwo.dto.request.SignUpRequest;
import ru.itis.servletlessontwo.dto.response.UserDataResponse;
import ru.itis.servletlessontwo.model.UserEntity;

public interface UserMapper extends RowMapper<UserEntity> {

    UserEntity toEntity(SignUpRequest request);

    UserDataResponse toDto(UserEntity entity);
}
