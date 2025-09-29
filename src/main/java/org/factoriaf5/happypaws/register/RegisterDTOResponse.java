package org.factoriaf5.happypaws.register;

import lombok.Builder;

@Builder
public record RegisterDTOResponse(
        String username,
        String fullName,
        String email,
        String phone
) {}