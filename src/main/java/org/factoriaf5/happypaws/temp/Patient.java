package org.factoriaf5.happypaws.temp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

// Esta es la versión mínima de Patient para que JPA pueda mapearlo correctamente.
// Cuando Yely suba la entidad real, esta se reemplazará.
@Data
@Entity
public class Patient {

    @Id
    private Long id;
    private String name;
    private Long idUser;

}