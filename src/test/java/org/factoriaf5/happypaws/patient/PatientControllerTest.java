package org.factoriaf5.happypaws.patient;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import org.factoriaf5.happypaws.patient.dtos.PatientDTORequest;
import org.factoriaf5.happypaws.patient.dtos.PatientDTOResponse;
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

                when(patientService.createPatient(dto)).thenReturn(null);

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

                when(patientService.createPatient(dto)).thenReturn(response);
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
}
