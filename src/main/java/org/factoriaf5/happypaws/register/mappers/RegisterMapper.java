package org.factoriaf5.happypaws.register.mappers;

import org.factoriaf5.happypaws.register.dtos.RegisterDTORequest;
//activar imports reales cuando se integren user role y repositorios
//import org.factoriaf5.happypaws.user.User;
//import org.factoriaf5.happypaws.role.Role;

// TODO:imports temporales
import org.factoriaf5.happypaws.temp.User;
import org.factoriaf5.happypaws.temp.Role;
// FIXME:fin imports temporales

import java.util.Set;

public class RegisterMapper {

    public static User dtoToEntity(RegisterDTORequest dto, String encodedPassword, Role defaultRole) {
        return new User(
                null, // id autogenerado por la BD
                dto.username(),
                encodedPassword,
                dto.fullName(),
                dto.phone(),
                dto.email(),
                Set.of(defaultRole)
        );
    }
}