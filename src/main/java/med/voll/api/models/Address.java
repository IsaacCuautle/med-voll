package med.voll.api.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AddressData;

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

    public Address(AddressData address) {
        this.street = address.street();
        this.number = address.number();
        this.complement = address.complement();
        this.district = address.district();
        this.city = address.city();
    }

    public Address updateAdress(AddressData address)
    {
        this.street = address.street();
        this.number = address.number();
        this.complement = address.complement();
        this.district = address.district();
        this.city = address.city();
        return this;
    }
}
