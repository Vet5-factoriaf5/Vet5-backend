package org.factoriaf5.happypaws.register;

public record RegisterDTORequest(
        String fullName,        // Nombre completo
        String username,        // DNI/NIE
        String email,
        String phone,
        String password,
        String confirmPassword
) {}