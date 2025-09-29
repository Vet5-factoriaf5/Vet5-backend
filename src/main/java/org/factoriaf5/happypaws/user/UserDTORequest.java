package org.factoriaf5.happypaws.user;

import lombok.*;

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

    // TODO: Añadir campos de mascotas cuando se requieran más adelante
    // private List<PetRequest> pets;

}