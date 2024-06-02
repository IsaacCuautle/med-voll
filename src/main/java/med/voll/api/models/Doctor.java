package med.voll.api.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private long id;

    private String name;
    private String email;
    private String phone;
    private String document;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Embedded
    private Address address;

    public Doctor(DoctorsData doctorsData)
    {
        this.name = doctorsData.name();
        this.email = doctorsData.email();
        this.phone = doctorsData.phone();
        this.document = doctorsData.document();
        this.speciality = doctorsData.speciality();
        this.address =  new Address(doctorsData.address());
    }
}
