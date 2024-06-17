package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AddressData;
import med.voll.api.dto.doctor.DoctorDataResponse;
import med.voll.api.dto.doctor.DoctorDataUpdate;
import med.voll.api.dto.doctor.DoctorsListData;
import med.voll.api.models.Doctor;
import med.voll.api.dto.doctor.DoctorsData;
import med.voll.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctor")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    // Registra a un medico
    @PostMapping
    public ResponseEntity<DoctorDataResponse> doctorsRegister(
            @RequestBody @Valid DoctorsData doctorsData,
            UriComponentsBuilder uriComponentsBuilder
    )
    {
        Doctor doctor = doctorRepository.save(new Doctor(doctorsData));
        DoctorDataResponse doctorDataResponse = new DoctorDataResponse(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getDocument(),
                new AddressData(
                        doctor.getAddress().getStreet(),
                        doctor.getAddress().getDistrict(),
                        doctor.getAddress().getCity(),
                        doctor.getAddress().getNumber(),
                        doctor.getAddress().getComplement()
                )
        );

        URI url = uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(url).body(doctorDataResponse);
    }

    // devuelve una lista de medicos
    @GetMapping
    public Page<DoctorsListData> doctorsList(@PageableDefault(size = 2,sort = "name") Pageable pageable)
    {
        return doctorRepository.findByActiveTrue(pageable)
                .map(DoctorsListData::new);
    }

    // Actualizar medico
    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDataResponse> doctorUpdate(@RequestBody @Valid DoctorDataUpdate doctorDataUpdate)
    {
        Doctor doctor = doctorRepository.getReferenceById(doctorDataUpdate.id());
        doctor.updateDoctorData(doctorDataUpdate);
        return ResponseEntity.ok(
                new DoctorDataResponse(
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getEmail(),
                        doctor.getPhone(),
                        doctor.getDocument(),
                        new AddressData(
                                        doctor.getAddress().getStreet(),
                                        doctor.getAddress().getDistrict(),
                                        doctor.getAddress().getCity(),
                                        doctor.getAddress().getNumber(),
                                        doctor.getAddress().getComplement()
                        )
                )
        );
    }

    // Eliminar un medico de la db
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DoctorDataResponse> doctorDelete(@PathVariable Long id)
    {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.disableDoctor();
        return ResponseEntity.noContent().build();
    }

    // Trae un medico por su id
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DoctorDataResponse> doctorGet(@PathVariable Long id)
    {
        Doctor doctor = doctorRepository.getReferenceById(id);
        var doctorData = new DoctorDataResponse(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getDocument(),
                    new AddressData(
                            doctor.getAddress().getStreet(),
                            doctor.getAddress().getDistrict(),
                            doctor.getAddress().getCity(),
                            doctor.getAddress().getNumber(),
                            doctor.getAddress().getComplement()
                    )
        );
        return ResponseEntity.ok(doctorData);
    }

}
