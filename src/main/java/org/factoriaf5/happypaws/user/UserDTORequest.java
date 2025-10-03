package org.factoriaf5.happypaws.user;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTORequest {

    private String fullName;
    private String username; // DNI/NIE
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;

    // Lista de pacientes asociados al usuario (ids)
    private List<Long> patientIds;
}