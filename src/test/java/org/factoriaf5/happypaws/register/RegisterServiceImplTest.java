package org.factoriaf5.happypaws.register;

import org.factoriaf5.happypaws.register.dtos.RegisterDTORequest;
import org.factoriaf5.happypaws.register.dtos.RegisterDTOResponse;
import org.factoriaf5.happypaws.register.exceptions.UserAlreadyExistsException;
import org.factoriaf5.happypaws.register.services.RegisterServiceImpl;
import org.factoriaf5.happypaws.register.validators.RegisterValidator;
import org.factoriaf5.happypaws.temp.Role;
import org.factoriaf5.happypaws.temp.RoleRepository;
import org.factoriaf5.happypaws.temp.User;
import org.factoriaf5.happypaws.temp.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class RegisterServiceImplTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private RegisterValidator validator;
    private RegisterServiceImpl service;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        roleRepository = Mockito.mock(RoleRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        validator = new RegisterValidator();
        service = new RegisterServiceImpl(userRepository, roleRepository, passwordEncoder, validator);
    }

    @Test
    void testRegisterUser_success() {
        RegisterDTORequest dto = new RegisterDTORequest(
                "12345678A", "Juan Perez", "600123456", "juan@mail.com", "pass123", "pass123"
        );

        Role clientRole = new Role(1L, "CLIENT");
        Mockito.when(userRepository.findByUsername(dto.username())).thenReturn(Optional.empty());
        Mockito.when(roleRepository.findByName("CLIENT")).thenReturn(Optional.of(clientRole));
        Mockito.when(passwordEncoder.encode(dto.password())).thenReturn("hashedPass");

        RegisterDTOResponse response = service.registerUser(dto);

        assertEquals(dto.username(), response.username());
        assertEquals(dto.fullName(), response.fullName());
        assertEquals(dto.phone(), response.phone());
        assertEquals(dto.email(), response.email());
        Mockito.verify(userRepository).save(any(User.class));
    }

    @Test
    void testRegisterUser_userAlreadyExists_throwsException() {
        RegisterDTORequest dto = new RegisterDTORequest(
                "12345678A", "Juan Perez", "600123456", "juan@mail.com", "pass123", "pass123"
        );
        Mockito.when(userRepository.findByUsername(dto.username())).thenReturn(Optional.of(new User()));
        assertThrows(UserAlreadyExistsException.class, () -> service.registerUser(dto));
    }
}