package org.factoriaf5.happypaws.register;

import org.factoriaf5.happypaws.role.RoleEntity;
import org.factoriaf5.happypaws.user.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterMapperTest {

    @Test
    void testDtoToEntity_mapsCorrectly() {
        RegisterDTORequest dto = new RegisterDTORequest(
                "12345678A", "Juan Perez", "600123456", "juan@mail.com", "pass123", "pass123"
        );
        RoleEntity role = new RoleEntity();
        String hashedPassword = "hashedPass";

        UserEntity user = RegisterMapper.dtoToEntity(dto, hashedPassword, role);

        assertNull(user.getId());
        assertEquals(dto.username(), user.getUsername());
        assertEquals(hashedPassword, user.getPassword());
        // assertEquals(dto.fullName(), user.getFullName());
        // assertEquals(dto.phone(), user.getPhone());
        // assertEquals(dto.email(), user.getEmail());
        assertEquals(Set.of(role), user.getRoles());
    }
}