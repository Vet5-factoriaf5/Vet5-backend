package org.factoriaf5.happypaws.user.dtos;

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

    // TODO: Añadir información de mascotas y citas cuando se requieran más adelante
    // private List<PetResponse> pets;
    // private List<AppointmentResponse> appointments;
}