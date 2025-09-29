package org.factoriaf5.happypaws.register;

import org.factoriaf5.happypaws.user.UserEntity;
import org.factoriaf5.happypaws.role.RoleEntity;
import java.util.Set;

public class RegisterMapper {

    public static UserEntity dtoToEntity(RegisterDTORequest dto, String encodedPassword, RoleEntity defaultRole) {
        return new UserEntity(
                null, // id autogenerado por la BD
                dto.username(),
                encodedPassword,
                dto.fullName(),
                dto.phone(),
                dto.email(), 
                encodedPassword, 
                Set.of(defaultRole)
        );
    }
}