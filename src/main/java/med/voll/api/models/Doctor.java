package med.voll.api.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.doctor.DoctorDataUpdate;
import med.voll.api.dto.doctor.DoctorsData;
import med.voll.api.dto.doctor.Speciality;

@Table(name = "doctors")
@Entity(name = "doctor")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Doctor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Embedded
    private Address address;

    private Boolean active;


    public Doctor(DoctorsData doctorsData)
    {
        this.active = true;
        this.name = doctorsData.name();
        this.email = doctorsData.email();
        this.phone = doctorsData.phone();
        this.document = doctorsData.document();
        this.speciality = doctorsData.speciality();
        this.address =  new Address(doctorsData.address());
    }

    public void updateDoctorData(DoctorDataUpdate doctorDataUpdate)
    {
        if(doctorDataUpdate.name() != null)
        {
            this.name = doctorDataUpdate.name();
        }
        if(doctorDataUpdate.document() != null)
        {
            this.document = doctorDataUpdate.document();
        }
        if(doctorDataUpdate.address() != null)
        {
            this.address = address.updateAdress(doctorDataUpdate.address());
        }
    }

    public void disableDoctor() {
        this.active = false;
    }
}
