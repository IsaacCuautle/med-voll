package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorDataUpdate (
        @NotNull
        Long id,

        @NotBlank
        String name,

        String document,
        AddressData address
) {

}
