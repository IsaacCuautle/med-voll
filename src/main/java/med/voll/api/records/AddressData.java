package med.voll.api.records;

public record AddressData(
        String street,
        String district,
        String city,
        int number,
        String complement
) {
}
