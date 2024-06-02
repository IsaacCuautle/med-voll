package med.voll.api.dto;

import med.voll.api.models.Doctor;

public record DoctorsListData(
        String name,
        String speciality,
        String document,
        String email
)
{

    public DoctorsListData(Doctor doctor)
    {
        this(doctor.getName(),
                doctor.getSpeciality().toString(),
                doctor.getDocument(),
                doctor.getEmail());
    }

}
