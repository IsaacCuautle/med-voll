package med.voll.api.dto.patient;

import med.voll.api.models.Patient;

public record PatientListData(
        Long id,
        String name,
        String email,
        String document)
{
    public PatientListData(Patient patient)
    {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getDocument());
    }
}