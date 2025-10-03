package org.factoriaf5.happypaws.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserDTORequest dto) {
        return UserEntity.builder()
                .fullName(dto.getFullName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .password(dto.getPassword())
                .build();
    }

    public UserDTOResponse toDTO(UserEntity entity) {
        return UserDTOResponse.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();
    }
}