package org.factoriaf5.happypaws.register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterValidatorTest {

    private RegisterValidator validator;

    @BeforeEach
    void setUp() {
        validator = new RegisterValidator();
    }

    @Test
    void testValidDto_doesNotThrow() {
        RegisterDTORequest dto = new RegisterDTORequest(
                "12345678A", "Juan Perez", "600123456", "juan@mail.com", "pass123", "pass123"
        );
        assertDoesNotThrow(() -> validator.validate(dto));
    }

    @Test
    void testPasswordsDoNotMatch_throwsException() {
        RegisterDTORequest dto = new RegisterDTORequest(
                "Juan Pérez", "12345678A", "600123456", "juan@mail.com", "pass123", "pass321"
        );
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> validator.validate(dto));
        assertEquals("Las contraseñas no coinciden", ex.getMessage());
    }

    @Test
    void testEmptyUsername_throwsException() {
        RegisterDTORequest dto = new RegisterDTORequest(
                "Juan Perez", "", "600123456", "juan@mail.com", "pass123", "pass123"
        );
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> validator.validate(dto));
        assertEquals("El DNI es obligatorio", ex.getMessage());
    }
}