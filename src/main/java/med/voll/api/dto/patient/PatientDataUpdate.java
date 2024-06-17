package med.voll.api.dto.patient;

import jakarta.validation.Valid;
import med.voll.api.dto.AddressData;

public record PatientDataUpdate(
            Long id,
            String name,
            String phone,
            @Valid AddressData address
)
{

}
