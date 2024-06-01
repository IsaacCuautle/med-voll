package med.voll.api.records.doctor;


import med.voll.api.records.AddressData;

public record DoctorsData(
        String name,
        String email,
        String document,
        Speciality speciality,
        AddressData address
)
{

}
