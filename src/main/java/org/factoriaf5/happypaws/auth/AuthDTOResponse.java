package org.factoriaf5.happypaws.auth;

public record AuthDTOResponse(
    String message,
    String username,
    String role
) {}
