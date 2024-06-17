package med.voll.api.controllers;

import med.voll.api.dto.consultations.ConsultationDetails;
import med.voll.api.dto.consultations.DataScheduleConsultation;
import med.voll.api.dto.doctor.Speciality;
import med.voll.api.services.consultation.ConsultationSchedulingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DataScheduleConsultation> details;

    @Autowired
    private JacksonTester<ConsultationDetails> getDetails;

    @MockBean
    private ConsultationSchedulingService service;

    @Test
    @DisplayName("retorna estado 400 cuando los datos ingresados no son validos")
    @WithMockUser
    void schedulingConsultation1() throws Exception {
        // GIVEN && WHEN
        var response = mvc.perform(post("/consultation")).andReturn().getResponse();
        // THEN
        assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("retorna estado 200 cuando los datos ingresados son validos")
    @WithMockUser
    void schedulingConsultation2() throws Exception {
        // GIVEN && WHEN
        var date = LocalDateTime.now().plusHours(1);

        var data = new ConsultationDetails(
                2L,
                2L,
                date);

        when(service.scheduling(any())).thenReturn(data);
        var response = mvc.perform(post("/consultation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(details.write(new DataScheduleConsultation(
                        null,
                        2L,
                        2L,
                        Speciality.CARDIOLOGY,
                        date
                )).getJson())
        ).andReturn().getResponse();
        // THEN
        assertEquals(response.getStatus(), HttpStatus.OK.value());

        var jsonAwait = getDetails.write(data).getJson();

        assertEquals(response.getContentAsString(),jsonAwait);
    }
}