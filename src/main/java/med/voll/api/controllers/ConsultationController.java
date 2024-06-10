package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.dto.consultations.ConsultationDetails;
import med.voll.api.dto.consultations.DataScheduleConsultation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/consultation")
public class ConsultationController
{


    @PostMapping
    public ResponseEntity schedule(@RequestBody @Valid DataScheduleConsultation dataScheduleConsultation)
    {

        return ResponseEntity.ok(new ConsultationDetails(null,null,null, null));
    }
}
