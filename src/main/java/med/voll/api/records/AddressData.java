package med.voll.api.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record AddressData(
        @NotBlank
        String street,

        @NotBlank
        String district,

        @NotBlank
        String city,

        @NotNull
        int number,

        @NotBlank
        String complement
) {
}
