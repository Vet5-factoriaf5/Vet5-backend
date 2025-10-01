package org.factoriaf5.happypaws.user;

import lombok.*;


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

    // TODO: Añadir información de pacientes y citas cuando se requieran más adelante
    // private List<PatientsResponse> patients;
    // private List<AppointmentResponse> appointments;
}