package org.factoriaf5.happypaws.patient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;

import org.factoriaf5.happypaws.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(properties = "api-endpoint=/v1")
public class PatientControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private PatientServiceImpl patientService;

        @MockitoBean
        private UserService userService;

        @Autowired
        private ObjectMapper mapper;

        @Test
        @DisplayName("Should return 400 when patient name is blank")
        void testCreatePatient_ShouldReturn400_IfNameBlank() throws Exception {
                PatientDTORequest dto = new PatientDTORequest("", 0, "Labrador", "Female", 123L);
                String json = mapper.writeValueAsString(dto);

                mockMvc.perform(post("/v1/patients")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Should return 204 when service returns null")
        void testCreatePatient_ShouldReturn204_IfServiceReturnsNull() throws Exception {
                PatientDTORequest dto = new PatientDTORequest("bobi", 0, "Labrador", "Female", 123L);
                String json = mapper.writeValueAsString(dto);

                when(userService.getUserById(dto.idUser())).thenReturn(null);

                mockMvc.perform(post("/v1/patients")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("Should return 201 and patient when created successfully")
        void testCreatePatient_ShouldReturn201_WhenCreated() throws Exception {
                PatientDTORequest dto = new PatientDTORequest("Anna", 0, "Labrador", "Female", 123L);
                String json = mapper.writeValueAsString(dto);

                PatientDTOResponse response = new PatientDTOResponse(1L, "Bobby", 0, "Labrador", "Female",
                                LocalDateTime.now(), LocalDateTime.now());

                when(patientService.storeEntity(dto)).thenReturn(response);
                mockMvc.perform(post("/v1/patients")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(response.id()))
                                .andExpect(jsonPath("$.name").value(response.name()))
                                .andExpect(jsonPath("$.age").value(response.age()))
                                .andExpect(jsonPath("$.breed").value(response.breed()))
                                .andExpect(jsonPath("$.gender").value(response.gender()))
                                .andExpect(jsonPath("$.createdAt").exists())
                                .andExpect(jsonPath("$.updatedAt").exists());
        }

        @Test
        @DisplayName("Should update patient successfully")
        void testUpdatePatient() throws Exception {
                PatientDTORequest dto = new PatientDTORequest("Bobby", 6, "Labrador", "Male", 123L);
                PatientDTOResponse response = new PatientDTOResponse(1L, "Bobby", 6, "Labrador", "Male",
                                LocalDateTime.now(), LocalDateTime.now());

                when(patientService.update(eq(1L), any(PatientDTORequest.class))).thenReturn(response);

                mockMvc.perform(put("/v1/patients/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(1L))
                                .andExpect(jsonPath("$.name").value("Bobby"));
        }

        @Test
        @DisplayName("Should delete patient successfully")
        void testDeletePatient() throws Exception {
                mockMvc.perform(delete("/v1/patients/1"))
                                .andExpect(status().isNoContent());

                verify(patientService).delete(1L);
        }
}
