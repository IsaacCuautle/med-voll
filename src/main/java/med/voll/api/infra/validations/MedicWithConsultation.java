package med.voll.api.infra.validations;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultations.DataScheduleConsultation;
import med.voll.api.repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicWithConsultation implements ConsultationValidation
{

    @Autowired
    ConsultationRepository repository;

    public void validate(DataScheduleConsultation data)
    {

        if (data.doctorId() == null) return;


        var doctorWithConsultation = repository.existsByDoctorIdAndDate(data.doctorId(), data.date());

        if(doctorWithConsultation)
        {
            throw new ValidationException("Ese medico ya tiene una cita para este dia");
        }
    }
}
