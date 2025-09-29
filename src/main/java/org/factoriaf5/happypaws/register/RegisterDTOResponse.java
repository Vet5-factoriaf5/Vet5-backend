package org.factoriaf5.happypaws.register;

import lombok.Builder;

@Builder
public record RegisterDTOResponse(
        Long id,
        String fullName,
        String username,
        String email,
        String phone
) {}