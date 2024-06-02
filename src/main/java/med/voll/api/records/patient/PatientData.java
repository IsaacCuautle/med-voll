package med.voll.api.records.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.records.AddressData;

public record PatientData(
            @NotBlank
            String name,

            @NotBlank
            @Email
            String email,

            @NotBlank
            String phone,

            @NotBlank
            @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
            String document,

            @NotNull
            @Valid
            AddressData addressData
)
{

}

