package med.voll.api.infra.validations;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultations.DataScheduleConsultation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceSchedule implements ConsultationValidation
{

    // Valida el horario de la cita
    public void validate(DataScheduleConsultation data)
    {
        var currentHour = LocalDateTime.now();
        var consultingHour = data.date();
        var diference = Duration.between(currentHour,consultingHour).toMinutes() < 30;

        if (diference)
        {
            throw new ValidationException("La consulta debe tener 30 minutos de antocipacion");
        }
    }
}
