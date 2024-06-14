package med.voll.api.infra.validations;

import med.voll.api.dto.consultations.DataScheduleConsultation;

public interface ConsultationValidation
{
    public void validate(DataScheduleConsultation data);
}