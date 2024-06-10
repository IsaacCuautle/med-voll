package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.patient.PatientDataUpdate;
import med.voll.api.dto.patient.PatientListData;
import med.voll.api.models.Patient;
import med.voll.api.dto.patient.PatientData;
import med.voll.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patient")
public class PatientController
{

        @Autowired
        private PatientRepository patientRepository;

        @GetMapping
        public Page<PatientListData> patientList(@PageableDefault(size = 2,sort = "name") Pageable pageable)
        {

                return patientRepository.findByActiveTrue(pageable)
                        .map(PatientListData::new);
        }


        @PostMapping
        @Transactional
        public void registrar(@RequestBody @Valid PatientData patientData) {
            patientRepository.save(new Patient(patientData));
        }

        @PutMapping
        @Transactional
        public void updatePatient(@RequestBody @Valid PatientDataUpdate patientDataUpdate) {
                var patient = patientRepository.getReferenceById(patientDataUpdate.id());
                patient.updatePatient(patientDataUpdate);
        }

        @DeleteMapping("/{id}")
        @Transactional
        public void deletePatient(@PathVariable Long id) {
                var paciente = patientRepository.getReferenceById(id);
                paciente.disablePatient();
        }
}
