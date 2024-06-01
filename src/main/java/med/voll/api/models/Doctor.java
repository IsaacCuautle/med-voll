package med.voll.api.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.records.doctor.Speciality;

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
    private String document;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Embedded
    private Address address;
}
