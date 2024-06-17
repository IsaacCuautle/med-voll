package med.voll.api.dto.doctor;

import med.voll.api.dto.AddressData;

public record DoctorDataResponse(
         Long id,
         String name,
         String email,
         String phone,
         String document,
         AddressData address
)
{

}
