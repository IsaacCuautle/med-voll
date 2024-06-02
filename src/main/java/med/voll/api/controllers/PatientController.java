package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.models.Patient;
import med.voll.api.records.patient.PatientData;
import med.voll.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patient")
public class PatientController
{

        @Autowired
        private PatientRepository patientRepository;

        @PostMapping
        @Transactional
        public void registrar(@RequestBody @Valid PatientData patientData) {
            patientRepository.save(new Patient(patientData));
        }
}
