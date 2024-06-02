package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.models.Doctor;
import med.voll.api.records.doctor.DoctorsData;
import med.voll.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public void doctorsRegister(@RequestBody @Valid DoctorsData doctorsData)
    {
        doctorRepository.save(new Doctor(doctorsData));
    }

}
