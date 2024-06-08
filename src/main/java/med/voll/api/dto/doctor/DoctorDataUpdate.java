package med.voll.api.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.AddressData;

public record DoctorDataUpdate (
        @NotNull
        Long id,

        @NotBlank
        String name,

        String document,
        AddressData address
) {

}
