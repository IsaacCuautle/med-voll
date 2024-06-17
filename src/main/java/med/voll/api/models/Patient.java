package med.voll.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.patient.PatientDataUpdate;
import med.voll.api.dto.patient.PatientData;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Patient")
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String document;
    private String phone;

    @Embedded
    private Address address;
    private Boolean active;

    public Patient(PatientData patientData) {
        this.active = true;
        this.name = patientData.name();
        this.email = patientData.email();
        this.phone = patientData.phone();
        this.document = patientData.document();
        this.address = new Address(patientData.address());
    }

    public void updatePatient(PatientDataUpdate patientDataUpdate) {
        if (patientDataUpdate.name() != null)
            this.name = patientDataUpdate.name();

        if (patientDataUpdate.phone() != null)
            this.phone = patientDataUpdate.phone();

        if (patientDataUpdate.address() != null)
            address.updateAdress(patientDataUpdate.address());
    }

    public void disablePatient() {
        this.active = false;
    }

}
