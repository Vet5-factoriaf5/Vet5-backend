package org.factoriaf5.happypaws.patient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PatientControllerTest {
        @Autowired
        MockMvc mockMvc;

        @Test
        @DisplayName("Root Path exists")
        void testRootPathExists() throws Exception {

                MockHttpServletResponse response = mockMvc.perform(get("/"))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse();

                assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
                assertThat(response.getContentAsString(), containsString("Welcome Spring Security"));

        }

        @Test
        @DisplayName("Public Path exists")
        void testPublicPathExists() throws Exception {

                MockHttpServletResponse response = mockMvc.perform(get("/public"))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse();

                assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
                assertThat(response.getContentAsString(), containsString("Public path"));

        }

        @Test
        @DisplayName("Private Path exists")
        void testPrivatePathExists() throws Exception {
                MockHttpServletResponse response = mockMvc.perform(get("/private"))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse();

                assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
                assertThat(response.getContentAsString(), containsString("Private path"));
        }
}
