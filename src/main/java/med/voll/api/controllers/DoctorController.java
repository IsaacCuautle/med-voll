package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorsListData;
import med.voll.api.models.Doctor;
import med.voll.api.dto.doctor.DoctorsData;
import med.voll.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    // Registra a un medico
    @PostMapping
    public void doctorsRegister(@RequestBody @Valid DoctorsData doctorsData)
    {
        doctorRepository.save(new Doctor(doctorsData));
    }

    // devuelve una lista de medicos
    @GetMapping
    public Page<DoctorsListData> doctorsList(@PageableDefault(size = 2,sort = "name") Pageable pageable)
    {
        return doctorRepository.findAll(pageable)
                .map(DoctorsListData::new);
    }

}
