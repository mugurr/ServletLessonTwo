package ru.itis.servletlessontwo.dto.response;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDataResponse {

    private Long id;

    private String username;

    private String email;

    private String role;
}
