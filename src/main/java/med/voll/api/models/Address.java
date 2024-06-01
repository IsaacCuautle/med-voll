package med.voll.api.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address
{
    private String street;
    private Integer number;
    private String complement;
    private String district;
    private String city;
}
