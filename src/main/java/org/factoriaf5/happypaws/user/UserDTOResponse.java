package org.factoriaf5.happypaws.user;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTOResponse {

    private Long id;
    private String fullName;
    private String username; // DNI/NIE
    private String email;
    private String phone;

    // Devolvemos solo los ids de pacientes asociados
    private List<Long> patientIds;
}