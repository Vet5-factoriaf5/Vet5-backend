package org.factoriaf5.happypaws.register.services;

import org.factoriaf5.happypaws.register.dtos.RegisterDTORequest;
import org.factoriaf5.happypaws.register.dtos.RegisterDTOResponse;
import org.factoriaf5.happypaws.register.exceptions.UserAlreadyExistsException;
import org.factoriaf5.happypaws.register.mappers.RegisterMapper;
import org.factoriaf5.happypaws.register.validators.RegisterValidator;
//activar imports cuando tengamos creado user, role y repositorios autenticos 
//import org.factoriaf5.happypaws.user.User;
//import org.factoriaf5.happypaws.user.UserRepository;
//import org.factoriaf5.happypaws.role.RoleRepository;
//import org.factoriaf5.happypaws.role.Role;

//imports temporales para que no de error
import org.factoriaf5.happypaws.temp.User;
import org.factoriaf5.happypaws.temp.Role;
import org.factoriaf5.happypaws.temp.UserRepository;
import org.factoriaf5.happypaws.temp.RoleRepository;
//fin imports temporales

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegisterValidator validator;

    public RegisterServiceImpl(UserRepository userRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder,
                               RegisterValidator validator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @Override
    public RegisterDTOResponse registerUser(RegisterDTORequest dto) {

        // Validar datos
        validator.validate(dto);

        // Comprobar si el usuario ya existe
        Optional<User> existingUser = userRepository.findByUsername(dto.username());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(dto.username());
        }

        // Rol por defecto
        Role clientRole = roleRepository.findByName("CLIENT")
                .orElseThrow(() -> new RuntimeException("Rol CLIENT no encontrado"));

        // Encriptar password
        String hashedPassword = passwordEncoder.encode(dto.password());

        // Mapear DTO → User
        User newUser = RegisterMapper.dtoToEntity(dto, hashedPassword, clientRole);

        // Guardar en BD
        userRepository.save(newUser);

        // Devolver respuesta completa (sin contraseña)
        return RegisterDTOResponse.builder()
                .username(newUser.getUsername())
                .fullName(newUser.getFullName())
                .phone(newUser.getPhone())
                .email(newUser.getEmail())
                .build();
    }
}