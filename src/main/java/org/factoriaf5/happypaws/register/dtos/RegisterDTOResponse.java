package org.factoriaf5.happypaws.register.dtos;

import lombok.Builder;

@Builder
public record RegisterDTOResponse(
        String username,
        String fullName,
        String email,
        String phone
) {}