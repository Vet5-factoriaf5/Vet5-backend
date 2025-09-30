package org.factoriaf5.happypaws.register;

import org.factoriaf5.happypaws.user.UserEntity;
import org.factoriaf5.happypaws.role.RoleEntity;
import java.util.Set;

public class RegisterMapper {

    public static UserEntity dtoToEntity(RegisterDTORequest dto, String encodedPassword, RoleEntity defaultRole) {
        return UserEntity.builder()
                .fullName(dto.fullName())
                .username(dto.username())
                .email(dto.email())
                .phone(dto.phone())
                .password(encodedPassword) // solo guardamos la contraseña encriptada
                .roles(Set.of(defaultRole))
                .build();
    }
}