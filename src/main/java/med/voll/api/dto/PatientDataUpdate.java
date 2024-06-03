package med.voll.api.dto;

import jakarta.validation.Valid;

public record PatientDataUpdate(
            Long id,
            String name,
            String phone,
            @Valid AddressData address
)
{

}
