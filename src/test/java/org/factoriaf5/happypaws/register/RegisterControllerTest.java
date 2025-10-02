package org.factoriaf5.happypaws.register;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @SuppressWarnings("removal")
@MockBean
    private RegisterService registerService;

    @Test
    void testRegisterUser_returns201() throws Exception {
        // DTO que enviamos en la petición
        RegisterDTORequest dto = new RegisterDTORequest(
                "12345678A", "Juan Perez", "600123456", "juan@mail.com", "pass123", "pass123"
        );

        // Respuesta simulada del service
        RegisterDTOResponse response = RegisterDTOResponse.builder()
                .username(dto.username())
                .fullName(dto.fullName())
                .phone(dto.phone())
                .email(dto.email())
                .build();

        Mockito.when(registerService.registerUser(any(RegisterDTORequest.class))).thenReturn(response);

        // Petición POST simulada con MockMvc
        mockMvc.perform(post("/api/v1/register")  // la ruta se mantiene consistente con el controller
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(dto.username()))
                .andExpect(jsonPath("$.fullName").value(dto.fullName()))
                .andExpect(jsonPath("$.phone").value(dto.phone()))
                .andExpect(jsonPath("$.email").value(dto.email()));
    }
}