package med.voll.api.dto.doctor;

import med.voll.api.models.Doctor;

public record DoctorsListData(
        Long id,
        String name,
        String speciality,
        String document,
        String email
)
{

    public DoctorsListData(Doctor doctor)
    {
        this(doctor.getId(),
                doctor.getName(),
                doctor.getSpeciality().toString(),
                doctor.getDocument(),
                doctor.getEmail());
    }

}
