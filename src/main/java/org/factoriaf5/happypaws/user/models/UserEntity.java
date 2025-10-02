package org.factoriaf5.happypaws.user.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username; // DNI/NIE único

    @Column(nullable = false, unique = true)
    private String email;  // Email único

    private String phone;

    @Column(nullable = false)
    private String password;

    // Relaciones con mascotas y citas (comentadas temporalmente agrego un TODO para acordarnos)
    // TODO: descomentar cuando PetEntity y AppointmentEntity estén creadas

    // RELACIÓN CLIENT → PETS

    // @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    // @Builder.Default
    // private List<PetEntity> pets = new ArrayList<>();

    // fetch = LAZY: por defecto en @OneToMany, no cargamos todas las mascotas al cargar el cliente
    // Esto evita problemas de rendimiento si un cliente tiene muchas mascotas

    // RELACIÓN CLIENT → APPOINTMENTS (indirecta a través de Pet)

    // @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    // @Builder.Default
    // private List<AppointmentEntity> appointments = new ArrayList<>();

     // fetch = LAZY: se cargan solo cuando se necesitan, no al cargar el cliente
}

// orphanRemoval = true: si se elimina una mascota o cita del cliente, también se elimina de la BD
// cascade = CascadeType.ALL: operaciones en cliente se propagan a mascotas y citas