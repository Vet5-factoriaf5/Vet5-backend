package org.factoriaf5.happypaws.register;

//activar imports cuando tengamos creado user, role y repositorios autenticos 
import org.factoriaf5.happypaws.user.UserEntity;
import org.factoriaf5.happypaws.user.UserRepository;
import org.factoriaf5.happypaws.role.RoleRepository;
import org.factoriaf5.happypaws.role.RoleEntity;

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
        Optional<UserEntity> existingUser = userRepository.findByUsername(dto.username());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(dto.username());
        }

        // Rol por defecto
        RoleEntity clientRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Rol CLIENT no encontrado"));

        // Encriptar password
        String hashedPassword = passwordEncoder.encode(dto.password());

        // Mapear DTO → User
        UserEntity newUser = RegisterMapper.dtoToEntity(dto, hashedPassword, clientRole);

        // Guardar en BD
        userRepository.save(newUser);

        // Devolver respuesta completa (sin contraseña)
        return RegisterDTOResponse.builder()
                .fullName(newUser.getFullName())
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .phone(newUser.getPhone())
                .build();
    }
}